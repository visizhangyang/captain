package com.porn.client.redis.vo;

import com.porn.client.common.entity.Pair;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

