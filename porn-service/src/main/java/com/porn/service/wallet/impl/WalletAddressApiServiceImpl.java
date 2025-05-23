package com.porn.service.wallet.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.user.api.UserApiService;
import com.porn.client.user.dto.UserValidatePwdDTO;
import com.porn.client.wallet.api.WalletAddressApiService;
import com.porn.client.wallet.dto.*;
import com.porn.client.wallet.enums.AddressStatusEnum;
import com.porn.client.wallet.enums.WalletChainEnum;
import com.porn.client.wallet.vo.WalletAddressVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.wallet.converter.WalletAddressConverter;
import com.porn.service.wallet.dao.entity.WalletAddressDO;
import com.porn.service.wallet.dao.mapper.WalletAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service

@Transactional(rollbackFor = {Exception.class})
public class WalletAddressApiServiceImpl implements WalletAddressApiService {
    private static final Logger log = LoggerFactory.getLogger(WalletAddressApiServiceImpl.class);


    @Autowired
    private WalletAddressMapper walletAddressMapper;


    @Autowired
    private WalletAddressConverter walletAddressConverter;


    @Autowired
    private DingdingMsgSender dingdingMsgSender;

    @Autowired
    private UserApiService userApiService;

    public WalletAddressVo queryWalletAddress(WalletAddressQueryDTO walletAddressQueryDTO) {

        List<WalletAddressVo> walletAddressVoList = queryWalletAddressList(walletAddressQueryDTO);

        return ObjectUtil.isEmpty(walletAddressVoList) ? null : walletAddressVoList.get(0);

    }


    public List<WalletAddressVo> queryWalletAddressList(WalletAddressQueryDTO walletAddressQueryDTO) {

        List<WalletAddressDO> walletAddressDOList = ChainWrappers.lambdaQueryChain(walletAddressMapper)
                .eq(ObjectUtil.isNotEmpty(walletAddressQueryDTO.getId()), BaseDO::getId, walletAddressQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(walletAddressQueryDTO.getName()), WalletAddressDO::getName, walletAddressQueryDTO.getName())
                .eq(ObjectUtil.isNotEmpty(walletAddressQueryDTO.getCode()), WalletAddressDO::getCode, walletAddressQueryDTO.getCode())
                .eq(ObjectUtil.isNotEmpty(walletAddressQueryDTO.getAddress()), WalletAddressDO::getAddress, walletAddressQueryDTO.getAddress())
                .eq(ObjectUtil.isNotEmpty(walletAddressQueryDTO.getAddressStatus()), WalletAddressDO::getAddressStatus, walletAddressQueryDTO.getAddressStatus())
                .eq(ObjectUtil.isNotEmpty(walletAddressQueryDTO.getStatus()), WalletAddressDO::getStatus, walletAddressQueryDTO.getStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();

        return ObjectUtil.isEmpty(walletAddressDOList) ? Collections.<WalletAddressVo>emptyList() : this.walletAddressConverter.toWalletAddressVoList(walletAddressDOList);

    }


    public PageVo<WalletAddressVo> queryPage(WalletAddressQueryPageDTO walletAddressQueryPageDTO) {

        Page page = new Page(walletAddressQueryPageDTO.getPageStart().intValue(), walletAddressQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper<WalletAddressDO> wrapper = new LambdaQueryWrapper();
        wrapper.like(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getName()), WalletAddressDO::getName, walletAddressQueryPageDTO.getName());
        wrapper.like(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getLkName()), WalletAddressDO::getName, walletAddressQueryPageDTO.getLkName());
        wrapper.like(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getCode()), WalletAddressDO::getCode, walletAddressQueryPageDTO.getCode());
        wrapper.like(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getLkCode()), WalletAddressDO::getCode, walletAddressQueryPageDTO.getLkCode());
        wrapper.like(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getLkAddress()), WalletAddressDO::getAddress, walletAddressQueryPageDTO.getLkAddress());
        wrapper.eq(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getStatus()), WalletAddressDO::getStatus, walletAddressQueryPageDTO.getStatus());
        wrapper.eq(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getAddressStatus()), WalletAddressDO::getAddressStatus, walletAddressQueryPageDTO.getAddressStatus());
        wrapper.like(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getLkRemark()), WalletAddressDO::getRemark, walletAddressQueryPageDTO.getLkRemark());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(BaseDO::getCreateTime);


        IPage<WalletAddressDO> walletAddressPage = this.walletAddressMapper.selectPage((IPage) page, wrapper);

        List<WalletAddressVo> walletAddressVoList = this.walletAddressConverter.toWalletAddressVoList(walletAddressPage.getRecords());

        return PageVo.<WalletAddressVo>builder()
                .pageStart(walletAddressQueryPageDTO.getPageStart())
                .pageSize(walletAddressQueryPageDTO.getPageSize())
                .total(Long.valueOf(walletAddressPage.getTotal()))
                .data(walletAddressVoList)
                .build();

    }

    public Boolean enableOrDisable(WalletAddressEnableOrDisableDTO walletAddressEnableOrDisableDTO) {

        WalletAddressVo walletAddressVo = queryWalletAddress(((WalletAddressQueryDTO.WalletAddressQueryDTOBuilder) WalletAddressQueryDTO.builder().id(walletAddressEnableOrDisableDTO.getId())).build());

        UserValidatePwdDTO userValidatePwdDTO = ((UserValidatePwdDTO.UserValidatePwdDTOBuilder) UserValidatePwdDTO.builder().currentUserId(walletAddressEnableOrDisableDTO.getCurrentUserId())).password(walletAddressEnableOrDisableDTO.getPassword()).build();

        if (!this.userApiService.validatePwd(userValidatePwdDTO)) {

            this.dingdingMsgSender.sendMsg("IP[" + walletAddressEnableOrDisableDTO.getRemoteIP() + "], " + (EnableStatusEnum.ENABLE.getStatus().equals(walletAddressEnableOrDisableDTO.getStatus()) ? "禁用" : "启用") + ", 密码校验不通过.");

            throw new BusinessException("密码错误.");

        }

        if (ObjectUtil.isNotEmpty(walletAddressVo)) {

            this.dingdingMsgSender.sendMsg("地址[" + walletAddressVo.getName() + "], 已[" + (EnableStatusEnum.ENABLE.getStatus().equals(walletAddressEnableOrDisableDTO.getStatus()) ? "禁用" : "启用") + "], 请核对.");

        }

        return ChainWrappers.lambdaUpdateChain(walletAddressMapper)
                .set(WalletAddressDO::getStatus, EnableStatusEnum.ENABLE.getStatus().equals(walletAddressEnableOrDisableDTO.getStatus()) ? EnableStatusEnum.DISABLED.getStatus() : EnableStatusEnum.ENABLE.getStatus())
                .eq(BaseDO::getId, walletAddressEnableOrDisableDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }

    public WalletAddressVo saveOrUpdate(WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO) {

        if (ObjectUtil.isNotEmpty(walletAddressSaveOrUpdateDTO.getPassword())) {

            UserValidatePwdDTO userValidatePwdDTO = ((UserValidatePwdDTO.UserValidatePwdDTOBuilder) UserValidatePwdDTO.builder().currentUserId(walletAddressSaveOrUpdateDTO.getCurrentUserId())).password(walletAddressSaveOrUpdateDTO.getPassword()).build();

            if (!this.userApiService.validatePwd(userValidatePwdDTO)) {

                this.dingdingMsgSender.sendMsg("IP[" + walletAddressSaveOrUpdateDTO.getRemoteIP() + "], " + (ObjectUtil.isEmpty(walletAddressSaveOrUpdateDTO.getId()) ? "新增" : "修改") + ", 密码校验不通过.");

                throw new BusinessException("密码错误.");

            }

        }

        if (ObjectUtil.isEmpty(walletAddressSaveOrUpdateDTO.getId())) {


            WalletAddressDO walletAddressDO1 = ChainWrappers.lambdaQueryChain(walletAddressMapper)
                    .eq(WalletAddressDO::getAddress, walletAddressSaveOrUpdateDTO.getAddress())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .one();

            if (ObjectUtil.isNotEmpty(walletAddressDO1)) {

                throw new BusinessException("地址[" + walletAddressSaveOrUpdateDTO.getAddress() + "]已存在.");

            }

            walletAddressDO1 = WalletAddressDO.builder().name((String) ObjectUtil.defaultIfNull(WalletChainEnum.queryName(walletAddressSaveOrUpdateDTO.getCode()), "")).code((String) ObjectUtil.defaultIfNull(walletAddressSaveOrUpdateDTO.getCode(), "")).address(walletAddressSaveOrUpdateDTO.getAddress()).addressStatus(AddressStatusEnum.NORMAL.getStatus()).status((Integer) ObjectUtil.defaultIfNull(walletAddressSaveOrUpdateDTO.getStatus(), EnableStatusEnum.ENABLE.getStatus())).version(CommonConst.LZERO).build();

            if (this.walletAddressMapper.insert(walletAddressDO1) <= 0) {

                throw new BusinessException("保存地址信息失败.");

            }

            this.dingdingMsgSender.sendMsg("新增地址[" + walletAddressDO1.getName() + "], 请核对.");

            return queryWalletAddress(((WalletAddressQueryDTO.WalletAddressQueryDTOBuilder) WalletAddressQueryDTO.builder().id(walletAddressDO1.getId())).build());

        }


        WalletAddressDO walletAddressDO = ChainWrappers.lambdaQueryChain(walletAddressMapper)
                .eq(BaseDO::getId, walletAddressSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();

        if (ObjectUtil.isEmpty(walletAddressDO)) {

            throw new BusinessException("地址不存在.");

        }

        if (!walletAddressDO.getAddress().equals(walletAddressSaveOrUpdateDTO.getAddress())) {


            WalletAddressDO dbWalletAddressDO = ChainWrappers.lambdaQueryChain(walletAddressMapper)
                    .eq(WalletAddressDO::getAddress, walletAddressSaveOrUpdateDTO.getAddress())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .one();

            if (ObjectUtil.isNotEmpty(dbWalletAddressDO)) {

                throw new BusinessException("地址[" + walletAddressSaveOrUpdateDTO.getAddress() + "]已存在.");

            }

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(walletAddressMapper)
                .set(ObjectUtil.isNotEmpty(walletAddressSaveOrUpdateDTO.getCode()), WalletAddressDO::getName, WalletChainEnum.queryName(walletAddressSaveOrUpdateDTO.getCode()))
                .set(ObjectUtil.isNotEmpty(walletAddressSaveOrUpdateDTO.getCode()), WalletAddressDO::getCode, walletAddressSaveOrUpdateDTO.getCode())
                .set(ObjectUtil.isNotEmpty(walletAddressSaveOrUpdateDTO.getAddress()), WalletAddressDO::getAddress, walletAddressSaveOrUpdateDTO.getAddress())
                .set(ObjectUtil.isNotEmpty(walletAddressSaveOrUpdateDTO.getStatus()), WalletAddressDO::getStatus, ObjectUtil.defaultIfNull(walletAddressSaveOrUpdateDTO.getStatus(), EnableStatusEnum.ENABLE.getStatus()))
                .set(ObjectUtil.isNotEmpty(walletAddressSaveOrUpdateDTO.getAddressStatus()), WalletAddressDO::getAddressStatus, walletAddressSaveOrUpdateDTO.getAddressStatus())
                .set(ObjectUtil.isNotEmpty(walletAddressSaveOrUpdateDTO.getLockTime()), WalletAddressDO::getLockTime, walletAddressSaveOrUpdateDTO.getLockTime())
                .set(walletAddressSaveOrUpdateDTO.isClearLockTime(), WalletAddressDO::getLockTime, null).eq(BaseDO::getId, walletAddressSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新用户信息失败.");

        }


        return queryWalletAddress(((WalletAddressQueryDTO.WalletAddressQueryDTOBuilder) WalletAddressQueryDTO.builder().id(walletAddressDO.getId())).build());

    }


    public Boolean delete(WalletAddressDeleteDTO walletAddressDeleteDTO) {

        WalletAddressVo walletAddressVo = queryWalletAddress(((WalletAddressQueryDTO.WalletAddressQueryDTOBuilder) WalletAddressQueryDTO.builder().id(walletAddressDeleteDTO.getId())).build());

        if (ObjectUtil.isEmpty(walletAddressDeleteDTO.getPassword())) {

            return Boolean.FALSE;

        }

        UserValidatePwdDTO userValidatePwdDTO = ((UserValidatePwdDTO.UserValidatePwdDTOBuilder) UserValidatePwdDTO.builder().currentUserId(walletAddressDeleteDTO.getCurrentUserId())).password(walletAddressDeleteDTO.getPassword()).build();

        if (!this.userApiService.validatePwd(userValidatePwdDTO)) {

            this.dingdingMsgSender.sendMsg("IP[" + walletAddressDeleteDTO.getRemoteIP() + "], " + "删除" + ", 密码校验不通过.");

            throw new BusinessException("密码错误.");

        }

        if (ObjectUtil.isNotEmpty(walletAddressVo)) {

            this.dingdingMsgSender.sendMsg("地址[" + walletAddressVo.getName() + "], 被删除, 请核对.");

        }

        return ChainWrappers.lambdaUpdateChain(walletAddressMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, walletAddressDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }


    public Boolean updateRemark(WalletAddressUpdateRemarkDTO walletAddressUpdateRemarkDTO) {

        WalletAddressQueryDTO walletAddressQueryDTO = ((WalletAddressQueryDTO.WalletAddressQueryDTOBuilder) WalletAddressQueryDTO.builder().id(walletAddressUpdateRemarkDTO.getId())).build();

        WalletAddressVo walletAddressVo = queryWalletAddress(walletAddressQueryDTO);

        if (ObjectUtil.isEmpty(walletAddressUpdateRemarkDTO.getPassword())) {

            return Boolean.FALSE;

        }

        UserValidatePwdDTO userValidatePwdDTO = ((UserValidatePwdDTO.UserValidatePwdDTOBuilder) UserValidatePwdDTO.builder().currentUserId(walletAddressUpdateRemarkDTO.getCurrentUserId())).password(walletAddressUpdateRemarkDTO.getPassword()).build();

        if (!this.userApiService.validatePwd(userValidatePwdDTO)) {

            this.dingdingMsgSender.sendMsg("IP[" + walletAddressUpdateRemarkDTO.getRemoteIP() + "], 更新备注, 密码校验不通过.");

            throw new BusinessException("密码错误.");

        }

        return ChainWrappers.lambdaUpdateChain(walletAddressMapper)
                .set(WalletAddressDO::getRemark, walletAddressUpdateRemarkDTO.getRemark())
                .eq(BaseDO::getId, walletAddressUpdateRemarkDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }


    public List<String> queryWalletChainList(WalletChainQueryDTO walletChainQueryDTO) {

        return this.walletAddressMapper.queryWalletChainList(walletChainQueryDTO);

    }

}
