
package com.porn.client.recommendapp.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RecommendAppVo extends BaseVo {
    @ApiModelProperty("app的logo")
     private String avatar;
    @ApiModelProperty("app的logo")
     private String avatarUrl;
    @ApiModelProperty("app英文名字")
     private String code;
    @ApiModelProperty("app中文名字")
     private String name;
    @ApiModelProperty("应用类型, AppTypeEnum")
     private String appType;
    @ApiModelProperty("应用地址")
     private String apkUrl;

    @ApiModelProperty("排序值")
     private Integer sortNo;

    @ApiModelProperty("复制标识, 0-跳转, 1-复制")
     private Integer copyFlag;

    @ApiModelProperty("类型，RecommendTypeEnum")
     private Integer recommendType;

    @ApiModelProperty("账户级别列表")
     private List<Integer> accountLevelList;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/recommendapp/vo/RecommendAppVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */