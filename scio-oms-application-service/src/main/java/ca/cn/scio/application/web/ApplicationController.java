/*
 * Copyright (c) 1888-2022 CN, Inc.
 * 935 de La Gauchetière Street West, Montreal, Quebec, H3B 2M9, CANADA.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of CN
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with CN.
 */
package ca.cn.scio.application.web;


import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import ca.cn.scio.application.service.impl.BreApiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.cn.scio.application.resource.Facility;
import ca.cn.scio.application.resource.Order;
import ca.cn.scio.application.resource.OrderStatusModel;
import ca.cn.scio.application.resource.OrdersSearchRequest;
import ca.cn.scio.application.resource.TransportShipmentStatusModel;
import ca.cn.scio.application.service.ApplicationService;
import ca.cn.scio.application.service.impl.MultiLanguageServiceImpl;

/**
 * This code is aligned with the following positions from the Development Position Book (DPB):
 * <pre>
 *   Jackson JSON library:   https://kb.cn.ca/display/SPB/Jackson+JSON+library+-+BE
 *   JAXB:                   https://kb.cn.ca/display/SPB/JAXB+-+BE
 *   Java REST API Standard: https://kb.cn.ca/display/SPB/Java+REST+API+Standard+-+BE
 *   POJO and Marshalling:   https://kb.cn.ca/display/SPB/POJO+and+Marshalling+Pattern+-+BE
 *   Spring Boot Actuator:   https://kb.cn.ca/display/SPB/Spring+Boot+Actuator+-+BE
 * </pre>
 */
@RestController
@CrossOrigin(origins = "*")
public class ApplicationController implements V1Api {
    private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);
    private final ApplicationService applicationService ;
    private final BreApiServiceImpl breApiService;
    private final MultiLanguageServiceImpl multiLanguageService;
    @Autowired
    public ApplicationController(ApplicationService applicationService, BreApiServiceImpl breApiService, MultiLanguageServiceImpl multiLanguageService){
        this.applicationService = applicationService ;
        this.breApiService = breApiService;
        this.multiLanguageService = multiLanguageService;
    }
    private static final String TIME_ZONE_FORMAT = "UTC+05:30";
    private static final String LANG_KEY = "fr";

    /**
     * POST /v1/order
     *
     * @param order create order (optional)
     * @return OK (status code 201)
     * or BAD_REQUEST (status code 400)
     * or INTERNAL_SERVER_ERROR (status code 500)
     */
    @Override
    public ResponseEntity<Order> createOrder(String userInfo,String langKey, @Valid Order order) {


        log.info("Execution started for Create Order at {}",java.time.LocalTime.now(ZoneId.of(TIME_ZONE_FORMAT)));
        long appStartTime=System.currentTimeMillis();
        log.info("userInfo header received: {}", userInfo);
        Order response =   applicationService.createOrder(order, userInfo,langKey) ;
        log.info("Order with Order Number {} created | Total time taken {} ms",response.getOrderNumber() ,(System.currentTimeMillis()-appStartTime));
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> discardOrderRequest(String orderId) {
        return new ResponseEntity<>(applicationService.discardOrder(orderId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> getOrderById(String orderId, String langKey) {
        Order orderResponse = applicationService.getOrderById(orderId);
        if (LANG_KEY.equals(langKey)) {
            Order frenchTranslatedOrder = multiLanguageService.returnFrenchTranslatedOrder(orderResponse);
            return new ResponseEntity<>(frenchTranslatedOrder, HttpStatus.OK);
        } else {
            Order formattedEnglishErrorMessages = breApiService.reformatEnglishErrorMessage(orderResponse);
            return new ResponseEntity<>(formattedEnglishErrorMessages, HttpStatus.OK);
        }

    }


    @Override
    public ResponseEntity<Order> cancelPartialOrder(String orderId, String itemRequestId, @Valid Order order) {
        log.info("Execution started for Cancelling Partial Order at {}",java.time.LocalTime.now(ZoneId.of(TIME_ZONE_FORMAT)));
        long appStartTime=System.currentTimeMillis();
        Order response =   applicationService.cancelPartialOrder(orderId,itemRequestId,order) ;
        log.info("Partial Order Cancellation | Total time taken {} ms",(System.currentTimeMillis()-appStartTime));
        return new ResponseEntity<>(response , HttpStatus.OK);
    }



    @Override
    public ResponseEntity<Order> cancelOrder(String orderId, @Valid Order order) {

        log.info("Execution started for Cancelling Order at {}",java.time.LocalTime.now(ZoneId.of(TIME_ZONE_FORMAT)));
        long appStartTime=System.currentTimeMillis();
        Order response =   applicationService.cancelOrder(orderId,order) ;
        log.info("Order Cancellation | Total time taken {} ms",(System.currentTimeMillis()-appStartTime));
        return new ResponseEntity<>(response , HttpStatus.OK);    }


    @Override
    public ResponseEntity<List<Facility>> getRamps(@NotNull @Valid String customerNumber, @NotNull @Valid BigDecimal customerLocationSequenceNumber) {
        List<Facility> facilities = applicationService.getRamps(customerNumber , customerLocationSequenceNumber) ;
        return new ResponseEntity<>(facilities, HttpStatus.OK) ;
    }


    @Override
    public ResponseEntity<List<Order>> searchOrders(String langKey, @Valid OrdersSearchRequest ordersSearchRequest) {
/**
//        if(langKey.equals("fr")){
//           List<Order> orderList = applicationService.searchOrder(ordersSearchRequest);
//           List<Order> frenchOrderList = new ArrayList<>();
//           for(Order order : orderList){
//               frenchOrderList.add(multiLanguageService.returnFrenchTranslatedOrder(order));
//           }
//           return new ResponseEntity<>(frenchOrderList,HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>(applicationService.searchOrder(ordersSearchRequest),HttpStatus.OK);
//        }

        return applicationService.searchOrder(ordersSearchRequest,langKey);

*/
        return applicationService.searchOrder(ordersSearchRequest,langKey);

    }






    /**
     * PUT /v1/order/{orderId}
     *
     * @param orderId (required)
     * @param order   update order (optional)
     * @return OK (status code 201)
     * or ORDER_NOT_FOUND (status code 404)
     * or Internal Server Error (status code 500)
     * or BAD_REQUEST (status code 400)
     */
    @Override

    public ResponseEntity<Order> updateOrder(String orderId,String langKey,String userInfo, @Valid Order order) {
        log.info("Execution started for Update Order at {}",java.time.LocalTime.now(ZoneId.of(TIME_ZONE_FORMAT)));
        long appStartTime=System.currentTimeMillis();
        Order response = applicationService.updateOrder(orderId , order, langKey, userInfo) ;
        log.info("Order with Order Number {} updated | Total time taken {} ms",response.getOrderNumber() ,(System.currentTimeMillis()-appStartTime));
        return new ResponseEntity<>(response , HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> updateOrderEvent(String orderEquipmentRequestId, @Valid OrderStatusModel orderStatusModel) {
        return new ResponseEntity<>(applicationService.updateOrderEvent(orderEquipmentRequestId , orderStatusModel) , HttpStatus.OK);
    }



    @Override
    public ResponseEntity<String> updateShipmentEvent(String transportShipmentRequestId, @Valid TransportShipmentStatusModel transportShipmentStatusModel) {
        return new ResponseEntity<>(applicationService.updateShipmentEvent(transportShipmentRequestId,
                transportShipmentStatusModel),HttpStatus.OK);
    }
}
