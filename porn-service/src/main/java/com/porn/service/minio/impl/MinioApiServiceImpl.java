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
    private static final Logger log = LoggerFactory.getLogger(MinioApiServiceImpl.class);
    private final Lock lock = new ReentrantLock();
    @Autowired

    @Qualifier("minioClient")
    private MinioClient minioClient;
    @Autowired

    @Qualifier("minioClient1")
    private MinioClient minioClient1;
    @Autowired
    private MinioConfig minioConfig;

    public boolean createBucket(String bucketName) throws Exception {

        this.minioClient.makeBucket(
                (MakeBucketArgs) ((MakeBucketArgs.Builder) MakeBucketArgs.builder()
                        .bucket(StrUtil.emptyToDefault(bucketName, "attachs")))
                        .build());


        return Boolean.TRUE.booleanValue();

    }


    public boolean existsBucket(String bucketName) throws Exception {

        return this.minioClient.bucketExists(
                (BucketExistsArgs) ((BucketExistsArgs.Builder) BucketExistsArgs.builder()
                        .bucket(StrUtil.emptyToDefault(bucketName, "attachs")))
                        .build());

    }

    public List<MinioUploadVo> batchUpload(List<MinioUploadDTO> uploadList) {

        List<MinioUploadVo> result = new ArrayList<>();

        for (MinioUploadDTO uploadDTO : uploadList) {

            result.add(upload(uploadDTO));

        }

        return result;

    }

    public MinioUploadVo upload(MinioUploadDTO upload) {

        try {

            try {

                this.lock.lock();

                if (!existsBucket(StrUtil.emptyToDefault(this.minioConfig.getBucketName(), "attachs"))) {

                    createBucket(StrUtil.emptyToDefault(this.minioConfig.getBucketName(), "attachs"));

                }

                String suffix = FileUtil.getSuffix(upload.getFileName());

                String pathName = StrUtil.join("/", new Object[]{DateUtil.format(new Date(), "/yyyy/MM/dd/HH/mm"), ObjectUtil.isEmpty(suffix) ? IdUtil.simpleUUID() : StrUtil.join(".", new Object[]{IdUtil.simpleUUID(), suffix})});


                PutObjectArgs args = (PutObjectArgs) ((PutObjectArgs.Builder) ((PutObjectArgs.Builder) ((PutObjectArgs.Builder) PutObjectArgs.builder().bucket(StrUtil.emptyToDefault(this.minioConfig.getBucketName(), "attachs"))).object(pathName)).stream(new ByteArrayInputStream(upload.getFileBytes()), upload.getFileSize().longValue(), -1L).userMetadata(MapUtil.of("filename", upload.getFileName()))).contentType(upload.getContentType()).build();

                this.minioClient.putObject(args);

                PrevFileVo prevFileVo = prevFile(PrevFileDTO.builder().filePath(pathName).build());

                return MinioUploadVo.builder()
                        .fileName(upload.getFileName())
                        .filePath(pathName)
                        .fileSize(upload.getFileSize())
                        .fileUrl(prevFileVo.getFileUrl())
                        .build();

            } finally {

                this.lock.unlock();

            }

        } catch (Exception e) {

            log.error(e.getMessage(), e);

            throw new BusinessException("文件上传失败.");

        }

    }

    public MinioDownloadVo download(MinioDownloadDTO minioDownloadDTO) {

        try {

            GetObjectArgs getObjectArgs = (GetObjectArgs) ((GetObjectArgs.Builder) ((GetObjectArgs.Builder) GetObjectArgs.builder().bucket(StrUtil.emptyToDefault(this.minioConfig.getBucketName(), "attachs"))).object(minioDownloadDTO.getFilePath())).build();

            GetObjectResponse getObjectResponse = this.minioClient.getObject(getObjectArgs);

            return MinioDownloadVo.builder()
                    .fileName(StrUtil.emptyToDefault(getObjectResponse.headers().get("x-amz-meta-filename"), ""))
                    .fileSize(Long.valueOf(getObjectResponse.available()))
                    .fileBytes(IoUtil.readBytes((InputStream) getObjectResponse))
                    .contentType(getObjectResponse.headers().get("Content-Type"))
                    .build();

        } catch (Exception e) {

            log.error(e.getMessage(), e);

            throw new BusinessException("下载文件失败.");

        }

    }


    public PrevFileVo prevFile(PrevFileDTO prevFileDTO) {

        try {

            if (ObjectUtil.isEmpty(this.minioConfig.getCdnUrl())) {


                GetPresignedObjectUrlArgs args = (GetPresignedObjectUrlArgs) ((GetPresignedObjectUrlArgs.Builder) ((GetPresignedObjectUrlArgs.Builder) GetPresignedObjectUrlArgs.builder().bucket(StrUtil.emptyToDefault(this.minioConfig.getBucketName(), "attachs"))).object(prevFileDTO.getFilePath())).expiry(60, TimeUnit.MINUTES).method(Method.GET).build();

                return PrevFileVo.builder()
                        .fileUrl(this.minioClient.getPresignedObjectUrl(args))
                        .build();

            }

            String cdnUrlPrefix = this.minioConfig.getCdnUrl();

            if (cdnUrlPrefix.endsWith("/")) {

                cdnUrlPrefix = cdnUrlPrefix.substring(0, cdnUrlPrefix.length() - 1);

            }

            String filePath = prevFileDTO.getFilePath();

            if (ObjectUtil.isNotEmpty(filePath) &&
                    !filePath.startsWith("/")) {

                filePath = StrUtil.join("", new Object[]{"/", filePath});

            }

            return PrevFileVo.builder()
                    .fileUrl(StrUtil.join("", new Object[]{cdnUrlPrefix, filePath
                    })).build();

        } catch (Exception e) {

            log.error(e.getMessage(), e);

            return PrevFileVo.builder()
                    .build();

        }

    }

    public PrevFileVo prevFilePhoto(PrevFileDTO prevFileDTO) {

        try {

            GetPresignedObjectUrlArgs args = (GetPresignedObjectUrlArgs) ((GetPresignedObjectUrlArgs.Builder) ((GetPresignedObjectUrlArgs.Builder) GetPresignedObjectUrlArgs.builder().bucket(StrUtil.emptyToDefault(this.minioConfig.getBucketName1(), "attachs"))).object(prevFileDTO.getFilePath())).expiry(60, TimeUnit.MINUTES).method(Method.GET).build();

            return PrevFileVo.builder()
                    .fileUrl(this.minioClient1.getPresignedObjectUrl(args))
                    .build();

        } catch (Exception e) {

            log.error(e.getMessage(), e);

            return PrevFileVo.builder()
                    .build();

        }

    }

}
