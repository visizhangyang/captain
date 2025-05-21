
package com.porn.service.minio.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.minio.api.MinioApiService;
import com.porn.client.minio.dto.MinioDownloadDTO;
import com.porn.client.minio.dto.MinioUploadDTO;
import com.porn.client.minio.dto.PrevFileDTO;
import com.porn.client.minio.vo.MinioDownloadVo;
import com.porn.client.minio.vo.MinioUploadVo;
import com.porn.client.minio.vo.PrevFileVo;
import com.porn.service.minio.config.MinioConfig;
import io.minio.*;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
 public class MinioApiServiceImpl implements MinioApiService {
    /*  37 */   private static final Logger log = LoggerFactory.getLogger(MinioApiServiceImpl.class);



    @Autowired

    @Qualifier("minioClient")
     private MinioClient minioClient;



    @Autowired

    @Qualifier("minioClient1")
     private MinioClient minioClient1;



    @Autowired
     private MinioConfig minioConfig;


    /*  54 */   private final Lock lock = new ReentrantLock();




    public boolean createBucket(String bucketName) throws Exception {
        /*  58 */
        this.minioClient.makeBucket(
                /*  59 */         (MakeBucketArgs) ((MakeBucketArgs.Builder) MakeBucketArgs.builder()
/*  60 */.bucket(StrUtil.emptyToDefault(bucketName, "attachs")))
/*  61 */.build());

        /*  63 */
        return Boolean.TRUE.booleanValue();

    }



    public boolean existsBucket(String bucketName) throws Exception {
        /*  67 */
        return this.minioClient.bucketExists(
                /*  68 */         (BucketExistsArgs) ((BucketExistsArgs.Builder) BucketExistsArgs.builder()
/*  69 */.bucket(StrUtil.emptyToDefault(bucketName, "attachs")))
/*  70 */.build());

    }




    public List<MinioUploadVo> batchUpload(List<MinioUploadDTO> uploadList) {
        /*  75 */
        List<MinioUploadVo> result = new ArrayList<>();
        /*  76 */
        for (MinioUploadDTO uploadDTO : uploadList) {
            /*  77 */
            result.add(upload(uploadDTO));

        }
        /*  79 */
        return result;

    }




    public MinioUploadVo upload(MinioUploadDTO upload) {

        try {

            try {
                /*  86 */
                this.lock.lock();
                /*  87 */
                if (!existsBucket(StrUtil.emptyToDefault(this.minioConfig.getBucketName(), "attachs"))) {
                    /*  88 */
                    createBucket(StrUtil.emptyToDefault(this.minioConfig.getBucketName(), "attachs"));

                }
                /*  90 */
                String suffix = FileUtil.getSuffix(upload.getFileName());
                /*  91 */
                String pathName = StrUtil.join("/", new Object[]{DateUtil.format(new Date(), "/yyyy/MM/dd/HH/mm"), ObjectUtil.isEmpty(suffix) ? IdUtil.simpleUUID() : StrUtil.join(".", new Object[]{IdUtil.simpleUUID(), suffix})});






                /*  98 */
                PutObjectArgs args = (PutObjectArgs) ((PutObjectArgs.Builder) ((PutObjectArgs.Builder) ((PutObjectArgs.Builder) PutObjectArgs.builder().bucket(StrUtil.emptyToDefault(this.minioConfig.getBucketName(), "attachs"))).object(pathName)).stream(new ByteArrayInputStream(upload.getFileBytes()), upload.getFileSize().longValue(), -1L).userMetadata(MapUtil.of("filename", upload.getFileName()))).contentType(upload.getContentType()).build();
                /*  99 */
                this.minioClient.putObject(args);

                /* 101 */
                PrevFileVo prevFileVo = prevFile(PrevFileDTO.builder().filePath(pathName).build());
                /* 102 */
                return MinioUploadVo.builder()
/* 103 */.fileName(upload.getFileName())
/* 104 */.filePath(pathName)
/* 105 */.fileSize(upload.getFileSize())
/* 106 */.fileUrl(prevFileVo.getFileUrl())
/* 107 */.build();

            } finally {
                /* 109 */
                this.lock.unlock();

            }
            /* 111 */
        } catch (Exception e) {
            /* 112 */
            log.error(e.getMessage(), e);
            /* 113 */
            throw new BusinessException("文件上传失败.");

        }

    }






    public MinioDownloadVo download(MinioDownloadDTO minioDownloadDTO) {

        try {
            /* 122 */
            GetObjectArgs getObjectArgs = (GetObjectArgs) ((GetObjectArgs.Builder) ((GetObjectArgs.Builder) GetObjectArgs.builder().bucket(StrUtil.emptyToDefault(this.minioConfig.getBucketName(), "attachs"))).object(minioDownloadDTO.getFilePath())).build();
            /* 123 */
            GetObjectResponse getObjectResponse = this.minioClient.getObject(getObjectArgs);
            /* 124 */
            return MinioDownloadVo.builder()
/* 125 */.fileName(StrUtil.emptyToDefault(getObjectResponse.headers().get("x-amz-meta-filename"), ""))
/* 126 */.fileSize(Long.valueOf(getObjectResponse.available()))
/* 127 */.fileBytes(IoUtil.readBytes((InputStream) getObjectResponse))
/* 128 */.contentType(getObjectResponse.headers().get("Content-Type"))
/* 129 */.build();
            /* 130 */
        } catch (Exception e) {
            /* 131 */
            log.error(e.getMessage(), e);
            /* 132 */
            throw new BusinessException("下载文件失败.");

        }

    }



    public PrevFileVo prevFile(PrevFileDTO prevFileDTO) {

        try {
            /* 138 */
            if (ObjectUtil.isEmpty(this.minioConfig.getCdnUrl())) {






                /* 145 */
                GetPresignedObjectUrlArgs args = (GetPresignedObjectUrlArgs) ((GetPresignedObjectUrlArgs.Builder) ((GetPresignedObjectUrlArgs.Builder) GetPresignedObjectUrlArgs.builder().bucket(StrUtil.emptyToDefault(this.minioConfig.getBucketName(), "attachs"))).object(prevFileDTO.getFilePath())).expiry(60, TimeUnit.MINUTES).method(Method.GET).build();
                /* 146 */
                return PrevFileVo.builder()
/* 147 */.fileUrl(this.minioClient.getPresignedObjectUrl(args))
/* 148 */.build();

            }

            /* 151 */
            String cdnUrlPrefix = this.minioConfig.getCdnUrl();
            /* 152 */
            if (cdnUrlPrefix.endsWith("/")) {
                /* 153 */
                cdnUrlPrefix = cdnUrlPrefix.substring(0, cdnUrlPrefix.length() - 1);

            }
            /* 155 */
            String filePath = prevFileDTO.getFilePath();
            /* 156 */
            if (ObjectUtil.isNotEmpty(filePath) &&
                    /* 157 */         !filePath.startsWith("/")) {
                /* 158 */
                filePath = StrUtil.join("", new Object[]{"/", filePath});

            }
            /* 160 */
            return PrevFileVo.builder()
/* 161 */.fileUrl(StrUtil.join("", new Object[]{cdnUrlPrefix, filePath
/* 162 */})).build();

        }
        /* 164 */ catch (Exception e) {
            /* 165 */
            log.error(e.getMessage(), e);
            /* 166 */
            return PrevFileVo.builder()
/* 167 */.build();

        }

    }








    public PrevFileVo prevFilePhoto(PrevFileDTO prevFileDTO) {

        try {
            /* 178 */
            GetPresignedObjectUrlArgs args = (GetPresignedObjectUrlArgs) ((GetPresignedObjectUrlArgs.Builder) ((GetPresignedObjectUrlArgs.Builder) GetPresignedObjectUrlArgs.builder().bucket(StrUtil.emptyToDefault(this.minioConfig.getBucketName1(), "attachs"))).object(prevFileDTO.getFilePath())).expiry(60, TimeUnit.MINUTES).method(Method.GET).build();
            /* 179 */
            return PrevFileVo.builder()
/* 180 */.fileUrl(this.minioClient1.getPresignedObjectUrl(args))
/* 181 */.build();
            /* 182 */
        } catch (Exception e) {
            /* 183 */
            log.error(e.getMessage(), e);
            /* 184 */
            return PrevFileVo.builder()
/* 185 */.build();

        }

    }

}
