
package com.porn.client.photo.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;





@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class UploadPhotoVo
         implements Serializable
         {
    
    @ApiModelProperty("上传状态, 上传状态, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
     private Integer uploadStatus;

    

    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/photo/vo/UploadPhotoVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */