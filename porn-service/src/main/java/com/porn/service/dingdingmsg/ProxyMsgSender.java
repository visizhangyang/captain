package com.porn.service.dingdingmsg;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.config.api.ConfigApiService;
import com.porn.client.config.dto.ConfigQueryDTO;
import com.porn.client.config.vo.ConfigVo;
import com.porn.service.dingdingmsg.config.TgConfig;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class ProxyMsgSender {
    private static final Logger log = LoggerFactory.getLogger(ProxyMsgSender.class);

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private ConfigApiService configApiService;


    @Async
    public void sendMsg(ProxyMsgDTO proxyMsgDTO) {

        if (ObjectUtil.isEmpty(proxyMsgDTO.getAccountId())) {

            log.error("消息的账户ID为空, [{}].", JSON.toJSONString(proxyMsgDTO));

            return;

        }

        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(proxyMsgDTO.getAccountId())).build());

        if (ObjectUtil.isEmpty(accountVo) ||
                ObjectUtil.isEmpty(accountVo.getParentId())) {

            log.error("账户信息不存在, [{}].", proxyMsgDTO.getAccountId());

            return;

        }

        AccountVo parentAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(accountVo.getParentId())).build());

        if (ObjectUtil.isEmpty(parentAccountVo) ||
                ObjectUtil.isEmpty(parentAccountVo.getParentId())) {

            log.error("一级父账户信息不存在, [{}].", accountVo.getParentId());

            return;

        }

        sendMsgToAccount(parentAccountVo, proxyMsgDTO);


        AccountVo parentParentAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(parentAccountVo.getParentId())).build());

        if (ObjectUtil.isEmpty(parentParentAccountVo) ||
                ObjectUtil.isEmpty(parentParentAccountVo.getParentId())) {

            log.error("二级父账户信息不存在, [{}].", parentAccountVo.getParentId());

            return;

        }


        sendMsgToAccount(parentParentAccountVo, proxyMsgDTO);


        AccountVo parentParentParentAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(parentParentAccountVo.getParentId())).build());

        if (ObjectUtil.isEmpty(parentParentParentAccountVo) ||
                ObjectUtil.isEmpty(parentParentParentAccountVo.getParentId())) {

            log.error("三级级父账户信息不存在, [{}].", parentParentParentAccountVo.getParentId());

            return;

        }


        sendMsgToAccount(parentParentParentAccountVo, proxyMsgDTO);

    }

    protected void sendMsgToAccount(AccountVo accountVo, ProxyMsgDTO proxyMsgDTO) {

        TgConfig tgConfig = getTgConfig(accountVo);

        if (ObjectUtil.isEmpty(tgConfig)) {

            log.info("tg配置为空[{}].", accountVo.getId());

            return;

        }

        try {

            log.error("tg消息推送开始, 消息[{}].", JSON.toJSONString(proxyMsgDTO));


            SimpleTgMsg simpleTgMsg = SimpleTgMsg.builder().tgConfig(tgConfig).build();

            SendMessage sendMessage = new SendMessage();

            sendMessage.setText(proxyMsgDTO.getMsg());

            sendMessage.setChatId(tgConfig.getChatId());

            simpleTgMsg.execute((BotApiMethod) sendMessage);

            log.info("tg消息推送结束, 消息[{}].", JSON.toJSONString(proxyMsgDTO));

        } catch (Exception e) {

            log.error("tg消息推送失败[{}].", e);

        }

    }


    protected TgConfig getTgConfig(AccountVo accountVo) {

        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().accountId(accountVo.getId()).configGroup("tg").build();

        List<ConfigVo> configVoList = this.configApiService.queryConfigList(configQueryDTO);

        if (ObjectUtil.isEmpty(configVoList)) {

            return null;

        }

        TgConfig tgConfig = TgConfig.builder().build();

        for (ConfigVo configVo : configVoList) {

            if ("botUsername".equals(configVo.getConfigCode())) {

                tgConfig.setBotUsername(configVo.getConfigValue());

            }

            if ("botToken".equals(configVo.getConfigCode())) {

                tgConfig.setBotToken(configVo.getConfigValue());

            }

            if ("chatId".equals(configVo.getConfigCode())) {

                try {

                    tgConfig.setChatId(Long.valueOf(configVo.getConfigValue()));

                } catch (Exception exception) {
                }

            }

        }


        return (ObjectUtil.isEmpty(tgConfig.getBotUsername()) || ObjectUtil.isEmpty(tgConfig.getBotToken()) || ObjectUtil.isEmpty(tgConfig.getChatId())) ? null : tgConfig;

    }

    public static class SimpleTgMsg extends TelegramWebhookBot {
        private TgConfig tgConfig;


        public SimpleTgMsg(TgConfig tgConfig) {

            this.tgConfig = tgConfig;

        }

        public SimpleTgMsg() {
        }

        public static SimpleTgMsgBuilder builder() {
            return new SimpleTgMsgBuilder();
        }

        public String getBotUsername() {

            return this.tgConfig.getBotUsername();

        }

        public String getBotToken() {

            return this.tgConfig.getBotToken();

        }

        public void onRegister() {

            super.onRegister();

        }

        public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

            return null;

        }

        public String getBotPath() {

            return null;

        }

        public static class SimpleTgMsgBuilder {
            private TgConfig tgConfig;

            public SimpleTgMsgBuilder tgConfig(TgConfig tgConfig) {
                this.tgConfig = tgConfig;
                return this;
            }

            public ProxyMsgSender.SimpleTgMsg build() {
                return new ProxyMsgSender.SimpleTgMsg(this.tgConfig);
            }

            public String toString() {
                return "ProxyMsgSender.SimpleTgMsg.SimpleTgMsgBuilder(tgConfig=" + this.tgConfig + ")";
            }
        }

    }

    public static class SimpleTgMsgBuilder {
        private TgConfig tgConfig;


        public SimpleTgMsgBuilder tgConfig(TgConfig tgConfig) {

            this.tgConfig = tgConfig;

            return this;

        }


        public ProxyMsgSender.SimpleTgMsg build() {

            return new ProxyMsgSender.SimpleTgMsg(this.tgConfig);

        }


        public String toString() {

            return "ProxyMsgSender.SimpleTgMsg.SimpleTgMsgBuilder(tgConfig=" + this.tgConfig + ")";

        }

    }

}

