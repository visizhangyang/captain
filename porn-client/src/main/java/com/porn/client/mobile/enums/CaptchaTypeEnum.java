package com.porn.client.mobile.enums;

public enum CaptchaTypeEnum {
    LOGIN(0, "account:session:%s", 1, "登录"),
    REGISTER(1, "account:regcaptcha:%s", 1, "注册"),
    MODIFYLOGINPWD(2, "account:modifyloginpwdcaptcha:%s", 1, "修改登录密码"),
    MODIFYTRADEPWD(3, "account:modifytradepwdcaptcha:%s", 1, "修改交易密码");

    private final Integer type;
    private final String cacheKeyFormate;
    private final Integer timeout;
    private final String description;

    CaptchaTypeEnum(Integer type, String cacheKeyFormate, Integer timeout, String description) {
        this.type = type;
        this.cacheKeyFormate = cacheKeyFormate;
        this.timeout = timeout;
        this.description = description;
    }

    public static CaptchaTypeEnum queryByType(Integer type) {
        if (null == type) {
            return null;
        }
        for (CaptchaTypeEnum captchaTypeEnum : values()) {
            if (captchaTypeEnum.getType().equals(type)) {
                return captchaTypeEnum;
            }
        }
        return null;
    }

    public Integer getType() {
        return this.type;
    }

    public String getCacheKeyFormate() {
        return this.cacheKeyFormate;
    }

    public Integer getTimeout() {
        return this.timeout;
    }

    public String getDescription() {
        return this.description;
    }
}