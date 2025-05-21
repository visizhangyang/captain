package com.porn.service.menu.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.service.menu.dao.entity.MenuDO;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper extends BaseMapper<MenuDO> {
    Integer queryMaxSortNo(@Param("parentId") Long paramLong);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/menu/dao/mapper/MenuMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */