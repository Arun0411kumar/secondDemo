/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;

import com.ideas2it.onlinestore.util.constants.Constant;
import lombok.Setter;

/**
 * It represents the user DTO.
 *
 * @author - Gokul V
 * @version - 1.0
 * @since - 2022-12-20
 */
@Getter
@Setter
@Builder
public class UserDTO {

    private long id;
    @NotBlank(message = "First name should be mentioned")
    @Pattern(regexp = Constant.REGEX_FOR_TEXT, message = Constant.REGEX_FOR_INVALID_FORMAT)
    private String firstName;
    private String middleName;
    @NotBlank(message = "Last name should be mentioned")
    @Pattern(regexp = Constant.REGEX_FOR_TEXT, message = Constant.REGEX_FOR_INVALID_FORMAT)
    private String lastName;
    @NotBlank(message = "Email must be mentioned")
    @Email(regexp = Constant.REGEX_FOR_EMAIL_ID, message = "Invalid format e.g. vel@abc.com")
    private String email;
    @NotBlank(message = "Password must be mentioned")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotBlank(message = "Phone number must be mentioned")
    @Pattern(regexp = Constant.REGEX_FOR_PHONE_NUMBER, message = "Invalid format - Start with 6 - 9 e.g. 9**********")
    private String mobileNumber;
    private List<RoleDTO> roles;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<AddressDTO> addresses;
}
