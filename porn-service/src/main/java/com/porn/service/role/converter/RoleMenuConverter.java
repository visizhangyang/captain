package com.porn.service.role.converter;

import com.porn.client.role.dto.RoleMenuSaveOrUpdateDTO;
import com.porn.client.role.vo.RoleMenuVo;
import com.porn.service.role.dao.entity.RoleMenuDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMenuConverter {
    RoleMenuVo toRoleMenuVo(RoleMenuDO paramRoleMenuDO);

    List<RoleMenuVo> toRoleMenuVoList(List<RoleMenuDO> paramList);

    RoleMenuDO toRoleMenuDO(RoleMenuSaveOrUpdateDTO paramRoleMenuSaveOrUpdateDTO);
}

