/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.onlinestore.dto.AddressDTO;
import com.ideas2it.onlinestore.model.Address;
import com.ideas2it.onlinestore.model.User;
import com.ideas2it.onlinestore.repository.AddressRepository;
import com.ideas2it.onlinestore.service.AddressService;
import com.ideas2it.onlinestore.util.constants.Constant;
import com.ideas2it.onlinestore.util.configuration.JwtFilter;
import com.ideas2it.onlinestore.util.customException.DataNotFoundException;
import com.ideas2it.onlinestore.util.customException.ResourcePersistenceException;
import com.ideas2it.onlinestore.util.mapper.UserMapper;

/**
 * Service Implementation class for Address.
 *
 * @author - Gokul V
 * @version - 1.0
 * @since - 2022-12-17
 */
@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private Logger logger = LogManager.getLogger(AddressServiceImpl.class);

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addAddress(AddressDTO addressDTO) {
        User user = JwtFilter.getThreadLocal().get();
        Address address = UserMapper.convertAddressDTOToDAO(addressDTO);
        address.setUser(user);
        address = addressRepository.save(address);

        if (!(0 < address.getId())) {
            throw new ResourcePersistenceException(Constant.ERROR_MESSAGE_ADDRESS_NOT_ADDED);
        }
        logger.info(Constant.ADDRESS_ADDED_SUCCESSFULLY);
        return user.getFirstName() + Constant.ADDRESS_ADDED_SUCCESSFULLY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteAddress(long id){
        User user = JwtFilter.getThreadLocal().get();
        List<Address> addresses = user.getAddresses();

        for (Address location : addresses) {
            if (id == location.getId()) {
                location.setDeleted(true);
                addressRepository.save(location);
                logger.info(Constant.ADDRESS_DELETED_SUCCESSFULLY);
                return user.getFirstName() + Constant.ADDRESS_DELETED_SUCCESSFULLY;
            }
        }
        throw new DataNotFoundException(Constant.ADDRESS_NOT_FOUND);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressDTO getAddressById(long id) {
        Address address = addressRepository.findById(id).orElse(null);
        
        if (null == address || address.isDeleted()) {
            logger.error(Constant.ADDRESS_NOT_FOUND);
            throw new DataNotFoundException(Constant.ADDRESS_NOT_FOUND);
        }
        logger.info(Constant.DETAILS_FETCHED_SUCCESSFULLY);
        return UserMapper.convertAddressDAOToDTO(address);
    }
}
