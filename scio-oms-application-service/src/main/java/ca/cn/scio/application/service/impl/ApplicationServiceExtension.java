package ca.cn.scio.application.service.impl;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.json.simple.JSONArray;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import  ca.cn.scio.application.exception.Error;
import ca.cn.scio.application.exception.ErrorCode;
import ca.cn.scio.application.exception.ErrorResponse;
import ca.cn.scio.application.resource.Facility;
import ca.cn.scio.application.util.AuthUtil;
import ca.cn.scio.application.util.VariableUtil;

@Component
public class ApplicationServiceExtension {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ApplicationServiceExtension.class);

    private final RestTemplate restTemplate;
    private final AuthUtil authUtil;

    @Autowired
    public ApplicationServiceExtension(RestTemplate restTemplate, AuthUtil authUtil) {
        this.restTemplate = restTemplate;
        this.authUtil = authUtil;
    }


    @Value("${referenceDomainUrl}")
    private String referenceDomainUrl;
    //** call to reference service and enhance facility details
    @Async
    public CompletableFuture<Void> callReferenceDomainForTerminal(UriComponentsBuilder url, String key, Map<String, Facility> facilities, List<Error> errors) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            long startTime = System.currentTimeMillis() ;
            facilities.replace(key, callReferenceDomain(url));
            String[] splits = key.split(VariableUtil.DELIMITER) ;
            LOGGER.info("CallReferenceDomainForTerminal: Retrieved and stored facility details in {} ms | Facility Number : {} , Facility Type : {}" , System.currentTimeMillis() - startTime , splits[0] , splits[1]);
        } catch (HttpClientErrorException exception) {

            if (exception.getStatusCode().equals(HttpStatus.NOT_FOUND)) return CompletableFuture.completedFuture(null);
            else if (exception.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                ErrorResponse errorResponse = mapper.readValue(exception.getResponseBodyAsString(), ErrorResponse.class);
                saveError(errors, ErrorCode.SCIOAPP005, errorResponse.getErrors().get(0).getDescription());
            } else throw exception;
        }
        return CompletableFuture.completedFuture(null);
    }


    private Facility callReferenceDomain(UriComponentsBuilder url) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = authUtil.generateAuthorizationHeader(referenceDomainUrl);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        JSONArray allFacilities = restTemplate.exchange(url.toUriString(), HttpMethod.GET, entity, JSONArray.class).getBody();
        assert allFacilities != null;
        String responseSting = allFacilities.toJSONString();
        List<Facility> facilities = Arrays.asList(mapper.readValue(responseSting, Facility[].class));
        return facilities.get(0);
    }

    //** save 400 client error
    public void saveError(List<Error> errors, ErrorCode errorCode, String message, String... msgArgs) {
        Error error = new Error();
        error.setError(errorCode);
        if (msgArgs.length == 3)
            error.setDescription(MessageFormat.format(message, msgArgs[0], msgArgs[1], msgArgs[2]));
        else error.setDescription(message);
        errors.add(error);
    }


}
