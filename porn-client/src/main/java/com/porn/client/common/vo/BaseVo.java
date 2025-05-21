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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/common/vo/BaseVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */