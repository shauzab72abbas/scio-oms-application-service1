package ca.cn.scio.application.service.impl;


import ca.cn.scio.application.resource.*;
import ca.cn.scio.application.util.AuthUtil;
import org.aspectj.weaver.ast.Or;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BreApiServiceImpl {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ReferenceApiServiceImpl.class);
    private final RestTemplate restTemplate;
    private final AuthUtil authUtil;

    @Value("${breILBUrl}")
    private String breILBUrl;
    @Value("${getRules}")
    private String getRules;
    @Value("${breURL}")
    private String breCloudRunUrl;


    public BreApiServiceImpl(RestTemplate restTemplate, AuthUtil authUtil) {
        this.restTemplate = restTemplate;
        this.authUtil = authUtil;
    }
    public List<Rule> getRules() {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(breILBUrl + getRules);
        HttpHeaders headers = authUtil.generateAuthorizationHeader(breCloudRunUrl);
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        long startOfReferenceCall = System.currentTimeMillis();
        ResponseEntity<Rule[]> responseEntity = restTemplate.exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET,
                request, Rule[].class);
        log.info("Retrieved Rules in {} ms", System.currentTimeMillis() - startOfReferenceCall);
        List<Rule> rules = new ArrayList<>();
        if (responseEntity.getBody() != null) {
            rules = Arrays.asList(responseEntity.getBody());
        }
        return rules;
    }
    public String getErrorMessageByLanguage(Rule rule, ErrorMessageLanguage.LanguageNameEnum language){
        for(ErrorMessageLanguage errorMessageLanguage : rule.getErrorMessageLanguage()){
            if(errorMessageLanguage.getLanguageName().equals(language)){
                return errorMessageLanguage.getErrorMessage();
            }
        }
        return null;
    }
    public void setErrorMessageByLanguage(Rule rule, ErrorMessageLanguage.LanguageNameEnum language, String errorMessage){
        for(ErrorMessageLanguage errorMessageLanguage : rule.getErrorMessageLanguage()){
            if(errorMessageLanguage.getLanguageName().equals(language)){
                errorMessageLanguage.setErrorMessage(errorMessage);
            }
        }

    }
    public Order reformatEnglishErrorMessage(Order order){
        if(order.getOrderError()!=null){
            for(int i=0;i<order.getOrderError().size();i++){
                String errorMessage = order.getOrderError().get(i).getErrorType().replace("<","").replace(">","");
                order.getOrderError().get(i).setErrorType(errorMessage);
            }
        }
        if(order.getCustomerOrder().getOrderError()!=null) {
            for (int i = 0; i < order.getCustomerOrder().getOrderError().size(); i++) {
                String errorMessage = order.getCustomerOrder().getOrderError().get(i).getErrorType().replace("<", "").replace(">", "");
                order.getCustomerOrder().getOrderError().get(i).setErrorType(errorMessage);

            }
        }

        if(order.getCustomerOrder().getOrderEquipmentRequest()!=null){
            formatEmptyErrorMessages(order);
        }
        if(order.getCustomerOrder().getOrderShipmentRequest()!=null){
            formatShipmentErrorMessages(order);
        }
        return order;
    }

    public void formatEmptyErrorMessages(Order order){
        for(int i = 0; i < order.getCustomerOrder().getOrderEquipmentRequest().size();i++){
            if(order.getCustomerOrder().getOrderEquipmentRequest().get(i).getOrderError()!=null) {
                for (int j = 0; j < order.getCustomerOrder().getOrderEquipmentRequest().get(i).getOrderError().size(); j++) {
                    String errorMessage = order.getCustomerOrder().getOrderEquipmentRequest().get(i).getOrderError().get(j).getErrorType().replace("<", "").replace(">", "");
                    order.getCustomerOrder().getOrderEquipmentRequest().get(i).getOrderError().get(j).setErrorType(errorMessage);
                }
            }
        }

    }
    public void formatShipmentErrorMessages(Order order){
        for(int i = 0; i < order.getCustomerOrder().getOrderShipmentRequest().size();i++){
            if(order.getCustomerOrder().getOrderShipmentRequest().get(i).getOrderError()!=null) {
                for (int j = 0; j < order.getCustomerOrder().getOrderShipmentRequest().get(i).getOrderError().size(); j++) {
                    String errorMessage = order.getCustomerOrder().getOrderShipmentRequest().get(i).getOrderError().get(j).getErrorType().replace("<", "").replace(">", "");
                    order.getCustomerOrder().getOrderShipmentRequest().get(i).getOrderError().get(j).setErrorType(errorMessage);
                }
            }
            formatStopErrorMessages(order.getCustomerOrder().getOrderShipmentRequest().get(i));
        }
    }
    public void formatStopErrorMessages(OrderShipmentRequest shipmentRequest){
        if(shipmentRequest.getOrderShipmentTransportStops()!=null){
            for(int i =0 ; i < shipmentRequest.getOrderShipmentTransportStops().size(); i++){
                if(shipmentRequest.getOrderShipmentTransportStops().get(i).getOrderError()!=null) {
                    for (int j = 0; j < shipmentRequest.getOrderShipmentTransportStops().get(i).getOrderError().size(); j++) {
                        String errorMessage = shipmentRequest.getOrderShipmentTransportStops().get(i).getOrderError().get(j).getErrorType().replace("<", "").replace(">", "");
                        shipmentRequest.getOrderShipmentTransportStops().get(i).getOrderError().get(j).setErrorType(errorMessage);
                    }
                }
            }
        }

    }
}
