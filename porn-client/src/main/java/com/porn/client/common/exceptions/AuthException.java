
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


