
package com.porn.service.mobile.api.impl;



import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.TodayTopAccountVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;








































@Service
 public class TodayTopAccountApiServiceImpl
         implements ApiService<List<TodayTopAccountVo>>, InitializingBean
         {

    @Autowired
     private OrderApiService orderApiService;

    @Autowired
     private AccountApiService accountApiService;
    /*  49 */   private final ReentrantLock lock = new ReentrantLock();


    /*  52 */   private final List<TodayTopAccountVo> localCacheData = new CopyOnWriteArrayList<>();




    public List<TodayTopAccountVo> cmd(CmdRequestDTO cmdRequestDTO) {

        try {
            /*  57 */
            this.lock.lock();
            /*  58 */
            return this.localCacheData;

        } finally {
            /*  60 */
            this.lock.unlock();

        }

    }






    protected void refreshCache() {

        try {
            /*  69 */
            this.lock.lock();
            /*  70 */
            List<TodayTopAccountVo> newLocalCacheData = loadLocalCache();
            /*  71 */
            this.localCacheData.clear();
            /*  72 */
            this.localCacheData.addAll(newLocalCacheData);

        } finally {
            /*  74 */
            this.lock.unlock();

        }

    }











    protected List<TodayTopAccountVo> loadLocalCache() {
        /*  87 */
        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).endTime(LocalDateTimeUtil.endOfDay(LocalDateTimeUtil.now())).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).build();
        /*  88 */
        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);
        /*  89 */
        List<TodayTopAccountVo> result = new ArrayList<>();
        /*  90 */
        if (ObjectUtil.isNotEmpty(orderVoList)) {

            /*  92 */
            Map<Long, List<OrderVo>> orderVoMap = (Map<Long, List<OrderVo>>) orderVoList.stream().collect(Collectors.groupingBy(OrderVo::getAccountId));
            /*  93 */
            List<AccountVo> accountVoList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().accountIdList(new ArrayList(orderVoMap.keySet())).build());
            /*  94 */
            Map<Long, AccountVo> accountVoMap = (Map<Long, AccountVo>) accountVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));
            /*  95 */
            for (Long accountId : orderVoMap.keySet()) {
                /*  96 */
                List<OrderVo> orderVos = orderVoMap.get(accountId);
                /*  97 */
                BigDecimal todayTotalAmount = BigDecimal.ZERO;
                /*  98 */
                BigDecimal todayTotalFree = BigDecimal.ZERO;
                /*  99 */
                for (OrderVo orderVo : orderVos) {
                    /* 100 */
                    todayTotalAmount = NumberUtil.add(todayTotalAmount, orderVo.getOrderAmount());
                    /* 101 */
                    todayTotalFree = NumberUtil.add(todayTotalFree, orderVo.getFreeAmount());

                }
                /* 103 */
                AccountVo accountVo = accountVoMap.get(accountId);








                /* 112 */
                TodayTopAccountVo todayTopAccountVo = TodayTopAccountVo.builder().accountId(accountId).accountName(((OrderVo) orderVos.get(0)).getAccountName()).nickName((ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getNickName())) ? "" : accountVo.getNickName()).accountAvatar(((OrderVo) orderVos.get(0)).getAccountAvatar()).accountAvatarUrl(((OrderVo) orderVos.get(0)).getAccountAvatarUrl()).todayTotalAmount(todayTotalAmount).todayTotalFree(todayTotalFree).build();
                /* 113 */
                result.add(todayTopAccountVo);

            }

            /* 116 */
            result = (List<TodayTopAccountVo>) result.stream().sorted(Comparator.comparing(TodayTopAccountVo::getTodayTotalAmount).reversed()).collect(Collectors.toList());

        }
        /* 118 */
        if (ObjectUtil.isEmpty(result) || result
/* 119 */.size() <= 100) {
            /* 120 */
            return result;

        }
        /* 122 */
        return ListUtil.sub(result, 0, 100);

    }



    @Scheduled(cron = "0 0/30 * * * ?")
     public void doRefresh() {
        /* 127 */
        refreshCache();

    }



    public void afterPropertiesSet() throws Exception {
        /* 131 */
        refreshCache();

    }



    public String getApi() {
        /* 135 */
        return "api_todaytopaccount";

    }

}


