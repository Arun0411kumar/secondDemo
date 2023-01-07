/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ideas2it.onlinestore.dto.UserDTO;

/**
 * Interface for UserService
 *
 * @author - Gokul V
 * @version - 1.0
 * @since - 2022-12-17
 */
public interface UserService extends UserDetailsService {

    /**
     * <p>User is created using given details,before create a user the
     * given email id and mobileNumber is validated whether
     * the given mail and mobileNumber is already exists or not.
     * if the given email id and mobileNumber is already exists throws
     * RuntimeException otherwise returns user object.</p>
     *
     * @param user                     details of the user.
     * @return String                  status message.
     */
    UserDTO createUser(UserDTO user);

    /**
     * <p>Get the user details using the given user id.
     * if the given user id is not exists throws RuntimeException
     * otherwise returns user details object.</p>
     *
     * @param id                        id of the user.
     * @return UserDTO                  details of the user.
     */
    UserDTO getUserById(long id);

    /**
     * <p>Update the user details using given details.
     * if the given email id and mobileNumber is already exists then
     * match with existing user and given user to find the two user is same.
     * if same update the user details and returns user details object
     * otherwise throws RuntimeException.</p>
     *
     * @param user                     details of the user.
     * @return UserDTO                 updated details of the user.
     */
    UserDTO updateUser(UserDTO user);

    /**
     * <p>Delete the user using given user id.
     * if the given user id is not exist throws RuntimeException
     * otherwise delete the user.</p>
     *
     * @param id         id of the user.
     * @return String    custom status message.
     */
    String deleteUser(long id);

    /**
     * <p>Get all registered user details.
     * if the users are not exists throws RuntimeException
     * otherwise returns user details.</p>
     *
     * @return List<User>              details of the registered user.
     */
    List<UserDTO> getAllUser() ;
}
