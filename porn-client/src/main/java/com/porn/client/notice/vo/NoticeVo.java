
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
 public class NoticeVo extends BaseVo {

    @ApiModelProperty("置顶标识, TopFlagEnum")
     private Integer topFlag;

    @ApiModelProperty("语言类型, LangTypeEnum")
     private Integer langType;

    @ApiModelProperty("语言名称")
     private String langTypeName;

    @ApiModelProperty("标题")
     private String title;

    @ApiModelProperty("内容(富文本)")
     private String content;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/notice/vo/NoticeVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */