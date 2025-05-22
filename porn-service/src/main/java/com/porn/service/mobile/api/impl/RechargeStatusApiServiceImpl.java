
package com.porn.service.mobile.api.impl;



import com.alibaba.fastjson.JSON;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.RechargeStatusApiRequestDTO;
import com.porn.client.recharge.api.RechargeApiService;
import com.porn.client.recharge.dto.RechargeQueryDTO;
import com.porn.client.recharge.vo.RechargeVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



















@Service
 public class RechargeStatusApiServiceImpl
         implements ApiService<RechargeVo>
         {

    @Autowired
     private RechargeApiService rechargeApiService;



    public RechargeVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 28 */
        RechargeStatusApiRequestDTO rechargeStatusApiRequestDTO = (RechargeStatusApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), RechargeStatusApiRequestDTO.class);




        /* 33 */
        RechargeQueryDTO rechargeQueryDTO = ((RechargeQueryDTO.RechargeQueryDTOBuilder) RechargeQueryDTO.builder().id(rechargeStatusApiRequestDTO.getRechargeId())).accountId(cmdRequestDTO.getAccountVo().getId()).build();
        /* 34 */
        return this.rechargeApiService.queryRecharge(rechargeQueryDTO);

    }



    public String getApi() {
        /* 38 */
        return "api_rechargestatus";

    }

}


