
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.ObjectUtil;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.notice.api.NoticeAccountApiService;
import com.porn.client.notice.api.NoticeApiService;
import com.porn.client.notice.dto.NoticeAccountBatchSaveDTO;
import com.porn.client.notice.dto.NoticeQueryReadStatusDTO;
import com.porn.client.notice.vo.NoticeVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


























@Service
 public class ReadAllNoticeApiServiceImpl
         implements ApiService<String>
         {

    @Autowired
     private NoticeApiService noticeApiService;

    @Autowired
     private NoticeAccountApiService noticeAccountApiService;



    public String cmd(CmdRequestDTO cmdRequestDTO) {
        /* 37 */
        NoticeQueryReadStatusDTO noticeQueryReadStatusDTO = NoticeQueryReadStatusDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).readType(CommonConst.IZERO).build();
        /* 38 */
        List<NoticeVo> noticeVoList = this.noticeApiService.queryNoticeReadStatusList(noticeQueryReadStatusDTO);
        /* 39 */
        if (ObjectUtil.isNotEmpty(noticeVoList)) {
            /* 40 */
            List<Long> noticeIdList = (List<Long>) noticeVoList.stream().map(BaseVo::getId).distinct().collect(Collectors.toList());



            /* 44 */
            NoticeAccountBatchSaveDTO noticeAccountBatchSaveDTO = NoticeAccountBatchSaveDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).noticeIdList(noticeIdList).build();
            /* 45 */
            this.noticeAccountApiService.batchSave(noticeAccountBatchSaveDTO);

        }
        /* 47 */
        return "success";

    }




    public String getApi() {
        /* 52 */
        return "api_readallnotice";

    }

}


