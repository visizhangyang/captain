
package com.porn.service.wallet.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
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
    /*  37 */   private static final Logger log = LoggerFactory.getLogger(WalletAddressApiServiceImpl.class);



    @Autowired
     private WalletAddressMapper walletAddressMapper;



    @Autowired
     private WalletAddressConverter walletAddressConverter;



    @Autowired
     private DingdingMsgSender dingdingMsgSender;


    @Autowired
     private UserApiService userApiService;




    public WalletAddressVo queryWalletAddress(WalletAddressQueryDTO walletAddressQueryDTO) {
        /*  56 */
        List<WalletAddressVo> walletAddressVoList = queryWalletAddressList(walletAddressQueryDTO);
        /*  57 */
        return ObjectUtil.isEmpty(walletAddressVoList) ? null : walletAddressVoList.get(0);

    }











    public List<WalletAddressVo> queryWalletAddressList(WalletAddressQueryDTO walletAddressQueryDTO) {
        /*  69 */
        List<WalletAddressDO> walletAddressDOList = ChainWrappers.lambdaQueryChain(walletAddressMapper)
                .eq(ObjectUtil.isNotEmpty(walletAddressQueryDTO.getId()), BaseDO::getId, walletAddressQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(walletAddressQueryDTO.getName()), WalletAddressDO::getName, walletAddressQueryDTO.getName())
                .eq(ObjectUtil.isNotEmpty(walletAddressQueryDTO.getCode()), WalletAddressDO::getCode, walletAddressQueryDTO.getCode())
                .eq(ObjectUtil.isNotEmpty(walletAddressQueryDTO.getAddress()), WalletAddressDO::getAddress, walletAddressQueryDTO.getAddress())
                .eq(ObjectUtil.isNotEmpty(walletAddressQueryDTO.getAddressStatus()), WalletAddressDO::getAddressStatus, walletAddressQueryDTO.getAddressStatus())
                .eq(ObjectUtil.isNotEmpty(walletAddressQueryDTO.getStatus()), WalletAddressDO::getStatus, walletAddressQueryDTO.getStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();
        /*  70 */
        return ObjectUtil.isEmpty(walletAddressDOList) ? Collections.<WalletAddressVo>emptyList() : this.walletAddressConverter.toWalletAddressVoList(walletAddressDOList);

    }



    public PageVo<WalletAddressVo> queryPage(WalletAddressQueryPageDTO walletAddressQueryPageDTO) {
        /*  74 */
        Page page = new Page(walletAddressQueryPageDTO.getPageStart().intValue(), walletAddressQueryPageDTO.getPageSize().intValue(), true);
        /*  75 */
        LambdaQueryChainWrapper lambdaQueryChainWrapper = ChainWrappers.lambdaQueryChain(walletAddressMapper)
                .like(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getName()), WalletAddressDO::getName, walletAddressQueryPageDTO.getName())
        /*  78 */.like(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getLkName()), WalletAddressDO::getName, walletAddressQueryPageDTO.getLkName())
        /*  79 */.like(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getCode()), WalletAddressDO::getCode, walletAddressQueryPageDTO.getCode())
        /*  80 */.like(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getLkCode()), WalletAddressDO::getCode, walletAddressQueryPageDTO.getLkCode())
        /*  81 */.like(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getLkAddress()), WalletAddressDO::getAddress, walletAddressQueryPageDTO.getLkAddress())
        /*  82 */.eq(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getStatus()), WalletAddressDO::getStatus, walletAddressQueryPageDTO.getStatus())
        /*  83 */.eq(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getAddressStatus()), WalletAddressDO::getAddressStatus, walletAddressQueryPageDTO.getAddressStatus())
        /*  84 */.like(ObjectUtil.isNotEmpty(walletAddressQueryPageDTO.getLkRemark()), WalletAddressDO::getRemark, walletAddressQueryPageDTO.getLkRemark())
        /*  85 */.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
        /*  86 */.orderByDesc(BaseDO::getCreateTime);
        /*  76 */
        /*  87 */
        IPage<WalletAddressDO> walletAddressPage = this.walletAddressMapper.selectPage((IPage) page, lambdaQueryChainWrapper);
        /*  88 */
        List<WalletAddressVo> walletAddressVoList = this.walletAddressConverter.toWalletAddressVoList(walletAddressPage.getRecords());
        /*  89 */
        return PageVo.<WalletAddressVo>builder()
/*  90 */.pageStart(walletAddressQueryPageDTO.getPageStart())
/*  91 */.pageSize(walletAddressQueryPageDTO.getPageSize())
/*  92 */.total(Long.valueOf(walletAddressPage.getTotal()))
/*  93 */.data(walletAddressVoList)
/*  94 */.build();

    }




    public Boolean enableOrDisable(WalletAddressEnableOrDisableDTO walletAddressEnableOrDisableDTO) {
        /*  99 */
        WalletAddressVo walletAddressVo = queryWalletAddress(((WalletAddressQueryDTO.WalletAddressQueryDTOBuilder) WalletAddressQueryDTO.builder().id(walletAddressEnableOrDisableDTO.getId())).build());



        /* 103 */
        UserValidatePwdDTO userValidatePwdDTO = ((UserValidatePwdDTO.UserValidatePwdDTOBuilder) UserValidatePwdDTO.builder().currentUserId(walletAddressEnableOrDisableDTO.getCurrentUserId())).password(walletAddressEnableOrDisableDTO.getPassword()).build();
        /* 104 */
        if (!this.userApiService.validatePwd(userValidatePwdDTO)) {

            /* 106 */
            this.dingdingMsgSender.sendMsg("IP[" + walletAddressEnableOrDisableDTO.getRemoteIP() + "], " + (EnableStatusEnum.ENABLE.getStatus().equals(walletAddressEnableOrDisableDTO.getStatus()) ? "禁用" : "启用") + ", 密码校验不通过.");
            /* 107 */
            throw new BusinessException("密码错误.");

        }

        /* 110 */
        if (ObjectUtil.isNotEmpty(walletAddressVo)) {
            /* 111 */
            this.dingdingMsgSender.sendMsg("地址[" + walletAddressVo.getName() + "], 已[" + (EnableStatusEnum.ENABLE.getStatus().equals(walletAddressEnableOrDisableDTO.getStatus()) ? "禁用" : "启用") + "], 请核对.");

        }
        /* 113 */
        return  ChainWrappers.lambdaUpdateChain(walletAddressMapper)
                .set(WalletAddressDO::getStatus, EnableStatusEnum.ENABLE.getStatus().equals(walletAddressEnableOrDisableDTO.getStatus()) ? EnableStatusEnum.DISABLED.getStatus() : EnableStatusEnum.ENABLE.getStatus())
                .eq(BaseDO::getId, walletAddressEnableOrDisableDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 117 */.update();

    }




    public WalletAddressVo saveOrUpdate(WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO) {
        /* 122 */
        if (ObjectUtil.isNotEmpty(walletAddressSaveOrUpdateDTO.getPassword())) {



            /* 126 */
            UserValidatePwdDTO userValidatePwdDTO = ((UserValidatePwdDTO.UserValidatePwdDTOBuilder) UserValidatePwdDTO.builder().currentUserId(walletAddressSaveOrUpdateDTO.getCurrentUserId())).password(walletAddressSaveOrUpdateDTO.getPassword()).build();
            /* 127 */
            if (!this.userApiService.validatePwd(userValidatePwdDTO)) {

                /* 129 */
                this.dingdingMsgSender.sendMsg("IP[" + walletAddressSaveOrUpdateDTO.getRemoteIP() + "], " + (ObjectUtil.isEmpty(walletAddressSaveOrUpdateDTO.getId()) ? "新增" : "修改") + ", 密码校验不通过.");
                /* 130 */
                throw new BusinessException("密码错误.");

            }

        }

        /* 134 */
        if (ObjectUtil.isEmpty(walletAddressSaveOrUpdateDTO.getId())) {




            /* 139 */
            WalletAddressDO walletAddressDO1 = ChainWrappers.lambdaQueryChain(walletAddressMapper)
                    .eq(WalletAddressDO::getAddress, walletAddressSaveOrUpdateDTO.getAddress())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .one();
            /* 140 */
            if (ObjectUtil.isNotEmpty(walletAddressDO1)) {
                /* 141 */
                throw new BusinessException("地址[" + walletAddressSaveOrUpdateDTO.getAddress() + "]已存在.");

            }







            /* 150 */
            walletAddressDO1 = WalletAddressDO.builder().name((String) ObjectUtil.defaultIfNull(WalletChainEnum.queryName(walletAddressSaveOrUpdateDTO.getCode()), "")).code((String) ObjectUtil.defaultIfNull(walletAddressSaveOrUpdateDTO.getCode(), "")).address(walletAddressSaveOrUpdateDTO.getAddress()).addressStatus(AddressStatusEnum.NORMAL.getStatus()).status((Integer) ObjectUtil.defaultIfNull(walletAddressSaveOrUpdateDTO.getStatus(), EnableStatusEnum.ENABLE.getStatus())).version(CommonConst.LZERO).build();
            /* 151 */
            if (this.walletAddressMapper.insert(walletAddressDO1) <= 0) {
                /* 152 */
                throw new BusinessException("保存地址信息失败.");

            }
            /* 154 */
            this.dingdingMsgSender.sendMsg("新增地址[" + walletAddressDO1.getName() + "], 请核对.");
            /* 155 */
            return queryWalletAddress(((WalletAddressQueryDTO.WalletAddressQueryDTOBuilder) WalletAddressQueryDTO.builder().id(walletAddressDO1.getId())).build());

        }




        /* 161 */
        WalletAddressDO walletAddressDO =  ChainWrappers.lambdaQueryChain(walletAddressMapper)
                .eq(BaseDO::getId, walletAddressSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();
        /* 162 */
        if (ObjectUtil.isEmpty(walletAddressDO)) {
            /* 163 */
            throw new BusinessException("地址不存在.");

        }

        /* 166 */
        if (!walletAddressDO.getAddress().equals(walletAddressSaveOrUpdateDTO.getAddress())) {




            /* 171 */
            WalletAddressDO dbWalletAddressDO = ChainWrappers.lambdaQueryChain(walletAddressMapper)
                    .eq(WalletAddressDO::getAddress, walletAddressSaveOrUpdateDTO.getAddress())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .one();
            /* 172 */
            if (ObjectUtil.isNotEmpty(dbWalletAddressDO)) {
                /* 173 */
                throw new BusinessException("地址[" + walletAddressSaveOrUpdateDTO.getAddress() + "]已存在.");

            }

        }










        /* 186 */
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
        /* 187 */
        if (!rs) {
            /* 188 */
            throw new BusinessException("更新用户信息失败.");

        }


        /* 192 */
        return queryWalletAddress(((WalletAddressQueryDTO.WalletAddressQueryDTOBuilder) WalletAddressQueryDTO.builder().id(walletAddressDO.getId())).build());

    }





    public Boolean delete(WalletAddressDeleteDTO walletAddressDeleteDTO) {
        /* 198 */
        WalletAddressVo walletAddressVo = queryWalletAddress(((WalletAddressQueryDTO.WalletAddressQueryDTOBuilder) WalletAddressQueryDTO.builder().id(walletAddressDeleteDTO.getId())).build());
        /* 199 */
        if (ObjectUtil.isEmpty(walletAddressDeleteDTO.getPassword())) {
            /* 200 */
            return Boolean.FALSE;

        }



        /* 205 */
        UserValidatePwdDTO userValidatePwdDTO = ((UserValidatePwdDTO.UserValidatePwdDTOBuilder) UserValidatePwdDTO.builder().currentUserId(walletAddressDeleteDTO.getCurrentUserId())).password(walletAddressDeleteDTO.getPassword()).build();
        /* 206 */
        if (!this.userApiService.validatePwd(userValidatePwdDTO)) {

            /* 208 */
            this.dingdingMsgSender.sendMsg("IP[" + walletAddressDeleteDTO.getRemoteIP() + "], " + "删除" + ", 密码校验不通过.");
            /* 209 */
            throw new BusinessException("密码错误.");

        }

        /* 212 */
        if (ObjectUtil.isNotEmpty(walletAddressVo)) {
            /* 213 */
            this.dingdingMsgSender.sendMsg("地址[" + walletAddressVo.getName() + "], 被删除, 请核对.");

        }
        /* 215 */
        return ChainWrappers.lambdaUpdateChain(walletAddressMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, walletAddressDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 219 */.update();

    }





    public Boolean updateRemark(WalletAddressUpdateRemarkDTO walletAddressUpdateRemarkDTO) {
        /* 225 */
        WalletAddressQueryDTO walletAddressQueryDTO = ((WalletAddressQueryDTO.WalletAddressQueryDTOBuilder) WalletAddressQueryDTO.builder().id(walletAddressUpdateRemarkDTO.getId())).build();
        /* 226 */
        WalletAddressVo walletAddressVo = queryWalletAddress(walletAddressQueryDTO);

        /* 228 */
        if (ObjectUtil.isEmpty(walletAddressUpdateRemarkDTO.getPassword())) {
            /* 229 */
            return Boolean.FALSE;

        }



        /* 234 */
        UserValidatePwdDTO userValidatePwdDTO = ((UserValidatePwdDTO.UserValidatePwdDTOBuilder) UserValidatePwdDTO.builder().currentUserId(walletAddressUpdateRemarkDTO.getCurrentUserId())).password(walletAddressUpdateRemarkDTO.getPassword()).build();
        /* 235 */
        if (!this.userApiService.validatePwd(userValidatePwdDTO)) {

            /* 237 */
            this.dingdingMsgSender.sendMsg("IP[" + walletAddressUpdateRemarkDTO.getRemoteIP() + "], 更新备注, 密码校验不通过.");
            /* 238 */
            throw new BusinessException("密码错误.");

        }

        /* 241 */
        return ChainWrappers.lambdaUpdateChain(walletAddressMapper)
                .set(WalletAddressDO::getRemark, walletAddressUpdateRemarkDTO.getRemark())
                .eq(BaseDO::getId, walletAddressUpdateRemarkDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 245 */.update();

    }



    public List<String> queryWalletChainList(WalletChainQueryDTO walletChainQueryDTO) {
        /* 249 */
        return this.walletAddressMapper.queryWalletChainList(walletChainQueryDTO);

    }

}
