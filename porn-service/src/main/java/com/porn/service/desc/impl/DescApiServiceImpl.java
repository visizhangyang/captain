
package com.porn.service.desc.impl;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.desc.api.DescApiService;
import com.porn.client.desc.dto.DescQueryDTO;
import com.porn.client.desc.dto.DescSaveOrUpdateDTO;
import com.porn.client.desc.vo.DescVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.desc.converter.DescConverter;
import com.porn.service.desc.dao.entity.DescDO;
import com.porn.service.desc.dao.mapper.DescMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service

@Transactional(rollbackFor = {Exception.class})
 public class DescApiServiceImpl implements DescApiService {
    /*  28 */   private static final Logger log = LoggerFactory.getLogger(DescApiServiceImpl.class);



    @Autowired
     private DescConverter descConverter;



    @Autowired
     private DescMapper descMapper;





    public DescVo queryDesc(DescQueryDTO descQueryDTO) {
        /*  41 */
        List<DescVo> descVoList = queryDescList(descQueryDTO);
        /*  42 */
        return ObjectUtil.isEmpty(descVoList) ? null : descVoList.get(0);

    }









    public List<DescVo> queryDescList(DescQueryDTO descQueryDTO) {
        LambdaQueryChainWrapper<DescDO> queryChain = ChainWrappers.lambdaQueryChain(descMapper);

        if (ObjectUtil.isNotEmpty(descQueryDTO.getDescType())) {
            queryChain.eq(DescDO::getDescType, descQueryDTO.getDescType());
        }
        if (ObjectUtil.isNotEmpty(descQueryDTO.getId())) {
            queryChain.eq(BaseDO::getId, descQueryDTO.getId());
        }
        if (ObjectUtil.isNotEmpty(descQueryDTO.getLangType())) {
            queryChain.eq(DescDO::getLangType, descQueryDTO.getLangType());
        }

        queryChain.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        queryChain.orderByAsc(DescDO::getDescType, DescDO::getSortNo);

        List<DescDO> descList = queryChain.list();

        if (ObjectUtil.isEmpty(descList)) {
            return Collections.emptyList();
        }

        List<DescVo> descVoList = descConverter.toDescVoList(descList);
        return descVoList;

    }



    public DescVo saveOrUpdate(DescSaveOrUpdateDTO descSaveOrUpdateDTO) {
        if (ObjectUtil.isEmpty(descSaveOrUpdateDTO.getId())) {
            DescDO descDO = descConverter.toDescDO(descSaveOrUpdateDTO);
            if (descMapper.insert(descDO) <= 0) {
                throw new BusinessException("保存描述信息失败.");
            }
            return queryDesc(DescQueryDTO.builder().id(descDO.getId()).build());
        } else {
            LambdaUpdateChainWrapper<DescDO> updateChain = ChainWrappers.lambdaUpdateChain(descMapper)
                    .set(DescDO::getDescType, descSaveOrUpdateDTO.getDescType())
                    .set(DescDO::getDescText, descSaveOrUpdateDTO.getDescText())
                    .set(DescDO::getSortNo, descSaveOrUpdateDTO.getSortNo())
                    .set(DescDO::getLangType, descSaveOrUpdateDTO.getLangType())
                    .eq(BaseDO::getId, descSaveOrUpdateDTO.getId())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

            boolean rs = updateChain.update();

            if (!rs) {
                throw new BusinessException("更新描述信息失败.");
            }

            return queryDesc(DescQueryDTO.builder().id(descSaveOrUpdateDTO.getId()).build());
        }

    }

    public Boolean batchCreate(List<DescSaveOrUpdateDTO> descSaveOrUpdateDTOList) {
        if (ObjectUtil.isEmpty(descSaveOrUpdateDTOList)) {
            throw new BusinessException("数据为空.");
        }

        DescSaveOrUpdateDTO firstDTO = descSaveOrUpdateDTOList.get(0);

        List<DescVo> descVoList = queryDescList(
                DescQueryDTO.builder()
                        .langType(firstDTO.getLangType())
                        .descType(firstDTO.getDescType())
                        .build()
        );

        // 获取数据库中已有的ID列表，空时初始化空列表
        List<Long> dbIdList = ObjectUtil.isEmpty(descVoList) ?
                new ArrayList<>() :
                descVoList.stream().map(BaseVo::getId).collect(Collectors.toList());

        int index = 0;
        for (DescSaveOrUpdateDTO dto : descSaveOrUpdateDTOList) {
            dto.setSortNo(index++);
            saveOrUpdate(dto);
            if (ObjectUtil.isNotEmpty(dto.getId())) {
                dbIdList.remove(dto.getId());
            }
        }

        if (ObjectUtil.isNotEmpty(dbIdList)) {
            LambdaUpdateChainWrapper<DescDO> updateChain = ChainWrappers.lambdaUpdateChain(descMapper)
                    .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                    .in(BaseDO::getId, dbIdList)
                    .eq(DescDO::getDescType, firstDTO.getDescType())
                    .eq(DescDO::getLangType, firstDTO.getLangType())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

            boolean updated = updateChain.update();
            return Boolean.valueOf(updated);
        }

        return Boolean.TRUE;

    }
}
