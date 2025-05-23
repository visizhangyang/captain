package com.porn.service.menu.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.service.menu.dao.entity.MenuDO;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper extends BaseMapper<MenuDO> {
    Integer queryMaxSortNo(@Param("parentId") Long paramLong);
}

