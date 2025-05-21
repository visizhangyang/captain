
package com.porn.service.plan.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("porn_plan")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class PlanDO extends BaseDO {
    @TableField("title")
     private String title;
    @TableField("min_range")
     private BigDecimal minRange;
    @TableField("max_range")
     private BigDecimal maxRange;
    @TableField("free")
     private BigDecimal free;
    @TableField("_desc")
     private String desc;
    @TableField("revenue")
     private BigDecimal revenue;
    @TableField("_days")
     private Integer days;

    @TableField("lang_type")
     private Integer langType;

    @TableField("sort_no")
     private Integer sortNo;

    @TableField("extra_bonus")
     private BigDecimal extraBonus;


}
