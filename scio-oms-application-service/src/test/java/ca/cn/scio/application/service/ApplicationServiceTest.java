///*
// * Copyright (c) 1888-2022 CN, Inc.
// * 935 de La Gauchetière Street West, Montreal, Quebec, H3B 2M9, CANADA.
// * All rights reserved.
// *
// * This software is the confidential and proprietary information of CN
// * You shall not disclose such Confidential Information and shall use it only in
// * accordance with the terms of the license agreement you entered into
// * with CN.

package ca.cn.scio.application.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import ca.cn.scio.application.resource.*;
import ca.cn.scio.application.service.impl.BreApiServiceImpl;
import ca.cn.scio.application.service.impl.MultiLanguageServiceImpl;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
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
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.cn.scio.application.exception.NotFoundException;
import ca.cn.scio.application.exception.SystemException;
import ca.cn.scio.application.service.impl.ApplicationServiceExtension;
import ca.cn.scio.application.service.impl.ApplicationServiceImpl;
import ca.cn.scio.application.util.AuthUtil;
import ca.cn.scio.application.util.CommonUtil;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ApplicationServiceTest {

    @InjectMocks
    @Spy
    ApplicationServiceImpl applicationService;

    @Mock
    MultiLanguageServiceImpl multiLanguageService;

    @Mock
    ApplicationServiceExtension asyncCalls;

    @Mock
    RestTemplate restTemplate;

    @Mock
    BreApiServiceImpl breApiService;

    @Mock
    AuthUtil authUtil;

    @Mock
    CommonUtil commonUtil ;

    private String itemRequestId="S22306080460";
    private String userInfo = "userInfo";
    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(applicationService, "referenceDomainILBUrl", "reference_ilb_url/");
        ReflectionTestUtils.setField(applicationService, "terminalById", "terminal_by_number_endpoint");
        ReflectionTestUtils.setField(applicationService, "customerDomainUrl", "customer_domain_url");
        ReflectionTestUtils.setField(applicationService, "getCustomerDetails", "get_customers");
        ReflectionTestUtils.setField(applicationService, "orderDomainUrl", "order_domain_ilb_url");
        ReflectionTestUtils.setField(applicationService, "orderDomainDevUrl", "order_domain_url");
        ReflectionTestUtils.setField(applicationService, "createOrder", "create_order");
        ReflectionTestUtils.setField(applicationService, "updateOrder", "update_order");
        ReflectionTestUtils.setField(applicationService, "referenceDomainCloudRunUrl", "reference_Domain_Url/");
        ReflectionTestUtils.setField(applicationService, "getEquipmentType", "getEquipment_Type");
        ReflectionTestUtils.setField(applicationService, "updateEvent", "update_Event");
        ReflectionTestUtils.setField(applicationService, "customerDomainILBUrl" , "customer_domain_ILB_URL/");
        ReflectionTestUtils.setField(applicationService, "updateTransportEvent" , "application_put_transportShipment");
        ReflectionTestUtils.setField(applicationService, "searchFacilityEndPoint" , "search/facility");
        ReflectionTestUtils.setField(applicationService, "cancelOrder" , "/v1/orders/{orderId}/cancellation");
        ReflectionTestUtils.setField(applicationService, "orderDomainDevUrl" , "orderDomainDevUrl");
        ReflectionTestUtils.setField(applicationService, "getOrderById" , "getOrderById");
        ReflectionTestUtils.setField(applicationService, "searchOrders" , "searchOrders");
        ReflectionTestUtils.setField(applicationService, "cancelPartialOrder" ,
                "/v1/orders/{orderId}/order-item/{itemRequestId}/cancellation");
        when(authUtil.generateAuthorizationHeader(anyString())).thenReturn(new HttpHeaders());

    }
    @Test
    void testDiscard() {
        String orderId = "123";
        String expectedResponse = "Success";
        ResponseEntity<String> mockedResponseEntity = ResponseEntity.ok(expectedResponse);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.DELETE), any(), eq(String.class), eq(orderId)))
                .thenReturn(mockedResponseEntity);
        String result = applicationService.discardOrder(orderId);
        assertEquals(expectedResponse, result);
    }

    @Test
     void createOrderTestSuccess() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(Order.class))).thenReturn(new Order());
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString() ,  anyMap() , anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(commonUtil.sanitizeOrder(any(Order.class))).thenReturn("") ;
        assertEquals(Order.class, applicationService.createOrder(order, userInfo, "eng").getClass());
    }

    @Test
    void createOrderTestSuccessWithSrcFacilityNumber() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponseWithSameFacilityNumber() , HttpStatus.OK)) ;
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(Order.class))).thenReturn(new Order());
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString() ,  anyMap() , anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(commonUtil.sanitizeOrder(any(Order.class))).thenReturn("") ;
        assertEquals(Order.class, applicationService.createOrder(order, userInfo, "eng").getClass());
    }

    @Test
    void testEnhanceOrder() throws JsonProcessingException {
        Order order = MockTestData.jsonInput() ;
        Map<String , Party> customerDetails = MockTestData.getCustomerDetails() ;
        Map<String, Facility> facilities = MockTestData.getFacilityDetails() ;
        //when(commonUtil.joltTransformation(any())).thenReturn(MockTestData.getJoltLocationObject()) ;
        applicationService.enhanceOrder(order, customerDetails , facilities , MockTestData.getRampDetails());
        //assert order.getShipFrom().getLocationType().equals(Location.LocationTypeEnum.CUSTOMERACCOUNTLOCATION) ;
        assertEquals("WAL-MART CANADA CORP" , order.getOrderingParty().getName());
    }

    @Test
     void createOrderTestValidationError1() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();


        when(restTemplate.postForObject(anyString(), any(), eq(OrderResponse.class))).thenReturn(new OrderResponse());
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;

        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenAnswer(answer -> {
            CompletableFuture<Void> completedFuture = new CompletableFuture<>();
            completedFuture.completeExceptionally(new HttpClientErrorException(HttpStatus.FORBIDDEN));
            return completedFuture;
        });


        assertThrows(HttpClientErrorException.class, () -> applicationService.createOrder(order, userInfo, "eng"));
    }

    @Test
     void createOrderTestValidationError2() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();


        when(restTemplate.postForObject(anyString(), any(), eq(OrderResponse.class))).thenReturn(new OrderResponse());
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;

        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenAnswer(answer -> {
            CompletableFuture<Void> completedFuture = new CompletableFuture<>();
            completedFuture.completeExceptionally(new IndexOutOfBoundsException());
            return completedFuture;
        });


        assertThrows(SystemException.class, () -> applicationService.createOrder(order, userInfo, "eng"));
    }

    @Test
    void createOrderTestValidationError3() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();


        when(restTemplate.postForObject(anyString(), any(), eq(OrderResponse.class))).thenReturn(new OrderResponse());
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;

        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenAnswer(answer -> {
            CompletableFuture<Void> completedFuture = new CompletableFuture<>();
            completedFuture.completeExceptionally(new HttpServerErrorException(HttpStatus.BAD_GATEWAY));
            return completedFuture;
        });


        assertThrows(HttpServerErrorException.class, () -> applicationService.createOrder(order, userInfo, "eng"));
    }

    @Test
    void createOrderTestValidationError4() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();


        when(restTemplate.postForObject(anyString(), any(), eq(OrderResponse.class))).thenReturn(new OrderResponse());
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;

        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenAnswer(answer -> {
            CompletableFuture<Void> completedFuture = new CompletableFuture<>();
            completedFuture.completeExceptionally(new ResourceAccessException("Not Available"));
            return completedFuture;
        });
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;


        assertThrows(ResourceAccessException.class, () -> applicationService.createOrder(order, userInfo, "eng"));
    }

    @Test
    void createOrderTestError5() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK)) ;
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(Order.class))).thenReturn(new Order());
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString() ,  anyMap() , anyList())).thenThrow(new JsonProcessingException("Unable to parse"){});
        when(commonUtil.sanitizeOrder(any(Order.class))).thenReturn("") ;
        assertThrows(SystemException.class, () -> applicationService.createOrder(order, userInfo, "eng"));
    }



    @Test
     void updateOrderTestSuccess() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(MockTestData.jsonInput(), HttpStatus.CREATED));
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        assertEquals(Order.class, applicationService.updateOrder("orderId", order, "eng",userInfo).getClass());
    }

    @Test
    void updateOrderTestSuccessNotIMX() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        order.setSourceUpdateSystem("SCIO");
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(MockTestData.jsonInput(), HttpStatus.CREATED));
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        assertEquals(Order.class, applicationService.updateOrder("orderId", order, "eng",userInfo).getClass());
    }

    @Test
    void updateOrderTestSuccessNullEquipmentTypeCode() throws JsonProcessingException {
        Order order = MockTestData.jsonInput1();
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(MockTestData.jsonInput(), HttpStatus.CREATED));
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        assertEquals(Order.class, applicationService.updateOrder("orderId", order, "eng",userInfo).getClass());
    }

    @Test
    void updateOrderTestSuccessNullEquipmentOwnerCode() throws JsonProcessingException {
        Order order = MockTestData.jsonInput2();
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(MockTestData.jsonInput(), HttpStatus.CREATED));
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        assertEquals(Order.class, applicationService.updateOrder("orderId", order, "eng", userInfo).getClass());
    }

    @Test
    void updateOrderTestSuccessNullReferenceList() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(null , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(MockTestData.jsonInput(), HttpStatus.CREATED));
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        assertEquals(Order.class, applicationService.updateOrder("orderId", order, "eng", userInfo).getClass());
    }

    @Test
    void updateOrderTestSuccessEmptyReferenceList() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(new BusinessConstants[0] , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(MockTestData.jsonInput(), HttpStatus.CREATED));
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        assertEquals(Order.class, applicationService.updateOrder("orderId", order, "eng", userInfo).getClass());
    }

    @Test
    void updateOrderTestSuccessNullCustomerOrder() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        List<EmptyDelivery> orderEquipmentRequestList = new ArrayList<>();
        EmptyDelivery orderEquipmentRequest = new EmptyDelivery();
        List<Reference> referencesList = new ArrayList<>();
        Reference reference = new Reference();
        reference.setReferenceDesc("select string");
        referencesList.add(reference);
        orderEquipmentRequest.setOrderEquipmentReference(referencesList);
        orderEquipmentRequestList.add(orderEquipmentRequest);
        order.getCustomerOrder().setOrderEquipmentRequest(orderEquipmentRequestList);
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(MockTestData.jsonInput(), HttpStatus.CREATED));
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        assertEquals(Order.class, applicationService.updateOrder("orderId", order, "eng", userInfo).getClass());
    }

    @Test
    void updateOrderTestSuccessNullEquipmentRequest() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        order.getCustomerOrder().setOrderEquipmentRequest(null);
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(MockTestData.jsonInput(), HttpStatus.CREATED));
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        assertEquals(Order.class, applicationService.updateOrder("orderId", order, "eng", userInfo).getClass());
    }

    @Test
    void updateOrderTestSuccessNullShipmentequest() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        order.getCustomerOrder().setOrderShipmentRequest(null);
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(MockTestData.jsonInput(), HttpStatus.CREATED));
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        assertEquals(Order.class, applicationService.updateOrder("orderId", order, "eng", userInfo).getClass());
    }

    @Test
    void updateOrderTestSuccessEmptyEquipmentRequest() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        List<EmptyDelivery> orderEquipmentRequestList = new ArrayList<>();
        EmptyDelivery orderEquipmentRequest = new EmptyDelivery();
        List<Reference> referencesList = new ArrayList<>();
        Reference reference = new Reference();
        reference.setReferenceDesc("select string");
        referencesList.add(reference);
        orderEquipmentRequest.setOrderEquipmentReference(referencesList);
        orderEquipmentRequestList.add(orderEquipmentRequest);
        order.getCustomerOrder().setOrderEquipmentRequest(orderEquipmentRequestList);
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(MockTestData.jsonInput(), HttpStatus.CREATED));
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        assertEquals(Order.class, applicationService.updateOrder("orderId", order, "eng", userInfo).getClass());
    }

    @Test
    void updateOrderTestSuccessEmptyShipmentRequest() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        List<OrderShipmentRequest> orderShipmentRequestList = new ArrayList<>();
        OrderShipmentRequest orderShipmentRequest = new OrderShipmentRequest();
        List<Reference> referencesList = new ArrayList<>();
        Reference reference = new Reference();
        reference.setReferenceDesc("select string");
        referencesList.add(reference);
        orderShipmentRequest.setOrderShipmentReferences(referencesList);
        orderShipmentRequestList.add(orderShipmentRequest);
        order.getCustomerOrder().setOrderShipmentRequest(orderShipmentRequestList);
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(MockTestData.jsonInput(), HttpStatus.CREATED));
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        assertEquals(Order.class, applicationService.updateOrder("orderId", order, "eng", userInfo).getClass());
    }

    @Test
    void updateOrderAcceptedEventSuccess() {
        OrderStatusModel orderStatusModel = new OrderStatusModel();
        Equipment equipment = new Equipment();
        equipment.setAllocatedEquipmentOwner("E");
        equipment.setAllocatedEquipmentType("KC7");
        Accepted accepted = new Accepted();
        accepted.setEquipment(equipment);
        accepted.setStatus("Accepted");
        orderStatusModel.setOrderEquipmentStatusEvent(accepted);
        EquipmentTypeOwner equipmentTypeOwner = new EquipmentTypeOwner();
        equipmentTypeOwner.setEquipmentTypeOwnerCodeDesc("CN");
        EquipmentCode equipmentCode = new EquipmentCode();
        equipmentCode.setEnglishDescription("45' Dry");
        equipmentCode.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.KC7);
        List<EquipmentCode> equipmentCodeList = new ArrayList<>();
        equipmentCodeList.add(equipmentCode);
        equipmentTypeOwner.setEquipmentCode(equipmentCodeList);
        EquipmentTypeOwner[] equipmentTypeOwnerList = new EquipmentTypeOwner[1];
        equipmentTypeOwnerList[0] = equipmentTypeOwner;
        ResponseEntity<EquipmentTypeOwner[]> equipmentTypeOwnerEntity = new ResponseEntity<>(equipmentTypeOwnerList, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(EquipmentTypeOwner[].class))).thenReturn(equipmentTypeOwnerEntity);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(String.class))).thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));

        assertEquals("Success", applicationService.updateOrderEvent("123", orderStatusModel));
    }

    @Test
    void updateOrderAssignedEventSuccess() {
        OrderStatusModel orderStatusModel = new OrderStatusModel();
        Equipment equipment = new Equipment();
        equipment.setAllocatedEquipmentOwner("E");
        equipment.setAllocatedEquipmentType("KC7");
        equipment.setUsedEquipmentOwner("E");
        equipment.setUsedEquipmentType("KC7");
        Assigned assigned = new Assigned();
        assigned.setEquipment(equipment);
        assigned.setStatus("Assigned");
        orderStatusModel.setOrderEquipmentStatusEvent(assigned);
        EquipmentTypeOwner equipmentTypeOwner = new EquipmentTypeOwner();
        equipmentTypeOwner.setEquipmentTypeOwnerCodeDesc("CN");
        EquipmentCode equipmentCode = new EquipmentCode();
        equipmentCode.setEnglishDescription("45' Dry");
        equipmentCode.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.KC7);
        List<EquipmentCode> equipmentCodeList = new ArrayList<>();
        equipmentCodeList.add(equipmentCode);
        equipmentTypeOwner.setEquipmentCode(equipmentCodeList);
        EquipmentTypeOwner[] equipmentTypeOwnerList = new EquipmentTypeOwner[1];
        equipmentTypeOwnerList[0] = equipmentTypeOwner;
        ResponseEntity<EquipmentTypeOwner[]> equipmentTypeOwnerEntity = new ResponseEntity<>(equipmentTypeOwnerList, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(EquipmentTypeOwner[].class))).thenReturn(equipmentTypeOwnerEntity);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(String.class))).thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));
        assertEquals("Success", applicationService.updateOrderEvent("123", orderStatusModel));
    }

    @Test
    void updateOrderAssignedEventForDifferentEquipmentTypeSuccess() {
        OrderStatusModel orderStatusModel = new OrderStatusModel();
        Equipment equipment = new Equipment();
        equipment.setAllocatedEquipmentOwner("E");
        equipment.setAllocatedEquipmentType("KC7");
        equipment.setUsedEquipmentOwner("E");
        equipment.setUsedEquipmentType("KC4");
        Assigned assigned = new Assigned();
        assigned.setEquipment(equipment);
        assigned.setStatus("Assigned");
        orderStatusModel.setOrderEquipmentStatusEvent(assigned);
        EquipmentTypeOwner equipmentTypeOwner = new EquipmentTypeOwner();
        equipmentTypeOwner.setEquipmentTypeOwnerCodeDesc("CN");
        EquipmentCode equipmentCode = new EquipmentCode();
        equipmentCode.setEnglishDescription("45' Dry");
        equipmentCode.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.KC7);
        EquipmentCode equipmentCode2 = new EquipmentCode();
        equipmentCode2.setEnglishDescription("40' Dry");
        equipmentCode2.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.KC4);
        List<EquipmentCode> equipmentCodeList = new ArrayList<>();
        equipmentCodeList.add(equipmentCode);
        equipmentCodeList.add(equipmentCode2);
        equipmentTypeOwner.setEquipmentCode(equipmentCodeList);
        EquipmentTypeOwner[] equipmentTypeOwnerList = new EquipmentTypeOwner[1];
        equipmentTypeOwnerList[0] = equipmentTypeOwner;
        ResponseEntity<EquipmentTypeOwner[]> equipmentTypeOwnerEntity = new ResponseEntity<>(equipmentTypeOwnerList, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(EquipmentTypeOwner[].class))).thenReturn(equipmentTypeOwnerEntity);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(String.class))).thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));
        assertEquals("Success", applicationService.updateOrderEvent("123", orderStatusModel));
    }


    @Test
    void updateOrderIntransitEventSuccess() {
        OrderStatusModel orderStatusModel = new OrderStatusModel();
        Equipment equipment = new Equipment();
        equipment.setAllocatedEquipmentOwner("E");
        equipment.setAllocatedEquipmentType("KC7");
        Intransit intransit = new Intransit();
        intransit.setEquipment(equipment);
        intransit.setStatus("Intransit");
        orderStatusModel.setOrderEquipmentStatusEvent(intransit);
        EquipmentTypeOwner equipmentTypeOwner = new EquipmentTypeOwner();
        equipmentTypeOwner.setEquipmentTypeOwnerCodeDesc("CN");
        EquipmentCode equipmentCode = new EquipmentCode();
        equipmentCode.setEnglishDescription("45' Dry");
        equipmentCode.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.KC7);
        List<EquipmentCode> equipmentCodeList = new ArrayList<>();
        equipmentCodeList.add(equipmentCode);
        equipmentTypeOwner.setEquipmentCode(equipmentCodeList);
        EquipmentTypeOwner[] equipmentTypeOwnerList = new EquipmentTypeOwner[1];
        equipmentTypeOwnerList[0] = equipmentTypeOwner;
        ResponseEntity<EquipmentTypeOwner[]> equipmentTypeOwnerEntity = new ResponseEntity<>(equipmentTypeOwnerList, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(EquipmentTypeOwner[].class))).thenReturn(equipmentTypeOwnerEntity);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(String.class))).thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));
        assertEquals("Success", applicationService.updateOrderEvent("123", orderStatusModel));
    }

    @Test
    void updateOrderCancelledEventSuccess() {
        OrderStatusModel orderStatusModel = new OrderStatusModel();

        Cancelled cancelled = new Cancelled();
        cancelled.setStatus("Cancelled");
        CancellationReason cancellationReason= new CancellationReason();
        cancellationReason.setCancellationReasonCode("OTH");
        cancelled.setCancellationReason(cancellationReason);
        orderStatusModel.setOrderEquipmentStatusEvent(cancelled);
        BusinessConstants businessConstants= new BusinessConstants();
        businessConstants.setCategory(BusinessConstants.CategoryEnum.CANCELLATION_REASON);
        businessConstants.setCode("OTH");
        businessConstants.setDescription("Others");
        BusinessConstants[] businessConstantsList  = new BusinessConstants[1];
        businessConstantsList[0] = businessConstants;
        ResponseEntity<BusinessConstants[]> businessConstantEntity = new ResponseEntity<>(businessConstantsList, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(BusinessConstants[].class))).thenReturn(businessConstantEntity);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(String.class))).thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));
        assertEquals("Success", applicationService.updateOrderEvent("123", orderStatusModel));
    }


    @Test
    void updateOrderAssignedEventForDifferentEquipmentOwnerSuccess() {
        OrderStatusModel orderStatusModel = new OrderStatusModel();
        Equipment equipment = new Equipment();
        equipment.setAllocatedEquipmentOwner("E");
        equipment.setAllocatedEquipmentType("KC7");
        equipment.setUsedEquipmentOwner("C");
        equipment.setUsedEquipmentType("KC4");
        Assigned assigned = new Assigned();
        assigned.setEquipment(equipment);
        assigned.setStatus("Assigned");
        orderStatusModel.setOrderEquipmentStatusEvent(assigned);
        EquipmentTypeOwner equipmentTypeOwner = new EquipmentTypeOwner();
        equipmentTypeOwner.setEquipmentTypeOwnerCodeDesc("CN");
        EquipmentCode equipmentCode = new EquipmentCode();
        equipmentCode.setEnglishDescription("45' Dry");
        equipmentCode.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.KC7);
        EquipmentCode equipmentCode2 = new EquipmentCode();
        equipmentCode2.setEnglishDescription("40' Dry");
        equipmentCode2.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.KC4);
        List<EquipmentCode> equipmentCodeList = new ArrayList<>();
        equipmentCodeList.add(equipmentCode);
        equipmentCodeList.add(equipmentCode2);
        equipmentTypeOwner.setEquipmentCode(equipmentCodeList);
        EquipmentTypeOwner[] equipmentTypeOwnerList = new EquipmentTypeOwner[1];
        equipmentTypeOwnerList[0] = equipmentTypeOwner;
        ResponseEntity<EquipmentTypeOwner[]> equipmentTypeOwnerEntity = new ResponseEntity<>(equipmentTypeOwnerList, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(EquipmentTypeOwner[].class))).thenReturn(equipmentTypeOwnerEntity);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(String.class))).thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));
        assertEquals("Success", applicationService.updateOrderEvent("123", orderStatusModel));
    }


    @Test
    void updateOrderCompletedEventSuccess() {
        OrderStatusModel orderStatusModel = new OrderStatusModel();
        Equipment equipment = new Equipment();
        equipment.setAllocatedEquipmentOwner("E");
        equipment.setAllocatedEquipmentType("KC7");
        Completed completed = new Completed();
        completed.setEquipment(equipment);
        completed.setStatus("Completed");
        orderStatusModel.setOrderEquipmentStatusEvent(completed);
        EquipmentTypeOwner equipmentTypeOwner = new EquipmentTypeOwner();
        equipmentTypeOwner.setEquipmentTypeOwnerCodeDesc("CN");
        EquipmentCode equipmentCode = new EquipmentCode();
        equipmentCode.setEnglishDescription("45' Dry");
        equipmentCode.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.KC7);
        List<EquipmentCode> equipmentCodeList = new ArrayList<>();
        equipmentCodeList.add(equipmentCode);
        equipmentTypeOwner.setEquipmentCode(equipmentCodeList);
        EquipmentTypeOwner[] equipmentTypeOwnerList = new EquipmentTypeOwner[1];
        equipmentTypeOwnerList[0] = equipmentTypeOwner;
        ResponseEntity<EquipmentTypeOwner[]> equipmentTypeOwnerEntity = new ResponseEntity<>(equipmentTypeOwnerList, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(EquipmentTypeOwner[].class))).thenReturn(equipmentTypeOwnerEntity);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(String.class))).thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));
        assertEquals("Success", applicationService.updateOrderEvent("123", orderStatusModel));
    }


    @Test

    void testUpdateShipmentEvent(){
        doNothing().when(applicationService).getOrderPartyForTransportShipment(any());
        TransportShipmentStatusModel transportShipmentStatusModel = new TransportShipmentStatusModel();
        TransportShipmentStatusEvent transportShipmentStatusEvent = new TransportShipmentStatusEvent();
        transportShipmentStatusEvent.setStatus("Cancelled");
        Cancellation cancellation = new Cancellation();
        cancellation.setCancellationReasonCode("OTH");
        transportShipmentStatusEvent.setCancellation(cancellation);
        EqpDetails eqpDetails=new EqpDetails();
        eqpDetails.setEqpOwnTypeCd("C");
        eqpDetails.setEqpType("KC7");
        transportShipmentStatusEvent.setEqpDetails(eqpDetails);
        transportShipmentStatusModel.setTransportShipmentStatusEvent(transportShipmentStatusEvent);
        BusinessConstants businessConstants= new BusinessConstants();
        businessConstants.setCategory(BusinessConstants.CategoryEnum.CANCELLATION_REASON);
        businessConstants.setCode("OTH");
        businessConstants.setDescription("Others");
        BusinessConstants[] businessConstantsList  = new BusinessConstants[1];
        businessConstantsList[0] = businessConstants;
        EquipmentTypeOwner equipmentTypeOwner = new EquipmentTypeOwner();
        equipmentTypeOwner.setEquipmentTypeOwnerCodeDesc("CN");
        EquipmentCode equipmentCode = new EquipmentCode();
        equipmentCode.setEnglishDescription("53' Dry");
        equipmentCode.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.KC7);
        List<EquipmentCode> equipmentCodeList = new ArrayList<>();
        equipmentCodeList.add(equipmentCode);
        equipmentTypeOwner.setEquipmentCode(equipmentCodeList);
        EquipmentTypeOwner[] equipmentTypeOwnerList = new EquipmentTypeOwner[1];
        equipmentTypeOwnerList[0] = equipmentTypeOwner;
        ResponseEntity<EquipmentTypeOwner[]> equipmentTypeOwnerEntity = new ResponseEntity<>(equipmentTypeOwnerList, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(EquipmentTypeOwner[].class))).thenReturn(equipmentTypeOwnerEntity);
        ResponseEntity<BusinessConstants[]> businessConstantEntity = new ResponseEntity<>(businessConstantsList, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(BusinessConstants[].class))).thenReturn(businessConstantEntity);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Success",HttpStatus.OK);
        when(restTemplate.exchange(anyString(),eq(HttpMethod.PUT),any(),eq(String.class))).thenReturn(responseEntity);
        assertEquals("Success",applicationService.updateShipmentEvent("1234",transportShipmentStatusModel));
    }

    @Test
    void testGetOrderPartyForTransportShipmentForCustomerAccountLocation() throws JsonProcessingException {
        TransportShipmentStatusModel transportShipmentStatusModel = new TransportShipmentStatusModel();
        TransportShipmentStatusEvent transportShipmentStatusEvent = new TransportShipmentStatusEvent();
        List<StopDetails> stopDetailsList = new ArrayList<>();
        StopDetails stopDetails = new StopDetails();
        stopDetails.setCustLocSeq(123);
        stopDetails.setCustNumber("123");
        stopDetails.setLocType(Location.LocationTypeEnum.CUSTOMERACCOUNTLOCATION.getValue());
        stopDetailsList.add(stopDetails);
        transportShipmentStatusEvent.setStopDetails(stopDetailsList);
        transportShipmentStatusModel.setTransportShipmentStatusEvent(transportShipmentStatusEvent);
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        applicationService.getOrderPartyForTransportShipment(transportShipmentStatusModel);
        CustomerAccountLocation customerAccountLocation = (CustomerAccountLocation) stopDetails.getOrderParty();
        assertEquals(123,customerAccountLocation.getCustomerLocationSequenceNumber());
    }

    @Test
    void testGetOrderPartyForTransportShipmentForInterModelLocation() throws JsonProcessingException {
        TransportShipmentStatusModel transportShipmentStatusModel = new TransportShipmentStatusModel();
        TransportShipmentStatusEvent transportShipmentStatusEvent = new TransportShipmentStatusEvent();
        List<StopDetails> stopDetailsList = new ArrayList<>();
        StopDetails stopDetails = new StopDetails();
        stopDetails.setCustLocSeq(123);
        stopDetails.setCustNumber("123");
        stopDetails.setLocType(Location.LocationTypeEnum.INTERMODALLOCATION.getValue());
        stopDetailsList.add(stopDetails);
        transportShipmentStatusEvent.setStopDetails(stopDetailsList);
        transportShipmentStatusModel.setTransportShipmentStatusEvent(transportShipmentStatusEvent);
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        applicationService.getOrderPartyForTransportShipment(transportShipmentStatusModel);
        IntermodalLocation intermodalLocation = (IntermodalLocation) stopDetails.getOrderParty();
        assertEquals(123,intermodalLocation.getCustomerLocationSequenceNumber());
    }

    @Test
    void testGetOrderPartyForTransportShipmentForTerminal() throws JsonProcessingException {
        TransportShipmentStatusModel transportShipmentStatusModel = new TransportShipmentStatusModel();
        TransportShipmentStatusEvent transportShipmentStatusEvent = new TransportShipmentStatusEvent();
        List<StopDetails> stopDetailsList = new ArrayList<>();
        StopDetails stopDetails = new StopDetails();

        stopDetails.setFacilityNumber(new BigDecimal(123));
        stopDetails.setLocType(Location.LocationTypeEnum.FACILITY.getValue());
        stopDetailsList.add(stopDetails);
        transportShipmentStatusEvent.setStopDetails(stopDetailsList);
        transportShipmentStatusModel.setTransportShipmentStatusEvent(transportShipmentStatusEvent);
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        applicationService.getOrderPartyForTransportShipment(transportShipmentStatusModel);
        Facility facility = (Facility) stopDetails.getOrderParty();
        BigDecimal facilityNumber = new BigDecimal(123);
        assertEquals("123",facility.getFacilityNumber());
    }
    @Test
    void testGetOrderPartyForConveyorFulfillmentForCustomerAccountLocation() throws JsonProcessingException {
        OrderEquipmentStatusEvent orderEquipmentStatusEvent = new OrderEquipmentStatusEvent();
        List<StopDetails> stopDetailsList = new ArrayList<>();
        StopDetails stopDetails = new StopDetails();
        stopDetails.setCustLocSeq(123);
        stopDetails.setCustNumber("123");
        stopDetails.setLocType(Location.LocationTypeEnum.CUSTOMERACCOUNTLOCATION.getValue());
        stopDetailsList.add(stopDetails);
        orderEquipmentStatusEvent.setStopDetails(stopDetailsList);
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        applicationService.getOrderPartyForConveyorFulfillment(orderEquipmentStatusEvent);
        CustomerAccountLocation customerAccountLocation = (CustomerAccountLocation) stopDetails.getOrderParty();
        assertEquals(123,customerAccountLocation.getCustomerLocationSequenceNumber());
    }

    @Test
    void testGetOrderPartyForConveyorFulfillmentForInterModelLocation() throws JsonProcessingException {
        OrderEquipmentStatusEvent orderEquipmentStatusEvent = new OrderEquipmentStatusEvent();
        List<StopDetails> stopDetailsList = new ArrayList<>();
        StopDetails stopDetails = new StopDetails();
        stopDetails.setCustLocSeq(123);
        stopDetails.setCustNumber("123");
        stopDetails.setLocType(Location.LocationTypeEnum.INTERMODALLOCATION.getValue());
        stopDetailsList.add(stopDetails);
        orderEquipmentStatusEvent.setStopDetails(stopDetailsList);
        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        applicationService.getOrderPartyForConveyorFulfillment(orderEquipmentStatusEvent);
        IntermodalLocation intermodalLocation = (IntermodalLocation) stopDetails.getOrderParty();
        assertEquals(123,intermodalLocation.getCustomerLocationSequenceNumber());
    }

    @Test
    void testGetOrderPartyForConveyorFulfillmentForTerminal() throws JsonProcessingException {
        OrderEquipmentStatusEvent orderEquipmentStatusEvent = new OrderEquipmentStatusEvent();
        List<StopDetails> stopDetailsList = new ArrayList<>();
        StopDetails stopDetails = new StopDetails();

        stopDetails.setFacilityNumber(new BigDecimal(123));
        stopDetails.setLocType(Location.LocationTypeEnum.FACILITY.getValue());
        stopDetailsList.add(stopDetails);
        orderEquipmentStatusEvent.setStopDetails(stopDetailsList);
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        applicationService.getOrderPartyForConveyorFulfillment(orderEquipmentStatusEvent);
        Facility facility = (Facility) stopDetails.getOrderParty();
        BigDecimal facilityNumber = new BigDecimal(123);
        assertEquals("123",facility.getFacilityNumber());
    }


    @Test
    void testGetRampsSuccess() throws JsonProcessingException {
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getFacilityDetailsWithAddress() , HttpStatus.OK));

        when(restTemplate.exchange(eq("customer_domain_ILB_URL/get_customers"), eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerDetailsWithTerminal() , HttpStatus.OK));

        List<Facility> facilities = new ArrayList<>();
        facilities.add(new Facility());
        facilities.add(new Facility());
        assertEquals(facilities.size() , applicationService.getRamps("123" , BigDecimal.valueOf(2345)).size());
    }

    @Test
    void testGetRampsSuccess2() throws JsonProcessingException {
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getFacilityDetailsWithAddress() , HttpStatus.OK));

        when(restTemplate.exchange(eq("customer_domain_ILB_URL/get_customers"), eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerDetailsWithZeroTerminal() , HttpStatus.OK));
        ObjectMapper mapper = new ObjectMapper() ;
        List<Facility> facilities = Arrays.asList(mapper.readValue(MockTestData.getFacilityDetailsWithAddress().toJSONString() , Facility[].class)) ;

        assertEquals(facilities ,  applicationService.getRamps("123" , BigDecimal.valueOf(2345)));
    }

    @Test
    void testGetRampsSuccess3() throws JsonProcessingException {
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getFacilityDetailsWithAddress() , HttpStatus.OK));

        when(restTemplate.exchange(eq("customer_domain_ILB_URL/get_customers"), eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerDetailsWithZeroTerminalCAL() , HttpStatus.OK));
        ObjectMapper mapper = new ObjectMapper() ;
        List<Facility> facilities = Arrays.asList(mapper.readValue(MockTestData.getFacilityDetailsWithAddress().toJSONString() , Facility[].class)) ;

        assertEquals(facilities ,  applicationService.getRamps("123" , BigDecimal.valueOf(2345)));
    }

    @Test
    void testGetRampsError1() throws JsonProcessingException {
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getFacilityDetailsWithAddressWrongJSON() , HttpStatus.OK));

        when(restTemplate.exchange(eq("customer_domain_ILB_URL/get_customers"), eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerDetailsWithZeroTerminalCAL() , HttpStatus.OK));
        ObjectMapper mapper = new ObjectMapper() ;
        List<Facility> facilities = Arrays.asList(mapper.readValue(MockTestData.getFacilityDetailsWithAddress().toJSONString() , Facility[].class)) ;

        assertThrows(SystemException.class , () ->  applicationService.getRamps("123" , BigDecimal.ONE));
    }

    @Test
    void testGetRampsError2() throws JsonProcessingException {
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getFacilityDetailsWithAddressWrongJSON() , HttpStatus.OK));

        when(restTemplate.exchange(eq("customer_domain_ILB_URL/get_customers"), eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerDetailsWithNoLocation() , HttpStatus.OK));
        ObjectMapper mapper = new ObjectMapper() ;
        List<Facility> facilities = Arrays.asList(mapper.readValue(MockTestData.getFacilityDetailsWithAddress().toJSONString() , Facility[].class)) ;

        assertThrows(NotFoundException.class , () ->  applicationService.getRamps("123" , BigDecimal.ONE));
    }
    @Test
    void testCancelOrder() {
        String orderId = "123";
        Order expectedResponse = new Order();
        ResponseEntity<Order> mockedResponseEntity = ResponseEntity.ok(expectedResponse);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class)))
                .thenReturn(mockedResponseEntity);
        Order order=new Order();
        Order result = applicationService.cancelOrder(orderId,order);
        assertEquals(expectedResponse, result);
    }

    @Test
    void testCancelPartialOrder() {
        String orderId = "123";
        Order expectedResponse = new Order();
        ResponseEntity<Order> mockedResponseEntity = ResponseEntity.ok(expectedResponse);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class)))
                .thenReturn(mockedResponseEntity);
        Order order=new Order();
        Order result = applicationService.cancelPartialOrder(orderId,itemRequestId,order);
        assertEquals(expectedResponse, result);
    }

    @Test
    void getOrderByIdTest() throws JsonProcessingException {
        String orderId = "order_id";
        when(authUtil.generateAuthorizationHeader(anyString())).thenReturn(new HttpHeaders());
        Order expectedResponse = MockTestData.jsonInput();
        Reservation reservation = new Reservation();
        Facility destination = new Facility();
        destination.setFacilityNumber("123");
        destination.setFacilityType(FacilityType.TERMINAL);
        reservation.setDestinationStation(destination);
        expectedResponse.getCustomerOrder().getOrderShipmentRequest().get(0).setReservation(reservation);
        ResponseEntity<Order> mockedResponseEntity = ResponseEntity.ok(expectedResponse);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(Order.class),anyString()))
                .thenReturn(mockedResponseEntity);

        doNothing().when(applicationService).handleFutures(anyList());
        Assertions.assertNotNull(applicationService.getOrderById(orderId));
    }

    @Test
    void searchOrderTest(){
        OrdersSearchRequest ordersSearchRequest = new OrdersSearchRequest();
        String langKey = "en";
        Order[] orders = new Order[1];
        Order order = new Order();
        orders[0] = order;
        HttpHeaders headers = new HttpHeaders();
        headers.set("totalorders", "17");
        ResponseEntity<Order[]> responseEntity = new ResponseEntity<>(orders,headers,HttpStatus.OK);
        doReturn(responseEntity).when(restTemplate).postForEntity(anyString(), any(), eq(Order[].class));
        Assertions.assertEquals("17",applicationService.searchOrder(ordersSearchRequest, langKey).getHeaders().getFirst("totalorders"));
    }
    @Test
    void searchOrderTestForFrench(){
        OrdersSearchRequest ordersSearchRequest = new OrdersSearchRequest();
        String langKey = "fr";
        Order[] orders = new Order[1];
        Order order = new Order();
        orders[0] = order;
        HttpHeaders headers = new HttpHeaders();
        headers.set("totalorders", null);
        ResponseEntity<Order[]> responseEntity = new ResponseEntity<>(orders,headers,HttpStatus.OK);
        doReturn(responseEntity).when(restTemplate).postForEntity(anyString(), any(), eq(Order[].class));
        doReturn(order).when(multiLanguageService).returnFrenchTranslatedOrder(any(Order.class));
        Assertions.assertEquals("0",applicationService.searchOrder(ordersSearchRequest, langKey).getHeaders().getFirst("totalorders"));
    }
    @Test
    void checkForMexicoDestinationTest(){
        Order order = new Order();
        List<EmptyDelivery> orderEquipmentList = new ArrayList<>();
        EmptyDelivery emptyDelivery = new EmptyDelivery();
        Facility facility = new Facility();
        facility.setLocationType(Location.LocationTypeEnum.FACILITY);
        facility.setCarrierAbbreviation("MEXICO");
        emptyDelivery.setDestination(facility);
        emptyDelivery.setUiEquipmentId("123");
        EmptyDelivery emptyDelivery2 = new EmptyDelivery();
        Facility facility2 = new Facility();
        facility2.setLocationType(Location.LocationTypeEnum.FACILITY);
        facility2.setCarrierAbbreviation("CN");
        emptyDelivery2.setDestination(facility2);
        emptyDelivery2.setUiEquipmentId("123");
        orderEquipmentList.add(emptyDelivery);
        orderEquipmentList.add(emptyDelivery2);
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderEquipmentRequest(orderEquipmentList);
        order.setCustomerOrder(customerOrder);
        List<String> uiEquipmentIds = new ArrayList<>();
        applicationService.checkForMexicoDestination(order, uiEquipmentIds);
        assertEquals(1, uiEquipmentIds.size());
    }

    @Test
    void setMexicoDestinationTest(){
        Order order = new Order();
        List<EmptyDelivery> orderEquipmentList = new ArrayList<>();
        EmptyDelivery emptyDelivery = new EmptyDelivery();
        Facility facility = new Facility();
        facility.setLocationType(Location.LocationTypeEnum.FACILITY);
        facility.setCarrierAbbreviation("CN");
        emptyDelivery.setDestination(facility);
        emptyDelivery.setUiEquipmentId("123");
        EmptyDelivery emptyDelivery2 = new EmptyDelivery();
        Facility facility2 = new Facility();
        facility2.setLocationType(Location.LocationTypeEnum.FACILITY);
        facility2.setCarrierAbbreviation("CN");
        emptyDelivery2.setDestination(facility2);
        emptyDelivery2.setUiEquipmentId("456");
        orderEquipmentList.add(0,emptyDelivery);
        orderEquipmentList.add(1,emptyDelivery2);
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderEquipmentRequest(orderEquipmentList);
        order.setCustomerOrder(customerOrder);
        List<String> uiEquipmentIds = new ArrayList<>();
        uiEquipmentIds.add("123");
        applicationService.setMexicoDestination(order, uiEquipmentIds);
        Facility resultFacility = (Facility) order.getCustomerOrder().getOrderEquipmentRequest().get(0).getDestination();
        assertEquals("MEXICO", resultFacility.getCarrierAbbreviation());
    }

    @Test
    void updateOrderTest_switchDescriptionHSCs_normal() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        ShipmentCommodity cmdy = new ShipmentCommodity();
        // don't care about the values in cmdy

        HSCode hsc1 = new HSCode();
        hsc1.setHarmonizedCode("code1");
        hsc1.setHarmonizedCodeDesc("long description 1");
        HSCode hsc2 = new HSCode();
        hsc2.setHarmonizedCode("code2");
        hsc2.setHarmonizedCodeDesc("long description 2");
        cmdy.setHarmonizedSystemCodes(Arrays.asList(hsc1, hsc2));

        List<ShipmentCommodity> cmdys = Arrays.asList(cmdy);

        order.getCustomerOrder().getOrderShipmentRequest().get(0).setOrderShipmentCommodities(cmdys);

        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(order, HttpStatus.CREATED)); // return the same order
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        HarmonizedCode[] returnedHscs = new HarmonizedCode[2];
        returnedHscs[0] = new HarmonizedCode();
        returnedHscs[0].setHarmonizedCode("code1");
        returnedHscs[0].setHarmonizedDescription("short description 1");
        returnedHscs[1] = new HarmonizedCode();
        returnedHscs[1].setHarmonizedCode("code2");
        returnedHscs[1].setHarmonizedDescription("short description 2");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(HarmonizedCode[].class))).thenReturn(new ResponseEntity<>(returnedHscs, HttpStatus.OK));


        Order updatedOrder = applicationService.updateOrder("OrderID1", order, "EN", userInfo);

        // verify
        List<HSCode> hscs = updatedOrder.getCustomerOrder().getOrderShipmentRequest().get(0)
                .getOrderShipmentCommodities().get(0).getHarmonizedSystemCodes();

        HSCode returnedHsc1 = null, returnedHsc2 = null;
        for (HSCode hsc: hscs) {
            if (hsc.getHarmonizedCode().equals("code1")) {
                returnedHsc1 = hsc;
            } else if (hsc.getHarmonizedCode().equals("code2")) {
                returnedHsc2 = hsc;
            }
        }

        assertNotNull(returnedHsc1, "should not be null");
        assertNotNull(returnedHsc2, "should not be null");

        assertEquals("short description 1", returnedHsc1.getHarmonizedCodeDesc());
        assertEquals("short description 2", returnedHsc2.getHarmonizedCodeDesc());
    }

    @Test
    void updateOrderTest_switchDescriptionHSCs_emptyResponse() throws JsonProcessingException {
        // let's be real here..this case shouldn't ever happen irl
        // if the data is correct..which it should be

        Order order = MockTestData.jsonInput();
        ShipmentCommodity cmdy = new ShipmentCommodity();
        // don't care about the values in cmdy

        HSCode hsc1 = new HSCode();
        hsc1.setHarmonizedCode("code1");
        hsc1.setHarmonizedCodeDesc("long description 1");
        HSCode hsc2 = new HSCode();
        hsc2.setHarmonizedCode("code2");
        hsc2.setHarmonizedCodeDesc("long description 2");
        cmdy.setHarmonizedSystemCodes(Arrays.asList(hsc1, hsc2));

        List<ShipmentCommodity> cmdys = Arrays.asList(cmdy);

        order.getCustomerOrder().getOrderShipmentRequest().get(0).setOrderShipmentCommodities(cmdys);

        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(order, HttpStatus.CREATED)); // return the same order
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.NOT_FOUND)) ;


        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(HarmonizedCode[].class))).thenReturn(new ResponseEntity<>(null, HttpStatus.OK));


        Order updatedOrder = applicationService.updateOrder("OrderID1", order, "EN", userInfo);

        // verify
        List<HSCode> hscs = updatedOrder.getCustomerOrder().getOrderShipmentRequest().get(0)
                .getOrderShipmentCommodities().get(0).getHarmonizedSystemCodes();

        HSCode returnedHsc1 = null, returnedHsc2 = null;
        for (HSCode hsc: hscs) {
            if (hsc.getHarmonizedCode().equals("code1")) {
                returnedHsc1 = hsc;
            } else if (hsc.getHarmonizedCode().equals("code2")) {
                returnedHsc2 = hsc;
            }
        }

        assertNotNull(returnedHsc1, "should not be null");
        assertNotNull(returnedHsc2, "should not be null");

        assertEquals("long description 1", returnedHsc1.getHarmonizedCodeDesc());
        assertEquals("long description 2", returnedHsc2.getHarmonizedCodeDesc());
    }

    @Test
    void updateOrderTest_switchDescriptionHSCs_oneFoundOneNotFound() throws JsonProcessingException {
        // let's be real here..this case shouldn't ever happen irl
        // if the data is correct..which it should be

        Order order = MockTestData.jsonInput();
        ShipmentCommodity cmdy = new ShipmentCommodity();
        // don't care about the values in cmdy

        HSCode hsc1 = new HSCode();
        hsc1.setHarmonizedCode("code1");
        hsc1.setHarmonizedCodeDesc("long description 1");
        HSCode hsc2 = new HSCode();
        hsc2.setHarmonizedCode("code2");
        hsc2.setHarmonizedCodeDesc("long description 2");
        cmdy.setHarmonizedSystemCodes(Arrays.asList(hsc1, hsc2));

        List<ShipmentCommodity> cmdys = Arrays.asList(cmdy);

        order.getCustomerOrder().getOrderShipmentRequest().get(0).setOrderShipmentCommodities(cmdys);

        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(order, HttpStatus.CREATED)); // return the same order
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.NOT_FOUND)) ;

        HarmonizedCode[] returnedHscs = new HarmonizedCode[1];
        returnedHscs[0] = new HarmonizedCode();
        returnedHscs[0].setHarmonizedCode("code1");
        returnedHscs[0].setHarmonizedDescription("short description 1");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(HarmonizedCode[].class))).thenReturn(new ResponseEntity<>(returnedHscs, HttpStatus.OK));



        Order updatedOrder = applicationService.updateOrder("OrderID1", order, "EN", userInfo);

        // verify
        List<HSCode> hscs = updatedOrder.getCustomerOrder().getOrderShipmentRequest().get(0)
                .getOrderShipmentCommodities().get(0).getHarmonizedSystemCodes();

        HSCode returnedHsc1 = null, returnedHsc2 = null;
        for (HSCode hsc: hscs) {
            if (hsc.getHarmonizedCode().equals("code1")) {
                returnedHsc1 = hsc;
            } else if (hsc.getHarmonizedCode().equals("code2")) {
                returnedHsc2 = hsc;
            }
        }

        assertNotNull(returnedHsc1, "should not be null");
        assertNotNull(returnedHsc2, "should not be null");


        assertEquals("short description 1", returnedHsc1.getHarmonizedCodeDesc());
        assertEquals("long description 2", returnedHsc2.getHarmonizedCodeDesc());
    }

    @Test
    void updateOrderTest_switchDescriptionHSCs_multipleCommodities() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        ShipmentCommodity cmdy1 = new ShipmentCommodity();
        ShipmentCommodity cmdy2 = new ShipmentCommodity();
        // don't care about the values in cmdy

        HSCode hsc1 = new HSCode();
        hsc1.setHarmonizedCode("code1");
        hsc1.setHarmonizedCodeDesc("long description 1");
        HSCode hsc2 = new HSCode();
        hsc2.setHarmonizedCode("code2");
        hsc2.setHarmonizedCodeDesc("long description 2");
        cmdy1.setHarmonizedSystemCodes(Arrays.asList(hsc1));
        cmdy2.setHarmonizedSystemCodes(Arrays.asList(hsc2));

        List<ShipmentCommodity> cmdys = Arrays.asList(cmdy1, cmdy2);

        order.getCustomerOrder().getOrderShipmentRequest().get(0).setOrderShipmentCommodities(cmdys);

        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(order, HttpStatus.CREATED)); // return the same order
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        HarmonizedCode[] returnedHscs = new HarmonizedCode[2];
        returnedHscs[0] = new HarmonizedCode();
        returnedHscs[0].setHarmonizedCode("code1");
        returnedHscs[0].setHarmonizedDescription("short description 1");
        returnedHscs[1] = new HarmonizedCode();
        returnedHscs[1].setHarmonizedCode("code2");
        returnedHscs[1].setHarmonizedDescription("short description 2");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(HarmonizedCode[].class))).thenReturn(new ResponseEntity<>(returnedHscs, HttpStatus.OK));


        Order updatedOrder = applicationService.updateOrder("OrderID1", order, "EN", userInfo);

        // verify
        List<HSCode> hscs = new ArrayList<>();
        for (ShipmentCommodity cmdy: updatedOrder.getCustomerOrder().getOrderShipmentRequest().get(0)
                .getOrderShipmentCommodities()) {
            if (cmdy.getHarmonizedSystemCodes() != null) {
                hscs.addAll(cmdy.getHarmonizedSystemCodes());
            }
        }

        HSCode returnedHsc1 = null, returnedHsc2 = null;
        for (HSCode hsc: hscs) {
            if (hsc.getHarmonizedCode().equals("code1")) {
                returnedHsc1 = hsc;
            } else if (hsc.getHarmonizedCode().equals("code2")) {
                returnedHsc2 = hsc;
            }
        }

        assertNotNull(returnedHsc1, "should not be null");
        assertNotNull(returnedHsc2, "should not be null");

        assertEquals("short description 1", returnedHsc1.getHarmonizedCodeDesc());
        assertEquals("short description 2", returnedHsc2.getHarmonizedCodeDesc());
    }

    @Test
    void updateOrderTest_switchDescriptionHSCs_multipleCommodities_oneFoundOneNotFound() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        ShipmentCommodity cmdy1 = new ShipmentCommodity();
        ShipmentCommodity cmdy2 = new ShipmentCommodity();
        // don't care about the values in cmdy

        HSCode hsc2 = new HSCode();
        hsc2.setHarmonizedCode("code2");
        hsc2.setHarmonizedCodeDesc("long description 2");
        cmdy1.setHarmonizedSystemCodes(null);
        cmdy2.setHarmonizedSystemCodes(Arrays.asList(hsc2));

        List<ShipmentCommodity> cmdys = Arrays.asList(cmdy1, cmdy2);

        order.getCustomerOrder().getOrderShipmentRequest().get(0).setOrderShipmentCommodities(cmdys);

        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(order, HttpStatus.CREATED)); // return the same order
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        HarmonizedCode[] returnedHscs = new HarmonizedCode[1];
        returnedHscs[0] = new HarmonizedCode();
        returnedHscs[0].setHarmonizedCode("code2");
        returnedHscs[0].setHarmonizedDescription("short description 2");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(HarmonizedCode[].class))).thenReturn(new ResponseEntity<>(returnedHscs, HttpStatus.OK));


        Order updatedOrder = applicationService.updateOrder("OrderID1", order, "EN", userInfo);

        // verify
        List<HSCode> hscs = new ArrayList<>();
        for (ShipmentCommodity cmdy: updatedOrder.getCustomerOrder().getOrderShipmentRequest().get(0)
                .getOrderShipmentCommodities()) {
            if (cmdy.getHarmonizedSystemCodes() != null) {
                hscs.addAll(cmdy.getHarmonizedSystemCodes());
            }
        }

        HSCode returnedHsc1 = null, returnedHsc2 = null;
        for (HSCode hsc: hscs) {
            if (hsc.getHarmonizedCode().equals("code1")) {
                returnedHsc1 = hsc;
            } else if (hsc.getHarmonizedCode().equals("code2")) {
                returnedHsc2 = hsc;
            }
        }

        assertNull(returnedHsc1, "should be null");
        assertNotNull(returnedHsc2, "should not be null");

        assertEquals("short description 2", returnedHsc2.getHarmonizedCodeDesc());
    }

    @Test
    void updateOrderTest_switchDescriptionHSCs_multipleCommodities_neitherFound() throws JsonProcessingException {
        Order order = MockTestData.jsonInput();
        ShipmentCommodity cmdy1 = new ShipmentCommodity();
        ShipmentCommodity cmdy2 = new ShipmentCommodity();
        // don't care about the values in cmdy

        HSCode hsc1 = new HSCode();
        hsc1.setHarmonizedCode("code1");
        hsc1.setHarmonizedCodeDesc("long description 1");
        HSCode hsc2 = new HSCode();
        hsc2.setHarmonizedCode("code2");
        hsc2.setHarmonizedCodeDesc("long description 2");
        cmdy1.setHarmonizedSystemCodes(Arrays.asList(hsc1));
        cmdy2.setHarmonizedSystemCodes(Arrays.asList(hsc2));

        List<ShipmentCommodity> cmdys = Arrays.asList(cmdy1, cmdy2);

        order.getCustomerOrder().getOrderShipmentRequest().get(0).setOrderShipmentCommodities(cmdys);

        when(restTemplate.exchange(anyString() , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class))).thenReturn(new ResponseEntity<>(MockTestData.getCustomerResponse() , HttpStatus.OK)) ;
        when(asyncCalls.callReferenceDomainForTerminal(any(UriComponentsBuilder.class), anyString(), anyMap(), anyList())).thenReturn(CompletableFuture.completedFuture(null));
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(BusinessConstants[].class))).thenReturn(new ResponseEntity<>(MockTestData.getReferences() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString() , eq(HttpMethod.GET) , any(HttpEntity.class) , eq(EquipmentTypeOwner[].class))).thenReturn(new ResponseEntity<>(MockTestData.getEquipmentTypeOwner() , HttpStatus.OK)) ;
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(), eq(Order.class), anyString())).thenReturn(new ResponseEntity<>(order, HttpStatus.CREATED)); // return the same order
        when(breApiService.reformatEnglishErrorMessage(any())).thenReturn(order);
        when(restTemplate.exchange(eq("reference_ilb_url/search/facility") , eq(HttpMethod.POST) , any(HttpEntity.class) , eq(JSONArray.class)))
                .thenReturn(new ResponseEntity<>(MockTestData.getFacilityResponseUsingTerminalId() , HttpStatus.OK) ,new ResponseEntity<>(MockTestData.getFacilityResponseUsingCityState() , HttpStatus.OK) ) ;

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(HarmonizedCode[].class))).thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));


        Order updatedOrder = applicationService.updateOrder("OrderID1", order, "EN", userInfo);

        // verify
        List<HSCode> hscs = new ArrayList<>();
        for (ShipmentCommodity cmdy: updatedOrder.getCustomerOrder().getOrderShipmentRequest().get(0)
                .getOrderShipmentCommodities()) {
            if (cmdy.getHarmonizedSystemCodes() != null) {
                hscs.addAll(cmdy.getHarmonizedSystemCodes());
            }
        }

        HSCode returnedHsc1 = null, returnedHsc2 = null;
        for (HSCode hsc: hscs) {
            if (hsc.getHarmonizedCode().equals("code1")) {
                returnedHsc1 = hsc;
            } else if (hsc.getHarmonizedCode().equals("code2")) {
                returnedHsc2 = hsc;
            }
        }

        assertNotNull(returnedHsc1, "should not be null");
        assertNotNull(returnedHsc2, "should not be null");

        assertEquals("long description 1", returnedHsc1.getHarmonizedCodeDesc());
        assertEquals("long description 2", returnedHsc2.getHarmonizedCodeDesc());
    }
}
