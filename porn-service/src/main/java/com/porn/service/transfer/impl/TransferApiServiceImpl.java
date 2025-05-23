package com.porn.service.transfer.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
    private static final Logger log = LoggerFactory.getLogger(TransferApiServiceImpl.class);


    @Autowired
    private TransferMapper transferMapper;


    @Autowired
    private TransferConverter transferConverter;


    @Autowired
    private AccountApiService accountApiService;


    public TransferVo queryTransfer(TransferQueryDTO transferQueryDTO) {

        List<TransferVo> transferVoList = queryTransferList(transferQueryDTO);

        return ObjectUtil.isEmpty(transferVoList) ? null : transferVoList.get(0);

    }

    public List<TransferVo> queryTransferList(TransferQueryDTO transferQueryDTO) {

        List<TransferDO> transferList = ChainWrappers.lambdaQueryChain(transferMapper)
                .eq(ObjectUtil.isNotEmpty(transferQueryDTO.getSrcAccountId()), TransferDO::getSrcAccountId, transferQueryDTO.getSrcAccountId())
                .eq(ObjectUtil.isNotEmpty(transferQueryDTO.getId()), BaseDO::getId, transferQueryDTO.getId())
                .ge(ObjectUtil.isNotEmpty(transferQueryDTO.getStartTime()), BaseDO::getCreateTime, transferQueryDTO.getStartTime())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();

        if (ObjectUtil.isEmpty(transferList)) {

            return Collections.emptyList();

        }

        List<TransferVo> transferVoList = this.transferConverter.toTransferVoList(transferList);

        Set<Long> accountIdList = new HashSet<>();

        transferVoList.forEach(transferVo -> {

            accountIdList.add(transferVo.getSrcAccountId());

            accountIdList.add(transferVo.getDstAccountId());

        });

        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().accountIdList(CollUtil.newArrayList(accountIdList)).build());

        Map<Long, AccountVo> accountVoMap = (Map<Long, AccountVo>) accountVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));

        transferVoList.forEach(transferVo -> {

            if (ObjectUtil.isNotEmpty(accountVoMap.get(transferVo.getSrcAccountId()))) {

                transferVo.setSrcAccountAvatarUrl(((AccountVo) accountVoMap.get(transferVo.getSrcAccountId())).getAvatarUrl());

            }

            if (ObjectUtil.isNotEmpty(accountVoMap.get(transferVo.getDstAccountId()))) {

                transferVo.setDstAccountAvatarUrl(((AccountVo) accountVoMap.get(transferVo.getDstAccountId())).getAvatarUrl());

            }

        });

        return transferVoList;

    }


    public PageVo<TransferVo> queryPage(TransferQueryPageDTO transferQueryPageDTO) {

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


        IPage<TransferDO> transferPage = this.transferMapper.selectPage((IPage) page, wrapper);

        List<TransferVo> transferVoList = this.transferConverter.toTransferVoList(transferPage.getRecords());

        Set<Long> accountIdList = new HashSet<>();

        transferVoList.forEach(transferVo -> {

            accountIdList.add(transferVo.getSrcAccountId());

            accountIdList.add(transferVo.getDstAccountId());

        });

        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(AccountQueryDTO.builder().accountIdList(CollUtil.newArrayList(accountIdList)).build());

        Map<Long, AccountVo> accountVoMap = (Map<Long, AccountVo>) accountVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));

        transferVoList.forEach(transferVo -> {

            if (ObjectUtil.isNotEmpty(accountVoMap.get(transferVo.getSrcAccountId()))) {

                transferVo.setSrcAccountAvatarUrl(((AccountVo) accountVoMap.get(transferVo.getSrcAccountId())).getAvatarUrl());

            }

            if (ObjectUtil.isNotEmpty(accountVoMap.get(transferVo.getDstAccountId()))) {

                transferVo.setDstAccountAvatarUrl(((AccountVo) accountVoMap.get(transferVo.getDstAccountId())).getAvatarUrl());

            }

        });

        return PageVo.<TransferVo>builder()
                .pageStart(transferQueryPageDTO.getPageStart())
                .pageSize(transferQueryPageDTO.getPageSize())
                .total(Long.valueOf(transferPage.getTotal()))
                .data(transferVoList)
                .build();

    }


    public TransferVo saveOrUpdate(TransferSaveOrUpdateDTO transferSaveOrUpdateDTO) {

        AccountVo srcAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(transferSaveOrUpdateDTO.getSrcAccountId())).build());

        AccountVo dstAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(transferSaveOrUpdateDTO.getDstAccountId())).build());

        if (ObjectUtil.isEmpty(srcAccountVo) ||
                ObjectUtil.isEmpty(dstAccountVo)) {

            throw new BusinessException("账户信息不存在.");

        }

        BigDecimal amount = transferSaveOrUpdateDTO.getAmount();

        if (srcAccountVo.getAvailableBalance().compareTo(amount) < 0) {

            throw new BusinessException("账户余额不足.");

        }

        TransferDO transferDO = this.transferConverter.toTransferDO(transferSaveOrUpdateDTO);

        transferDO.setDstAccountId(dstAccountVo.getId());

        transferDO.setDstAccountName(dstAccountVo.getName());

        transferDO.setTransferStatus(TransferStatusEnum.WAIT_AUDIT.getStatus());

        transferDO.setSrcAccountRemark(srcAccountVo.getRemark());

        transferDO.setDstAccountRemark(dstAccountVo.getRemark());

        if (this.transferMapper.insert(transferDO) <= 0) {

            throw new BusinessException("保存转账信息失败.");

        }

        AccountAmountOperateDTO srcAccountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(srcAccountVo.getId())).amountType(AmountTypeEnum.SUBAVAILABLE_ADDFREEZE.getType()).operateAmount(transferSaveOrUpdateDTO.getAmount()).bizId(transferDO.getId()).streamTypeEnum(StreamTypeEnum.TRANSFER_WAIT_AUDIT).build();

        this.accountApiService.operateAmount(srcAccountAmountOperateDTO);


        return queryTransfer(((TransferQueryDTO.TransferQueryDTOBuilder) TransferQueryDTO.builder().id(transferDO.getId())).build());

    }


    public Boolean audit(TransferAuditDTO transferAuditDTO) {

        TransferVo transferVo = queryTransfer(((TransferQueryDTO.TransferQueryDTOBuilder) TransferQueryDTO.builder().id(transferAuditDTO.getId())).build());

        if (ObjectUtil.isEmpty(transferVo)) {

            throw new BusinessException("转账信息不存在.");

        }


        AccountAmountOperateDTO srcAccountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(transferVo.getSrcAccountId())).amountType(AmountTypeEnum.SUBTOTAL_SUBFREEZE.getType()).operateAmount(transferVo.getAmount()).bizId(transferVo.getId()).streamTypeEnum(StreamTypeEnum.TRANSFER_SRC).build();

        this.accountApiService.operateAmount(srcAccountAmountOperateDTO);


        AccountAmountOperateDTO dstAccountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(transferVo.getDstAccountId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).operateAmount(transferVo.getAmount()).bizId(transferVo.getId()).streamTypeEnum(StreamTypeEnum.TRANSFER_DST).build();

        this.accountApiService.operateAmount(dstAccountAmountOperateDTO);


        return ChainWrappers.lambdaUpdateChain(transferMapper)
                .set(TransferDO::getTransferStatus, TransferStatusEnum.TRANSFER_SUCCESS.getStatus())
                .eq(BaseDO::getId, transferVo.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }


    public Boolean reject(TransferRejectDTO transferRejectDTO) {

        TransferVo transferVo = queryTransfer(((TransferQueryDTO.TransferQueryDTOBuilder) TransferQueryDTO.builder().id(transferRejectDTO.getId())).build());

        if (ObjectUtil.isEmpty(transferVo)) {

            throw new BusinessException("转账信息不存在.");

        }

        if (!TransferStatusEnum.WAIT_AUDIT.getStatus().equals(transferVo.getTransferStatus())) {

            throw new BusinessException("状态信息不对, 请刷新页面.");

        }

        AccountAmountOperateDTO dstAccountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(transferVo.getSrcAccountId())).amountType(AmountTypeEnum.ADDAVAILABLE_SUBFREEZE.getType()).operateAmount(transferVo.getAmount()).bizId(transferVo.getId()).streamTypeEnum(StreamTypeEnum.TRANSFER_REJECT).build();

        this.accountApiService.operateAmount(dstAccountAmountOperateDTO);


        return ChainWrappers.lambdaUpdateChain(transferMapper)
                .set(TransferDO::getTransferStatus, TransferStatusEnum.REJECT.getStatus())
                .eq(BaseDO::getId, transferVo.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }


    public Boolean delete(TransferDeleteDTO transferDeleteDTO) {

        return ChainWrappers.lambdaUpdateChain(transferMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, transferDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }

}

