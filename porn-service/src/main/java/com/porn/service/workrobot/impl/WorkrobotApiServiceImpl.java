package com.porn.service.workrobot.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.workrobot.api.WorkrobotApiService;
import com.porn.client.workrobot.dto.WorkrobotQueryDTO;
import com.porn.client.workrobot.dto.WorkrobotSaveOrUpdateDTO;
import com.porn.client.workrobot.vo.WorkrobotVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.workrobot.converter.WorkrobotConverter;
import com.porn.service.workrobot.dao.entity.WorkrobotDO;
import com.porn.service.workrobot.dao.mapper.WorkrobotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

@Transactional(rollbackFor = {Exception.class})
public class WorkrobotApiServiceImpl implements WorkrobotApiService {
    private static final Logger log = LoggerFactory.getLogger(WorkrobotApiServiceImpl.class);


    @Autowired
    private WorkrobotConverter workrobotConverter;


    @Autowired
    private WorkrobotMapper workrobotMapper;


    public WorkrobotVo queryWorkrobot(WorkrobotQueryDTO workrobotQueryDTO) {

        List<WorkrobotVo> workrobotVoList = queryWorkrobotList(workrobotQueryDTO);

        return ObjectUtil.isEmpty(workrobotVoList) ? null : workrobotVoList.get(0);

    }


    public List<WorkrobotVo> queryWorkrobotList(WorkrobotQueryDTO workrobotQueryDTO) {

        List<WorkrobotDO> workrobotDOList = ChainWrappers.lambdaQueryChain(workrobotMapper)
                .eq(ObjectUtil.isNotEmpty(workrobotQueryDTO.getId()), BaseDO::getId, workrobotQueryDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByAsc(BaseDO::getId)
                .list();

        List<WorkrobotVo> workrobotVoList = this.workrobotConverter.toWorkrobotVoList(workrobotDOList);

        return workrobotVoList;

    }


    public WorkrobotVo saveOrUpdate(WorkrobotSaveOrUpdateDTO workrobotSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(workrobotSaveOrUpdateDTO.getId())) {

            WorkrobotDO workrobotDO = this.workrobotConverter.toWorkrobotDO(workrobotSaveOrUpdateDTO);

            if (this.workrobotMapper.insert(workrobotDO) <= 0) {

                throw new BusinessException("保存参数信息失败.");

            }

            return queryWorkrobot(((WorkrobotQueryDTO.WorkrobotQueryDTOBuilder) WorkrobotQueryDTO.builder().id(workrobotDO.getId())).build());

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(workrobotMapper)
                .set(WorkrobotDO::getMinWorkAmount, workrobotSaveOrUpdateDTO.getMinWorkAmount())
                .set(WorkrobotDO::getMaxWorkAmount, workrobotSaveOrUpdateDTO.getMaxWorkAmount())
                .set(WorkrobotDO::getMinWorkTime, workrobotSaveOrUpdateDTO.getMinWorkTime())
                .set(WorkrobotDO::getMaxWorkTime, workrobotSaveOrUpdateDTO.getMaxWorkTime())
                .set(WorkrobotDO::getOrderCount, workrobotSaveOrUpdateDTO.getOrderCount())
                .set(WorkrobotDO::getMinLoanTime, workrobotSaveOrUpdateDTO.getMinLoanTime())
                .set(WorkrobotDO::getMaxLoanTime, workrobotSaveOrUpdateDTO.getMaxLoanTime())
                .set(WorkrobotDO::getMinCompleteTime, workrobotSaveOrUpdateDTO.getMinCompleteTime())
                .set(WorkrobotDO::getMaxCompleteTime, workrobotSaveOrUpdateDTO.getMaxCompleteTime())
                .set(BaseDO::getModifyTime, LocalDateTimeUtil.now())
                .eq(BaseDO::getId, workrobotSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新参数信息失败.");

        }

        return queryWorkrobot(((WorkrobotQueryDTO.WorkrobotQueryDTOBuilder) WorkrobotQueryDTO.builder().id(workrobotSaveOrUpdateDTO.getId())).build());

    }

    public Boolean batchCreate(List<WorkrobotSaveOrUpdateDTO> workrobotSaveOrUpdateDTOList) {

        if (ObjectUtil.isEmpty(workrobotSaveOrUpdateDTOList)) {

            throw new BusinessException("配置信息为空.");

        }


        List<WorkrobotVo> workrobotVoList = queryWorkrobotList(WorkrobotQueryDTO.builder().build());


        List<Long> dbIdList = ObjectUtil.isEmpty(workrobotVoList) ? ListUtil.list(false) : (List<Long>) workrobotVoList.stream().map(BaseVo::getId).collect(Collectors.toList());

        for (WorkrobotSaveOrUpdateDTO workrobotSaveOrUpdateDTO : workrobotSaveOrUpdateDTOList) {

            DateTime dateTime = DateUtil.parse(workrobotSaveOrUpdateDTO.getMaxWorkTime(), "yyyy-MM-dd HH:mm:ss");

            dateTime.setSeconds(59);

            workrobotSaveOrUpdateDTO.setMaxWorkTime(DateUtil.format((Date) dateTime, "yyyy-MM-dd HH:mm:ss"));

            saveOrUpdate(workrobotSaveOrUpdateDTO);

            if (ObjectUtil.isNotEmpty(workrobotSaveOrUpdateDTO.getId())) {

                dbIdList.remove(workrobotSaveOrUpdateDTO.getId());

            }

        }

        if (ObjectUtil.isNotEmpty(dbIdList)) {

            return ChainWrappers.lambdaUpdateChain(workrobotMapper)
                    .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                    .in(BaseDO::getId, dbIdList)
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .update();

        }

        return Boolean.TRUE;

    }

}

