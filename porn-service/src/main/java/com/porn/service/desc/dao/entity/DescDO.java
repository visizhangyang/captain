package com.porn.service.desc.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.porn.service.common.entity.BaseDO;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@TableName("porn_desc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DescDO extends BaseDO {

    @TableField("desc_type")
    private Integer descType;

    @TableField("desc_text")
    private String descText;

    @TableField("sort_no")
    private Integer sortNo;

    @TableField("lang_type")
    private Integer langType;

}

