package com.porn.client.role.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserRoleQueryDTO
        extends BaseDTO {

    @ApiModelProperty("用户ID")
    private Long userId;

}
