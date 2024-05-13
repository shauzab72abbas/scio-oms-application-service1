///*
// * Copyright (c) 1888-2022 CN, Inc.
// * 935 de La Gauchetière Street West, Montreal, Quebec, H3B 2M9, CANADA.
// * All rights reserved.
// *
// * This software is the confidential and proprietary information of CN
// * You shall not disclose such Confidential Information and shall use it only in
// * accordance with the terms of the license agreement you entered into
// * with CN.

package ca.cn.scio.application.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ca.cn.scio.application.resource.OrdersSearchRequest;
import ca.cn.scio.application.service.impl.BreApiServiceImpl;
import ca.cn.scio.application.service.impl.MultiLanguageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import ca.cn.scio.application.resource.Order;
import ca.cn.scio.application.resource.OrderStatusModel;
import ca.cn.scio.application.resource.TransportShipmentStatusModel;
import ca.cn.scio.application.service.ApplicationService;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ApplicationControllerTest {

   @InjectMocks
    ApplicationController applicationController;
   @Mock
    ApplicationService applicationService;
   @Mock
    MultiLanguageServiceImpl multiLanguageService;
   @Mock
    BreApiServiceImpl breApiService;

    private String orderId = "9dac85eb-138d-4b53-a50b-a49561b6c0d2";
    private String itemRequestId="S22306080460";

   @Test
   void test1() {
       Order order=new Order();
       String userInfo = "userInfo" ;

       when(applicationService.createOrder(order, userInfo, "eng")).thenReturn(new Order());
       assertEquals(HttpStatus.CREATED, applicationController.createOrder(userInfo , "eng",order).getStatusCode());
   }

    @Test
    void updateOrderControllerTest(){
        Order order=new Order();
       when(applicationService.updateOrder(anyString() , eq(order) ,anyString(), anyString() )).thenReturn(new Order()) ;
       assertEquals(HttpStatus.OK , applicationController.updateOrder("orderId" , "eng", "userInfo" ,order).getStatusCode());

    }


    @Test
    void updateOrderEventControllerTest(){
        OrderStatusModel orderStatusModel=new OrderStatusModel();
        when(applicationService.updateOrderEvent(anyString() , eq(orderStatusModel) )).thenReturn(String.valueOf(new OrderStatusModel())) ;
        assertEquals(HttpStatus.OK , applicationController.updateOrderEvent("orderId" , orderStatusModel).getStatusCode());

    }

    @Test
    void updateShipmentEventControllerTest(){
        TransportShipmentStatusModel transportShipmentStatusModel=new TransportShipmentStatusModel();
        when(applicationService.updateShipmentEvent(anyString() , eq(transportShipmentStatusModel) )).thenReturn(String.valueOf(new OrderStatusModel())) ;
        assertEquals(HttpStatus.OK , applicationController.updateShipmentEvent("orderId" , transportShipmentStatusModel).getStatusCode());

    }

    @Test
    void getRampsTest(){
       when(applicationService.getRamps(anyString() , any(BigDecimal.class))).thenReturn(new ArrayList<>()) ;
       assertEquals(HttpStatus.OK , applicationController.getRamps("1234" , BigDecimal.valueOf(1234)).getStatusCode());

    }
    @Test
    void testDeleteOrder(){
        when(applicationService.discardOrder(orderId)).thenReturn(new String()) ;
        assertEquals(200 , applicationController.discardOrderRequest(orderId).getStatusCodeValue());
    }
    @Test
    void testCancelOrder(){
        Order order=new Order();
        when(applicationService.cancelOrder(orderId,order)).thenReturn(new Order()) ;
        assertEquals(200 , applicationController.cancelOrder(orderId,order).getStatusCodeValue());
    }
    @Test
    void testCancelPartialOrder(){
        Order order=new Order();
        when(applicationService.cancelPartialOrder(orderId,itemRequestId,order)).thenReturn(new Order()) ;
        assertEquals(200 , applicationController.cancelPartialOrder(orderId,itemRequestId,order).getStatusCodeValue());
    }
    @Test
    void getOrderByIdForEnglish(){
        Order order = new Order();
        when(applicationService.getOrderById(orderId)).thenReturn(order);
        when(breApiService.reformatEnglishErrorMessage(any(Order.class))).thenReturn(order);
        assertEquals(200 , applicationController.getOrderById(orderId,"en").getStatusCodeValue());
    }
    @Test
    void getOrderByIdForFrench(){
        Order order = new Order();
        when(applicationService.getOrderById(orderId)).thenReturn(order);
        when(multiLanguageService.returnFrenchTranslatedOrder(any(Order.class))).thenReturn(order);
        assertEquals(200 , applicationController.getOrderById(orderId,"fr").getStatusCodeValue());
    }
    @Test
    void searchOrdersTest(){
        OrdersSearchRequest ordersSearchRequest = new OrdersSearchRequest();
        String langKey = "en";
        List<Order> orders = new ArrayList<>();
        ResponseEntity<List<Order>> responseEntity = new ResponseEntity<>(orders, HttpStatus.OK);
        when(applicationService.searchOrder(ordersSearchRequest, langKey)).thenReturn(responseEntity);
        assertEquals(200 , applicationController.searchOrders(langKey,ordersSearchRequest).getStatusCodeValue());
    }


}
