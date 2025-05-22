
package com.porn.service.mobile.api.impl;



import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.photo.vo.UploadPhotoVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



















@Service
 public class QueryPhotoStatusApiServiceImpl
         implements ApiService<UploadPhotoVo>
         {

    @Autowired
     private AccountApiService accountApiService;



    public UploadPhotoVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 28 */
        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build());
        /* 29 */
        return UploadPhotoVo.builder().uploadStatus(accountVo.getUploadStatus()).build();

    }



    public String getApi() {
        /* 33 */
        return "api_queryphotostatus";

    }

}


