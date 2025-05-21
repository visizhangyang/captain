
package com.porn.service.notice.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;



import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("porn_notice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class NoticeDO extends BaseDO {
    
    @TableField("top_flag")
     private Integer topFlag;
    
    @TableField("lang_type")
     private Integer langType;
    
    @TableField("title")
     private String title;
    
    @TableField("_content")
     private String content;


    
}
