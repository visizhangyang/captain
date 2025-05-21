
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
         implements ApplicationContextAware
         {
    /*  36 */   private static final Logger log = LoggerFactory.getLogger(GoodsRuleCron.class);



    @Autowired
     private GoodsApiService goodsApiService;



    @Autowired
     private GoodsRuleApiService goodsRuleApiService;


    @Autowired
     private MerchantApiService merchantApiService;

       private ApplicationContext applicationContext;




    @Scheduled(cron = "0/10 * * * * ?")
     public void doWork() {
        /*  54 */
        GoodsRuleQueryDTO goodsRuleQueryDTO = GoodsRuleQueryDTO.builder().status(EnableStatusEnum.ENABLE.getStatus()).build();
        /*  55 */
        List<GoodsRuleVo> goodsRuleVoList = this.goodsRuleApiService.queryGoodsRuleList(goodsRuleQueryDTO);
        /*  56 */
        if (ObjectUtil.isEmpty(goodsRuleVoList)) {

            return;

        }
        /*  59 */
        for (GoodsRuleVo goodsRuleVo : goodsRuleVoList) {



            try {


                /*  65 */
                GoodsQueryCountDTO goodsQueryCountDTO = GoodsQueryCountDTO.builder().goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).merchantId(goodsRuleVo.getMerchantId()).build();
                /*  66 */
                Integer curCount = this.goodsApiService.queryGoodsCount(goodsQueryCountDTO);
                /*  67 */
                if (ObjectUtil.isEmpty(curCount)) {

                    continue;

                }
                /*  70 */
                if (ObjectUtil.isEmpty(goodsRuleVo.getMinGoodsCount())) {

                    continue;

                }
                /*  73 */
                if (NumberUtil.compare(curCount.intValue(), goodsRuleVo.getMinGoodsCount().intValue()) >= 0) {

                    continue;

                }

                int remaining = goodsRuleVo.getMaxGoodsCount() - curCount;
                Number result = NumberUtil.sub(new Number[]{Integer.valueOf(remaining)});

                ((GoodsRuleCron) this.applicationContext.getBean(GoodsRuleCron.class))
                        .doCreateGoods(result.intValue(), goodsRuleVo);

///*  77 */         ((GoodsRuleCron)this.applicationContext.getBean(GoodsRuleCron.class)).doCreateGoods(Integer.valueOf(NumberUtil.sub(new Number[] { Integer.valueOf(goodsRuleVo.getMaxGoodsCount().intValue() - curCount.intValue()) }, ).intValue()), goodsRuleVo);
                /*  78 */
            } catch (Exception e) {
                /*  79 */
                log.debug(e.getMessage(), e);

            }

        }

    }









    @Async

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     public void doCreateGoods(Integer createCount, GoodsRuleVo goodsRuleVo) {
        /*  93 */
        MerchantVo merchantVo = this.merchantApiService.queryMerchant(((MerchantQueryDTO.MerchantQueryDTOBuilder) MerchantQueryDTO.builder().id(goodsRuleVo.getMerchantId())).build());
        /*  94 */
        for (int i = 0; ObjectUtil.isNotEmpty(createCount) && i < createCount.intValue(); i++) {

            try {
                /*  96 */
                BigDecimal amount = RandomUtil.randomBigDecimal(goodsRuleVo.getMinAmount(), goodsRuleVo.getMaxAmount()).setScale(0, RoundingMode.HALF_DOWN);








                /* 105 */
                GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO = GoodsSaveOrUpdateDTO.builder().merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar()).rate(BigDecimal.ZERO).amount(amount).freeAmount(BigDecimal.ZERO).goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).build();
                /* 106 */
                this.goodsApiService.saveOrUpdate(goodsSaveOrUpdateDTO);
                /* 107 */
            } catch (Exception e) {
                /* 108 */
                log.debug(e.getMessage(), e);

            }

        }

    }







    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* 118 */
        this.applicationContext = applicationContext;

    }

}
