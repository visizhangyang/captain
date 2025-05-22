
package com.porn.service.transfer.impl;


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
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.common.vo.PageVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.transfer.api.TransferApiService;
import com.porn.client.transfer.dto.*;
import com.porn.client.transfer.enums.TransferStatusEnum;
import com.porn.client.transfer.vo.TransferVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.transfer.converter.TransferConverter;
import com.porn.service.transfer.dao.entity.TransferDO;
import com.porn.service.transfer.dao.mapper.TransferMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;





































@Service

@Transactional(rollbackFor = {Exception.class})
 public class TransferApiServiceImpl implements TransferApiService {
    /*  40 */   private static final Logger log = LoggerFactory.getLogger(TransferApiServiceImpl.class);



    @Autowired
     private TransferMapper transferMapper;



    @Autowired
     private TransferConverter transferConverter;



    @Autowired
     private AccountApiService accountApiService;





    public TransferVo queryTransfer(TransferQueryDTO transferQueryDTO) {
        /*  57 */
        List<TransferVo> transferVoList = queryTransferList(transferQueryDTO);
        /*  58 */
        return ObjectUtil.isEmpty(transferVoList) ? null : transferVoList.get(0);

    }








    public List<TransferVo> queryTransferList(TransferQueryDTO transferQueryDTO) {
        /*  67 */
        List<TransferDO> transferList =  ChainWrappers.lambdaQueryChain(transferMapper)
                .eq(ObjectUtil.isNotEmpty(transferQueryDTO.getSrcAccountId()), TransferDO::getSrcAccountId, transferQueryDTO.getSrcAccountId())
                .eq(ObjectUtil.isNotEmpty(transferQueryDTO.getId()), BaseDO::getId, transferQueryDTO.getId())
                .ge(ObjectUtil.isNotEmpty(transferQueryDTO.getStartTime()), BaseDO::getCreateTime, transferQueryDTO.getStartTime())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();
        /*  68 */
        if (ObjectUtil.isEmpty(transferList)) {
            /*  69 */
            return Collections.emptyList();

        }
        /*  71 */
        List<TransferVo> transferVoList = this.transferConverter.toTransferVoList(transferList);
        /*  72 */
        Set<Long> accountIdList = new HashSet<>();
        /*  73 */
        transferVoList.forEach(transferVo -> {

            accountIdList.add(transferVo.getSrcAccountId());

            accountIdList.add(transferVo.getDstAccountId());

        });
        /*  77 */
        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().accountIdList(CollUtil.newArrayList(accountIdList)).build());
        /*  78 */
        Map<Long, AccountVo> accountVoMap = (Map<Long, AccountVo>) accountVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));
        /*  79 */
        transferVoList.forEach(transferVo -> {

            if (ObjectUtil.isNotEmpty(accountVoMap.get(transferVo.getSrcAccountId()))) {

                transferVo.setSrcAccountAvatarUrl(((AccountVo) accountVoMap.get(transferVo.getSrcAccountId())).getAvatarUrl());

            }

            if (ObjectUtil.isNotEmpty(accountVoMap.get(transferVo.getDstAccountId()))) {

                transferVo.setDstAccountAvatarUrl(((AccountVo) accountVoMap.get(transferVo.getDstAccountId())).getAvatarUrl());

            }

        });
        /*  87 */
        return transferVoList;

    }



    public PageVo<TransferVo> queryPage(TransferQueryPageDTO transferQueryPageDTO) {
        /*  91 */
        Page page = new Page(transferQueryPageDTO.getPageStart().intValue(), transferQueryPageDTO.getPageSize().intValue(), true);
        LambdaQueryWrapper<TransferDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(transferQueryPageDTO.getSrcAccountId()), TransferDO::getSrcAccountId, transferQueryPageDTO.getSrcAccountId());
        wrapper.eq(ObjectUtil.isNotEmpty(transferQueryPageDTO.getDstAccountId()), TransferDO::getDstAccountId, transferQueryPageDTO.getDstAccountId());
        wrapper.eq(ObjectUtil.isNotEmpty(transferQueryPageDTO.getTransferStatus()), TransferDO::getTransferStatus, transferQueryPageDTO.getTransferStatus());
        wrapper.like(ObjectUtil.isNotEmpty(transferQueryPageDTO.getSrcLkAccountName()), TransferDO::getSrcAccountName, transferQueryPageDTO.getSrcLkAccountName());
        wrapper.like(ObjectUtil.isNotEmpty(transferQueryPageDTO.getDstLkAccountName()), TransferDO::getDstAccountName, transferQueryPageDTO.getDstLkAccountName());
        wrapper.like(ObjectUtil.isNotEmpty(transferQueryPageDTO.getLkSrcAccountRemark()), TransferDO::getSrcAccountRemark, transferQueryPageDTO.getLkSrcAccountRemark());
        wrapper.like(ObjectUtil.isNotEmpty(transferQueryPageDTO.getLkDstAccountRemark()), TransferDO::getDstAccountRemark, transferQueryPageDTO.getLkDstAccountRemark());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(BaseDO::getCreateTime);
        /*  93 */
        /* 103 */
        IPage<TransferDO> transferPage = this.transferMapper.selectPage((IPage) page, wrapper);
        /* 104 */
        List<TransferVo> transferVoList = this.transferConverter.toTransferVoList(transferPage.getRecords());
        /* 105 */
        Set<Long> accountIdList = new HashSet<>();
        /* 106 */
        transferVoList.forEach(transferVo -> {

            accountIdList.add(transferVo.getSrcAccountId());

            accountIdList.add(transferVo.getDstAccountId());

        });
        /* 110 */
        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().accountIdList(CollUtil.newArrayList(accountIdList)).build());
        /* 111 */
        Map<Long, AccountVo> accountVoMap = (Map<Long, AccountVo>) accountVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));
        /* 112 */
        transferVoList.forEach(transferVo -> {

            if (ObjectUtil.isNotEmpty(accountVoMap.get(transferVo.getSrcAccountId()))) {

                transferVo.setSrcAccountAvatarUrl(((AccountVo) accountVoMap.get(transferVo.getSrcAccountId())).getAvatarUrl());

            }

            if (ObjectUtil.isNotEmpty(accountVoMap.get(transferVo.getDstAccountId()))) {

                transferVo.setDstAccountAvatarUrl(((AccountVo) accountVoMap.get(transferVo.getDstAccountId())).getAvatarUrl());

            }

        });
        /* 120 */
        return PageVo.<TransferVo>builder()
/* 121 */.pageStart(transferQueryPageDTO.getPageStart())
/* 122 */.pageSize(transferQueryPageDTO.getPageSize())
/* 123 */.total(Long.valueOf(transferPage.getTotal()))
/* 124 */.data(transferVoList)
/* 125 */.build();

    }



    public TransferVo saveOrUpdate(TransferSaveOrUpdateDTO transferSaveOrUpdateDTO) {
        /* 129 */
        AccountVo srcAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(transferSaveOrUpdateDTO.getSrcAccountId())).build());
        /* 130 */
        AccountVo dstAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(transferSaveOrUpdateDTO.getDstAccountId())).build());
        /* 131 */
        if (ObjectUtil.isEmpty(srcAccountVo) ||
                /* 132 */       ObjectUtil.isEmpty(dstAccountVo)) {
            /* 133 */
            throw new BusinessException("账户信息不存在.");

        }

        /* 136 */
        BigDecimal amount = transferSaveOrUpdateDTO.getAmount();
        /* 137 */
        if (srcAccountVo.getAvailableBalance().compareTo(amount) < 0) {
            /* 138 */
            throw new BusinessException("账户余额不足.");

        }

        /* 141 */
        TransferDO transferDO = this.transferConverter.toTransferDO(transferSaveOrUpdateDTO);
        /* 142 */
        transferDO.setDstAccountId(dstAccountVo.getId());
        /* 143 */
        transferDO.setDstAccountName(dstAccountVo.getName());
        /* 144 */
        transferDO.setTransferStatus(TransferStatusEnum.WAIT_AUDIT.getStatus());
        /* 145 */
        transferDO.setSrcAccountRemark(srcAccountVo.getRemark());
        /* 146 */
        transferDO.setDstAccountRemark(dstAccountVo.getRemark());
        /* 147 */
        if (this.transferMapper.insert(transferDO) <= 0) {
            /* 148 */
            throw new BusinessException("保存转账信息失败.");

        }







        /* 157 */
        AccountAmountOperateDTO srcAccountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(srcAccountVo.getId())).amountType(AmountTypeEnum.SUBAVAILABLE_ADDFREEZE.getType()).operateAmount(transferSaveOrUpdateDTO.getAmount()).bizId(transferDO.getId()).streamTypeEnum(StreamTypeEnum.TRANSFER_WAIT_AUDIT).build();
        /* 158 */
        this.accountApiService.operateAmount(srcAccountAmountOperateDTO);


        /* 161 */
        return queryTransfer(((TransferQueryDTO.TransferQueryDTOBuilder) TransferQueryDTO.builder().id(transferDO.getId())).build());

    }



    public Boolean audit(TransferAuditDTO transferAuditDTO) {
        /* 165 */
        TransferVo transferVo = queryTransfer(((TransferQueryDTO.TransferQueryDTOBuilder) TransferQueryDTO.builder().id(transferAuditDTO.getId())).build());
        /* 166 */
        if (ObjectUtil.isEmpty(transferVo)) {
            /* 167 */
            throw new BusinessException("转账信息不存在.");

        }








        /* 177 */
        AccountAmountOperateDTO srcAccountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(transferVo.getSrcAccountId())).amountType(AmountTypeEnum.SUBTOTAL_SUBFREEZE.getType()).operateAmount(transferVo.getAmount()).bizId(transferVo.getId()).streamTypeEnum(StreamTypeEnum.TRANSFER_SRC).build();
        /* 178 */
        this.accountApiService.operateAmount(srcAccountAmountOperateDTO);








        /* 187 */
        AccountAmountOperateDTO dstAccountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(transferVo.getDstAccountId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).operateAmount(transferVo.getAmount()).bizId(transferVo.getId()).streamTypeEnum(StreamTypeEnum.TRANSFER_DST).build();
        /* 188 */
        this.accountApiService.operateAmount(dstAccountAmountOperateDTO);


        /* 191 */
        return ChainWrappers.lambdaUpdateChain(transferMapper)
                .set(TransferDO::getTransferStatus, TransferStatusEnum.TRANSFER_SUCCESS.getStatus())
                .eq(BaseDO::getId, transferVo.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 195 */.update();

    }



    public Boolean reject(TransferRejectDTO transferRejectDTO) {
        /* 199 */
        TransferVo transferVo = queryTransfer(((TransferQueryDTO.TransferQueryDTOBuilder) TransferQueryDTO.builder().id(transferRejectDTO.getId())).build());
        /* 200 */
        if (ObjectUtil.isEmpty(transferVo)) {
            /* 201 */
            throw new BusinessException("转账信息不存在.");

        }
        /* 203 */
        if (!TransferStatusEnum.WAIT_AUDIT.getStatus().equals(transferVo.getTransferStatus())) {
            /* 204 */
            throw new BusinessException("状态信息不对, 请刷新页面.");

        }







        /* 213 */
        AccountAmountOperateDTO dstAccountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(transferVo.getSrcAccountId())).amountType(AmountTypeEnum.ADDAVAILABLE_SUBFREEZE.getType()).operateAmount(transferVo.getAmount()).bizId(transferVo.getId()).streamTypeEnum(StreamTypeEnum.TRANSFER_REJECT).build();
        /* 214 */
        this.accountApiService.operateAmount(dstAccountAmountOperateDTO);


        /* 217 */
        return ChainWrappers.lambdaUpdateChain(transferMapper)
                .set(TransferDO::getTransferStatus, TransferStatusEnum.REJECT.getStatus())
                .eq(BaseDO::getId, transferVo.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 221 */.update();

    }



    public Boolean delete(TransferDeleteDTO transferDeleteDTO) {
        /* 225 */
        return ChainWrappers.lambdaUpdateChain(transferMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, transferDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 229 */.update();

    }

}

