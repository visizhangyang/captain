
package com.porn.client.photo.vo;
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/photo/vo/PhotoVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */