package com.porn.service.mobile.api.impl;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.MyTeamTreeVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class QueryTeamTreeApiServiceImpl
        implements ApiService<List<MyTeamTreeVo>> {

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private OrderApiService orderApiService;


    public List<MyTeamTreeVo> cmd(CmdRequestDTO cmdRequestDTO) {

        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build());


        List<AccountVo> firstLevelAccount = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentId(accountVo.getId()).build());


        List<AccountVo> secondLevelAccount = ListUtil.list(false);

        if (ObjectUtil.isNotEmpty(firstLevelAccount)) {

            secondLevelAccount = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentIdList((List) firstLevelAccount.stream().map(BaseVo::getId).collect(Collectors.toList())).build());

        }


        List<AccountVo> thirdLevelAccount = ListUtil.list(false);

        if (ObjectUtil.isNotEmpty(secondLevelAccount)) {

            thirdLevelAccount = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentIdList((List) secondLevelAccount.stream().map(BaseVo::getId).collect(Collectors.toList())).build());

        }


        List<MyTeamTreeVo> result = new ArrayList<>();


        MyTeamTreeVo root = MyTeamTreeVo.builder().accountId(accountVo.getId()).accountName(ObjectUtil.isEmpty(accountVo) ? "" : accountVo.getName()).avatar((ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatar())) ? "" : accountVo.getAvatar()).avatarUrl((ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatarUrl())) ? "" : accountVo.getAvatarUrl()).workTotalAmount(BigDecimal.ZERO).freeTotalAmount(BigDecimal.ZERO).registerTime(accountVo.getCreateTime()).parentAccountId(CommonConst.LZERO).build();

        result.add(root);


        List<AccountVo> sunAccountList = new ArrayList<>();

        if (ObjectUtil.isNotEmpty(firstLevelAccount)) {

            sunAccountList.addAll(firstLevelAccount);

        }

        if (ObjectUtil.isNotEmpty(secondLevelAccount)) {

            sunAccountList.addAll(secondLevelAccount);

        }

        if (ObjectUtil.isNotEmpty(thirdLevelAccount)) {

            sunAccountList.addAll(thirdLevelAccount);

        }

        result.addAll(toTeamTreeVoListNew(sunAccountList));

        return result;

    }

    protected List<MyTeamTreeVo> toTeamTreeVoListNew(List<AccountVo> accountList) {

        if (ObjectUtil.isEmpty(accountList)) {

            return Collections.emptyList();

        }

        return toTeamTreeVoList((List<Long>) accountList.stream().map(BaseVo::getId).collect(Collectors.toList()));

    }


    protected List<MyTeamTreeVo> toTeamTreeVoList(List<Long> accountIdList) {

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

        List<MyTeamTreeVo> result = new ArrayList<>();

        accountVoList.forEach(accountVo -> {

            List<OrderVo> orders = (List<OrderVo>) orderMap.get(accountVo.getId());


            MyTeamTreeVo myTeamTreeVo = MyTeamTreeVo.builder().accountId(accountVo.getId()).accountName(ObjectUtil.isEmpty(accountVo) ? "" : accountVo.getName()).avatar((ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatar())) ? "" : accountVo.getAvatar()).avatarUrl(
                    (ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatarUrl())) ? "" : accountVo.getAvatarUrl()).workTotalAmount(ObjectUtil.isEmpty(orders) ? BigDecimal.ZERO : orders.stream().map(OrderVo::getOrderAmount).reduce(BigDecimal.ZERO, BigDecimal::add)).freeTotalAmount(ObjectUtil.isEmpty(orders) ? BigDecimal.ZERO : orders.stream().map(OrderVo::getFreeAmount).reduce(BigDecimal.ZERO, BigDecimal::add)).registerTime(accountVo.getCreateTime()).parentAccountId(accountVo.getParentId()).build();

            result.add(myTeamTreeVo);

        });

        return result;

    }


    public String getApi() {

        return "api_queryteamtree";

    }

}

