package com.porn.service.rhamount.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.rhamount.api.RhAmountApiService;
import com.porn.client.rhamount.dto.RhAmountQueryDTO;
import com.porn.client.rhamount.dto.RhAmountSaveOrUpdateDTO;
import com.porn.client.rhamount.vo.RhAmountVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.rhamount.converter.RhAmountConverter;
import com.porn.service.rhamount.dao.entity.RhAmountDO;
import com.porn.service.rhamount.dao.mapper.RhAmountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service

@Transactional(rollbackFor = {Exception.class})
public class RhAmountApiServiceImpl implements RhAmountApiService {
    private static final Logger log = LoggerFactory.getLogger(RhAmountApiServiceImpl.class);


    @Autowired
    private RhAmountMapper rhAmountMapper;


    @Autowired
    private RhAmountConverter rhAmountConverter;


    public RhAmountVo queryRhAmount(RhAmountQueryDTO rhAmountQueryDTO) {

        List<RhAmountVo> rhAmountVoList = queryRhAmountList(rhAmountQueryDTO);

        return ObjectUtil.isEmpty(rhAmountVoList) ? null : rhAmountVoList.get(0);

    }

    public List<RhAmountVo> queryRhAmountList(RhAmountQueryDTO rhAmountQueryDTO) {

        List<RhAmountDO> rhAmountList = ChainWrappers.lambdaQueryChain(rhAmountMapper)
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByAsc(RhAmountDO::getSortNo)
                .list();

        List<RhAmountVo> rhAmountVoList = this.rhAmountConverter.toRhAmountVoList(rhAmountList);

        return rhAmountVoList;

    }


    public RhAmountVo saveOrUpdate(RhAmountSaveOrUpdateDTO rhAmountSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(rhAmountSaveOrUpdateDTO.getId())) {

            RhAmountDO rhAmountDO = this.rhAmountConverter.toRhAmountDO(rhAmountSaveOrUpdateDTO);

            if (this.rhAmountMapper.insert(rhAmountDO) <= 0) {

                throw new BusinessException("保存金额信息失败.");

            }

            return queryRhAmount(((RhAmountQueryDTO.RhAmountQueryDTOBuilder) RhAmountQueryDTO.builder().id(rhAmountDO.getId())).build());

        }

        boolean rs = ChainWrappers.lambdaUpdateChain(rhAmountMapper)
                .set(RhAmountDO::getAmount, rhAmountSaveOrUpdateDTO.getAmount())
                .set(RhAmountDO::getSortNo, rhAmountSaveOrUpdateDTO.getSortNo())
                .eq(BaseDO::getId, rhAmountSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新参数信息失败.");

        }

        return queryRhAmount(((RhAmountQueryDTO.RhAmountQueryDTOBuilder) RhAmountQueryDTO.builder().id(rhAmountSaveOrUpdateDTO.getId())).build());

    }

    public Boolean batchCreate(List<RhAmountSaveOrUpdateDTO> rhAmountSaveOrUpdateDTOList) {

        if (ObjectUtil.isEmpty(rhAmountSaveOrUpdateDTOList)) {

            throw new BusinessException("金额信息为空.");

        }

        List<RhAmountVo> rhAmountVoList = queryRhAmountList(RhAmountQueryDTO.builder().build());

        List<Long> dbIdList = ObjectUtil.isEmpty(rhAmountVoList) ? ListUtil.list(false) : (List<Long>) rhAmountVoList.stream().map(BaseVo::getId).collect(Collectors.toList());

        int index = 0;

        for (RhAmountSaveOrUpdateDTO rhAmountSaveOrUpdateDTO : rhAmountSaveOrUpdateDTOList) {

            rhAmountSaveOrUpdateDTO.setSortNo(Integer.valueOf(index++));

            saveOrUpdate(rhAmountSaveOrUpdateDTO);

            if (ObjectUtil.isNotEmpty(rhAmountSaveOrUpdateDTO.getId())) {

                dbIdList.remove(rhAmountSaveOrUpdateDTO.getId());

            }

        }

        if (ObjectUtil.isNotEmpty(dbIdList)) {

            return ChainWrappers.lambdaUpdateChain(rhAmountMapper)
                    .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                    .in(BaseDO::getId, dbIdList)
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .update();

        }

        return Boolean.TRUE;

    }

}
