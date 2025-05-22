
package com.porn.service.mobile.api.impl;



import com.alibaba.fastjson.JSON;
import com.porn.client.common.vo.PageVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryPageDTO;
import com.porn.client.stream.vo.StreamVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


















@Service
 public class QueryRecord101ApiServiceImpl
         implements ApiService<PageVo<StreamVo>>
         {

    @Autowired
     private StreamApiService streamApiService;



    public PageVo<StreamVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 27 */
        StreamQueryPageDTO streamQueryDTO = (StreamQueryPageDTO) JSON.parseObject(cmdRequestDTO.getData(), StreamQueryPageDTO.class);
        /* 28 */
        streamQueryDTO.setAccountId(cmdRequestDTO.getAccountVo().getId());
        /* 29 */
        return this.streamApiService.queryPage(streamQueryDTO);

    }



    public String getApi() {
        /* 33 */
        return "api_queryrecord";

    }



    public String getVersion() {
        /* 37 */
        return "1.0.1";

    }

}


