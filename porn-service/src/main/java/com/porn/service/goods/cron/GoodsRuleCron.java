package com.porn.service.goods.cron;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.goods.api.GoodsApiService;
import com.porn.client.goods.api.GoodsRuleApiService;
import com.porn.client.goods.dto.GoodsQueryCountDTO;
import com.porn.client.goods.dto.GoodsRuleQueryDTO;
import com.porn.client.goods.dto.GoodsSaveOrUpdateDTO;
import com.porn.client.goods.enums.GoodsStatusEnum;
import com.porn.client.goods.vo.GoodsRuleVo;
import com.porn.client.merchant.api.MerchantApiService;
import com.porn.client.merchant.dto.MerchantQueryDTO;
import com.porn.client.merchant.vo.MerchantVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class GoodsRuleCron
        implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(GoodsRuleCron.class);


    @Autowired
    private GoodsApiService goodsApiService;


    @Autowired
    private GoodsRuleApiService goodsRuleApiService;

    @Autowired
    private MerchantApiService merchantApiService;

    private ApplicationContext applicationContext;

    @Scheduled(cron = "0/10 * * * * ?")
    public void doWork() {

        GoodsRuleQueryDTO goodsRuleQueryDTO = GoodsRuleQueryDTO.builder().status(EnableStatusEnum.ENABLE.getStatus()).build();

        List<GoodsRuleVo> goodsRuleVoList = this.goodsRuleApiService.queryGoodsRuleList(goodsRuleQueryDTO);

        if (ObjectUtil.isEmpty(goodsRuleVoList)) {

            return;

        }

        for (GoodsRuleVo goodsRuleVo : goodsRuleVoList) {


            try {


                GoodsQueryCountDTO goodsQueryCountDTO = GoodsQueryCountDTO.builder().goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).merchantId(goodsRuleVo.getMerchantId()).build();

                Integer curCount = this.goodsApiService.queryGoodsCount(goodsQueryCountDTO);

                if (ObjectUtil.isEmpty(curCount)) {

                    continue;

                }

                if (ObjectUtil.isEmpty(goodsRuleVo.getMinGoodsCount())) {

                    continue;

                }

                if (NumberUtil.compare(curCount.intValue(), goodsRuleVo.getMinGoodsCount().intValue()) >= 0) {

                    continue;

                }

                int remaining = goodsRuleVo.getMaxGoodsCount() - curCount;
                Number result = NumberUtil.sub(new Number[]{Integer.valueOf(remaining)});

                ((GoodsRuleCron) this.applicationContext.getBean(GoodsRuleCron.class))
                        .doCreateGoods(result.intValue(), goodsRuleVo);

//         ((GoodsRuleCron)this.applicationContext.getBean(GoodsRuleCron.class)).doCreateGoods(Integer.valueOf(NumberUtil.sub(new Number[] { Integer.valueOf(goodsRuleVo.getMaxGoodsCount().intValue() - curCount.intValue()) }, ).intValue()), goodsRuleVo);

            } catch (Exception e) {

                log.debug(e.getMessage(), e);

            }

        }

    }


    @Async

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void doCreateGoods(Integer createCount, GoodsRuleVo goodsRuleVo) {

        MerchantVo merchantVo = this.merchantApiService.queryMerchant(((MerchantQueryDTO.MerchantQueryDTOBuilder) MerchantQueryDTO.builder().id(goodsRuleVo.getMerchantId())).build());

        for (int i = 0; ObjectUtil.isNotEmpty(createCount) && i < createCount.intValue(); i++) {

            try {

                BigDecimal amount = RandomUtil.randomBigDecimal(goodsRuleVo.getMinAmount(), goodsRuleVo.getMaxAmount()).setScale(0, RoundingMode.HALF_DOWN);


                GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO = GoodsSaveOrUpdateDTO.builder().merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar()).rate(BigDecimal.ZERO).amount(amount).freeAmount(BigDecimal.ZERO).goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).build();

                this.goodsApiService.saveOrUpdate(goodsSaveOrUpdateDTO);

            } catch (Exception e) {

                log.debug(e.getMessage(), e);

            }

        }

    }


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

    }

}
