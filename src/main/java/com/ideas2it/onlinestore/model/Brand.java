/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


/**
 * It is a simple JavaBean domain object representing a brand.
 *
 * @author Sangeetha Ilangovan
 * @version 1.0
 * @since 09.12.2022
 */
@Entity
@Getter
@SuperBuilder
@Table(name = "brand")
public class Brand extends BaseModel {

	@Column(name = "name", unique = true)
	private String name;

	public Brand() {

	}
}