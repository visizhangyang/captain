
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.mobile.dto.CancelRechargeApiRequestDTO;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.CreateRechargeVo;
import com.porn.client.recharge.api.RechargeApiService;
import com.porn.client.recharge.dto.RechargeCancelDTO;
import com.porn.client.recharge.dto.RechargeQueryDTO;
import com.porn.client.recharge.vo.RechargeVo;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.mobile.converter.MobileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

























@Service
 public class CancelRechargeApiServiceImpl
         implements ApiService<CreateRechargeVo>
         {

    @Autowired
     private RechargeApiService rechargeApiService;

    @Autowired
     private MobileConverter mobileConverter;



    public CreateRechargeVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 36 */
        CancelRechargeApiRequestDTO cancelRechargeApiRequestDTO = (CancelRechargeApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), CancelRechargeApiRequestDTO.class);
        /* 37 */
        if (ObjectUtil.isEmpty(cancelRechargeApiRequestDTO.getRechargeId())) {
            /* 38 */
            throw new BusinessException("充值ID不能为空.");

        }



        /* 43 */
        RechargeQueryDTO rechargeQueryDTO = ((RechargeQueryDTO.RechargeQueryDTOBuilder) RechargeQueryDTO.builder().id(cancelRechargeApiRequestDTO.getRechargeId())).accountId(cmdRequestDTO.getAccountVo().getId()).build();
        /* 44 */
        RechargeVo rechargeVo = this.rechargeApiService.queryRecharge(rechargeQueryDTO);
        /* 45 */
        if (ObjectUtil.isEmpty(rechargeVo)) {
            /* 46 */
            throw new BusinessException("充值信息不存在.");

        }


        /* 50 */
        RechargeCancelDTO rechargeCancelDTO = ((RechargeCancelDTO.RechargeCancelDTOBuilder) RechargeCancelDTO.builder().id(rechargeVo.getId())).build();
        /* 51 */
        this.rechargeApiService.cancel(rechargeCancelDTO);
        /* 52 */
        return this.mobileConverter.toCreateRechargeVo(rechargeVo);

    }



    public String getApi() {
        /* 56 */
        return "api_cancelrecharge";

    }

}


