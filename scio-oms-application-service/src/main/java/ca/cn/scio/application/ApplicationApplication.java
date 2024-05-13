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
package ca.cn.scio.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * This code is aligned with the following positions from the Development Position Book (DPB):
 * <pre>
 *   Microservices Architecture:     https://kb.cn.ca/display/SPB/Microservice+Architecture+-+BE
 *   Swagger:                        https://kb.cn.ca/display/SPB/Swagger+Springfox+Library+-+BE
 *   SpringBoot:                     https://kb.cn.ca/display/SPB/Spring+Boot+Framework+-+BE
 *   S.O.L.I.D.:                     https://kb.cn.ca/display/SPB/S.O.L.I.D+details+-+BE
 *   Logging standard:               https://kb.cn.ca/display/SPB/Java+Logging+Standard+-+BE
 *   Java Secure Coding Standard:    https://kb.cn.ca/display/SPB/Java+Secure+Coding+Standard+-+BE
 *   Java Package and Class Naming:  https://kb.cn.ca/display/SPB/Java+Package+and+Class+Naming+Standard+-+BE
 *   Java Secure SDLC Consideration: https://kb.cn.ca/display/SPB/Java+Secure+SDLC+Consideration+-+BE
 *     Build tool:                   https://kb.cn.ca/display/SPB/Build+automation+tool+-+Apache+Maven+-+BE
 * </pre>
 */

@SpringBootApplication
public class ApplicationApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationApplication.class, args);
    }
}
