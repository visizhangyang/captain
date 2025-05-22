
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
         implements ApiService<MyTeamVo>
         {

    @Autowired
     private AccountApiService accountApiService;



    public MyTeamVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 34 */
        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build());
        /* 35 */
        Integer level1Count = Integer.valueOf(0);
        /* 36 */
        Integer level2Count = Integer.valueOf(0);
        /* 37 */
        Integer level3Count = Integer.valueOf(0);
        /* 38 */
        MyTeamVo.MyTeamVoBuilder build = MyTeamVo.builder();

        /* 40 */
        List<AccountVo> firstLevelAccount = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentId(accountVo.getId()).build());

        /* 42 */
        List<AccountVo> secondLevelAccount = ListUtil.list(false);
        /* 43 */
        if (ObjectUtil.isNotEmpty(firstLevelAccount)) {
            /* 44 */
            secondLevelAccount = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentIdList((List) firstLevelAccount.stream().map(BaseVo::getId).collect(Collectors.toList())).build());

        }

        /* 47 */
        List<AccountVo> thirdLevelAccount = ListUtil.list(false);
        /* 48 */
        if (ObjectUtil.isNotEmpty(secondLevelAccount)) {
            /* 49 */
            thirdLevelAccount = this.accountApiService.queryAccountList(AccountQueryDTO.builder().parentIdList((List) secondLevelAccount.stream().map(BaseVo::getId).collect(Collectors.toList())).build());

        }
        /* 51 */
        return build
/* 52 */.level1Count(Integer.valueOf(ObjectUtil.isEmpty(firstLevelAccount) ? 0 : firstLevelAccount.size()))
/* 53 */.level2Count(Integer.valueOf(ObjectUtil.isEmpty(secondLevelAccount) ? 0 : secondLevelAccount.size()))
/* 54 */.level3Count(Integer.valueOf(ObjectUtil.isEmpty(thirdLevelAccount) ? 0 : thirdLevelAccount.size()))
/* 55 */.totalCount(Integer.valueOf(NumberUtil.add(new Number[]{Integer.valueOf(ObjectUtil.isEmpty(firstLevelAccount) ? 0 : firstLevelAccount.size()),
/* 56 */               Integer.valueOf(ObjectUtil.isEmpty(secondLevelAccount) ? 0 : secondLevelAccount.size()),
/* 57 */               Integer.valueOf(ObjectUtil.isEmpty(thirdLevelAccount) ? 0 : thirdLevelAccount.size())
/* 58 */}).intValue()))
/* 59 */.build();

    }



    public String getApi() {
        /* 63 */
        return "api_queryteam";

    }

}


