/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.dto;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * It is a simple JavaBean domain object representing a Role.
 *
 * @author - Gokul V
 * @version - 1.0
 * @since - 2022-12-17
 */
@Getter
@Setter
@Builder
public class RoleDTO  {

    private long id;
    @NotBlank(message = "Role must be mentioned")
    private String type;
}
