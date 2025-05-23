package com.porn.service.account.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.account.api.AccountWalletService;
import com.porn.client.account.dto.AccountWalletDeleteDTO;
import com.porn.client.account.dto.AccountWalletQueryDTO;
import com.porn.client.account.dto.AccountWalletQueryPageDTO;
import com.porn.client.account.dto.AccountWalletSaveOrUpdateDTO;
import com.porn.client.account.vo.AccountWalletVo;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.plan.api.PlanInsApiService;
import com.porn.client.wallet.enums.WalletChainEnum;
import com.porn.service.account.converter.AccountWalletConverter;
import com.porn.service.account.dao.entity.AccountWalletDO;
import com.porn.service.account.dao.mapper.AccountWalletMapper;
import com.porn.service.common.entity.BaseDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service

@Transactional(rollbackFor = {Exception.class})
public class AccountWalletServiceImpl implements AccountWalletService {
    private static final Logger log = LoggerFactory.getLogger(AccountWalletServiceImpl.class);


    @Autowired
    private AccountWalletConverter accountWalletConverter;


    @Autowired
    private AccountWalletMapper accountWalletMapper;


    @Autowired
    private PlanInsApiService planInsApiService;


    public AccountWalletVo queryAccountWallet(AccountWalletQueryDTO accountWalletQueryDTO) {

        List<AccountWalletVo> accountWalletVoList = queryAccountWalletList(accountWalletQueryDTO);

        return ObjectUtil.isEmpty(accountWalletVoList) ? null : accountWalletVoList.get(0);

    }

    public List<AccountWalletVo> queryAccountWalletList(AccountWalletQueryDTO dto) {
        LambdaQueryChainWrapper<AccountWalletDO> query = ChainWrappers.lambdaQueryChain(accountWalletMapper)
                .eq(ObjectUtil.isNotEmpty(dto.getId()), BaseDO::getId, dto.getId())
                .eq(ObjectUtil.isNotEmpty(dto.getAccountId()), AccountWalletDO::getAccountId, dto.getAccountId())
                .eq(ObjectUtil.isNotEmpty(dto.getWalletCode()), AccountWalletDO::getWalletCode, dto.getWalletCode())
                .eq(ObjectUtil.isNotEmpty(dto.getAddress()), AccountWalletDO::getAddress, dto.getAddress())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

        List<AccountWalletDO> accountWalletDOList = query.list();
        if (ObjectUtil.isEmpty(accountWalletDOList)) {
            return Collections.emptyList();

        }
        List<AccountWalletVo> accountWalletVoList = this.accountWalletConverter.toAccountWalletVoList(accountWalletDOList);
        return accountWalletVoList;

    }


    public PageVo<AccountWalletVo> queryPage(AccountWalletQueryPageDTO dto) {

        Page page = new Page(dto.getPageStart().intValue(), dto.getPageSize().intValue(), true);
        LambdaQueryChainWrapper<AccountWalletDO> queryWrapper = ChainWrappers.lambdaQueryChain(accountWalletMapper)
                .eq(ObjectUtil.isNotEmpty(dto.getAccountId()), AccountWalletDO::getAccountId, dto.getAccountId())
                .eq(ObjectUtil.isNotEmpty(dto.getWalletCode()), AccountWalletDO::getWalletCode, dto.getWalletCode())
                .eq(ObjectUtil.isNotEmpty(dto.getAddress()), AccountWalletDO::getAddress, dto.getAddress())
                .like(ObjectUtil.isNotEmpty(dto.getLkAddress()), AccountWalletDO::getAddress, dto.getLkAddress())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        IPage<AccountWalletDO> accountWalletPage = this.accountWalletMapper.selectPage((IPage) page, (Wrapper) queryWrapper);

        List<AccountWalletVo> accountWalletVoList = this.accountWalletConverter.toAccountWalletVoList(accountWalletPage.getRecords());

        return PageVo.<AccountWalletVo>builder()
                .pageStart(dto.getPageStart())
                .pageSize(dto.getPageSize())
                .total(Long.valueOf(accountWalletPage.getTotal()))
                .data(accountWalletVoList)
                .build();
    }


    public AccountWalletVo saveOrUpdate(AccountWalletSaveOrUpdateDTO accountWalletSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(accountWalletSaveOrUpdateDTO.getId())) {


            AccountWalletVo accountWalletVo = queryAccountWallet(AccountWalletQueryDTO.builder().accountId(accountWalletSaveOrUpdateDTO.getAccountId()).walletCode(accountWalletSaveOrUpdateDTO.getWalletCode()).build());

            if (ObjectUtil.isNotEmpty(accountWalletVo)) {

                delete(((AccountWalletDeleteDTO.AccountWalletDeleteDTOBuilder) AccountWalletDeleteDTO.builder().id(accountWalletVo.getId())).build());

            }


            AccountWalletDO accountWalletDO = this.accountWalletConverter.toAccountWalletDO(accountWalletSaveOrUpdateDTO);

            if (ObjectUtil.isEmpty(accountWalletDO.getWalletName())) {

                accountWalletDO.setWalletName(WalletChainEnum.queryName(accountWalletDO.getWalletCode()));

            }

            if (this.accountWalletMapper.insert(accountWalletDO) <= 0) {

                throw new BusinessException("保存地址信息失败.");

            }

            return queryAccountWallet(((AccountWalletQueryDTO.AccountWalletQueryDTOBuilder) AccountWalletQueryDTO.builder().id(accountWalletDO.getId())).build());

        }


//        boolean rs = ((LambdaUpdateChainWrapper) ((LambdaUpdateChainWrapper) ChainWrappers.lambdaUpdateChain((BaseMapper) this.accountWalletMapper).set(ObjectUtil.isNotEmpty(accountWalletSaveOrUpdateDTO.getAccountId()), AccountWalletDO::getAccountId, accountWalletSaveOrUpdateDTO.getAccountId()).set(ObjectUtil.isNotEmpty(accountWalletSaveOrUpdateDTO.getWalletCode()), AccountWalletDO::getWalletCode, accountWalletSaveOrUpdateDTO.getWalletCode()).set(ObjectUtil.isNotEmpty(accountWalletSaveOrUpdateDTO.getAddress()), AccountWalletDO::getAddress, accountWalletSaveOrUpdateDTO.getAddress()).set(ObjectUtil.isNotEmpty(accountWalletSaveOrUpdateDTO.getWalletCode()), AccountWalletDO::getWalletName, WalletChainEnum.queryName(accountWalletSaveOrUpdateDTO.getWalletCode())).eq(BaseDO::getId, accountWalletSaveOrUpdateDTO.getId())).eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())).update();
        boolean rs = ChainWrappers.lambdaUpdateChain(accountWalletMapper)
                .set(ObjectUtil.isNotEmpty(accountWalletSaveOrUpdateDTO.getAccountId()), AccountWalletDO::getAccountId, accountWalletSaveOrUpdateDTO.getAccountId())
                .set(ObjectUtil.isNotEmpty(accountWalletSaveOrUpdateDTO.getWalletCode()), AccountWalletDO::getWalletCode, accountWalletSaveOrUpdateDTO.getWalletCode())
                .set(ObjectUtil.isNotEmpty(accountWalletSaveOrUpdateDTO.getAddress()), AccountWalletDO::getAddress, accountWalletSaveOrUpdateDTO.getAddress())
                .eq(BaseDO::getId, accountWalletSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();


        if (!rs) {

            throw new BusinessException("更新钱包信息失败.");

        }

        return queryAccountWallet(((AccountWalletQueryDTO.AccountWalletQueryDTOBuilder) AccountWalletQueryDTO.builder().id(accountWalletSaveOrUpdateDTO.getId())).build());

    }


    public boolean delete(AccountWalletDeleteDTO dto) {

        return ChainWrappers.lambdaUpdateChain(accountWalletMapper)
                .eq(ObjectUtil.isNotEmpty(dto.getId()), BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

}

