package com.porn.service.menu.converter;

import com.porn.client.menu.dto.MenuSaveOrUpdateDTO;
import com.porn.client.menu.vo.MenuVo;
import com.porn.service.menu.dao.entity.MenuDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuConverter {
    MenuVo toMenuVo(MenuDO paramMenuDO);

    List<MenuVo> toMenuVoList(List<MenuDO> paramList);

    MenuDO toMenuDO(MenuSaveOrUpdateDTO paramMenuSaveOrUpdateDTO);
}

