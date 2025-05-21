package com.porn.client.minio.api;

import com.porn.client.minio.dto.MinioDownloadDTO;
import com.porn.client.minio.dto.MinioUploadDTO;
import com.porn.client.minio.dto.PrevFileDTO;
import com.porn.client.minio.vo.MinioDownloadVo;
import com.porn.client.minio.vo.MinioUploadVo;
import com.porn.client.minio.vo.PrevFileVo;

import java.util.List;

public interface MinioApiService {
    boolean createBucket(String paramString) throws Exception;

    boolean existsBucket(String paramString) throws Exception;

    List<MinioUploadVo> batchUpload(List<MinioUploadDTO> paramList);

    MinioUploadVo upload(MinioUploadDTO paramMinioUploadDTO);

    MinioDownloadVo download(MinioDownloadDTO paramMinioDownloadDTO);

    PrevFileVo prevFile(PrevFileDTO paramPrevFileDTO);

    PrevFileVo prevFilePhoto(PrevFileDTO paramPrevFileDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/minio/api/MinioApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */