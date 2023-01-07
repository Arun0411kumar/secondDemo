/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * This class represents the model we are implementing
 * for the Cart. In addition to its own fields it also
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
public class Cart extends BaseModel {

	@Transient
	private double cartTotal;
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cart_id", nullable = false)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CartProduct> cartProducts;
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

}