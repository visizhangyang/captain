
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


