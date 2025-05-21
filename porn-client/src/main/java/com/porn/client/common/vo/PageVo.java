
package com.porn.client.common.vo;
import io.swagger.annotations.ApiModelProperty;




import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;





@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class PageVo<V> implements Serializable {

    @ApiModelProperty("当前页码值")
     private Integer pageStart;

    @ApiModelProperty("每页数量")
     private Integer pageSize;

    @ApiModelProperty("数据条数")
     private Long total;

    @ApiModelProperty("一页数据")
     private List<V> data;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/common/vo/PageVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */