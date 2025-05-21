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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/role/converter/RoleMenuConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */