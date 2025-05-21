
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
 public class ProxyMsgSender
         {
    /*  32 */   private static final Logger log = LoggerFactory.getLogger(ProxyMsgSender.class);




    @Autowired
     private AccountApiService accountApiService;




    @Autowired
     private ConfigApiService configApiService;





    @Async
     public void sendMsg(ProxyMsgDTO proxyMsgDTO) {
        /*  48 */
        if (ObjectUtil.isEmpty(proxyMsgDTO.getAccountId())) {
            /*  49 */
            log.error("消息的账户ID为空, [{}].", JSON.toJSONString(proxyMsgDTO));


            return;

        }
        /*  53 */
        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(proxyMsgDTO.getAccountId())).build());
        /*  54 */
        if (ObjectUtil.isEmpty(accountVo) ||
                /*  55 */       ObjectUtil.isEmpty(accountVo.getParentId())) {
            /*  56 */
            log.error("账户信息不存在, [{}].", proxyMsgDTO.getAccountId());


            return;

        }
        /*  60 */
        AccountVo parentAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(accountVo.getParentId())).build());
        /*  61 */
        if (ObjectUtil.isEmpty(parentAccountVo) ||
                /*  62 */       ObjectUtil.isEmpty(parentAccountVo.getParentId())) {
            /*  63 */
            log.error("一级父账户信息不存在, [{}].", accountVo.getParentId());


            return;

        }
        /*  67 */
        sendMsgToAccount(parentAccountVo, proxyMsgDTO);


        /*  70 */
        AccountVo parentParentAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(parentAccountVo.getParentId())).build());
        /*  71 */
        if (ObjectUtil.isEmpty(parentParentAccountVo) ||
                /*  72 */       ObjectUtil.isEmpty(parentParentAccountVo.getParentId())) {
            /*  73 */
            log.error("二级父账户信息不存在, [{}].", parentAccountVo.getParentId());


            return;

        }

        /*  78 */
        sendMsgToAccount(parentParentAccountVo, proxyMsgDTO);


        /*  81 */
        AccountVo parentParentParentAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(parentParentAccountVo.getParentId())).build());
        /*  82 */
        if (ObjectUtil.isEmpty(parentParentParentAccountVo) ||
                /*  83 */       ObjectUtil.isEmpty(parentParentParentAccountVo.getParentId())) {
            /*  84 */
            log.error("三级级父账户信息不存在, [{}].", parentParentParentAccountVo.getParentId());


            return;

        }

        /*  89 */
        sendMsgToAccount(parentParentParentAccountVo, proxyMsgDTO);

    }








    protected void sendMsgToAccount(AccountVo accountVo, ProxyMsgDTO proxyMsgDTO) {
        /*  98 */
        TgConfig tgConfig = getTgConfig(accountVo);
        /*  99 */
        if (ObjectUtil.isEmpty(tgConfig)) {
            /* 100 */
            log.info("tg配置为空[{}].", accountVo.getId());

            return;

        }

        try {
            /* 104 */
            log.error("tg消息推送开始, 消息[{}].", JSON.toJSONString(proxyMsgDTO));


            /* 107 */
            SimpleTgMsg simpleTgMsg = SimpleTgMsg.builder().tgConfig(tgConfig).build();
            /* 108 */
            SendMessage sendMessage = new SendMessage();
            /* 109 */
            sendMessage.setText(proxyMsgDTO.getMsg());
            /* 110 */
            sendMessage.setChatId(tgConfig.getChatId());
            /* 111 */
            simpleTgMsg.execute((BotApiMethod) sendMessage);
            /* 112 */
            log.info("tg消息推送结束, 消息[{}].", JSON.toJSONString(proxyMsgDTO));
            /* 113 */
        } catch (Exception e) {
            /* 114 */
            log.error("tg消息推送失败[{}].", e);

        }

    }











    protected TgConfig getTgConfig(AccountVo accountVo) {
        /* 127 */
        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().accountId(accountVo.getId()).configGroup("tg").build();
        /* 128 */
        List<ConfigVo> configVoList = this.configApiService.queryConfigList(configQueryDTO);
        /* 129 */
        if (ObjectUtil.isEmpty(configVoList)) {
            /* 130 */
            return null;

        }
        /* 132 */
        TgConfig tgConfig = TgConfig.builder().build();
        /* 133 */
        for (ConfigVo configVo : configVoList) {
            /* 134 */
            if ("botUsername".equals(configVo.getConfigCode())) {
                /* 135 */
                tgConfig.setBotUsername(configVo.getConfigValue());

            }
            /* 137 */
            if ("botToken".equals(configVo.getConfigCode())) {
                /* 138 */
                tgConfig.setBotToken(configVo.getConfigValue());

            }
            /* 140 */
            if ("chatId".equals(configVo.getConfigCode())) {

                try {
                    /* 142 */
                    tgConfig.setChatId(Long.valueOf(configVo.getConfigValue()));
                    /* 143 */
                } catch (Exception exception) {
                }

            }

        }


        /* 148 */
        return (ObjectUtil.isEmpty(tgConfig.getBotUsername()) || ObjectUtil.isEmpty(tgConfig.getBotToken()) || ObjectUtil.isEmpty(tgConfig.getChatId())) ? null : tgConfig;

    }


       public static class SimpleTgMsg extends TelegramWebhookBot {
             private TgConfig tgConfig;


        /* 154 */
        public static SimpleTgMsgBuilder builder() {
            return new SimpleTgMsgBuilder();
        }

        public static class SimpleTgMsgBuilder {
            public SimpleTgMsgBuilder tgConfig(TgConfig tgConfig) {
                this.tgConfig = tgConfig;
                return this;
            }

            private TgConfig tgConfig;

            public ProxyMsgSender.SimpleTgMsg build() {
                return new ProxyMsgSender.SimpleTgMsg(this.tgConfig);
            }

            public String toString() {
                return "ProxyMsgSender.SimpleTgMsg.SimpleTgMsgBuilder(tgConfig=" + this.tgConfig + ")";
            }
        }

        public SimpleTgMsg(TgConfig tgConfig) {
            /* 155 */
            this.tgConfig = tgConfig;

        }





        public SimpleTgMsg() {
        }




        public String getBotUsername() {
            /* 164 */
            return this.tgConfig.getBotUsername();

        }



        public String getBotToken() {
            /* 168 */
            return this.tgConfig.getBotToken();

        }



        public void onRegister() {
            /* 172 */
            super.onRegister();

        }



        public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
            /* 176 */
            return null;

        }



        public String getBotPath() {
            /* 180 */
            return null;

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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/dingdingmsg/ProxyMsgSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */