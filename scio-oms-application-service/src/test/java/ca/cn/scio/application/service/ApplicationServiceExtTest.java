package ca.cn.scio.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.cn.scio.application.exception.Error;
import ca.cn.scio.application.resource.Facility;
import ca.cn.scio.application.service.impl.ApplicationServiceExtension;
import ca.cn.scio.application.util.AuthUtil;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
 class ApplicationServiceExtTest {

    @InjectMocks
    ApplicationServiceExtension underTest ;

    @Mock
    RestTemplate restTemplate ;

    @Mock
    AuthUtil authUtil ;

    @BeforeEach
    void init(){
        ReflectionTestUtils.setField(underTest , "referenceDomainUrl" , "reference_domain_url");
        when(authUtil.generateAuthorizationHeader(anyString())).thenReturn(new HttpHeaders()) ;
    }




    @Test
    void testReferenceCall() throws JsonProcessingException {
        Map<String , Facility> facilities = MockTestData.getFacilityDetails() ;
        String url = "http://localhost:8080/v1/facilities" ;
        BigDecimal terminalNum = BigDecimal.valueOf(746729) ;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("facilityNumber", terminalNum)
                .queryParam("facilityType" , "Terminal");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getFacility(), HttpStatus.OK)) ;
        assertEquals(CompletableFuture.class , underTest.callReferenceDomainForTerminal(builder , "837465:Terminal" , facilities  , new ArrayList<Error>()).getClass()) ;



    }

    @Test
    void testReferenceCallCE() throws JsonProcessingException {
        Map<String , Facility> facilities = MockTestData.getFacilityDetails() ;
        String url = "http://localhost:8080/v1/facilities" ;
        BigDecimal terminalNum = BigDecimal.valueOf(746729) ;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("facilityNumber", terminalNum)
                .queryParam("facilityType" , "Terminal");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET) , any(HttpEntity.class) , eq(JSONArray.class))).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST ,"BadRequest" , MockTestData.errorResponse() , null)) ;
        assertEquals(CompletableFuture.class , underTest.callReferenceDomainForTerminal(builder , "837465-Terminal" , facilities  , new ArrayList<>()).getClass()) ;



    }
}
