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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/user/converter/UserConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */