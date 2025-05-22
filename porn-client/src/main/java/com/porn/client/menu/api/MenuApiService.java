package com.porn.client.menu.api;

import com.porn.client.menu.dto.MenuDeleteDTO;
import com.porn.client.menu.dto.MenuQueryDTO;
import com.porn.client.menu.dto.MenuSaveOrUpdateDTO;
import com.porn.client.menu.vo.MenuVo;

import java.util.List;

public interface MenuApiService {
    MenuVo queryMenu(MenuQueryDTO paramMenuQueryDTO);

    List<MenuVo> queryMenuList(MenuQueryDTO paramMenuQueryDTO);

    MenuVo saveOrUpdate(MenuSaveOrUpdateDTO paramMenuSaveOrUpdateDTO);

    Boolean delete(MenuDeleteDTO paramMenuDeleteDTO);
}


