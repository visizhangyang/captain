package com.porn.client.imglib.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.imglib.dto.*;
import com.porn.client.imglib.vo.ImageLibVo;

import java.util.List;

public interface ImageLibApiService {
    ImageLibVo queryImageLib(ImageLibQueryDTO paramImageLibQueryDTO);

    List<ImageLibVo> queryImageLibList(ImageLibQueryDTO paramImageLibQueryDTO);

    PageVo<ImageLibVo> queryPage(ImageLibQueryPageDTO paramImageLibQueryPageDTO);

    Boolean batchSaveImageLib(List<ImageLibSaveOrUpdateDTO> paramList);

    Boolean batchDelete(ImageLibBatchDeleteDTO paramImageLibBatchDeleteDTO);

    Boolean updateStatus(ImageLibUpdateStatusDTO paramImageLibUpdateStatusDTO);
}
