
package com.porn.service.test.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("porn_test")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class TestDO
         extends BaseDO
         {

    @TableField("name")
     private String name;

}

