
package com.porn.service.workrobot.cron;



import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.api.AccountExtApiService;
import com.porn.client.account.dto.AccountExtQueryDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AccountExtTypeEnum;
import com.porn.client.account.enums.AccountLevelEnum;
import com.porn.client.account.enums.AccountTypeEnum;
import com.porn.client.account.vo.AccountExtVo;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.goods.api.GoodsApiService;
import com.porn.client.goods.dto.GoodsSaveOrUpdateDTO;
import com.porn.client.goods.enums.GoodsStatusEnum;
import com.porn.client.goods.vo.GoodsVo;
import com.porn.client.merchant.api.MerchantApiService;
import com.porn.client.merchant.dto.MerchantQueryDTO;
import com.porn.client.merchant.vo.MerchantVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderSaveOrUpdateDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.enums.OrderTypeEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamSaveOrUpdateDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.workrobot.api.WorkrobotApiService;
import com.porn.client.workrobot.dto.WorkrobotQueryDTO;
import com.porn.client.workrobot.vo.WorkrobotVo;
import com.porn.common.spring.utils.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;




























































@Component
 public class WorkrobotCron
         implements ApplicationContextAware, InitializingBean
         {
    /*  65 */   private static final Logger log = LoggerFactory.getLogger(WorkrobotCron.class);



    @Autowired
     private WorkrobotApiService workrobotApiService;



    @Autowired
     private ParamsetApiService paramsetApiService;



    @Autowired
     private MerchantApiService merchantApiService;



    @Autowired
     private AccountApiService accountApiService;


    @Autowired
     private GoodsApiService goodsApiService;


    @Autowired
     private OrderApiService orderApiService;


    @Autowired
     private StreamApiService streamApiService;


    @Autowired
     private ThreadPoolUtil threadPoolUtil;


    @Autowired
     private RedisTemplate redisTemplate;


    @Autowired
     private AccountExtApiService accountExtApiService;

    /* 101 */   private final List<MerchantVo> cacheMerchantVoList = new CopyOnWriteArrayList<>();
    /* 102 */   private final List<AccountVo> cacheAccountVoList = new CopyOnWriteArrayList<>();


       private ApplicationContext applicationContext;




    public void doWork() {
        /* 109 */
        List<WorkrobotVo> workrobotVoList = this.workrobotApiService.queryWorkrobotList(WorkrobotQueryDTO.builder().build());
        /* 110 */
        if (ObjectUtil.isEmpty(workrobotVoList)) {

            return;

        }

        /* 114 */
        WorkrobotVo workrobotVo = matchWorkrobot(workrobotVoList);
        /* 115 */
        if (ObjectUtil.isEmpty(workrobotVo)) {

            return;

        }


        /* 120 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());
        /* 121 */
        if (ObjectUtil.isEmpty(paramsetVo)) {

            return;

        }

        /* 125 */
        doRobotWork(paramsetVo, workrobotVo);

    }





    public void doRefreshCache() {
        /* 131 */
        refreshCache();

    }









    protected void doRobotWork(ParamsetVo paramsetVo, WorkrobotVo workrobotVo) {
        /* 141 */
        DateTime dateTime1 = DateUtil.parse(workrobotVo.getMinWorkTime(), "yyyy-MM-dd HH:mm:ss");
        /* 142 */
        DateTime dateTime2 = DateUtil.parse(workrobotVo.getMaxWorkTime(), "yyyy-MM-dd HH:mm:ss");

        /* 144 */
        long second = DateUtil.between((Date) dateTime1, (Date) dateTime2, DateUnit.SECOND);

        /* 146 */
        BigDecimal secondTime = NumberUtil.div(Long.valueOf(second), new BigDecimal(10)).setScale(0, RoundingMode.HALF_DOWN);


        /* 149 */
        Integer orderCount = Integer.valueOf(NumberUtil.div(workrobotVo.getOrderCount(), secondTime).setScale(0, RoundingMode.HALF_DOWN).intValue());


        /* 152 */
        Integer dbCount = (Integer) this.redisTemplate.opsForValue().get(StrUtil.join("_", new Object[]{"robotprefix_", DateUtil.format((Date) dateTime1, "yyyyMMddHHmmss"), DateUtil.format((Date) dateTime2, "yyyyMMddHHmmss")}));
        /* 153 */
        if (ObjectUtil.isEmpty(dbCount)) {
            /* 154 */
            this.redisTemplate.opsForValue().set(StrUtil.join("_", new Object[]{"robotprefix_", DateUtil.format((Date) dateTime1, "yyyyMMddHHmmss"), DateUtil.format((Date) dateTime2, "yyyyMMddHHmmss")}), Integer.valueOf(0), 1L, TimeUnit.HOURS);

        } else {
            /* 156 */
            if (NumberUtil.compare(dbCount.intValue(), workrobotVo.getOrderCount().intValue()) >= 0) {

                return;

            }

            /* 160 */
            if (NumberUtil.compare(NumberUtil.add(dbCount, orderCount).intValue(), workrobotVo.getOrderCount().intValue()) > 0) {
                /* 161 */
                orderCount = Integer.valueOf(NumberUtil.sub(workrobotVo.getOrderCount(), dbCount).intValue());

            }

            /* 164 */
            Date now = new Date();
            /* 165 */
            DateTime dateTime3 = DateUtil.offsetSecond(now, 10);
            /* 166 */
            DateTime dateTime4 = DateUtil.parse(workrobotVo.getMaxWorkTime(), "yyyy-MM-dd HH:mm:ss");
            /* 167 */
            dateTime4.setYear(now.getYear());
            /* 168 */
            dateTime4.setMonth(now.getMonth());
            /* 169 */
            dateTime4.setDate(now.getDate());
            /* 170 */
            if (DateUtil.compare((Date) dateTime3, (Date) dateTime4) > 0)
                 {
                /* 172 */
                orderCount = Integer.valueOf(NumberUtil.sub(workrobotVo.getOrderCount(), dbCount).setScale(0, RoundingMode.HALF_DOWN).intValue());

            }

        }

        /* 176 */
        Integer newOrderCount = orderCount;
        /* 177 */
        this.threadPoolUtil.addTask(() -> doRobotWorkPlay(paramsetVo, workrobotVo, newOrderCount));





        /* 183 */
        this.redisTemplate.opsForValue().increment(StrUtil.join("_", new Object[]{"robotprefix_", DateUtil.format((Date) dateTime1, "yyyyMMddHHmmss"), DateUtil.format((Date) dateTime2, "yyyyMMddHHmmss")}), orderCount.intValue());

    }










    protected void doRobotWorkPlay(ParamsetVo paramsetVo, WorkrobotVo workrobotVo, Integer orderCount) {
        /* 194 */
        if (this.cacheMerchantVoList.size() == 0 || this.cacheAccountVoList
/* 195 */.size() == 0) {

            return;

        }
        /* 198 */
        List<MerchantVo> merchantVoList = this.cacheMerchantVoList;
        /* 199 */
        List<AccountVo> accountVoList = this.cacheAccountVoList;

        /* 201 */
        for (int i = 0; i < orderCount.intValue(); i++) {

            try {
                /* 203 */
                MerchantVo merchantVo = RandomUtil.randomEles(merchantVoList, 1).get(0);
                /* 204 */
                AccountVo accountVo = RandomUtil.randomEles(accountVoList, 1).get(0);
                /* 205 */
                BigDecimal amount = RandomUtil.randomBigDecimal(workrobotVo.getMinWorkAmount(), workrobotVo.getMaxWorkAmount()).setScale(0, RoundingMode.HALF_UP);

                /* 207 */
                GoodsVo goodsVo = ((WorkrobotCron) this.applicationContext.getBean(WorkrobotCron.class)).createGoods(paramsetVo, merchantVo, amount);

                /* 209 */
                ((WorkrobotCron) this.applicationContext.getBean(WorkrobotCron.class)).doWorkGoods(paramsetVo, accountVo, merchantVo, goodsVo);


            }
            /* 212 */ catch (Exception e) {
                /* 213 */
                log.error(e.getMessage(), e);

            }

        }

    }











    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     public void doWorkGoods(ParamsetVo paramsetVo, AccountVo accountVo, MerchantVo merchantVo, GoodsVo goodsVo) {
        /* 228 */
        OrderVo orderVo = this.orderApiService.saveOrUpdate(
                /* 229 */         OrderSaveOrUpdateDTO.builder()
/* 230 */.accountId(accountVo.getId()).accountName(accountVo.getName()).accountAvatar(accountVo.getAvatar())
/* 231 */.merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar())
/* 232 */.orderAmount(goodsVo.getAmount())
/* 233 */.orderRate(goodsVo.getRate())
/* 234 */.freeAmount(buildFreeAmount(accountVo, paramsetVo, goodsVo))
/* 235 */.orderStatus(OrderStatusEnum.CONFIRED.getStatus())
/* 236 */.orderType(OrderTypeEnum.ROBOT.getType())
/* 237 */.build());



        /* 241 */
        this.streamApiService.saveOrUpdate(
                /* 242 */         StreamSaveOrUpdateDTO.builder()
/* 243 */.accountId(accountVo.getId()).accountName(accountVo.getName())
/* 244 */.beforeTotalBalance(BigDecimal.ZERO).beforeAvailableBalance(BigDecimal.ZERO).beforeFreezeBalance(BigDecimal.ZERO)
/* 245 */.afterTotalBalance(BigDecimal.ZERO).afterAvailableBalance(BigDecimal.ZERO).afterFreezeBalance(BigDecimal.ZERO)
/* 246 */.bizId(orderVo.getId())
/* 247 */.amount(goodsVo.getAmount())
/* 248 */.type(StreamTypeEnum.WORKING_SUB.getType())
/* 249 */.flag(StreamTypeEnum.WORKING_SUB.getFlag())
/* 250 */.build());



        /* 254 */
        this.streamApiService.saveOrUpdate(
                /* 255 */         StreamSaveOrUpdateDTO.builder()
/* 256 */.accountId(accountVo.getId()).accountName(accountVo.getName())
/* 257 */.beforeTotalBalance(BigDecimal.ZERO).beforeAvailableBalance(BigDecimal.ZERO).beforeFreezeBalance(BigDecimal.ZERO)
/* 258 */.afterTotalBalance(BigDecimal.ZERO).afterAvailableBalance(BigDecimal.ZERO).afterFreezeBalance(BigDecimal.ZERO)
/* 259 */.bizId(orderVo.getId())
/* 260 */.amount(orderVo.getFreeAmount())
/* 261 */.type(StreamTypeEnum.WORKING_ADD.getType())
/* 262 */.flag(StreamTypeEnum.WORKING_ADD.getFlag())
/* 263 */.build());



        /* 267 */
        this.goodsApiService.saveOrUpdate((
                /* 268 */         (GoodsSaveOrUpdateDTO.GoodsSaveOrUpdateDTOBuilder) GoodsSaveOrUpdateDTO.builder()
/* 269 */.id(goodsVo.getId()))
/* 270 */.goodsStatus(GoodsStatusEnum.WORKED.getStatus())
/* 271 */.build());

    }










    public BigDecimal buildFreeAmount(AccountVo accountVo, ParamsetVo paramsetVo, GoodsVo goodsVo) {
        /* 282 */
        AccountExtVo accountExtVo = this.accountExtApiService.queryAccountExt(
                /* 283 */         AccountExtQueryDTO.builder()
/* 284 */.accountId(accountVo.getId())
/* 285 */.extType(AccountExtTypeEnum.EXTRAREBATE.getType())
/* 286 */.extKey(AccountExtTypeEnum.EXTRAREBATE.toString())
/* 287 */.build());


        /* 290 */
        BigDecimal extraRebate = BigDecimal.ZERO;
        /* 291 */
        if (ObjectUtil.isNotEmpty(accountExtVo)) {


            try {
                /* 294 */
                extraRebate = new BigDecimal(accountExtVo.getExtValue());
                /* 295 */
            } catch (Exception e) {
                /* 296 */
                log.debug("获取额外加成数据异常[{}].", e);
                /* 297 */
                extraRebate = BigDecimal.ZERO;

            }

        }
        /* 300 */
        if (accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel())
            /* 301 */
            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getNormalLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);
        /* 302 */
        if (accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) {
            /* 303 */
            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getLargeLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

        }
        /* 305 */
        return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getPartnerLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

    }


















    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     public GoodsVo createGoods(ParamsetVo paramsetVo, MerchantVo merchantVo, BigDecimal amount) {
        /* 325 */
        GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO = GoodsSaveOrUpdateDTO.builder().merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar()).amount(amount).rate(BigDecimal.ZERO).freeAmount(BigDecimal.ZERO).goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).build();
        /* 326 */
        return this.goodsApiService.saveOrUpdate(goodsSaveOrUpdateDTO);

    }








    protected WorkrobotVo matchWorkrobot(List<WorkrobotVo> workrobotVoList) {
        /* 335 */
        if (ObjectUtil.isEmpty(workrobotVoList)) {
            /* 336 */
            return null;

        }
        /* 338 */
        for (WorkrobotVo workrobotVo : workrobotVoList) {
            /* 339 */
            if (matchWorkrobot(workrobotVo)) {
                /* 340 */
                return workrobotVo;

            }

        }
        /* 343 */
        return null;

    }







    protected boolean matchWorkrobot(WorkrobotVo workrobotVo) {
        /* 351 */
        if (ObjectUtil.isEmpty(workrobotVo)) {
            /* 352 */
            return false;

        }
        /* 354 */
        Date now = new Date();
        /* 355 */
        DateTime dateTime1 = DateUtil.parse(workrobotVo.getMinWorkTime(), "yyyy-MM-dd HH:mm:ss");
        /* 356 */
        dateTime1.setYear(now.getYear());
        /* 357 */
        dateTime1.setMonth(now.getMonth());
        /* 358 */
        dateTime1.setDate(now.getDate());
        /* 359 */
        DateTime dateTime2 = DateUtil.parse(workrobotVo.getMaxWorkTime(), "yyyy-MM-dd HH:mm:ss");
        /* 360 */
        dateTime2.setYear(now.getYear());
        /* 361 */
        dateTime2.setMonth(now.getMonth());
        /* 362 */
        dateTime2.setDate(now.getDate());

        /* 364 */
        return DateUtil.isIn(now, (Date) dateTime1, (Date) dateTime2);

    }




    public void afterPropertiesSet() throws Exception {
        /* 369 */
        refreshCache();

    }






    public void refreshCache() {
        /* 376 */
        MerchantQueryDTO merchantQueryDTO = MerchantQueryDTO.builder().build();
        /* 377 */
        List<MerchantVo> merchantVoList = this.merchantApiService.queryMerchantList(merchantQueryDTO);
        /* 378 */
        if (ObjectUtil.isNotEmpty(merchantVoList)) {
            /* 379 */
            this.cacheMerchantVoList.clear();
            /* 380 */
            this.cacheMerchantVoList.addAll(merchantVoList);

        }



        /* 385 */
        AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().accountType(AccountTypeEnum.ROBOT.getType()).build();
        /* 386 */
        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);
        /* 387 */
        if (ObjectUtil.isNotEmpty(accountVoList)) {
            /* 388 */
            this.cacheAccountVoList.clear();
            /* 389 */
            this.cacheAccountVoList.addAll(accountVoList);

        }

    }






    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* 397 */
        this.applicationContext = applicationContext;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/workrobot/cron/WorkrobotCron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */