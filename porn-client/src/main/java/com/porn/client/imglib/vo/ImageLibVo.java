package com.porn.client.imglib.vo;

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
public class ImageLibVo extends BaseVo {

    @ApiModelProperty("图片路径")
    private String imgPath;

    @ApiModelProperty("图片全路径")
    private String imgPathUrl;

    @ApiModelProperty("图片类型ImageTypeEnum, 0-商户, 1-账户")
    private Integer imageType;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer status;

}

