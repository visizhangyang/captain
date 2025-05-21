package com.porn.client.common.constrant;

public interface RedisConst {
    public static final String CAPTCHACODE_KEY = "captcha:%s";

    public static final String LOGINUSER_PREFIX_KEY = "login:*";

    public static final String LOGINUSER_KEY = "login:%s";

    public static final String ACCOUNT_LOGINACCOUNT_KEY = "account:session:%s";

    public static final String ACCOUNT_REGCAPTCHA_KEY = "account:regcaptcha:%s";

    public static final String ACCOUNT_MODIFYLOGINPWDCAPTCHA_KEY = "account:modifyloginpwdcaptcha:%s";

    public static final String ACCOUNT_MODIFYTRADEPWDCAPTCHA_KEY = "account:modifytradepwdcaptcha:%s";

    public static final String AUTOWORK_ACCOUNTINFO = "AUTOWORK%s%s";

    public static final String AUTOWORK_ACCOUNT_ORDER = "AUTOWORKORDER%s%s";

    public static final String PLANWORK_ACCOUNTINFO = "PLANWORK:%s:%s";

    public static final String AUTOWORK_ACCOUNTWEEKADDRESS = "AUTOWORKACCOUNTWEEK%s%s%s";

    public static final String REWARDRECORD_PREF_DAY_AVAILABLE = "REWARDRECORD:%s:%s";
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/common/constrant/RedisConst.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */