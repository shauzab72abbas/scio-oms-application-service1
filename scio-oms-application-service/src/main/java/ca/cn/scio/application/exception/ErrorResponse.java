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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is a Error model class
 */
public class ErrorResponse {
	@JsonProperty("message")
	private String message;

	@JsonProperty("errors")
	private List<Error> errors;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
}
