package com.porn.service.mobile.api.impl;


import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.api.AccountExtApiService;
import com.porn.client.account.api.AccountWalletService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountExtQueryDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.dto.AccountWalletQueryDTO;
import com.porn.client.account.enums.AccountExtTypeEnum;
import com.porn.client.account.enums.AccountLevelEnum;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.account.vo.AccountExtVo;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.account.vo.AccountWalletVo;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.goods.api.GoodsApiService;
import com.porn.client.goods.dto.GoodsQueryDTO;
import com.porn.client.goods.dto.GoodsSaveOrUpdateDTO;
import com.porn.client.goods.enums.GoodsStatusEnum;
import com.porn.client.goods.vo.GoodsVo;
import com.porn.client.merchant.api.MerchantApiService;
import com.porn.client.merchant.dto.MerchantQueryDTO;
import com.porn.client.merchant.vo.MerchantVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.CreateOrderDTO;
import com.porn.client.mobile.vo.CreateOrderVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderAuditDTO;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.dto.OrderSaveOrUpdateDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.enums.OrderTypeEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.wallet.enums.WalletChainEnum;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import com.porn.service.mobile.api.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class CreateOrderApiServiceImpl
        implements ApiService<CreateOrderVo> {
    private static final Logger log = LoggerFactory.getLogger(CreateOrderApiServiceImpl.class);


    @Autowired
    private OrderApiService orderApiService;


    @Autowired
    private GoodsApiService goodsApiService;


    @Autowired
    private MerchantApiService merchantApiService;

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private ParamsetApiService paramsetApiService;

    @Autowired
    private DingdingMsgSender dingdingMsgSender;

    @Autowired
    private AccountWalletService accountWalletService;

    @Autowired
    private AccountExtApiService accountExtApiService;

    public CreateOrderVo cmd(CmdRequestDTO cmdRequestDTO) {

        CreateOrderDTO createOrderDTO = (CreateOrderDTO) JSON.parseObject(cmdRequestDTO.getData(), CreateOrderDTO.class);

        if (ObjectUtil.isNotEmpty(createOrderDTO.getWalletAddress())) {


            AccountWalletQueryDTO accountWalletQueryDTO = AccountWalletQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).address(createOrderDTO.getWalletAddress()).build();

            AccountWalletVo accountWalletVo = this.accountWalletService.queryAccountWallet(accountWalletQueryDTO);

            if (ObjectUtil.isNotEmpty(accountWalletVo)) {

                createOrderDTO.setWalletCode(accountWalletVo.getWalletCode());

            }

        }


        validate(cmdRequestDTO.getAccountVo());


        GoodsQueryDTO goodsQueryDTO = ((GoodsQueryDTO.GoodsQueryDTOBuilder) GoodsQueryDTO.builder().id(createOrderDTO.getGoodsId())).build();

        GoodsVo goodsVo = this.goodsApiService.queryGoods(goodsQueryDTO);

        if (ObjectUtil.isEmpty(goodsVo)) {

            throw new BusinessException("商品信息不存在.");

        }

        MerchantQueryDTO merchantQueryDTO = ((MerchantQueryDTO.MerchantQueryDTOBuilder) MerchantQueryDTO.builder().id(goodsVo.getMerchantId())).build();

        MerchantVo merchantVo = this.merchantApiService.queryMerchant(merchantQueryDTO);

        if (ObjectUtil.isEmpty(merchantVo)) {

            throw new BusinessException("商户信息不存在.");

        }

        AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build();

        AccountVo accountVo = this.accountApiService.queryAccount(accountQueryDTO);

        if (ObjectUtil.isEmpty(accountVo)) {

            throw new BusinessException("用户信息不存在.");

        }

        if (accountVo.getAvailableBalance().compareTo(goodsVo.getAmount()) < 0) {

            throw new BusinessException("余额不足.");

        }


        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        AccountExtVo accountExtVo = this.accountExtApiService.queryAccountExt(
                AccountExtQueryDTO.builder()
                        .accountId(accountVo.getId())
                        .extType(AccountExtTypeEnum.EXTRAREBATE.getType())
                        .extKey(AccountExtTypeEnum.EXTRAREBATE.toString())
                        .build());

        OrderSaveOrUpdateDTO orderSaveOrUpdateDTO = OrderSaveOrUpdateDTO.builder().accountId(accountVo.getId()).accountName(accountVo.getName()).accountAvatar(accountVo.getAvatar()).merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar()).orderAmount(goodsVo.getAmount()).orderRate(goodsVo.getRate()).freeAmount(buildFreeAmount(accountVo, accountExtVo, paramsetVo, goodsVo)).orderStatus(OrderStatusEnum.WAIT_PAY.getStatus()).orderType(OrderTypeEnum.NORMAL.getType()).remark(accountVo.getRemark()).address(createOrderDTO.getWalletAddress()).walletCode(createOrderDTO.getWalletCode()).walletName(WalletChainEnum.queryName(createOrderDTO.getWalletCode())).build();

        OrderVo orderVo = this.orderApiService.saveOrUpdate(orderSaveOrUpdateDTO);


        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(orderVo.getAccountId())).amountType(AmountTypeEnum.SUBAVAILABLE_ADDFREEZE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.WORKING_UNLOCK).operateAmount(orderVo.getOrderAmount()).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);

        GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO = ((GoodsSaveOrUpdateDTO.GoodsSaveOrUpdateDTOBuilder) GoodsSaveOrUpdateDTO.builder().id(goodsVo.getId())).goodsStatus(GoodsStatusEnum.WORKED.getStatus()).build();

        this.goodsApiService.saveOrUpdate(goodsSaveOrUpdateDTO);


        if (EnableStatusEnum.ENABLE.getStatus().equals(accountVo.getWorkAutoAudit())) {

            this.orderApiService.audit(((OrderAuditDTO.OrderAuditDTOBuilder) OrderAuditDTO.builder().id(orderVo.getId())).build());

        } else {

            this.dingdingMsgSender.sendMsg(
                    ProxyMsgDTO.builder()
                            .accountId(orderVo.getAccountId())
                            .msg("账户[" + accountVo.getName() + "]开始进行搬砖, 请及时放款.")
                            .build());

        }

        return CreateOrderVo.builder()
                .build();

    }


    protected void validate(AccountVo accountVo) {

        AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(accountVo.getId())).build();

        AccountVo dbAccountVo = this.accountApiService.queryAccount(accountQueryDTO);

        if (ObjectUtil.isEmpty(dbAccountVo)) {

            throw new BusinessException("账户信息不存在.");

        }

        if (EnableStatusEnum.DISABLED.getStatus().equals(dbAccountVo.getWorkStatus())) {

            throw new BusinessException("你已被禁止搬砖");

        }

        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().accountId(dbAccountVo.getId()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTime.now())).endTime(LocalDateTimeUtil.endOfDay(LocalDateTime.now())).build();

        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);

        if (ObjectUtil.isEmpty(orderVoList)) {

            return;

        }

        OrderVo orderVo = orderVoList.get(0);

        long subMins = LocalDateTimeUtil.between(orderVo.getCreateTime(), LocalDateTime.now()).toMinutes();

        if (AccountLevelEnum.NORMAL.getLevel().equals(dbAccountVo.getAccountLevel())) {

            if (NumberUtil.compare(subMins, Long.valueOf(paramsetVo.getNormalLevelWorkSpace().intValue()).longValue()) < 0) {

                throw new BusinessException("普通会员搬砖时间间隔不得低于" + Long.valueOf(paramsetVo.getNormalLevelWorkSpace().intValue()) + "分钟.");

            }

        } else if (AccountLevelEnum.LARGE.getLevel().equals(dbAccountVo.getAccountLevel())) {

            if (NumberUtil.compare(subMins, Long.valueOf(paramsetVo.getLargeLevelWorkSpace().intValue()).longValue()) < 0) {

                throw new BusinessException("大户会员搬砖时间间隔不得低于" + Long.valueOf(paramsetVo.getLargeLevelWorkSpace().intValue()) + "分钟.");

            }

        } else if (NumberUtil.compare(subMins, Long.valueOf(paramsetVo.getPartnerLevelWorkSpace().intValue()).longValue()) < 0) {

            throw new BusinessException("合伙人搬砖时间间隔不得低于" + Long.valueOf(paramsetVo.getPartnerLevelWorkSpace().intValue()) + "分钟.");

        }

    }


    protected BigDecimal buildFreeAmount(AccountVo accountVo, AccountExtVo accountExtVo, ParamsetVo paramsetVo, GoodsVo goodsVo) {

        BigDecimal extraRebate = BigDecimal.ZERO;

        if (ObjectUtil.isNotEmpty(accountExtVo)) {

            try {

                extraRebate = new BigDecimal(accountExtVo.getExtValue());

            } catch (Exception e) {

                log.error("获取额外加成数据异常[{}].", e);

                extraRebate = BigDecimal.ZERO;

            }

        }

        if (accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel())

            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getNormalLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

        if (accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) {

            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getLargeLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

        }

        return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getPartnerLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

    }


    public String getApi() {

        return "api_createorder";

    }

}

