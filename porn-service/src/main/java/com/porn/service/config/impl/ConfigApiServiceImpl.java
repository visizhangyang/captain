
package com.porn.service.config.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryProxyTeamsDTO;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.config.api.ConfigApiService;
import com.porn.client.config.dto.*;
import com.porn.client.config.vo.ConfigVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.config.converter.ConfigConverter;
import com.porn.service.config.dao.entity.ConfigDO;
import com.porn.service.config.dao.mapper.ConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service

@Transactional(rollbackFor = {Exception.class})
 public class ConfigApiServiceImpl implements ConfigApiService {
    /*  33 */   private static final Logger log = LoggerFactory.getLogger(ConfigApiServiceImpl.class);



    @Autowired
     private ConfigConverter configConverter;



    @Autowired
     private ConfigMapper configMapper;



    @Autowired
     private AccountApiService accountApiService;




    public ConfigVo queryConfig(ConfigQueryDTO configQueryDTO) {
        /*  49 */
        List<ConfigVo> configVoList = queryConfigList(configQueryDTO);
        /*  50 */
        return ObjectUtil.isEmpty(configVoList) ? null : configVoList.get(0);

    }













    public List<ConfigVo> queryConfigList(ConfigQueryDTO configQueryDTO) {
        LambdaQueryChainWrapper<ConfigDO> query = ChainWrappers.lambdaQueryChain(configMapper);

        query.eq(ObjectUtil.isNotEmpty(configQueryDTO.getId()), BaseDO::getId, configQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(configQueryDTO.getConfigCode()), ConfigDO::getConfigCode, configQueryDTO.getConfigCode())
                .in(ObjectUtil.isNotEmpty(configQueryDTO.getConfigCodeList()), ConfigDO::getConfigCode, configQueryDTO.getConfigCodeList())
                .eq(ObjectUtil.isNotEmpty(configQueryDTO.getConfigGroup()), ConfigDO::getConfigGroup, configQueryDTO.getConfigGroup())
                .in(ObjectUtil.isNotEmpty(configQueryDTO.getConfigGroupList()), ConfigDO::getConfigGroup, configQueryDTO.getConfigGroupList())
                .eq(ObjectUtil.isNotEmpty(configQueryDTO.getAccountId()), ConfigDO::getAccountId, configQueryDTO.getAccountId())
                .eq(ObjectUtil.isNotEmpty(configQueryDTO.getStatus()), ConfigDO::getStatus, configQueryDTO.getStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByAsc(ConfigDO::getSortNo);

        List<ConfigDO> configList = query.list();

        if (CollUtil.isEmpty(configList)) {
            return Collections.emptyList();
        }

        return configConverter.toConfigVoList(configList);
    }



    public PageVo<ConfigVo> queryPage(ConfigQueryPageDTO configQueryPageDTO) {
        // 创建分页对象
        Page<ConfigDO> page = new Page<>(configQueryPageDTO.getPageStart(), configQueryPageDTO.getPageSize(), true);

        // 构造查询条件
        LambdaQueryWrapper<ConfigDO> queryWrapper = new LambdaQueryWrapper<ConfigDO>()
                .eq(ObjectUtil.isNotEmpty(configQueryPageDTO.getConfigCode()), ConfigDO::getConfigCode, configQueryPageDTO.getConfigCode())
                .like(ObjectUtil.isNotEmpty(configQueryPageDTO.getLkConfigCode()), ConfigDO::getConfigCode, configQueryPageDTO.getLkConfigCode())
                .eq(ObjectUtil.isNotEmpty(configQueryPageDTO.getConfigGroup()), ConfigDO::getConfigGroup, configQueryPageDTO.getConfigGroup())
                .eq(ObjectUtil.isNotEmpty(configQueryPageDTO.getStatus()), ConfigDO::getStatus, configQueryPageDTO.getStatus())
                .eq(ObjectUtil.isNotEmpty(configQueryPageDTO.getAccountId()), ConfigDO::getAccountId, configQueryPageDTO.getAccountId())
                .in(ObjectUtil.isNotEmpty(configQueryPageDTO.getAccountIdList()), ConfigDO::getAccountId, configQueryPageDTO.getAccountIdList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

        // 执行分页查询
        IPage<ConfigDO> configPage = configMapper.selectPage(page, queryWrapper);

        // 转换为 VO
        List<ConfigVo> configVoList = configConverter.toConfigVoList(configPage.getRecords());

        // 构建并返回分页结果
        return PageVo.<ConfigVo>builder()
                .pageStart(configQueryPageDTO.getPageStart())
                .pageSize(configQueryPageDTO.getPageSize())
                .total(configPage.getTotal())
                .data(configVoList)
                .build();
    }



    public ConfigVo saveOrUpdate(ConfigSaveOrUpdateDTO configSaveOrUpdateDTO) {
        if (ObjectUtil.isEmpty(configSaveOrUpdateDTO.getId())) {
            // 新增逻辑
            ConfigDO configDO = configConverter.toConfigDO(configSaveOrUpdateDTO);
            if (ObjectUtil.isEmpty(configDO.getAccountId())) {
                configDO.setAccountId(CommonConst.LZERO);
            }
            int insertResult = configMapper.insert(configDO);
            if (insertResult <= 0) {
                throw new BusinessException("保存配置信息失败.");
            }
            return queryConfig(ConfigQueryDTO.builder().id(configDO.getId()).build());
        } else {
            // 更新逻辑
            LambdaUpdateChainWrapper<ConfigDO> updateChain = ChainWrappers.lambdaUpdateChain(configMapper)
                    .set(ConfigDO::getConfigCode, configSaveOrUpdateDTO.getConfigCode())
                    .set(ConfigDO::getConfigGroup, configSaveOrUpdateDTO.getConfigGroup())
                    .set(ConfigDO::getConfigValue, configSaveOrUpdateDTO.getConfigValue());

            // 条件设置只在非空时调用 set，避免覆盖为null
            if (ObjectUtil.isNotEmpty(configSaveOrUpdateDTO.getConfigDesc())) {
                updateChain.set(ConfigDO::getConfigDesc, configSaveOrUpdateDTO.getConfigDesc());
            }
            if (ObjectUtil.isNotEmpty(configSaveOrUpdateDTO.getStatus())) {
                updateChain.set(ConfigDO::getStatus, configSaveOrUpdateDTO.getStatus());
            }
            if (ObjectUtil.isNotEmpty(configSaveOrUpdateDTO.getSortNo())) {
                updateChain.set(ConfigDO::getSortNo, configSaveOrUpdateDTO.getSortNo());
            }
            if (ObjectUtil.isNotEmpty(configSaveOrUpdateDTO.getAccountId())) {
                updateChain.set(ConfigDO::getAccountId, configSaveOrUpdateDTO.getAccountId());
            }

            boolean updateResult = updateChain
                    .eq(BaseDO::getId, configSaveOrUpdateDTO.getId())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .update();

            if (!updateResult) {
                throw new BusinessException("更新配置信息失败.");
            }
            return queryConfig(ConfigQueryDTO.builder().id(configSaveOrUpdateDTO.getId()).build());
        }
    }





    public Boolean enableOrDisable(ConfigEnableOrDisableDTO configEnableOrDisableDTO) {
        Integer newStatus = EnableStatusEnum.ENABLE.getStatus().equals(configEnableOrDisableDTO.getStatus())
                ? EnableStatusEnum.DISABLED.getStatus()
                : EnableStatusEnum.ENABLE.getStatus();

        return ChainWrappers.lambdaUpdateChain(configMapper)
                .set(ConfigDO::getStatus, newStatus)
                .eq(BaseDO::getId, configEnableOrDisableDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }




    public Boolean delete(ConfigDeleteDTO configDeleteDTO) {
        return ChainWrappers.lambdaUpdateChain(configMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, configDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .eq(ObjectUtil.isNotEmpty(configDeleteDTO.getAccountId()), ConfigDO::getAccountId, configDeleteDTO.getAccountId())
                .update();
    }







    public PageVo<ConfigVo> queryProxyPage(ProxyConfigQueryPageDTO proxyConfigQueryPageDTO) {
        AccountQueryProxyTeamsDTO proxyTeamsDTO = AccountQueryProxyTeamsDTO.builder()
                .mngUserId(proxyConfigQueryPageDTO.getCurrentUserId())
                .build();

        List<Long> proxyAccountIds = accountApiService.queryProxyTeams(proxyTeamsDTO);

        if (ObjectUtil.isEmpty(proxyAccountIds)) {
            return PageVo.<ConfigVo>builder()
                    .pageStart(proxyConfigQueryPageDTO.getPageStart())
                    .pageSize(proxyConfigQueryPageDTO.getPageSize())
                    .total(CommonConst.LZERO)
                    .data(Collections.emptyList())
                    .build();
        }

        Long lastAccountId = proxyAccountIds.get(proxyAccountIds.size() - 1);
        ConfigQueryPageDTO configQueryPageDTO = configConverter.toConfigQueryPageDTO(proxyConfigQueryPageDTO);
        configQueryPageDTO.setAccountId(lastAccountId);

        return queryPage(configQueryPageDTO);

    }

    public ConfigVo proxySaveOrUpdate(ProxyConfigSaveOrUpdateDTO proxyConfigSaveOrUpdateDTO) {
        /* 167 */
        AccountQueryProxyTeamsDTO accountQueryProxyTeamsDTO = AccountQueryProxyTeamsDTO.builder().mngUserId(proxyConfigSaveOrUpdateDTO.getCurrentUserId()).build();
        /* 168 */
        List<Long> accountIdList = this.accountApiService.queryProxyTeams(accountQueryProxyTeamsDTO);
        /* 169 */
        if (ObjectUtil.isEmpty(accountIdList)) {
            /* 170 */
            return null;

        }
        /* 172 */
        Long accountId = accountIdList.get(accountIdList.size() - 1);
        /* 173 */
        ConfigSaveOrUpdateDTO configSaveOrUpdateDTO = this.configConverter.toConfigSaveOrUpdateDTO(proxyConfigSaveOrUpdateDTO);
        /* 174 */
        configSaveOrUpdateDTO.setAccountId(accountId);
        /* 175 */
        return saveOrUpdate(configSaveOrUpdateDTO);

    }







    public Boolean proxyDelete(ProxyConfigDeleteDTO proxyConfigDeleteDTO) {
        /* 183 */
        AccountQueryProxyTeamsDTO accountQueryProxyTeamsDTO = AccountQueryProxyTeamsDTO.builder().mngUserId(proxyConfigDeleteDTO.getCurrentUserId()).build();
        /* 184 */
        List<Long> accountIdList = this.accountApiService.queryProxyTeams(accountQueryProxyTeamsDTO);
        /* 185 */
        if (ObjectUtil.isEmpty(accountIdList)) {
            /* 186 */
            return Boolean.FALSE;

        }
        /* 188 */
        Long accountId = accountIdList.get(accountIdList.size() - 1);
        /* 189 */
        ConfigDeleteDTO configDeleteDTO = this.configConverter.toConfigDeleteDTO(proxyConfigDeleteDTO);
        /* 190 */
        configDeleteDTO.setAccountId(accountId);
        /* 191 */
        return delete(configDeleteDTO);

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/config/impl/ConfigApiServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */