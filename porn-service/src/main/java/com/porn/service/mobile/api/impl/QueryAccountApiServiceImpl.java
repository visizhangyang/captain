
package com.porn.service.mobile.api.impl;



import com.alibaba.fastjson2.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;



















@Service
 public class QueryAccountApiServiceImpl
         implements ApiService<List<AccountVo>>
         {

    @Autowired
     private RedisTemplate redisTemplate;

    @Autowired
     private AccountApiService accountApiService;



    public List<AccountVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 30 */
        AccountQueryDTO accountQueryDTO = (AccountQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), AccountQueryDTO.class);
        /* 31 */
        return this.accountApiService.queryAccountList(accountQueryDTO);

    }




    public String getApi() {
        /* 36 */
        return "api_queryaccount";

    }




    public boolean validateToken() {
        /* 41 */
        return Boolean.FALSE.booleanValue();

    }

}


