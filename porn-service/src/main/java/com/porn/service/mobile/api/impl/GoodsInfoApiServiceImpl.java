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
    private static final Logger log = LoggerFactory.getLogger(GoodsInfoApiServiceImpl.class);


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

        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build());

        GoodsQueryPageDTO goodsQueryPageDTO = ((GoodsQueryPageDTO.GoodsQueryPageDTOBuilder) ((GoodsQueryPageDTO.GoodsQueryPageDTOBuilder) GoodsQueryPageDTO.builder().goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).pageStart(Integer.valueOf(1))).pageSize(Integer.valueOf(500))).accountId(EnableStatusEnum.DISABLED.getStatus().equals(accountVo.getKeynoteFollow()) ? CommonConst.LZERO : accountVo.getId()).build();

        List<GoodsVo> dbGoodsList = this.goodsApiService.groupRandGoodsList(goodsQueryPageDTO);

        List<GoodsItemVo> goodsItemList = new ArrayList<>();

        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());

        if (ObjectUtil.isNotEmpty(dbGoodsList)) {

            List<GoodsVo> goodsVoList = dbGoodsList;

            List<Long> merchantIdList = (List<Long>) goodsVoList.stream().map(GoodsVo::getMerchantId).distinct().collect(Collectors.toList());


            MerchantQueryDTO merchantQueryDTO = MerchantQueryDTO.builder().merchantIdList(merchantIdList).build();

            List<MerchantVo> merchantVoList = this.merchantApiService.queryMerchantList(merchantQueryDTO);

            Map<Long, MerchantVo> merchantVoMap = ObjectUtil.isEmpty(merchantVoList) ? MapUtil.newHashMap() : (Map<Long, MerchantVo>) merchantVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));

            OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().merchantIdList(merchantIdList).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).endTime(LocalDateTimeUtil.endOfDay(LocalDateTimeUtil.now())).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).build();

            List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);

            Map<Long, List<OrderVo>> orderVoMap = ObjectUtil.isEmpty(orderVoList) ? MapUtil.newHashMap() : (Map<Long, List<OrderVo>>) orderVoList.stream().collect(Collectors.groupingBy(OrderVo::getMerchantId));

            AccountExtVo accountExtVo = this.accountExtApiService.queryAccountExt(
                    AccountExtQueryDTO.builder()
                            .accountId(accountVo.getId())
                            .extType(AccountExtTypeEnum.EXTRAREBATE.getType())
                            .extKey(AccountExtTypeEnum.EXTRAREBATE.toString())
                            .build());


            for (GoodsVo goodsVo : goodsVoList) {

                List<OrderVo> orderVos = orderVoMap.get(goodsVo.getMerchantId());

                MerchantVo merchantVo = merchantVoMap.get(goodsVo.getMerchantId());

                GoodsItemVo goodsItemVo = this.mobileConverter.toGoodsItemVo(merchantVo);

                goodsItemVo.setMerchantId(merchantVo.getId());

                goodsItemVo.setGoodsId(goodsVo.getId());

                goodsItemVo.setGoodsAmount(goodsVo.getAmount());

                goodsItemVo.setGoodsFreeAmount(calcGoodsFreeAmount(accountVo, accountExtVo, paramsetVo, goodsVo));

                goodsItemVo.setGoodsRate((accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel()) ? paramsetVo.getNormalLevelRate() : ((accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) ? paramsetVo.getLargeLevelRate() : paramsetVo.getPartnerLevelRate()));

                goodsItemVo.setTodayOrderCount(ObjectUtil.isEmpty(orderVos) ? CommonConst.LZERO : Long.valueOf(orderVos.size()));

                goodsItemVo.setTodayTotalAmount(ObjectUtil.isEmpty(orderVos) ? BigDecimal.ZERO : orderVos.stream().map(OrderVo::getOrderAmount).reduce(BigDecimal.ZERO, BigDecimal::add));

                merchantVo.setAccumulateCount(Long.valueOf(ObjectUtil.isEmpty(merchantVo.getAccumulateCount()) ? 0L : merchantVo.getAccumulateCount().longValue()));

                merchantVo.setAccumulateAmount(ObjectUtil.isEmpty(merchantVo.getAccumulateAmount()) ? BigDecimal.ZERO : merchantVo.getAccumulateAmount());

                goodsItemVo.setMerchantTag(merchantVo.getMerchantTag());

                goodsItemList.add(goodsItemVo);

            }

        }

        if (EnableStatusEnum.DISABLED.getStatus().equals(accountVo.getKeynoteFollow())) {

            Collections.shuffle(goodsItemList);

        }


        PlanInsVo planInsVo = this.planInsApiService.queryPlanIns(
                PlanInsQueryDTO.builder()
                        .accountId(accountVo.getId())
                        .status(PlanInsStatusEnum.PROGRESSING.getStatus())
                        .build());

        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configCode("work_enable").configGroup("global").accountId(CommonConst.LZERO).build();

        ConfigVo configVo = this.configApiService.queryConfig(configQueryDTO);

        return GoodsInfoVo.builder()
                .inlineAccountCount(genInlineAccountCount(paramsetVo))
                .keynoteFollow(accountVo.getKeynoteFollow())
                .planing(ObjectUtil.isEmpty(planInsVo) ? CommonConst.IZERO : CommonConst.IONE)
                .enableWork(ObjectUtil.isEmpty(configQueryDTO) ? CommonConst.IONE : (CommonConst.IONE_STR.equals(configVo.getConfigValue()) ? CommonConst.IONE : CommonConst.IZERO))
                .goodsItemList(goodsItemList)
                .build();

    }


    protected List<GoodsVo> randomGoodsVoList(List<GoodsVo> goodsVoList, Integer size) {

        if (ObjectUtil.isEmpty(goodsVoList) ||
                ObjectUtil.isEmpty(size)) {

            return CollUtil.newArrayList((GoodsVo[]) new GoodsVo[0]);

        }

        Map<Long, List<GoodsVo>> goodsVoMap = (Map<Long, List<GoodsVo>>) goodsVoList.stream().collect(Collectors.groupingBy(GoodsVo::getMerchantId));

        List<GoodsVo> result = new ArrayList<>();

        List<Long> merchantIdList = CollUtil.newArrayList(goodsVoMap.keySet());

        if (merchantIdList.size() <= size.intValue()) {

            for (Long merchantId : merchantIdList) {

                List<GoodsVo> newGoodsList = new ArrayList<>(goodsVoMap.get(merchantId));

                Collections.shuffle(newGoodsList);

                result.add(RandomUtil.randomEleList(newGoodsList, 1).get(0));

            }

        } else {

            List<Long> selMerchantIdList = RandomUtil.randomEleList(merchantIdList, size.intValue());

            for (Long merchantId : selMerchantIdList) {

                List<GoodsVo> newGoodsList = new ArrayList<>(goodsVoMap.get(merchantId));

                Collections.shuffle(newGoodsList);

                result.add(RandomUtil.randomEleList(newGoodsList, 1).get(0));

            }

        }

        return result;

    }

    protected BigDecimal calcGoodsFreeAmount(AccountVo accountVo, AccountExtVo accountExtVo, ParamsetVo paramsetVo, GoodsVo goodsVo) {

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


    protected Long genInlineAccountCount(ParamsetVo paramsetVo) {

        List<InlineVo> inlineVoList = this.inlineApiService.queryInlineList(InlineQueryDTO.builder().build());

        if (ObjectUtil.isEmpty(inlineVoList)) {

            try {

                return Long.valueOf(RandomUtil.randomLong(
                        (ObjectUtil.isEmpty(paramsetVo) || ObjectUtil.isEmpty(paramsetVo.getMinWorkCount())) ? 8L : paramsetVo.getMinWorkCount().intValue(),
                        (ObjectUtil.isEmpty(paramsetVo) || ObjectUtil.isEmpty(paramsetVo.getMaxWorkCount())) ? 200L : paramsetVo.getMaxWorkCount().intValue()));

            } catch (Exception e) {

                return Long.valueOf(RandomUtil.randomLong(8L, 200L));

            }

        }

        InlineVo activeInlineVo = null;

        for (InlineVo inlineVo : inlineVoList) {

            if (matchInline(inlineVo)) {

                activeInlineVo = inlineVo;

                break;

            }

        }

        if (ObjectUtil.isNotEmpty(activeInlineVo)) {

            try {

                return Long.valueOf(RandomUtil.randomLong(activeInlineVo
                        .getMinInlineCount().longValue(), activeInlineVo
                        .getMaxInlineCount().longValue()));

            } catch (Exception e) {

                log.error(e.getMessage(), e);

                return Long.valueOf(RandomUtil.randomLong(
                        (ObjectUtil.isEmpty(paramsetVo) || ObjectUtil.isEmpty(paramsetVo.getMinWorkCount())) ? 8L : paramsetVo.getMinWorkCount().intValue(),
                        (ObjectUtil.isEmpty(paramsetVo) || ObjectUtil.isEmpty(paramsetVo.getMaxWorkCount())) ? 200L : paramsetVo.getMaxWorkCount().intValue()));

            }

        }

        return Long.valueOf(RandomUtil.randomLong(
                (ObjectUtil.isEmpty(paramsetVo) || ObjectUtil.isEmpty(paramsetVo.getMinWorkCount())) ? 8L : paramsetVo.getMinWorkCount().intValue(),
                (ObjectUtil.isEmpty(paramsetVo) || ObjectUtil.isEmpty(paramsetVo.getMaxWorkCount())) ? 200L : paramsetVo.getMaxWorkCount().intValue()));

    }


    protected boolean matchInline(InlineVo inlineVo) {

        if (ObjectUtil.isEmpty(inlineVo)) {

            return false;

        }

        Date now = new Date();

        DateTime dateTime1 = DateUtil.parse(inlineVo.getMinInlineTime(), "yyyy-MM-dd HH:mm:ss");

        dateTime1.setYear(now.getYear());

        dateTime1.setMonth(now.getMonth());

        dateTime1.setDate(now.getDate());

        DateTime dateTime2 = DateUtil.parse(inlineVo.getMaxInlineTime(), "yyyy-MM-dd HH:mm:ss");

        dateTime2.setYear(now.getYear());

        dateTime2.setMonth(now.getMonth());

        dateTime2.setDate(now.getDate());

        return DateUtil.isIn(now, (Date) dateTime1, (Date) dateTime2);

    }


    public String getApi() {

        return "api_goodsinfo";

    }

}
