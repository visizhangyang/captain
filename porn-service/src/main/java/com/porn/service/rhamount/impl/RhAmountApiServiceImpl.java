
package com.porn.service.rhamount.impl;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
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
    /* 27 */   private static final Logger log = LoggerFactory.getLogger(RhAmountApiServiceImpl.class);



    @Autowired
     private RhAmountMapper rhAmountMapper;



    @Autowired
     private RhAmountConverter rhAmountConverter;





    public RhAmountVo queryRhAmount(RhAmountQueryDTO rhAmountQueryDTO) {
        /* 40 */
        List<RhAmountVo> rhAmountVoList = queryRhAmountList(rhAmountQueryDTO);
        /* 41 */
        return ObjectUtil.isEmpty(rhAmountVoList) ? null : rhAmountVoList.get(0);

    }






    public List<RhAmountVo> queryRhAmountList(RhAmountQueryDTO rhAmountQueryDTO) {
        /* 48 */
        List<RhAmountDO> rhAmountList = ChainWrappers.lambdaQueryChain(rhAmountMapper)
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByAsc(RhAmountDO::getSortNo)
                .list();
        /* 49 */
        List<RhAmountVo> rhAmountVoList = this.rhAmountConverter.toRhAmountVoList(rhAmountList);
        /* 50 */
        return rhAmountVoList;

    }



    public RhAmountVo saveOrUpdate(RhAmountSaveOrUpdateDTO rhAmountSaveOrUpdateDTO) {
        /* 54 */
        if (ObjectUtil.isEmpty(rhAmountSaveOrUpdateDTO.getId())) {
            /* 55 */
            RhAmountDO rhAmountDO = this.rhAmountConverter.toRhAmountDO(rhAmountSaveOrUpdateDTO);
            /* 56 */
            if (this.rhAmountMapper.insert(rhAmountDO) <= 0) {
                /* 57 */
                throw new BusinessException("保存金额信息失败.");

            }
            /* 59 */
            return queryRhAmount(((RhAmountQueryDTO.RhAmountQueryDTOBuilder) RhAmountQueryDTO.builder().id(rhAmountDO.getId())).build());

        }





        /* 66 */
        boolean rs = ChainWrappers.lambdaUpdateChain(rhAmountMapper)
                .set(RhAmountDO::getAmount, rhAmountSaveOrUpdateDTO.getAmount())
                .set(RhAmountDO::getSortNo, rhAmountSaveOrUpdateDTO.getSortNo())
                .eq(BaseDO::getId, rhAmountSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /* 67 */
        if (!rs) {
            /* 68 */
            throw new BusinessException("更新参数信息失败.");

        }
        /* 70 */
        return queryRhAmount(((RhAmountQueryDTO.RhAmountQueryDTOBuilder) RhAmountQueryDTO.builder().id(rhAmountSaveOrUpdateDTO.getId())).build());

    }




    public Boolean batchCreate(List<RhAmountSaveOrUpdateDTO> rhAmountSaveOrUpdateDTOList) {
        /* 75 */
        if (ObjectUtil.isEmpty(rhAmountSaveOrUpdateDTOList)) {
            /* 76 */
            throw new BusinessException("金额信息为空.");

        }

        /* 79 */
        List<RhAmountVo> rhAmountVoList = queryRhAmountList(RhAmountQueryDTO.builder().build());

        /* 81 */
        List<Long> dbIdList = ObjectUtil.isEmpty(rhAmountVoList) ? ListUtil.list(false) : (List<Long>) rhAmountVoList.stream().map(BaseVo::getId).collect(Collectors.toList());
        /* 82 */
        int index = 0;
        /* 83 */
        for (RhAmountSaveOrUpdateDTO rhAmountSaveOrUpdateDTO : rhAmountSaveOrUpdateDTOList) {
            /* 84 */
            rhAmountSaveOrUpdateDTO.setSortNo(Integer.valueOf(index++));
            /* 85 */
            saveOrUpdate(rhAmountSaveOrUpdateDTO);
            /* 86 */
            if (ObjectUtil.isNotEmpty(rhAmountSaveOrUpdateDTO.getId())) {
                /* 87 */
                dbIdList.remove(rhAmountSaveOrUpdateDTO.getId());

            }

        }
        /* 90 */
        if (ObjectUtil.isNotEmpty(dbIdList))
             {
            /* 92 */
            return ChainWrappers.lambdaUpdateChain(rhAmountMapper)
                    .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                    .in(BaseDO::getId, dbIdList)
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 96 */.update();

        }
        /* 98 */
        return Boolean.TRUE;

    }

}
