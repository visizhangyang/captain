
package com.porn.client.common.holder;



import com.porn.client.user.vo.UserLoginVo;







 public class UserHolder
         {
    /* 12 */   private static final ThreadLocal<UserLoginVo> userHolder = new ThreadLocal<>();







    public static void setUser(UserLoginVo user) {
        /* 19 */
        userHolder.set(user);

    }







    public static UserLoginVo getUser() {
        /* 27 */
        return userHolder.get();

    }






    public static void removeUser() {
        /* 34 */
        userHolder.remove();

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/common/holder/UserHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */