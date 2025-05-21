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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/menu/api/MenuApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */