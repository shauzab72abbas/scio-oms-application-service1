package ca.cn.scio.application.service.impl;

import java.util.List;
import java.util.Objects;

import ca.cn.scio.application.resource.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class MultiLanguageServiceImpl {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MultiLanguageServiceImpl.class);
    private final CacheManager cacheManager;
    private final ReferenceApiServiceImpl referenceApiService;
    private final BreApiServiceImpl breApiService;


    @Autowired
    public MultiLanguageServiceImpl(CacheManager cacheManager, ReferenceApiServiceImpl referenceApiService, BreApiServiceImpl breApiService) {
        this.cacheManager = cacheManager;
        this.referenceApiService = referenceApiService;
        this.breApiService = breApiService;
    }


    public String getFrenchDescriptionForEqpCode(String equipmentTypeOwner, EquipmentTypeCode equipmentCode) {
        Cache cache = cacheManager.getCache("equipmentTypeOwnerCache");
        log.info("EquipmentTypeOwner cache details fetched");
        assert cache != null;
        EquipmentTypeOwner equipmentOwner = cache.get(equipmentTypeOwner, EquipmentTypeOwner.class);
        if (equipmentOwner != null) {
            for (EquipmentCode eqpCode : equipmentOwner.getEquipmentCode()) {
                if (eqpCode.getEquipmentCode().toString().equals(equipmentCode.toString())) {
                    return eqpCode.getFrenchDescription();
                }
            }
        }
        return "";

    }
    public String getFrenchDescriptionForBusinessConstant(String businessConstantCode){
        Cache cache = cacheManager.getCache("businessConstantCache");
        log.info("BusinessConstants cache details fetched");
        assert cache != null;
        BusinessConstants businessConstants = cache.get(businessConstantCode, BusinessConstants.class);
        if(businessConstants !=null){
            return  businessConstants.getDescription();
        }
        return "";

    }


    public String getFrenchDescriptionForHarmonizedCode(String harmonizedCode) {
        Cache cache = cacheManager.getCache("harmonizedCodeCache");
        if (cache != null) {
            HarmonizedCode hscCode = cache.get(harmonizedCode, HarmonizedCode.class);
            if (hscCode != null) {
                log.info("Value found in cache for harmonizedCode -{}",harmonizedCode);
                return hscCode.getHarmonizedDescription();
            } else {
                log.info("Value Not found in cache.Reference call started for harmonizedCode -{}",harmonizedCode);
                HarmonizedCode hsCode= referenceApiService.getHarmonizedCode(harmonizedCode).get(0);
                cache.put(harmonizedCode, hsCode);
                log.info(" Value added in cache for harmonizedCode -{}",harmonizedCode);
                return hsCode.getHarmonizedDescription();
            }
        }

        return "";
    }
    public String getFrenchErrorMessage(String ruleId,String errorMessage){
        Cache cache = cacheManager.getCache("breCache");
        assert cache != null;
        Rule rule = cache.get(ruleId,Rule.class);
        if(rule!=null){
            log.info("Value found in Cache for ruleId -{}",ruleId);
            return getFrenchErrorMessage(errorMessage, rule);
        }
         log.info("Value not found in Cache for ruleId -{}",ruleId);
         log.info("Started the getRules call");
         List<Rule> rules = breApiService.getRules();
         for(Rule ruleFromApi : rules){
             if(ruleFromApi.getRuleConfigId().equals(ruleId)){
                 log.info("Value found in API call for ruleId -{}",ruleId);
                 return getFrenchErrorMessage(errorMessage, ruleFromApi);
             }
         }
        return "";
    }

    private String getFrenchErrorMessage(String errorMessage, Rule rule) {
        String frenchErrorMessage = breApiService.getErrorMessageByLanguage(rule, ErrorMessageLanguage.LanguageNameEnum.FRENCH);
        if(errorMessage.contains("<")){
            return extractFrenchErrorMessageWithConfigValues(frenchErrorMessage,errorMessage);
        }
        return frenchErrorMessage;
    }

    public String extractFrenchErrorMessageWithConfigValues(String frenchErrorMessage , String englishErrorMessageWithConfigParams){
        int openingDiscriminatorIndex = 0 ;
        int closingDiscriminatorIndex = 0 ;
        log.info("Started extracting config values from english error message");
        List<String> configValues = new ArrayList<>();
        while(true){
            openingDiscriminatorIndex = englishErrorMessageWithConfigParams.indexOf("<",openingDiscriminatorIndex);
            closingDiscriminatorIndex = englishErrorMessageWithConfigParams.indexOf(">",closingDiscriminatorIndex);
            if(openingDiscriminatorIndex!=-1 && closingDiscriminatorIndex!=-1){
                configValues.add(englishErrorMessageWithConfigParams.substring(openingDiscriminatorIndex+1,closingDiscriminatorIndex));
                openingDiscriminatorIndex+=1;
                closingDiscriminatorIndex+=1;
            }
            else{
                break;
            }
        }
        log.info("extracted config values with size : {}",configValues.size());

        for (String configValue : configValues) {
            frenchErrorMessage = frenchErrorMessage.replaceFirst("<Config_Value>", configValue);
        }
        return frenchErrorMessage;
    }
    public Order returnFrenchTranslatedOrder(Order order) {
        if(order.getOrderError()!=null){
            log.info("Started translating order error for order id : {}", order.getOrderId());
            for(int i = 0 ; i< order.getOrderError().size(); i++){
                String englishErrorMessage = order.getOrderError().get(i).getErrorType();
                String ruleId = order.getOrderError().get(i).getRuleConfigId();
                String frenchErrorMessage = this.getFrenchErrorMessage(ruleId,englishErrorMessage);
                order.getOrderError().get(i).setErrorType(frenchErrorMessage);
            }
        }
        if(order.getCustomerOrder().getOrderError()!=null){
            log.info("Started translating order error for customer order level errors");
            for(int i = 0 ; i< order.getCustomerOrder().getOrderError().size(); i++){
                String englishErrorMessage = order.getCustomerOrder().getOrderError().get(i).getErrorType();
                String ruleId = order.getCustomerOrder().getOrderError().get(i).getRuleConfigId();
                String frenchErrorMessage = this.getFrenchErrorMessage(ruleId,englishErrorMessage);
                order.getCustomerOrder().getOrderError().get(i).setErrorType(frenchErrorMessage);
            }
        }

        convertEmptyDeliveryToFrench(order.getCustomerOrder().getOrderEquipmentRequest());

        if (order.getCustomerOrder().getOrderShipmentRequest() != null) {
            convertOrderShipmentRequestToFrench(order.getCustomerOrder().getOrderShipmentRequest());
        }
        log.info("French translation completed");

        return order;
    }

    public void convertEmptyDeliveryToFrench(List<EmptyDelivery> emptyDeliveryList){
        if (emptyDeliveryList != null) {
            for (EmptyDelivery emptyDelivery : emptyDeliveryList) {
                convertEquipmentTypeToFrench(emptyDelivery);

                if(emptyDelivery.getOrderError()!=null){
                    log.info("Started translating empty delivery with orderEquipmentRequestId : {}", emptyDelivery.getOrderEquipmentRequestId());
                    emptyDeliveryErrorTypeChange(emptyDelivery);
                }

                List<Reference> referenceList = emptyDelivery.getOrderEquipmentReference();

                if (referenceList != null && !referenceList.isEmpty()) {
                        convertReferenceTypeToFrench(referenceList);

                }
                if (emptyDelivery.getCancellation() != null) {
                    convertCancellationReasonToFrench(emptyDelivery.getCancellation());
                }
            }
        }
    }

    public void emptyDeliveryErrorTypeChange(EmptyDelivery emptyDelivery){
        for(int i = 0; i< emptyDelivery.getOrderError().size(); i++){
            String englishErrorMessage = emptyDelivery.getOrderError().get(i).getErrorType();
            String ruleId = emptyDelivery.getOrderError().get(i).getRuleConfigId();
            String frenchErrorMessage = this.getFrenchErrorMessage(ruleId,englishErrorMessage);
            emptyDelivery.getOrderError().get(i).setErrorType(frenchErrorMessage);
        }
    }

    public void convertOrderShipmentRequestToFrench(List<OrderShipmentRequest> orderShipmentRequestList){

            for (OrderShipmentRequest orderShipmentRequest : orderShipmentRequestList) {
                convertEqpTypeToFrenchShipment(orderShipmentRequest);

                if(orderShipmentRequest.getOrderError()!=null){
                    log.info("Started translating error orderShipmentRequestID : {}", orderShipmentRequest.getOrderShipmentRequestId());
                    orderShipmentErrorTypeChange(orderShipmentRequest);
                }

                List<Reference> referenceList = orderShipmentRequest.getOrderShipmentReferences();


                if (referenceList != null && !referenceList.isEmpty()) {
                     convertReferenceTypeToFrench(referenceList);
                }
                List<ShipmentCommodity> commodityList = orderShipmentRequest.getOrderShipmentCommodities();
                if (commodityList != null) {
                    convertShipmentCommodityToFrench(commodityList);
                }
                if (orderShipmentRequest.getCancellation() != null) {
                    convertCancellationReasonToFrench(orderShipmentRequest.getCancellation());
                }
                if(orderShipmentRequest.getOrderShipmentTransportStops()!=null){
                    convertTransportStopsToFrench(orderShipmentRequest);
                }

            }

    }

    public void orderShipmentErrorTypeChange(OrderShipmentRequest orderShipmentRequest){
        for(int i = 0; i< orderShipmentRequest.getOrderError().size(); i++){
            String englishErrorMessage = orderShipmentRequest.getOrderError().get(i).getErrorType();
            String ruleId = orderShipmentRequest.getOrderError().get(i).getRuleConfigId();
            String frenchErrorMessage = this.getFrenchErrorMessage(ruleId,englishErrorMessage);
            orderShipmentRequest.getOrderError().get(i).setErrorType(frenchErrorMessage);
        }
    }

    public void convertShipmentCommodityToFrench(List<ShipmentCommodity> commodityList){
        for (ShipmentCommodity commodity : commodityList) {
            if (commodity.getHarmonizedSystemCodes() != null && !commodity.getHarmonizedSystemCodes().isEmpty()) {
                for (HSCode hsCode : commodity.getHarmonizedSystemCodes()) {
                    convertHscToFrench(hsCode);
                }
            }

        }

    }

    public void convertEquipmentTypeToFrench(EmptyDelivery emptyDelivery){
        if(Objects.nonNull(emptyDelivery.getEquipmentOwner()) && Objects.nonNull(emptyDelivery.getEquipmentType())) {
            String eqpFrench = getFrenchDescriptionForEqpCode(emptyDelivery.getEquipmentOwner().toString(), emptyDelivery.getEquipmentType());
            if (eqpFrench != null && !eqpFrench.isEmpty()) {
                emptyDelivery.setEquipmentTypeDesc(eqpFrench);
            }
        }
       if(Objects.nonNull(emptyDelivery.getAllocatedEquipmentOwner()) && Objects.nonNull(emptyDelivery.getAllocatedEquipmentType())) {
            String allocatedEqpFrench = getFrenchDescriptionForEqpCode(emptyDelivery.getAllocatedEquipmentOwner().toString(), emptyDelivery.getAllocatedEquipmentType());
            if (allocatedEqpFrench != null && !allocatedEqpFrench.isEmpty()) {
                emptyDelivery.setAllocatedEquipmentTypeDesc(allocatedEqpFrench);
            }
        }
    }

    public void convertReferenceTypeToFrench(List<Reference> referenceList){
        for (Reference reference : referenceList) {
            if (reference.getReferenceType() != null && !reference.getReferenceType().isEmpty()) {
                String refValueFrench = getFrenchDescriptionForBusinessConstant(reference.getReferenceType());
                if (refValueFrench != null && !refValueFrench.isEmpty()) {
                    reference.setReferenceValue(refValueFrench);
                }
            }
        }

    }

    public void convertTransportStopsToFrench(OrderShipmentRequest orderShipmentRequest){
        for(Stop stop : orderShipmentRequest.getOrderShipmentTransportStops()){
            if(stop.getOrderError()!=null){
                log.info("Started translating error for stop with orderShipmentTransportStopId : {} ", stop.getOrderShipmentTransportStopId());
                for(int i = 0; i< stop.getOrderError().size(); i++){
                    String englishErrorMessage = stop.getOrderError().get(i).getErrorType();
                    String ruleId = stop.getOrderError().get(i).getRuleConfigId();
                    String frenchErrorMessage = this.getFrenchErrorMessage(ruleId,englishErrorMessage);
                    stop.getOrderError().get(i).setErrorType(frenchErrorMessage);
                }
            }
            List<Reference> referenceList = stop.getOrderShipmentStopReferences();
            if (referenceList != null && !referenceList.isEmpty()) {
                convertReferenceTypeToFrench(referenceList);
            }
        }
    }

//    public void convertPackageTypeToFrench(OrderShipmentRequest orderShipmentRequest){
//        if(orderShipmentRequest.getTotalQuantityUom()!=null && !orderShipmentRequest.getTotalQuantityUom().isEmpty()) {
//            String packageTypeFrench = getFrenchDescriptionForBusinessConstant(orderShipmentRequest.getTotalQuantityUom());
//            if (packageTypeFrench != null && !packageTypeFrench.isEmpty()) {
//                orderShipmentRequest.setTotalQuantityUom(packageTypeFrench);
//            }
//        }
//    }

   

    public void convertCancellationReasonToFrench(Cancellation cancellation){
        if(cancellation.getCancellationReasonCode()!=null && !cancellation.getCancellationReasonCode().isEmpty()) {
            String cancelReasonFrench = getFrenchDescriptionForBusinessConstant(cancellation.getCancellationReasonCode());
            if (cancelReasonFrench != null && !cancelReasonFrench.isEmpty()) {
                cancellation.setCancellationReasonDesc(cancelReasonFrench);
            }
        }
    }

    public void convertEqpTypeToFrenchShipment(OrderShipmentRequest orderShipmentRequest){
        if(orderShipmentRequest.getOrderedEquipmentOwner() != null && orderShipmentRequest.getOrderedEquipmentOwner().toString()!=null && !orderShipmentRequest.getOrderedEquipmentOwner().toString().isEmpty() 
        && orderShipmentRequest.getOrderedEquipmentType() != null && orderShipmentRequest.getOrderedEquipmentType()!=null && !orderShipmentRequest.getOrderedEquipmentType().toString().isEmpty() ) {
            String eqpFrench = getFrenchDescriptionForEqpCode(orderShipmentRequest.getOrderedEquipmentOwner().toString(), orderShipmentRequest.getOrderedEquipmentType());
            if (eqpFrench != null && !eqpFrench.isEmpty()) {
                orderShipmentRequest.setOrderedEquipmentTypeDesc(eqpFrench);
            }
        }

    }
    public  void convertHscToFrench(HSCode hsCode){
        if(hsCode.getHarmonizedCode()!=null && !hsCode.getHarmonizedCode().isEmpty()) {
            String hscFrenchDesc = getFrenchDescriptionForHarmonizedCode(hsCode.getHarmonizedCode());
            if (hscFrenchDesc != null && !hscFrenchDesc.isEmpty()) {
                hsCode.setHarmonizedCodeDesc(hscFrenchDesc);
            }
        }
    }

}
