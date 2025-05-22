
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
         implements ApiService<CreateOrderVo>
         {
    /*  62 */   private static final Logger log = LoggerFactory.getLogger(CreateOrderApiServiceImpl.class);



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
        /*  93 */
        CreateOrderDTO createOrderDTO = (CreateOrderDTO) JSON.parseObject(cmdRequestDTO.getData(), CreateOrderDTO.class);
        /*  94 */
        if (ObjectUtil.isNotEmpty(createOrderDTO.getWalletAddress())) {



            /*  98 */
            AccountWalletQueryDTO accountWalletQueryDTO = AccountWalletQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).address(createOrderDTO.getWalletAddress()).build();
            /*  99 */
            AccountWalletVo accountWalletVo = this.accountWalletService.queryAccountWallet(accountWalletQueryDTO);
            /* 100 */
            if (ObjectUtil.isNotEmpty(accountWalletVo)) {
                /* 101 */
                createOrderDTO.setWalletCode(accountWalletVo.getWalletCode());

            }

        }


        /* 106 */
        validate(cmdRequestDTO.getAccountVo());




        /* 111 */
        GoodsQueryDTO goodsQueryDTO = ((GoodsQueryDTO.GoodsQueryDTOBuilder) GoodsQueryDTO.builder().id(createOrderDTO.getGoodsId())).build();
        /* 112 */
        GoodsVo goodsVo = this.goodsApiService.queryGoods(goodsQueryDTO);
        /* 113 */
        if (ObjectUtil.isEmpty(goodsVo)) {
            /* 114 */
            throw new BusinessException("商品信息不存在.");

        }



        /* 119 */
        MerchantQueryDTO merchantQueryDTO = ((MerchantQueryDTO.MerchantQueryDTOBuilder) MerchantQueryDTO.builder().id(goodsVo.getMerchantId())).build();
        /* 120 */
        MerchantVo merchantVo = this.merchantApiService.queryMerchant(merchantQueryDTO);
        /* 121 */
        if (ObjectUtil.isEmpty(merchantVo)) {
            /* 122 */
            throw new BusinessException("商户信息不存在.");

        }



        /* 127 */
        AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build();
        /* 128 */
        AccountVo accountVo = this.accountApiService.queryAccount(accountQueryDTO);
        /* 129 */
        if (ObjectUtil.isEmpty(accountVo)) {
            /* 130 */
            throw new BusinessException("用户信息不存在.");

        }

        /* 133 */
        if (accountVo.getAvailableBalance().compareTo(goodsVo.getAmount()) < 0)
             {
            /* 135 */
            throw new BusinessException("余额不足.");

        }


        /* 139 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        /* 142 */
        AccountExtVo accountExtVo = this.accountExtApiService.queryAccountExt(
                /* 143 */         AccountExtQueryDTO.builder()
/* 144 */.accountId(accountVo.getId())
/* 145 */.extType(AccountExtTypeEnum.EXTRAREBATE.getType())
/* 146 */.extKey(AccountExtTypeEnum.EXTRAREBATE.toString())
/* 147 */.build());















        /* 163 */
        OrderSaveOrUpdateDTO orderSaveOrUpdateDTO = OrderSaveOrUpdateDTO.builder().accountId(accountVo.getId()).accountName(accountVo.getName()).accountAvatar(accountVo.getAvatar()).merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar()).orderAmount(goodsVo.getAmount()).orderRate(goodsVo.getRate()).freeAmount(buildFreeAmount(accountVo, accountExtVo, paramsetVo, goodsVo)).orderStatus(OrderStatusEnum.WAIT_PAY.getStatus()).orderType(OrderTypeEnum.NORMAL.getType()).remark(accountVo.getRemark()).address(createOrderDTO.getWalletAddress()).walletCode(createOrderDTO.getWalletCode()).walletName(WalletChainEnum.queryName(createOrderDTO.getWalletCode())).build();
        /* 164 */
        OrderVo orderVo = this.orderApiService.saveOrUpdate(orderSaveOrUpdateDTO);








        /* 173 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(orderVo.getAccountId())).amountType(AmountTypeEnum.SUBAVAILABLE_ADDFREEZE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.WORKING_UNLOCK).operateAmount(orderVo.getOrderAmount()).build();
        /* 174 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);





        /* 180 */
        GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO = ((GoodsSaveOrUpdateDTO.GoodsSaveOrUpdateDTOBuilder) GoodsSaveOrUpdateDTO.builder().id(goodsVo.getId())).goodsStatus(GoodsStatusEnum.WORKED.getStatus()).build();
        /* 181 */
        this.goodsApiService.saveOrUpdate(goodsSaveOrUpdateDTO);


        /* 184 */
        if (EnableStatusEnum.ENABLE.getStatus().equals(accountVo.getWorkAutoAudit())) {

            /* 186 */
            this.orderApiService.audit(((OrderAuditDTO.OrderAuditDTOBuilder) OrderAuditDTO.builder().id(orderVo.getId())).build());

        } else {

            /* 189 */
            this.dingdingMsgSender.sendMsg(
                    /* 190 */           ProxyMsgDTO.builder()
/* 191 */.accountId(orderVo.getAccountId())
/* 192 */.msg("账户[" + accountVo.getName() + "]开始进行搬砖, 请及时放款.")
/* 193 */.build());

        }

        /* 196 */
        return CreateOrderVo.builder()
/* 197 */.build();

    }







    protected void validate(AccountVo accountVo) {
        /* 205 */
        AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(accountVo.getId())).build();
        /* 206 */
        AccountVo dbAccountVo = this.accountApiService.queryAccount(accountQueryDTO);
        /* 207 */
        if (ObjectUtil.isEmpty(dbAccountVo)) {
            /* 208 */
            throw new BusinessException("账户信息不存在.");

        }

        /* 211 */
        if (EnableStatusEnum.DISABLED.getStatus().equals(dbAccountVo.getWorkStatus())) {
            /* 212 */
            throw new BusinessException("你已被禁止搬砖");

        }

        /* 215 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());






        /* 222 */
        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().accountId(dbAccountVo.getId()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTime.now())).endTime(LocalDateTimeUtil.endOfDay(LocalDateTime.now())).build();
        /* 223 */
        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);
        /* 224 */
        if (ObjectUtil.isEmpty(orderVoList)) {

            return;

        }
        /* 227 */
        OrderVo orderVo = orderVoList.get(0);

        /* 229 */
        long subMins = LocalDateTimeUtil.between(orderVo.getCreateTime(), LocalDateTime.now()).toMinutes();
        /* 230 */
        if (AccountLevelEnum.NORMAL.getLevel().equals(dbAccountVo.getAccountLevel())) {

            /* 232 */
            if (NumberUtil.compare(subMins, Long.valueOf(paramsetVo.getNormalLevelWorkSpace().intValue()).longValue()) < 0) {
                /* 233 */
                throw new BusinessException("普通会员搬砖时间间隔不得低于" + Long.valueOf(paramsetVo.getNormalLevelWorkSpace().intValue()) + "分钟.");

            }
            /* 235 */
        } else if (AccountLevelEnum.LARGE.getLevel().equals(dbAccountVo.getAccountLevel())) {

            /* 237 */
            if (NumberUtil.compare(subMins, Long.valueOf(paramsetVo.getLargeLevelWorkSpace().intValue()).longValue()) < 0) {
                /* 238 */
                throw new BusinessException("大户会员搬砖时间间隔不得低于" + Long.valueOf(paramsetVo.getLargeLevelWorkSpace().intValue()) + "分钟.");


            }

        }
        /* 242 */
        else if (NumberUtil.compare(subMins, Long.valueOf(paramsetVo.getPartnerLevelWorkSpace().intValue()).longValue()) < 0) {
            /* 243 */
            throw new BusinessException("合伙人搬砖时间间隔不得低于" + Long.valueOf(paramsetVo.getPartnerLevelWorkSpace().intValue()) + "分钟.");

        }

    }













    protected BigDecimal buildFreeAmount(AccountVo accountVo, AccountExtVo accountExtVo, ParamsetVo paramsetVo, GoodsVo goodsVo) {
        /* 258 */
        BigDecimal extraRebate = BigDecimal.ZERO;
        /* 259 */
        if (ObjectUtil.isNotEmpty(accountExtVo)) {


            try {
                /* 262 */
                extraRebate = new BigDecimal(accountExtVo.getExtValue());
                /* 263 */
            } catch (Exception e) {
                /* 264 */
                log.error("获取额外加成数据异常[{}].", e);
                /* 265 */
                extraRebate = BigDecimal.ZERO;

            }

        }
        /* 268 */
        if (accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel())
            /* 269 */
            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getNormalLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);
        /* 270 */
        if (accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) {
            /* 271 */
            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getLargeLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

        }
        /* 273 */
        return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getPartnerLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

    }





    public String getApi() {
        /* 279 */
        return "api_createorder";

    }

}


