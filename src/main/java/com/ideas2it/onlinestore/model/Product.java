/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * It is a simple JavaBean domain object representing a product.
 *
 * @author Sangeetha Ilangovan
 * @version 1.0
 * @since 09.12.2022
 */
@Entity
@Getter
@SuperBuilder
@Table(name = "product")
public class Product extends BaseModel {

	@Transient
	private Integer quantity;
	@Column(name = "price")
	private Double price;
	@Transient
	private Date dateOfExpire;
	@Transient
	private Date dateOfManufacture;
	@Column(name = "description")
	private String description;
	@Column(name = "name")
	private String name;
	@ManyToOne
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "brand_id")
	private Brand brand;
	@ManyToOne(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "category_id")
	private Category category;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_category_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Category subCategory;
	@ManyToMany(mappedBy = "products")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Wishlist> wishlists;

	public Product() {

	}
}