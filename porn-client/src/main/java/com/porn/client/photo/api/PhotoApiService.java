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

