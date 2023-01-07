/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.util.constants;

/**
 * Constant class for String message
 *
 * @author Gokul V
 * @version 1.0
 * @since 2022-12-19
 */
public class Constant {
    public static final String DETAILS_FETCHED_SUCCESSFULLY = "Details Fetched Successfully";
    public static final String PROFILE_CREATED = "Profile is created Successfully";
    public static final String CART_CREATED = "Cart is created successfully";
    public static final String CART_FETCHED = "Cart is fetched successfully";
    public static final String CART_TOTAL_UPDATED = "Your cart total has been updated successfully";
    public static final String ORDER_SUCCESSFUL = "Order placed successfully"; 
    public static final String ORDER_UPDATED = "Cart is updated successfully";
    public static final String ORDER_FETCHED = "order fetched successfully";  
    public static final String ORDERS_FETCHED = "orders fetched successfully"; 
    public static final String ORDER_NOT_FOUND = "Order not found for the entered ID";  
    public static final String ADD_TO_CART = "Product has been added to cart successfully";
    public static final String REMOVE_FROM_CART = "Product has been removed from the cart";
    public static final String PRODUCT_ALREADY_EXISTS = "Profile is Already Exists";
    public static final String PROFILE_NOT_CREATED = "Profile is Not created";
    public static final String DELETED_SUCCESSFULLY = " Has Deleted Successfully";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String ERROR_MESSAGE_EMAIL_ID_EXISTS = "This EmailId is already registered";
    public static final String ERROR_MESSAGE_MOBILE_NUMBER_EXISTS = "This Phone number is already registered";
    public static final String ERROR_MESSAGE_EMAIL_ID_PHONE_NUMBER_EXISTS = "This Phone number and Email Id is already registered";
    public static final String REMOVED_SUCCESSFULLY = "Items Removed Successfully";
    public static final String UPDATED_SUCCESSFULLY = " Has Updated Successfully";
    public static final String ERROR_MESSAGE_NOT_UPDATED = "Profile is not updated";
    public static final String ADDRESS_DELETED_SUCCESSFULLY = " address is deleted";
    public static final String ADDRESS_ADDED_SUCCESSFULLY = " Your Address is added successfully";
    public static final String ERROR_MESSAGE_ADDRESS_NOT_ADDED = " Address is not added";
    public static final String ERROR_MESSAGE_INVALID_CREDENTIALS = "Invalid_credentials";
    public static final String PRODUCT_ADDED_SUCCESSFULLY = "Product is added Successfully";
    public static final String PRODUCT_NOT_FOUND = "Product not found";
    public static final String ERROR_MESSAGE_WISHLIST_NOT_FOUND = "Wishlist not found";
    public static final String PRODUCTS_NOT_EXISTS = "Products not exists";
    public static final String REGEX_FOR_TEXT = "^[a-zA-Z\\s\\.]*$";
    public static final String REGEX_FOR_DOOR_NUMBER = "^[a-zA-Z0-9\\/]{1,7}$";
    public static final String REGEX_FOR_PHONE_NUMBER = "^[6-9]{1}[0-9]{9}$";
    public static final String REGEX_FOR_EMAIL_ID = "^[a-z]{1}[a-z0-9._]+@[a-z0-9]+[.][a-z]*$";
    public static final String REGEX_FOR_INVALID_FORMAT = "Invalid Format";
    public static final String ADDRESS_NOT_FOUND = "Address not found";
    public static final String CATEGORY_ALREDY_EXIST = "your adding category or sub category alredy exist";
    public static final String PRODUCT_CREATION_FAILED = "Product creation failed";
    public static final String PRODUCT_UPDATION_SUCCESS = "Product updated successfully";
    public static final String PRODUCT_UPDATION_FAILED = "Product updation failed";
    public static final String DUPLICATE_BRAND_NAME = "Brand name already exists";
    public static final String BRAND_CREATION_FAILED = "Brand creation failed";
    public static final String EXPIRY_DATE_COMPULSORY = "Date of expire is compulsory";
    public static final String MANUFACTURE_DATE_COMPULSORY = "Date of manufacture is compulsory";
    public static final String DESCRIPTION_COMPULSORY = "Product description is compulsory";
    public static final String PRODUCT_NAME_COMPULSORY = "Product name is compulsory";
    public static final String REGEX_FOR_NAME = "(([A-Za-z]{3,20})((([ ][A-Za-z0-9]{1,20}){1,2})?))";
    public static final String INVALID_NAME = "invalid product name";
    public static final String BRAND_COMPULSORY = "Brand is compulsory";
    public static final String CATEGORY_COMPULSORY = "Category is compulsory";
    public static final String SUB_CATEGORY_COMPULSORY = "Sub category is compulsory";
    public static final String BRAND_NAME_COMPULSORY = "Brand name is compulsory";
    public static final String BRAND_NAME_REGEX = "[A-Za-z]{3,20}";
    public static final String INVALID_BRAND_NAME = "invalid brand name";
    public static final String GET_BY_BRAND_NAME = "from Brand where name = :brandName and deleted = false";
    public static final String GET_BY_CATEGORY = "from Product where category_id = :categoryId and deleted = false";
    public static final String GET_BY_SUB_CATEGORY = "from Product where sub_category_id = :subCategoryId and deleted = false";
    public static final String GET_BY_BRAND = "from Product where brand_id = :brandId and deleted = false";
    public static final String NO_PRODUCT_FOUND_IN_CATEGORY = "No products found in that category";
    public static final String NO_PRODUCT_FOUND_IN_SUBCATEGORY = "No products found in that sub category";
    public static final String NO_PRODUCT_FOUND_IN_BRAND = "No products found in that brand";
    public static final String NO_PRODUCT_BRAND_FOUND = "No brands or products found";
    public static final String BRANDS_NOT_FOUND = "Brands does not exist";
    public static final String BRAND_NOT_FOUND = "Brand not found";
    public static final String GET_BY_NAME_AND_DESCRIPTION = "from Product where name = :name and description = :description and deleted = false";
    public static final String STOCK_CREATION_FAILED = "Product is created but stock for product creation failed";
    public static final String GETTING_PRODUCTS_SUCCESS = "Getting products successfull";
    public static final String GETTING_PRODUCT_SUCCESS = "Getting product successfull";
    public static final String BRAND_UPDATION_FAILED = "Brand updation failed";
    public static final String INVALID_MANUFACTURE_EXPIRY_DATE = "Manufacture date must be before expiry date";
    public static final String BRAND_FOUND = "Brand found succesfully";
    public static final String BRAND_UPDATION_SUCCESS = "Brand updated successfully";
    public static final String BRAND_CREATION_SUCCESS = "Brand created successfully";
    public static final String BRANDS_FOUND = "Brands found successfully";
    public static final String NO_CATEGORY_FOUND = "No categories found";
    public static final String INSUFFICIENT_STOCK = "Stock insufficient for the requested quantity";
    public static final String EMPTY_CART = "Your shopping cart is empty.";
    public static final String ORDER_CANCELLED = "Your order has been cancelled successfully";
    public static final String ORDER_CANCELATION_FAILURE = "There was an error while cancelling this order";
    public static final String CATEGORY_NOT_EXISTS = "Your searching category is not there";
    public static final String SUB_CATEGORY_NOT_EXISTS = "Your searching sub category is not there";
    public static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };
    public static final String API_V1_USER = "/user";
    public static final String API_V1_USER_ID = "/user/{id}";
    public static final String API_V1_USER_LOGIN = "/user/login";
    public static final String API_V1_ADDRESS = "/address";
    public static final String API_V1_ADDRESS_ID = "/address/{id}";
    public static final String API_V1_WISHLIST = "/wishlist";
    public static final String API_V1_WISHLIST_ADD = "/wishlist/product/{id}";
    public static final String API_V1_WISHLIST_REMOVE = "/wishlist/product/{id}";
    public static final String API_V1_PRODUCT = "/api/v1/product";
    public static final String API_V1_PRODUCT_STOCK = "/api/v1/product/stock";
    public static final String API_V1_PRODUCT_STOCK_ALL = "/api/v1/product/stock/all";
    public static final String API_V1_ORDER = "/api/v1/order";
    public static final String API_V1_ORDER_ID = "/api/v1/order/{orderId}";
    public static final String CUSTOMER = "CUSTOMER";
    public static final String SELLER = "SELLER";
    public static final String ADMIN = "ADMIN";
    public static final String API_V1_PRODUCT_ALL = "/product/all";
    public static final String API_V1_PRODUCT_BRAND = "/product/brand";
    public static final String API_V1_PRODUCT_BRAND_ALL = "/product/brand/all";
    public static final String API_V1_PRODUCT_BRANDS = "/product/brands";
    public static final String API_V1_PRODUCT_CATEGORY = "/product/category";
    public static final String API_V1_PRODUCT_SUBCATEGORY = "/product/subcategory";
    public static final String API_V1_PRODUCT_STOCK_SELLER = "/product/stock/seller";
    public static final String STOCK_ADDED_SUCCESSFULLY = "Stock created successfully";
    public static final String WITH_PRODUCT_NAME = " with product name: ";
    public static final String WITH_BRAND_NAME = " with brand name: ";
    public static final String ADD_PRODUCT = "Add Product to Cart";
    public static final String ADD_PRODUCT_DESCRIPTION = "This method takes a product Id corresponding to a particular product "
    		+ "and adds that product to the cart of currently logged in user. The id must be a numeric value and there should be sufficient "
    		+ "stock available in the inventory for the concerned product.";
    
    public static final String REMOVE_PRODUCT = "Remove Product from Cart";
    public static final String REMOVE_PRODUCT_DESCRIPTION = "Provide an ID that identifies the product that is to be added to cart. "
    		+ "The field takes only numeric values.";
    
    public static final String CART = "Show Cart";
    public static final String CART_DESCRIPTION = "This API fetches current status of cart for the currently logged in user. "
    		+ "Shows what products have been added to the cart so far and what is the total value of the products in cart";
    
    public static final String PLACE_ORDER = "Checkout Cart";
    public static final String PLACE_ORDER_DESCRIPTION = "The method places an order for the products that a user has added "
    		+ "to his cart. If the cart is empty the method triggers a custom exception";

    public static final String UPDATE_ORDER = "Update Order";
    public static final String UPDATE_ORDER_DESCRIPTION = "This method is used to cancel a particular order. The order Id must "
    		+ "be a numeric value. The method returns a custom response with a message describing if the "
    		+ "order has been cancelled or not with a timestamp and a response code";
    
    public static final String SHOW_PARTICULAR_ORDER = "Search Order";
    public static final String SHOW_PARTICULAR_ORDER_DESCRIPTION = "The method searches for a particular order using the order Id. "
    		+ "The method returns the order details along with the delivery address details. The Id provided must be a numeric value.";
    
    public static final String SHOW_ALL_ORDERS = "Order History";
    public static final String SHOW_ALL_ORDERS_DESCRIPTION = "The method returns the order history of a user.";
    public static final String CART_PRODUCT_UPDATED = "Cart products have been updated successfully";
    public static final String ERROR_MESSAGE_EMPTY_LIST = "your list is empty";
    public static final String STOCK_NOT_FOUND = "stock not found for given request input";
    public static final String CATEGORY_NOT_CREATED = "your category or subcategory not created";
    public static final String CATEGORY = "/category";
    public static final String CATEGORY_NAME = "/category/{name}";
    public static final String ORDER_ALREADY_CANCELLED = "The order you are trying to cancel has already been cancelled. ID: ";
    public static final String ORDER_ALREADY_DELIVERED = "The order you are trying to cancel has already been delivered. ID: ";

}
