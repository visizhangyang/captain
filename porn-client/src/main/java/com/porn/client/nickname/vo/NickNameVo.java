
package com.porn.client.nickname.vo;
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
 public class NickNameVo extends BaseVo {

    @ApiModelProperty("昵称")
     private String nickName;

    @ApiModelProperty("昵称类型, NickNameTypeEnum, 0-机器人, 1-手工导入")
     private Integer nickNameType;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
     private Integer status;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/nickname/vo/NickNameVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */