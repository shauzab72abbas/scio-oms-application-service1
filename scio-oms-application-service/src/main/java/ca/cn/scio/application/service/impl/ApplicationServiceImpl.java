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
package ca.cn.scio.application.service.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ca.cn.scio.application.resource.*;
import org.json.simple.JSONArray;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.cn.scio.application.exception.Error;
import ca.cn.scio.application.exception.ErrorCode;
import ca.cn.scio.application.exception.MultipleErrorsException;
import ca.cn.scio.application.exception.NotFoundException;
import ca.cn.scio.application.exception.SystemException;
import ca.cn.scio.application.service.ApplicationService;
import ca.cn.scio.application.util.AuthUtil;
import ca.cn.scio.application.util.CommonUtil;
import ca.cn.scio.application.util.VariableUtil;

import static ca.cn.scio.application.util.VariableUtil.*;
import static ca.cn.scio.application.util.VariableUtil.CITY_STATE_DELIMITER;

/**
 * Please refer to the following positions from the Development Position Book
 * (DPB):
 *
 * <pre>
 *   Microservices Architecture:     https://kb.cn.ca/display/SPB/Microservice+Architecture+-+BE
 *   SpringBoot:                     https://kb.cn.ca/display/SPB/Spring+Boot+Framework+-+BE
 *   S.O.L.I.D.:            		 https://kb.cn.ca/display/SPB/S.O.L.I.D+details+-+BE
 *   Logging standard:				 https://kb.cn.ca/display/SPB/Java+Logging+Standard+-+BE
 *   Java Secure Coding Standard:	 https://kb.cn.ca/display/SPB/Java+Secure+Coding+Standard+-+BE
 *   Java Package and Class Naming:	 https://kb.cn.ca/display/SPB/Java+Package+and+Class+Naming+Standard+-+BE
 *   Java Secure SDLC Consideration: https://kb.cn.ca/display/SPB/Java+Secure+SDLC+Consideration+-+BE
 * </pre>
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ApplicationServiceImpl.class);
    private final RestTemplate restTemplate;
    private final AuthUtil authUtil;
    private final BreApiServiceImpl breApiService;

    private final CommonUtil commonUtil;

    private static final String LANG_KE = "langKey";
    ApplicationServiceExtension asyncCalls;
    @Value("${referenceDomainILBUrl}")
    private String referenceDomainILBUrl;
    @Value("${referenceDomainUrl}")
    private String referenceDomainCloudRunUrl;
    @Value("${searchFacilityEndPoint}")
    private String searchFacilityEndPoint;
    @Value("${terminalByNumber}")
    private String terminalById;
    @Value("${customerDomainILBUrl}")
    private String customerDomainILBUrl;
    @Value("${customerDomainUrl}")
    private String customerDomainUrl;
    @Value("${getCustomerDetails}")
    private String getCustomerDetails;
    @Value("${orderDomainILBUrl}")
    private String orderDomainUrl;
    @Value("${orderDomainUrl}")
    private String orderDomainDevUrl;
    @Value("${createOrder}")
    private String createOrder;
    @Value("${updateOrder}")
    private String updateOrder;
    @Value("${getOrderById}")
    private String getOrderById;
    @Value("${cancelOrder}")
    private String cancelOrder;
    @Value("${updateEvent}")
    private String updateEvent;
    @Value("${equipmentTypes}")
    private String getEquipmentType;
    @Value("${businessConstants}")
    private String getReferences ;
    @Value("${discardOrder}")
    private String discardOrder;
    @Value("${cancelPartialOrder}")
    private String cancelPartialOrder;
    @Value("${updateTransportEvent}")
    private String updateTransportEvent;
    @Value("${searchOrders}")
    private String searchOrders;

    @Value("${harmonizedCodesByList}")
    private String harmonizedCodesByList;

    private MultiLanguageServiceImpl multiLanguageService;
    private static final String LANG_EN = "en";
    private static final String LANG_KEY="fr";
    private static final String SCIO = "SCIO";
    private static final String FACILITY = "Facility";
    private static final String MEXICO = "MEXICO";

    @Autowired
    public ApplicationServiceImpl(RestTemplate restTemplate, ApplicationServiceExtension applicationServiceExtension,
                                  AuthUtil authUtil, BreApiServiceImpl breApiService, CommonUtil commonUtil, MultiLanguageServiceImpl multiLanguageService) {
        this.restTemplate = restTemplate;
        this.authUtil = authUtil;
        this.asyncCalls = applicationServiceExtension;
        this.breApiService = breApiService;
        this.commonUtil = commonUtil;
        this.multiLanguageService = multiLanguageService;
    }
    public void setMexicoDestination(Order order, List<String> uiEquipmentIds){
        List<EmptyDelivery> orderEquipmentRequestList = order.getCustomerOrder().getOrderEquipmentRequest();
        int size = uiEquipmentIds.size();
        int i = 0;
        for(EmptyDelivery emptyDelivery : orderEquipmentRequestList){
            if(uiEquipmentIds.contains(emptyDelivery.getUiEquipmentId())){
                Facility destination = (Facility) emptyDelivery.getDestination();
                destination.setCarrierAbbreviation(MEXICO);
                i++;
            }
            if(i == size){
                break;
            }
        }
    }
    public void checkForMexicoDestination(Order order, List<String> uiEquipmentIds){
        if(order.getCustomerOrder() != null && order.getCustomerOrder().getOrderEquipmentRequest() != null && !order.getCustomerOrder().getOrderEquipmentRequest().isEmpty()) {
            List<EmptyDelivery> orderEquipmentRequestList = order.getCustomerOrder().getOrderEquipmentRequest();
            for(EmptyDelivery emptyDelivery : orderEquipmentRequestList){
                if(FACILITY.equals(emptyDelivery.getDestination().getLocationType().getValue())){
                    Facility destination = (Facility) emptyDelivery.getDestination();
                    if(MEXICO.equals(destination.getCarrierAbbreviation())){
                        uiEquipmentIds.add(emptyDelivery.getUiEquipmentId());
                    }
                }
            }
        }
    }

    // ** Entry method for create order
    @Override
    public Order createOrder(Order order, String userInfo, String langKey) {
        List<String> uiEquipmentIds = new ArrayList<>();
        if(order != null && order.getSourceCreateSystem() != null && SCIO.equals(order.getSourceCreateSystem())){
            LOGGER.info("CreateOrder: Entering method to check mexico destination");
            checkForMexicoDestination(order, uiEquipmentIds);
        }

        String   orderString = commonUtil.sanitizeOrder(order);
        LOGGER.info("CreateOrder: Create Order Payload | Order : {}", orderString);
        List<Error> errors = new ArrayList<>();
        if(!("SCIO").equals(order.getSourceCreateSystem()))
            enhanceIMXOrder(order);


        preprocessOrder(order, errors);

        if(!uiEquipmentIds.isEmpty()){
            LOGGER.info("CreateOrder: Mexico destination present in order payload : {}", uiEquipmentIds.toString());
            setMexicoDestination(order, uiEquipmentIds);
        }

        if (errors.isEmpty()) {
            Order englishOrderResponse = callToPostOrder(order, userInfo);
            if(LANG_KEY.equals(langKey)){
                LOGGER.info("CreateOrder: French translation started for create order");
                return multiLanguageService.returnFrenchTranslatedOrder(englishOrderResponse);
            }

            return breApiService.reformatEnglishErrorMessage(englishOrderResponse);
        }
        else
            throw new MultipleErrorsException("CreateOrder: One or More Validation Failure", errors);

    }

    // ** Entry method for update order
    @Override
    public Order updateOrder(String orderId, Order order, String langKey, String userInfo) {
        List<String> uiEquipmentIds = new ArrayList<>();
        if(order != null && order.getSourceCreateSystem() != null && SCIO.equals(order.getSourceCreateSystem())){
            LOGGER.info("UpdateOrder: Entering method to check mexico destination");
            checkForMexicoDestination(order, uiEquipmentIds);
        }
        String   orderPayload = commonUtil.sanitizeOrder(order);
        LOGGER.info("UpdateOrder: Update Order payload | Order : {}", orderPayload);
        if(!("SCIO").equals(order.getSourceUpdateSystem()) && order.getSourceUpdateSystem()!=null)
            enhanceIMXOrder(order);

        List<Error> errors = new ArrayList<>();
        preprocessOrder(order, errors);
        if(!uiEquipmentIds.isEmpty()){
            LOGGER.info("UpdateOrder: Mexico destination present in order payload : {}", uiEquipmentIds.toString());
            setMexicoDestination(order, uiEquipmentIds);
        }
        if (errors.isEmpty()) {
            Order englishOrderResponse =  callToPutOrder(orderId, order, userInfo);
            if(LANG_KEY.equals(langKey)){
                LOGGER.info("UpdateOrder: French translation completed for search order");
                return multiLanguageService.returnFrenchTranslatedOrder(englishOrderResponse);
            }
            return breApiService.reformatEnglishErrorMessage(englishOrderResponse);
        } else
            throw new MultipleErrorsException("UpdateOrder: Multiple Validation Failure", errors);
    }

    @Override
    public String updateOrderEvent(String orderEquipmentRequestId, OrderStatusModel orderStatusModel) {
        long startOfCall = System.currentTimeMillis();
        Status status = Status.fromValue(orderStatusModel.getOrderEquipmentStatusEvent().getStatus().toUpperCase());
        LOGGER.info("UpdateOrderEvent: status-{}",status);
        if (Status.ACCEPTED.equals(status) || Status.ASSIGNED.equals(status) || Status.INTRANSIT.equals(status) || Status.COMPLETED.equals(status)) {
            enrichOrderStatusModel(orderStatusModel, status);
        }
        if(Status.CANCELLED.equals(status)){
            LOGGER.info("UpdateOrderEvent: Entered canceled event");
            List<BusinessConstants> cancelReasonComment=getCancellationReasonList();
            LOGGER.info("UpdateOrderEvent: Fetched cancel reason description : {}",cancelReasonComment);
            OrderEquipmentStatusEvent cancelledEvent=enrichCancellationReason(orderStatusModel.getOrderEquipmentStatusEvent(),cancelReasonComment);
            orderStatusModel.setOrderEquipmentStatusEvent(cancelledEvent);
        }
        LOGGER.info("UpdateOrderEvent: Started order party enrichment for conveyor fulfillment");
        this.getOrderPartyForConveyorFulfillment(orderStatusModel.getOrderEquipmentStatusEvent());
        LOGGER.info("UpdateOrderEvent: Completed order party enrichment");
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("orderEquipmentRequestId", orderEquipmentRequestId);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(orderDomainUrl + updateEvent);
        String statusUpdateUrl = uriComponentsBuilder.buildAndExpand(pathParams).toUriString();
        HttpHeaders httpHeaders = authUtil.generateAuthorizationHeader(orderDomainDevUrl);
        LOGGER.info("UpdateOrderEvent: OrderStatusModel -{}",orderStatusModel);
        HttpEntity<OrderStatusModel> httpEntity = new HttpEntity<>(orderStatusModel, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(statusUpdateUrl, HttpMethod.PUT, httpEntity,
                String.class);
        LOGGER.info("UpdateOrderEvent: Updated status to {} for equipmentRequestId {} inside order {} in {} ms ", status,
                orderStatusModel.getOrderEquipmentStatusEvent().getOrderEquipmentRequestId(),
                orderStatusModel.getOrderEquipmentStatusEvent().getOrderNumber(),
                System.currentTimeMillis() - startOfCall);
        return responseEntity.getBody();
    }

    @Override
    public String updateShipmentEvent(String transportShipmentId,
                                      TransportShipmentStatusModel transportShipmentStatusModel) {
        long startOfUpdateShipment = System.currentTimeMillis();
        String status = transportShipmentStatusModel.getTransportShipmentStatusEvent().getStatus().toUpperCase();
        if(status.equals(Status.CANCELLED.toString())){
            LOGGER.info("UpdateShipmentEvent: Entered canceled event");
            List<BusinessConstants> cancelReasonComment=getCancellationReasonList();
            LOGGER.info("UpdateShipmentEvent: Fetched cancel reason description : {}",cancelReasonComment);
            enrichCancellationReasonForShipment(transportShipmentStatusModel.getTransportShipmentStatusEvent(),cancelReasonComment);
        }
        this.getOrderPartyForTransportShipment(transportShipmentStatusModel);
        this.enrichEquipmentDetailsForTransportShipment(transportShipmentStatusModel);

        LOGGER.info("UpdateShipmentEvent: Enhanced locations for Shipment request Id {} in {} ms", transportShipmentId,
                System.currentTimeMillis() - startOfUpdateShipment);

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("transportShipmentRequestId", transportShipmentId);
        String url = orderDomainUrl.concat(updateTransportEvent);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
        String statusUpdateUrl = uriComponentsBuilder.buildAndExpand(pathParams).toUriString();

        HttpHeaders httpHeaders = authUtil.generateAuthorizationHeader(orderDomainDevUrl);
        HttpEntity<TransportShipmentStatusModel> httpEntity = new HttpEntity<>(transportShipmentStatusModel,
                httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(statusUpdateUrl, HttpMethod.PUT, httpEntity,
                String.class);
        LOGGER.info("UpdateShipmentEvent: Updated status for Shipment Request Id {} in {} ms", transportShipmentId,
                System.currentTimeMillis() - startOfUpdateShipment);
        return responseEntity.getBody();
    }

    public void enrichEquipmentDetailsForTransportShipment(TransportShipmentStatusModel transportShipmentStatusModel) {
        EqpDetails eqpDetails = transportShipmentStatusModel.getTransportShipmentStatusEvent().getEqpDetails();
        if (eqpDetails != null && eqpDetails.getEqpOwnTypeCd() != null) {
            EquipmentTypeOwner equipmentTypeOwner = getEquipmentTypeOwner(eqpDetails.getEqpOwnTypeCd());
            if (equipmentTypeOwner != null) {
                settingEquipmentDetailsInTransportShipment(eqpDetails, equipmentTypeOwner);
            }
        }
    }

    private void settingEquipmentDetailsInTransportShipment(EqpDetails eqpDetails, EquipmentTypeOwner equipmentTypeOwner) {
        eqpDetails.setEquipmentOwnerDesc(equipmentTypeOwner.getEquipmentTypeOwnerCodeDesc());
        if (eqpDetails.getEqpType() != null) {
            for (EquipmentCode equipmentCode : equipmentTypeOwner.getEquipmentCode()) {
                if (equipmentCode.getEquipmentCode().getValue().equals(eqpDetails.getEqpType().trim())) {
                    eqpDetails.setEquipmentTypeDesc(equipmentCode.getEnglishDescription());
                }
            }
        }
    }


    @Override
    public String discardOrder(String orderId) {
        long startOfCall = System.currentTimeMillis();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(orderDomainUrl + discardOrder);
        String statusDiscardUrl = uriComponentsBuilder.buildAndExpand(orderId).toUriString();
        HttpHeaders httpHeaders = authUtil.generateAuthorizationHeader(orderDomainDevUrl);
        HttpEntity<OrderStatusModel> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(statusDiscardUrl, HttpMethod.DELETE, httpEntity,
                String.class,orderId);
        LOGGER.info("DiscardOrder: For url {} time taken is {}", statusDiscardUrl, System.currentTimeMillis() - startOfCall);
        return responseEntity.getBody();
    }

    @Override
    public List<Facility> getRamps(String customerNumber, BigDecimal customerLocationSequenceNumber) {
        CustomerFilterPayload customerFilterPayload = new CustomerFilterPayload();
        List<CustomerFilterPayload> customerFilterPayloads = new ArrayList<>();
        SequenceNumber sequenceNumber = new SequenceNumber();
        List<SequenceNumber> sequenceNumbers = new ArrayList<>();

        sequenceNumber.setIncludeLocationProfile(true);
        sequenceNumber.setLocationSequenceNumber(customerLocationSequenceNumber.toString());
        sequenceNumbers.add(sequenceNumber);
        customerFilterPayload.setSequenceNumbers(sequenceNumbers);
        customerFilterPayload.setCustomerNumber(customerNumber);
        customerFilterPayloads.add(customerFilterPayload);

        List<CustomerAccountLocationHistory> customersWithTerminals;
        try {
            customersWithTerminals = getAllCustomerDetails(customerFilterPayloads);
            if(customersWithTerminals.isEmpty() ){
                throw new NotFoundException("Not Found", ErrorCode.SCIOAPP009);
            }
            CustomerAccountLocationHistory singleCustomerTerminals = customersWithTerminals.get(0);
            if (singleCustomerTerminals.getCustomerAccountLocation() == null || (singleCustomerTerminals.getCustomerAccountLocation() != null && singleCustomerTerminals.getCustomerAccountLocation().isEmpty())) {
                throw new NotFoundException("Not Found", ErrorCode.SCIOAPP009);
            }
            assert singleCustomerTerminals.getCustomerAccountLocation() != null;
            LOGGER.info("GetRamps: Size of CustomerAccountLocation list is {}",singleCustomerTerminals.getCustomerAccountLocation().size());
            final Location location = getLocationFromCALH(singleCustomerTerminals);

            /**Location location = singleCustomerTerminals.getCustomerAccountLocation().get(0); **/
            CustomerAccountLocationProfile customerAccountLocationProfile;
            if (location instanceof CustomerAccountLocation) {
                CustomerAccountLocation customerAccountLocation = (CustomerAccountLocation) location;
                customerAccountLocationProfile = customerAccountLocation.getCustomerAccountLocationProfile();
            } else {
                IntermodalLocation intermodalLocation = (IntermodalLocation) location;
                customerAccountLocationProfile = null != intermodalLocation ? intermodalLocation.getCustomerAccountLocationProfile() : null;
            }

            List<CustomerAccountLocationFacility> terminals = null;
            if (customerAccountLocationProfile != null) {
                terminals = customerAccountLocationProfile.getCustomerAccountLocationFacility();
            }
            List<FacilitySearch> searchFacilities = new ArrayList<>();
            if ((terminals == null || terminals.isEmpty()) || !checkPrimaryRamp(customerAccountLocationProfile)) {
                String city = location.getCity();
                State state = location.getState();
                FacilitySearch searchFacility = new FacilitySearch();
                searchFacility.setCity(city);
                searchFacility.setState(state);
                searchFacility.setFacilityType(FacilitySearch.FacilityTypeEnum.TERMINAL);
                searchFacilities.add(searchFacility);
                return getFacilityDetails(searchFacilities);

            } else {
                return getFacilitiesUsingTerminalId(terminals , searchFacilities);
            }
        } catch (JsonProcessingException ex) {
            SystemException systemException = new SystemException();
            systemException.initCause(ex);
            throw systemException;
        }
    }

    private Location getLocationFromCALH(CustomerAccountLocationHistory singleCustomerTerminals) {
        Location location = null;
        if (singleCustomerTerminals.getCustomerAccountLocation().size() != 1) {
            for (Location loc : singleCustomerTerminals.getCustomerAccountLocation()) {
                final List<CustomerAccountLocationFacility> terminals = getCustomerAccountLocationFacilities(loc);
                for (CustomerAccountLocationFacility facility : terminals) {
                    if (facility.getSequence()!=null && facility.getSequence().equals("1")) {
                        location = loc;
                        break;
                    } else {
                        if (location == null) {
                            location = loc;
                        }
                    }
                }
            }
        } else {
            location = singleCustomerTerminals.getCustomerAccountLocation().get(0);
        }
        return location;
    }

    private List<CustomerAccountLocationFacility> getCustomerAccountLocationFacilities(Location loc) {
        final CustomerAccountLocationProfile customerAccountLocationProfile = getCustomerAccountLocationProfileFromLocation(loc);
        List<CustomerAccountLocationFacility> terminals = null;
        if (customerAccountLocationProfile != null) {
            terminals = customerAccountLocationProfile.getCustomerAccountLocationFacility();
        }
        return terminals;
    }

    private boolean checkPrimaryRamp(CustomerAccountLocationProfile customerAccountLocationProfile) {
        boolean isRampPresent = false;
        if (customerAccountLocationProfile != null && customerAccountLocationProfile.getCustomerAccountLocationFacility() != null && !customerAccountLocationProfile.getCustomerAccountLocationFacility().isEmpty()) {
            for (CustomerAccountLocationFacility customerAccountLocationFacility : customerAccountLocationProfile.getCustomerAccountLocationFacility()) {
                if (customerAccountLocationFacility.getSequence() != null && customerAccountLocationFacility.getSequence().equals("1")) {
                    isRampPresent = true;
                    break;
                }
            }
        }
        return isRampPresent;
    }

    private List<Facility> getFacilitiesUsingTerminalId(List<CustomerAccountLocationFacility> terminals , List<FacilitySearch> searchFacilities) throws JsonProcessingException {
        List<Facility> facilities = new ArrayList<>();
        Integer srcTerminalNumber = null;
        for (CustomerAccountLocationFacility terminal : terminals) {
            if (terminal.getSequence() != null && terminal.getSequence().equals("1")) {
                srcTerminalNumber = terminal.getSrcFacilityNumber();
            } else {
                Facility facility = new Facility();
                facility.setSrcFacilityNumber(terminal.getSrcFacilityNumber());
                facility.setLocationType(Location.LocationTypeEnum.FACILITY);
                facility.isPrimaryRamp(false) ;
                facilities.add(facility);
            }
        }

        FacilitySearch facilitySearch = new FacilitySearch();
        if (srcTerminalNumber != null) {
            facilitySearch.setSrcFacilityNumber(srcTerminalNumber);
            facilitySearch.setFacilityType(FacilitySearch.FacilityTypeEnum.TERMINAL);
            searchFacilities.add(facilitySearch);
            List<Facility> primaryFacilityDetail = getFacilityDetails(searchFacilities);
            primaryFacilityDetail.get(0).setIsPrimaryRamp(true);
            facilities.add(primaryFacilityDetail.get(0));
        }

        return facilities;
    }

    private List<Facility> getFacilityDetails(List<FacilitySearch> searchFacilities) throws JsonProcessingException {

        HttpHeaders headers = authUtil.generateAuthorizationHeader(referenceDomainCloudRunUrl);
        HttpEntity<List<FacilitySearch>> entity = new HttpEntity<>(searchFacilities, headers);
        JSONArray jsonArray = restTemplate.exchange(referenceDomainILBUrl + searchFacilityEndPoint, HttpMethod.POST, entity, JSONArray.class).getBody();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        assert jsonArray != null;
        String responseJsonString = jsonArray.toJSONString();
        return Arrays.asList(mapper.readValue(responseJsonString, Facility[].class));

    }

    /*
     This method creates collections to store customer number and location Sequence Number
     with location type .
     It populate the collections , call another method to make payload for calling customer domain
     customer-details api , calls two another method to bring facility and customer enhancer data
     and finally calls a method to enhance our Order object .
     */
    private void preprocessOrder(Order order, List<Error> errors) {
        Set<String> customerNumbers = new HashSet<>();
        Map<String, Party> custNumLocNum = new HashMap<>();
        Map<String, Facility> facilities = new HashMap<>();
        Set<String> facNumFacType = new HashSet<>();
        Map<String , List<Facility>> rampDetails = new HashMap<>() ;
        List<CustomerFilterPayload> customerFilterPayloadList = new ArrayList<>();
        // Traverse through whole Order object to populate maps
        long startOfCall3 = System.currentTimeMillis();
        addSearchParamsFromOrder(order, customerNumbers, facNumFacType, custNumLocNum, facilities , rampDetails);
        LOGGER.info("PreProcessOrder: Total time taken by addSearchParamsFromOrder method {} ms",
                System.currentTimeMillis() - startOfCall3);

        // Create payload for customer-details api
        long startOfCall = System.currentTimeMillis();
        addToFilterPayload(customerFilterPayloadList, custNumLocNum, customerNumbers , rampDetails);
        LOGGER.info("PreProcessOrder: Total time taken by addToFilterPayload method {} ms",
                System.currentTimeMillis() - startOfCall);

        try {

            long startOfGettingFacilityDetails = System.currentTimeMillis();
            // async call to reference service to get enhancer Facility details
            getFacilityData(errors, facNumFacType, facilities);
            LOGGER.info("PreProcessOrder: Total time taken by Create Order - get facility data {} ms",
                    System.currentTimeMillis() - startOfGettingFacilityDetails);

            long startOfGettingCustomerDetails = System.currentTimeMillis();

            // call to customer-details api to get details of all customers at one shot
            getCustomersAndLocationsWithRampsData(custNumLocNum, customerFilterPayloadList , rampDetails);
            LOGGER.info("PreProcessOrder: Total time taken to get customer Locations with Ramps data {} ms",
                    System.currentTimeMillis() - startOfGettingCustomerDetails);

            // Enhance order object
            long startOfCall2 = System.currentTimeMillis();
            enhanceOrder(order, custNumLocNum, facilities , rampDetails);
            LOGGER.info("PreProcessOrder: Total time taken by enhanceOrder method {} ms",
                    System.currentTimeMillis() - startOfCall2);

            // Hide PII data before logging
            String orderString = commonUtil.sanitizeOrder(order);
            LOGGER.info("PreProcessOrder: Customer and Location details enhanced | Order : {}", orderString);
        } catch (JsonProcessingException exception) {
            throw new SystemException("Json Processing Exception" , exception);
        }

    }
    private OrderEquipmentStatusEvent enrichCancellationReason(OrderEquipmentStatusEvent orderEquipmentStatusEvent,List<BusinessConstants> cancellationReasonList){
        ObjectMapper objectMapper= new ObjectMapper();
        Cancelled cancelEvent= objectMapper.convertValue(orderEquipmentStatusEvent,Cancelled.class);
        LOGGER.info("EnrichCancellationReason: Converted to Cancelled object");
        if(cancelEvent.getCancellationReason()!= null && cancelEvent.getCancellationReason().getCancellationReasonCode()!=null && !cancelEvent.getCancellationReason().getCancellationReasonCode().isEmpty()){
            for(BusinessConstants businessConstants : cancellationReasonList){
                if(businessConstants.getCode().equals(cancelEvent.getCancellationReason().getCancellationReasonCode())){
                    cancelEvent.getCancellationReason().setCancellationReasonDesc(businessConstants.getDescription());
                    break;
                }
            }
        }

        LOGGER.info("EnrichCancellationReason: Cancelled reason enriched : {}",cancelEvent);
        return objectMapper.convertValue(cancelEvent,OrderEquipmentStatusEvent.class);

    }
    private void enrichCancellationReasonForShipment(TransportShipmentStatusEvent transportShipmentStatusEvent,List<BusinessConstants> cancellationReasonList){
        if(transportShipmentStatusEvent!=null && transportShipmentStatusEvent.getCancellation()!=null
                && transportShipmentStatusEvent.getCancellation().getCancellationReasonCode()!=null && !transportShipmentStatusEvent.getCancellation().getCancellationReasonCode().isEmpty()){
            for(BusinessConstants businessConstants : cancellationReasonList){
                if(businessConstants.getCode().equals(transportShipmentStatusEvent.getCancellation().getCancellationReasonCode())){
                    transportShipmentStatusEvent.getCancellation().setCancellationReasonDesc(businessConstants.getDescription());
                    break;
                }
            }
        }
        LOGGER.info("EnrichCancellationReasonForShipment: Cancelled reason enriched : {}",transportShipmentStatusEvent);

    }


    private void enrichReferences(List<Reference> references, List<BusinessConstants> referenceList){
        for (Reference reference : references) {
            for (BusinessConstants referenceDb : referenceList) {
                if (reference.getReferenceType() != null && !reference.getReferenceType().isEmpty() && !("0").equals(reference.getReferenceType()) && referenceDb!=null && referenceDb.getCode()!=null &&
                        (reference.getReferenceType()).equals(referenceDb.getCode())) {
                    reference.setReferenceValue(referenceDb.getDescription());
                }

            }
        }
    }

    private void enrichEquipmentTypeDesc(EquipmentTypeOwner equipmentTypeOwner, CustomerOrder customerOrder){
        List<EquipmentCode> equipmentCodes = equipmentTypeOwner.getEquipmentCode();
        for (EquipmentCode equipmentCode : equipmentCodes) {
            if (customerOrder.getOrderEquipmentRequest().get(0).getEquipmentType() != null &&
                    equipmentCode.getEquipmentCode() != null &&
                    (String.valueOf(customerOrder.getOrderEquipmentRequest().get(0).getEquipmentType())).equals(String.valueOf(equipmentCode.getEquipmentCode())) &&
                    equipmentCode.getEnglishDescription() != null && !equipmentCode.getEnglishDescription().isEmpty()) {
                customerOrder.getOrderEquipmentRequest().get(0).setEquipmentTypeDesc(equipmentCode.getEnglishDescription());
                if(customerOrder.getOrderEquipmentRequest().get(0).getEquipmentTypeDesc().contains(INSULATED)){
                    customerOrder.getOrderEquipmentRequest().get(0).setTemperature(BigDecimal.valueOf(21));
                    customerOrder.getOrderEquipmentRequest().get(0).setTemperatureUnit(TemperatureUnit.C);
                }

                if(!customerOrder.getOrderEquipmentRequest().get(0).getEquipmentTypeDesc().contains("Heater") &&
                        !customerOrder.getOrderEquipmentRequest().get(0).getEquipmentTypeDesc().contains("Reefer") &&
                        !customerOrder.getOrderEquipmentRequest().get(0).getEquipmentTypeDesc().contains(INSULATED)){
                    customerOrder.getOrderEquipmentRequest().get(0).setTemperature(null);
                    customerOrder.getOrderEquipmentRequest().get(0).setTemperatureUnit(null);
                }
            }
        }
    }

    private void enrichEquipmentDetails(CustomerOrder customerOrder,List<BusinessConstants> referenceList){
        if (customerOrder.getOrderEquipmentRequest().get(0).getEquipmentOwner() != null) {
            String equipmentTypeOwnerCode = String.valueOf(customerOrder.getOrderEquipmentRequest().get(0).getEquipmentOwner());
            EquipmentTypeOwner equipmentTypeOwner = getEquipmentTypeOwner(equipmentTypeOwnerCode);
            if (equipmentTypeOwner.getEquipmentCode() != null && !equipmentTypeOwner.getEquipmentCode().isEmpty()) {
                if (equipmentTypeOwner.getEquipmentTypeOwnerCodeDesc() != null && !equipmentTypeOwner.getEquipmentTypeOwnerCodeDesc().isEmpty()) {
                    customerOrder.getOrderEquipmentRequest().get(0).setEquipmentOwnerDesc(equipmentTypeOwner.getEquipmentTypeOwnerCodeDesc());
                }
                enrichEquipmentTypeDesc(equipmentTypeOwner,customerOrder);
            }
        }

        if (customerOrder.getOrderEquipmentRequest().get(0).getOrderEquipmentReference() != null && !customerOrder.getOrderEquipmentRequest().get(0).getOrderEquipmentReference().isEmpty() &&
                referenceList!=null && !referenceList.isEmpty()) {
            List<Reference> references = customerOrder.getOrderEquipmentRequest().get(0).getOrderEquipmentReference();
            enrichReferences(references,referenceList);
        }
    }

    private void enrichShipmentEquipmentTypeDesc(EquipmentTypeOwner equipmentTypeOwner, CustomerOrder customerOrder){
        List<EquipmentCode> equipmentCodes = equipmentTypeOwner.getEquipmentCode();
        if (equipmentCodes != null && !equipmentCodes.isEmpty()) {
            for (EquipmentCode equipmentCode : equipmentCodes) {
                if (customerOrder.getOrderShipmentRequest().get(0).getOrderedEquipmentType() != null &&
                        equipmentCode.getEquipmentCode() != null &&
                        (String.valueOf(customerOrder.getOrderShipmentRequest().get(0).getOrderedEquipmentType())).equals(String.valueOf(equipmentCode.getEquipmentCode())) &&
                        equipmentCode.getEnglishDescription() != null && !equipmentCode.getEnglishDescription().isEmpty()) {
                    customerOrder.getOrderShipmentRequest().get(0).setOrderedEquipmentTypeDesc(equipmentCode.getEnglishDescription());
                }
            }
            if(customerOrder.getOrderShipmentRequest().get(0).getOrderedEquipmentTypeDesc()!=null &&
                    customerOrder.getOrderShipmentRequest().get(0).getOrderedEquipmentTypeDesc().contains(INSULATED)){
                customerOrder.getOrderShipmentRequest().get(0).getOrderShipmentConveyor().getEquipment().setTemperature(BigDecimal.valueOf(21));
                customerOrder.getOrderShipmentRequest().get(0).getOrderShipmentConveyor().getEquipment().setTemperatureUnit(TemperatureUnit.C);
            }

        }
    }

    private void enrichTransportStopDetails(CustomerOrder customerOrder,List<BusinessConstants> referenceList){
        if(customerOrder.getOrderShipmentRequest().get(0).getOrderShipmentTransportStops()!=null && !customerOrder.getOrderShipmentRequest().get(0).getOrderShipmentTransportStops().isEmpty()){
            for(Stop transportStop: customerOrder.getOrderShipmentRequest().get(0).getOrderShipmentTransportStops()){
                if(transportStop.getOrderShipmentStopReferences()!=null && !transportStop.getOrderShipmentStopReferences().isEmpty()){
                    List<Reference> references=transportStop.getOrderShipmentStopReferences();
                    enrichReferences(references,referenceList);
                }
            }
        }
    }

    private void enrichShipmentDetails(CustomerOrder customerOrder,List<BusinessConstants> referenceList){

        if (customerOrder.getOrderShipmentRequest().get(0).getOrderedEquipmentOwner() != null) {
            String equipmentTypeOwnerCode = String.valueOf(customerOrder.getOrderShipmentRequest().get(0).getOrderedEquipmentOwner());
            EquipmentTypeOwner equipmentTypeOwner = getEquipmentTypeOwner(equipmentTypeOwnerCode);

            if (equipmentTypeOwner.getEquipmentTypeOwnerCodeDesc() != null && !String.valueOf(equipmentTypeOwner.getEquipmentTypeOwnerCodeDesc()).isEmpty()) {
                customerOrder.getOrderShipmentRequest().get(0).setOrderedEquipmentOwnerDesc(equipmentTypeOwner.getEquipmentTypeOwnerCodeDesc());
            }
            enrichShipmentEquipmentTypeDesc(equipmentTypeOwner,customerOrder);

        }

        if (customerOrder.getOrderShipmentRequest().get(0).getOrderShipmentReferences() != null && !customerOrder.getOrderShipmentRequest().get(0).getOrderShipmentReferences().isEmpty() &&
                referenceList!=null && !referenceList.isEmpty()) {
            List<Reference> references = customerOrder.getOrderShipmentRequest().get(0).getOrderShipmentReferences();
            enrichReferences(references,referenceList);
        }

        enrichHarmonizedCodeDescriptions(customerOrder);

        enrichTransportStopDetails(customerOrder,referenceList);
    }

    /**
     * For updates from IMX New Order, we should not use descriptions for HSCs from New Order.
     * Use the SCIO datastore to populate the HSC descriptions
     * Here we fetch the HSC descriptions from HSC API.
     *
     * @param customerOrder
     */
    private void enrichHarmonizedCodeDescriptions(CustomerOrder customerOrder) {
        // customerOrder or the shipmentRequest cannot be null here.
        // updates from IMX happen at the line item level, so we need only get the first item,
        // since that will be the only item.
        OrderShipmentRequest shipmentRequest = customerOrder.getOrderShipmentRequest().get(0);
        Set<HSCode> hsCodeSet = new HashSet<>();
        Map<String, String> descriptionMap = new HashMap<>();
        if (shipmentRequest.getOrderShipmentCommodities() != null) {
            for (ShipmentCommodity cmdy: shipmentRequest.getOrderShipmentCommodities()) {
                if (cmdy.getHarmonizedSystemCodes() == null) {
                    continue;
                }
                for (HSCode hsc: cmdy.getHarmonizedSystemCodes()) {
                    hsCodeSet.add(hsc);
                }
            }
        }

        List<String> hsCodeList = hsCodeSet.stream().map(HSCode::getHarmonizedCode).collect(Collectors.toList());
        getHarmonizedCodes(hsCodeList, LANG_EN).stream()
                .forEach(hsc -> {
                    if (hsc != null) {
                        descriptionMap.put(hsc.getHarmonizedCode(), hsc.getHarmonizedDescription());
                    }
                });

        for (HSCode hsc: hsCodeSet) {
            if (descriptionMap.get(hsc.getHarmonizedCode()) != null) {
                hsc.setHarmonizedCodeDesc(descriptionMap.get(hsc.getHarmonizedCode()));
            }
        }
    }

    /* Enhance IMX Order
    Filling Equipment type desc based on equipment type code
    Filling Reference details based on Reference type
    */
    private void enhanceIMXOrder(Order order){

        List<BusinessConstants> referenceList=getReferencesList();
        if(order.getCustomerOrder() !=null){
            CustomerOrder customerOrder= order.getCustomerOrder();
            if(customerOrder.getOrderEquipmentRequest()!=null && !customerOrder.getOrderEquipmentRequest().isEmpty()) {
                enrichEquipmentDetails(customerOrder,referenceList);
            }

            if(customerOrder.getOrderShipmentRequest()!=null && !customerOrder.getOrderShipmentRequest().isEmpty()) {
                enrichShipmentDetails(customerOrder,referenceList);
            }
        }
    }
    private List<BusinessConstants> getCancellationReasonList() {

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("category", "CANCELLATION_REASON");
        queryParams.add(LANG_KE, "en");
        return referenceApiCall(queryParams);
    }

    List<BusinessConstants> referenceApiCall(MultiValueMap<String,String> queryParams){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(referenceDomainILBUrl + getReferences);
        UriComponentsBuilder uri = uriComponentsBuilder.queryParams(queryParams);
        HttpHeaders headers = authUtil.generateAuthorizationHeader(referenceDomainCloudRunUrl);
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        long startOfReferenceCall = System.currentTimeMillis();
        ResponseEntity<BusinessConstants[]> responseEntity = restTemplate.exchange(uri.toUriString(), HttpMethod.GET,
                request, BusinessConstants[].class);
        LOGGER.info("ReferenceAPICall: Retrieved Reference Details in {} ms", System.currentTimeMillis() - startOfReferenceCall);
        List<BusinessConstants> references = new ArrayList<>();
        if (responseEntity.getBody() != null) {
            references = Arrays.asList(responseEntity.getBody());
        }
        return references;
    }

    /**
     * Sends an HTTP GET request to Reference Domain service to fetch the
     * harmonized code objects corresponding to `hsCodes` argument.
     *
     * @param hsCodes - List of harmonized Codes
     * @param lang - language - either EN or FR
     * @return a list of HarmonizedCode objects
     */
    private List<HarmonizedCode> getHarmonizedCodes(List<String> hsCodes, String lang) {
        if (hsCodes == null || hsCodes.isEmpty()) {
            return new ArrayList<>();
        }
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(referenceDomainILBUrl + harmonizedCodesByList);
        UriComponentsBuilder uri = uriComponentsBuilder.queryParam("harmonizedCodes", String.join(",", hsCodes))
                .queryParam("languageIdentifier", lang);
        HttpHeaders headers = authUtil.generateAuthorizationHeader(referenceDomainCloudRunUrl);
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        long startOfCall = System.currentTimeMillis();
        ResponseEntity<HarmonizedCode[]> responseEntity = restTemplate.exchange(uri.toUriString(), HttpMethod.GET,
                request, HarmonizedCode[].class);
        LOGGER.info("GetHarmonizedCodes: Retrieved Harmonized Codes in {} ms", System.currentTimeMillis() - startOfCall);
        List<HarmonizedCode> hsCodeList = new ArrayList<>();
        // this responseEntity != null is needed, as, without it, many unit test cases will fail.
        if (responseEntity != null && responseEntity.getBody() != null) {   // NOSONAR
            hsCodeList = Arrays.asList(responseEntity.getBody());
        }
        return hsCodeList;
    }

    private List<BusinessConstants> getReferencesList() {

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("category", "REFERENCE_TYPE");
        queryParams.add(LANG_KE, "en");
        return referenceApiCall(queryParams);
    }


    // entry method to call customer-details api to get customer enhancer details and populate enhancer map (custNumLocNum)
    private void getCustomersAndLocationsWithRampsData(Map<String, Party> custNumLocNum, List<CustomerFilterPayload> customerFilterPayloadList , Map<String , List<Facility>> rampDetails) throws JsonProcessingException {

        List<CustomerAccountLocationHistory> allCustomers = getAllCustomerDetails(customerFilterPayloadList);
        for (CustomerAccountLocationHistory customer : allCustomers) {
            populateCustomerEnhancer(custNumLocNum, customer);

        }
        if(rampDetails != null)
            populateRampDetails(rampDetails , custNumLocNum) ;

    }

    private void populateRampDetails(Map<String, List<Facility>> rampDetails, Map<String, Party> custNumLocNum) throws JsonProcessingException {

        Map<String , String> locationCityStateMap = new HashMap<>() ;
        List<FacilitySearch> facilitySearchesUsingCityState = new ArrayList<>() ;
        List<FacilitySearch> facilitySearchesUsingSrcFacilityNum = new ArrayList<>() ;
        Map<Integer , List<String>> locationSrcFacilityNum = new HashMap<>() ;

        for (Map.Entry<String, List<Facility>> keyValue : rampDetails.entrySet()){
            Party party = custNumLocNum.get(keyValue.getKey()) ;
            if(party == null || party.getAddress() == null) continue;
            Location location = party.getAddress() ;
            final CustomerAccountLocationProfile customerAccountLocationProfile = getCustomerAccountLocationProfileFromLocation(location);

            boolean isRampPresent = checkPrimaryRamp(customerAccountLocationProfile);
            if(isRampPresent){
                List<Facility> secondaryRamps = makeFacilityAndPayload(customerAccountLocationProfile.getCustomerAccountLocationFacility() , facilitySearchesUsingSrcFacilityNum , locationSrcFacilityNum , keyValue.getKey()) ;
                rampDetails.put(keyValue.getKey() , secondaryRamps ) ;

            }else{
                if(location.getState()!=null && location.getCity()!=null) {
                    locationCityStateMap.put(location.getState().toString() + CITY_STATE_DELIMITER + location.getCity(), keyValue.getKey());
                    FacilitySearch facilitySearch = new FacilitySearch();
                    facilitySearch.setCity(location.getCity());
                    facilitySearch.setState(location.getState());
                    facilitySearch.setFacilityType(FacilitySearch.FacilityTypeEnum.TERMINAL);
                    facilitySearchesUsingCityState.add(facilitySearch);
                }
            }
        }

        if(!facilitySearchesUsingSrcFacilityNum.isEmpty())
            populateRampDetailsUsingTerminalIds(facilitySearchesUsingSrcFacilityNum , locationSrcFacilityNum , rampDetails);
        if(!facilitySearchesUsingCityState.isEmpty())
            populateRampDetailsUsingCityState(facilitySearchesUsingCityState , locationCityStateMap , rampDetails);

    }

    private CustomerAccountLocationProfile getCustomerAccountLocationProfileFromLocation(Location location) {
        CustomerAccountLocationProfile customerAccountLocationProfile;
        if (location instanceof CustomerAccountLocation) {
            CustomerAccountLocation customerAccountLocation = (CustomerAccountLocation) location;
            customerAccountLocationProfile = customerAccountLocation.getCustomerAccountLocationProfile();
        } else {
            IntermodalLocation intermodalLocation = (IntermodalLocation) location;
            customerAccountLocationProfile = intermodalLocation.getCustomerAccountLocationProfile();
        }
        return customerAccountLocationProfile;
    }

    private void populateRampDetailsUsingCityState(List<FacilitySearch> facilitySearchesUsingCityState, Map<String, String> locationCityStateMap, Map<String, List<Facility>> rampDetails) throws JsonProcessingException {
        LOGGER.info("PopulateRampDetailsUsingCityState: Calling search facilities to find ramp using cities and states {}" , facilitySearchesUsingCityState);
        List<Facility> facilitiesResponseForCityState = null ;
        try {
            facilitiesResponseForCityState = getFacilityDetails(facilitySearchesUsingCityState);
        }catch(HttpClientErrorException exception) {
            if(exception.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                LOGGER.info("PopulateRampDetailsUsingCityState: No facility found for city-state (s)") ;
            }else
                throw exception ;
        }

        if(facilitiesResponseForCityState == null) return ;
        Map<String , List<Facility>> cityStateFacility = new HashMap<>() ; // key holds city-state and value holds list of Facility came from Reference MS

        for(Facility facility : facilitiesResponseForCityState) {
            List<String> cityStateList = facility.getCustomerLocationKey() ;
            for(String cityState : cityStateList ) {
                List<Facility> data = new ArrayList<>() ;
                cityStateFacility.computeIfAbsent(cityState, k -> data);
                cityStateFacility.get(cityState).add(facility);
            }
        }

        for(Map.Entry<String, List<Facility>> keyValue : cityStateFacility.entrySet()){
            rampDetails.put(locationCityStateMap.get(keyValue.getKey()) , keyValue.getValue()) ;
        }

    }

    private void populateRampDetailsUsingTerminalIds(List<FacilitySearch> facilitySearchesUsingSrcFacilityNum, Map<Integer, List<String>> locationSrcFacilityNum, Map<String, List<Facility>> rampDetails) throws JsonProcessingException {
        LOGGER.info("PopulateRampDetailsUsingTerminalIds: Calling search facilities to find ramp using src facility numbers {}" , facilitySearchesUsingSrcFacilityNum);
        List<Facility> facilitiesResponseForTerminalId = null ;

        try{
            facilitiesResponseForTerminalId = getFacilityDetails(facilitySearchesUsingSrcFacilityNum) ;
        }catch(HttpClientErrorException exception) {
            if(exception.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                LOGGER.info("PopulateRampDetailsUsingTerminalIds: No facility found for src facility numbers") ;
            }else
                throw exception ;
        }
        if(facilitiesResponseForTerminalId == null) return ;
        for(Facility facility : facilitiesResponseForTerminalId) {
            Integer srcFacilityNumber = facility.getSrcFacilityNumber() ;
            List<String> rampDetailKey = locationSrcFacilityNum.get(srcFacilityNumber) ;
            if(rampDetailKey != null && !rampDetailKey.isEmpty()) {
                for(String rampDetail : rampDetailKey) {
                    rampDetails.get(rampDetail).add(facility);
                }
            }
        }


    }

    private List<Facility> makeFacilityAndPayload(List<CustomerAccountLocationFacility> customerAccountLocationFacility, List<FacilitySearch> facilitySearchesUsingSrcFacilityNum, Map<Integer, List<String>> locationSrcFacilityNum , String key) {
        List<Facility> facilities = new ArrayList<>() ;
        for(CustomerAccountLocationFacility preferredRamp : customerAccountLocationFacility) {
            if(preferredRamp.getSequence() != null && preferredRamp.getSequence().equalsIgnoreCase("1")){
                FacilitySearch facilitySearch = new FacilitySearch() ;
                facilitySearch.setSrcFacilityNumber(preferredRamp.getSrcFacilityNumber());
                facilitySearch.setFacilityType(FacilitySearch.FacilityTypeEnum.TERMINAL);
                facilitySearchesUsingSrcFacilityNum.add(facilitySearch);
                if(locationSrcFacilityNum.get(preferredRamp.getSrcFacilityNumber())==null)
                    locationSrcFacilityNum.put( preferredRamp.getSrcFacilityNumber() , Collections.singletonList(key)) ;
                else {
                    List<String> previousFacilityNumber = new ArrayList<>(locationSrcFacilityNum.get(preferredRamp.getSrcFacilityNumber()));
                    previousFacilityNumber.add(key);
                    locationSrcFacilityNum.put(preferredRamp.getSrcFacilityNumber(), previousFacilityNumber);
                }
            }else{
                Facility facility = new Facility() ;
                facility.setLocationType(Location.LocationTypeEnum.FACILITY);
                facility.setIsPrimaryRamp(false);
                facility.setSrcFacilityNumber(preferredRamp.getSrcFacilityNumber());
                facilities.add(facility) ;
            }
        }
        return facilities ;
    }

    // Parsing response from customer-details api for consuming during enhancing Order
    private void populateCustomerEnhancer(Map<String, Party> custNumLocNum, CustomerAccountLocationHistory customer) {
        String customerNumber = customer.getCustomerAccountNumber();
        for (Map.Entry<String, Party> keyValue : custNumLocNum.entrySet()) {
            String custLocNum = keyValue.getKey();
            if (custLocNum.startsWith(customerNumber)) {
                String[] splitParts = custLocNum.split(VariableUtil.DELIMITER);
                String locSeq = splitParts[1];
                String locationType = splitParts[2];
                Location lookBackLocation;

                lookBackLocation = iterateOverLocations(customer, locSeq, locationType, custNumLocNum, custLocNum);

                if (custNumLocNum.get(custLocNum) == null)
                    custNumLocNum.replace(custLocNum, buildParty(lookBackLocation, customer));

            }
        }

    }

    // finding enhancer location from list of locations came from customer-details api
    public Location iterateOverLocations(CustomerAccountLocationHistory customer, String locSeq, String locationType, Map<String, Party> custNumLocNum, String custLocNum) {
        List<Location> locations = customer.getCustomerAccountLocation();
        Location lookBackLocation = null;
        if (locations != null && !locations.isEmpty() && !locSeq.equals("None") && !locSeq.isEmpty()) {
            int locSeqNum = Integer.parseInt(locSeq);
            for (Location location : locations) {
                Location matchedLocation = findEnhancerLocation(location, custNumLocNum, custLocNum, locationType, locSeqNum, customer);
                if (matchedLocation != null) {
                    lookBackLocation = matchedLocation;

                }
                if (custNumLocNum.get(custLocNum) != null) break;
            }
        }
        return lookBackLocation;
    }

    /*
     deciding exact/lookback location .
     => lookback location is the location whose location sequence number is same as loc seq num present in
     Order object but with different locationType
     */
    public Location findEnhancerLocation(Location location, Map<String, Party> custNumLocNum, String custLocNum, String locationType, int locSeqNum, CustomerAccountLocationHistory customer) {

        Location lookBackLocation = null;
        if (location instanceof CustomerAccountLocation) {
            lookBackLocation = getLocationFromCustomerAccountLocation((CustomerAccountLocation) location, custNumLocNum, custLocNum, locationType, locSeqNum, customer, lookBackLocation);
        } else {
            lookBackLocation = getLocationFromIntermodalLocation((IntermodalLocation) location, custNumLocNum, custLocNum, locationType, locSeqNum, customer, lookBackLocation);
        }
        return lookBackLocation;
    }

    private Location getLocationFromIntermodalLocation(IntermodalLocation location, Map<String, Party> custNumLocNum, String custLocNum, String locationType, int locSeqNum, CustomerAccountLocationHistory customer, Location lookBackLocation) {
        IntermodalLocation intermodalLocation = location;
        if (intermodalLocation.getCustomerLocationSequenceNumber() != null && locSeqNum == intermodalLocation.getCustomerLocationSequenceNumber()) {
            if (locationType.equals(VariableUtil.INTERMODAL_LOCATION)) {
                custNumLocNum.replace(custLocNum, buildParty(intermodalLocation, customer));
            } else
                lookBackLocation = intermodalLocation;
        }
        return lookBackLocation;
    }

    private Location getLocationFromCustomerAccountLocation(CustomerAccountLocation location, Map<String, Party> custNumLocNum, String custLocNum, String locationType, int locSeqNum, CustomerAccountLocationHistory customer, Location lookBackLocation) {
        CustomerAccountLocation customerAccountLocation = location;
        if (customerAccountLocation.getCustomerLocationSequenceNumber() != null && locSeqNum == customerAccountLocation.getCustomerLocationSequenceNumber()) {
            if (locationType.equals(VariableUtil.CUSTOMER_ACCOUNT_LOCATION)) {
                custNumLocNum.replace(custLocNum, buildParty(customerAccountLocation, customer));
            } else
                lookBackLocation = customerAccountLocation;
        }
        return lookBackLocation;
    }

    // Build Party object from Location object to set to enhancer map (custNumlocNum)
    private Party buildParty(Location location, CustomerAccountLocationHistory customer) {
        Party party = new Party();
        enhanceCustomerDetails(customer, party);

        if (location != null)
            party.setAddress(location);

        return party;
    }

    private void enhanceCustomerDetails(CustomerAccount customer, Party party) {
        party.setOrderPartyId(customer.getOrderPartyId());
        party.setCustomerAccountId(customer.getCustomerAccountId());
        party.setCustomerAccountNumber(customer.getCustomerAccountNumber());
        party.setName(customer.getName());
        party.setCustomerAccountStatusDesc(customer.getCustomerAccountStatusDesc());
        party.setCustomerAccountStatusCode(customer.getCustomerAccountStatusCode());
        party.setPreferredLanguageTypeCode(customer.getPreferredLanguageTypeCode());
        party.setPreferredLanguageTypeName(customer.getPreferredLanguageTypeName());
        party.setCustomerAccountProfile(customer.getCustomerAccountProfile());
    }

    // calling customer-details api
    public List<CustomerAccountLocationHistory> getAllCustomerDetails(
            List<CustomerFilterPayload> customerFilterPayloadList) throws JsonProcessingException {

        //** call to customer domain service
        HttpHeaders headers = authUtil.generateAuthorizationHeader(customerDomainUrl);
        HttpEntity<List<CustomerFilterPayload>> entity = new HttpEntity<>(customerFilterPayloadList, headers);
        JSONArray jsonArray = restTemplate.exchange(customerDomainILBUrl + getCustomerDetails, HttpMethod.POST, entity, JSONArray.class).getBody();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        assert jsonArray != null;
        String responseJsonString = jsonArray.toJSONString();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return Arrays.asList(mapper.readValue(responseJsonString, CustomerAccountLocationHistory[].class));

    }

    // creating request body for customer-details api
    private void addToFilterPayload(List<CustomerFilterPayload> customerFilterPayloadList, Map<String, Party> custNumLocNum, Set<String> custNums , Map<String , List<Facility>> rampDetails) {
        Map<String, List<String>> payloadMap = new HashMap<>();
        custNums.forEach(customerNumber -> payloadMap.put(customerNumber, new ArrayList<>()));

        for (String key : custNumLocNum.keySet()) {
            String[] splits = key.split(DELIMITER , 2);
            String custNum = splits[0];
            String locSeqLocType = splits[1];
            if( payloadMap.get(custNum) != null) {
                payloadMap.get(custNum).add(locSeqLocType);
            }
        }

        for (Map.Entry<String, List<String>> keyValue : payloadMap.entrySet()) {
            CustomerFilterPayload customerFilterPayload = new CustomerFilterPayload();
            customerFilterPayload.setCustomerNumber(keyValue.getKey());
            List<SequenceNumber> sequenceNumbers = new ArrayList<>();
            for (String locationSeq : keyValue.getValue()) {
                String locSeq = locationSeq.split(DELIMITER)[0] ;
                SequenceNumber sequenceNumber = new SequenceNumber();
                sequenceNumber.setLocationSequenceNumber(locSeq);
                if(rampDetails != null)
                    sequenceNumber.setIncludeLocationProfile(rampDetails.containsKey(keyValue.getKey() + DELIMITER + locationSeq));
                else{
                    sequenceNumber.setIncludeLocationProfile(false);
                }
                sequenceNumbers.add(sequenceNumber);
            }
            customerFilterPayload.setSequenceNumbers(sequenceNumbers);
            customerFilterPayloadList.add(customerFilterPayload);
        }
    }

    // add customer numbers , location sequence number , facility number , facility type from whole Order
    private void addSearchParamsFromOrder(Order order, Set<String> customerNumbers, Set<String> facNumFacType,
                                          Map<String, Party> custNumLocNum, Map<String, Facility> facilities ,
                                          Map<String , List<Facility>> rampDetails) {

        String custNum;
        String locSeq;

        // ** adding orderingParty to map
        if (order.getOrderingParty().getCustomerAccountNumber() != null) {
            custNum = order.getOrderingParty().getCustomerAccountNumber();
            locSeq = "None";
            customerNumbers.add(custNum);
            custNumLocNum.put(buildCustNumLocSeq(Arrays.asList(custNum, locSeq, "None")), null);
        }

        // ** adding shipper to map

        if (order.getShipper() != null && order.getShipper().getCustomerAccountNumber() != null) {
            addFromParty(order.getShipper(), customerNumbers, custNumLocNum , rampDetails);

        }

        // ** adding payerOfFreight to map

        if (order.getPayerOfFreight() != null && order.getPayerOfFreight().getCustomerAccountNumber() != null) {
            custNum = order.getPayerOfFreight().getCustomerAccountNumber();
            locSeq = "None";
            customerNumbers.add(custNum);
            custNumLocNum.put(buildCustNumLocSeq(Arrays.asList(custNum, locSeq, "None")), null);
        }

        // ** adding shipFrom to map
        if (order.getShipFrom() != null)
            addFromLocationSite(order.getShipFrom(), customerNumbers, facNumFacType, custNumLocNum, facilities , rampDetails , SHIP_FROM);

        // ** adding search param from Empty Delivery and Loaded Shipment

        if (order.getCustomerOrder() != null)
            addSearchParamsfromCustomerOrder(order.getCustomerOrder(), customerNumbers, facNumFacType, custNumLocNum,
                    facilities , rampDetails);

    }

    // ** adding search params from loaded shipment and empty delivery
    private void addSearchParamsfromCustomerOrder(CustomerOrder customerOrder, Set<String> customerNumbers,
                                                  Set<String> facNumFacType, Map<String, Party> custNumLocNum, Map<String, Facility> facilities ,
                                                  Map<String , List<Facility>> rampDetails) {
        List<EmptyDelivery> emptyDeliveries = customerOrder.getOrderEquipmentRequest();
        if (emptyDeliveries != null) {
            for (EmptyDelivery emptyDelivery : emptyDeliveries) {
                Location location = emptyDelivery.getDestination();
                if (location != null ) {
                    addFromLocationSite(location, customerNumbers, facNumFacType, custNumLocNum , facilities , rampDetails, DESTINATION);
                }
            }
        }
        if (customerOrder.getOrderShipmentRequest() != null)
            for (OrderShipmentRequest orderShipmentRequest : customerOrder.getOrderShipmentRequest()) {
                addSearchParamFromLoadedShipment(orderShipmentRequest, customerNumbers, facNumFacType, custNumLocNum,
                        facilities , rampDetails);

            }

    }

    // ** add search param from loaded shipment
    private void addSearchParamFromLoadedShipment(OrderShipmentRequest orderShipmentRequest,
                                                  Set<String> customerNumbers, Set<String> facNumFacType, Map<String, Party> custNumLocNum,
                                                  Map<String, Facility> facilities , Map<String , List<Facility>> rampDetails) {
        Party consignee = orderShipmentRequest.getConsignee();
        if (consignee != null) {
            addFromParty(consignee, customerNumbers, custNumLocNum , rampDetails);
        }
        Location shipTo = orderShipmentRequest.getShipTo();
        if (shipTo != null) {
            addFromLocationSite(shipTo, customerNumbers, facNumFacType, custNumLocNum, facilities , rampDetails , SHIP_TO);
        }
        if (orderShipmentRequest.getOrderShipmentTransportStops() != null)
            for (Stop stop : orderShipmentRequest.getOrderShipmentTransportStops()) {
                Location location = stop.getLocation();
                if (location != null)
                    addFromLocationSite(location, customerNumbers, facNumFacType, custNumLocNum, facilities , rampDetails , "stop");
            }
    }


    // ** async call to enhance terminal
    private void getFacilityData(List<Error> errors, Set<String> facNumFacType, Map<String, Facility> facilities)
            throws JsonProcessingException {
        StringBuilder url = new StringBuilder(referenceDomainILBUrl);
        url.append(terminalById);

        // ** iterating on facilityTypes and calling customer service asynchronously
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (String combinedString : facNumFacType) {
            String[] splitRecords = combinedString.split(VariableUtil.DELIMITER);

            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.toString())
                    .queryParam("facilityType", splitRecords[1]).queryParam("facilityNumber", splitRecords[0]);
            futures.add(asyncCalls.callReferenceDomainForTerminal(builder, combinedString, facilities, errors));
        }
        handleFutures(futures);
    }

    // ** handle response coming from the threads
    public void handleFutures(List<CompletableFuture<Void>> futures) {
        List<Exception> allOtherExceptions = new ArrayList<>();

        // ** iterating over all stored futures (stored async calls) and check for
        // exceptions
        for (CompletableFuture<Void> future : futures) {
            try {
                future.join();

            } catch (CompletionException exception) {

                // ** getting real cause behind exceptions thrown from threads and storing them
                Throwable causeOfException = exception.getCause();
                if (causeOfException instanceof HttpClientErrorException) {
                    allOtherExceptions.add((HttpClientErrorException) causeOfException);
                } else if (causeOfException instanceof HttpServerErrorException) {
                    allOtherExceptions.add((HttpServerErrorException) causeOfException);
                } else if (causeOfException instanceof ResourceAccessException) {
                    allOtherExceptions.add((ResourceAccessException) causeOfException);
                } else {

                    SystemException exception1 = new SystemException();
                    exception1.initCause(exception.getCause());
                    allOtherExceptions.add(exception1);
                }

            }
        }

        // ** if there are multiple exceptions thrown from different threads , we
        // finally throw one of'em
        if (!allOtherExceptions.isEmpty()) {
            Exception exception = allOtherExceptions.get(0);
            if (exception instanceof HttpServerErrorException) {
                throw (HttpServerErrorException) exception;
            } else if (exception instanceof HttpClientErrorException) {
                throw (HttpClientErrorException) exception;
            } else if (exception instanceof ResourceAccessException) {
                throw (ResourceAccessException) exception;
            } else
                throw (SystemException) exception;
        }
    }

    // ** call to order domain to Post order
    private Order callToPostOrder(Order order, String userInfo) {

        // ** making request to call order domain service
        LOGGER.info("CallToPostOrder: passing {} as header to order-domain-service", userInfo);
        HttpHeaders headers = authUtil.generateAuthorizationHeader(orderDomainDevUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("userInfo", userInfo);
        HttpEntity<Order> postdata = new HttpEntity<>(order, headers);

        // ** call to order domain service
        long startTime = System.currentTimeMillis();
        LOGGER.info("CallToPostOrder: Enhanced order object : {}", order);
        Order orderResponse = restTemplate.postForObject(orderDomainUrl + createOrder
                // ** call to order domain service
                , postdata, Order.class);
        long endTime = System.currentTimeMillis() - startTime;
        LOGGER.info("CallToPostOrder: Order Created | Order number : {} | Total time taken by Order Domain service {} ms",
                order.getOrderNumber(), endTime);

        return orderResponse;

    }

    // ** call to order domain to Update order
    private Order callToPutOrder(String orderId, Order order, String userInfo) {

        // ** building request
        LOGGER.info("CallToPutOrder: passing {} as header to order-domain-service in updateOrder", userInfo);
        HttpHeaders headers = authUtil.generateAuthorizationHeader(orderDomainDevUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("userInfo", userInfo);
        HttpEntity<Order> putdata = new HttpEntity<>(order, headers);

        // ** calling order domain service to update order
        long startTime = System.currentTimeMillis();
        ResponseEntity<Order> updateResponse = restTemplate.exchange(orderDomainUrl + updateOrder
                // ** calling order domain service to update order
                , HttpMethod.PUT, putdata, Order.class, orderId);
        long endTime = System.currentTimeMillis() - startTime;
        LOGGER.info("CallToPutOrder: Updated Ordered | Order number : {} | Total time taken by Order Domain service {} ms",
                order.getOrderNumber(), endTime);
        return updateResponse.getBody();

    }

    // join multiple strings using "-"
    private String buildCustNumLocSeq(List<String> strs) {

        return String.join(":", strs);

    }

    // finds location sequence number for a location
    private String findLocationNumber(Location location) {

        if (location instanceof CustomerAccountLocation) {
            CustomerAccountLocation customerAccountLocation = (CustomerAccountLocation) location;
            if (customerAccountLocation.getCustomerLocationSequenceNumber() == null)
                return "None";

            return customerAccountLocation.getCustomerLocationSequenceNumber().toString();
        } else if (location instanceof IntermodalLocation) {
            IntermodalLocation intermodalLocation = (IntermodalLocation) location;
            if (intermodalLocation.getCustomerLocationSequenceNumber() == null)
                return "None";

            return intermodalLocation.getCustomerLocationSequenceNumber().toString();
        }
        return null;
    }

    private void addFromLocationSite(Location location, Set<String> customerNumbers, Set<String> facNumFacType,
                                     Map<String, Party> custNumLocNum, Map<String, Facility> facilities ,
                                     Map<String , List<Facility>> rampDetails , String field) {
        String locationType = location.getLocationType().toString();

        if (locationType.equals(CUSTOMER_ACCOUNT_LOCATION)) {
            CustomerAccountLocation customerAccountLocation = (CustomerAccountLocation) location;
            CustomerAccount customer = customerAccountLocation.getCustomer();
            if (customer != null) {
                customerNumbers.add(customer.getCustomerAccountNumber());
                if (customerAccountLocation.getCustomerLocationSequenceNumber() == null)
                    custNumLocNum.put(
                            buildCustNumLocSeq(Arrays.asList(customer.getCustomerAccountNumber(), "None", "None")),
                            null);
                else{
                    String joinedString = buildCustNumLocSeq(Arrays.asList(customer.getCustomerAccountNumber(),
                            customerAccountLocation.getCustomerLocationSequenceNumber().toString(), locationType)) ;
                    custNumLocNum.put( joinedString,
                            null);
                    addKeyToRampDetail(field , rampDetails , joinedString) ;
                }
            }

        } else if (locationType.equals(INTERMODAL_LOCATION)) {
            IntermodalLocation intermodalLocation = (IntermodalLocation) location;
            CustomerAccount customer = intermodalLocation.getCustomer();
            if (customer != null) {
                customerNumbers.add(customer.getCustomerAccountNumber());
                if (intermodalLocation.getCustomerLocationSequenceNumber() == null)
                    custNumLocNum.put(
                            buildCustNumLocSeq(Arrays.asList(customer.getCustomerAccountNumber(), "None", "None")),
                            null);
                else{
                    String joinedString =  buildCustNumLocSeq(Arrays.asList(customer.getCustomerAccountNumber(),
                            intermodalLocation.getCustomerLocationSequenceNumber().toString(), locationType)) ;
                    custNumLocNum.put( joinedString, null);
                    addKeyToRampDetail(field , rampDetails , joinedString) ;

                }
            }

        } else {
            addFromFacility(location , facNumFacType , facilities) ;

        }
    }

    private void addKeyToRampDetail(String field, Map<String, List<Facility>> rampDetails, String joinedString) {
        final boolean addRamp = field != null && (field.equalsIgnoreCase("stop") || field.equalsIgnoreCase(SHIP_TO) || field.equalsIgnoreCase(SHIP_FROM) || field.equalsIgnoreCase(DESTINATION));
        if(addRamp) {
            rampDetails.put(joinedString , null) ;
        }
    }

    private void addFromFacility(Location location  , Set<String> facNumFacType , Map<String , Facility> facilities)  {
        if ( location instanceof Facility) {
            Facility facility = (Facility) location;

            String joinedString = buildCustNumLocSeq(
                    Arrays.asList(facility.getFacilityNumber(), facility.getFacilityType().toString()));
            facNumFacType.add(joinedString);
            facilities.put(joinedString, null);
        }
    }

    private void addFromParty(Party party, Set<String> customerNumbers, Map<String, Party> custNumLocNum ,
                              Map<String , List<Facility>> rampDetails) {
        String custNum = party.getCustomerAccountNumber();
        String locSeq = party.getAddress() != null ? findLocationNumber(party.getAddress()) : "None";
        String locationType = party.getAddress() != null ? party.getAddress().getLocationType().toString() : "None";
        if (locSeq != null && locSeq.equals("None"))
            locationType = "None";
        customerNumbers.add(custNum);
        String joinedString = buildCustNumLocSeq(Arrays.asList(custNum, locSeq, locationType)) ;
        custNumLocNum.put(joinedString, null);
        rampDetails.put(joinedString , null) ;
    }

    public void enhanceOrder(Order order, Map<String, Party> customerDetails, Map<String, Facility> facilities , Map<String , List<Facility>> rampDetails)


            throws JsonProcessingException {

        if (order.getOrderingParty().getCustomerAccountNumber() != null) {
            String key = buildCustNumLocSeq(
                    Arrays.asList(order.getOrderingParty().getCustomerAccountNumber(), "None", "None"));
            Contact contact = order.getOrderingParty().getOrderPartyContact();
            OrderingParty orderingParty = enhanceOrderingParty(customerDetails.get(key));
            if (orderingParty != null) {
                orderingParty.setOrderPartyContact(contact);
                order.setOrderingParty(orderingParty);
            }
        }

        order.setShipper(enhancePartyType(order.getShipper(), customerDetails , rampDetails));
        enhancePayerOfFreight(order, customerDetails);
        order.setShipFrom(enhanceLocationType(order.getShipFrom(), customerDetails, facilities , rampDetails , "ShipFrom"));
        enhanceCustomerOrder(order, customerDetails, facilities , rampDetails);

    }

    private void enhanceCustomerOrder(Order order, Map<String, Party> customerDetails, Map<String, Facility> facilities , Map<String , List<Facility>> rampDetails)
            throws JsonProcessingException {
        if(order.getCustomerOrder() != null) {
            CustomerOrder customerOrder = order.getCustomerOrder();
            enhanceEmptyDelivery(customerOrder.getOrderEquipmentRequest(), customerDetails ,facilities, rampDetails);
            enhanceLoadedShipment(customerOrder.getOrderShipmentRequest(), customerDetails, facilities , rampDetails);
        }

    }

    private void enhanceLoadedShipment(List<OrderShipmentRequest> orderShipmentRequests,
                                       Map<String, Party> customerDetails, Map<String, Facility> facilities , Map<String , List<Facility>> rampDetails) throws JsonProcessingException {
        if (orderShipmentRequests == null || orderShipmentRequests.isEmpty())
            return;
        for (OrderShipmentRequest orderShipmentRequest : orderShipmentRequests) {
            if (orderShipmentRequest.getConsignee() != null)
                orderShipmentRequest
                        .setConsignee(enhancePartyType(orderShipmentRequest.getConsignee(), customerDetails , rampDetails));

            if (orderShipmentRequest.getShipTo() != null)
                orderShipmentRequest
                        .setShipTo(enhanceLocationType(orderShipmentRequest.getShipTo(), customerDetails, facilities , rampDetails , "ShipTo"));

            List<Stop> stops = orderShipmentRequest.getOrderShipmentTransportStops();
            enhanceStops(stops, customerDetails, facilities, rampDetails);
        }
    }

    private void enhanceStops(List<Stop> stops, Map<String, Party> customerDetails, Map<String, Facility> facilities, Map<String , List<Facility>> rampDetails) {
        if (stops != null && !stops.isEmpty())
            for (Stop stop : stops)
                if (stop.getLocation() != null)
                    stop.setLocation(enhanceLocationType(stop.getLocation(), customerDetails, facilities , rampDetails , "stop" ));

    }

    private void enhanceEmptyDelivery(List<EmptyDelivery> orderEquipmentRequest, Map<String, Party> customerDetails , Map<String, Facility> facilities,  Map<String , List<Facility>> rampDetails) {

        if (orderEquipmentRequest == null || orderEquipmentRequest.isEmpty())
            return;
        for (EmptyDelivery emptyDelivery : orderEquipmentRequest)
            emptyDelivery.setDestination(enhanceLocationType(emptyDelivery.getDestination(), customerDetails , facilities, rampDetails, DESTINATION));
    }

    private Location enhanceLocationType(Location location, Map<String, Party> customerDetails,
                                         Map<String, Facility> facilities , Map<String , List<Facility>> rampDetails , String field) {
        if (location == null)
            return null;

        if (location instanceof CustomerAccountLocation)
            return enhanceCALType(location, customerDetails , rampDetails , field);
        else if (location instanceof IntermodalLocation)
            return enhanceILType(location, customerDetails , rampDetails , field);
        else
            return enhanceFacilityType(location, facilities);

    }

    private Location enhanceFacilityType(Location location, Map<String, Facility> facilities) {
        ObjectMapper mapper = new ObjectMapper();
        if (location instanceof Facility) {
            Facility facility = (Facility) location;
            if (facility.getFacilityNumber() == null || facility.getFacilityType() == null)
                return location;
            String key = buildCustNumLocSeq(
                    Arrays.asList(facility.getFacilityNumber(), facility.getFacilityType().toString()));
            try{
                return facilities.get(key) != null ? mapper.readValue(mapper.writeValueAsString(facilities.get(key)), Facility.class) : location;
            } catch (JsonProcessingException e) {
                throw new SystemException();
            }
        }
        else{
            return location;
        }
    }

    private Location enhanceCALType(Location location, Map<String, Party> customerDetails , Map<String , List<Facility>> rampDetails , String field) {
        CustomerAccountLocation customerAccountLocation = (CustomerAccountLocation) location;
        if (customerAccountLocation.getCustomer() == null)
            return location;
        String key;
        if (customerAccountLocation.getCustomerLocationSequenceNumber() != null) {
            String locSeq = customerAccountLocation.getCustomerLocationSequenceNumber().toString();
            key = buildCustNumLocSeq(Arrays.asList(customerAccountLocation.getCustomer().getCustomerAccountNumber(),
                    locSeq, CUSTOMER_ACCOUNT_LOCATION));
        } else
            key = buildCustNumLocSeq(
                    Arrays.asList(customerAccountLocation.getCustomer().getCustomerAccountNumber(), "None", "None"));

        Party enhancer = customerDetails.get(key);
        if (enhancer == null)
            return location;
        if (enhancer.getAddress() == null) {
            customerAccountLocation.setCustomer(enhanceCustomerAccount(enhancer));
            return customerAccountLocation;
        } else {
            Location enhancedLocation = buildLocationFromParty(enhancer);
            if(rampDetails != null && field != null)
                addRampsToLocation(enhancedLocation , rampDetails.get(key) , field);
            return enhancedLocation ;
        }

    }

    private void addRampsToLocation(Location enhancedLocation, List<Facility> facilities, String field ) {
        final boolean addRamp = field != null && (field.equalsIgnoreCase("stop") || field.equalsIgnoreCase(SHIP_TO) || field.equalsIgnoreCase(SHIP_FROM) || field.equalsIgnoreCase(DESTINATION));
        if(!addRamp) return ;
        if(enhancedLocation instanceof CustomerAccountLocation) {
            CustomerAccountLocation customerAccountLocation = (CustomerAccountLocation) enhancedLocation ;
            customerAccountLocation.setRamps(facilities);
        }else if(enhancedLocation instanceof  IntermodalLocation) {
            IntermodalLocation intermodalLocation = (IntermodalLocation) enhancedLocation ;
            intermodalLocation.setRamps(facilities);
        }

    }

    private Location enhanceILType(Location location, Map<String, Party> customerDetails , Map<String , List<Facility>> rampDetails , String field) {
        IntermodalLocation intermodalLocation = (IntermodalLocation) location;
        if (intermodalLocation.getCustomer() == null)
            return location;
        String key;
        if (intermodalLocation.getCustomerLocationSequenceNumber() != null) {
            String locSeq = intermodalLocation.getCustomerLocationSequenceNumber().toString();
            key = buildCustNumLocSeq(Arrays.asList(intermodalLocation.getCustomer().getCustomerAccountNumber(), locSeq,
                    INTERMODAL_LOCATION));
        } else
            key = buildCustNumLocSeq(
                    Arrays.asList(intermodalLocation.getCustomer().getCustomerAccountNumber(), "None", "None"));

        Party enhancer = customerDetails.get(key);
        if (enhancer == null)
            return location;
        if (enhancer.getAddress() == null) {
            intermodalLocation.setCustomer(enhanceCustomerAccount(enhancer));
            return intermodalLocation;
        } else{
            Location enhancedLocation =  buildLocationFromParty(enhancer);
            if(rampDetails != null && field != null)
                addRampsToLocation(enhancedLocation , rampDetails.get(key) , field );
            return enhancedLocation ;
        }

    }

    private void enhancePayerOfFreight(Order order, Map<String, Party> customerDetails) {
        if (order.getPayerOfFreight() != null && order.getPayerOfFreight().getCustomerAccountNumber() != null) {
            String key = buildCustNumLocSeq(
                    Arrays.asList(order.getPayerOfFreight().getCustomerAccountNumber(), "None", "None"));
            Party enhancer = customerDetails.get(key);
            if (enhancer != null)
                order.setPayerOfFreight(enhanceCustomerAccount(enhancer));
        }

    }

    private Party enhancePartyType(Party party, Map<String, Party> customerDetails , Map<String , List<Facility>> rampDetails) throws JsonProcessingException {
        if (party != null && party.getCustomerAccountNumber() != null) {
            String custNum = party.getCustomerAccountNumber();
            String key;
            Location location = party.getAddress();
            if (party.getAddress() != null) {
                String locSeq = findLocationNumber(party.getAddress());
                String locationType = party.getAddress().getLocationType().toString();
                if (locSeq != null && locSeq.equals("None"))
                    locationType = "None";
                key = buildCustNumLocSeq(Arrays.asList(custNum, locSeq, locationType));
            } else
                key = buildCustNumLocSeq(Arrays.asList(custNum, "None", "None"));

            Party enhancer = customerDetails.get(key);
            if (enhancer == null)
                return party;
            if (enhancer.getAddress() == null) {
                ObjectMapper mapper = new ObjectMapper();
                Party deepCopyPartyObj = mapper.readValue(mapper.writeValueAsString(enhancer), Party.class);
                deepCopyPartyObj.setAddress(location);
                return deepCopyPartyObj;
            } else{
                addRampToEnhancerParty(enhancer , rampDetails.get(key)) ;
                return enhancer;
            }

        }
        return party;
    }

    private void addRampToEnhancerParty(Party enhancer, List<Facility> facilities) {
        Location location = enhancer.getAddress() ;
        if(location instanceof  CustomerAccountLocation) {
            CustomerAccountLocation customerAccountLocation = (CustomerAccountLocation) location ;
            customerAccountLocation.setRamps(facilities);
        }else if(location instanceof  IntermodalLocation) {
            IntermodalLocation intermodalLocation = (IntermodalLocation) location ;
            intermodalLocation.setRamps(facilities);
        }
    }

    private Location buildLocationFromParty(Party party) {
        ObjectMapper mapper = new ObjectMapper();
        Object inputJSON = mapper.convertValue(party, Object.class);
        Object transformJSON = commonUtil.joltTransformation(inputJSON);
        return mapper.convertValue(transformJSON, Location.class);

    }

    private CustomerAccount enhanceCustomerAccount(Party party) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.convertValue(party, CustomerAccount.class);
    }

    private OrderingParty enhanceOrderingParty(Party party) {
        if (party == null)
            return null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.convertValue(party, OrderingParty.class);
    }


    private void enrichOrderStatusModel(OrderStatusModel orderStatusModel, Status status){
        LOGGER.info("EnrichOrderStatusModel: enriching order status model starts");
        ObjectMapper objectMapper = new ObjectMapper();
        OrderEquipmentStatusEvent orderEquipmentStatusEvent;
        if (Status.ACCEPTED.equals(status)) {
            Accepted accepted = objectMapper.convertValue(orderStatusModel.getOrderEquipmentStatusEvent(), Accepted.class);
            this.enrichEquipmentInformationForEmptyStatusUpdates(accepted.getEquipment());
            orderEquipmentStatusEvent = objectMapper.convertValue(accepted, OrderEquipmentStatusEvent.class);
        } else if (Status.ASSIGNED.equals(status)) {
            Assigned assigned = objectMapper.convertValue(orderStatusModel.getOrderEquipmentStatusEvent(), Assigned.class);
            this.enrichEquipmentInformationForEmptyStatusUpdates(assigned.getEquipment());
            orderEquipmentStatusEvent = objectMapper.convertValue(assigned, OrderEquipmentStatusEvent.class);
        } else if((Status.INTRANSIT.equals(status))){
            Intransit intransit = objectMapper.convertValue(orderStatusModel.getOrderEquipmentStatusEvent(), Intransit.class);
            this.enrichEquipmentInformationForEmptyStatusUpdates(intransit.getEquipment());
            orderEquipmentStatusEvent = objectMapper.convertValue(intransit, OrderEquipmentStatusEvent.class);

        }else{
            Completed completed = objectMapper.convertValue(orderStatusModel.getOrderEquipmentStatusEvent(), Completed.class);
            this.enrichEquipmentInformationForEmptyStatusUpdates(completed.getEquipment());
            orderEquipmentStatusEvent = objectMapper.convertValue(completed, OrderEquipmentStatusEvent.class);
        }
        LOGGER.info("EnrichOrderStatusModel: Enhanced OrderEquipmentStatus with Equipment Owner desc and Equipment type desc ");
        orderStatusModel.setOrderEquipmentStatusEvent(orderEquipmentStatusEvent);
    }


    private void enrichEquipmentInformationForEmptyStatusUpdates(Equipment equipment){
        EquipmentTypeOwner equipmentTypeOwner=null;
        if(equipment!=null){
            if(equipment.getAllocatedEquipmentOwner()!=null){
                equipmentTypeOwner = getEquipmentTypeOwner(equipment.getAllocatedEquipmentOwner());
                equipment.setAllocatedEquipmentOwnerDesc(equipmentTypeOwner.getEquipmentTypeOwnerCodeDesc());
                for (EquipmentCode equipmentCode : equipmentTypeOwner.getEquipmentCode()) {
                    if (equipmentCode.getEquipmentCode().getValue().equals(equipment.getAllocatedEquipmentType().trim())) {
                        equipment.setAllocatedEquipmentTypeDesc(equipmentCode.getEnglishDescription());
                    }
                }
            }
            if(equipment.getUsedEquipmentOwner()!=null){
                settingDescriptionInEquipment(equipment, equipmentTypeOwner);
            }
        }
    }


    private void settingDescriptionInEquipment(Equipment equipment, EquipmentTypeOwner equipmentTypeOwner) {
        if(equipment.getUsedEquipmentOwner().equals(equipment.getAllocatedEquipmentOwner())){
            settingDescriptionInSameUsedAllocatedEquipment(equipment, equipmentTypeOwner);
        }else{
            EquipmentTypeOwner usedEquipmentTypeOwner = getEquipmentTypeOwner(equipment.getUsedEquipmentOwner());
            equipment.setUsedEquipmentOwnerDesc(usedEquipmentTypeOwner.getEquipmentTypeOwnerCodeDesc());
            for (EquipmentCode equipmentCode : usedEquipmentTypeOwner.getEquipmentCode()) {
                if (equipmentCode.getEquipmentCode().getValue().equals(equipment.getUsedEquipmentType().trim())) {
                    equipment.setUsedEquipmentTypeDesc(equipmentCode.getEnglishDescription());
                }
            }
        }
    }

    private void settingDescriptionInSameUsedAllocatedEquipment(Equipment equipment, EquipmentTypeOwner equipmentTypeOwner) {
        equipment.setUsedEquipmentOwnerDesc(equipment.getAllocatedEquipmentOwnerDesc());
        if(equipment.getUsedEquipmentType().trim().equals(equipment.getAllocatedEquipmentType().trim())){
            equipment.setUsedEquipmentTypeDesc((equipment.getAllocatedEquipmentTypeDesc()));
        }else {
            if (equipmentTypeOwner != null) {
                for (EquipmentCode equipmentCode : equipmentTypeOwner.getEquipmentCode()) {
                    if (equipmentCode.getEquipmentCode().getValue().equals(equipment.getUsedEquipmentType().trim())) {
                        equipment.setUsedEquipmentTypeDesc(equipmentCode.getEnglishDescription());
                    }
                }
            }
        }
    }

    private EquipmentTypeOwner getEquipmentTypeOwner(String equipmentOwnerCode) {

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("equipmentTypeOwnerCode", equipmentOwnerCode);
        queryParams.add(LANG_KE, "en");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(referenceDomainILBUrl + getEquipmentType);
        UriComponentsBuilder uri = uriComponentsBuilder.queryParams(queryParams);
        HttpHeaders headers = authUtil.generateAuthorizationHeader(referenceDomainCloudRunUrl);
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        long startOfReferenceCall = System.currentTimeMillis();
        ResponseEntity<EquipmentTypeOwner[]> responseEntity = restTemplate.exchange(uri.toUriString(), HttpMethod.GET,
                request, EquipmentTypeOwner[].class);
        LOGGER.info("GetEquipmentTypeOwner: Retrieved Equipment Details in {} ms", System.currentTimeMillis() - startOfReferenceCall);
        List<EquipmentTypeOwner> equipmentTypeOwners = new ArrayList<>();
        if (responseEntity.getBody() != null) {
            equipmentTypeOwners = Arrays.asList(responseEntity.getBody());
        }
        return equipmentTypeOwners.get(0);
    }

    public void getOrderPartyForTransportShipment(TransportShipmentStatusModel transportShipmentStatusModel) {
        try {
            Set<String> customerNumbers = new HashSet<>();
            Map<String, Party> custNumLocNum = new HashMap<>();
            Map<String, Facility> facilities = new HashMap<>();
            Set<String> facNumFacType = new HashSet<>();
            List<Error> errors = new ArrayList<>();
            List<CustomerFilterPayload> customerFilterPayloadList = new ArrayList<>();


            if (transportShipmentStatusModel.getTransportShipmentStatusEvent().getStopDetails() != null) {
                for (StopDetails stopDetails : transportShipmentStatusModel.getTransportShipmentStatusEvent()
                        .getStopDetails()) {

                    if (stopDetails.getLocType().equalsIgnoreCase("customerAccountLocation")) {
                        CustomerAccountLocation customerAccountLocation = new CustomerAccountLocation();
                        CustomerAccount customerAccount = new CustomerAccount();
                        customerAccount.setCustomerAccountNumber(stopDetails.getCustNumber());
                        customerAccountLocation.setCustomer(customerAccount);
                        customerAccountLocation.setCustomerLocationSequenceNumber(stopDetails.getCustLocSeq());
                        customerAccountLocation.getCustomer().setCustomerAccountNumber(stopDetails.getCustNumber());
                        customerAccountLocation.setLocationType(Location.LocationTypeEnum.CUSTOMERACCOUNTLOCATION);
                        addFromLocationSite(customerAccountLocation, customerNumbers, facNumFacType, custNumLocNum,
                                facilities , null , null);
                        addToFilterPayload(customerFilterPayloadList, custNumLocNum, customerNumbers, null);
                        getCustomersAndLocationsWithRampsData(custNumLocNum, customerFilterPayloadList , null);

                        stopDetails
                                .setOrderParty(enhanceLocationType(customerAccountLocation, custNumLocNum, facilities, null , null));
                    } else if (stopDetails.getLocType().equalsIgnoreCase("IntermodalLocation")) {
                        IntermodalLocation intermodalLocation = new IntermodalLocation();
                        CustomerAccount customerAccount = new CustomerAccount();
                        customerAccount.setCustomerAccountNumber(stopDetails.getCustNumber());
                        intermodalLocation.setCustomer(customerAccount);
                        intermodalLocation.getCustomer().setCustomerAccountNumber(stopDetails.getCustNumber());
                        intermodalLocation.getCustomer().setCustomerAccountNumber(stopDetails.getCustNumber());
                        intermodalLocation.setCustomerLocationSequenceNumber(stopDetails.getCustLocSeq());
                        intermodalLocation.setLocationType(Location.LocationTypeEnum.INTERMODALLOCATION);
                        addFromLocationSite(intermodalLocation, customerNumbers, facNumFacType, custNumLocNum,
                                facilities , null , null);

                        addToFilterPayload(customerFilterPayloadList, custNumLocNum, customerNumbers, null);
                        getCustomersAndLocationsWithRampsData(custNumLocNum, customerFilterPayloadList , null);

                        stopDetails.setOrderParty(enhanceLocationType(intermodalLocation, custNumLocNum, facilities , null, null));
                    } else if (stopDetails.getLocType().equalsIgnoreCase("Facility")) {
                        Facility facility = new Facility();
                        facility.setFacilityNumber(stopDetails.getFacilityNumber().toString());
                        facility.setFacilityType(FacilityType.TERMINAL);
                        facility.setLocationType(Location.LocationTypeEnum.FACILITY);
                        addFromLocationSite(facility, customerNumbers, facNumFacType, custNumLocNum, facilities, null , null);

                        getFacilityData(errors, facNumFacType, facilities);

                        stopDetails.setOrderParty(enhanceLocationType(facility, custNumLocNum, facilities , null , null));
                    }
                }

            }

        } catch (JsonProcessingException exception) {
            throw new SystemException("Json Processing Exception", exception);
        }

    }

    public void getOrderPartyForConveyorFulfillment(OrderEquipmentStatusEvent orderEquipmentStatusEvent) {
        try {
            Set<String> customerNumbers = new HashSet<>();
            Map<String, Party> custNumLocNum = new HashMap<>();
            Map<String, Facility> facilities = new HashMap<>();
            Set<String> facNumFacType = new HashSet<>();
            List<Error> errors = new ArrayList<>();
            List<CustomerFilterPayload> customerFilterPayloadList = new ArrayList<>();
            if (orderEquipmentStatusEvent.getStopDetails() != null) {
                for (StopDetails stopDetails : orderEquipmentStatusEvent
                        .getStopDetails()) {
                    if (stopDetails.getLocType().equalsIgnoreCase("customerAccountLocation")) {
                        CustomerAccountLocation customerAccountLocation = new CustomerAccountLocation();
                        CustomerAccount customerAccount = new CustomerAccount();
                        customerAccount.setCustomerAccountNumber(stopDetails.getCustNumber());
                        customerAccountLocation.setCustomer(customerAccount);
                        customerAccountLocation.setCustomerLocationSequenceNumber(stopDetails.getCustLocSeq());
                        customerAccountLocation.getCustomer().setCustomerAccountNumber(stopDetails.getCustNumber());
                        customerAccountLocation.setLocationType(Location.LocationTypeEnum.CUSTOMERACCOUNTLOCATION);
                        addFromLocationSite(customerAccountLocation, customerNumbers, facNumFacType, custNumLocNum,
                                facilities , null , null);
                        addToFilterPayload(customerFilterPayloadList, custNumLocNum, customerNumbers, null);
                        getCustomersAndLocationsWithRampsData(custNumLocNum, customerFilterPayloadList , null);

                        stopDetails
                                .setOrderParty(enhanceLocationType(customerAccountLocation, custNumLocNum, facilities, null , null));
                    } else if (stopDetails.getLocType().equalsIgnoreCase("IntermodalLocation")) {
                        IntermodalLocation intermodalLocation = new IntermodalLocation();
                        CustomerAccount customerAccount = new CustomerAccount();
                        customerAccount.setCustomerAccountNumber(stopDetails.getCustNumber());
                        intermodalLocation.setCustomer(customerAccount);
                        intermodalLocation.getCustomer().setCustomerAccountNumber(stopDetails.getCustNumber());
                        intermodalLocation.getCustomer().setCustomerAccountNumber(stopDetails.getCustNumber());
                        intermodalLocation.setCustomerLocationSequenceNumber(stopDetails.getCustLocSeq());
                        intermodalLocation.setLocationType(Location.LocationTypeEnum.INTERMODALLOCATION);
                        addFromLocationSite(intermodalLocation, customerNumbers, facNumFacType, custNumLocNum,
                                facilities , null , null);

                        addToFilterPayload(customerFilterPayloadList, custNumLocNum, customerNumbers, null);
                        getCustomersAndLocationsWithRampsData(custNumLocNum, customerFilterPayloadList , null);

                        stopDetails.setOrderParty(enhanceLocationType(intermodalLocation, custNumLocNum, facilities , null, null));
                    } else if (stopDetails.getLocType().equalsIgnoreCase("Facility")) {
                        Facility facility = new Facility();
                        facility.setFacilityNumber(stopDetails.getFacilityNumber().toString());
                        facility.setFacilityType(FacilityType.TERMINAL);
                        facility.setLocationType(Location.LocationTypeEnum.FACILITY);
                        addFromLocationSite(facility, customerNumbers, facNumFacType, custNumLocNum, facilities, null , null);

                        getFacilityData(errors, facNumFacType, facilities);

                        stopDetails.setOrderParty(enhanceLocationType(facility, custNumLocNum, facilities , null , null));
                    }
                }

            }

        } catch (JsonProcessingException exception) {
            throw new SystemException("Json Processing Exception", exception);
        }

    }
    @Override
    public Order cancelOrder(String orderId, Order order) {
        HttpHeaders headers = authUtil.generateAuthorizationHeader(orderDomainDevUrl);
        HttpEntity<Order> entity = new HttpEntity<>(order, headers);
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("orderId", orderId);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(orderDomainUrl + cancelOrder);
        String statusUpdateUrl = uriComponentsBuilder.buildAndExpand(pathParams).toUriString();
        ResponseEntity<Order> response = restTemplate.exchange(statusUpdateUrl, HttpMethod.PUT, entity, Order.class);
        return response.getBody();
    }

    @Override
    public Order cancelPartialOrder(String orderId, String itemRequestId, Order order) {
        HttpHeaders headers = authUtil.generateAuthorizationHeader(orderDomainDevUrl);
        long startTime = System.currentTimeMillis();
        HttpEntity<Order> entity = new HttpEntity<>(order, headers);
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("orderId", orderId);
        pathParams.put("itemRequestId", itemRequestId);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(orderDomainUrl + cancelPartialOrder);
        String statusUpdateUrl = uriComponentsBuilder.buildAndExpand(pathParams).toUriString();
        ResponseEntity<Order> response = restTemplate.exchange(statusUpdateUrl, HttpMethod.PUT, entity, Order.class);

        LOGGER.info("CancelPartialOrder: Time taken by Order Domain service to cancel Partial Order {}", startTime - System.currentTimeMillis());
        return response.getBody();
    }
    @Override
    public Order getOrderById(String orderId) {
        long startTime = System.currentTimeMillis();
        Order order = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        HttpHeaders headers = authUtil.generateAuthorizationHeader(orderDomainDevUrl);

        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        long callDomainServiceTime = System.currentTimeMillis();
        ResponseEntity<Order> response = restTemplate.exchange(orderDomainUrl + getOrderById, HttpMethod.GET,
                request, Order.class, orderId);
        long responseFromDomainServiceTime = System.currentTimeMillis() - callDomainServiceTime;
        LOGGER.info("GetOrderById: Time taken to get order details from Domain service  {}ms ", responseFromDomainServiceTime);

        order = response.getBody();
        List<Error> errors = new ArrayList<>();
        Set<String> facNumFacType = new HashSet<>();
        Map<String, Facility> facilities = new HashMap<>();
        assert order != null;
        validateOrderResponseFromDomain(order, facNumFacType, facilities, errors);
        long responseFromApplicationServiceTime = System.currentTimeMillis() - startTime;
        LOGGER.info("GetOrderById: Time taken for getOrderById in application service {} ", responseFromApplicationServiceTime);
        return order;
    }

    private void validateOrderResponseFromDomain(Order order, Set<String> facNumFacType, Map<String, Facility> facilities, List<Error> errors) {
        if (order != null && order.getCustomerOrder() != null && order.getCustomerOrder().getOrderShipmentRequest() != null) {
            handleAndValidateFacilityData(order, facNumFacType, facilities, errors);
        }
    }

    private void handleAndValidateFacilityData(Order order, Set<String> facNumFacType, Map<String, Facility> facilities, List<Error> errors) {
        for (OrderShipmentRequest orderShipmentRequest : order.getCustomerOrder().getOrderShipmentRequest()) {
            if (orderShipmentRequest.getReservation() != null && orderShipmentRequest.getReservation().getOriginStation() != null)
                addFromFacility(orderShipmentRequest.getReservation().getOriginStation(), facNumFacType, facilities);
        }
        try {
            getFacilityData(errors, facNumFacType, facilities);
        } catch (JsonProcessingException e) {
            SystemException systemException = new SystemException();
            systemException.initCause(e);
            throw systemException;
        }
        for (OrderShipmentRequest orderShipmentRequest : order.getCustomerOrder().getOrderShipmentRequest()) {
            if (orderShipmentRequest.getReservation() != null && orderShipmentRequest.getReservation().getOriginStation() != null) {
                Location facility = enhanceFacilityType(orderShipmentRequest.getReservation().getOriginStation(), facilities);
                orderShipmentRequest.getReservation().setOriginStation((Facility) facility);
            }

            }
    }

    @Override
    public ResponseEntity<List<Order>> searchOrder(OrdersSearchRequest ordersSearchRequest,String langKey) {
        HttpHeaders headers = authUtil.generateAuthorizationHeader(orderDomainDevUrl);
        HttpEntity<OrdersSearchRequest> request = new HttpEntity<>(ordersSearchRequest, headers);
        ResponseEntity<Order[]> response = restTemplate.postForEntity(orderDomainUrl + searchOrders,
                request, Order[].class);
        int totalOrders = validateTotalCount(response.getHeaders().getFirst("totalorders"));
        Order[] orderList = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        List<Order> objectList = Arrays
                .asList(mapper.convertValue(orderList, Order[].class));
        HttpHeaders responseHeaders = new HttpHeaders();
        if (!objectList.isEmpty()) {
            responseHeaders.set("totalOrders", String.valueOf(totalOrders));
        }
        if(LANG_KEY.equals(langKey)){
            List<Order> frenchTranslatedOrderList = new ArrayList<>();
            for(Order order : objectList){
                frenchTranslatedOrderList.add(multiLanguageService.returnFrenchTranslatedOrder(order));
            }
            LOGGER.info("SearchOrder: French translation completed for search order");
            return  new ResponseEntity<>(frenchTranslatedOrderList,responseHeaders,HttpStatus.OK);
        }
        return new ResponseEntity<>(objectList, responseHeaders, HttpStatus.OK);

    }
    private int validateTotalCount(String countFromHeader) {
        if (countFromHeader == null || countFromHeader.isEmpty()) {
            countFromHeader = "0";
        }
        String regexPattern = "//d+";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(countFromHeader);
        if (matcher.find()) {
            countFromHeader = matcher.group();
        }
        return Integer.parseInt(countFromHeader);
    }
}
