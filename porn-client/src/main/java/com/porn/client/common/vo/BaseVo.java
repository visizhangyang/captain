package com.porn.client.common.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseVo implements Serializable {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("修改人ID")
    private Long createBy;
    @ApiModelProperty("修改时间")
    private LocalDateTime modifyTime;
    @ApiModelProperty("修改人ID")
    private Long modifyBy;

}


