
package com.porn.client.redis.vo;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.entity.Pair;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;
import java.util.Properties;






@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class RedisInfoVo implements Serializable {

    @ApiModelProperty("redis属性")
     private Properties props;

    @ApiModelProperty("key数量")
     private Long dbSize;

    @ApiModelProperty("命令的使用次数统计")
     private List<Pair<String, String>> commandStats;




}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/redis/vo/RedisInfoVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */