package com.porn.client.nickname.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.nickname.dto.*;
import com.porn.client.nickname.vo.NickNameVo;

import java.util.List;

public interface NickNameApiService {
    NickNameVo queryNickName(NickNameQueryDTO paramNickNameQueryDTO);

    List<NickNameVo> queryNickNameList(NickNameQueryDTO paramNickNameQueryDTO);

    PageVo<NickNameVo> queryPage(NickNameQueryPageDTO paramNickNameQueryPageDTO);

    Boolean batchSaveNickName(List<NickNameSaveOrUpdateDTO> paramList);

    Boolean batchDelete(NickNameBatchDeleteDTO paramNickNameBatchDeleteDTO);

    Boolean updateStatus(NickNameUpdateStatusDTO paramNickNameUpdateStatusDTO);

    Boolean upload(NickNameUploadDTO paramNickNameUploadDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/nickname/api/NickNameApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */