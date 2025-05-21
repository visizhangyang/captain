
package com.porn.client.mobile.vo;
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
 public class MyTeamVo implements Serializable {

    @ApiModelProperty("总数量")
     private Integer totalCount;

    @ApiModelProperty("一级数量")
     private Integer level1Count;

    @ApiModelProperty("二级数量")
     private Integer level2Count;

    @ApiModelProperty("三级数量")
     private Integer level3Count;



}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/vo/MyTeamVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */