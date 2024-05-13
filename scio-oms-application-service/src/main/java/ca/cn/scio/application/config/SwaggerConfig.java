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
package ca.cn.scio.application.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

/**
 * This code is aligned with the following positions from the Development Position Book (DPB):
 * <pre>
 *   Swagger: https://kb.cn.ca/display/SPB/Swagger+Springfox+Library+-+BE
 * </pre>
 */
@ComponentScan(basePackages = "ca.cn")
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("public-apis").pathsToMatch("/**").pathsToExclude("/actuator/**").build();
    }

    @Bean
    public GroupedOpenApi actuatorApi() {
        return GroupedOpenApi.builder().group("actuators").pathsToMatch("/actuators/**").build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI();
    }

}
