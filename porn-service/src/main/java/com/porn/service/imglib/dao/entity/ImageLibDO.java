package com.porn.service.imglib.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@TableName("porn_imagelib")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ImageLibDO extends BaseDO {

    @TableField("img_path")
    private String imgPath;

    @TableField("image_type")
    private Integer imageType;

    @TableField("_status")
    private Integer status;


}

