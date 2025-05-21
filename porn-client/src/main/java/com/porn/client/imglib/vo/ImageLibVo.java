
package com.porn.client.imglib.vo;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/imglib/vo/ImageLibVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */