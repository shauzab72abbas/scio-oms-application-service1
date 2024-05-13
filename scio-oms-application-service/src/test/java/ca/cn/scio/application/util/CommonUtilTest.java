package ca.cn.scio.application.util;

import ca.cn.scio.application.exception.SystemException;
import ca.cn.scio.application.resource.Order;
import ca.cn.scio.application.service.MockTestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@ExtendWith(MockitoExtension.class)
class CommonUtilTest {
    @InjectMocks
    CommonUtil commonUtil;
    @Mock
    private ResourceLoader resourceLoader;
    @Mock
    private Resource resource;
    @Mock
    private ObjectMapper mapper ;



    @Test
    void sanitizeOrderTest() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        Assertions.assertNotNull(commonUtil.sanitizeOrder(order));
    }

    //Testing for Json Processing Exception
    @Test
    void sanitizeOrderTestError1() throws JsonProcessingException{
        Order order = MockTestData.jsonInput() ;
        //ObjectMapper mapper = new ObjectMapper() ;
        JsonProcessingException exception = new JsonProcessingException("Unable to Parse" , new SystemException()){} ;
        Mockito.when(mapper.writeValueAsString(order)).thenThrow(exception) ;
        Assertions.assertEquals("UNABLE_TO_PARSE" , commonUtil.sanitizeOrder(order));

    }
    @Test
    void joltTransformationTest() throws IOException {
        Object inputJSON = new Object();
        Mockito.when(this.resourceLoader.getResource(VariableUtil.JOLT_DATA_TO_PARTYTOLOCATION)).thenReturn(this.resource);
        final InputStream joltJson = new ByteArrayInputStream(MockTestData.getPartyToLocation().getBytes());
        Mockito.doReturn(joltJson).when(resource).getInputStream();
        Assertions.assertNotNull(commonUtil.joltTransformation(inputJSON));
    }
    @Test
    void joltTransformationTestForIOException() throws IOException {
        Object inputJSON = new Object();
        Mockito.when(this.resourceLoader.getResource(VariableUtil.JOLT_DATA_TO_PARTYTOLOCATION)).thenReturn(this.resource);
        Mockito.doThrow(IOException.class).when(resource).getInputStream();
        Assertions.assertNull(commonUtil.joltTransformation(inputJSON));
    }

}
