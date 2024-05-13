package ca.cn.scio.application.exception;

public class NotFoundException extends  RuntimeException{

    final ErrorCode errorCode ;
    public NotFoundException(String msg , ErrorCode errorCode){
        super(msg) ;
        this.errorCode = errorCode ;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
