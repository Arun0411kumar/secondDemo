/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The stock class have stock attributes
 * This class contain getter and setter method for stock attributes
 *
 * @author arunkumar
 * @version 1.0
 * @since 16-12-2022
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Stock extends BaseModel {

	@Column(nullable = false)
	private String productName;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private Date dateOfManufacture;
	private Date dateOfExpire;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private User seller;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Product product;
}