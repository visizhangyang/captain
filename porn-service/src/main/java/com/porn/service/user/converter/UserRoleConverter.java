package com.porn.service.user.converter;

import com.porn.client.user.dto.UserRoleSaveOrUpdateDTO;
import com.porn.client.user.vo.UserRoleVo;
import com.porn.service.user.dao.entity.UserRoleDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleConverter {
    UserRoleVo toUserRoleVo(UserRoleDO paramUserRoleDO);

    List<UserRoleVo> toUserRoleVoList(List<UserRoleDO> paramList);

    UserRoleDO toUserRoleDO(UserRoleSaveOrUpdateDTO paramUserRoleSaveOrUpdateDTO);
}

