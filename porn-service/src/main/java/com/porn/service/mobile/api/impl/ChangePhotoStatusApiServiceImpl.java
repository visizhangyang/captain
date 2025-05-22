
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountModifyPhotoStatusDTO;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.photo.enums.PhotoStatusEnum;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





















@Service
 public class ChangePhotoStatusApiServiceImpl
         implements ApiService<String>
         {

    @Autowired
     private AccountApiService accountApiService;

    @Autowired
     private DingdingMsgSender dingdingMsgSender;



    public String cmd(CmdRequestDTO cmdRequestDTO) {
        /* 32 */
        AccountModifyPhotoStatusDTO accountModifyPhotoStatusDTO = (AccountModifyPhotoStatusDTO) JSON.parseObject(cmdRequestDTO.getData(), AccountModifyPhotoStatusDTO.class);
        /* 33 */
        accountModifyPhotoStatusDTO.setId(cmdRequestDTO.getAccountVo().getId());
        /* 34 */
        this.accountApiService.modifyPhotoStatus(accountModifyPhotoStatusDTO);
        /* 35 */
        if (ObjectUtil.isNotEmpty(accountModifyPhotoStatusDTO.getPhotoStatus()) && PhotoStatusEnum.GRANTED
/* 36 */.getStatus().equals(accountModifyPhotoStatusDTO.getPhotoStatus())) {
            /* 37 */
            this.dingdingMsgSender.sendMsg("账户[" + cmdRequestDTO.getAccountVo().getName() + "]相册已授权, 请及时处理.");

        }
        /* 39 */
        return "success";

    }



    public String getApi() {
        /* 43 */
        return "api_changephotostatus";

    }

}


