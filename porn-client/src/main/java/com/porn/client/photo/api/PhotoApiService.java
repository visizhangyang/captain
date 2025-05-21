package com.porn.client.photo.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.photo.dto.PhotoQueryDTO;
import com.porn.client.photo.dto.PhotoQueryPageDTO;
import com.porn.client.photo.dto.PhotoSaveOrUpdateDTO;
import com.porn.client.photo.vo.PhotoVo;

import java.util.List;

public interface PhotoApiService {
    PhotoVo queryPhoto(PhotoQueryDTO paramPhotoQueryDTO);

    List<PhotoVo> queryPhotoList(PhotoQueryDTO paramPhotoQueryDTO);

    PageVo<PhotoVo> queryPage(PhotoQueryPageDTO paramPhotoQueryPageDTO);

    PhotoVo saveOrUpdate(PhotoSaveOrUpdateDTO paramPhotoSaveOrUpdateDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/photo/api/PhotoApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */