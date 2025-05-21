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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/role/converter/RoleConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */