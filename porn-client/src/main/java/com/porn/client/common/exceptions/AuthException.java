
package com.porn.client.common.exceptions;







 public class AuthException
         extends RuntimeException
         {

    public AuthException() {
    }



    public AuthException(String message) {
        /* 14 */
        super(message);

    }


    public AuthException(String message, Throwable cause) {
        /* 17 */
        super(message, cause);

    }


    public AuthException(Throwable cause) {
        /* 20 */
        super(cause);

    }


    protected AuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        /* 23 */
        super(message, cause, enableSuppression, writableStackTrace);

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/common/exceptions/AuthException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */