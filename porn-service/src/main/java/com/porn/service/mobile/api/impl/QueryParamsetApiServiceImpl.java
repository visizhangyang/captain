
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
         implements ApiService<ParamsetVo>
         {
    /*  31 */   private static final Logger log = LoggerFactory.getLogger(QueryParamsetApiServiceImpl.class);



    @Autowired
     private ParamsetApiService paramsetApiService;



    @Autowired
     private AccountApiService accountApiService;


    @Autowired
     private AccountExtApiService accountExtApiService;




    public ParamsetVo cmd(CmdRequestDTO cmdRequestDTO) {
        /*  46 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());

        /*  48 */
        if (ObjectUtil.isNotEmpty(cmdRequestDTO.getAccountVo())) {
            /*  49 */
            AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build());
            /*  50 */
            initExtraRebate(paramsetVo, accountVo);

        }
        /*  52 */
        return paramsetVo;

    }









    protected void initExtraRebate(ParamsetVo paramsetVo, AccountVo accountVo) {
        /*  62 */
        AccountExtVo accountExtVo = this.accountExtApiService.queryAccountExt(
                /*  63 */         AccountExtQueryDTO.builder()
/*  64 */.accountId(accountVo.getId())
/*  65 */.extType(AccountExtTypeEnum.EXTRAREBATE.getType())
/*  66 */.extKey(AccountExtTypeEnum.EXTRAREBATE.toString())
/*  67 */.build());

        /*  69 */
        if (ObjectUtil.isEmpty(accountExtVo)) {

            return;

        }

        /*  73 */
        BigDecimal extraRebate = BigDecimal.ZERO;
        /*  74 */
        if (ObjectUtil.isNotEmpty(accountExtVo)) {


            try {
                /*  77 */
                extraRebate = new BigDecimal(accountExtVo.getExtValue());
                /*  78 */
            } catch (Exception e) {
                /*  79 */
                log.error("获取额外加成数据异常[{}].", e);
                /*  80 */
                extraRebate = BigDecimal.ZERO;

            }

        }

        try {
            /*  84 */
            if (accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel()) {
                /*  85 */
                paramsetVo.setNormalLevelRate(NumberUtil.add(paramsetVo.getNormalLevelRate(), extraRebate));
                /*  86 */
            } else if (accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) {
                /*  87 */
                paramsetVo.setLargeLevelRate(NumberUtil.add(paramsetVo.getLargeLevelRate(), extraRebate));

            } else {
                /*  89 */
                paramsetVo.setPartnerLevelRate(NumberUtil.add(paramsetVo.getPartnerLevelRate(), extraRebate));

            }
            /*  91 */
        } catch (Exception e) {
            /*  92 */
            log.error(e.getMessage(), e);

        }

    }




    public String getApi() {
        /*  98 */
        return "api_queryparamset";

    }



    public boolean validateToken() {
        /* 102 */
        return false;

    }

}


