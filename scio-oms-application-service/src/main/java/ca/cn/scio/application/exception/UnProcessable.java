package ca.cn.scio.application.exception;

public class UnProcessable extends  RuntimeException{

    final ErrorCode errorCode ;
    public UnProcessable(String msg , ErrorCode errorCode){
        super(msg) ;
        this.errorCode = errorCode ;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

