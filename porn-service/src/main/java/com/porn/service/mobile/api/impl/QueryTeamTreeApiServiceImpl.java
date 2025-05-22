
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
         implements ApiService<List<MyTeamTreeVo>>
         {

    @Autowired
     private AccountApiService accountApiService;

    @Autowired
     private OrderApiService orderApiService;



    public List<MyTeamTreeVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /*  45 */
        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build());

        /*  47 */
        List<AccountVo> firstLevelAccount = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentId(accountVo.getId()).build());

        /*  49 */
        List<AccountVo> secondLevelAccount = ListUtil.list(false);
        /*  50 */
        if (ObjectUtil.isNotEmpty(firstLevelAccount)) {
            /*  51 */
            secondLevelAccount = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentIdList((List) firstLevelAccount.stream().map(BaseVo::getId).collect(Collectors.toList())).build());

        }

        /*  54 */
        List<AccountVo> thirdLevelAccount = ListUtil.list(false);
        /*  55 */
        if (ObjectUtil.isNotEmpty(secondLevelAccount)) {
            /*  56 */
            thirdLevelAccount = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentIdList((List) secondLevelAccount.stream().map(BaseVo::getId).collect(Collectors.toList())).build());

        }

        /*  59 */
        List<MyTeamTreeVo> result = new ArrayList<>();









        /*  69 */
        MyTeamTreeVo root = MyTeamTreeVo.builder().accountId(accountVo.getId()).accountName(ObjectUtil.isEmpty(accountVo) ? "" : accountVo.getName()).avatar((ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatar())) ? "" : accountVo.getAvatar()).avatarUrl((ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatarUrl())) ? "" : accountVo.getAvatarUrl()).workTotalAmount(BigDecimal.ZERO).freeTotalAmount(BigDecimal.ZERO).registerTime(accountVo.getCreateTime()).parentAccountId(CommonConst.LZERO).build();
        /*  70 */
        result.add(root);


        /*  73 */
        List<AccountVo> sunAccountList = new ArrayList<>();
        /*  74 */
        if (ObjectUtil.isNotEmpty(firstLevelAccount)) {
            /*  75 */
            sunAccountList.addAll(firstLevelAccount);

        }
        /*  77 */
        if (ObjectUtil.isNotEmpty(secondLevelAccount)) {
            /*  78 */
            sunAccountList.addAll(secondLevelAccount);

        }
        /*  80 */
        if (ObjectUtil.isNotEmpty(thirdLevelAccount)) {
            /*  81 */
            sunAccountList.addAll(thirdLevelAccount);

        }
        /*  83 */
        result.addAll(toTeamTreeVoListNew(sunAccountList));
        /*  84 */
        return result;

    }








    protected List<MyTeamTreeVo> toTeamTreeVoListNew(List<AccountVo> accountList) {
        /*  93 */
        if (ObjectUtil.isEmpty(accountList)) {
            /*  94 */
            return Collections.emptyList();

        }
        /*  96 */
        return toTeamTreeVoList((List<Long>) accountList.stream().map(BaseVo::getId).collect(Collectors.toList()));

    }









    protected List<MyTeamTreeVo> toTeamTreeVoList(List<Long> accountIdList) {
        /* 106 */
        if (ObjectUtil.isEmpty(accountIdList)) {
            /* 107 */
            return Collections.emptyList();

        }

        /* 110 */
        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(OrderQueryDTO.builder().accountIdList(accountIdList).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).build());
        /* 111 */
        if (null == orderVoList) {
            /* 112 */
            orderVoList = ListUtil.list(false);

        }

        /* 115 */
        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().accountIdList(accountIdList).build());
        /* 116 */
        Map<Long, AccountVo> accountVoMap = (Map<Long, AccountVo>) accountVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));

        /* 118 */
        Map<Long, List<OrderVo>> orderMap = (Map<Long, List<OrderVo>>) orderVoList.stream().collect(Collectors.groupingBy(OrderVo::getAccountId));
        /* 119 */
        List<MyTeamTreeVo> result = new ArrayList<>();
        /* 120 */
        accountVoList.forEach(accountVo -> {

            List<OrderVo> orders = (List<OrderVo>) orderMap.get(accountVo.getId());


            /* 124 */
            MyTeamTreeVo myTeamTreeVo = MyTeamTreeVo.builder().accountId(accountVo.getId()).accountName(ObjectUtil.isEmpty(accountVo) ? "" : accountVo.getName()).avatar((ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatar())) ? "" : accountVo.getAvatar()).avatarUrl(
                    /* 125 */               (ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatarUrl())) ? "" : accountVo.getAvatarUrl()).workTotalAmount(ObjectUtil.isEmpty(orders) ? BigDecimal.ZERO : orders.stream().map(OrderVo::getOrderAmount).reduce(BigDecimal.ZERO, BigDecimal::add)).freeTotalAmount(ObjectUtil.isEmpty(orders) ? BigDecimal.ZERO : orders.stream().map(OrderVo::getFreeAmount).reduce(BigDecimal.ZERO, BigDecimal::add)).registerTime(accountVo.getCreateTime()).parentAccountId(accountVo.getParentId()).build();




            result.add(myTeamTreeVo);

        });



        /* 134 */
        return result;

    }



    public String getApi() {
        /* 138 */
        return "api_queryteamtree";

    }

}


