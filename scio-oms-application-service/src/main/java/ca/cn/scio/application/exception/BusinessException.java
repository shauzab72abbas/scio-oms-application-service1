package ca.cn.scio.application.exception;

public class BusinessException extends RuntimeException {
    final ErrorCode errorCode ;
    public BusinessException(String msg , ErrorCode errorCode){
        super(msg) ;
        this.errorCode = errorCode ;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
