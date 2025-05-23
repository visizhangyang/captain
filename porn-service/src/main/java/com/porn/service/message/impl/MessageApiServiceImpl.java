package com.porn.service.message.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
    private static final Logger log = LoggerFactory.getLogger(MessageApiServiceImpl.class);


    @Autowired
    private MessageMapper messageMapper;


    @Autowired
    private MessageConverter messageConverter;


    @Autowired
    private MessageServer messageServer;


    @Autowired
    private UserRoleApiService userRoleApiService;

    public MessageVo queryMessage(MessageQueryDTO messageQueryDTO) {

        List<MessageVo> messageVoList = queryMessageList(messageQueryDTO);

        return ObjectUtil.isEmpty(messageVoList) ? null : messageVoList.get(0);

    }


    public List<MessageVo> queryMessageList(MessageQueryDTO messageQueryDTO) {

        List<MessageDO> messageList = ChainWrappers.lambdaQueryChain(messageMapper)
                .eq(ObjectUtil.isNotEmpty(messageQueryDTO.getId()), BaseDO::getId, messageQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(messageQueryDTO.getAccountId()), MessageDO::getAccountId, messageQueryDTO.getAccountId())
                .eq(ObjectUtil.isNotEmpty(messageQueryDTO.getAccountName()), MessageDO::getAccountName, messageQueryDTO.getAccountName())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(BaseDO::getCreateTime)
                .list();

        if (ObjectUtil.isEmpty(messageList)) {

            return Collections.emptyList();

        }

        List<MessageVo> messageVoList = this.messageConverter.toMessageVoList(messageList);

        return messageVoList;

    }


    public PageVo<MessageVo> queryPage(MessageQueryPageDTO messageQueryPageDTO) {

        Page page = new Page(messageQueryPageDTO.getPageStart().intValue(), messageQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper<MessageDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(messageQueryPageDTO.getAccountId()), MessageDO::getAccountId, messageQueryPageDTO.getAccountId());
        wrapper.eq(ObjectUtil.isNotEmpty(messageQueryPageDTO.getAccountName()), MessageDO::getAccountName, messageQueryPageDTO.getAccountName());
        wrapper.like(ObjectUtil.isNotEmpty(messageQueryPageDTO.getLkAccountName()), MessageDO::getAccountName, messageQueryPageDTO.getLkAccountName());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(BaseDO::getCreateTime);

        IPage<MessageDO> messagePage = this.messageMapper.selectPage((IPage) page, wrapper);

        List<MessageVo> messageVoList = this.messageConverter.toMessageVoList(messagePage.getRecords());

        return PageVo.<MessageVo>builder()
                .pageStart(messageQueryPageDTO.getPageStart())
                .pageSize(messageQueryPageDTO.getPageSize())
                .total(Long.valueOf(messagePage.getTotal()))
                .data(messageVoList)
                .build();

    }


    public MessageVo saveOrUpdate(MessageSaveOrUpdateDTO messageSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(messageSaveOrUpdateDTO.getId())) {

            MessageDO messageDO = this.messageConverter.toMessageDO(messageSaveOrUpdateDTO);

            if (this.messageMapper.insert(messageDO) <= 0) {

                throw new BusinessException("保存消息信息失败.");

            }

            return queryMessage(((MessageQueryDTO.MessageQueryDTOBuilder) MessageQueryDTO.builder().id(messageDO.getId())).build());

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(messageMapper)
                .set(ObjectUtil.isNotEmpty(messageSaveOrUpdateDTO.getAccountId()), MessageDO::getAccountId, messageSaveOrUpdateDTO.getAccountId())
                .set(ObjectUtil.isNotEmpty(messageSaveOrUpdateDTO.getAccountName()), MessageDO::getAccountName, messageSaveOrUpdateDTO.getAccountName())
                .set(ObjectUtil.isNotEmpty(messageSaveOrUpdateDTO.getMsg()), MessageDO::getMsg, messageSaveOrUpdateDTO.getMsg())
                .eq(BaseDO::getId, messageSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新消息信息失败.");

        }

        return queryMessage(((MessageQueryDTO.MessageQueryDTOBuilder) MessageQueryDTO.builder().id(messageSaveOrUpdateDTO.getId())).build());

    }


    public Boolean batchDelete(MessageBatchDeleteDTO messageBatchDeleteDTO) {

        if (ObjectUtil.isEmpty(messageBatchDeleteDTO.getId()) &&
                ObjectUtil.isEmpty(messageBatchDeleteDTO.getIdList())) {

            return Boolean.FALSE;

        }

        return ChainWrappers.lambdaUpdateChain(messageMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(ObjectUtil.isNotEmpty(messageBatchDeleteDTO.getId()), BaseDO::getId, messageBatchDeleteDTO.getId())
                .in(ObjectUtil.isNotEmpty(messageBatchDeleteDTO.getIdList()), BaseDO::getId, messageBatchDeleteDTO.getIdList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }


    public List<String> adminMsg(AdminMessageDTO adminMessageDTO) {

        UserRoleQueryDTO userRoleQueryDTO = UserRoleQueryDTO.builder().userId(adminMessageDTO.getCurrentUserId()).build();

        List<RoleVo> roleVoList = this.userRoleApiService.queryRoleList(userRoleQueryDTO);

        if (ObjectUtil.isEmpty(roleVoList)) {

            return Collections.emptyList();

        }

        return checkAdmin(roleVoList) ? this.messageServer.getMsgs() : Collections.<String>emptyList();

    }


    protected boolean checkAdmin(List<RoleVo> roleVoList) {

        if (ObjectUtil.isEmpty(roleVoList)) {

            return false;

        }

        for (RoleVo roleVo : roleVoList) {

            if ("admin".equalsIgnoreCase(roleVo.getName()) || "管理员"
                    .equalsIgnoreCase(roleVo.getName()) || "超级管理员"
                    .equalsIgnoreCase(roleVo.getName())) {

                return true;

            }

        }

        return false;

    }

}

