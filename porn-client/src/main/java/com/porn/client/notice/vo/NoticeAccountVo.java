
package com.porn.client.notice.vo;
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
 public class NoticeAccountVo
         extends BaseVo
         {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("公告ID")
     private Long noticeId;




}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/notice/vo/NoticeAccountVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */