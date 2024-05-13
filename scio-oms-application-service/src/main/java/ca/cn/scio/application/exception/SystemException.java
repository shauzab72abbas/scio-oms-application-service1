/*
 * Copyright (c) 1888-2020 CN, Inc.
 * 935 de La Gauchetière Street West, Montreal, Quebec, H3B 2M9, CANADA.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of CN
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with CN.
 */
package ca.cn.scio.application.exception;

/**
 * This is the System Exception class
 */
public class SystemException extends RuntimeException {
	public SystemException(){
		super() ;
	}

	public SystemException(String msg , Exception exception){
		super(msg , exception);
	}
}
