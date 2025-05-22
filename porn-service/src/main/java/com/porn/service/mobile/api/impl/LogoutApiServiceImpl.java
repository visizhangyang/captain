
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.StrUtil;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
















@Service
 public class LogoutApiServiceImpl
         implements ApiService<String>
         {

    @Autowired
     private RedisTemplate redisTemplate;



    public String cmd(CmdRequestDTO cmdRequestDTO) {
        /* 25 */
        String token = cmdRequestDTO.getToken();
        /* 26 */
        this.redisTemplate.delete(String.format("account:session:%s", new Object[]{StrUtil.emptyToDefault(token, "")}));
        /* 27 */
        return "success";

    }



    public String getApi() {
        /* 31 */
        return "api_logout";

    }

}


