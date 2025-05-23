package com.porn.client.photo.vo;

import com.porn.client.common.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PhotoVo extends BaseVo {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

    @ApiModelProperty("文件路径")
    private String filePath;

    @ApiModelProperty("文件url")
    private String fileUrl;

    @ApiModelProperty("app端唯一")
    private String localIdentifier;

}

