/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.onlinestore.model.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ideas2it.onlinestore.dto.CartDTO;
import com.ideas2it.onlinestore.dto.UserDTO;
import com.ideas2it.onlinestore.dto.WishlistDTO;
import com.ideas2it.onlinestore.model.Role;
import com.ideas2it.onlinestore.model.User;
import com.ideas2it.onlinestore.repository.UserRepository;
import com.ideas2it.onlinestore.service.AddressService;
import com.ideas2it.onlinestore.service.UserService;
import com.ideas2it.onlinestore.service.WishlistService;
import com.ideas2it.onlinestore.service.CartService;
import com.ideas2it.onlinestore.util.configuration.JwtFilter;
import com.ideas2it.onlinestore.util.customException.DataNotFoundException;
import com.ideas2it.onlinestore.util.customException.RedundantDataException;
import com.ideas2it.onlinestore.util.customException.ResourcePersistenceException;
import com.ideas2it.onlinestore.util.mapper.UserMapper;
import com.ideas2it.onlinestore.util.constants.Constant;

/**
 * Service Implementation class for User.
 *
 * @author - Gokul V
 * @version - 1.0
 * @since - 2022-12-17
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private WishlistService wishlistService;
    private AddressService addressService;
    private CartService cartService;
    private Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, WishlistService wishlistService, AddressService addressService, CartService cartService) {
        this.userRepository = userRepository;
        this.wishlistService = wishlistService;
        this.addressService = addressService;
        this.cartService = cartService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        System.out.println(userDTO.getEmail());
        if (isEmailIdExists(userDTO.getEmail()) || isMobileNumberExists(userDTO.getMobileNumber())) {
            throw new RedundantDataException(Constant.ERROR_MESSAGE_EMAIL_ID_PHONE_NUMBER_EXISTS);
        }
        userDTO.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        User a = UserMapper.convertUserDTO(userDTO);
        System.out.println(a.getEmail());
        User createdUser = userRepository.save(UserMapper.convertUserDTO(userDTO));
        UserDTO user = UserMapper.convertUserDAO(createdUser);

        if (0 < createdUser.getId()) {
            for (Role role: createdUser.getRoles()) {
                if (role.getType().equalsIgnoreCase("CUSTOMER")) {
                    WishlistDTO wishlist = WishlistDTO.builder().name(createdUser.getFirstName() + "Wishlist").build();
                    CartDTO cartDTO = CartDTO.builder().user(user).build();
                    wishlistService.createWishlist(wishlist, createdUser);
                    cartService.createCart(cartDTO);
                    logger.info(Constant.PROFILE_CREATED);
                    break;
                }
            }
        } else {
            throw new ResourcePersistenceException(Constant.PROFILE_NOT_CREATED);
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO getUserById(long id) {
        User user = userRepository.findById(id).orElse(null);

        if (null == user || user.isDeleted()) {
            throw new DataNotFoundException(Constant.USER_NOT_FOUND);
        }
        UserDTO userDTO = UserMapper.convertUserDAO(user);
        logger.info(Constant.DETAILS_FETCHED_SUCCESSFULLY);
        return userDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO updateUser(UserDTO user) {
        User existingUser = JwtFilter.getThreadLocal().get();

        if (null != existingUser) {
            user.setId(existingUser.getId());
            validByEmailId(user);
            validByPhoneNumber(user);
            user.setPassword(new BCryptPasswordEncoder().encode(existingUser.getPassword()));
            User userDAO = UserMapper.convertUserDTOToDAO(user);
            userDAO.setRoles(existingUser.getRoles());
            userDAO.setAddresses(existingUser.getAddresses());
            userDAO = userRepository.save(userDAO);
            UserDTO userDTO = UserMapper.convertUserDAO(userDAO);
            logger.info(Constant.UPDATED_SUCCESSFULLY);
            return userDTO;
        }
        throw new DataNotFoundException(Constant.USER_NOT_FOUND);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteUser(long id) {
        User user = JwtFilter.getThreadLocal().get();

        if (user.getId() == id) {
            user.setDeleted(true);
            userRepository.save(user);
            logger.info(Constant.DELETED_SUCCESSFULLY);
            return user.getFirstName() + Constant.DELETED_SUCCESSFULLY;
        }
        throw new DataNotFoundException(Constant.USER_NOT_FOUND);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();

        if (!users.isEmpty()) {
            for (User existingUser: users) {
                UserDTO user = UserMapper.convertUserDAOToDTO(existingUser);
                usersDTO.add(user);
            }
            logger.info(Constant.DETAILS_FETCHED_SUCCESSFULLY);
        }
        return usersDTO;
    }

    /**
     * <p>Validates if the given email id is already
     * exists or not. if the given email id is already
     * exists returns true otherwise false.</p>
     *
     * @param userEmailId    Email id to validate
     * @return boolean       true or false
     */
    private boolean isEmailIdExists(String userEmailId) {
        List<String> userEmailIds = userRepository.findAll().stream().map(User::getEmail).toList();

        for (String emailId : userEmailIds) {
            if ( userEmailId.equals(emailId) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>Validates if the given mobileNumber is already
     * exists or not. if the given mobileNumber
     * is already exists returns true otherwise false.</p>
     *
     * @param mobileNumber   mobileNumber to validate
     * @return boolean       true or false
     */
    private boolean isMobileNumberExists(String mobileNumber) {
        List<String> userMobileNumbers = userRepository.findAll().stream().map(User::getMobileNumber).toList();

        for (String number : userMobileNumbers) {
            if ( number.equals(mobileNumber) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>Validate email id before update the user.
     * if the given mail id is not match with existing
     * user email id throws RuntimeException.</p>
     *
     * @param user                    details of the user.
     * @throws RedundantDataException   occur when email id already exit
     */
    private void validByEmailId(UserDTO user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (null != existingUser) {
            if (!(existingUser.getId() == (user.getId()))) {
                throw new RedundantDataException(Constant.ERROR_MESSAGE_EMAIL_ID_EXISTS);
            }
        }
    }

    /**
     * <p>Validate phone number before update the user.
     * if the given mobile number is not match with existing
     * user mobile number throws RuntimeException.</p>
     *
     * @param user                    details of the user.
     * @throws RedundantDataException   occur when email id already exit
     */
    private void validByPhoneNumber(UserDTO user) {
        User existingUser = userRepository.findByMobileNumber(user.getMobileNumber());

        if (null != existingUser) {
            if (!(user.getId() == (existingUser.getId())) ) {
                throw new RedundantDataException(Constant.ERROR_MESSAGE_MOBILE_NUMBER_EXISTS);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);

        if (null == user) {
            logger.error(Constant.USER_NOT_FOUND);
            throw new DataNotFoundException(Constant.USER_NOT_FOUND);
        }
        return user;
    }
}
