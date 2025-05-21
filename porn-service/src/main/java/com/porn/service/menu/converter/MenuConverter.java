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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/menu/converter/MenuConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */