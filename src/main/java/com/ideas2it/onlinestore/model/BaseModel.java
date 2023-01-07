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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * It is a simple JavaBean domain object representing BaseModel.
 *
 * @author - Gokul V
 * @version - 1.0
 * @since - 2022-12-17
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "created_at", updatable=false, nullable = false)
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;
    @Column(name = "deleted",
            columnDefinition = "tinyint default 0")
    private boolean deleted;
}
