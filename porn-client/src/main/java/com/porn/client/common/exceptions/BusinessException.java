
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/common/exceptions/BusinessException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */