package com.porn.service.withdraw.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountQueryProxyTeamsDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.wallet.enums.WalletChainEnum;
import com.porn.client.withdraw.api.WithdrawApiService;
import com.porn.client.withdraw.dto.*;
import com.porn.client.withdraw.enums.WithdrawStatusEnum;
import com.porn.client.withdraw.vo.WithdrawVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.withdraw.converter.WithdrawConverter;
import com.porn.service.withdraw.dao.entity.WithdrawDO;
import com.porn.service.withdraw.dao.mapper.WithdrawMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service

@Transactional(rollbackFor = {Exception.class})
public class WithdrawApiServiceImpl implements WithdrawApiService {
    private static final Logger log = LoggerFactory.getLogger(WithdrawApiServiceImpl.class);


    @Autowired
    private WithdrawConverter withdrawConverter;


    @Autowired
    private WithdrawMapper withdrawMapper;


    @Autowired
    private AccountApiService accountApiService;

    public WithdrawVo queryWithdraw(WithdrawQueryDTO withdrawQueryDTO) {

        List<WithdrawVo> withdrawVoList = queryWithdrawList(withdrawQueryDTO);

        return ObjectUtil.isEmpty(withdrawVoList) ? null : withdrawVoList.get(0);

    }

    public List<WithdrawVo> queryWithdrawList(WithdrawQueryDTO withdrawQueryDTO) {

        List<WithdrawDO> withdrawList = ChainWrappers.lambdaQueryChain(withdrawMapper)
                .eq(ObjectUtil.isNotEmpty(withdrawQueryDTO.getStatus()), WithdrawDO::getStatus, withdrawQueryDTO.getStatus())
                .in(ObjectUtil.isNotEmpty(withdrawQueryDTO.getStatusList()), WithdrawDO::getStatus, withdrawQueryDTO.getStatusList())
                .eq(ObjectUtil.isNotEmpty(withdrawQueryDTO.getId()), BaseDO::getId, withdrawQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(withdrawQueryDTO.getWalletCode()), WithdrawDO::getWalletCode, withdrawQueryDTO.getWalletCode())
                .eq(ObjectUtil.isNotEmpty(withdrawQueryDTO.getAccountId()), WithdrawDO::getAccountId, withdrawQueryDTO.getAccountId())
                .ge(ObjectUtil.isNotEmpty(withdrawQueryDTO.getStartTime()), BaseDO::getCreateTime, withdrawQueryDTO.getStartTime())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(BaseDO::getCreateTime)
                .list();

        List<WithdrawVo> withdrawVoList = this.withdrawConverter.toWithdrawVoList(withdrawList);

        return withdrawVoList;

    }


    public PageVo<WithdrawVo> queryPage(WithdrawQueryPageDTO withdrawQueryPageDTO) {

        Page page = new Page(withdrawQueryPageDTO.getPageStart().intValue(), withdrawQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper<WithdrawDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(withdrawQueryPageDTO.getAccountId()), WithdrawDO::getAccountId, withdrawQueryPageDTO.getAccountId());
        wrapper.eq(ObjectUtil.isNotEmpty(withdrawQueryPageDTO.getWithdrawNo()), WithdrawDO::getWithdrawNo, withdrawQueryPageDTO.getWithdrawNo());
        wrapper.eq(ObjectUtil.isNotEmpty(withdrawQueryPageDTO.getWalletCode()), WithdrawDO::getWalletCode, withdrawQueryPageDTO.getWalletCode());
        wrapper.in(ObjectUtil.isNotEmpty(withdrawQueryPageDTO.getAccountIdList()), WithdrawDO::getAccountId, withdrawQueryPageDTO.getAccountIdList());
        wrapper.like(ObjectUtil.isNotEmpty(withdrawQueryPageDTO.getLkAccountName()), WithdrawDO::getAccountName, withdrawQueryPageDTO.getLkAccountName());
        wrapper.like(ObjectUtil.isNotEmpty(withdrawQueryPageDTO.getLkRemark()), WithdrawDO::getAccountRemark, withdrawQueryPageDTO.getLkRemark());
        wrapper.eq(ObjectUtil.isNotEmpty(withdrawQueryPageDTO.getReceiveAddress()), WithdrawDO::getReceiveAddress, withdrawQueryPageDTO.getReceiveAddress());
        wrapper.eq(ObjectUtil.isNotEmpty(withdrawQueryPageDTO.getStatus()), WithdrawDO::getStatus, withdrawQueryPageDTO.getStatus());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(BaseDO::getCreateTime);


        IPage<WithdrawDO> withdrawPage = this.withdrawMapper.selectPage((IPage) page, wrapper);

        List<WithdrawVo> withdrawVoList = this.withdrawConverter.toWithdrawVoList(withdrawPage.getRecords());

        return PageVo.<WithdrawVo>builder()
                .pageStart(withdrawQueryPageDTO.getPageStart())
                .pageSize(withdrawQueryPageDTO.getPageSize())
                .total(Long.valueOf(withdrawPage.getTotal()))
                .data(withdrawVoList)
                .build();

    }

    public PageVo<WithdrawVo> queryProxyPage(ProxyWithdrawQueryPageDTO proxyWithdrawQueryPageDTO) {

        AccountQueryProxyTeamsDTO accountQueryProxyTeamsDTO = AccountQueryProxyTeamsDTO.builder().mngUserId(proxyWithdrawQueryPageDTO.getCurrentUserId()).build();

        List<Long> accountIdList = this.accountApiService.queryProxyTeams(accountQueryProxyTeamsDTO);

        if (ObjectUtil.isEmpty(accountIdList)) {

            return PageVo.<WithdrawVo>builder()
                    .pageStart(proxyWithdrawQueryPageDTO.getPageStart())
                    .pageSize(proxyWithdrawQueryPageDTO.getPageSize())
                    .total(CommonConst.LZERO)
                    .data(Collections.emptyList())
                    .build();

        }

        WithdrawQueryPageDTO withdrawQueryPageDTO = this.withdrawConverter.toWithdrawQueryPageDTO(proxyWithdrawQueryPageDTO);

        withdrawQueryPageDTO.setAccountIdList(accountIdList);

        return queryPage(withdrawQueryPageDTO);

    }


    public WithdrawVo pass(WithdrawPassDTO withdrawPassDTO) {

        WithdrawVo withdrawVo = queryWithdraw(((WithdrawQueryDTO.WithdrawQueryDTOBuilder) WithdrawQueryDTO.builder().id(withdrawPassDTO.getId())).build());

        if (ObjectUtil.isEmpty(withdrawVo)) {

            throw new BusinessException("提现信息不存在.");

        }


        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(withdrawVo.getAccountId())).amountType(AmountTypeEnum.SUBTOTAL_SUBFREEZE.getType()).operateAmount(withdrawVo.getTotalAmount()).bizId(withdrawVo.getId()).streamTypeEnum(StreamTypeEnum.WITHDRAW_PASS).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);


        boolean rs = ChainWrappers.lambdaUpdateChain(withdrawMapper)
                .set(WithdrawDO::getStatus, WithdrawStatusEnum.EXAMINEPASS.getStatus())
                .eq(BaseDO::getId, withdrawVo.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新状态失败.");

        }

        return queryWithdraw(((WithdrawQueryDTO.WithdrawQueryDTOBuilder) WithdrawQueryDTO.builder().id(withdrawPassDTO.getId())).build());

    }


    public Boolean cancel(WithdrawCancelDTO withdrawCancelDTO) {

        WithdrawQueryDTO withdrawQueryDTO = ((WithdrawQueryDTO.WithdrawQueryDTOBuilder) WithdrawQueryDTO.builder().id(withdrawCancelDTO.getId())).build();

        WithdrawVo withdrawVo = queryWithdraw(withdrawQueryDTO);

        if (ObjectUtil.isEmpty(withdrawVo)) {

            throw new BusinessException("提现信息不存在.");

        }


        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(withdrawVo.getAccountId())).operateAmount(withdrawVo.getTotalAmount()).amountType(AmountTypeEnum.ADDAVAILABLE_SUBFREEZE.getType()).bizId(withdrawVo.getId()).streamTypeEnum(StreamTypeEnum.WITHDRAW_UNLOCK).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);


        WithdrawSaveOrUpdateDTO withdrawSaveOrUpdateDTO = ((WithdrawSaveOrUpdateDTO.WithdrawSaveOrUpdateDTOBuilder) WithdrawSaveOrUpdateDTO.builder().id(withdrawVo.getId())).status(WithdrawStatusEnum.TIMEOUT.getStatus()).build();

        saveOrUpdate(withdrawSaveOrUpdateDTO);

        return Boolean.TRUE;

    }


    public Boolean delete(WithdrawDeleteDTO withdrawDeleteDTO) {

        return ChainWrappers.lambdaUpdateChain(withdrawMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, withdrawDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }


    public WithdrawVo saveOrUpdate(WithdrawSaveOrUpdateDTO withdrawSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(withdrawSaveOrUpdateDTO.getId())) {


            WithdrawDO withdrawDO = this.withdrawConverter.toWithdrawDO(withdrawSaveOrUpdateDTO);

            withdrawDO.setStatus(WithdrawStatusEnum.EXAMINEING.getStatus());

            if (ObjectUtil.isNotEmpty(withdrawSaveOrUpdateDTO.getWalletCode())) {

                withdrawDO.setWalletName(WalletChainEnum.queryName(withdrawSaveOrUpdateDTO.getWalletCode()));

            }

            if (this.withdrawMapper.insert(withdrawDO) <= 0) {

                throw new BusinessException("保存提现信息失败.");

            }

            return queryWithdraw(((WithdrawQueryDTO.WithdrawQueryDTOBuilder) WithdrawQueryDTO.builder().id(withdrawDO.getId())).build());

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(withdrawMapper)
                .set(ObjectUtil.isNotEmpty(withdrawSaveOrUpdateDTO.getStatus()), WithdrawDO::getStatus, withdrawSaveOrUpdateDTO.getStatus())
                .eq(BaseDO::getId, withdrawSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新提现信息失败.");

        }

        return queryWithdraw(((WithdrawQueryDTO.WithdrawQueryDTOBuilder) WithdrawQueryDTO.builder().id(withdrawSaveOrUpdateDTO.getId())).build());

    }

}
