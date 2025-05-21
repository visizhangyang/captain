
package com.porn.client.message.vo;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class MessageVo
         extends BaseVo {

    @ApiModelProperty("消息体")
     private String msg;

    @ApiModelProperty("账户id")
     private Long accountId;

    @ApiModelProperty("账户名称")
     private String accountName;



}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/message/vo/MessageVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */