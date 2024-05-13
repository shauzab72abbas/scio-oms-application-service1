package ca.cn.scio.application.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.cn.scio.application.exception.SystemException;
import ca.cn.scio.application.resource.Facility;
import ca.cn.scio.application.resource.Order;
import ca.cn.scio.application.resource.OrderingParty;

@Component
public class CommonUtil {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);
    private ResourceLoader resourceLoader;
    private  ObjectMapper mapper ;

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader , ObjectMapper mapper) {
        this.resourceLoader = resourceLoader;
        this.mapper = mapper ;
    }



    public String sanitizeOrder(Order order) {
        final String UNABLE_TO_PARSE = "UNABLE_TO_PARSE" ;
        try {
            Order deepCopyPartyObj = mapper
                    .readValue(mapper.writeValueAsString(order), Order.class);
            OrderingParty orderingParty = deepCopyPartyObj.getOrderingParty();
            if (orderingParty.getOrderPartyContact() != null) {
                orderingParty.getOrderPartyContact().setBusinessPhoneWithExtn(null);
                orderingParty.getOrderPartyContact().setEmail(null);
                orderingParty.getOrderPartyContact().setFirstName(null);
                orderingParty.getOrderPartyContact().setLastName(null);
                orderingParty.getOrderPartyContact().setMobilePhone(null);
            }
            return mapper.writeValueAsString(deepCopyPartyObj);
        }catch(Exception exception) {
            // Need to catch every exception which occurs here as we should not stop the flow
            //  even if this method fails .
            LOGGER.warn(exception.getMessage() ,exception.getCause());
            LOGGER.info("Failed inside sanitize method with order input payload {}" , order);
            return UNABLE_TO_PARSE ;
        }

    }

    public Object joltTransformation(Object inputJSON) {

        Resource resource = resourceLoader.getResource(VariableUtil.JOLT_DATA_TO_PARTYTOLOCATION);
        InputStream jsonAsStream = null;
        try {
            jsonAsStream = resource.getInputStream();
            List<Object> specs = JsonUtils.jsonToList(jsonAsStream);
            Chainr chainr = Chainr.fromSpec(specs);
            return chainr.transform(inputJSON);
        } catch (IOException exception) {
            LOGGER.error("Exception : {}, Message : {}", exception, exception.getMessage());
        }
        finally{
            if(jsonAsStream != null){
                try {
                    jsonAsStream.close();
                } catch (IOException e) {
                    LOGGER.error("Exception : {}, Message : {}", e, e.getMessage());
                }
            }

        }
        return null;


    }



}
