/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import com.ideas2it.onlinestore.util.constants.OrderStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
/**
 * This class represents the model we are implementing
 * for the Order. In addition to its own fields it also
 * extends a base class that contains fields which are
 * common across all models.
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Where(clause = "deleted = '0'")
@Table(name = "`order`")
public class Order extends BaseModel{

	@Column(columnDefinition = "datetime default current_timestamp")
	private LocalDate date;

	@Column(columnDefinition = "double not null")
	private double amount;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", nullable = false)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<OrderProduct> orderProducts;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Address address;

}