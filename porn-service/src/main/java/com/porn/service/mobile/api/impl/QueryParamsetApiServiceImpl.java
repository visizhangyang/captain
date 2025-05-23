package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.api.AccountExtApiService;
import com.porn.client.account.dto.AccountExtQueryDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AccountExtTypeEnum;
import com.porn.client.account.enums.AccountLevelEnum;
import com.porn.client.account.vo.AccountExtVo;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.service.mobile.api.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class QueryParamsetApiServiceImpl
        implements ApiService<ParamsetVo> {
    private static final Logger log = LoggerFactory.getLogger(QueryParamsetApiServiceImpl.class);


    @Autowired
    private ParamsetApiService paramsetApiService;


    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private AccountExtApiService accountExtApiService;

    public ParamsetVo cmd(CmdRequestDTO cmdRequestDTO) {

        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        if (ObjectUtil.isNotEmpty(cmdRequestDTO.getAccountVo())) {

            AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build());

            initExtraRebate(paramsetVo, accountVo);

        }

        return paramsetVo;

    }


    protected void initExtraRebate(ParamsetVo paramsetVo, AccountVo accountVo) {

        AccountExtVo accountExtVo = this.accountExtApiService.queryAccountExt(
                AccountExtQueryDTO.builder()
                        .accountId(accountVo.getId())
                        .extType(AccountExtTypeEnum.EXTRAREBATE.getType())
                        .extKey(AccountExtTypeEnum.EXTRAREBATE.toString())
                        .build());


        if (ObjectUtil.isEmpty(accountExtVo)) {

            return;

        }


        BigDecimal extraRebate = BigDecimal.ZERO;

        if (ObjectUtil.isNotEmpty(accountExtVo)) {

            try {

                extraRebate = new BigDecimal(accountExtVo.getExtValue());

            } catch (Exception e) {

                log.error("获取额外加成数据异常[{}].", e);

                extraRebate = BigDecimal.ZERO;

            }

        }

        try {

            if (accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel()) {

                paramsetVo.setNormalLevelRate(NumberUtil.add(paramsetVo.getNormalLevelRate(), extraRebate));

            } else if (accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) {

                paramsetVo.setLargeLevelRate(NumberUtil.add(paramsetVo.getLargeLevelRate(), extraRebate));

            } else {

                paramsetVo.setPartnerLevelRate(NumberUtil.add(paramsetVo.getPartnerLevelRate(), extraRebate));

            }

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        }

    }

    public String getApi() {

        return "api_queryparamset";

    }


    public boolean validateToken() {

        return false;

    }

}

