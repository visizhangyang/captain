
package com.porn.service.common.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import com.porn.service.common.entity.BaseDO;
import lombok.*;import lombok.experimental.*;


import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class BaseDO implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
     private Long id;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
     private LocalDateTime createTime;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
     private Long createBy;

    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
     private LocalDateTime modifyTime;

    @TableField(value = "modify_by", fill = FieldFill.INSERT_UPDATE)
     private Long modifyBy;

    @TableField(value = "del_flag", fill = FieldFill.INSERT_UPDATE)
     private Integer delFlag;



}
