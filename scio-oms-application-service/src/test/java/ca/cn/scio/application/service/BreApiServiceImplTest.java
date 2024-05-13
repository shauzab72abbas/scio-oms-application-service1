package ca.cn.scio.application.service;

import ca.cn.scio.application.resource.*;
import ca.cn.scio.application.service.impl.BreApiServiceImpl;
import ca.cn.scio.application.util.AuthUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BreApiServiceImplTest {
    @Mock
    AuthUtil authUtil;
    @Mock
    RestTemplate restTemplate;
    @Spy
    @InjectMocks
    BreApiServiceImpl breApiService;


    @Test
    void testGetRulesApi(){
        ReflectionTestUtils.setField(breApiService, "breILBUrl", "breTest");
        ReflectionTestUtils.setField(breApiService, "getRules", "breTest");
        ReflectionTestUtils.setField(breApiService, "breCloudRunUrl", "breTest");
        Mockito.doReturn(new HttpHeaders()).when(authUtil).generateAuthorizationHeader("breTest");
        Rule[] rules = new Rule[1];
        ResponseEntity<Rule[]> responseEntity = new ResponseEntity<>(rules, HttpStatus.OK);
        Mockito.doReturn(responseEntity).when(restTemplate).exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET),Mockito.any(),Mockito.eq(Rule[].class));
        Assertions.assertNotNull(breApiService.getRules());
    }

    @Test
    void testFormatEnglishErrorMessage() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        List<BusinessRuleError> ruleError = new ArrayList<>();
        BusinessRuleError businessRuleError = new BusinessRuleError();
        businessRuleError.setErrorType("daddfd<dsd>");
        ruleError.add(businessRuleError);
        order.setOrderError(ruleError);
        order.getCustomerOrder().setOrderError(ruleError);
        order.getCustomerOrder().getOrderEquipmentRequest().get(0).setOrderError(ruleError);
        order.getCustomerOrder().getOrderShipmentRequest().get(0).setOrderError(ruleError);
        order.getCustomerOrder().getOrderShipmentRequest().get(0).getOrderShipmentTransportStops().get(0).setOrderError(ruleError);
        breApiService.reformatEnglishErrorMessage(order);
        Assertions.assertFalse(businessRuleError.getErrorType().contains("<"));
    }
    @Test
    void testSetErrorMessageLanguage(){
        Rule rule = new Rule();
        List<ErrorMessageLanguage> errorMessageLanguages = new ArrayList<>();
        ErrorMessageLanguage errorMessageLanguage = new ErrorMessageLanguage();
        errorMessageLanguage.setLanguageName(ErrorMessageLanguage.LanguageNameEnum.ENGLISH);
        errorMessageLanguages.add(errorMessageLanguage);
        rule.setErrorMessageLanguage(errorMessageLanguages);
        breApiService.setErrorMessageByLanguage(rule, ErrorMessageLanguage.LanguageNameEnum.ENGLISH,"asdvahsdbvjhr");
        String errorMessage = rule.getErrorMessageLanguage().get(0).getErrorMessage();
        Assertions.assertNotNull(errorMessage);
    }
    @Test
    void testGetErrorMessageLanguage(){
        Rule rule = new Rule();
        List<ErrorMessageLanguage> errorMessageLanguages = new ArrayList<>();
        ErrorMessageLanguage errorMessageLanguage = new ErrorMessageLanguage();
        errorMessageLanguage.setLanguageName(ErrorMessageLanguage.LanguageNameEnum.ENGLISH);
        errorMessageLanguage.setErrorMessage("asbvhs");
        errorMessageLanguages.add(errorMessageLanguage);
        rule.setErrorMessageLanguage(errorMessageLanguages);
        String errorMessage = breApiService.getErrorMessageByLanguage(rule, ErrorMessageLanguage.LanguageNameEnum.ENGLISH);
        Assertions.assertNotNull(errorMessage);
    }
    @Test
    void testGetErrorMessageLanguageNull(){
        Rule rule = new Rule();
        List<ErrorMessageLanguage> errorMessageLanguages = new ArrayList<>();
        rule.setErrorMessageLanguage(errorMessageLanguages);
        String errorMessage = breApiService.getErrorMessageByLanguage(rule, ErrorMessageLanguage.LanguageNameEnum.ENGLISH);
        Assertions.assertNull(errorMessage);
    }
}
