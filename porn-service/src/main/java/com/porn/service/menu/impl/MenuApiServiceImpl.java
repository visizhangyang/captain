package com.porn.service.menu.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.menu.api.MenuApiService;
import com.porn.client.menu.dto.MenuDeleteDTO;
import com.porn.client.menu.dto.MenuQueryDTO;
import com.porn.client.menu.dto.MenuSaveOrUpdateDTO;
import com.porn.client.menu.enums.IconTypeEnum;
import com.porn.client.menu.vo.MenuVo;
import com.porn.client.minio.api.MinioApiService;
import com.porn.client.minio.dto.PrevFileDTO;
import com.porn.client.minio.vo.PrevFileVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.menu.converter.MenuConverter;
import com.porn.service.menu.dao.entity.MenuDO;
import com.porn.service.menu.dao.mapper.MenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service

@Transactional(rollbackFor = {Exception.class})
public class MenuApiServiceImpl implements MenuApiService {
    private static final Logger log = LoggerFactory.getLogger(MenuApiServiceImpl.class);


    @Autowired
    private MenuMapper menuMapper;


    @Autowired
    private MenuConverter menuConverter;


    @Autowired
    private MinioApiService minioApiService;

    public MenuVo queryMenu(MenuQueryDTO menuQueryDTO) {

        List<MenuVo> menuVoList = queryMenuList(menuQueryDTO);

        return ObjectUtil.isEmpty(menuVoList) ? null : menuVoList.get(0);

    }

    public List<MenuVo> queryMenuList(MenuQueryDTO menuQueryDTO) {
        // 构建查询条件
        LambdaQueryChainWrapper<MenuDO> queryChain = ChainWrappers.lambdaQueryChain(menuMapper)
                .eq(ObjectUtil.isNotEmpty(menuQueryDTO.getId()), BaseDO::getId, menuQueryDTO.getId())
                .in(ObjectUtil.isNotEmpty(menuQueryDTO.getMenuIdList()), BaseDO::getId, menuQueryDTO.getMenuIdList())
                .eq(ObjectUtil.isNotEmpty(menuQueryDTO.getName()), MenuDO::getName, menuQueryDTO.getName())
                .like(ObjectUtil.isNotEmpty(menuQueryDTO.getLkName()), MenuDO::getName, menuQueryDTO.getLkName())
                .eq(ObjectUtil.isNotEmpty(menuQueryDTO.getIconType()), MenuDO::getIconType, menuQueryDTO.getIconType())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

        List<MenuDO> menuList = queryChain.list();
        List<MenuVo> menuVoList = menuConverter.toMenuVoList(menuList);

        if (ObjectUtil.isNotEmpty(menuVoList)) {
            menuVoList.stream()
                    .filter(menuVo -> IconTypeEnum.UPLOAD.getType().equals(menuVo.getIconType()))
                    .filter(menuVo -> ObjectUtil.isNotEmpty(menuVo.getIconPath()))
                    .forEach(menuVo -> {
                        PrevFileVo prevFileVo = minioApiService.prevFile(
                                PrevFileDTO.builder().filePath(menuVo.getIconPath()).build()
                        );
                        if (prevFileVo != null) {
                            menuVo.setIconUrl(prevFileVo.getFileUrl());
                        }
                    });
        }

        return menuVoList;

    }

    public MenuVo saveOrUpdate(MenuSaveOrUpdateDTO menuSaveOrUpdateDTO) {
        // 新增逻辑
        if (ObjectUtil.isEmpty(menuSaveOrUpdateDTO.getId())) {
            MenuDO menuDO = menuConverter.toMenuDO(menuSaveOrUpdateDTO);

            if (ObjectUtil.isEmpty(menuDO.getSortNo())) {
                Integer maxSortNo = menuMapper.queryMaxSortNo(
                        ObjectUtil.isEmpty(menuSaveOrUpdateDTO.getParentId()) ? CommonConst.LZERO : menuSaveOrUpdateDTO.getParentId()
                );
                menuDO.setSortNo(maxSortNo == null ? 0 : maxSortNo + 1);
            }

            if (ObjectUtil.isEmpty(menuDO.getParentId())) {
                menuDO.setParentId(CommonConst.LZERO);
            }

            int insertCount = menuMapper.insert(menuDO);
            if (insertCount <= 0) {
                throw new BusinessException("保存菜单信息失败.");
            }
            return queryMenu(MenuQueryDTO.builder().id(menuDO.getId()).build());
        }

        // 更新逻辑
        LambdaUpdateChainWrapper<MenuDO> updateChain = ChainWrappers.lambdaUpdateChain(menuMapper)
                .set(ObjectUtil.isNotEmpty(menuSaveOrUpdateDTO.getName()), MenuDO::getName, menuSaveOrUpdateDTO.getName())
                .set(MenuDO::getIconType, menuSaveOrUpdateDTO.getIconType())
                .set(MenuDO::getIconPath, StrUtil.emptyToDefault(menuSaveOrUpdateDTO.getIconPath(), ""))
                .set(MenuDO::getUrlPath, StrUtil.emptyToDefault(menuSaveOrUpdateDTO.getUrlPath(), ""))
                .set(MenuDO::getDescription, StrUtil.emptyToDefault(menuSaveOrUpdateDTO.getDescription(), ""))
                .eq(BaseDO::getId, menuSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

        boolean updated = updateChain.update();
        if (!updated) {
            throw new BusinessException("更新菜单信息失败.");
        }

        return queryMenu(MenuQueryDTO.builder().id(menuSaveOrUpdateDTO.getId()).build());

    }


    public Boolean delete(MenuDeleteDTO menuDeleteDTO) {
        return ChainWrappers.lambdaUpdateChain(menuMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, menuDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

}

