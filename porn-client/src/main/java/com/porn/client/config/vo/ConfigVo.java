
package com.porn.client.config.vo;
import com.porn.client.common.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ConfigVo extends BaseVo {
    @ApiModelProperty("配置编码")
     private String configCode;
    @ApiModelProperty("配置组")
     private String configGroup;

    @ApiModelProperty("配置值")
     private String configValue;

    @ApiModelProperty("配置描述")
     private String configDesc;

    @ApiModelProperty("是否启用, EnableStatusEnum")
     private Integer status;

    @ApiModelProperty("排序值")
     private Integer sortNo;

    @ApiModelProperty("账户ID")
     private Long accountId;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/config/vo/ConfigVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */