
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
         implements ApiService<List<MultistageLevelFreeVo>>
         {

    @Autowired
     private AccountApiService accountApiService;

    @Autowired
     private StreamApiService streamApiService;

    @Autowired
     private OrderApiService orderApiService;



    public List<MultistageLevelFreeVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /*  47 */
        MultistageLevelFreeApiRequestDTO multistageLevelFreeApiRequestDTO = (MultistageLevelFreeApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), MultistageLevelFreeApiRequestDTO.class);


        /*  50 */
        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build());
        /*  51 */
        if (Integer.valueOf(1).equals(multistageLevelFreeApiRequestDTO.getLevelType())) {

            /*  53 */
            List<AccountVo> list = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentPromotionCodeList(Arrays.asList(new String[]{accountVo.getPromotionCode()})).build());
            /*  54 */
            if (ObjectUtil.isEmpty(list)) {
                /*  55 */
                return Collections.emptyList();

            }

            /*  58 */
            List<Long> level1IdList = (List<Long>) list.stream().map(BaseVo::getId).collect(Collectors.toList());
            /*  59 */
            return toMultistageLevelFreeVoList(level1IdList);
            /*  60 */
        }
        if (Integer.valueOf(2).equals(multistageLevelFreeApiRequestDTO.getLevelType())) {


            /*  63 */
            List<AccountVo> list1 = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentPromotionCodeList(Arrays.asList(new String[]{accountVo.getPromotionCode()})).build());
            /*  64 */
            if (ObjectUtil.isEmpty(list1)) {
                /*  65 */
                return Collections.emptyList();

            }

            /*  68 */
            List<AccountVo> list2 = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentPromotionCodeList((List) list1.stream().map(AccountVo::getPromotionCode).collect(Collectors.toList())).build());
            /*  69 */
            if (ObjectUtil.isEmpty(list2)) {
                /*  70 */
                return Collections.emptyList();

            }
            /*  72 */
            return toMultistageLevelFreeVoList((List<Long>) list2.stream().map(BaseVo::getId).collect(Collectors.toList()));

        }


        /*  76 */
        List<AccountVo> level1AccountList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentPromotionCodeList(Arrays.asList(new String[]{accountVo.getPromotionCode()})).build());
        /*  77 */
        if (ObjectUtil.isEmpty(level1AccountList)) {
            /*  78 */
            return Collections.emptyList();

        }

        /*  81 */
        List<AccountVo> level2AccountList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentPromotionCodeList((List) level1AccountList.stream().map(AccountVo::getPromotionCode).collect(Collectors.toList())).build());
        /*  82 */
        if (ObjectUtil.isEmpty(level2AccountList)) {
            /*  83 */
            return Collections.emptyList();

        }

        /*  86 */
        List<AccountVo> level3AccountList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentPromotionCodeList((List) level2AccountList.stream().map(AccountVo::getPromotionCode).collect(Collectors.toList())).build());
        /*  87 */
        if (ObjectUtil.isEmpty(level3AccountList)) {
            /*  88 */
            return Collections.emptyList();

        }
        /*  90 */
        return toMultistageLevelFreeVoList((List<Long>) level3AccountList.stream().map(BaseVo::getId).collect(Collectors.toList()));

    }









    protected List<MultistageLevelFreeVo> toMultistageLevelFreeVoList(List<Long> accountIdList) {
        /* 100 */
        if (ObjectUtil.isEmpty(accountIdList)) {
            /* 101 */
            return Collections.emptyList();

        }

        /* 104 */
        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(OrderQueryDTO.builder().accountIdList(accountIdList).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).build());
        /* 105 */
        if (null == orderVoList) {
            /* 106 */
            orderVoList = ListUtil.list(false);

        }

        /* 109 */
        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().accountIdList(accountIdList).build());
        /* 110 */
        Map<Long, AccountVo> accountVoMap = (Map<Long, AccountVo>) accountVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));

        /* 112 */
        Map<Long, List<OrderVo>> orderMap = (Map<Long, List<OrderVo>>) orderVoList.stream().collect(Collectors.groupingBy(OrderVo::getAccountId));
        /* 113 */
        List<MultistageLevelFreeVo> result = new ArrayList<>();
        /* 114 */
        accountVoList.forEach(accountVo -> {

            List<OrderVo> orders = (List<OrderVo>) orderMap.get(accountVo.getId());


            /* 118 */
            MultistageLevelFreeVo multistageLevelFreeVo = MultistageLevelFreeVo.builder().accountId(accountVo.getId()).accountName(ObjectUtil.isEmpty(accountVo) ? "" : accountVo.getName()).avatar((ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatar())) ? "" : accountVo.getAvatar()).avatarUrl(
                    /* 119 */               (ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatarUrl())) ? "" : accountVo.getAvatarUrl()).workTotalAmount(ObjectUtil.isEmpty(orders) ? BigDecimal.ZERO : orders.stream().map(OrderVo::getOrderAmount).reduce(BigDecimal.ZERO, BigDecimal::add)).freeTotalAmount(ObjectUtil.isEmpty(orders) ? BigDecimal.ZERO : orders.stream().map(OrderVo::getFreeAmount).reduce(BigDecimal.ZERO, BigDecimal::add)).registerTime(accountVo.getCreateTime()).build();




            result.add(multistageLevelFreeVo);

        });


        /* 127 */
        return result;

    }




    public String getApi() {
        /* 132 */
        return "api_multistagelevelfree";

    }

}


