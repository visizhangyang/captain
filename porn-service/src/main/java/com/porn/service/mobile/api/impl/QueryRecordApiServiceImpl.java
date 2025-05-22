
package com.porn.service.mobile.api.impl;



import com.alibaba.fastjson.JSON;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.vo.StreamVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;





















@Service
 public class QueryRecordApiServiceImpl
         implements ApiService<List<StreamVo>>
         {

    @Autowired
     private StreamApiService streamApiService;



    public List<StreamVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 30 */
        StreamQueryDTO streamQueryDTO = (StreamQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), StreamQueryDTO.class);
        /* 31 */
        streamQueryDTO.setAccountId(cmdRequestDTO.getAccountVo().getId());
        /* 32 */
        return this.streamApiService.queryStreamList(streamQueryDTO);

    }



    public String getApi() {
        /* 36 */
        return "api_queryrecord";

    }

}


