package com.porn.service.recharge.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ConfigurationProperties(prefix = "proxy")
public class ProxyConfig {
    @ApiModelProperty("代理地址")
    private String host;
    @ApiModelProperty("代理端口")
    private Integer port;
}