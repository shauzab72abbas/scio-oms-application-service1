package ca.cn.scio.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import ca.cn.scio.application.resource.BusinessConstants;
import ca.cn.scio.application.resource.EquipmentTypeOwner;
import ca.cn.scio.application.resource.HarmonizedCode;
import ca.cn.scio.application.service.impl.ReferenceApiServiceImpl;
import ca.cn.scio.application.util.AuthUtil;

@ExtendWith(MockitoExtension.class)
class ReferenceApiServiceImplTest {
    @Mock
    AuthUtil authUtil;
    @Mock
    RestTemplate restTemplate;
    @Spy
    @InjectMocks
    ReferenceApiServiceImpl referenceApiService;

    @Test
    void getEquipmentTypeOwnerTest(){

        ReflectionTestUtils.setField(referenceApiService, "referenceDomainILBUrl", "customertest");
        ReflectionTestUtils.setField(referenceApiService, "getEquipmentType", "customertest");
        ReflectionTestUtils.setField(referenceApiService, "referenceDomainCloudRunUrl", "customertest");
        Mockito.doReturn(new HttpHeaders()).when(authUtil).generateAuthorizationHeader("customertest");
        EquipmentTypeOwner[] equipmentTypeOwners = new EquipmentTypeOwner[1];
        ResponseEntity<EquipmentTypeOwner[]> responseEntity = new ResponseEntity<>(equipmentTypeOwners, HttpStatus.OK);
        Mockito.doReturn(responseEntity).when(restTemplate).exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET),Mockito.any(),Mockito.eq(EquipmentTypeOwner[].class));
        Assertions.assertNotNull(referenceApiService.getEquipmentTypeOwner());
    }

    @Test
    void getBusinessConstantsTest(){
        ReflectionTestUtils.setField(referenceApiService, "referenceDomainILBUrl", "customertest");
        ReflectionTestUtils.setField(referenceApiService, "getReferences", "customertest");
        ReflectionTestUtils.setField(referenceApiService, "referenceDomainCloudRunUrl", "customertest");
        Mockito.doReturn(new HttpHeaders()).when(authUtil).generateAuthorizationHeader("customertest");
        BusinessConstants[] businessConstants = new BusinessConstants[1];
        ResponseEntity<BusinessConstants[]> responseEntity = new ResponseEntity<>(businessConstants,HttpStatus.OK);
        Mockito.doReturn(responseEntity).when(restTemplate).exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET),Mockito.any(),Mockito.eq(BusinessConstants[].class));
        Assertions.assertNotNull(referenceApiService.getBusinessConstants());
    }
    @Test
    void getHarmonizedCodeTest(){
        ReflectionTestUtils.setField(referenceApiService, "referenceDomainILBUrl", "customertest");
        ReflectionTestUtils.setField(referenceApiService, "getHarmonizedCode", "customertest");
        ReflectionTestUtils.setField(referenceApiService, "referenceDomainCloudRunUrl", "customertest");
        Mockito.doReturn(new HttpHeaders()).when(authUtil).generateAuthorizationHeader("customertest");
        HarmonizedCode[] businessConstants = new HarmonizedCode[1];
        ResponseEntity<HarmonizedCode[]> responseEntity = new ResponseEntity<>(businessConstants,HttpStatus.OK);
        Mockito.doReturn(responseEntity).when(restTemplate).exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET),Mockito.any(),Mockito.eq(HarmonizedCode[].class));
        Assertions.assertNotNull(referenceApiService.getHarmonizedCode("1234"));
    }


}
