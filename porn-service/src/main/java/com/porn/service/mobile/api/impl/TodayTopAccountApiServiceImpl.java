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
        implements ApiService<List<TodayTopAccountVo>>, InitializingBean {

    private final ReentrantLock lock = new ReentrantLock();
    private final List<TodayTopAccountVo> localCacheData = new CopyOnWriteArrayList<>();
    @Autowired
    private OrderApiService orderApiService;
    @Autowired
    private AccountApiService accountApiService;

    public List<TodayTopAccountVo> cmd(CmdRequestDTO cmdRequestDTO) {

        try {

            this.lock.lock();

            return this.localCacheData;

        } finally {

            this.lock.unlock();

        }

    }

    protected void refreshCache() {

        try {

            this.lock.lock();

            List<TodayTopAccountVo> newLocalCacheData = loadLocalCache();

            this.localCacheData.clear();

            this.localCacheData.addAll(newLocalCacheData);

        } finally {

            this.lock.unlock();

        }

    }


    protected List<TodayTopAccountVo> loadLocalCache() {

        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).endTime(LocalDateTimeUtil.endOfDay(LocalDateTimeUtil.now())).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).build();

        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);

        List<TodayTopAccountVo> result = new ArrayList<>();

        if (ObjectUtil.isNotEmpty(orderVoList)) {


            Map<Long, List<OrderVo>> orderVoMap = (Map<Long, List<OrderVo>>) orderVoList.stream().collect(Collectors.groupingBy(OrderVo::getAccountId));

            List<AccountVo> accountVoList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().accountIdList(new ArrayList(orderVoMap.keySet())).build());

            Map<Long, AccountVo> accountVoMap = (Map<Long, AccountVo>) accountVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));

            for (Long accountId : orderVoMap.keySet()) {

                List<OrderVo> orderVos = orderVoMap.get(accountId);

                BigDecimal todayTotalAmount = BigDecimal.ZERO;

                BigDecimal todayTotalFree = BigDecimal.ZERO;

                for (OrderVo orderVo : orderVos) {

                    todayTotalAmount = NumberUtil.add(todayTotalAmount, orderVo.getOrderAmount());

                    todayTotalFree = NumberUtil.add(todayTotalFree, orderVo.getFreeAmount());

                }

                AccountVo accountVo = accountVoMap.get(accountId);


                TodayTopAccountVo todayTopAccountVo = TodayTopAccountVo.builder().accountId(accountId).accountName(((OrderVo) orderVos.get(0)).getAccountName()).nickName((ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getNickName())) ? "" : accountVo.getNickName()).accountAvatar(((OrderVo) orderVos.get(0)).getAccountAvatar()).accountAvatarUrl(((OrderVo) orderVos.get(0)).getAccountAvatarUrl()).todayTotalAmount(todayTotalAmount).todayTotalFree(todayTotalFree).build();

                result.add(todayTopAccountVo);

            }


            result = (List<TodayTopAccountVo>) result.stream().sorted(Comparator.comparing(TodayTopAccountVo::getTodayTotalAmount).reversed()).collect(Collectors.toList());

        }

        if (ObjectUtil.isEmpty(result) || result
                .size() <= 100) {

            return result;

        }

        return ListUtil.sub(result, 0, 100);

    }


    @Scheduled(cron = "0 0/30 * * * ?")
    public void doRefresh() {

        refreshCache();

    }


    public void afterPropertiesSet() throws Exception {

        refreshCache();

    }


    public String getApi() {

        return "api_todaytopaccount";

    }

}

