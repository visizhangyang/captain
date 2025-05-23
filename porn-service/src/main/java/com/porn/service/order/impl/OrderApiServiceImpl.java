package com.porn.service.order.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.dto.AccountQueryProxyTeamsDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.common.vo.PageVo;
import com.porn.client.merchant.api.MerchantApiService;
import com.porn.client.merchant.dto.MerchantConfirmOrderDTO;
import com.porn.client.merchant.dto.MerchantQueryDTO;
import com.porn.client.merchant.vo.MerchantVo;
import com.porn.client.minio.api.MinioApiService;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.*;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderStatisticsVo;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.order.converter.OrderConverter;
import com.porn.service.order.dao.entity.OrderDO;
import com.porn.service.order.dao.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service

@Transactional(rollbackFor = {Exception.class})
public class OrderApiServiceImpl implements OrderApiService {
    private static final Logger log = LoggerFactory.getLogger(OrderApiServiceImpl.class);


    @Autowired
    private OrderMapper orderMapper;


    @Autowired
    private OrderConverter orderConverter;


    @Autowired
    private MinioApiService minioApiService;


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private MerchantApiService merchantApiService;

    @Autowired
    private ParamsetApiService paramsetApiService;

    public OrderVo queryOrder(OrderQueryDTO orderQueryDTO) {
        List<OrderVo> orderVoList = queryOrderList(orderQueryDTO);
        return ObjectUtil.isEmpty(orderVoList) ? null : orderVoList.get(0);
    }

    public List<OrderVo> queryOrderList(OrderQueryDTO orderQueryDTO) {
        List<OrderDO> merchantList = ChainWrappers.lambdaQueryChain(orderMapper)
                .eq(ObjectUtil.isNotEmpty(orderQueryDTO.getAccountId()), OrderDO::getAccountId, orderQueryDTO.getAccountId())
                .in(ObjectUtil.isNotEmpty(orderQueryDTO.getAccountIdList()), OrderDO::getAccountId, orderQueryDTO.getAccountIdList())
                .eq(ObjectUtil.isNotEmpty(orderQueryDTO.getMerchantId()), OrderDO::getMerchantId, orderQueryDTO.getMerchantId())
                .in(ObjectUtil.isNotEmpty(orderQueryDTO.getMerchantIdList()), OrderDO::getMerchantId, orderQueryDTO.getMerchantIdList())
                .ge(ObjectUtil.isNotEmpty(orderQueryDTO.getStartTime()), BaseDO::getCreateTime, orderQueryDTO.getStartTime())
                .le(ObjectUtil.isNotEmpty(orderQueryDTO.getEndTime()), BaseDO::getCreateTime, orderQueryDTO.getEndTime())
                .eq(ObjectUtil.isNotEmpty(orderQueryDTO.getOrderStatus()), OrderDO::getOrderStatus, orderQueryDTO.getOrderStatus())
                .in(ObjectUtil.isNotEmpty(orderQueryDTO.getOrderStatusList()), OrderDO::getOrderStatus, orderQueryDTO.getOrderStatusList())
                .eq(ObjectUtil.isNotEmpty(orderQueryDTO.getId()), BaseDO::getId, orderQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(orderQueryDTO.getOrderType()), OrderDO::getOrderType, orderQueryDTO.getOrderType())
                .eq(ObjectUtil.isNotEmpty(orderQueryDTO.getWalletCode()), OrderDO::getWalletCode, orderQueryDTO.getWalletCode())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(BaseDO::getCreateTime)
                .list();

        if (ObjectUtil.isEmpty(merchantList)) {
            return Collections.emptyList();
        }

        List<OrderVo> orderVoList = this.orderConverter.toOrderVoList(merchantList);

        if (ObjectUtil.isNotEmpty(orderVoList)) {
            List<Long> accountIdList = orderVoList.stream()
                    .map(OrderVo::getAccountId)
                    .distinct()
                    .collect(Collectors.toList());

            List<AccountVo> accountVoList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().accountIdList(accountIdList).build());
            Map<Long, AccountVo> accountVoMap = accountVoList.stream()
                    .collect(Collectors.toMap(BaseVo::getId, Function.identity()));

            List<Long> merchantIdList = orderVoList.stream()
                    .map(OrderVo::getMerchantId)
                    .distinct()
                    .collect(Collectors.toList());

            List<MerchantVo> merchantVoList = this.merchantApiService.queryMerchantList(MerchantQueryDTO.builder().merchantIdList(merchantIdList).build());
            Map<Long, MerchantVo> merchantVoMap = merchantVoList.stream()
                    .collect(Collectors.toMap(BaseVo::getId, Function.identity()));

            orderVoList.forEach(orderVo -> {
                AccountVo accountVo = accountVoMap.get(orderVo.getAccountId());
                if (ObjectUtil.isNotEmpty(accountVo)) {
                    orderVo.setAccountAvatar(accountVo.getAvatar());
                    orderVo.setAccountAvatarUrl(accountVo.getAvatarUrl());
                    orderVo.setReceiveAddress(ObjectUtil.isNotEmpty(orderVo.getAddress()) ? orderVo.getAddress() : accountVo.getReceiveAddress());
                }

                MerchantVo merchantVo = merchantVoMap.get(orderVo.getMerchantId());
                if (ObjectUtil.isNotEmpty(merchantVo)) {
                    orderVo.setMerchantAvatar(merchantVo.getAvatar());
                    orderVo.setMerchantAvatarUrl(merchantVo.getAvatarUrl());
                }
            });
        }

        return orderVoList;

    }

    public PageVo<OrderVo> queryPage(OrderQueryPageDTO orderQueryPageDTO) {
        Page<OrderDO> page = new Page<>(orderQueryPageDTO.getPageStart().intValue(), orderQueryPageDTO.getPageSize().intValue());

        LambdaQueryWrapper<OrderDO> queryWrapper = new LambdaQueryWrapper<OrderDO>()
                .eq(ObjectUtil.isNotEmpty(orderQueryPageDTO.getMerchantId()), OrderDO::getMerchantId, orderQueryPageDTO.getMerchantId())
                .eq(ObjectUtil.isNotEmpty(orderQueryPageDTO.getOrderStatus()), OrderDO::getOrderStatus, orderQueryPageDTO.getOrderStatus())
                .eq(ObjectUtil.isNotEmpty(orderQueryPageDTO.getOrderType()), OrderDO::getOrderType, orderQueryPageDTO.getOrderType())
                .eq(ObjectUtil.isNotEmpty(orderQueryPageDTO.getAccountId()), OrderDO::getAccountId, orderQueryPageDTO.getAccountId())
                .like(ObjectUtil.isNotEmpty(orderQueryPageDTO.getLkAccountName()), OrderDO::getAccountName, orderQueryPageDTO.getLkAccountName())
                .like(ObjectUtil.isNotEmpty(orderQueryPageDTO.getLkRemark()), OrderDO::getRemark, orderQueryPageDTO.getLkRemark())
                .in(ObjectUtil.isNotEmpty(orderQueryPageDTO.getAccountIdList()), OrderDO::getAccountId, orderQueryPageDTO.getAccountIdList())
                .in(ObjectUtil.isNotEmpty(orderQueryPageDTO.getMerchantIdList()), OrderDO::getMerchantId, orderQueryPageDTO.getMerchantIdList())
                .ge(ObjectUtil.isNotEmpty(orderQueryPageDTO.getStartTime()), BaseDO::getCreateTime, orderQueryPageDTO.getStartTime())
                .le(ObjectUtil.isNotEmpty(orderQueryPageDTO.getEndTime()), BaseDO::getCreateTime, orderQueryPageDTO.getEndTime())
                .in(ObjectUtil.isNotEmpty(orderQueryPageDTO.getOrderStatusList()), OrderDO::getOrderStatus, orderQueryPageDTO.getOrderStatusList())
                .eq(ObjectUtil.isNotEmpty(orderQueryPageDTO.getWalletCode()), OrderDO::getWalletCode, orderQueryPageDTO.getWalletCode())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(BaseDO::getCreateTime);

        IPage<OrderDO> orderPage = orderMapper.selectPage(page, queryWrapper);

        List<OrderVo> orderVoList = orderConverter.toOrderVoList(orderPage.getRecords());

        if (ObjectUtil.isNotEmpty(orderVoList)) {
            List<Long> accountIdList = orderVoList.stream()
                    .map(OrderVo::getAccountId)
                    .distinct()
                    .collect(Collectors.toList());

            List<AccountVo> accountVoList = accountApiService.queryAccountList(AccountQueryDTO.builder().accountIdList(accountIdList).build());
            Map<Long, AccountVo> accountVoMap = accountVoList.stream()
                    .collect(Collectors.toMap(BaseVo::getId, Function.identity()));

            List<Long> merchantIdList = orderVoList.stream()
                    .map(OrderVo::getMerchantId)
                    .distinct()
                    .collect(Collectors.toList());

            List<MerchantVo> merchantVoList = merchantApiService.queryMerchantList(MerchantQueryDTO.builder().merchantIdList(merchantIdList).build());
            Map<Long, MerchantVo> merchantVoMap = merchantVoList.stream()
                    .collect(Collectors.toMap(BaseVo::getId, Function.identity()));

            orderVoList.forEach(orderVo -> {
                AccountVo accountVo = accountVoMap.get(orderVo.getAccountId());
                if (ObjectUtil.isNotEmpty(accountVo)) {
                    orderVo.setAccountAvatar(accountVo.getAvatar());
                    orderVo.setAccountAvatarUrl(accountVo.getAvatarUrl());
                    orderVo.setAccountLevel(accountVo.getAccountLevel());
                    orderVo.setReceiveAddress(ObjectUtil.isNotEmpty(orderVo.getAddress()) ? orderVo.getAddress() : accountVo.getReceiveAddress());
                }

                MerchantVo merchantVo = merchantVoMap.get(orderVo.getMerchantId());
                if (ObjectUtil.isNotEmpty(merchantVo)) {
                    orderVo.setMerchantAvatar(merchantVo.getAvatar());
                    orderVo.setMerchantAvatarUrl(merchantVo.getAvatarUrl());
                }
            });
        }

        return PageVo.<OrderVo>builder()
                .pageStart(orderQueryPageDTO.getPageStart())
                .pageSize(orderQueryPageDTO.getPageSize())
                .total(orderPage.getTotal())
                .data(orderVoList)
                .build();

    }

    public PageVo<OrderVo> queryProxyPage(ProxyOrderQueryPageDTO proxyOrderQueryPageDTO) {
        AccountQueryProxyTeamsDTO accountQueryProxyTeamsDTO = AccountQueryProxyTeamsDTO.builder()
                .mngUserId(proxyOrderQueryPageDTO.getCurrentUserId())
                .build();

        List<Long> accountIdList = accountApiService.queryProxyTeams(accountQueryProxyTeamsDTO);

        if (ObjectUtil.isEmpty(accountIdList)) {
            return PageVo.<OrderVo>builder()
                    .pageStart(proxyOrderQueryPageDTO.getPageStart())
                    .pageSize(proxyOrderQueryPageDTO.getPageSize())
                    .total(CommonConst.LZERO)
                    .data(Collections.emptyList())
                    .build();
        }

        OrderQueryPageDTO orderQueryPageDTO = orderConverter.toOrderQueryPageDTO(proxyOrderQueryPageDTO);
        orderQueryPageDTO.setAccountIdList(accountIdList);

        return queryPage(orderQueryPageDTO);

    }

    public OrderVo saveOrUpdate(OrderSaveOrUpdateDTO orderSaveOrUpdateDTO) {
        if (ObjectUtil.isEmpty(orderSaveOrUpdateDTO.getId())) {
            // 新增订单
            OrderDO orderDO = orderConverter.toOrderDO(orderSaveOrUpdateDTO);
            orderDO.setOrderNo(genOrderNo());
            orderDO.setPlayStatus(0);

            if (ObjectUtil.isEmpty(orderSaveOrUpdateDTO.getAddress())) {
                AccountVo accountVo = accountApiService.queryAccount(AccountQueryDTO.builder()
                        .id(orderDO.getAccountId())
                        .build());
                if (ObjectUtil.isNotEmpty(accountVo)) {
                    orderDO.setAddress(accountVo.getReceiveAddress());
                }
            }

            if (orderMapper.insert(orderDO) <= 0) {
                throw new BusinessException("保存订单信息失败.");
            }

            OrderVo orderVo = queryOrder(OrderQueryDTO.builder().id(orderDO.getId()).build());

            if (OrderStatusEnum.CONFIRED.getStatus().equals(orderSaveOrUpdateDTO.getOrderStatus()) &&
                    ObjectUtil.isEmpty(orderSaveOrUpdateDTO.getNotifyMsg())) {
                merchantApiService.confirmOrder(MerchantConfirmOrderDTO.builder()
                        .id(orderVo.getMerchantId())
                        .amount(orderVo.getOrderAmount())
                        .build());
            }

            return orderVo;
        } else {
            // 更新订单
            boolean updated = ChainWrappers.lambdaUpdateChain(orderMapper)
                    .set(ObjectUtil.isNotEmpty(orderSaveOrUpdateDTO.getOrderStatus()), OrderDO::getOrderStatus, orderSaveOrUpdateDTO.getOrderStatus())
                    .set(ObjectUtil.isNotEmpty(orderSaveOrUpdateDTO.getFreeAmount()), OrderDO::getFreeAmount, orderSaveOrUpdateDTO.getFreeAmount())
                    .set(ObjectUtil.isNotEmpty(orderSaveOrUpdateDTO.getPlayStatus()), OrderDO::getPlayStatus, orderSaveOrUpdateDTO.getPlayStatus())
                    .set(ObjectUtil.isNotEmpty(orderSaveOrUpdateDTO.getRemark()), OrderDO::getRemark, orderSaveOrUpdateDTO.getRemark())
                    .set(BaseDO::getModifyTime, LocalDateTimeUtil.now())
                    .eq(BaseDO::getId, orderSaveOrUpdateDTO.getId())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .update();

            if (!updated) {
                throw new BusinessException("更新订单信息失败.");
            }

            OrderVo orderVo = queryOrder(OrderQueryDTO.builder().id(orderSaveOrUpdateDTO.getId()).build());

            if (OrderStatusEnum.CONFIRED.getStatus().equals(orderSaveOrUpdateDTO.getOrderStatus()) &&
                    ObjectUtil.isEmpty(orderSaveOrUpdateDTO.getNotifyMsg())) {
                merchantApiService.confirmOrder(MerchantConfirmOrderDTO.builder()
                        .id(orderVo.getMerchantId())
                        .amount(orderVo.getOrderAmount())
                        .build());
            }

            return orderVo;
        }

    }

    protected String genOrderNo() {

        LocalDateTime now = LocalDateTimeUtil.now();

        String year = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "yyyy");

        String month = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "MM");

        String day = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "dd");

        String randNum = String.format("%04d", new Object[]{Integer.valueOf((new Random()).nextInt(1000))});

        String incKey = "orderno_key" + LocalDateTimeUtil.format(now, "yyyyMMdd");

        Long incNum = this.redisTemplate.opsForValue().increment(incKey);

        this.redisTemplate.expire(incKey, 24L, TimeUnit.HOURS);

        return StrUtil.join("", new Object[]{"NO", randNum, month, year, day, String.valueOf(Math.abs(incNum.longValue()))});

    }


    public Boolean delete(OrderDeleteDTO orderDeleteDTO) {
        return ChainWrappers.lambdaUpdateChain(orderMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, orderDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }


    public OrderStatisticsVo orderStatistics(OrderStatisticsDTO orderStatisticsDTO) {
        // 统计已确认订单总数
        Integer totalOrderCount = ChainWrappers.lambdaQueryChain(orderMapper)
                .eq(OrderDO::getOrderStatus, OrderStatusEnum.CONFIRED.getStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .count();

// 统计已确认订单总金额
        QueryWrapper<OrderDO> amountQuery = new QueryWrapper<>();
        amountQuery.select("SUM(order_amount) AS orderAmount")
                .eq("order_status", OrderStatusEnum.CONFIRED.getStatus())
                .eq("del_flag", DelFlagEnum.NORMAL.getFlag());

        OrderDO orderAmountResult = orderMapper.selectOne(amountQuery);

        return OrderStatisticsVo.builder()
                .totalOrderCount(totalOrderCount == null ? 0L : totalOrderCount.longValue())
                .totalOrderAmount(orderAmountResult == null || orderAmountResult.getOrderAmount() == null
                        ? BigDecimal.ZERO : orderAmountResult.getOrderAmount())
                .build();

    }

    public Boolean audit(OrderAuditDTO orderAuditDTO) {
        // 查询订单信息
        OrderVo orderVo = queryOrder(OrderQueryDTO.builder().id(orderAuditDTO.getId()).build());
        if (orderVo == null) {
            throw new BusinessException("订单信息不存在.");
        }

        // 构造账户金额操作 DTO 并调用账户服务
        AccountAmountOperateDTO accountAmountOperateDTO = AccountAmountOperateDTO.builder()
                .id(orderVo.getAccountId())
                .amountType(AmountTypeEnum.SUBTOTAL_SUBFREEZE.getType())
                .bizId(orderVo.getId())
                .streamTypeEnum(StreamTypeEnum.WORKING_SUB)
                .operateAmount(orderVo.getOrderAmount())
                .build();
        this.accountApiService.operateAmount(accountAmountOperateDTO);

        // 更新订单状态为支付成功
        boolean updated = ChainWrappers.lambdaUpdateChain(orderMapper)
                .set(OrderDO::getOrderStatus, OrderStatusEnum.PAY_SUCCESS.getStatus())
                .eq(BaseDO::getId, orderAuditDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        return updated;

    }

    public Boolean keepAudit(OrderAuditDTO orderAuditDTO) {
        // 审核订单
        audit(orderAuditDTO);

        // 查询订单
        OrderVo orderVo = queryOrder(OrderQueryDTO.builder().id(orderAuditDTO.getId()).build());
        if (orderVo == null) {
            throw new BusinessException("订单信息不存在.");
        }

        // 解冻资金：增加可用余额
        AccountAmountOperateDTO freeAmountOperateDTO = AccountAmountOperateDTO.builder()
                .id(orderVo.getAccountId())
                .amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType())
                .bizId(orderVo.getId())
                .streamTypeEnum(StreamTypeEnum.WORKING_ADD)
                .operateAmount(orderVo.getFreeAmount())
                .build();
        accountApiService.operateAmount(freeAmountOperateDTO);

        // 代理分润处理
        proxyFreeProcess(orderVo);

        // 更新订单状态为 CONFIRMED
        OrderSaveOrUpdateDTO orderUpdateDTO = OrderSaveOrUpdateDTO.builder()
                .id(orderVo.getId())
                .orderStatus(OrderStatusEnum.CONFIRED.getStatus())
                .freeAmount(freeAmountOperateDTO.getOperateAmount())
                .build();
        OrderVo newOrderVo = saveOrUpdate(orderUpdateDTO);

        // KEEP 流水记录：添加完整订单金额
        AccountAmountOperateDTO keepAmountOperateDTO = AccountAmountOperateDTO.builder()
                .id(orderVo.getAccountId())
                .amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType())
                .bizId(orderVo.getId())
                .streamTypeEnum(StreamTypeEnum.KEEP)
                .operateAmount(orderVo.getOrderAmount())
                .build();
        accountApiService.operateAmount(keepAmountOperateDTO);

        return Boolean.TRUE;

    }

    protected void proxyFreeProcess(OrderVo orderVo) {
        AccountVo accountVo = accountApiService.queryAccount(
                AccountQueryDTO.builder().id(orderVo.getAccountId()).build()
        );
        if (accountVo.getParentId() == null) {
            return;
        }

        ParamsetVo paramsetVo = paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());

        // 代理等级与对应分润比例映射
//        Map<Integer, BigDecimal> proxyRateMap = Map.of(
//                1, paramsetVo.getProxyLevel1Rate(),
//                2, paramsetVo.getProxyLevel2Rate(),
//                3, paramsetVo.getProxyLevel3Rate()
//        );

        Map<Integer, BigDecimal> proxyRateMap = new HashMap<>();
        proxyRateMap.put(1, paramsetVo.getProxyLevel1Rate());
        proxyRateMap.put(2, paramsetVo.getProxyLevel2Rate());
        proxyRateMap.put(3, paramsetVo.getProxyLevel3Rate());

        // 当前要查询的上级账号ID
        Long currentParentId = accountVo.getParentId();

        for (int level = 1; level <= 3; level++) {
            if (currentParentId == null) {
                return;
            }

            AccountVo parentAccountVo = accountApiService.queryAccount(
                    AccountQueryDTO.builder().accountIdList(Collections.singletonList(currentParentId)).build()
            );

            if (parentAccountVo == null) {
                return;
            }

            BigDecimal rate = proxyRateMap.get(level);
            if (rate != null) {
                BigDecimal amount = NumberUtil.mul(orderVo.getOrderAmount(), rate)
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

                AccountAmountOperateDTO operateDTO = AccountAmountOperateDTO.builder()
                        .id(parentAccountVo.getId())
                        .amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType())
                        .bizId(orderVo.getId())
                        .streamTypeEnum(StreamTypeEnum.valueOf("PROXY_" + level)) // 枚举名为 PROXY_1, PROXY_2, PROXY_3
                        .operateAmount(amount)
                        .build();

                accountApiService.operateAmount(operateDTO);
            }

            currentParentId = parentAccountVo.getParentId();
        }

    }

    public Boolean freeze(OrderFreezeDTO orderFreezeDTO) {
        return ChainWrappers.lambdaUpdateChain(this.orderMapper)
                .set(OrderDO::getOrderStatus, OrderStatusEnum.FREEZE.getStatus())
                .eq(BaseDO::getId, orderFreezeDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

    public Boolean unfreeze(OrderUnFreezeDTO orderUnFreezeDTO) {
        return ChainWrappers.lambdaUpdateChain(this.orderMapper)
                .set(OrderDO::getOrderStatus, OrderStatusEnum.WAIT_PAY.getStatus())
                .set(BaseDO::getCreateTime, LocalDateTimeUtil.now())
                .set(BaseDO::getModifyTime, LocalDateTimeUtil.now())
                .eq(BaseDO::getId, orderUnFreezeDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }

    public Boolean orderTimeOut(OrderTimeOutDTO orderTimeOutDTO) {
        // 查询订单
        OrderVo orderVo = queryOrder(
                OrderQueryDTO.builder()
                        .orderStatus(OrderStatusEnum.WAIT_PAY.getStatus())
                        .id(orderTimeOutDTO.getId())
                        .build()
        );

        if (ObjectUtil.isEmpty(orderVo)) {
            throw new BusinessException("订单信息不存在");
        }

        // 解冻资金，转为可用余额
        AccountAmountOperateDTO accountAmountOperateDTO = AccountAmountOperateDTO.builder()
                .id(orderVo.getAccountId())
                .amountType(AmountTypeEnum.ADDAVAILABLE_SUBFREEZE.getType())
                .bizId(orderVo.getId())
                .streamTypeEnum(StreamTypeEnum.WORKING_UNLOCK)
                .operateAmount(orderVo.getOrderAmount())
                .build();

        accountApiService.operateAmount(accountAmountOperateDTO);

        // 更新订单状态为支付超时
        OrderSaveOrUpdateDTO orderSaveOrUpdateDTO = OrderSaveOrUpdateDTO.builder()
                .id(orderVo.getId())
                .orderStatus(OrderStatusEnum.PAY_TIMEOUT.getStatus())
                .build();

        saveOrUpdate(orderSaveOrUpdateDTO);

        return Boolean.TRUE;

    }

    public BigDecimal sumOrderAmount(OrderQueryDTO orderQueryDTO) {
        BigDecimal rs = this.orderMapper.sumOrderAmount(orderQueryDTO);
        return ObjectUtil.isEmpty(rs) ? BigDecimal.ZERO : rs;
    }
}
