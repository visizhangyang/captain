package com.porn.service.mobile.api.impl;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.MultistageLevelFreeApiRequestDTO;
import com.porn.client.mobile.vo.MultistageLevelFreeVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MultistageLevelFreeApiServiceImpl
        implements ApiService<List<MultistageLevelFreeVo>> {

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private StreamApiService streamApiService;

    @Autowired
    private OrderApiService orderApiService;


    public List<MultistageLevelFreeVo> cmd(CmdRequestDTO cmdRequestDTO) {

        MultistageLevelFreeApiRequestDTO multistageLevelFreeApiRequestDTO = (MultistageLevelFreeApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), MultistageLevelFreeApiRequestDTO.class);


        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build());

        if (Integer.valueOf(1).equals(multistageLevelFreeApiRequestDTO.getLevelType())) {


            List<AccountVo> list = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentPromotionCodeList(Arrays.asList(new String[]{accountVo.getPromotionCode()})).build());

            if (ObjectUtil.isEmpty(list)) {

                return Collections.emptyList();

            }


            List<Long> level1IdList = (List<Long>) list.stream().map(BaseVo::getId).collect(Collectors.toList());

            return toMultistageLevelFreeVoList(level1IdList);

        }
        if (Integer.valueOf(2).equals(multistageLevelFreeApiRequestDTO.getLevelType())) {


            List<AccountVo> list1 = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentPromotionCodeList(Arrays.asList(new String[]{accountVo.getPromotionCode()})).build());

            if (ObjectUtil.isEmpty(list1)) {

                return Collections.emptyList();

            }


            List<AccountVo> list2 = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentPromotionCodeList((List) list1.stream().map(AccountVo::getPromotionCode).collect(Collectors.toList())).build());

            if (ObjectUtil.isEmpty(list2)) {

                return Collections.emptyList();

            }

            return toMultistageLevelFreeVoList((List<Long>) list2.stream().map(BaseVo::getId).collect(Collectors.toList()));

        }


        List<AccountVo> level1AccountList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentPromotionCodeList(Arrays.asList(new String[]{accountVo.getPromotionCode()})).build());

        if (ObjectUtil.isEmpty(level1AccountList)) {

            return Collections.emptyList();

        }


        List<AccountVo> level2AccountList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentPromotionCodeList((List) level1AccountList.stream().map(AccountVo::getPromotionCode).collect(Collectors.toList())).build());

        if (ObjectUtil.isEmpty(level2AccountList)) {

            return Collections.emptyList();

        }


        List<AccountVo> level3AccountList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentPromotionCodeList((List) level2AccountList.stream().map(AccountVo::getPromotionCode).collect(Collectors.toList())).build());

        if (ObjectUtil.isEmpty(level3AccountList)) {

            return Collections.emptyList();

        }

        return toMultistageLevelFreeVoList((List<Long>) level3AccountList.stream().map(BaseVo::getId).collect(Collectors.toList()));

    }


    protected List<MultistageLevelFreeVo> toMultistageLevelFreeVoList(List<Long> accountIdList) {

        if (ObjectUtil.isEmpty(accountIdList)) {

            return Collections.emptyList();

        }

        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(OrderQueryDTO.builder().accountIdList(accountIdList).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).build());

        if (null == orderVoList) {

            orderVoList = ListUtil.list(false);

        }

        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().accountIdList(accountIdList).build());

        Map<Long, AccountVo> accountVoMap = (Map<Long, AccountVo>) accountVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));

        Map<Long, List<OrderVo>> orderMap = (Map<Long, List<OrderVo>>) orderVoList.stream().collect(Collectors.groupingBy(OrderVo::getAccountId));

        List<MultistageLevelFreeVo> result = new ArrayList<>();

        accountVoList.forEach(accountVo -> {

            List<OrderVo> orders = (List<OrderVo>) orderMap.get(accountVo.getId());


            MultistageLevelFreeVo multistageLevelFreeVo = MultistageLevelFreeVo.builder().accountId(accountVo.getId()).accountName(ObjectUtil.isEmpty(accountVo) ? "" : accountVo.getName()).avatar((ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatar())) ? "" : accountVo.getAvatar()).avatarUrl(
                    (ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatarUrl())) ? "" : accountVo.getAvatarUrl()).workTotalAmount(ObjectUtil.isEmpty(orders) ? BigDecimal.ZERO : orders.stream().map(OrderVo::getOrderAmount).reduce(BigDecimal.ZERO, BigDecimal::add)).freeTotalAmount(ObjectUtil.isEmpty(orders) ? BigDecimal.ZERO : orders.stream().map(OrderVo::getFreeAmount).reduce(BigDecimal.ZERO, BigDecimal::add)).registerTime(accountVo.getCreateTime()).build();

            result.add(multistageLevelFreeVo);

        });


        return result;

    }

    public String getApi() {

        return "api_multistagelevelfree";

    }

}

