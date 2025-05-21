
package com.porn.service.recharge.impl;


import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.dto.AccountQueryProxyTeamsDTO;
import com.porn.client.account.dto.AccountSaveOrUpdateDTO;
import com.porn.client.account.enums.AccountLevelEnum;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.recharge.api.RechargeApiService;
import com.porn.client.recharge.dto.*;
import com.porn.client.recharge.enums.RechargeStatusEnum;
import com.porn.client.recharge.vo.RechargeVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.wallet.api.WalletAddressApiService;
import com.porn.client.wallet.dto.WalletAddressQueryDTO;
import com.porn.client.wallet.dto.WalletAddressSaveOrUpdateDTO;
import com.porn.client.wallet.enums.AddressStatusEnum;
import com.porn.client.wallet.vo.WalletAddressVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.recharge.converter.RechargeConverter;
import com.porn.service.recharge.dao.entity.RechargeDO;
import com.porn.service.recharge.dao.mapper.RechargeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service

@Transactional(rollbackFor = {Exception.class})
 public class RechargeApiServiceImpl implements RechargeApiService {
    /*  54 */   private static final Logger log = LoggerFactory.getLogger(RechargeApiServiceImpl.class);



    @Autowired
     private RechargeMapper rechargeMapper;



    @Autowired
     private RechargeConverter rechargeConverter;



    @Autowired
     private AccountApiService accountApiService;



    @Autowired
     private WalletAddressApiService walletAddressApiService;


    @Autowired
     private RedisTemplate redisTemplate;


    @Autowired
     private ParamsetApiService paramsetApiService;

    /*  78 */   private final Map<String, RechargeCacheItemDTO> rechargeCacheItemMap = new ConcurrentHashMap<>();




    public RechargeVo queryRecharge(RechargeQueryDTO rechargeQueryDTO) {
        /*  82 */
        List<RechargeVo> rechargeVoList = queryRechargeList(rechargeQueryDTO);
        /*  83 */
        return ObjectUtil.isEmpty(rechargeVoList) ? null : rechargeVoList.get(0);

    }












    public List<RechargeVo> queryRechargeList(RechargeQueryDTO rechargeQueryDTO) {
        LambdaQueryChainWrapper<RechargeDO> query = ChainWrappers.lambdaQueryChain(rechargeMapper);

        if (ObjectUtil.isNotEmpty(rechargeQueryDTO.getId())) {
            query.eq(BaseDO::getId, rechargeQueryDTO.getId());
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryDTO.getWalletCode())) {
            query.eq(RechargeDO::getWalletCode, rechargeQueryDTO.getWalletCode());
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryDTO.getAccountId())) {
            query.eq(RechargeDO::getAccountId, rechargeQueryDTO.getAccountId());
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryDTO.getHasHash()) && rechargeQueryDTO.getHasHash()) {
            query.isNotNull(RechargeDO::getHash);
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryDTO.getStartTime())) {
            query.ge(BaseDO::getCreateTime, rechargeQueryDTO.getStartTime());
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryDTO.getHashList())) {
            query.in(RechargeDO::getHash, rechargeQueryDTO.getHashList());
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryDTO.getStatus())) {
            query.eq(RechargeDO::getStatus, rechargeQueryDTO.getStatus());
        }
        query.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

        List<RechargeDO> rechargeList = query.list();

        if (ObjectUtil.isEmpty(rechargeList)) {
            return Collections.emptyList();
        }

        return rechargeConverter.toRechargeVoList(rechargeList);

    }



    public PageVo<RechargeVo> queryPage(RechargeQueryPageDTO rechargeQueryPageDTO) {
        Page<RechargeDO> page = new Page<>(rechargeQueryPageDTO.getPageStart().intValue(), rechargeQueryPageDTO.getPageSize().intValue());

        LambdaQueryWrapper<RechargeDO> queryWrapper = new LambdaQueryWrapper<>();

        if (ObjectUtil.isNotEmpty(rechargeQueryPageDTO.getRechargeNo())) {
            queryWrapper.eq(RechargeDO::getRechargeNo, rechargeQueryPageDTO.getRechargeNo());
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryPageDTO.getWalletCode())) {
            queryWrapper.eq(RechargeDO::getWalletCode, rechargeQueryPageDTO.getWalletCode());
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryPageDTO.getStatus())) {
            queryWrapper.eq(RechargeDO::getStatus, rechargeQueryPageDTO.getStatus());
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryPageDTO.getAccountId())) {
            queryWrapper.eq(RechargeDO::getAccountId, rechargeQueryPageDTO.getAccountId());
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryPageDTO.getAccountIdList())) {
            queryWrapper.in(RechargeDO::getAccountId, rechargeQueryPageDTO.getAccountIdList());
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryPageDTO.getLkAccountName())) {
            queryWrapper.like(RechargeDO::getAccountName, rechargeQueryPageDTO.getLkAccountName());
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryPageDTO.getLkRemark())) {
            queryWrapper.like(RechargeDO::getRemark, rechargeQueryPageDTO.getLkRemark());
        }
        if (ObjectUtil.isNotEmpty(rechargeQueryPageDTO.getLkWalletRemark())) {
            queryWrapper.like(RechargeDO::getWalletRemark, rechargeQueryPageDTO.getLkWalletRemark());
        }

        queryWrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(BaseDO::getCreateTime);

        IPage<RechargeDO> rechargePage = rechargeMapper.selectPage(page, queryWrapper);

        List<RechargeVo> rechargeVoList = rechargeConverter.toRechargeVoList(rechargePage.getRecords());

        return PageVo.<RechargeVo>builder()
                .pageStart(rechargeQueryPageDTO.getPageStart())
                .pageSize(rechargeQueryPageDTO.getPageSize())
                .total(rechargePage.getTotal())
                .data(rechargeVoList)
                .build();

    }






    public PageVo<RechargeVo> queryProxyPage(ProxyRechargeQueryPageDTO proxyRechargeQueryPageDTO) {
        AccountQueryProxyTeamsDTO proxyTeamsDTO = AccountQueryProxyTeamsDTO.builder()
                .mngUserId(proxyRechargeQueryPageDTO.getCurrentUserId())
                .build();

        List<Long> accountIdList = accountApiService.queryProxyTeams(proxyTeamsDTO);

        if (ObjectUtil.isEmpty(accountIdList)) {
            return PageVo.<RechargeVo>builder()
                    .pageStart(proxyRechargeQueryPageDTO.getPageStart())
                    .pageSize(proxyRechargeQueryPageDTO.getPageSize())
                    .total(CommonConst.LZERO)
                    .data(Collections.emptyList())
                    .build();
        }

        RechargeQueryPageDTO rechargeQueryPageDTO = rechargeConverter.toRechargeQueryPageDTO(proxyRechargeQueryPageDTO);
        rechargeQueryPageDTO.setAccountIdList(accountIdList);

        return queryPage(rechargeQueryPageDTO);

    }



    public RechargeVo saveOrUpdate(RechargeSaveOrUpdateDTO rechargeSaveOrUpdateDTO) {
        if (ObjectUtil.isEmpty(rechargeSaveOrUpdateDTO.getId())) {
            // 新增充值

            AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder()
                    .id(rechargeSaveOrUpdateDTO.getAccountId())
                    .build();
            AccountVo accountVo = accountApiService.queryAccount(accountQueryDTO);
            if (ObjectUtil.isEmpty(accountVo)) {
                throw new BusinessException("账户信息不存在.");
            }

            RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder()
                    .accountId(rechargeSaveOrUpdateDTO.getAccountId())
                    .walletCode(rechargeSaveOrUpdateDTO.getWalletCode())
                    .status(RechargeStatusEnum.WAIT_PAY.getStatus())
                    .build();
            RechargeVo rechargeVo = queryRecharge(rechargeQueryDTO);

            if (ObjectUtil.isNotEmpty(rechargeVo)) {
                // 存在等待支付中的充值，复用钱包信息更新钱包状态
                RechargeDO rechargeDO1 = RechargeDO.builder()
                        .rechargeNo(genRechargeNo())
                        .accountId(accountVo.getId())
                        .accountName(accountVo.getName())
                        .fromAddress("")
                        .hash("")
                        .walletId(rechargeVo.getWalletId())
                        .walletCode(rechargeVo.getWalletCode())
                        .walletName(rechargeVo.getWalletName())
                        .walletAddress(rechargeVo.getWalletAddress())
                        .amount(rechargeSaveOrUpdateDTO.getAmount())
                        .gasAmount(rechargeSaveOrUpdateDTO.getGasAmount())
                        .receiveAmount(rechargeSaveOrUpdateDTO.getReceiveAmount())
                        .status(RechargeStatusEnum.WAIT_PAY.getStatus())
                        .remark(rechargeSaveOrUpdateDTO.getRemark())
                        .walletRemark(rechargeVo.getWalletRemark())
                        .build();

                WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO1 = WalletAddressSaveOrUpdateDTO.builder()
                        .id(rechargeVo.getWalletId())
                        .addressStatus(AddressStatusEnum.PAYING.getStatus())
                        .status(EnableStatusEnum.ENABLE.getStatus())
                        .lockTime(LocalDateTimeUtil.now())
                        .build();
                walletAddressApiService.saveOrUpdate(walletAddressSaveOrUpdateDTO1);

                if (rechargeMapper.insert(rechargeDO1) <= 0) {
                    throw new BusinessException("创建充值信息失败.");
                }
                return queryRecharge(RechargeQueryDTO.builder().id(rechargeDO1.getId()).build());
            }

            // 不存在等待支付充值，查询钱包地址列表
            WalletAddressQueryDTO walletAddressQueryDTO = WalletAddressQueryDTO.builder()
                    .addressStatus(AddressStatusEnum.NORMAL.getStatus())
                    .code(rechargeSaveOrUpdateDTO.getWalletCode())
                    .status(EnableStatusEnum.ENABLE.getStatus())
                    .build();
            List<WalletAddressVo> walletAddressVoList = walletAddressApiService.queryWalletAddressList(walletAddressQueryDTO);
            if (ObjectUtil.isEmpty(walletAddressVoList)) {
                throw new BusinessException("创建充值信息失败, 请稍后重试.");
            }
            WalletAddressVo walletAddressVo = walletAddressVoList.get(0);

            WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO = WalletAddressSaveOrUpdateDTO.builder()
                    .id(walletAddressVo.getId())
                    .addressStatus(AddressStatusEnum.PAYING.getStatus())
                    .code(rechargeSaveOrUpdateDTO.getWalletCode())
                    .status(EnableStatusEnum.ENABLE.getStatus())
                    .lockTime(LocalDateTimeUtil.now())
                    .build();
            walletAddressApiService.saveOrUpdate(walletAddressSaveOrUpdateDTO);

            RechargeDO rechargeDO = RechargeDO.builder()
                    .rechargeNo(genRechargeNo())
                    .accountId(accountVo.getId())
                    .accountName(accountVo.getName())
                    .walletId(walletAddressVo.getId())
                    .walletName(walletAddressVo.getName())
                    .walletCode(walletAddressVo.getCode())
                    .walletAddress(walletAddressVo.getAddress())
                    .amount(rechargeSaveOrUpdateDTO.getAmount())
                    .gasAmount(rechargeSaveOrUpdateDTO.getGasAmount())
                    .receiveAmount(rechargeSaveOrUpdateDTO.getReceiveAmount())
                    .status(RechargeStatusEnum.WAIT_PAY.getStatus())
                    .remark(rechargeSaveOrUpdateDTO.getRemark())
                    .walletRemark(walletAddressVo.getRemark())
                    .build();

            if (rechargeMapper.insert(rechargeDO) <= 0) {
                throw new BusinessException("创建充值信息失败.");
            }
            return queryRecharge(RechargeQueryDTO.builder().id(rechargeDO.getId()).build());

        } else {
            // 更新充值信息

            LambdaUpdateChainWrapper<RechargeDO> updateWrapper = ChainWrappers.lambdaUpdateChain(rechargeMapper)
                    .set(ObjectUtil.isNotEmpty(rechargeSaveOrUpdateDTO.getHash()), RechargeDO::getHash, rechargeSaveOrUpdateDTO.getHash())
                    .set(ObjectUtil.isNotEmpty(rechargeSaveOrUpdateDTO.getFromAddress()), RechargeDO::getFromAddress, rechargeSaveOrUpdateDTO.getFromAddress())
                    .set(ObjectUtil.isNotEmpty(rechargeSaveOrUpdateDTO.getStatus()), RechargeDO::getStatus, rechargeSaveOrUpdateDTO.getStatus())
                    .set(ObjectUtil.isNotEmpty(rechargeSaveOrUpdateDTO.getWalletCode()), RechargeDO::getWalletCode, rechargeSaveOrUpdateDTO.getWalletCode())
                    .set(ObjectUtil.isNotEmpty(rechargeSaveOrUpdateDTO.getAmount()), RechargeDO::getAmount, rechargeSaveOrUpdateDTO.getAmount())
                    .set(ObjectUtil.isNotEmpty(rechargeSaveOrUpdateDTO.getGasAmount()), RechargeDO::getGasAmount, rechargeSaveOrUpdateDTO.getGasAmount())
                    .set(ObjectUtil.isNotEmpty(rechargeSaveOrUpdateDTO.getReceiveAmount()), RechargeDO::getReceiveAmount, rechargeSaveOrUpdateDTO.getReceiveAmount())
                    .set((ObjectUtil.isNotEmpty(rechargeSaveOrUpdateDTO.getUpdateCreateTime()) && rechargeSaveOrUpdateDTO.getUpdateCreateTime()), BaseDO::getCreateTime, LocalDateTimeUtil.now())
                    .set(ObjectUtil.isNotEmpty(rechargeSaveOrUpdateDTO.getRemark()), RechargeDO::getRemark, rechargeSaveOrUpdateDTO.getRemark())
                    .set(ObjectUtil.isNotEmpty(rechargeSaveOrUpdateDTO.getWalletRemark()), RechargeDO::getWalletRemark, rechargeSaveOrUpdateDTO.getWalletRemark())
                    .eq(BaseDO::getId, rechargeSaveOrUpdateDTO.getId())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

            updateWrapper.update();

            return queryRecharge(RechargeQueryDTO.builder().id(rechargeSaveOrUpdateDTO.getId()).build());
        }

    }






    public Boolean receipt(RechargeReceiptDTO rechargeReceiptDTO) {
        RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder()
                .id(rechargeReceiptDTO.getId())
                .build();
        RechargeVo rechargeVo = queryRecharge(rechargeQueryDTO);
        if (ObjectUtil.isEmpty(rechargeVo)) {
            throw new BusinessException("充值信息不存在.");
        }

        ParamsetVo paramsetVo = paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());
        if (ObjectUtil.isEmpty(paramsetVo)) {
            throw new BusinessException("参数信息不存在.");
        }

        // 增加账户金额（总额和可用余额）
        AccountAmountOperateDTO accountAmountOperateDTO = AccountAmountOperateDTO.builder()
                .id(rechargeVo.getAccountId())
                .amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType())
                .operateAmount(rechargeVo.getReceiveAmount())
                .bizId(rechargeVo.getId())
                .streamTypeEnum(StreamTypeEnum.RECHARGE)
                .build();
        accountApiService.operateAmount(accountAmountOperateDTO);

        // 更新充值状态为支付成功，清空hash和fromAddress
        RechargeSaveOrUpdateDTO rechargeSaveOrUpdateDTO = RechargeSaveOrUpdateDTO.builder()
                .id(rechargeVo.getId())
                .hash("")
                .fromAddress("")
                .status(RechargeStatusEnum.PAY_SUCCESS.getStatus())
                .build();
        saveOrUpdate(rechargeSaveOrUpdateDTO);

        // 解锁钱包地址，清除锁定时间
        WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO = WalletAddressSaveOrUpdateDTO.builder()
                .id(rechargeVo.getWalletId())
                .addressStatus(AddressStatusEnum.NORMAL.getStatus())
                .clearLockTime(true)
                .build();
        walletAddressApiService.saveOrUpdate(walletAddressSaveOrUpdateDTO);

        // 根据充值金额调整账户等级
        if (rechargeVo.getAmount().compareTo(paramsetVo.getPartnerLevelIncAmount()) >= 0) {
            AccountSaveOrUpdateDTO accountSaveOrUpdateDTO = AccountSaveOrUpdateDTO.builder()
                    .id(rechargeVo.getAccountId())
                    .accountLevel(AccountLevelEnum.PARTNER.getLevel())
                    .build();
            accountApiService.saveOrUpdate(accountSaveOrUpdateDTO);
        } else if (rechargeVo.getAmount().compareTo(paramsetVo.getLargeLevelIncAmount()) >= 0) {
            AccountSaveOrUpdateDTO accountSaveOrUpdateDTO = AccountSaveOrUpdateDTO.builder()
                    .id(rechargeVo.getAccountId())
                    .accountLevel(AccountLevelEnum.LARGE.getLevel())
                    .build();
            accountApiService.saveOrUpdate(accountSaveOrUpdateDTO);
        }

        return Boolean.TRUE;

    }





    public Boolean cancel(RechargeCancelDTO rechargeCancelDTO) {
        RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder()
                .id(rechargeCancelDTO.getId())
                .build();
        RechargeVo rechargeVo = queryRecharge(rechargeQueryDTO);
        if (ObjectUtil.isEmpty(rechargeVo)) {
            throw new BusinessException("充值信息不存在.");
        }

        // 更新充值状态为支付超时
        RechargeSaveOrUpdateDTO rechargeSaveOrUpdateDTO = RechargeSaveOrUpdateDTO.builder()
                .id(rechargeVo.getId())
                .status(RechargeStatusEnum.PAY_TIMEOUT.getStatus())
                .build();
        saveOrUpdate(rechargeSaveOrUpdateDTO);

        // 解锁钱包地址，清除锁定时间
        WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO = WalletAddressSaveOrUpdateDTO.builder()
                .id(rechargeVo.getWalletId())
                .addressStatus(AddressStatusEnum.NORMAL.getStatus())
                .clearLockTime(true)
                .build();
        walletAddressApiService.saveOrUpdate(walletAddressSaveOrUpdateDTO);

        return Boolean.TRUE;

    }



    public Boolean delete(RechargeDeleteDTO rechargeDeleteDTO) {
        return ChainWrappers.lambdaUpdateChain(rechargeMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, rechargeDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }



    public BigDecimal sumRechargeAmount(RechargeQueryDTO rechargeQueryDTO) {
        /* 352 */
        BigDecimal result = this.rechargeMapper.sumRechargeAmount(rechargeQueryDTO);
        /* 353 */
        return ObjectUtil.isEmpty(result) ? BigDecimal.ZERO : result;

    }






    protected String genRechargeNo() {
        /* 360 */
        String orderDate = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "HHmmss");
        /* 361 */
        String randNum = String.format("%04d", new Object[]{Integer.valueOf((new Random()).nextInt(1000))});
        /* 362 */
        Long incNum = this.redisTemplate.opsForValue().increment("rechargeno_key");
        /* 363 */
        return StrUtil.join("", new Object[]{"NO", orderDate, randNum, String.valueOf(incNum)});

    }



    public Boolean addCacheItem(RechargeCacheItemDTO rechargeCacheItemDTO) {
        /* 367 */
        this.rechargeCacheItemMap.put(rechargeCacheItemDTO.getCode().toUpperCase(), rechargeCacheItemDTO);
        /* 368 */
        return Boolean.TRUE;

    }



    public Boolean clearCacheItem() {
        /* 372 */
        this.rechargeCacheItemMap.clear();
        /* 373 */
        return Boolean.TRUE;

    }



    public RechargeCacheItemDTO getCacheItem(String code) {
        /* 377 */
        return this.rechargeCacheItemMap.get(code);

    }

}
