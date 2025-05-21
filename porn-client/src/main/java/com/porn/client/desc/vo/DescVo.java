
package com.porn.client.desc.vo;
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
 public class DescVo extends BaseVo {

    @ApiModelProperty("描述类型, DescTypeEnum")
     private Integer descType;

    @ApiModelProperty("描述文本")
     private String descText;

    @ApiModelProperty("排序号")
     private Integer sortNo;



}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/desc/vo/DescVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */