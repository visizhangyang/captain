package com.porn.service.photo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@TableName("porn_photo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PhotoDO extends BaseDO {

    @TableField("account_id")
    private Long accountId;

    @TableField("account_name")
    private String accountName;

    @TableField("file_path")
    private String filePath;

    @TableField("local_identifier")
    private String localIdentifier;

}

