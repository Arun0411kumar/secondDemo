/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The Category class have Category attributes
 * This class contain getter and setter method for Inventory attributes
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
public class Category extends BaseModel {

	@Column(nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@OneToMany(mappedBy = "category")
	private List<Category> subCategories;
}