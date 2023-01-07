/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class Message {
	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;

	public Message(int statusCode, Date timestamp, String message) {
		super();
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
	}
	
	public Message(int statusCode, Date timestamp, String message, String description) {
		super();
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}
}
