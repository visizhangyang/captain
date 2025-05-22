
package com.porn.service.withdraw.impl;


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
import org.checkerframework.checker.units.qual.C;
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
    /*  39 */   private static final Logger log = LoggerFactory.getLogger(WithdrawApiServiceImpl.class);



    @Autowired
     private WithdrawConverter withdrawConverter;



    @Autowired
     private WithdrawMapper withdrawMapper;



    @Autowired
     private AccountApiService accountApiService;




    public WithdrawVo queryWithdraw(WithdrawQueryDTO withdrawQueryDTO) {
        /*  55 */
        List<WithdrawVo> withdrawVoList = queryWithdrawList(withdrawQueryDTO);
        /*  56 */
        return ObjectUtil.isEmpty(withdrawVoList) ? null : withdrawVoList.get(0);

    }












    public List<WithdrawVo> queryWithdrawList(WithdrawQueryDTO withdrawQueryDTO) {
        /*  69 */
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
        /*  70 */
        List<WithdrawVo> withdrawVoList = this.withdrawConverter.toWithdrawVoList(withdrawList);
        /*  71 */
        return withdrawVoList;

    }



    public PageVo<WithdrawVo> queryPage(WithdrawQueryPageDTO withdrawQueryPageDTO) {
        /*  75 */
        Page page = new Page(withdrawQueryPageDTO.getPageStart().intValue(), withdrawQueryPageDTO.getPageSize().intValue(), true);
        /*  76 */
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

        /*  77 */
        /*  88 */
        IPage<WithdrawDO> withdrawPage = this.withdrawMapper.selectPage((IPage) page, wrapper);
        /*  89 */
        List<WithdrawVo> withdrawVoList = this.withdrawConverter.toWithdrawVoList(withdrawPage.getRecords());
        /*  90 */
        return PageVo.<WithdrawVo>builder()
/*  91 */.pageStart(withdrawQueryPageDTO.getPageStart())
/*  92 */.pageSize(withdrawQueryPageDTO.getPageSize())
/*  93 */.total(Long.valueOf(withdrawPage.getTotal()))
/*  94 */.data(withdrawVoList)
/*  95 */.build();

    }






    public PageVo<WithdrawVo> queryProxyPage(ProxyWithdrawQueryPageDTO proxyWithdrawQueryPageDTO) {
        /* 102 */
        AccountQueryProxyTeamsDTO accountQueryProxyTeamsDTO = AccountQueryProxyTeamsDTO.builder().mngUserId(proxyWithdrawQueryPageDTO.getCurrentUserId()).build();
        /* 103 */
        List<Long> accountIdList = this.accountApiService.queryProxyTeams(accountQueryProxyTeamsDTO);
        /* 104 */
        if (ObjectUtil.isEmpty(accountIdList)) {
            /* 105 */
            return PageVo.<WithdrawVo>builder()
/* 106 */.pageStart(proxyWithdrawQueryPageDTO.getPageStart())
/* 107 */.pageSize(proxyWithdrawQueryPageDTO.getPageSize())
/* 108 */.total(CommonConst.LZERO)
/* 109 */.data(Collections.emptyList())
/* 110 */.build();

        }
        /* 112 */
        WithdrawQueryPageDTO withdrawQueryPageDTO = this.withdrawConverter.toWithdrawQueryPageDTO(proxyWithdrawQueryPageDTO);
        /* 113 */
        withdrawQueryPageDTO.setAccountIdList(accountIdList);
        /* 114 */
        return queryPage(withdrawQueryPageDTO);

    }



    public WithdrawVo pass(WithdrawPassDTO withdrawPassDTO) {
        /* 118 */
        WithdrawVo withdrawVo = queryWithdraw(((WithdrawQueryDTO.WithdrawQueryDTOBuilder) WithdrawQueryDTO.builder().id(withdrawPassDTO.getId())).build());
        /* 119 */
        if (ObjectUtil.isEmpty(withdrawVo)) {
            /* 120 */
            throw new BusinessException("提现信息不存在.");

        }






        /* 128 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(withdrawVo.getAccountId())).amountType(AmountTypeEnum.SUBTOTAL_SUBFREEZE.getType()).operateAmount(withdrawVo.getTotalAmount()).bizId(withdrawVo.getId()).streamTypeEnum(StreamTypeEnum.WITHDRAW_PASS).build();
        /* 129 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);





        /* 135 */
        boolean rs = ChainWrappers.lambdaUpdateChain(withdrawMapper)
                .set(WithdrawDO::getStatus, WithdrawStatusEnum.EXAMINEPASS.getStatus())
                .eq(BaseDO::getId, withdrawVo.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /* 136 */
        if (!rs) {
            /* 137 */
            throw new BusinessException("更新状态失败.");

        }
        /* 139 */
        return queryWithdraw(((WithdrawQueryDTO.WithdrawQueryDTOBuilder) WithdrawQueryDTO.builder().id(withdrawPassDTO.getId())).build());

    }





    public Boolean cancel(WithdrawCancelDTO withdrawCancelDTO) {
        /* 145 */
        WithdrawQueryDTO withdrawQueryDTO = ((WithdrawQueryDTO.WithdrawQueryDTOBuilder) WithdrawQueryDTO.builder().id(withdrawCancelDTO.getId())).build();
        /* 146 */
        WithdrawVo withdrawVo = queryWithdraw(withdrawQueryDTO);
        /* 147 */
        if (ObjectUtil.isEmpty(withdrawVo)) {
            /* 148 */
            throw new BusinessException("提现信息不存在.");

        }







        /* 157 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(withdrawVo.getAccountId())).operateAmount(withdrawVo.getTotalAmount()).amountType(AmountTypeEnum.ADDAVAILABLE_SUBFREEZE.getType()).bizId(withdrawVo.getId()).streamTypeEnum(StreamTypeEnum.WITHDRAW_UNLOCK).build();
        /* 158 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);





        /* 164 */
        WithdrawSaveOrUpdateDTO withdrawSaveOrUpdateDTO = ((WithdrawSaveOrUpdateDTO.WithdrawSaveOrUpdateDTOBuilder) WithdrawSaveOrUpdateDTO.builder().id(withdrawVo.getId())).status(WithdrawStatusEnum.TIMEOUT.getStatus()).build();
        /* 165 */
        saveOrUpdate(withdrawSaveOrUpdateDTO);
        /* 166 */
        return Boolean.TRUE;

    }



    public Boolean delete(WithdrawDeleteDTO withdrawDeleteDTO) {
        /* 170 */
        return ChainWrappers.lambdaUpdateChain(withdrawMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, withdrawDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 174 */.update();

    }



    public WithdrawVo saveOrUpdate(WithdrawSaveOrUpdateDTO withdrawSaveOrUpdateDTO) {
        /* 178 */
        if (ObjectUtil.isEmpty(withdrawSaveOrUpdateDTO.getId())) {

            /* 180 */
            WithdrawDO withdrawDO = this.withdrawConverter.toWithdrawDO(withdrawSaveOrUpdateDTO);
            /* 181 */
            withdrawDO.setStatus(WithdrawStatusEnum.EXAMINEING.getStatus());
            /* 182 */
            if (ObjectUtil.isNotEmpty(withdrawSaveOrUpdateDTO.getWalletCode())) {
                /* 183 */
                withdrawDO.setWalletName(WalletChainEnum.queryName(withdrawSaveOrUpdateDTO.getWalletCode()));

            }
            /* 185 */
            if (this.withdrawMapper.insert(withdrawDO) <= 0) {
                /* 186 */
                throw new BusinessException("保存提现信息失败.");

            }
            /* 188 */
            return queryWithdraw(((WithdrawQueryDTO.WithdrawQueryDTOBuilder) WithdrawQueryDTO.builder().id(withdrawDO.getId())).build());

        }




        /* 194 */
        boolean rs = ChainWrappers.lambdaUpdateChain(withdrawMapper)
                .set(ObjectUtil.isNotEmpty(withdrawSaveOrUpdateDTO.getStatus()), WithdrawDO::getStatus, withdrawSaveOrUpdateDTO.getStatus())
                .eq(BaseDO::getId, withdrawSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /* 195 */
        if (!rs) {
            /* 196 */
            throw new BusinessException("更新提现信息失败.");

        }
        /* 198 */
        return queryWithdraw(((WithdrawQueryDTO.WithdrawQueryDTOBuilder) WithdrawQueryDTO.builder().id(withdrawSaveOrUpdateDTO.getId())).build());

    }

}
