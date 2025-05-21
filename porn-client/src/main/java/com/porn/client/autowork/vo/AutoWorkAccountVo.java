
package com.porn.client.autowork.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AutoWorkAccountVo extends BaseVo {
    @ApiModelProperty("开始搬砖时间")
     private LocalDateTime startWorkTime;
    @ApiModelProperty("上一次搬砖时间")
     private LocalDateTime lastWorkTime;
    @ApiModelProperty("下一次搬砖时间")
     private LocalDateTime nextWorkTime;

    @ApiModelProperty("下一次放款时间")
     private LocalDateTime nextLoanTime;

    @ApiModelProperty("完成时间")
     private LocalDateTime nextCompleteTime;

    @ApiModelProperty("搬砖总次数")
     private Integer workTotalCount;

    @ApiModelProperty("当前搬砖次数")
     private Integer workCount;

    @ApiModelProperty("缓存key")
     private String cacheKey;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/autowork/vo/AutoWorkAccountVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */