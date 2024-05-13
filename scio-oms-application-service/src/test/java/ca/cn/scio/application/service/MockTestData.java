package ca.cn.scio.application.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.cn.scio.application.exception.SystemException;
import ca.cn.scio.application.resource.BusinessConstants;
import ca.cn.scio.application.resource.Country;
import ca.cn.scio.application.resource.CustomerAccountLocation;
import ca.cn.scio.application.resource.CustomerAccountLocationFacility;
import ca.cn.scio.application.resource.CustomerAccountLocationProfile;
import ca.cn.scio.application.resource.EquipmentCode;
import ca.cn.scio.application.resource.EquipmentTypeOwner;
import ca.cn.scio.application.resource.Facility;
import ca.cn.scio.application.resource.FacilityType;
import ca.cn.scio.application.resource.IntermodalLocation;
import ca.cn.scio.application.resource.Location;
import ca.cn.scio.application.resource.Order;
import ca.cn.scio.application.resource.Party;
import ca.cn.scio.application.resource.State;
import ca.cn.scio.application.util.CommonUtil;

public class MockTestData {
    static ObjectMapper objectMapper = new ObjectMapper();

//    public static OrderResponse getOrderResponse() throws JsonProcessingException {
//        String orderResponseString = "{\n" +
//                "    \"orderRequestId\": null,\n" +
//                "    \"orderId\": null,\n" +
//                "    \"orderNumber\": null,\n" +
//                "    \"errors\": [\n" +
//                "        {\n" +
//                "            \"errorCode\": \"SCIOBRE002\",\n" +
//                "            \"errorMessage\": \"Shipper information is mandatory\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//        return objectMapper.readValue(orderResponseString, OrderResponse.class);
//    }

    public static Order jsonInput() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"IM-Order\",\n" +
                "    \"sourceUpdateSystem\": \"IM-Order\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-567-3890-4444\",\n" +
                "            \"mobilePhone\": \"1-234-567-2890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KC2\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "            \"locationType\": \"Facility\",\n" +
                "            \"facilityNumber\": \"8625\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"facilityType\": \"Terminal\"\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"OS\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"string\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"OS\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"String\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"OS\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": null,\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
               "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputContactFirstName() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom45\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-567-3890-8444\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"select * from  table\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"String\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": null,\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);

    }

    public static Order jsonInputContactInvalidPhone() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"IM-Order\",\n" +
                "    \"sourceUpdateSystem\": \"IM-Order\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": null,\n" +
                "            \"mobilePhone\": \"1-234-567-890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KC2\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"OS\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"string\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"OS\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"String\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"OS\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": null,\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputSelect() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"select * from  table\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"select * from  table\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"select * from  table\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputInsert() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"insert into table\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"insert into table\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"insert into table\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentInsert() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"testing sql failed\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"insert into table\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"insert into table\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputFailedInsert() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"insert test123345667778\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"insert into table\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"insert into table\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentFailedInsert() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"testing sql failed\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"insert test123345667778\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"insert test123345667778\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputDelete() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"delete from  table\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"delete from  table\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"delete from  table\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentDelete() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"testing delete sql input for ship\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"delete from  table\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"delete from  table\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputUpdate() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +

                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"update table set\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"update table set\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"update table set\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentUpdate() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +

                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"shipmentTest\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"update table set\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"update table set\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputFailedUpdate() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"update table test123345678998765544\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"update table set\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"update table set\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentFailedUpdate() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"shipmentTest\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"update table test123345678998765544\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"update table test123345678998765544\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputCreate() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"create database table\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"create database table\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"create database table\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentCreate() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"shipmentest table\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"create database table\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"create database table\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputFailedCreate() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"create testsqlinjeectionwithinvalid\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"create database table\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"create database table\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentFailedCreate() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"shipmentest table\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"create testsqlinjeectionwithinvalid\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"create testsqlinjeectionwithinvalid\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputAlter() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"alter table customer\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"alter table customer\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"alter table customer\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentAlter() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"testingshipment\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"alter table customer\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"alter table customer\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputFailedAlter() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"alter sqlinjectionfailedInput\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"alter table customer\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"alter table customer\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentFailedAlter() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"testingshipment\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"alter sqlinjectionfailedInput\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"alter sqlinjectionfailedInput\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputBackup() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +

                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"backup database table\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"backup database table\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"backup database table\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentBackup() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +

                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"shipmenttest table\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"backup database table\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"backup database table\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputFailedBackup() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"backup Testsqlinjetion46544\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"backup database table\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"backup database table\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentFailedBackup() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"shipmenttest table\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"backup Testsqlinjetion46544\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"backup Testsqlinjetion46544\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputRefText() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"ab\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"backup Testsqlinjetion46544\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"backup Testsqlinjetion46544\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentRefText() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"shipmenttest table\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"ab\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"ab\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputLengthRefText() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"Test11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksasdfghjuytfgvb\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +

                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"backup Testsqlinjetion46544\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"backup Testsqlinjetion46544\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInputShipmentLengthRefText() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-562-1234-+234\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"shipmenttest table\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +
                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"Test11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksasdfghjuytfgvb\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": \"Test11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksTest11234567890!@#$%^&*()_+~|}{<>/?<,.aksasdfghjuytfgvb\",\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInput1() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"IM-Order\",\n" +
                "    \"sourceUpdateSystem\": \"IM-Order\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-567-3890-4444\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": null,\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "            \"locationType\": \"Facility\",\n" +
                "            \"facilityNumber\": \"8625\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"facilityType\": \"Terminal\"\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": null,\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": null,\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +
                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": null,\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": null\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Order jsonInput2() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"IM-Order\",\n" +
                "    \"sourceUpdateSystem\": \"IM-Order\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"1-234-567-3890-4444\",\n" +
                "            \"mobilePhone\": \"1-234-567-3890\",\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": null,\n" +
                "                    \"equipmentType\": \"KC2\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "            \"locationType\": \"Facility\",\n" +
                "            \"facilityNumber\": \"8625\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"facilityType\": \"Terminal\"\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": null,\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +
                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static JSONArray getCustomerDetailsWithTerminal() throws JsonProcessingException {
        String json = "[{\n" +
                "        \"customerAccountId\": \"uuid\",\n" +
                "        \"customerAccountNumber\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"customerAccountLocation\": [{\n" +
                "                \"locationType\": \"IntermodalLocation\",\n" +
                "                \"customerLocationSequenceNumber\": 0,\n" +
                "                \"customerAccountLocationProfile\": {\n" +
                "                    \"customerAccountLocationProfileId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002a\",\n" +
                "                    \"customerAccountNumber\": \"121212\",\n" +
                "                    \"customerLocationSequenceNumber\": 123,\n" +
                "\n" +
                "                    \"customerAccountLocationFacility\": [{\n" +
                "                            \"srcFacilityId\": 234,\n" +
                "                            \"srcFacilityNumber\": 180,\n" +
                "                            \"sequence\": \"1\"\n" +
                "                        }, \n" +
                "\t\t\t\t\t\t{\n" +
                "                            \"srcFacilityId\": 454,\n" +
                "                            \"srcFacilityNumber\": 181,\n" +
                "                            \"sequence\": \"2\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]\n" ;
        return objectMapper.readValue(json , JSONArray.class) ;
    }

    public static JSONArray getCustomerDetailsWithNoLocation() throws JsonProcessingException {
        String json = "[{\n" +
                "        \"customerAccountId\": \"uuid\",\n" +
                "        \"customerAccountNumber\": \"string\",\n" +
                "        \"name\": \"string\"\n" +
                "    }\n" +
                "]\n" ;
        return objectMapper.readValue(json , JSONArray.class) ;
    }

    public static JSONArray getCustomerDetailsWithZeroTerminal() throws JsonProcessingException {
        String json = "[{\n" +
                "        \"customerAccountId\": \"uuid\",\n" +
                "        \"customerAccountNumber\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"customerAccountLocation\": [{\n" +
                "                \"locationType\": \"IntermodalLocation\",\n" +
                "                \"customerLocationSequenceNumber\": 0,\n" +
                "                \"customerAccountLocationProfile\": {\n" +
                "                    \"customerAccountLocationProfileId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002a\",\n" +
                "                    \"customerAccountNumber\": \"121212\",\n" +
                "                    \"customerLocationSequenceNumber\": 123\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]\n" ;
        return objectMapper.readValue(json , JSONArray.class) ;
    }

    public static JSONArray getCustomerDetailsWithZeroTerminalCAL() throws JsonProcessingException {
        String json = "[{\n" +
                "        \"customerAccountId\": \"uuid\",\n" +
                "        \"customerAccountNumber\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"customerAccountLocation\": [{\n" +
                "                \"locationType\": \"CustomerAccountLocation\",\n" +
                "                \"customerLocationSequenceNumber\": 0,\n" +
                "                \"customerAccountLocationProfile\": {\n" +
                "                    \"customerAccountLocationProfileId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002a\",\n" +
                "                    \"customerAccountNumber\": \"121212\",\n" +
                "                    \"customerLocationSequenceNumber\": 123\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]\n" ;
        return objectMapper.readValue(json , JSONArray.class) ;
    }

    public static JSONArray getFacilityDetailsWithAddress() throws JsonProcessingException {
        String json = "[{\n" +
                "        \"locationType\": \"Facility\",\n" +
                "        \"city\": \"Prince Rupert\",\n" +
                "        \"state\": \"QC\",\n" +
                "        \"country\": \"CA\",\n" +
                "        \"facilityId\": \"firestore id\",\n" +
                "        \"facilityType\": \"Terminal\",\n" +
                "        \"facilityCode\": \"C\",\n" +
                "        \"facilityNumber\": \"19845\",\n" +
                "        \"srcFacilityNumber\": \"78785\",\n" +
                "        \"isPrimaryRamp\": true,\n" +
                "        \"splcCode\": \"12121\",\n" +
                "        \"facilityName\": \"Calgary\",\n" +
                "        \"facilityAltName\": \"string\",\n" +
                "        \"carrierAbbreviation\": \"AASE\",\n" +
                "        \"carrierNumber\": \"\",\n" +
                "        \"effectiveDate\": \"16/07/2022\",\n" +
                "        \"expiryDate\": \"16/07/2022\",\n" +
                "        \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"updatedTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"facilityAddress\": [{\n" +
                "                \"facilityAddressId\": \"uuid\",\n" +
                "                \"addressId\": 1,\n" +
                "                \"streetNumber\": \"17569\",\n" +
                "                \"streetNumberSuffix\": \"\",\n" +
                "                \"streetType\": \"AVE\",\n" +

                "                \"streetAddressUnit\": \"\",\n" +
                "                \"provinceStateCode\": \"QC\",\n" +
                "                \"countryCode\": \"CA\",\n" +
                "                \"postalCode\": \"V4N 3M4\",\n" +
                "                \"longitude\": 49.19129,\n" +
                "                \"latitude\": -122.735275,\n" +
                "                \"timeZoneOffset\": \"PT\",\n" +

                "                \"streetName\": \"104\",\n" +
                "                \"cityName\": \"SURREY\",\n" +
                "                \"addressCommonName\": \"Main Address\",\n" +
                "                \"effectiveDate\": \"16/07/2022\",\n" +
                "                \"expiryDate\": \"16/07/2022\",\n" +
                "                \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"updatedTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]\n" ;
        return objectMapper.readValue(json , JSONArray.class) ;
    }

    public static JSONArray getFacilityDetailsWithAddressWrongJSON() throws JsonProcessingException {
        String json = "[{\n" +
                "        \"locationType\": \"\",\n" +
                "        \"city\": \"Prince Rupert\",\n" +
                "        \"state\": \"QC\",\n" +
                "        \"country\": \"CA\",\n" +
                "        \"facilityId\": \"firestore id\",\n" +
                "        \"facilityType\": \"Terminal\",\n" +
                "        \"facilityCode\": \"C\",\n" +
                "        \"facilityNumber\": \"19845\",\n" +
                "        \"srcFacilityNumber\": \"78785\",\n" +
                "        \"isPrimaryRamp\": true,\n" +
                "        \"splcCode\": \"12121\",\n" +
                "        \"facilityName\": \"Calgary\",\n" +
                "        \"facilityAltName\": \"string\",\n" +
                "        \"carrierAbbreviation\": \"AASE\",\n" +
                "        \"carrierNumber\": \"\",\n" +
                "        \"effectiveDate\": \"16/07/2022\",\n" +
                "        \"expiryDate\": \"16/07/2022\",\n" +
                "        \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"updatedTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"facilityAddress\": [{\n" +
                "                \"facilityAddressId\": \"uuid\",\n" +
                "                \"addressId\": 1,\n" +
                "                \"streetNumber\": \"17569\",\n" +
                "                \"streetNumberSuffix\": \"\",\n" +
                "                \"streetType\": \"AVE\",\n" +

                "                \"streetAddressUnit\": \"\",\n" +
                "                \"provinceStateCode\": \"QC\",\n" +
                "                \"countryCode\": \"CA\",\n" +
                "                \"postalCode\": \"V4N 3M4\",\n" +
                "                \"longitude\": 49.19129,\n" +
                "                \"latitude\": -122.735275,\n" +
                "                \"timeZoneOffset\": \"PT\",\n" +

                "                \"streetName\": \"104\",\n" +
                "                \"cityName\": \"SURREY\",\n" +
                "                \"addressCommonName\": \"Main Address\",\n" +
                "                \"effectiveDate\": \"16/07/2022\",\n" +
                "                \"expiryDate\": \"16/07/2022\",\n" +
                "                \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"updatedTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]\n" ;
        return objectMapper.readValue(json , JSONArray.class) ;
    }

    public static Order orderInputErrCALSCIOAPP008() throws JsonProcessingException {

        String json = "{\n" +
                "    \"orderStatus\": \"SUBMITTED\",\n" +
                "    \"sourceCreateSystem\": \"SCIO\",\n" +
                "    \"createdBy\": \"SCIO\",\n" +
                "    \"orderType\": \"ORDER_REQUEST\",\n" +
                "    \"orderProductService\": \"DOOR_TO_DOOR\",\n" +
                "    \"deliveryType\": \"DOMESTIC\",\n" +
                "    \"materialType\": \"NON_HAZARDOUS\",\n" +


                "    \"orderingParty\": {\n" +
                "        \"customerAccountId\": \"00056417\",\n" +
                "        \"customerAccountNumber\": \"898566\",\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountStatusCode\": \"A\",\n" +
                "        \"orderPartyContact\": {\n" +
                "            \"firstName\": \"Tom\",\n" +
                "            \"lastName\": \"Hardy\",\n" +
                "            \"businessPhoneWithExtn\": \"\",\n" +
                "            \"mobilePhone\": 1234567890,\n" +
                "            \"orderContact\": \"Tom\",\n" +
                "            \"email\": \"vaibhavgautam.gautam@cn.ca\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"payerOfFreight\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868567\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\" \n" +
                "    },\n" +
                "    \"shipFrom\": {\n" +
                "        \"locationType\": \"IntermodalLocation\",\n" +
                "        \"customerLocationSequenceNumber\" : 3567 ,\n" +
                "        \"customer\" : {\n" +
                "            \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "            \"customerAccountNumber\": \"095794\",\n" +
                "            \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"shipper\": {\n" +
                "        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"868566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null\n" +
//                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "    \"orderNotification\": [\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"DECLINED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"notificationTrigger\": \"SUBMITTED\",\n" +
                "            \"notificationType\": \"EMAIL\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerOrder\": " +
                "        {\n" +
                "            \"customerOrderId\": \"\",\n" +
                "            \"versionKey\": 0,\n" +
                "            \"customerOrderStatus\": \"SUBMITTED\",\n" +
                "            \"currentVersion\": true,\n" +
                "            \"orderEquipmentRequest\": [\n" +
                "                {\n" +
                "                    \"equipmentOwnerDesc\": \"CN\",\n" +
                "                    \"equipmentTypeDesc\": \"53' Dry\",\n" +
                "                    \"orderEquipmentRequestId\": \"\",\n" +
                "                    \"orderEquipmentRequestNumber\": 0,\n" +
                "                    \"equipmentOwner\": \"C\",\n" +
                "                    \"equipmentType\": \"KW7\",\n" +
                "                    \"requestCreationTimestamp\": \"2022-12-16T12:05:07.570+00:00\",\n" +
                "                    \"equipmentOperationType\": \"DELIVERY\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-12-29T18:30:00.000+00:00\",\n" +
                "                    \"destination\": {\n" +
                "        \"name\": \"WALMART CANADA CORP\",\n" +
                "        \"customerAccountNumber\": \"860566\",\n" +
                "        \"customerAccountId\": \"hRrEoFiH0deq1jBUgRWV\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"IntermodalLocation\",\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerAccountLocationId\": null,\n" +
                "            \"customerLocationSequenceNumber\": 4932\n" +
                "        }\n" +
                "    },\n" +
                "                    \"guaranteeEquipmentProgram\": false,\n" +
                "                    \"orderEquipmentReference\": [\n" +
                "                        {\n" +
                "                            \"referenceType\": \"Shipment ID\",\n" +
                "                            \"referenceValue\": \"28652\",\n" +
                "                            \"referenceDesc\": \"string\",\n" +
                "                            \"primaryIndicator\": false,\n" +
                "                            \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appointmentWindow\": {\n" +
                "                        \"startTimestamp\": \"2022-12-16T18:31:00.000+00:00\",\n" +
                "                        \"endTimestamp\": \"2022-12-16T21:00:00.000+00:00\"\n" +
                "                    },\n" +
                "                    \"stopType\": \"DROP\"\n" +
                "                }\n" +
                "            ],\n" +
                "\"orderShipmentRequest\": [\n" +
                "                {\n" +
                "                    \"orderShipmentRequestId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                    \"orderShipmentRequestNumber\": \"123456789\",\n" +
                "                    \"requestedAtDestinationTimestamp\": \"2022-19-19T10:45:07.719\",\n" +
                "                    \"status\": \"SUBMITTED\",\n" +
                "                    \"paymentCode\": \"P\",\n" +
                "                    \"totalLadingWeight\": \"5\",\n" +
                "                    \"totalLadingWeightUom\":\"K\",\n" +
                "                    \"totalQuantity\": \"5\",\n" +
                "                    \"totalQuantityUom\":\"BAG\",\n" +
                "                    \"orderedEquipmentOwner\": \"C\",\n" +
                "                    \"orderedEquipmentOwnerDesc\": \"Description\",\n" +
                "                    \"orderedEquipmentType\": \"KC2\",\n" +
                "                    \"orderedEquipmentTypeDesc\": \"Description\",\n" +
                "                    \"srcCreateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"srcLastUpdateTimestamp\": \"2022-12-19T10:45:07.719\",\n" +
                "                    \"consignee\": {\n" +
                "                        \"customerAccountId\": \"00056417\",\n" +
                "                        \"customerAccountNumber\": \"868566\",\n" +
                "                        \"name\": \"WAL-MART CANADA CORP\",\n" +
                "                        \"address\": {\n" +
                "                            \"locationType\": \"CustomerAccountLocation\",\n" +
                "                            \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                            \"city\": \"Prince Rupert\",\n" +
                "                            \"state\": \"QC\",\n" +
                "                            \"country\": \"CA\",\n" +
                "                            \"customerAccountLocationId\": \"String\",\n" +
                "                            \"customerLocationSequenceNumber\": 0\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"shipTo\": {\n" +
                "                        \"locationType\": \"CustomerAccountLocation\",\n" +
                "                        \"customerLocationsiteId\": \"G35HBHELedzCx0t7Rq9Z\",\n" +
                "                        \"city\": \"Prince Rupert\",\n" +
                "                        \"state\": \"QC\",\n" +
                "                        \"country\": \"CA\",\n" +
                "                        \"customerLocationSequenceNumber\": 0,\n" +
                "                        \"customer\" : {\n" +
                "                                   \"name\": \"DMI 2012 SERVICES LTD\",\n" +
                "                                   \"customerAccountNumber\": \"868566\",\n" +
                "                                   \"customerAccountId\": \"0000GMVQxoJMHBDH0EIe\"\n" +
                "                           }\n" +

                "                    },\n" +
                "                    \"orderShipmentConveyor\": {\n" +
                "                        \"orderShipmentConveyorId\": \"String\",\n" +
                "                        \"equipment\": {\n" +
                "                            \"equipmentId\": \"CNRU57463\",\n" +
                "                            \"equipmentInit\": \"CNRU\",\n" +
                "                            \"equipmentNumber\": 100823,\n" +
                "                            \"checkDigit\": 5,\n" +
                "                            \"grossWeight\": 611,\n" +
                "                            \"grossWeightUom\": \"K\",\n" +
                "                            \"weightQualifier\": \"String\",\n" +
                "                            \"temperature\": 30,\n" +
                "                            \"temperatureUnit\": \"C\",\n" +
                "                            \"status\":\"null\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                   \"orderShipmentCommodities\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentCommodityId\": \"String\",\n" +
                "                            \"commodity\": {\n" +
                "                                \"stcc\": \"6777678\",\n" +
                "                                \"stccDescription\": \"COAL\"\n" +
                "                            },\n" +
                "                            \"harmonizedSystemCodes\": [\n" +
                "                                {\n" +
                "                                    \"harmonizedCode\": \"878787\",\n" +
                "                                    \"harmonizedCodeDesc\": \"COAL\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"orderShipmentTransportStops\": [\n" +
                "                        {\n" +
                "                            \"orderShipmentTransportStopId\": \"String\",\n" +
                "                            \"stopOperationType\": \"DELIVERY\",\n" +
                "                            \"stopType\": \"DROP\",\n" +
                "                            \"helperCount\": 4,\n" +
                "                            \"stopSequence\": 1,\n" +
                "                            \"appointmentWindow\": {\n" +
                "                                \"startTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                                \"endTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                            },\n" +
                "                            \"orderShipmentStopReferences\": [\n" +
                "                                {\n" +
                "                                    \"referenceType\": \"String\",\n" +
                "                                    \"referenceValue\": \"28652\",\n" +
                "                                    \"referenceDesc\": \"String\",\n" +
                "                                    \"primaryIndicator\": false,\n" +
                "                                    \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"location\": {\n" +
                "                                \"locationType\": \"Facility\",\n" +
                "                                \"facilityType\": \"Terminal\",\n" +
                "                                \"facilityNumber\": \"837465\"\n" +
//                "                                \"state\": \"QC\",\n" +
//                "                                \"country\": \"CA\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "          \"orderError\": [\n" +
                "            {\n" +
                "              \"orderErrorId\": null,\n" +
                "              \"errorId\": \"E539\",\n" +
                "              \"errorType\": \"ShipmentID Missing\",\n" +
                "              \"ruleConfigId\": \"B106\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"orderLog\": [\n" +
                "            {\n" +
                "              \"orderLogId\": null,\n" +
                "              \"processId\": \"P157\",\n" +
                "              \"processName\": \"OPERATIONAL\",\n" +
                "              \"logDateTime\": \"2020-04-14T10:45:07.719\",\n" +
                "              \"logType\": null,\n" +
                "              \"ruleConfigId\": null,\n" +
                "              \"currentStatus\": \"SUBMITTED\",\n" +
                "              \"previousStatus\": \"SUBMITTED\",\n" +
                "              \"createdTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cancellation\": {\n" +
                "            \"cancellationReasonCode\": \"T1584\",\n" +
                "            \"cancellationReasonDesc\": \"Expected delivery time is too long\",\n" +
                "            \"cancellationTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "            \"cancelledBy\": \"Hemanth\"\n" +
                "          },\n" +
                "          \"orderShipmentReferences\": [\n" +
                "            {\n" +
                "              \"referenceType\": \"Shipment ID\",\n" +
                "              \"referenceValue\": \"28652\",\n" +
                "              \"referenceDesc\": null,\n" +
                "              \"primaryIndicator\": false,\n" +
                "              \"referenceTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "\t\t ]\n" +
                "        }\n" +
                //"    ]\n" +
                "}\n" +
                "\n";

        return objectMapper.readValue(json, Order.class);


    }

    public static Map<String , Party> getEmptyCustomerDetails() {
        String[] customerKeys = new String[]{"095794-3567-IntermodalLocation", "095094-None-None",
                "868566-4932-IntermodalLocation", "860566-4932-IntermodalLocation", "898566-None-None"
                , "868566-None-None", "868566-0-CustomerAccountLocation"};
        Map<String, Party> customerDetails = new HashMap<>();
        for(String key : customerKeys)
            customerDetails.put(key , null)  ;
        return customerDetails ;

    }

    public static Map<String , List<Facility>> getRampDetails(){
        String[] customerKeys = new String[]{"095794-3567-IntermodalLocation" , "095094-None-None" ,
                "868566-4932-IntermodalLocation" , "860566-4932-IntermodalLocation" , "898566-None-None"
                , "868566-None-None" , "868566-0-CustomerAccountLocation"} ;
        Map<String , List<Facility>> rampDetails  = new HashMap<>() ;
        for (String key : customerKeys) {
            String[] splits = key.split("-");
            String customerNumber = splits[0];
            String locSeq = splits[1];
            switch (customerNumber) {
                case "095794": {
                    List<Facility> facilities = new ArrayList<>() ;
                    Facility facility = new Facility() ;
                    facility.setIsPrimaryRamp(true) ;
                    facility.setLocationType(Location.LocationTypeEnum.FACILITY) ;
                    facility.setFacilityNumber("1234");
                    facility.setSrcFacilityNumber(12345);
                    Facility facility1 = new Facility() ;
                    facility1.setIsPrimaryRamp(false) ;
                    facility1.setLocationType(Location.LocationTypeEnum.FACILITY) ;
                    facility1.setFacilityNumber("1235");
                    facility1.setSrcFacilityNumber(1236);
                    facilities.add(facility) ;
                    facilities.add(facility1) ;
                    rampDetails.put(key , facilities) ;
                    break;
                }
                case "868566": {

                    if (locSeq.equals("4932")) {
                        List<Facility> facilities = new ArrayList<>() ;
                        Facility facility = new Facility() ;
                        facility.setIsPrimaryRamp(true) ;
                        facility.setLocationType(Location.LocationTypeEnum.FACILITY) ;
                        facility.setFacilityNumber("2345");
                        facility.setSrcFacilityNumber(12345);
                        facilities.add(facility) ;
                        rampDetails.put(key , facilities) ;

                    }
                    break;
                }
            }
        }
        return  rampDetails ;

    }

    public static Map<String , Party> getCustomerDetails(){
        String[] customerKeys = new String[]{"095794-3567-IntermodalLocation" , "095094-None-None" ,
                "868566-4932-IntermodalLocation" , "860566-4932-IntermodalLocation" , "898566-None-None"
                , "868566-None-None" , "868566-0-CustomerAccountLocation"} ;
        Map<String , Party> customerDetails  = new HashMap<>() ;
        for (String key : customerKeys) {
            String[] splits = key.split("-");
            String customerNumber = splits[0];
            String locSeq = splits[1];
            switch (customerNumber) {
                case "095794": {
                    Party party = new Party();

                    party.setCustomerAccountNumber(customerNumber);
                    party.setName("ZIM INTEGRATED");

                    if (!locSeq.equals("None")) {
                        CustomerAccountLocation customerAccountLocation = new CustomerAccountLocation();
                        customerAccountLocation.setLocationType(Location.LocationTypeEnum.CUSTOMERACCOUNTLOCATION);
                        customerAccountLocation.setCustomerLocationSequenceNumber(Integer.parseInt(locSeq));
                        customerAccountLocation.setAddrLine1("#101 Brampton");
                        CustomerAccountLocationProfile customerAccountLocationProfile = new CustomerAccountLocationProfile() ;
                        customerAccountLocationProfile.setCustomerLocationSequenceNumber(Integer.parseInt(locSeq));
                        customerAccountLocationProfile.setCustomerAccountNumber(customerNumber);
                        CustomerAccountLocationFacility customerAccountLocationFacility = new CustomerAccountLocationFacility() ;
                        customerAccountLocationFacility.setSrcFacilityNumber(1234);
                        customerAccountLocationFacility.setSequence("1");
                        CustomerAccountLocationFacility customerAccountLocationFacility1 = new CustomerAccountLocationFacility() ;
                        customerAccountLocationFacility1.setSrcFacilityNumber(1235);
                        customerAccountLocationFacility1.setSequence("2");
                        List<CustomerAccountLocationFacility> customerAccountLocationFacilities = new ArrayList<>() ;
                        customerAccountLocationFacilities.add(customerAccountLocationFacility) ;
                        customerAccountLocationFacilities.add(customerAccountLocationFacility1) ;

                        customerAccountLocationProfile.setCustomerAccountLocationFacility(customerAccountLocationFacilities);
                        party.setAddress(customerAccountLocation);
                    }

                    customerDetails.put(key, party);
                    break;
                }
                case "868566": {
                    Party party = new Party();
                    party.setCustomerAccountNumber(customerNumber);
                    party.setName("BREWERS");
                    if (locSeq.equals("4932")) {
                        IntermodalLocation customerAccountLocation = new IntermodalLocation();
                        customerAccountLocation.setLocationType(Location.LocationTypeEnum.INTERMODALLOCATION);
                        customerAccountLocation.setCustomerLocationSequenceNumber(Integer.parseInt(locSeq));
                        customerAccountLocation.setAddrLine1("#101 Montreal");
                        customerAccountLocation.setCountry(Country.CA);
                        customerAccountLocation.setState(State.AB);
                        party.setAddress(customerAccountLocation);
                    } else if (locSeq.equals("0")) {
                        IntermodalLocation customerAccountLocation = new IntermodalLocation();
                        customerAccountLocation.setLocationType(Location.LocationTypeEnum.INTERMODALLOCATION);
                        customerAccountLocation.setCustomerLocationSequenceNumber(Integer.parseInt(locSeq));
                        customerAccountLocation.setAddrLine1("#121 Address1");
                        customerAccountLocation.setCountry(Country.CA);
                        customerAccountLocation.setState(State.AB);
                        party.setAddress(customerAccountLocation);
                    }

                    customerDetails.put(key, party);
                    break;
                }
                case "898566": {
                    Party party = new Party();

                    party.setCustomerAccountNumber(customerNumber);
                    party.setName("TRANSX");
                    customerDetails.put(key, party);

                    break;
                }
            }
        }
        return  customerDetails ;

    }

    public static Map<String , Facility> getFacilityDetails(){
        String[] facilityKeys = new String[]{"837465-Terminal"} ;
        Map<String , Facility> facilities = new HashMap<>() ;
        for (String key : facilityKeys) {
            String[] splits = key.split("-");
            String facilityNumber = splits[0];
            String facilityType = splits[1];
            if ("837465".equals(facilityNumber)) {
                Facility terminal = new Facility();
                terminal.setFacilityNumber(facilityNumber);
                terminal.setFacilityType(FacilityType.TERMINAL);
                terminal.setCity("LA");
                terminal.setCountry(Country.CA);
                facilities.put(key, terminal);
            }

        }
        return facilities;


    }

    public static BusinessConstants[] getReferences(){
        List<BusinessConstants> businessConstants= new ArrayList<>();
        BusinessConstants businessConstants1=new BusinessConstants();
        businessConstants1.setCategory(BusinessConstants.CategoryEnum.valueOf("REFERENCE_TYPE"));
        businessConstants1.setCode("OS");
        businessConstants1.setValue("OUTBOUND_FROM_PARTY");
        businessConstants.add(businessConstants1);
        return businessConstants.toArray(new BusinessConstants[0]);
    }

    public static EquipmentTypeOwner[] getEquipmentTypeOwner(){
        List<EquipmentTypeOwner> equipmentTypeOwners= new ArrayList<>();
        EquipmentTypeOwner equipmentTypeOwner=new EquipmentTypeOwner();
        equipmentTypeOwner.setEquipmentTypeOwnerCodeDesc("CN");
        equipmentTypeOwner.setEquipmentTypeOwnerCode(EquipmentTypeOwner.EquipmentTypeOwnerCodeEnum.valueOf("C"));
        List<EquipmentCode> equipmentCodes=new ArrayList<>();
        EquipmentCode equipmentCode=new EquipmentCode();
        equipmentCode.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.valueOf("KC2"));
        equipmentCode.setEnglishDescription("40' Dry Container");
        EquipmentCode equipmentCode1=new EquipmentCode();
        equipmentCode1.setEnglishDescription("Container");
        EquipmentCode equipmentCode2=new EquipmentCode();
        equipmentCode2.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.valueOf("KC7"));
        equipmentCode2.setEnglishDescription("53' Dry Container");
        EquipmentCode equipmentCode3=new EquipmentCode();
        equipmentCode3.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.valueOf("KC6"));
        EquipmentCode equipmentCode4=new EquipmentCode();
        equipmentCode4.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.valueOf("KC6"));
        equipmentCode4.setEnglishDescription("");
        EquipmentCode equipmentCode5=new EquipmentCode();
        equipmentCode5.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.valueOf("KC2"));
        equipmentCode5.setEnglishDescription("");
        EquipmentCode equipmentCode6=new EquipmentCode();
        equipmentCode6.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.valueOf("KC2"));
        equipmentCodes.add(equipmentCode);
        equipmentCodes.add(equipmentCode1);
        equipmentCodes.add(equipmentCode2);
        equipmentCodes.add(equipmentCode3);
        equipmentCodes.add(equipmentCode4);
        equipmentCodes.add(equipmentCode5);
        equipmentCodes.add(equipmentCode6);
        equipmentTypeOwner.setEquipmentCode(equipmentCodes);
        equipmentTypeOwners.add(equipmentTypeOwner);

        EquipmentTypeOwner equipmentTypeOwner1=new EquipmentTypeOwner();
        equipmentTypeOwner1.setEquipmentTypeOwnerCodeDesc("");
        equipmentTypeOwner1.setEquipmentTypeOwnerCode(EquipmentTypeOwner.EquipmentTypeOwnerCodeEnum.valueOf("C"));

        EquipmentTypeOwner equipmentTypeOwner2=new EquipmentTypeOwner();
        equipmentTypeOwner2.setEquipmentTypeOwnerCode(EquipmentTypeOwner.EquipmentTypeOwnerCodeEnum.valueOf("C"));

        equipmentTypeOwners.add(equipmentTypeOwner1);
        equipmentTypeOwners.add(equipmentTypeOwner2);
        return equipmentTypeOwners.toArray(new EquipmentTypeOwner[0]);
    }

    public static JSONArray getCustomerResponse() throws JsonProcessingException {
        String json = "[{\n" +


                "  \"customerAccountStatusCode\": \"A\",\n" +
                "  \"customerAccountStatusDesc\": \"Active\",\n" +
                "  \"preferredLanguageTypeCode\": \"ENG\",\n" +
                "  \"preferredLanguageTypeName\": \"English\",\n" +
                "  \"name\": \"INTERFOR CORPORATION\",\n" +
                "  \"customerAccountNumber\": \"868566\",\n" +
                "  \"customerAccountLocation\": [\n" +
                "    {\n" +

                "      \"locationType\": \"CustomerAccountLocation\",\n" +
                "      \"customerLocationSequenceNumber\": 4932,\n" +
                "      \"city\": \"CASTLEGAR\",\n" +
                "      \"streetNumber\": null,\n" +
                "      \"suffix\": null,\n" +
                "      \"streetName\": null,\n" +
                "      \"streetType\": null,\n" +
                "      \"streetDirection\": null,\n" +
                "      \"state\": \"BC\",\n" +
                "      \"country\": \"CA\",\n" +
                "      \"addrLine1\": \"2705 ARROW LAKES DR\",\n" +
                "      \"addrLine2\": null,\n" +
                "      \"addrLine3\": null,\n" +
                "      \"timeZone\": \"PT\",\n" +
                "\"customerAccountLocationProfile\": {\n" +
                "          \"customerAccountLocationProfileId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002a\",\n" +
                "          \"srcProfileId\": 1233,\n" +
                "          \"profileType\": \"CUS\",\n" +
                "          \"profileStatus\": \"A\",\n" +
                "          \"effectiveDate\": \"\",\n" +
                "          \"expiryDate\": \"\",\n" +
                "          \"doorCount\": 5,\n" +
                "          \"customerAccountNumber\": \"121212\",\n" +
                "          \"customerLocationSequenceNumber\": 123,\n" +
                "          \"customerReferenceLocationType\": \"\",\n" +
                "          \"locationProfileUpdateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "          \"equipmentGridMinimumCode\": \"A\",\n" +
                "          \"equipmentGridMaximumCode\": \"A\",\n" +
                "          \"truckMaximumWeight\": \"\",\n" +
                "          \"tracSizeCode\": \"\",\n" +
                "          \"srcCreateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "          \"srcLastUpdateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "          \"locationCommonName\": \"DEFAULT LOCATION\",\n" +
                "          \"createdTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "          \"updatedTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "          \"customerAccountLocationFacility\": [\n" +
                "            {\n" +
                "              \"customerAccountLocationFacilityId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002a\",\n" +
                "              \"srcFacilityId\": 234,\n" +
                "              \"srcFacilityNumber\": 180,\n" +
                "              \"usageCode\": \"\",\n" +
                "              \"sequence\": \"1\",\n" +
                "              \"srcCreateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "              \"srcLastUpdateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "              \"customerAccountLocationProfileId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002a\"\n" +
                "            } , \n" +
                "\t\t\t{\n" +
                "              \"customerAccountLocationFacilityId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002b\",\n" +
                "              \"srcFacilityId\": 2341,\n" +
                "              \"srcFacilityNumber\": 1801,\n" +
                "              \"usageCode\": \"\",\n" +
                "              \"sequence\": \"2\",\n" +
                "              \"srcCreateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "              \"srcLastUpdateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "              \"customerAccountLocationProfileId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002b\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n"+
                "    } ,\n" +
                "    {\n" +

                "      \"locationType\": \"IntermodalLocation\",\n" +
                "      \"customerLocationSequenceNumber\": 0,\n" +
                "      \"city\": \"CASTLEGAR\",\n" +
                "      \"streetNumber\": null,\n" +
                "      \"suffix\": null,\n" +
                "      \"streetName\": null,\n" +
                "      \"streetType\": null,\n" +
                "      \"streetDirection\": null,\n" +
                "      \"state\": \"BC\",\n" +
                "      \"country\": \"CA\",\n" +
                "      \"addrLine1\": \"2705 ARROW LAKES DR\",\n" +
                "      \"addrLine2\": null,\n" +
                "      \"addrLine3\": null,\n" +
                "      \"timeZone\": \"PT\"\n" +
                "    } \n" +
                "  ]\n" +
                "}]";
        return objectMapper.readValue(json, JSONArray.class);
    }

    public static JSONArray getCustomerResponseWithSameFacilityNumber() throws JsonProcessingException {
        String json = "[{\n" +


                "  \"customerAccountStatusCode\": \"A\",\n" +
                "  \"customerAccountStatusDesc\": \"Active\",\n" +
                "  \"preferredLanguageTypeCode\": \"ENG\",\n" +
                "  \"preferredLanguageTypeName\": \"English\",\n" +
                "  \"name\": \"INTERFOR CORPORATION\",\n" +
                "  \"customerAccountNumber\": \"868566\",\n" +
                "  \"customerAccountLocation\": [\n" +
                "    {\n" +

                "      \"locationType\": \"CustomerAccountLocation\",\n" +
                "      \"customerLocationSequenceNumber\": 4932,\n" +
                "      \"city\": \"CASTLEGAR\",\n" +
                "      \"streetNumber\": null,\n" +
                "      \"suffix\": null,\n" +
                "      \"streetName\": null,\n" +
                "      \"streetType\": null,\n" +
                "      \"streetDirection\": null,\n" +
                "      \"state\": \"BC\",\n" +
                "      \"country\": \"CA\",\n" +
                "      \"addrLine1\": \"2705 ARROW LAKES DR\",\n" +
                "      \"addrLine2\": null,\n" +
                "      \"addrLine3\": null,\n" +
                "      \"timeZone\": \"PT\",\n" +
                "\"customerAccountLocationProfile\": {\n" +
                "          \"customerAccountLocationProfileId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002a\",\n" +
                "          \"srcProfileId\": 1233,\n" +
                "          \"profileType\": \"CUS\",\n" +
                "          \"profileStatus\": \"A\",\n" +
                "          \"effectiveDate\": \"\",\n" +
                "          \"expiryDate\": \"\",\n" +
                "          \"doorCount\": 5,\n" +
                "          \"customerAccountNumber\": \"121212\",\n" +
                "          \"customerLocationSequenceNumber\": 123,\n" +
                "          \"customerReferenceLocationType\": \"\",\n" +
                "          \"locationProfileUpdateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "          \"equipmentGridMinimumCode\": \"A\",\n" +
                "          \"equipmentGridMaximumCode\": \"A\",\n" +
                "          \"truckMaximumWeight\": \"\",\n" +
                "          \"tracSizeCode\": \"\",\n" +
                "          \"srcCreateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "          \"srcLastUpdateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "          \"locationCommonName\": \"DEFAULT LOCATION\",\n" +
                "          \"createdTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "          \"updatedTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "          \"customerAccountLocationFacility\": [\n" +
                "            {\n" +
                "              \"customerAccountLocationFacilityId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002a\",\n" +
                "              \"srcFacilityId\": 234,\n" +
                "              \"srcFacilityNumber\": 180,\n" +
                "              \"usageCode\": \"\",\n" +
                "              \"sequence\": \"1\",\n" +
                "              \"srcCreateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "              \"srcLastUpdateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "              \"customerAccountLocationProfileId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002a\"\n" +
                "            } , \n" +
                "\t\t\t{\n" +
                "              \"customerAccountLocationFacilityId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002b\",\n" +
                "              \"srcFacilityId\": 2341,\n" +
                "              \"srcFacilityNumber\": 180,\n" +
                "              \"usageCode\": \"\",\n" +
                "              \"sequence\": \"1\",\n" +
                "              \"srcCreateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "              \"srcLastUpdateTimestamp\": \"2020-04-14T10:00:00.000\",\n" +
                "              \"customerAccountLocationProfileId\": \"2cdf4154-ef0e-4705-84f9-9efa6578002b\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n"+
                "    } ,\n" +
                "    {\n" +

                "      \"locationType\": \"IntermodalLocation\",\n" +
                "      \"customerLocationSequenceNumber\": 0,\n" +
                "      \"city\": \"CASTLEGAR\",\n" +
                "      \"streetNumber\": null,\n" +
                "      \"suffix\": null,\n" +
                "      \"streetName\": null,\n" +
                "      \"streetType\": null,\n" +
                "      \"streetDirection\": null,\n" +
                "      \"state\": \"BC\",\n" +
                "      \"country\": \"CA\",\n" +
                "      \"addrLine1\": \"2705 ARROW LAKES DR\",\n" +
                "      \"addrLine2\": null,\n" +
                "      \"addrLine3\": null,\n" +
                "      \"timeZone\": \"PT\"\n" +
                "    } \n" +
                "  ]\n" +
                "}]";
        return objectMapper.readValue(json, JSONArray.class);
    }
    public static JSONArray getFacilityResponseUsingCityState() throws JsonProcessingException {
        String json = "[\n" +
                "\t{\n" +
                "        \"facilityId\": \"firestore id\",\n" +
                "        \"facilityType\": \"Terminal\",\n" +
                "        \"locationType\": \"Facility\",\n" +
                "        \"facilityCode\": \"C\",\n" +
                "        \"facilityNumber\": \"19845\",\n" +
                "        \"srcFacilityNumber\": 78785,\n" +
                "        \"isPrimaryRamp\": true,\n" +
                "        \"splcCode\": \"12121\",\n" +
                "        \"facilityName\": \"Calgary\",\n" +
                "        \"facilityAltName\": \"string\",\n" +
                "        \"customerLocationKey\": [\"CASTLEGAR-BC\"],\n" +
                "        \"country\": \"CA\",\n" +
                "        \"state\": \"AB\",\n" +
                "        \"carrierAbbreviation\": \"AASE\",\n" +
                "        \"carrierNumber\": \"\",\n" +
                "        \"effectiveDate\": \"16/07/2022\",\n" +
                "        \"expiryDate\": \"16/07/2022\",\n" +
                "        \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"updatedTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"facilityAddress\": [{\n" +
                "                \"facilityAddressId\": \"uuid\",\n" +
                "                \"addressId\": 1,\n" +
                "                \"streetNumber\": \"17569\",\n" +
                "                \"streetNumberSuffix\": \"\",\n" +
                "                \"streetType\": \"AVE\",\n" +

                "                \"streetAddressUnit\": \"\",\n" +
                "                \"provinceStateCode\": \"AB\",\n" +
                "                \"countryCode\": \"CA\",\n" +
                "                \"postalCode\": \"V4N 3M4\",\n" +
                "                \"longitude\": 49.19129,\n" +
                "                \"latitude\": -122.735275,\n" +
                "                \"timeZoneOffset\": \"PT\",\n" +

                "                \"streetName\": \"104\",\n" +
                "                \"cityName\": \"SURREY\",\n" +
                "                \"addressCommonName\": \"Main Address\",\n" +
                "                \"effectiveDate\": \"16/07/2022\",\n" +
                "                \"expiryDate\": \"16/07/2022\",\n" +
                "                \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"updatedTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "        ]\n" +
                "    } , \n" +
                "\t\n" +
                "\t{\n" +
                "        \"facilityId\": \"firestore id\",\n" +
                "        \"facilityType\": \"Terminal\",\n" +
                "        \"locationType\": \"Facility\",\n" +
                "        \"facilityCode\": \"C\",\n" +
                "        \"facilityNumber\": \"19845\",\n" +
                "        \"srcFacilityNumber\": 1234,\n" +
                "        \"isPrimaryRamp\": false,\n" +
                "        \"splcCode\": \"12121\",\n" +
                "        \"facilityName\": \"Calgary\",\n" +
                "        \"facilityAltName\": \"string\",\n" +
                "        \"customerLocationKey\": [\"CASTLEGAR-BC\"],\n" +
                "        \"country\": \"CA\",\n" +
                "        \"state\": \"AB\",\n" +
                "        \"carrierAbbreviation\": \"AASE\",\n" +
                "        \"carrierNumber\": \"\",\n" +
                "        \"effectiveDate\": \"16/07/2022\",\n" +
                "        \"expiryDate\": \"16/07/2022\",\n" +
                "        \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"updatedTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"facilityAddress\": [{\n" +
                "                \"facilityAddressId\": \"uuid\",\n" +
                "                \"addressId\": 1,\n" +
                "                \"streetNumber\": \"17569\",\n" +
                "                \"streetNumberSuffix\": \"\",\n" +
                "                \"streetType\": \"AVE\",\n" +

                "                \"streetAddressUnit\": \"\",\n" +
                "                \"provinceStateCode\": \"AB\",\n" +
                "                \"countryCode\": \"CA\",\n" +
                "                \"postalCode\": \"V4N 3M4\",\n" +
                "                \"longitude\": 49.19129,\n" +
                "                \"latitude\": -122.735275,\n" +
                "                \"timeZoneOffset\": \"PT\",\n" +

                "                \"streetName\": \"104\",\n" +
                "                \"cityName\": \"SURREY\",\n" +
                "                \"addressCommonName\": \"Main Address\",\n" +
                "                \"effectiveDate\": \"16/07/2022\",\n" +
                "                \"expiryDate\": \"16/07/2022\",\n" +
                "                \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"updatedTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]\n" ;

        return objectMapper.readValue(json, JSONArray.class);
    }

    public static JSONArray getFacilityResponseUsingTerminalId() throws JsonProcessingException {
        String json = "[\n" +
                "\t{\n" +
                "        \"facilityId\": \"firestore id\",\n" +
                "        \"facilityType\": \"Terminal\",\n" +
                "        \"locationType\": \"Facility\",\n" +
                "        \"facilityCode\": \"C\",\n" +
                "        \"facilityNumber\": \"19845\",\n" +
                "        \"srcFacilityNumber\": 180,\n" +
                "        \"isPrimaryRamp\": true,\n" +
                "        \"splcCode\": \"12121\",\n" +
                "        \"facilityName\": \"Calgary\",\n" +
                "        \"facilityAltName\": \"string\",\n" +
                "        \"customerLocationKey\": [\"CASTLEGAR-BC\"],\n" +
                "        \"country\": \"CA\",\n" +
                "        \"state\": \"AB\",\n" +
                "        \"carrierAbbreviation\": \"AASE\",\n" +
                "        \"carrierNumber\": \"\",\n" +
                "        \"effectiveDate\": \"16/07/2022\",\n" +
                "        \"expiryDate\": \"16/07/2022\",\n" +
                "        \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"updatedTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"facilityAddress\": [{\n" +
                "                \"facilityAddressId\": \"uuid\",\n" +
                "                \"addressId\": 1,\n" +
                "                \"streetNumber\": \"17569\",\n" +
                "                \"streetNumberSuffix\": \"\",\n" +
                "                \"streetType\": \"AVE\",\n" +

                "                \"streetAddressUnit\": \"\",\n" +
                "                \"provinceStateCode\": \"AB\",\n" +
                "                \"countryCode\": \"CA\",\n" +
                "                \"postalCode\": \"V4N 3M4\",\n" +
                "                \"longitude\": 49.19129,\n" +
                "                \"latitude\": -122.735275,\n" +
                "                \"timeZoneOffset\": \"PT\",\n" +

                "                \"streetName\": \"104\",\n" +
                "                \"cityName\": \"SURREY\",\n" +
                "                \"addressCommonName\": \"Main Address\",\n" +
                "                \"effectiveDate\": \"16/07/2022\",\n" +
                "                \"expiryDate\": \"16/07/2022\",\n" +
                "                \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"updatedTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "        ]\n" +
                "    } , \n" +
                "\t\n" +
                "\t{\n" +
                "        \"facilityId\": \"firestore id\",\n" +
                "        \"facilityType\": \"Terminal\",\n" +
                "        \"locationType\": \"Facility\",\n" +

                "        \"facilityCode\": \"C\",\n" +
                "        \"facilityNumber\": \"19845\",\n" +
                "        \"srcFacilityNumber\": 1234,\n" +
                "        \"isPrimaryRamp\": false,\n" +
                "        \"splcCode\": \"12121\",\n" +
                "        \"facilityName\": \"Calgary\",\n" +
                "        \"facilityAltName\": \"string\",\n" +
                "        \"customerLocationKey\": [\"CASTLEGAR-BC\"],\n" +
                "        \"country\": \"CA\",\n" +
                "        \"state\": \"AB\",\n" +
                "        \"carrierAbbreviation\": \"AASE\",\n" +
                "        \"carrierNumber\": \"\",\n" +
                "        \"effectiveDate\": \"16/07/2022\",\n" +
                "        \"expiryDate\": \"16/07/2022\",\n" +
                "        \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"updatedTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"facilityAddress\": [{\n" +
                "                \"facilityAddressId\": \"uuid\",\n" +
                "                \"addressId\": 1,\n" +
                "                \"streetNumber\": \"17569\",\n" +
                "                \"streetNumberSuffix\": \"\",\n" +
                "                \"streetType\": \"AVE\",\n" +

                "                \"streetAddressUnit\": \"\",\n" +
                "                \"provinceStateCode\": \"AB\",\n" +
                "                \"countryCode\": \"CA\",\n" +
                "                \"postalCode\": \"V4N 3M4\",\n" +
                "                \"longitude\": 49.19129,\n" +
                "                \"latitude\": -122.735275,\n" +
                "                \"timeZoneOffset\": \"PT\",\n" +

                "                \"streetName\": \"104\",\n" +
                "                \"cityName\": \"SURREY\",\n" +
                "                \"addressCommonName\": \"Main Address\",\n" +
                "                \"effectiveDate\": \"16/07/2022\",\n" +
                "                \"expiryDate\": \"16/07/2022\",\n" +
                "                \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"updatedTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]\n" ;

        return objectMapper.readValue(json, JSONArray.class);
    }

    public static JSONArray getIntermodalLocationResponse() throws JsonProcessingException {
        String json = "[{\n" +

                "  \"businessUnitDesc\": \"Forest Products\",\n" +
                "  \"businessUnitCode\": \"10\",\n" +
                "  \"accountCreateDate\": \"1997-04-02\",\n" +
                "  \"accountActiveDate\": \"1997-04-02\",\n" +
                "  \"accountDisableDate\": \"9999-12-31\",\n" +
                "  \"accountAbbrCmntTxt\": null,\n" +
                "  \"revenueCreditAmount\": null,\n" +
                "  \"revenueCreditIndicator\": \"Y\",\n" +
                "  \"customerAccountTransportationStatusCode\": \"A\",\n" +
                "  \"customerAccountTransportationStatusDesc\": \"Active\",\n" +
                "  \"customerAccountStatusCode\": \"A\",\n" +
                "  \"customerAccountStatusDesc\": \"Active\",\n" +
                "  \"customerAccountAccountingStatusCode\": \"A\",\n" +
                "  \"customerAccountAccountingStatusDesc\": \"Active\",\n" +
                "  \"preferredLanguageTypeCode\": \"ENG\",\n" +
                "  \"preferredLanguageTypeName\": \"English\",\n" +
                "  \"name\": \"INTERFOR CORPORATION\",\n" +
                "  \"srcCreateDatetime\": \"2021-10-26T05:41:19.973Z\",\n" +
                "  \"srcUpdateDatetime\": \"2022-02-23T01:24:44.245Z\",\n" +
                "  \"customerVerificationSourceVal\": \"EBUSINESS\",\n" +
                "  \"customerVerificationSourceCode\": \"EB\",\n" +
                "  \"customerAccountNumber\": \"411682\",\n" +
                "  \"srcAcctMdmId\": \"0000106256\",\n" +
                "  \"delFlag\": \"N\",\n" +
                "  \"customerAccountLocation\": [\n" +
                "    {\n" +

                "      \"locationType\": \"IntermodalLocation\",\n" +
                "      \"customerLocationSequenceNumber\": 746638,\n" +
                "      \"customerLocationAbbrCmntTxt\": null,\n" +
                "      \"customerLocationCmntTxt\": null,\n" +
                "      \"transModeCarloadIndicator\": null,\n" +
                "      \"transModeNotSpecified\": null,\n" +
                "      \"transModeFromIload\": \"Y\",\n" +
                "      \"transModeImdlIndicator\": null,\n" +
                "      \"srcAcctRId\": \"102521\",\n" +
                "      \"srcLocMdmId\": \"102678\",\n" +
                "      \"srcCreateDatetime\": \"2021-10-26T05:54:08.138Z\",\n" +
                "      \"srcPostalAddrFk\": \"440512\",\n" +
                "      \"delFlag\": \"N\",\n" +
                "      \"srcPostalAddrMdmId\": \"187983\",\n" +
                "      \"sourcePostalCodeExtension\": null,\n" +
                "      \"city\": \"CASTLEGAR\",\n" +
                "      \"postalCode\": \"V1N 3W4\",\n" +
                "      \"latitude\": 49.336967,\n" +
                "      \"longitude\": -117.76187,\n" +
                "      \"streetNumber\": null,\n" +
                "      \"suffix\": null,\n" +
                "      \"streetName\": null,\n" +
                "      \"streetType\": null,\n" +
                "      \"streetDirection\": null,\n" +
                "      \"unit\": null,\n" +
                "      \"state\": \"BC\",\n" +
                "      \"country\": \"CA\",\n" +
                "      \"geoCodingCoordinateSystemCode\": \"WGS84\",\n" +
                "      \"geoCodingCoordinateSystemDesc\": \"World Geodetic System – Version 84\",\n" +
                "      \"sourceCoordinateSystem\": null,\n" +
                "      \"sourceCoordinateSystemDesc\": null,\n" +
                "      \"addrLine1\": \"2705 ARROW LAKES DR\",\n" +
                "      \"addrLine2\": null,\n" +
                "      \"addrLine3\": null,\n" +
                "      \"addrLine4\": null,\n" +
                "      \"addrLine5\": null,\n" +
                "      \"addrTypeInd\": \"POSTAL-ADDRESS\",\n" +
                "      \"addrTypeIndDesc\": \"MDM Enterprise Addresses\",\n" +
                "      \"timeZone\": \"PT\",\n" +
                "      \"adCity\": null,\n" +
                "      \"adPostalCode\": null,\n" +
                "      \"adPostalCodeExt\": null,\n" +
                "      \"adLongitude\": null,\n" +
                "      \"adCountryCode\": null,\n" +
                "      \"adAddrLine1\": null,\n" +
                "      \"adAddrLine2\": null,\n" +
                "      \"adState\": null,\n" +
                "      \"adValidationDate\": \"2022-06-24\",\n" +
                "      \"doNotValidateInd\": null,\n" +
                "      \"adStreetNumber\": null,\n" +
                "      \"adstreetname\": null,\n" +
                "      \"adStreetPostDescriptor\": null,\n" +
                "      \"adStreetPostDirectional\": null,\n" +
                "      \"adStreetPreDescriptor\": null,\n" +
                "      \"adStreetPreDirectional\": null,\n" +
                "      \"adTimeZoneCode\": null,\n" +
                "      \"adTimeZoneDesc\": null\n" +
                "    }\n" +
                "  ]\n" +
                "}]";
        return objectMapper.readValue(json, JSONArray.class);
    }

    public static JSONArray getLocationHistory() throws JsonProcessingException {
        String json = "[\n" +
                "  {\n" +
                "    \"customerAccountId\": \"string\",\n" +
                "    \"customerAccountNumber\": \"string\",   \n" +
                "    \"name\": \"string\",    \n" +
                "    \"customerAccountLocation\": [\n" +
                "      {\n" +
                "        \"customerAccountLocationId\": \"string\",\n" +
                "        \"locationType\": \"CustomerAccountLocation\",\n" +
                "        \"customerLocationSequenceNumber\": 0,        \n" +
                "        \"customerLocationsiteId\": \"string\",\n" +
                "        \"sourcePostalCodeExtension\": \"string\",\n" +
                "        \"streetNumber\": \"string\",\n" +
                "        \"suffix\": \"string\",\n" +
                "        \"streetName\": \"string\",\n" +
                "        \"streetType\": \"string\",\n" +
                "        \"streetDirection\": \"string\",\n" +
                "        \"unit\": \"string\",\n" +
                "        \"city\": \"CN\",\n" +
                "        \"state\": \"AB\",\n" +
                "        \"postalCode\": \"string\",\n" +
                "        \"country\": \"CA\",\n" +
                "        \"addrLine1\": \"string\",\n" +
                "        \"addrLine2\": \"string\",\n" +
                "        \"addrLine3\": \"string\"\n" +
                "        \n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";
        return objectMapper.readValue(json, JSONArray.class);
    }

    public static JSONArray getMetaDataforLocation() throws JsonProcessingException {
        String json = "[\n" +
                "  {\n" +
                "    \"customerAccountId\": \"hgyyeysvDgayhn465hctdgf7\",\n" +
                "    \"customerLocationSequenceNumber\": 38726,\n" +
                "    \"customerLocationsiteId\": \"3af0d242-437f-4cd4-bb8b-554cb77ea49a\",\n" +
                "    \"locationType\": \"CustomerAccountLocation\"\n" +

                "  }\n" +
                "]";
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, JSONArray.class);
    }

    public static JSONArray getFacility() throws JsonProcessingException {
        String json = "[\n" +
                "    {\n" +
                "        \"facilityId\": \"000EmUKhzfnRLh8BLgtP\",\n" +
                "        \"facilityType\": \"Terminal\",\n" +
                "        \"facilityNumber\": 39030,\n" +
                "        \"facilityName\": \"FINDLEY\",\n" +
                "        \"locationType\": \"Facility\",\n" +
                "        \"country\": \"US\",\n" +
                "        \"state\": \"MI\",\n" +
                "        \"createdTimestamp\": \"2022-11-24T13:20:46.482+00:00\",\n" +
                "        \"updatedTimestamp\": \"2022-11-24T13:20:46.482+00:00\",\n" +
                "        \"carrierAbbreviation\": \"XUS\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"facilityId\": \"88w4Ekj70tEUvcGBpLvW\",\n" +
                "        \"facilityType\": \"Terminal\",\n" +
                "        \"facilityNumber\": 39030,\n" +
                "        \"facilityName\": \"ENCANTO\",\n" +
                "        \"locationType\": \"Facility\",\n" +
                "        \"country\": \"US\",\n" +
                "        \"state\": \"CA\",\n" +
                "        \"createdTimestamp\": \"2022-11-24T13:22:32.544+00:00\",\n" +
                "        \"updatedTimestamp\": \"2022-11-24T13:22:32.544+00:00\",\n" +
                "        \"carrierAbbreviation\": \"US\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"facilityId\": \"UEzoV261T0sDHfOFJrPD\",\n" +
                "        \"facilityType\": \"Terminal\",\n" +
                "        \"facilityNumber\": 39030,\n" +
                "        \"facilityName\": \"NORTH LAKE\",\n" +
                "        \"locationType\": \"Facility\",\n" +
                "        \"country\": \"US\",\n" +
                "        \"state\": \"MI\",\n" +
                "        \"createdTimestamp\": \"2022-11-24T13:23:08.429+00:00\",\n" +
                "        \"updatedTimestamp\": \"2022-11-24T13:23:08.429+00:00\",\n" +
                "        \"carrierAbbreviation\": \"US01\"\n" +
                "    }\n" +
                "]";
        return objectMapper.readValue(json, JSONArray.class);
    }

    public static Object getJoltLocationObject() throws JsonProcessingException {
        String inputJson = "\"orderPartyId\": \"0ad78ce4-8d5a-4f59-9ed4-fe6d4c347a73\",\n" +
                "        \"customerAccountId\": \"845c7544-ae74-4ea6-9790-3fcd8932bc17\",\n" +
                "        \"customerAccountNumber\": \"921151\",\n" +
                "        \"name\": \"ZIM INTEGRATED SHPG SVCS (CDA) CO\",\n" +
                "        \"address\": {\n" +
                "            \"locationType\": \"CustomerAccountLocation\",\n" +
                "            \"city\": \"MISSISSAUGA\",\n" +
                "            \"state\": \"ON\",\n" +
                "            \"country\": \"CA\",\n" +
                "            \"customerLocationSequenceNumber\": 61945,\n" +
                "            \"customerLocationsiteId\": \"86252956-e894-497b-b9b1-a96c162d3fe2\"\n" +
                "        }\n" +
                "    }" ;
        CommonUtil commonUtil = new CommonUtil() ;
        System.out.println(commonUtil.joltTransformation(inputJson));
        return commonUtil.joltTransformation(inputJson) ;
    }

    public static byte[] errorResponse() {
        String jsonString = "{\n" +
                "    \"message\": \"One or More Validation Failure\",\n" +
                "    \"errors\": [\n" +
                "        {\n" +
                "            \"errorCode\": \"SCIOAPP001\",\n" +
                "            \"description\": \"Terminal with terminalId C9692 not found .\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return jsonString.getBytes();
    }

    public List<Facility> getRampsMock() {
        String json = "[{\n" +
                "        \"facilityId\": \"9YQ0ADJS4NndqYEZIZq4\",\n" +
                "\t\t\"locationType\" : \"Facility\" , \n" +
                "        \"facilityType\": \"Terminal\",\n" +
                "        \"facilityCode\": \"C\",\n" +
                "        \"facilityNumber\": \"19845\",\n" +
                "        \"srcFacilityNumber\": \"78785\",\n" +
                "        \"isPrimaryRamp\": true,\n" +
                "        \"splcCode\": \"12121\",\n" +
                "        \"facilityName\": \"Calgary\",\n" +
                "        \"facilityAltName\": \"HALIFAX\",\n" +
                "        \"country\": \"CA\",\n" +
                "        \"state\": \"AB\",\n" +
                "        \"carrierAbbreviation\": \"AASE\",\n" +
                "        \"carrierNumber\": \"\",\n" +
                "        \"effectiveDate\": \"16/07/2022\",\n" +
                "        \"expiryDate\": \"16/07/2022\",\n" +
                "        \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"updatedTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"facilityAddress\": [{\n" +
                "                \"facilityAddressId\": \"9YQ0ADJS4NndqYEZIksjbfg5\",\n" +
                "                \"addressId\": 1,\n" +
                "                \"streetNumber\": \"17569\",\n" +
                "                \"streetNumberSuffix\": \"\",\n" +
                "                \"streetType\": \"AVE\",\n" +

                "                \"streetAddressUnit\": \"\",\n" +
                "                \"provinceStateCode\": \"AB\",\n" +
                "                \"countryCode\": \"CA\",\n" +
                "                \"postalCode\": \"V4N 3M4\",\n" +
                "                \"longitude\": 49.19129,\n" +
                "                \"latitude\": -122.735275,\n" +
                "                \"timeZoneOffset\": \"PT\",\n" +

                "                \"streetName\": \"104\",\n" +
                "                \"cityName\": \"SURREY\",\n" +
                "                \"addressCommonName\": \"Main Address\",\n" +
                "                \"effectiveDate\": \"16/07/2022\",\n" +
                "                \"expiryDate\": \"16/07/2022\",\n" +
                "                \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"updatedTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "        ]\n" +
                "    } , \n" +
                "\t\n" +
                "\t{\n" +
                "        \"facilityId\": \"9YQ0ADJS4NndqYEZIhsvd4\",\n" +
                "\t\t\"locationType\" : \"Facility\" , \n" +
                "        \"facilityType\": \"Terminal\",\n" +
                "        \"facilityCode\": \"C\",\n" +
                "        \"facilityNumber\": \"19846\",\n" +
                "        \"srcFacilityNumber\": \"74585\",\n" +
                "        \"isPrimaryRamp\": false,\n" +
                "        \"splcCode\": \"12121\",\n" +
                "        \"facilityName\": \"Calgary\",\n" +
                "        \"facilityAltName\": \"HALIFAX\",\n" +
                "        \"country\": \"CA\",\n" +
                "        \"state\": \"AG\",\n" +
                "        \"carrierAbbreviation\": \"ABSE\",\n" +
                "        \"carrierNumber\": \"\",\n" +
                "        \"effectiveDate\": \"16/07/2022\",\n" +
                "        \"expiryDate\": \"16/07/2022\",\n" +
                "        \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"updatedTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "        \"facilityAddress\": [{\n" +
                "                \"facilityAddressId\": \"9YQ0ADJS4NndqYEZIksjbf4\",\n" +
                "                \"addressId\": 1,\n" +
                "                \"streetNumber\": \"17569\",\n" +
                "                \"streetNumberSuffix\": \"\",\n" +
                "                \"streetType\": \"AVE\",\n" +

                "                \"streetAddressUnit\": \"\",\n" +
                "                \"provinceStateCode\": \"AG\",\n" +
                "                \"countryCode\": \"CA\",\n" +
                "                \"postalCode\": \"V4N 3M4\",\n" +
                "                \"longitude\": 49.19129,\n" +
                "                \"latitude\": -122.735275,\n" +
                "                \"timeZoneOffset\": \"PT\",\n" +

                "                \"streetName\": \"104\",\n" +
                "                \"cityName\": \"SURREY\",\n" +
                "                \"addressCommonName\": \"Main Address\",\n" +
                "                \"effectiveDate\": \"16/07/2022\",\n" +
                "                \"expiryDate\": \"16/07/2022\",\n" +
                "                \"srcCreateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"srcLastUpdateTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"createdTimestamp\": \"2020-04-14T10:45:07.719\",\n" +
                "                \"updatedTimestamp\": \"2020-04-14T10:45:07.719\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]\n" ;
        try{
            ObjectMapper mapper = new ObjectMapper() ;
            return Arrays.asList(mapper.readValue(json , Facility[].class)) ;
        }catch(JsonProcessingException ex){
            ex.printStackTrace();
            throw new SystemException() ;

        }
    }

    public static String getPartyToLocation(){
        return "[\n" +
                "  {\n" +
                "    \"operation\": \"remove\",\n" +
                "    \"spec\": {\n" +
                "      \"address\": {\n" +
                "        \"customer\": \"\"\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"operation\": \"shift\",\n" +
                "    \"spec\": {\n" +
                "      \"address\": {\n" +
                "        \"*\": \"&\"\n" +
                "      },\n" +
                "      \"*\": \"customer.&\"\n" +
                "    }\n" +
                "  }\n" +
                "]\n";
    }
}
