
package com.porn.service.imglib.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.*;import lombok.experimental.*;


@TableName("porn_imagelib")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
 public class ImageLibDO extends BaseDO {
    
    @TableField("img_path")
     private String imgPath;
    
    @TableField("image_type")
     private Integer imageType;
    
    @TableField("_status")
     private Integer status;


    
}

