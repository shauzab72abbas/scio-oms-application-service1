package ca.cn.scio.application.service;

import java.util.ArrayList;
import java.util.List;

import ca.cn.scio.application.resource.*;
import ca.cn.scio.application.service.impl.BreApiServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import ca.cn.scio.application.service.impl.MultiLanguageServiceImpl;
import ca.cn.scio.application.service.impl.ReferenceApiServiceImpl;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MultiLanguageServiceImplTest {

    @Mock
    CacheManager cacheManager;
    @Mock
    ReferenceApiServiceImpl referenceApiService;
    @Mock
    BreApiServiceImpl breApiService;
    @InjectMocks
    @Spy
    MultiLanguageServiceImpl multiLanguageService;

    private List<BusinessRuleError> orderError = new ArrayList<>();
    private BusinessRuleError error = new BusinessRuleError();

    @BeforeEach
    void setup(){
        error.setRuleConfigId("1234");
        error.setErrorType("error");
        orderError.add(error);
    }

    @Test
    void convertHSCToFrenchTest() {
        HSCode hsCode = new HSCode();
        hsCode.setHarmonizedCode("1234");
        doReturn("Hsc French").when(multiLanguageService).getFrenchDescriptionForHarmonizedCode("1234");
        multiLanguageService.convertHscToFrench(hsCode);
        Assertions.assertNotNull(hsCode.getHarmonizedCodeDesc());
    }

    @Test
    void convertEqpTypeForShipment() {
        OrderShipmentRequest orderShipmentRequest = new OrderShipmentRequest();
        orderShipmentRequest.setOrderedEquipmentOwner(EquipmentOwnerCode.E);
        orderShipmentRequest.setOrderedEquipmentType(EquipmentTypeCode.JC1);
        doReturn("EqpType French").when(multiLanguageService).getFrenchDescriptionForEqpCode(orderShipmentRequest.getOrderedEquipmentOwner().toString(), orderShipmentRequest.getOrderedEquipmentType());
        multiLanguageService.convertEqpTypeToFrenchShipment(orderShipmentRequest);
        Assertions.assertNotNull(orderShipmentRequest.getOrderedEquipmentTypeDesc());
    }

    @Test
    void convertCancellationToFrench() {
        Cancellation cancellation = new Cancellation();
        cancellation.setCancellationReasonCode("OTH");
        doReturn("Business Constant French").when(multiLanguageService).getFrenchDescriptionForBusinessConstant(cancellation.getCancellationReasonCode());
        multiLanguageService.convertCancellationReasonToFrench(cancellation);
        Assertions.assertNotNull(cancellation.getCancellationReasonDesc());
    }

    @Test
    void convertReferenceToFrench() {
        Reference reference = new Reference();
        reference.setReferenceType("BO");
        List<Reference> referenceList = new ArrayList<>();
        referenceList.add(reference);
        doReturn("Reference in French").when(multiLanguageService).getFrenchDescriptionForBusinessConstant(reference.getReferenceType());
        multiLanguageService.convertReferenceTypeToFrench(referenceList);
        Assertions.assertNotNull(reference.getReferenceValue());
    }

    @Test
    void convertEqpType() {
        EmptyDelivery emptyDelivery = new EmptyDelivery();
        emptyDelivery.setEquipmentOwner(EquipmentOwnerCode.E);
        emptyDelivery.setEquipmentType(EquipmentTypeCode.JC1);
        emptyDelivery.setAllocatedEquipmentOwner(EquipmentOwnerCode.E);
        emptyDelivery.setAllocatedEquipmentType(EquipmentTypeCode.JC1);
        doReturn("EqpType French").when(multiLanguageService).getFrenchDescriptionForEqpCode(emptyDelivery.getEquipmentOwner().toString(), emptyDelivery.getEquipmentType());
        multiLanguageService.convertEquipmentTypeToFrench(emptyDelivery);
        Assertions.assertNotNull(emptyDelivery.getEquipmentTypeDesc());
    }

    @Test
    void getFrenchDescriptionForEqpCodeTest() {
        ConcurrentMapCache equipmentTypeOwnerCache = new ConcurrentMapCache("equipmentTypeOwnerCache");
        EquipmentTypeOwner equipmentTypeOwner = new EquipmentTypeOwner();
        List<EquipmentCode> equipmentCodeList = new ArrayList<>();
        EquipmentCode equipmentCode = new EquipmentCode();
        equipmentCode.setEquipmentCode(EquipmentCode.EquipmentCodeEnum.JC1);
        equipmentCode.setFrenchDescription("French text");
        equipmentCodeList.add(equipmentCode);
        equipmentTypeOwner.setEquipmentCode(equipmentCodeList);
        equipmentTypeOwnerCache.put("C", equipmentTypeOwner);
        doReturn(equipmentTypeOwnerCache).when(cacheManager).getCache("equipmentTypeOwnerCache");
        Assertions.assertNotNull(multiLanguageService.getFrenchDescriptionForEqpCode("C", EquipmentTypeCode.JC1));
    }
    @Test
    void getFrenchDescriptionForEqpCodeTestFailure() {
        ConcurrentMapCache equipmentTypeOwnerCache = new ConcurrentMapCache("equipmentTypeOwnerCache");
        doReturn(equipmentTypeOwnerCache).when(cacheManager).getCache("equipmentTypeOwnerCache");
        Assertions.assertNotNull(multiLanguageService.getFrenchDescriptionForEqpCode("C", EquipmentTypeCode.JC1));
    }
    @Test
    void getFrenchDescriptionForBusinessConstantTest() {
        ConcurrentMapCache equipmentTypeOwnerCache = new ConcurrentMapCache("businessConstantCache");
        BusinessConstants businessConstants= new BusinessConstants();
        businessConstants.setCode("OTH");
        businessConstants.setDescription("French text");
        doReturn(equipmentTypeOwnerCache).when(cacheManager).getCache("businessConstantCache");
        Assertions.assertNotNull(multiLanguageService.getFrenchDescriptionForBusinessConstant("OTH"));
    }
    @Test
    void getFrenchDescriptionForBusinessConstantTestFailure() {
        ConcurrentMapCache equipmentTypeOwnerCache = new ConcurrentMapCache("businessConstantCache");
        doReturn(equipmentTypeOwnerCache).when(cacheManager).getCache("businessConstantCache");
        Assertions.assertNotNull(multiLanguageService.getFrenchDescriptionForBusinessConstant("OTH"));
    }
    @Test
    void getFrenchDescriptionForHarmonizedCodeTest(){
        ConcurrentMapCache equipmentTypeOwnerCache = new ConcurrentMapCache("harmonizedCodeCache");
        HarmonizedCode harmonizedCode= new HarmonizedCode();
        harmonizedCode.setHarmonizedCode("1234");
        harmonizedCode.setHarmonizedDescription("French text");
        equipmentTypeOwnerCache.put("1234",harmonizedCode);
        doReturn(equipmentTypeOwnerCache).when(cacheManager).getCache("harmonizedCodeCache");
        Assertions.assertNotNull(multiLanguageService.getFrenchDescriptionForHarmonizedCode("1234"));

    }
    @Test
    void getFrenchDescriptionForHarmonizedCodeTestFailure(){
        ConcurrentMapCache equipmentTypeOwnerCache = new ConcurrentMapCache("harmonizedCodeCache");
        List<HarmonizedCode> harmonizedCodesList = new ArrayList<>();
        HarmonizedCode harmonizedCode= new HarmonizedCode();
        harmonizedCode.setHarmonizedCode("1234");
        harmonizedCode.setHarmonizedDescription("French text");
        harmonizedCode.setHarmonizedCodeEnhancedDescription("French Text");
        harmonizedCodesList.add(harmonizedCode);
        doReturn(equipmentTypeOwnerCache).when(cacheManager).getCache("harmonizedCodeCache");
        doReturn(harmonizedCodesList).when(referenceApiService).getHarmonizedCode("1234");
        Assertions.assertNotNull(multiLanguageService.getFrenchDescriptionForHarmonizedCode("1234"));
    }

    @Test
    void convertEmptyDeliveryToFrenchTest(){
        List<EmptyDelivery> emptyDeliveryList = new ArrayList<>();
        EmptyDelivery emptyDelivery = new EmptyDelivery();

        List<Reference> referenceList = new ArrayList<>();
        Reference ref = new Reference();
        referenceList.add(ref);

        emptyDelivery.setOrderError(orderError);
        emptyDelivery.setOrderEquipmentReference(referenceList);
        emptyDelivery.setCancellation(new Cancellation());
        emptyDeliveryList.add(emptyDelivery);


        lenient().doNothing().when(multiLanguageService).convertEquipmentTypeToFrench(emptyDelivery);
        lenient().doNothing().when(multiLanguageService).emptyDeliveryErrorTypeChange(emptyDelivery);
        lenient().doNothing().when(multiLanguageService).convertReferenceTypeToFrench(referenceList);
        lenient().doNothing().when(multiLanguageService).convertCancellationReasonToFrench(emptyDelivery.getCancellation());

        multiLanguageService.convertEmptyDeliveryToFrench(emptyDeliveryList);
        Assertions.assertNotNull(emptyDelivery.getOrderError());

    }

    @Test
    void emptyDeliveryErrorTypeChangeTest(){
        EmptyDelivery emptyDelivery = new EmptyDelivery();

        emptyDelivery.setOrderError(orderError);
        doReturn("error").when(multiLanguageService).getFrenchErrorMessage("1234", "error");

        multiLanguageService.emptyDeliveryErrorTypeChange(emptyDelivery);
        Assertions.assertNotNull(emptyDelivery.getOrderError());
    }

    @Test
    void orderShipmentErrorTypeChangeTest(){
        OrderShipmentRequest orderShipmentRequest = new OrderShipmentRequest();

        orderShipmentRequest.setOrderError(orderError);
        doReturn("error").when(multiLanguageService).getFrenchErrorMessage("1234", "error");

        multiLanguageService.orderShipmentErrorTypeChange(orderShipmentRequest);
        Assertions.assertNotNull(orderShipmentRequest.getOrderError());
    }

    @Test
    void convertShipmentCommodityToFrenchTest(){
        List<ShipmentCommodity> shipmentCommodityList = new ArrayList<>();
        ShipmentCommodity shipmentCommodity = new ShipmentCommodity();

        List<HSCode> hsCodes = new ArrayList<>();
        HSCode hsCode = new HSCode();

        hsCodes.add(hsCode);

        shipmentCommodity.setHarmonizedSystemCodes(hsCodes);
        shipmentCommodityList.add(shipmentCommodity);

        lenient().doNothing().when(multiLanguageService).convertHscToFrench(hsCode);
        multiLanguageService.convertShipmentCommodityToFrench(shipmentCommodityList);

        Assertions.assertNotNull(shipmentCommodityList);
    }

    @Test
    void convertTransportStopsToFrench(){
        OrderShipmentRequest orderShipmentRequest = new OrderShipmentRequest();

        List<Stop> stops = new ArrayList<>();

        Stop stop = new Stop();

        stop.setOrderError(orderError);
        stops.add(stop);

        orderShipmentRequest.setOrderShipmentTransportStops(stops);
        lenient().doReturn("error").when(multiLanguageService).getFrenchErrorMessage("1234", "error");

        multiLanguageService.convertTransportStopsToFrench(orderShipmentRequest);
        Assertions.assertNotNull(orderShipmentRequest.getOrderShipmentTransportStops());
    }

    @Test
    void returnFrenchTranslatedOrderTest(){
        Order order = new Order();
        CustomerOrder customerOrder = new CustomerOrder();
        List<EmptyDelivery> emptyDeliveryList = new ArrayList<>();
        List<OrderShipmentRequest> orderShipmentRequests = new ArrayList<>();

        customerOrder.setOrderError(orderError);
        customerOrder.setOrderEquipmentRequest(emptyDeliveryList);
        customerOrder.setOrderShipmentRequest(orderShipmentRequests);
        order.setCustomerOrder(customerOrder);
        order.setOrderError(orderError);

        lenient().doReturn("error").when(multiLanguageService).getFrenchErrorMessage("1234", "error");
        lenient().doNothing().when(multiLanguageService).convertEmptyDeliveryToFrench(emptyDeliveryList);
        lenient().doNothing().when(multiLanguageService).convertOrderShipmentRequestToFrench(orderShipmentRequests);

        Order returned = multiLanguageService.returnFrenchTranslatedOrder(order);
        Assertions.assertNotNull(returned);

    }

    @Test
    void convertOrderShipmentRequestToFrench(){
        List<OrderShipmentRequest> orderShipmentRequests = new ArrayList<>();
        OrderShipmentRequest orderShipmentRequest = new OrderShipmentRequest();

        List<Reference> referenceList = new ArrayList<>();
        Reference ref = new Reference();
        referenceList.add(ref);

        List<ShipmentCommodity> commodityList = new ArrayList<>();
        ShipmentCommodity commodity = new ShipmentCommodity();
        commodityList.add(commodity);

        List<Stop> stops = new ArrayList<>();
        Stop stop = new Stop();
        stops.add(stop);

        Cancellation cancellation = new Cancellation();
        orderShipmentRequest.setCancellation(cancellation);

        orderShipmentRequest.setOrderError(orderError);
        orderShipmentRequest.setOrderShipmentReferences(referenceList);
        orderShipmentRequest.setOrderShipmentCommodities(commodityList);
        orderShipmentRequest.setOrderShipmentTransportStops(stops);

        orderShipmentRequests.add(orderShipmentRequest);

        lenient().doReturn("error").when(multiLanguageService).getFrenchErrorMessage("1234", "error");
        lenient().doNothing().when(multiLanguageService).convertTransportStopsToFrench(orderShipmentRequest);
        lenient().doNothing().when(multiLanguageService).convertCancellationReasonToFrench(cancellation);
        lenient().doNothing().when(multiLanguageService).convertShipmentCommodityToFrench(commodityList);
        lenient().doNothing().when(multiLanguageService).convertEqpTypeToFrenchShipment(orderShipmentRequest);

        multiLanguageService.convertOrderShipmentRequestToFrench(orderShipmentRequests);
        Assertions.assertNotNull(orderShipmentRequest);
    }

    @Test
    void getFrenchErrorMessageTest(){

        ConcurrentMapCache breCache = new ConcurrentMapCache("breCache");

        Rule rule = new Rule();
        rule.setRuleConfigId("1234");
        rule.setErrorId("error");
        breCache.put("1234",rule);

        lenient().doReturn(breCache).when(cacheManager).getCache("breCache");
        lenient().doReturn("error").when(breApiService).getErrorMessageByLanguage(rule, ErrorMessageLanguage.LanguageNameEnum.FRENCH);

        String returnedResult = multiLanguageService.getFrenchErrorMessage("1234", "error");
        Assertions.assertNotNull(returnedResult);

    }

    @Test
    void getFrenchErrorMessageTest1(){

        ConcurrentMapCache breCache = new ConcurrentMapCache("breCache");

        Rule rule = new Rule();
        rule.setRuleConfigId("1234");
        rule.setErrorId("error with <config_param>");
        breCache.put("1234",rule);

        lenient().doReturn(breCache).when(cacheManager).getCache("breCache");
        lenient().doReturn("error").when(breApiService).getErrorMessageByLanguage(rule, ErrorMessageLanguage.LanguageNameEnum.FRENCH);

        String returnedResult = multiLanguageService.getFrenchErrorMessage("1234", "error with <config_param>");
        Assertions.assertNotNull(returnedResult);

    }

}
