package com.porn.service.mobile.api.impl;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.MyTeamVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class QueryTeamApiServiceImpl
        implements ApiService<MyTeamVo> {

    @Autowired
    private AccountApiService accountApiService;


    public MyTeamVo cmd(CmdRequestDTO cmdRequestDTO) {

        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build());

        Integer level1Count = Integer.valueOf(0);

        Integer level2Count = Integer.valueOf(0);

        Integer level3Count = Integer.valueOf(0);

        MyTeamVo.MyTeamVoBuilder build = MyTeamVo.builder();


        List<AccountVo> firstLevelAccount = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentId(accountVo.getId()).build());


        List<AccountVo> secondLevelAccount = ListUtil.list(false);

        if (ObjectUtil.isNotEmpty(firstLevelAccount)) {

            secondLevelAccount = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentIdList((List) firstLevelAccount.stream().map(BaseVo::getId).collect(Collectors.toList())).build());

        }


        List<AccountVo> thirdLevelAccount = ListUtil.list(false);

        if (ObjectUtil.isNotEmpty(secondLevelAccount)) {

            thirdLevelAccount = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentIdList((List) secondLevelAccount.stream().map(BaseVo::getId).collect(Collectors.toList())).build());

        }

        return build
                .level1Count(Integer.valueOf(ObjectUtil.isEmpty(firstLevelAccount) ? 0 : firstLevelAccount.size()))
                .level2Count(Integer.valueOf(ObjectUtil.isEmpty(secondLevelAccount) ? 0 : secondLevelAccount.size()))
                .level3Count(Integer.valueOf(ObjectUtil.isEmpty(thirdLevelAccount) ? 0 : thirdLevelAccount.size()))
                .totalCount(Integer.valueOf(NumberUtil.add(new Number[]{Integer.valueOf(ObjectUtil.isEmpty(firstLevelAccount) ? 0 : firstLevelAccount.size()),
                        Integer.valueOf(ObjectUtil.isEmpty(secondLevelAccount) ? 0 : secondLevelAccount.size()),
                        Integer.valueOf(ObjectUtil.isEmpty(thirdLevelAccount) ? 0 : thirdLevelAccount.size())
                }).intValue()))
                .build();

    }


    public String getApi() {

        return "api_queryteam";

    }

}

