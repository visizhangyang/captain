
package com.porn.service.richtext.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;


@TableName("porn_richtext")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class RichTextDO extends BaseDO {

    @TableField("_type")
     private Integer type;

    @TableField("lang_type")
     private Integer langType;

    @TableField("rich_text")
     private String richText;



}
