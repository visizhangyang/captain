package com.porn.service.role.converter;

import com.porn.client.role.vo.RoleVo;
import com.porn.service.role.dao.entity.RoleDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleConverter {
    RoleVo toRoleVo(RoleDO paramRoleDO);

    List<RoleVo> toRoleVoList(List<RoleDO> paramList);
}


