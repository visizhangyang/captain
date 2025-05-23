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
public class PrevFileVo
        implements Serializable {

    @ApiModelProperty("文件域名的URL")
    private String fileUrl;

}

