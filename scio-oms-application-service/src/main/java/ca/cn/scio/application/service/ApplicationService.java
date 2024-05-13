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
package ca.cn.scio.application.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ca.cn.scio.application.resource.Facility;
import ca.cn.scio.application.resource.Order;
import ca.cn.scio.application.resource.OrderStatusModel;
import ca.cn.scio.application.resource.OrdersSearchRequest;
import ca.cn.scio.application.resource.TransportShipmentStatusModel;


public interface ApplicationService {
    Order createOrder(Order order, String userInfo,String langKey) ;
    Order updateOrder(String orderId,  Order order, String langKey, String userInfo);
    String updateOrderEvent(String orderEquipmentRequestId, OrderStatusModel orderStatusModel);
    String updateShipmentEvent(String transportShipmentId,TransportShipmentStatusModel transportShipmentStatusModel);
    List<Facility> getRamps(String customerNumber, BigDecimal customerLocationSequenceNumber);
    String discardOrder(String orderId);
    Order cancelPartialOrder(String orderId,String itemRequestId,Order order);
 
    Order cancelOrder(String orderId,Order order);
    Order getOrderById(String orderId);

    ResponseEntity<List<Order>> searchOrder(OrdersSearchRequest ordersSearchRequest,String langKey);
 

 

}
