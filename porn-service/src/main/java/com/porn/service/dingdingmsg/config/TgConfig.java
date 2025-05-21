
package com.porn.service.dingdingmsg.config;



import io.swagger.annotations.ApiModelProperty;
import lombok.*;import lombok.experimental.*;

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

