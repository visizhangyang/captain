package com.porn.client.minio.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MinioDownloadVo implements Serializable {
    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件大小")
    private Long fileSize;

    @ApiModelProperty("内容类型")
    private String contentType;

    @ApiModelProperty("文件内容")
    private byte[] fileBytes;

}