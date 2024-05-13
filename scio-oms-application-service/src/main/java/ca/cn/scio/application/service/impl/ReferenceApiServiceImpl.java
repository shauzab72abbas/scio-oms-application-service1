package ca.cn.scio.application.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ca.cn.scio.application.resource.BusinessConstants;
import ca.cn.scio.application.resource.EquipmentTypeOwner;
import ca.cn.scio.application.resource.HarmonizedCode;
import ca.cn.scio.application.util.AuthUtil;

@Service
public class ReferenceApiServiceImpl {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ReferenceApiServiceImpl.class);
    private final RestTemplate restTemplate;
    private final AuthUtil authUtil;
    @Value("${referenceDomainILBUrl}")
    private String referenceDomainILBUrl;
    @Value("${equipmentTypes}")
    private String getEquipmentType;
    @Value("${referenceDomainUrl}")
    private String referenceDomainCloudRunUrl;
    @Value("${businessConstants}")
    private String getReferences;
    @Value("${harmonizedCodes}")
    private String getHarmonizedCode;

    @Autowired
    public ReferenceApiServiceImpl(AuthUtil authUtil, RestTemplate restTemplate) {
        this.authUtil = authUtil;
        this.restTemplate = restTemplate;
    }

    public List<EquipmentTypeOwner> getEquipmentTypeOwner() {

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("langKey", "fr");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(referenceDomainILBUrl + getEquipmentType);
        UriComponentsBuilder uri = uriComponentsBuilder.queryParams(queryParams);
        HttpHeaders headers = authUtil.generateAuthorizationHeader(referenceDomainCloudRunUrl);
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        long startOfReferenceCall = System.currentTimeMillis();
        ResponseEntity<EquipmentTypeOwner[]> responseEntity = restTemplate.exchange(uri.toUriString(), HttpMethod.GET,
                request, EquipmentTypeOwner[].class);
        log.info("Retrieved Equipment Details in {} ms", System.currentTimeMillis() - startOfReferenceCall);
        List<EquipmentTypeOwner> equipmentTypeOwnerList = new ArrayList<>();
        if (responseEntity.getBody() != null) {
            equipmentTypeOwnerList = Arrays.asList(responseEntity.getBody());
        }
        return equipmentTypeOwnerList;
    }

    public List<BusinessConstants> getBusinessConstants() {
        List<String> categoryList= Arrays.asList("REFERENCE_TYPE","PACKAGE_TYPE","CANCELLATION_REASON");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(referenceDomainILBUrl + getReferences);
        UriComponentsBuilder uri = uriComponentsBuilder.queryParam("category",categoryList).queryParam("langKey","fr");
        HttpHeaders headers = authUtil.generateAuthorizationHeader(referenceDomainCloudRunUrl);
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        long startOfReferenceCall = System.currentTimeMillis();
        ResponseEntity<BusinessConstants[]> responseEntity = restTemplate.exchange(uri.toUriString(), HttpMethod.GET,
                request, BusinessConstants[].class);
        log.info("Retrieved Reference Details in {} ms", System.currentTimeMillis() - startOfReferenceCall);
        List<BusinessConstants> references = new ArrayList<>();
        if (responseEntity.getBody() != null) {
            references = Arrays.asList(responseEntity.getBody());
        }
        return references;
    }


    @Cacheable(value = "harmonizedCodeCache", key = "#harmonizedCode")
    public List<HarmonizedCode> getHarmonizedCode(String harmonizedCode) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("harmonizedCode", harmonizedCode);
        queryParams.add("languageIdentifier", "FR");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(referenceDomainILBUrl + getHarmonizedCode);
        UriComponentsBuilder uri = uriComponentsBuilder.queryParams(queryParams);
        HttpHeaders headers = authUtil.generateAuthorizationHeader(referenceDomainCloudRunUrl);
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        long startOfReferenceCall = System.currentTimeMillis();
        ResponseEntity<HarmonizedCode[]> responseEntity = restTemplate.exchange(uri.toUriString(), HttpMethod.GET,
                request, HarmonizedCode[].class);
        log.info("Retrieved Reference Details in {} ms", System.currentTimeMillis() - startOfReferenceCall);
        List<HarmonizedCode> references = new ArrayList<>();
        if (responseEntity.getBody() != null) {
            references = Arrays.asList(responseEntity.getBody());
        }
        return references;
    }


}
