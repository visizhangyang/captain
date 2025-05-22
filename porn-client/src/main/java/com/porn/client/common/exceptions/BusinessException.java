
package com.porn.client.common.exceptions;







 public class BusinessException
         extends RuntimeException
         {

    public BusinessException() {
    }



    public BusinessException(String message) {
        /* 14 */
        super(message);

    }


    public BusinessException(String message, Throwable cause) {
        /* 17 */
        super(message, cause);

    }


    public BusinessException(Throwable cause) {
        /* 20 */
        super(cause);

    }


    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        /* 23 */
        super(message, cause, enableSuppression, writableStackTrace);

    }

}


