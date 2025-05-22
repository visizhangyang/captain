package com.porn.service.user.converter;

import com.porn.client.user.vo.UserLoginVo;
import com.porn.client.user.vo.UserVo;
import com.porn.service.user.dao.entity.UserDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserConverter {
    UserVo toUserVo(UserDO paramUserDO);

    List<UserVo> toUserVoList(List<UserDO> paramList);

    UserLoginVo toUserLoginVo(UserDO paramUserDO);
}


