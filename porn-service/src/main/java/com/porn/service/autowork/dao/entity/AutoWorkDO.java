
package com.porn.service.autowork.dao.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.*;import lombok.experimental.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@TableName("porn_autowork")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
 public class AutoWorkDO extends BaseDO {

    @TableField("account_id")
     private Long accountId;

    @TableField("min_workcount")
     private Integer minWorkCount;

    @TableField("max_workcount")
     private Integer maxWorkCount;

    @TableField("min_workamount")
     private BigDecimal minWorkAmount;

    @TableField("max_workamount")
     private BigDecimal maxWorkAmount;

    @TableField("min_workspace")
     private Integer minWorkSpace;

    @TableField("max_workspace")
    private Integer maxWorkSpace;
    @TableField("min_worktime")
    private LocalDateTime minWorkTime;
    @TableField("max_worktime")
    private LocalDateTime maxWorkTime;
    @TableField("min_loantime")
    private Integer minLoanTime;
    @TableField("max_loantime")
    private Integer maxLoanTime;
    @TableField("min_completetime")
    private Integer minCompleteTime;
    @TableField("max_completetime")
    private Integer maxCompleteTime;


}

