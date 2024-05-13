package ca.cn.scio.application.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.cn.scio.application.exception.BusinessException;
import ca.cn.scio.application.exception.Error;
import ca.cn.scio.application.exception.ErrorCode;
import ca.cn.scio.application.exception.MultipleErrorsException;
import ca.cn.scio.application.exception.SystemException;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;


    String errorMessage = " Resolved [org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public org.springframework.http.ResponseEntity<ca.cn.scio.order.resource.OrderResponse> ca.cn.scio.order.web.OrderController.createOrder(ca.cn.scio.order.resource.Order): [Field error in object 'order' on field 'orderType': rejected value [null]; codes [NotNull.order.orderType,NotNull.orderType,NotNull.ca.cn.scio.order.resource.Order$OrderTypeEnum,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [order.orderType,orderType]; arguments []; default message [orderType]]; default message [must not be null]] ]";

    HttpMessageNotReadableException mockNotReadable = Mockito.mock(HttpMessageNotReadableException.class);
    MethodArgumentNotValidException mockNotValid = Mockito.mock(MethodArgumentNotValidException.class);
    SystemException mockSystemException = Mockito.mock(SystemException.class);
    ResourceAccessException mockResourceAccessException = Mockito.mock(ResourceAccessException.class) ;
    MissingRequestHeaderException mockMissingRequestHeaderException = Mockito.mock(MissingRequestHeaderException.class) ;


    @Test
    void handleBadFieldRequest() {

        Mockito.when(mockNotValid.getMessage()).thenReturn(errorMessage);
        assertEquals(400,
                globalExceptionHandler.handleMissingBadRequest( mockNotValid).getStatusCodeValue());
    }

    @Test
    void handleBadValueRequest() {
        assertEquals(400,
                globalExceptionHandler.handleInvalidBadRequest( mockNotReadable).getStatusCodeValue());
    }

    @Test
    void handleSystemException() {
        assertEquals(500,
                globalExceptionHandler.handleSystemException(mockSystemException).getStatusCodeValue());
    }

    @Test
    void handleMissingHeaderException() {
        ResponseEntity<Object> entity = globalExceptionHandler.handleMissingHeaderException(mockMissingRequestHeaderException);
        Assertions.assertNotNull(entity);
    }

    @Test void handleResourceAccessException() {
        assertEquals(500, globalExceptionHandler.handleResourceAccessException(mockResourceAccessException).getStatusCodeValue());
    }

    @Test
    void handleHttpClientErrorException() throws JsonProcessingException, ParseException {
        String message = "{\n" +
                "    \"message\" : \"NOT_FOUND\" \n" +
                "}" ;
        assertEquals(404 , globalExceptionHandler.handleHttpClientErrorException( new HttpClientErrorException(HttpStatus.NOT_FOUND ,"Not Found" , message.getBytes() , null)).getStatusCodeValue());
    }

    @Test
    void handleHttpServerErrorException() throws  ParseException {
        String message = "{\n" +
                "    \"message\" : \"GATEWAY_TIMEOUT\" \n" +
                "}" ;
        assertEquals(502 , globalExceptionHandler.handleHttpServerErrorException( new HttpServerErrorException(HttpStatus.BAD_GATEWAY ,"Bad_Gateway" , message.getBytes() , null)).getStatusCodeValue());
    }

    @Test
    void handleMultipleErrorsException(){
        List<Error> errors = new ArrayList<>() ;
        errors.add(new Error());

        MultipleErrorsException exception = new MultipleErrorsException("One or More validation Failure" ,  errors);
        assertEquals(400 , globalExceptionHandler.handleMultipleErrorsException( exception).getStatusCodeValue());


    }

    @Test
    void handleNoHandlerFoundException(){
        NoHandlerFoundException noHandlerFoundException = Mockito.mock(NoHandlerFoundException.class) ;
        assertEquals(400 , globalExceptionHandler.handleNoHandlerFoundException(noHandlerFoundException).getStatusCodeValue()) ;
    }

    @Test
    void handleBusinessException(){
        BusinessException businessException = new BusinessException("Bad Request" , ErrorCode.SCIOAPP007);
        assertEquals(400 , globalExceptionHandler.handleBusinessException(businessException).getStatusCodeValue()) ;

    }


}