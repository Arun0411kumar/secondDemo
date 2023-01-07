/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service;

import com.ideas2it.onlinestore.dto.AddressDTO;

/**
 * Interface for Address service
 *
 * @author Gokul V
 * @version 1.0
 * @since 2022-12-17
 */
public interface AddressService {

    /**
     * <p>Add address to user using given address details.
     * if the given address details are not valid
     * throws RuntimeException otherwise returns string message.</p>
     *
     * @param address                 details of the address.
     * @return String                 status message.
     */
    String addAddress(AddressDTO address);

    /**
     * <p>Delete the address from the user using given address id.
     * if the given address id is not exists throws RuntimeException
     * otherwise returns string message.</p>
     *
     * @param id            id of the address.
     * @return String       status message.
     */
    String deleteAddress(long id);

    /**
     * <p>Get the address using given address id.
     * if the given address id is not valid throws
     * RuntimeException otherwise returns address object.</p>
     *
     * @param id             id of the address.
     * @return AddressDTO    details of the address.
     */
    AddressDTO getAddressById(long id);
}
