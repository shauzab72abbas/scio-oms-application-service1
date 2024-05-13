package ca.cn.scio.application.exception;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MultipleErrorsException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("errors")
    private final transient List<Error> errors;
    public MultipleErrorsException(String msg , List<Error> errors) {
        super(msg) ;
        this.errors = errors ;
    }

    public List<Error> getErrors() {
        return errors;
    }


}
