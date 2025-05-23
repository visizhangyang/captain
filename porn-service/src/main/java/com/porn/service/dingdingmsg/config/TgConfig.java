package com.porn.service.dingdingmsg.config;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TgConfig implements Serializable {

    @ApiModelProperty("机器人的用户名")
    private String botUsername;

    @ApiModelProperty("token")
    private String botToken;

    @ApiModelProperty("会话id")
    private Long chatId;


}

