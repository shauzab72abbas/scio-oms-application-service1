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
/**
 * Business layer of the application. Used to hold and apply business rules and requirements.
 * Abstract the data layer from the web access layer (if any). May also orchestrate calls to other
 * objects of the service layer to fulfill a business transaction (data validation, data mapping,
 * etc.). Depending on the needs and application scope, can be further be split in sub modules such
 * as "validation", "rules", etc. if necessary.<br/> DPS position: https://kb.cn.ca/x/GhEMAw
 */
package ca.cn.scio.application.service;
