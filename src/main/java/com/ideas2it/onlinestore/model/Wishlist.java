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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * It is a simple JavaBean domain object representing a Wishlist.
 *
 * @author - Gokul V
 * @version - 1.0
 * @since - 2022-12-17
 */
@Entity
@Getter
@Setter
@SuperBuilder
public class Wishlist extends BaseModel {
    @Column(columnDefinition = "varchar(255) not null")
    private String name;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "wishlist_product",
            joinColumns = { @JoinColumn(name = "wishlist_id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id")})
    private List<Product> products;

    public Wishlist() {

    }
}
