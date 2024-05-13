package ca.cn.scio.application.config;



import ca.cn.scio.application.resource.Rule;
import ca.cn.scio.application.service.impl.BreApiServiceImpl;
import ca.cn.scio.application.service.impl.ReferenceApiServiceImpl;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.cn.scio.application.resource.BusinessConstants;
import ca.cn.scio.application.resource.EquipmentTypeOwner;

@Configuration
@EnableCaching
public class CacheConfig {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);
    private final ReferenceApiServiceImpl referenceApiService;
    private final BreApiServiceImpl breApiService;

    @Autowired
    public CacheConfig(ReferenceApiServiceImpl referenceApiService, BreApiServiceImpl breApiService) {
        this.referenceApiService = referenceApiService;
        this.breApiService = breApiService;
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        LOGGER.info("CacheManager: Reference api call for equipmentTypeOwner started");
        List<EquipmentTypeOwner> equipmentTypeOwnerList = referenceApiService.getEquipmentTypeOwner();
        LOGGER.info("CacheManager: Reference api call for equipmentTypeOwner ended");
        LOGGER.info("CacheManager: Reference api call for businessConstants started");
        List<BusinessConstants> businessConstantsList = referenceApiService.getBusinessConstants();
        LOGGER.info("CacheManager: Reference api call for businessConstants ended");
        LOGGER.info("CacheManager: Bre api call for rules started");
        List<Rule> ruleList = breApiService.getRules();
        LOGGER.info("CacheManager: Bre api call for rules Success full with size : {}",ruleList.size() );
        ConcurrentMapCache equipmentTypeOwnerCache = new ConcurrentMapCache("equipmentTypeOwnerCache");
        ConcurrentMapCache businessConstantCache = new ConcurrentMapCache("businessConstantCache");
        ConcurrentMapCache harmonizedCodeCache = new ConcurrentMapCache("harmonizedCodeCache");
        ConcurrentMapCache breCache = new ConcurrentMapCache("breCache");
        for (EquipmentTypeOwner equipmentTypeOwner : equipmentTypeOwnerList) {
            equipmentTypeOwnerCache.put(equipmentTypeOwner.getEquipmentTypeOwnerCode().toString(), equipmentTypeOwner);
        }
        for (BusinessConstants businessConstants : businessConstantsList) {

            if (businessConstants.getCategory().getValue().equals("PACKAGE_TYPE")){
                businessConstantCache.put(businessConstants.getValue(),businessConstants);
            }

            businessConstantCache.put(businessConstants.getCode(), businessConstants);
        }
        for(Rule rule : ruleList){
            breCache.put(rule.getRuleConfigId(),rule);
        }
        Collection<Cache> caches = Arrays.asList(equipmentTypeOwnerCache, businessConstantCache, harmonizedCodeCache, breCache);
        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }


}
