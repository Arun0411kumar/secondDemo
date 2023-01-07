/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.util.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.onlinestore.dto.AddressDTO;
import com.ideas2it.onlinestore.dto.ProductDTO;
import com.ideas2it.onlinestore.dto.RoleDTO;
import com.ideas2it.onlinestore.dto.UserDTO;
import com.ideas2it.onlinestore.dto.WishlistDTO;
import com.ideas2it.onlinestore.model.Address;
import com.ideas2it.onlinestore.model.Product;
import com.ideas2it.onlinestore.model.Role;
import com.ideas2it.onlinestore.model.User;
import com.ideas2it.onlinestore.model.Wishlist;

/**
 * Mapper class of the user.
 *
 * @author - Gokul V
 * @version - 1.0
 * @since - 2022-12-21
 */
public class UserMapper {

    /**
     * Converts the user DTO object into user DAO objects
     *
     * @param userDTO   details of the user.
     * @return User     converted user details.
     */
    public static User convertUserDTOToDAO(UserDTO userDTO) {
        System.out.println(userDTO.getEmail());
        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .middleName(userDTO.getMiddleName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .mobileNumber(userDTO.getMobileNumber()).build();
    }

    /**
     * Converts the user DAO object into user DTO object.
     *
     * @param user       details of the user DAO.
     * @return UserDTO   details of the user DTO.
     */
    public static UserDTO convertUserDAOToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber()).build();
    }

    /**
     * Converts the address DTO object into address DAO object.
     *
     * @param addressDTO   details of the address DTO.
     * @return Address     details of the address DAO.
     */
    public static Address convertAddressDTOToDAO(AddressDTO addressDTO) {
        return Address.builder()
                .id(addressDTO.getId())
                .doorNumber(addressDTO.getDoorNumber())
                .street(addressDTO.getStreet())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .pinCode(addressDTO.getPinCode())
                .type(addressDTO.getType())
                .landmark(addressDTO.getLandmark()).build();
    }

    /**
     * Converts the address DAO object into address DTO object.
     *
     * @param address         details of the address DAO.
     * @return AddressDTO     details of the address DTO.
     */
    public static AddressDTO convertAddressDAOToDTO(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .doorNumber(address.getDoorNumber())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .pinCode(address.getPinCode())
                .type(address.getType())
                .landmark(address.getLandmark()).build();
    }

    /**
     * Converts the role DTO object into role DAO object.
     *
     * @param roleDTO    details of the role DTO.
     * @return Role      details of the role DAO.
     */
    public static Role convertRoleDTOToDAO(RoleDTO roleDTO) {
        return Role.builder()
                .id(roleDTO.getId())
                .type(roleDTO.getType()).build();
    }

    /**
     * Converts the role DAO object into role DTO object.
     *
     * @param role        details of the role DAO.
     * @return RoleDTO    details of the role DTO.
     */
    public static RoleDTO convertRoleDAOToDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .type(role.getType()).build();
    }

    /**
     * Converts the wishlist DAO object into wishlist DTO object
     * also converts the product DAO object into product DTO object.
     *
     * @param wishlist        details of the wishlist DAO.
     * @return WishlistDTO    details of the wishlist DTO.
     */
    public static WishlistDTO convertWishlistDAO(Wishlist wishlist) {
        List<ProductDTO> products = new ArrayList<>();

        if (null != wishlist.getProducts()) {
            ProductMapper productMapper = new ProductMapper();

            for (Product product: wishlist.getProducts()) {
                products.add(productMapper.convertProductToProductDTO(product));
            }
        }
        return WishlistDTO.builder()
                .id(wishlist.getId())
                .name(wishlist.getName())
                .products(products).build();
    }

    /**
     * Converts the wishlist DTO object into wishlist DAO object.
     *
     * @param wishlistDTO    details of the wishlist DAO.
     * @return Wishlist      details of the wishlist DTO.
     */
    public static Wishlist convertWishlistDTO(WishlistDTO wishlistDTO) {
        return Wishlist.builder()
                .id(wishlistDTO.getId())
                .name(wishlistDTO.getName()).build();
    }

    /**
     * Converts the user DTO objects into user DAO objects.
     * also converts the address DTO objects into address DAO objects
     * and converts the role DTO objects into role DAO object.
     *
     * @param userDTO    details of the user DTO.
     * @return User      details of the user DAO.
     */
    public static User convertUserDTO(UserDTO userDTO) {
        User user = convertUserDTOToDAO(userDTO);

        if ((null != userDTO.getAddresses())) {
            List<Address> addresses = new ArrayList<>();
            for (AddressDTO addressDTO: userDTO.getAddresses()) {
                addresses.add(convertAddressDTOToDAO(addressDTO));
            }
            user = User.builder().addresses(addresses).build();
        }

        if (!(userDTO.getRoles().isEmpty())) {
            List<Role> roles = new ArrayList<>();
            for (RoleDTO role: userDTO.getRoles()) {
                roles.add(convertRoleDTOToDAO(role));
            }
            user = User.builder().roles(roles).build();
        }
        return user;
    }

    /**
     * Converts the user DAO objects into user DTO objects.
     * also converts the address DAO objects into address DTO objects
     * and converts the role DAO objects into role DTO object.
     *
     * @param user        details of the user DTO.
     * @return UserDTO    details of the user DAO.
     */
    public static UserDTO convertUserDAO(User user) {
        UserDTO userDTO = convertUserDAOToDTO(user);

        if ((null != user.getAddresses())) {
            List<AddressDTO> addresses = new ArrayList<>();
            for (Address address: user.getAddresses()) {
                addresses.add(convertAddressDAOToDTO(address));
            }
            userDTO = UserDTO.builder().addresses(addresses).build();
        }

        if (!(user.getRoles().isEmpty())) {
            List<RoleDTO> roles = new ArrayList<>();
            for (Role role: user.getRoles()) {
                roles.add(convertRoleDAOToDTO(role));
            }
            userDTO = UserDTO.builder().roles(roles).build();
        }
        return userDTO;
    }
}
