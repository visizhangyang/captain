
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.ObjectUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.mobile.converter.MobileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




















@Service
 public class RefreshParentAccountInfoServiceImpl
         implements ApiService<AccountVo>
         {

    @Autowired
     private AccountApiService accountApiService;

    @Autowired
     private MobileConverter mobileConverter;



    public AccountVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 31 */
        AccountVo accountVo = cmdRequestDTO.getAccountVo();

        /* 33 */
        AccountVo dbAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(accountVo.getId())).build());
        /* 34 */
        if (ObjectUtil.isEmpty(dbAccountVo.getParentPromotionCode())) {
            /* 35 */
            return AccountVo.builder().build();

        }
        /* 37 */
        dbAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(dbAccountVo.getParentId())).build());

        /* 39 */
        return (dbAccountVo == null || EnableStatusEnum.DISABLED.getStatus().equals(dbAccountVo.getSubVisit())) ? AccountVo.builder().build() : dbAccountVo;

    }



    public String getApi() {
        /* 43 */
        return "api_refreshparentaccount";

    }

}


