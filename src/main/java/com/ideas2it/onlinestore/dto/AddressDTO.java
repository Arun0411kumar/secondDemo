/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;

import com.ideas2it.onlinestore.util.constants.Constant;
import lombok.Setter;

/**
 * It represents the address DTO.
 *
 * @author - Gokul V
 * @version - 1.0
 * @since - 2022-12-20
 */
@Getter
@Setter
@Builder
public class AddressDTO {

    private long id;
    @NotBlank(message = "Plot number must be mentioned")
    @Pattern(regexp = Constant.REGEX_FOR_DOOR_NUMBER, message = Constant.REGEX_FOR_INVALID_FORMAT)
    private String doorNumber;
    @NotBlank(message = "Street name must be mentioned")
    @Pattern(regexp = Constant.REGEX_FOR_TEXT, message = Constant.REGEX_FOR_INVALID_FORMAT)
    private String street;
    @NotBlank(message = "City Street name must be mentioned")
    @Pattern(regexp = Constant.REGEX_FOR_TEXT, message = Constant.REGEX_FOR_INVALID_FORMAT)
    private String city;
    @NotNull(message = "PinCode name must be mentioned")
    @Size(min = 6, max = 6)
    private int pinCode;
    @NotBlank(message = "State name must be mentioned")
    @Pattern(regexp = Constant.REGEX_FOR_TEXT, message = Constant.REGEX_FOR_INVALID_FORMAT)
    private String state;
    @NotBlank(message = "Address type name must be mentioned")
    @Pattern(regexp = Constant.REGEX_FOR_TEXT, message = Constant.REGEX_FOR_INVALID_FORMAT)
    private String type;
    @NotBlank(message = "Landmark name must be mentioned")
    @Pattern(regexp = Constant.REGEX_FOR_TEXT, message = Constant.REGEX_FOR_INVALID_FORMAT)
    private String landmark;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserDTO user;
}