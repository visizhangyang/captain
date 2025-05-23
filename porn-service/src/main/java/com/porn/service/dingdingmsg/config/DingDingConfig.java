package com.porn.service.dingdingmsg.config;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component

@Configuration
public class DingDingConfig implements Serializable {

    @ApiModelProperty("接口地址")
    private String url;

    @ApiModelProperty("关键词")
    private String keywords;

    @ApiModelProperty("机器人的用户名")
    private String botUsername;

    @ApiModelProperty("token")
    private String botToken;

    @ApiModelProperty("会话id")
    private Long chatId;


    public DingDingConfig() {
    }

    public DingDingConfig(String url, String keywords, String botUsername, String botToken, Long chatId) {

        this.url = url;
        this.keywords = keywords;
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.chatId = chatId;

    }

    public static DingDingConfigBuilder builder() {
        return new DingDingConfigBuilder();
    }

    public String getUrl() {

        return this.url;

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeywords() {

        return this.keywords;

    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getBotUsername() {

        return this.botUsername;

    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public String getBotToken() {

        return this.botToken;

    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public Long getChatId() {

        return this.chatId;

    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public static class DingDingConfigBuilder {
        private String url;
        private String keywords;
        private String botUsername;
        private String botToken;
        private Long chatId;

        public DingDingConfigBuilder url(String url) {
            this.url = url;
            return this;
        }

        public DingDingConfigBuilder keywords(String keywords) {
            this.keywords = keywords;
            return this;
        }

        public DingDingConfigBuilder botUsername(String botUsername) {
            this.botUsername = botUsername;
            return this;
        }

        public DingDingConfigBuilder botToken(String botToken) {
            this.botToken = botToken;
            return this;
        }

        public DingDingConfigBuilder chatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public DingDingConfig build() {
            return new DingDingConfig(this.url, this.keywords, this.botUsername, this.botToken, this.chatId);
        }

        public String toString() {
            return "DingDingConfig.DingDingConfigBuilder(url=" + this.url + ", keywords=" + this.keywords + ", botUsername=" + this.botUsername + ", botToken=" + this.botToken + ", chatId=" + this.chatId + ")";
        }

    }

}
