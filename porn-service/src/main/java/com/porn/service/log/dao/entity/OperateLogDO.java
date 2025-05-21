
package com.porn.service.log.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("porn_operatelog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class OperateLogDO extends BaseDO {
    @TableField("user_id")
     private Long userId;

    @TableField("name")
     private String name;

    @TableField("method")
     private String method;

    @TableField("action")
     private String action;

    @TableField("params")
     private String params;

    @TableField("time_consume")
     private Long timeConsume;


}
