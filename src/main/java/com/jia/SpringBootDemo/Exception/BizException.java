package com.jia.SpringBootDemo.Exception;

// 业务异常类
public class BizException extends RuntimeException{

        private String errorCode;

        private String errorMessage;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }


        public BizException() {
            super();
        }

        public BizException(BaseErrorInfo baseErrorInfo) {
            super(baseErrorInfo.getResultMsg());
            errorCode = baseErrorInfo.getResultCode();
            errorMessage = baseErrorInfo.getResultMsg();
        }

        public BizException(String errMsg) {
            super(errMsg);
            errorMessage = errMsg;
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }


        public BizException(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public BizException(String message, String errorCode, String errorMessage) {
            super(message);
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public BizException(String message, Throwable cause, String errorCode, String errorMessage) {
            super(message, cause);
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public BizException(Throwable cause, String errorCode, String errorMessage) {
            super(cause);
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode, String errorMessage) {
            super(message, cause, enableSuppression, writableStackTrace);
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
}
