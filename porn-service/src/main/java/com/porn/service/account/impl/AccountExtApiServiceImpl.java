package com.porn.service.account.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.account.api.AccountExtApiService;
import com.porn.client.account.dto.AccountExtQueryDTO;
import com.porn.client.account.dto.AccountExtSaveOrUpdateDTO;
import com.porn.client.account.dto.AccountExtQueryDTO.AccountExtQueryDTOBuilder;
import com.porn.client.account.vo.AccountExtVo;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.service.account.converter.AccountExtConverter;
import com.porn.service.account.dao.entity.AccountExtDO;
import com.porn.service.account.dao.mapper.AccountExtMapper;
import com.porn.service.common.entity.BaseDO;
import java.lang.invoke.SerializedLambda;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
   rollbackFor = {Exception.class}
)
public class AccountExtApiServiceImpl implements AccountExtApiService {
   private static final Logger log = LoggerFactory.getLogger(AccountExtApiServiceImpl.class);
   @Autowired
   private AccountExtConverter accountExtConverter;
   @Autowired
   private AccountExtMapper accountExtMapper;

   public AccountExtVo queryAccountExt(AccountExtQueryDTO accountExtQueryDTO) {
      List<AccountExtVo> accountExtVoList = this.queryAccountExtList(accountExtQueryDTO);
      return ObjectUtil.isEmpty(accountExtVoList) ? null : (AccountExtVo)accountExtVoList.get(0);
   }

   public List<AccountExtVo> queryAccountExtList(AccountExtQueryDTO dto) {
      LambdaQueryChainWrapper<AccountExtDO> query = ChainWrappers.lambdaQueryChain(accountExtMapper)
              .eq(ObjectUtil.isNotEmpty(dto.getId()), BaseDO::getId, dto.getId())
              .eq(ObjectUtil.isNotEmpty(dto.getAccountId()), AccountExtDO::getAccountId, dto.getAccountId())
              .in(ObjectUtil.isNotEmpty(dto.getAccountIdList()), AccountExtDO::getAccountId, dto.getAccountIdList())
              .eq(ObjectUtil.isNotEmpty(dto.getExtType()), AccountExtDO::getExtType, dto.getExtType())
              .in(ObjectUtil.isNotEmpty(dto.getExtTypeList()), AccountExtDO::getExtType, dto.getExtTypeList())
              .eq(ObjectUtil.isNotEmpty(dto.getExtKey()), AccountExtDO::getExtKey, dto.getExtKey())
              .in(ObjectUtil.isNotEmpty(dto.getExtKeyList()), AccountExtDO::getExtKey, dto.getExtKeyList())
              .eq(ObjectUtil.isNotEmpty(dto.getAttr1()), AccountExtDO::getAttr1, dto.getAttr1())
              .eq(ObjectUtil.isNotEmpty(dto.getAttr2()), AccountExtDO::getAttr2, dto.getAttr2())
              .eq(ObjectUtil.isNotEmpty(dto.getAttr3()), AccountExtDO::getAttr3, dto.getAttr3())
              .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

      List<AccountExtDO> extList = query.list();
      if (CollectionUtil.isEmpty(extList)) {
         return Collections.emptyList();
      }

      return accountExtConverter.toAccountExtVoList(extList);
   }


   public AccountExtVo saveOrUpdate(AccountExtSaveOrUpdateDTO dto) {
      if (ObjectUtil.isEmpty(dto.getId())) {
         AccountExtDO accountExtDO = this.accountExtConverter.toAccountExtDO(dto);
         accountExtDO.setExtKey(StrUtil.emptyToDefault(accountExtDO.getExtKey(), "").trim());
         accountExtDO.setExtValue(StrUtil.emptyToDefault(accountExtDO.getExtValue(), "").trim());
         accountExtDO.setAttr1(StrUtil.emptyToDefault(accountExtDO.getAttr1(), "").trim());
         accountExtDO.setAttr2(StrUtil.emptyToDefault(accountExtDO.getAttr2(), "").trim());
         accountExtDO.setAttr3(StrUtil.emptyToDefault(accountExtDO.getAttr3(), "").trim());
         if (this.accountExtMapper.insert(accountExtDO) <= 0) {
            throw new BusinessException("保存账户扩展信息失败.");
         } else {
            return this.queryAccountExt(((AccountExtQueryDTOBuilder)AccountExtQueryDTO.builder().id(accountExtDO.getId())).build());
         }
      } else {
         boolean updated = ChainWrappers.lambdaUpdateChain(accountExtMapper)
                 .set(ObjectUtil.isNotEmpty(dto.getAccountId()), AccountExtDO::getAccountId, dto.getAccountId())
                 .set(ObjectUtil.isNotEmpty(dto.getExtType()), AccountExtDO::getExtType, dto.getExtType())
                 .set(ObjectUtil.isNotEmpty(dto.getExtKey()), AccountExtDO::getExtKey, dto.getExtKey())
                 .set(ObjectUtil.isNotEmpty(dto.getExtValue()), AccountExtDO::getExtValue, dto.getExtValue())
                 .set(ObjectUtil.isNotEmpty(dto.getAttr1()), AccountExtDO::getAttr1, dto.getAttr1())
                 .set(ObjectUtil.isNotEmpty(dto.getAttr2()), AccountExtDO::getAttr2, dto.getAttr2())
                 .set(ObjectUtil.isNotEmpty(dto.getAttr3()), AccountExtDO::getAttr3, dto.getAttr3())
                 .eq(BaseDO::getId, dto.getId())
                 .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                 .update();
         if (!updated) {
            throw new BusinessException("更新账户扩展信息失败.");
         }
         return this.queryAccountExt(((AccountExtQueryDTOBuilder)AccountExtQueryDTO.builder().id(dto.getId())).build());
      }
   }

}
