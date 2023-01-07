/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.onlinestore.dto.AddressDTO;
import com.ideas2it.onlinestore.dto.UserDTO;
import com.ideas2it.onlinestore.service.AddressService;

/**
 * Controller for Address.
 *
 * @author - Gokul V
 * @version - 1.0
 * @since - 2022-12-17
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * <p>Add address to the user using given address details.
     * if the address details are not valid throws
     * Runtime otherwise returns message.</p>
     *
     * @param addressDTO                 details of the address.
     * @return ResponseEntity<String>    status message.
     */
    @PostMapping
    @ApiOperation(value = "Add Address",
            notes = "This Api add address details to the user",
            response = UserDTO.class)
    public ResponseEntity<String> addAddress(@Valid @RequestBody AddressDTO addressDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(addressDTO));
    }

    /**
     * <p>Deletes the address from the user using given address id.
     * if the given address id is not exists throws RuntimeException
     * otherwise returns message.</p>
     *
     * @param addressId                 id of the address.
     * @return ResponseEntity<String>   status message.
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete User Address",
            notes = "This Api Remove the address from the user account",
            response = UserDTO.class)
    public ResponseEntity<String> deleteAddress(
    		@ApiParam(name = "ID", value = "ID of the Address") 
			@PathVariable("id") long addressId){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.deleteAddress(addressId));
    }
}
