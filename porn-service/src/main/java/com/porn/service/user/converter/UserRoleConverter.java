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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/user/converter/UserRoleConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */