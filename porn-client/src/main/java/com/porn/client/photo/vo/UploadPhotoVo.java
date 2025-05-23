package com.porn.client.photo.vo;

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
public class UploadPhotoVo
        implements Serializable {

    @ApiModelProperty("上传状态, 上传状态, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer uploadStatus;

}

