
package com.porn.service.mobile.api.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.api.AccountExtApiService;
import com.porn.client.account.dto.AccountExtQueryDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AccountExtTypeEnum;
import com.porn.client.account.enums.AccountLevelEnum;
import com.porn.client.account.vo.AccountExtVo;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.config.api.ConfigApiService;
import com.porn.client.config.dto.ConfigQueryDTO;
import com.porn.client.config.vo.ConfigVo;
import com.porn.client.goods.api.GoodsApiService;
import com.porn.client.goods.dto.GoodsQueryPageDTO;
import com.porn.client.goods.enums.GoodsStatusEnum;
import com.porn.client.goods.vo.GoodsVo;
import com.porn.client.inline.api.InlineApiService;
import com.porn.client.inline.dto.InlineQueryDTO;
import com.porn.client.inline.vo.InlineVo;
import com.porn.client.merchant.api.MerchantApiService;
import com.porn.client.merchant.dto.MerchantQueryDTO;
import com.porn.client.merchant.vo.MerchantVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.GoodsInfoVo;
import com.porn.client.mobile.vo.GoodsItemVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.plan.api.PlanInsApiService;
import com.porn.client.plan.dto.PlanInsQueryDTO;
import com.porn.client.plan.enums.PlanInsStatusEnum;
import com.porn.client.plan.vo.PlanInsVo;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.mobile.converter.MobileConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
 public class GoodsInfoApiServiceImpl implements ApiService<GoodsInfoVo> {
    /*  65 */   private static final Logger log = LoggerFactory.getLogger(GoodsInfoApiServiceImpl.class);



    @Autowired
     private GoodsApiService goodsApiService;



    @Autowired
     private MerchantApiService merchantApiService;



    @Autowired
     private OrderApiService orderApiService;



    @Autowired
     private MobileConverter mobileConverter;


    @Autowired
     private ParamsetApiService paramsetApiService;


    @Autowired
     private AccountApiService accountApiService;


    @Autowired
     private InlineApiService inlineApiService;


    @Autowired
     private PlanInsApiService planInsApiService;


    @Autowired
     private ConfigApiService configApiService;


    @Autowired
     private AccountExtApiService accountExtApiService;




    public GoodsInfoVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 103 */
        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build());







        /* 111 */
        GoodsQueryPageDTO goodsQueryPageDTO = ((GoodsQueryPageDTO.GoodsQueryPageDTOBuilder) ((GoodsQueryPageDTO.GoodsQueryPageDTOBuilder) GoodsQueryPageDTO.builder().goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).pageStart(Integer.valueOf(1))).pageSize(Integer.valueOf(500))).accountId(EnableStatusEnum.DISABLED.getStatus().equals(accountVo.getKeynoteFollow()) ? CommonConst.LZERO : accountVo.getId()).build();

        /* 113 */
        List<GoodsVo> dbGoodsList = this.goodsApiService.groupRandGoodsList(goodsQueryPageDTO);

        /* 115 */
        List<GoodsItemVo> goodsItemList = new ArrayList<>();

        /* 117 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());

        /* 119 */
        if (ObjectUtil.isNotEmpty(dbGoodsList)) {

            /* 121 */
            List<GoodsVo> goodsVoList = dbGoodsList;

            /* 123 */
            List<Long> merchantIdList = (List<Long>) goodsVoList.stream().map(GoodsVo::getMerchantId).distinct().collect(Collectors.toList());


            /* 126 */
            MerchantQueryDTO merchantQueryDTO = MerchantQueryDTO.builder().merchantIdList(merchantIdList).build();
            /* 127 */
            List<MerchantVo> merchantVoList = this.merchantApiService.queryMerchantList(merchantQueryDTO);

            /* 129 */
            Map<Long, MerchantVo> merchantVoMap = ObjectUtil.isEmpty(merchantVoList) ? MapUtil.newHashMap() : (Map<Long, MerchantVo>) merchantVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));







            /* 137 */
            OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().merchantIdList(merchantIdList).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).endTime(LocalDateTimeUtil.endOfDay(LocalDateTimeUtil.now())).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).build();
            /* 138 */
            List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);
            /* 139 */
            Map<Long, List<OrderVo>> orderVoMap = ObjectUtil.isEmpty(orderVoList) ? MapUtil.newHashMap() : (Map<Long, List<OrderVo>>) orderVoList.stream().collect(Collectors.groupingBy(OrderVo::getMerchantId));



            /* 143 */
            AccountExtVo accountExtVo = this.accountExtApiService.queryAccountExt(
                    /* 144 */           AccountExtQueryDTO.builder()
/* 145 */.accountId(accountVo.getId())
/* 146 */.extType(AccountExtTypeEnum.EXTRAREBATE.getType())
/* 147 */.extKey(AccountExtTypeEnum.EXTRAREBATE.toString())
/* 148 */.build());


            /* 151 */
            for (GoodsVo goodsVo : goodsVoList) {
                /* 152 */
                List<OrderVo> orderVos = orderVoMap.get(goodsVo.getMerchantId());
                /* 153 */
                MerchantVo merchantVo = merchantVoMap.get(goodsVo.getMerchantId());
                /* 154 */
                GoodsItemVo goodsItemVo = this.mobileConverter.toGoodsItemVo(merchantVo);
                /* 155 */
                goodsItemVo.setMerchantId(merchantVo.getId());
                /* 156 */
                goodsItemVo.setGoodsId(goodsVo.getId());
                /* 157 */
                goodsItemVo.setGoodsAmount(goodsVo.getAmount());
                /* 158 */
                goodsItemVo.setGoodsFreeAmount(calcGoodsFreeAmount(accountVo, accountExtVo, paramsetVo, goodsVo));
                /* 159 */
                goodsItemVo.setGoodsRate((accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel()) ? paramsetVo.getNormalLevelRate() : ((accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) ? paramsetVo.getLargeLevelRate() : paramsetVo.getPartnerLevelRate()));
                /* 160 */
                goodsItemVo.setTodayOrderCount(ObjectUtil.isEmpty(orderVos) ? CommonConst.LZERO : Long.valueOf(orderVos.size()));
                /* 161 */
                goodsItemVo.setTodayTotalAmount(ObjectUtil.isEmpty(orderVos) ? BigDecimal.ZERO : orderVos.stream().map(OrderVo::getOrderAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
                /* 162 */
                merchantVo.setAccumulateCount(Long.valueOf(ObjectUtil.isEmpty(merchantVo.getAccumulateCount()) ? 0L : merchantVo.getAccumulateCount().longValue()));
                /* 163 */
                merchantVo.setAccumulateAmount(ObjectUtil.isEmpty(merchantVo.getAccumulateAmount()) ? BigDecimal.ZERO : merchantVo.getAccumulateAmount());
                /* 164 */
                goodsItemVo.setMerchantTag(merchantVo.getMerchantTag());
                /* 165 */
                goodsItemList.add(goodsItemVo);

            }

        }

        /* 169 */
        if (EnableStatusEnum.DISABLED.getStatus().equals(accountVo.getKeynoteFollow())) {
            /* 170 */
            Collections.shuffle(goodsItemList);

        }


        /* 174 */
        PlanInsVo planInsVo = this.planInsApiService.queryPlanIns(
                /* 175 */         PlanInsQueryDTO.builder()
/* 176 */.accountId(accountVo.getId())
/* 177 */.status(PlanInsStatusEnum.PROGRESSING.getStatus())
/* 178 */.build());







        /* 186 */
        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configCode("work_enable").configGroup("global").accountId(CommonConst.LZERO).build();
        /* 187 */
        ConfigVo configVo = this.configApiService.queryConfig(configQueryDTO);

        /* 189 */
        return GoodsInfoVo.builder()
/* 190 */.inlineAccountCount(genInlineAccountCount(paramsetVo))
/* 191 */.keynoteFollow(accountVo.getKeynoteFollow())
/* 192 */.planing(ObjectUtil.isEmpty(planInsVo) ? CommonConst.IZERO : CommonConst.IONE)
/* 193 */.enableWork(ObjectUtil.isEmpty(configQueryDTO) ? CommonConst.IONE : (CommonConst.IONE_STR.equals(configVo.getConfigValue()) ? CommonConst.IONE : CommonConst.IZERO))
/* 194 */.goodsItemList(goodsItemList)
/* 195 */.build();

    }









    protected List<GoodsVo> randomGoodsVoList(List<GoodsVo> goodsVoList, Integer size) {
        /* 205 */
        if (ObjectUtil.isEmpty(goodsVoList) ||
                /* 206 */       ObjectUtil.isEmpty(size)) {
            /* 207 */
            return CollUtil.newArrayList((GoodsVo[]) new GoodsVo[0]);

        }
        /* 209 */
        Map<Long, List<GoodsVo>> goodsVoMap = (Map<Long, List<GoodsVo>>) goodsVoList.stream().collect(Collectors.groupingBy(GoodsVo::getMerchantId));
        /* 210 */
        List<GoodsVo> result = new ArrayList<>();
        /* 211 */
        List<Long> merchantIdList = CollUtil.newArrayList(goodsVoMap.keySet());
        /* 212 */
        if (merchantIdList.size() <= size.intValue()) {

            /* 214 */
            for (Long merchantId : merchantIdList) {
                /* 215 */
                List<GoodsVo> newGoodsList = new ArrayList<>(goodsVoMap.get(merchantId));
                /* 216 */
                Collections.shuffle(newGoodsList);
                /* 217 */
                result.add(RandomUtil.randomEleList(newGoodsList, 1).get(0));

            }

        } else {

            /* 221 */
            List<Long> selMerchantIdList = RandomUtil.randomEleList(merchantIdList, size.intValue());
            /* 222 */
            for (Long merchantId : selMerchantIdList) {
                /* 223 */
                List<GoodsVo> newGoodsList = new ArrayList<>(goodsVoMap.get(merchantId));
                /* 224 */
                Collections.shuffle(newGoodsList);
                /* 225 */
                result.add(RandomUtil.randomEleList(newGoodsList, 1).get(0));

            }

        }
        /* 228 */
        return result;

    }












    protected BigDecimal calcGoodsFreeAmount(AccountVo accountVo, AccountExtVo accountExtVo, ParamsetVo paramsetVo, GoodsVo goodsVo) {
        /* 241 */
        BigDecimal extraRebate = BigDecimal.ZERO;
        /* 242 */
        if (ObjectUtil.isNotEmpty(accountExtVo)) {


            try {
                /* 245 */
                extraRebate = new BigDecimal(accountExtVo.getExtValue());
                /* 246 */
            } catch (Exception e) {
                /* 247 */
                log.error("获取额外加成数据异常[{}].", e);
                /* 248 */
                extraRebate = BigDecimal.ZERO;

            }

        }
        /* 251 */
        if (accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel())
            /* 252 */
            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getNormalLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);
        /* 253 */
        if (accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) {
            /* 254 */
            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getLargeLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

        }
        /* 256 */
        return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getPartnerLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

    }









    protected Long genInlineAccountCount(ParamsetVo paramsetVo) {
        /* 266 */
        List<InlineVo> inlineVoList = this.inlineApiService.queryInlineList(InlineQueryDTO.builder().build());
        /* 267 */
        if (ObjectUtil.isEmpty(inlineVoList)) {

            try {
                /* 269 */
                return Long.valueOf(RandomUtil.randomLong(
                        /* 270 */               (ObjectUtil.isEmpty(paramsetVo) || ObjectUtil.isEmpty(paramsetVo.getMinWorkCount())) ? 8L : paramsetVo.getMinWorkCount().intValue(),
                        /* 271 */               (ObjectUtil.isEmpty(paramsetVo) || ObjectUtil.isEmpty(paramsetVo.getMaxWorkCount())) ? 200L : paramsetVo.getMaxWorkCount().intValue()));

            }
            /* 273 */ catch (Exception e) {
                /* 274 */
                return Long.valueOf(RandomUtil.randomLong(8L, 200L));

            }

        }

        /* 278 */
        InlineVo activeInlineVo = null;
        /* 279 */
        for (InlineVo inlineVo : inlineVoList) {
            /* 280 */
            if (matchInline(inlineVo)) {
                /* 281 */
                activeInlineVo = inlineVo;

                break;

            }

        }
        /* 285 */
        if (ObjectUtil.isNotEmpty(activeInlineVo)) {

            try {
                /* 287 */
                return Long.valueOf(RandomUtil.randomLong(activeInlineVo
/* 288 */.getMinInlineCount().longValue(), activeInlineVo
/* 289 */.getMaxInlineCount().longValue()));

            }
            /* 291 */ catch (Exception e) {
                /* 292 */
                log.error(e.getMessage(), e);
                /* 293 */
                return Long.valueOf(RandomUtil.randomLong(
                        /* 294 */               (ObjectUtil.isEmpty(paramsetVo) || ObjectUtil.isEmpty(paramsetVo.getMinWorkCount())) ? 8L : paramsetVo.getMinWorkCount().intValue(),
                        /* 295 */               (ObjectUtil.isEmpty(paramsetVo) || ObjectUtil.isEmpty(paramsetVo.getMaxWorkCount())) ? 200L : paramsetVo.getMaxWorkCount().intValue()));

            }

        }

        /* 299 */
        return Long.valueOf(RandomUtil.randomLong(
                /* 300 */           (ObjectUtil.isEmpty(paramsetVo) || ObjectUtil.isEmpty(paramsetVo.getMinWorkCount())) ? 8L : paramsetVo.getMinWorkCount().intValue(),
                /* 301 */           (ObjectUtil.isEmpty(paramsetVo) || ObjectUtil.isEmpty(paramsetVo.getMaxWorkCount())) ? 200L : paramsetVo.getMaxWorkCount().intValue()));

    }









    protected boolean matchInline(InlineVo inlineVo) {
        /* 311 */
        if (ObjectUtil.isEmpty(inlineVo)) {
            /* 312 */
            return false;

        }
        /* 314 */
        Date now = new Date();
        /* 315 */
        DateTime dateTime1 = DateUtil.parse(inlineVo.getMinInlineTime(), "yyyy-MM-dd HH:mm:ss");
        /* 316 */
        dateTime1.setYear(now.getYear());
        /* 317 */
        dateTime1.setMonth(now.getMonth());
        /* 318 */
        dateTime1.setDate(now.getDate());
        /* 319 */
        DateTime dateTime2 = DateUtil.parse(inlineVo.getMaxInlineTime(), "yyyy-MM-dd HH:mm:ss");
        /* 320 */
        dateTime2.setYear(now.getYear());
        /* 321 */
        dateTime2.setMonth(now.getMonth());
        /* 322 */
        dateTime2.setDate(now.getDate());

        /* 324 */
        return DateUtil.isIn(now, (Date) dateTime1, (Date) dateTime2);

    }



    public String getApi() {
        /* 328 */
        return "api_goodsinfo";

    }

}
