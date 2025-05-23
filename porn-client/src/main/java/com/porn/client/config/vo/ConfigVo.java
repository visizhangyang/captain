package com.porn.client.config.vo;

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

