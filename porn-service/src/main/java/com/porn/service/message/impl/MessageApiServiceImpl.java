
package com.porn.service.message.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.message.api.MessageApiService;
import com.porn.client.message.dto.*;
import com.porn.client.message.vo.MessageVo;
import com.porn.client.role.vo.RoleVo;
import com.porn.client.user.api.UserRoleApiService;
import com.porn.client.user.dto.UserRoleQueryDTO;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.message.converter.MessageConverter;
import com.porn.service.message.dao.entity.MessageDO;
import com.porn.service.message.dao.mapper.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;





























@Service

@Transactional(rollbackFor = {Exception.class})
 public class MessageApiServiceImpl implements MessageApiService {
    /*  32 */   private static final Logger log = LoggerFactory.getLogger(MessageApiServiceImpl.class);



    @Autowired
     private MessageMapper messageMapper;



    @Autowired
     private MessageConverter messageConverter;



    @Autowired
     private MessageServer messageServer;



    @Autowired
     private UserRoleApiService userRoleApiService;




    public MessageVo queryMessage(MessageQueryDTO messageQueryDTO) {
        /*  52 */
        List<MessageVo> messageVoList = queryMessageList(messageQueryDTO);
        /*  53 */
        return ObjectUtil.isEmpty(messageVoList) ? null : messageVoList.get(0);

    }









    public List<MessageVo> queryMessageList(MessageQueryDTO messageQueryDTO) {
        /*  63 */
        List<MessageDO> messageList =  ChainWrappers.lambdaQueryChain(messageMapper)
                .eq(ObjectUtil.isNotEmpty(messageQueryDTO.getId()), BaseDO::getId, messageQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(messageQueryDTO.getAccountId()), MessageDO::getAccountId, messageQueryDTO.getAccountId())
                .eq(ObjectUtil.isNotEmpty(messageQueryDTO.getAccountName()), MessageDO::getAccountName, messageQueryDTO.getAccountName())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(BaseDO::getCreateTime)
                .list();
        /*  64 */
        if (ObjectUtil.isEmpty(messageList)) {
            /*  65 */
            return Collections.emptyList();

        }
        /*  67 */
        List<MessageVo> messageVoList = this.messageConverter.toMessageVoList(messageList);
        /*  68 */
        return messageVoList;

    }



    public PageVo<MessageVo> queryPage(MessageQueryPageDTO messageQueryPageDTO) {
        /*  72 */
        Page page = new Page(messageQueryPageDTO.getPageStart().intValue(), messageQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper<MessageDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(messageQueryPageDTO.getAccountId()), MessageDO::getAccountId, messageQueryPageDTO.getAccountId());
        wrapper.eq(ObjectUtil.isNotEmpty(messageQueryPageDTO.getAccountName()), MessageDO::getAccountName, messageQueryPageDTO.getAccountName());
        wrapper.like(ObjectUtil.isNotEmpty(messageQueryPageDTO.getLkAccountName()), MessageDO::getAccountName, messageQueryPageDTO.getLkAccountName());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(BaseDO::getCreateTime);

        IPage<MessageDO> messagePage = this.messageMapper.selectPage((IPage) page, wrapper);
        /*  81 */
        List<MessageVo> messageVoList = this.messageConverter.toMessageVoList(messagePage.getRecords());
        /*  82 */
        return PageVo.<MessageVo>builder()
/*  83 */.pageStart(messageQueryPageDTO.getPageStart())
/*  84 */.pageSize(messageQueryPageDTO.getPageSize())
/*  85 */.total(Long.valueOf(messagePage.getTotal()))
/*  86 */.data(messageVoList)
/*  87 */.build();

    }



    public MessageVo saveOrUpdate(MessageSaveOrUpdateDTO messageSaveOrUpdateDTO) {
        /*  91 */
        if (ObjectUtil.isEmpty(messageSaveOrUpdateDTO.getId())) {
            /*  92 */
            MessageDO messageDO = this.messageConverter.toMessageDO(messageSaveOrUpdateDTO);
            /*  93 */
            if (this.messageMapper.insert(messageDO) <= 0)
                 {
                /*  95 */
                throw new BusinessException("保存消息信息失败.");

            }
            /*  97 */
            return queryMessage(((MessageQueryDTO.MessageQueryDTOBuilder) MessageQueryDTO.builder().id(messageDO.getId())).build());

        }






        /* 105 */
        boolean rs = ChainWrappers.lambdaUpdateChain(messageMapper)
                .set(ObjectUtil.isNotEmpty(messageSaveOrUpdateDTO.getAccountId()), MessageDO::getAccountId, messageSaveOrUpdateDTO.getAccountId())
                .set(ObjectUtil.isNotEmpty(messageSaveOrUpdateDTO.getAccountName()), MessageDO::getAccountName, messageSaveOrUpdateDTO.getAccountName())
                .set(ObjectUtil.isNotEmpty(messageSaveOrUpdateDTO.getMsg()), MessageDO::getMsg, messageSaveOrUpdateDTO.getMsg())
                .eq(BaseDO::getId, messageSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /* 106 */
        if (!rs)
             {
            /* 108 */
            throw new BusinessException("更新消息信息失败.");

        }
        /* 110 */
        return queryMessage(((MessageQueryDTO.MessageQueryDTOBuilder) MessageQueryDTO.builder().id(messageSaveOrUpdateDTO.getId())).build());

    }





    public Boolean batchDelete(MessageBatchDeleteDTO messageBatchDeleteDTO) {
        /* 116 */
        if (ObjectUtil.isEmpty(messageBatchDeleteDTO.getId()) &&
                /* 117 */       ObjectUtil.isEmpty(messageBatchDeleteDTO.getIdList())) {
            /* 118 */
            return Boolean.FALSE;

        }
        /* 120 */
        return  ChainWrappers.lambdaUpdateChain(messageMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(ObjectUtil.isNotEmpty(messageBatchDeleteDTO.getId()), BaseDO::getId, messageBatchDeleteDTO.getId())
                .in(ObjectUtil.isNotEmpty(messageBatchDeleteDTO.getIdList()), BaseDO::getId, messageBatchDeleteDTO.getIdList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 125 */.update();

    }







    public List<String> adminMsg(AdminMessageDTO adminMessageDTO) {
        /* 133 */
        UserRoleQueryDTO userRoleQueryDTO = UserRoleQueryDTO.builder().userId(adminMessageDTO.getCurrentUserId()).build();
        /* 134 */
        List<RoleVo> roleVoList = this.userRoleApiService.queryRoleList(userRoleQueryDTO);
        /* 135 */
        if (ObjectUtil.isEmpty(roleVoList)) {
            /* 136 */
            return Collections.emptyList();

        }
        /* 138 */
        return checkAdmin(roleVoList) ? this.messageServer.getMsgs() : Collections.<String>emptyList();

    }







    protected boolean checkAdmin(List<RoleVo> roleVoList) {
        /* 146 */
        if (ObjectUtil.isEmpty(roleVoList)) {
            /* 147 */
            return false;

        }
        /* 149 */
        for (RoleVo roleVo : roleVoList) {
            /* 150 */
            if ("admin".equalsIgnoreCase(roleVo.getName()) || "管理员"
/* 151 */.equalsIgnoreCase(roleVo.getName()) || "超级管理员"
/* 152 */.equalsIgnoreCase(roleVo.getName())) {
                /* 153 */
                return true;

            }

        }
        /* 156 */
        return false;

    }

}


