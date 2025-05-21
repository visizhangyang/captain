
package com.porn.client.inline.vo;
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
 public class InlineVo extends BaseVo {

    @ApiModelProperty("最小在线人数")
     private Long minInlineCount;

    @ApiModelProperty("最大搬砖金额")
     private Long maxInlineCount;

    @ApiModelProperty("在线时间范围(小)")
     private String minInlineTime;

    @ApiModelProperty("在线时间范围(大)")
     private String maxInlineTime;

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/inline/vo/InlineVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */