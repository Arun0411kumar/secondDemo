/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.util.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ideas2it.onlinestore.util.constants.Constant;

/**
 * This class customize the security configuration 
 * 
 * @author arunkumar
 * @version 1.0
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
    
	/**
	 * This method return encoded password  
	 * 
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * This method build user object and return authendication manager object
	 * 
	 * @param httpSecurity
	 * @return AuthenticationManager
	 * @throws Exception
	 */
	@Bean
	public AuthenticationManager authManager(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder.build();
	}

	/**
	 * This method handles authorization
	 * 
	 * @param httpSecurity
	 * @return SecurityFilterChain
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.httpBasic().disable();
		httpSecurity.csrf().disable().authorizeRequests()		    
		    .antMatchers(HttpMethod.GET, Constant.API_V1_USER_ID).hasAnyAuthority(Constant.CUSTOMER, Constant.SELLER, Constant.ADMIN)
		    .antMatchers(HttpMethod.PUT, Constant.API_V1_USER).hasAnyAuthority(Constant.CUSTOMER, Constant.SELLER)
		    .antMatchers(HttpMethod.DELETE, Constant.API_V1_USER_ID).hasAnyAuthority(Constant.CUSTOMER, Constant.SELLER)
			.antMatchers(HttpMethod.GET, Constant.API_V1_USER).hasAuthority(Constant.ADMIN)
		    .antMatchers(HttpMethod.DELETE, Constant.API_V1_ADDRESS_ID).hasAnyAuthority(Constant.CUSTOMER, Constant.SELLER)
			.antMatchers(HttpMethod.POST, Constant.API_V1_ADDRESS).hasAnyAuthority(Constant.CUSTOMER, Constant.SELLER)
		    .antMatchers(HttpMethod.POST, Constant.API_V1_WISHLIST_ADD).hasAuthority(Constant.CUSTOMER)
		    .antMatchers(HttpMethod.POST, Constant.API_V1_WISHLIST_REMOVE).hasAuthority(Constant.CUSTOMER)
		    .antMatchers(HttpMethod.GET, Constant.API_V1_WISHLIST).hasAuthority(Constant.CUSTOMER)
			.antMatchers(HttpMethod.POST, Constant.API_V1_PRODUCT).hasAuthority(Constant.SELLER)
			.antMatchers(HttpMethod.PUT, Constant.API_V1_PRODUCT).hasAuthority(Constant.SELLER)
			.antMatchers(HttpMethod.GET, Constant.API_V1_PRODUCT_ALL).hasAnyAuthority(Constant.ADMIN, Constant.CUSTOMER)
			.antMatchers(HttpMethod.GET, Constant.API_V1_PRODUCT).hasAnyAuthority(Constant.ADMIN, Constant.CUSTOMER)
			.antMatchers(HttpMethod.POST, Constant.API_V1_PRODUCT_BRAND).hasAuthority(Constant.ADMIN)
			.antMatchers(HttpMethod.GET, Constant.API_V1_PRODUCT_BRAND).hasAnyAuthority(Constant.ADMIN)
			.antMatchers(HttpMethod.GET, Constant.API_V1_PRODUCT_BRAND_ALL).hasAnyAuthority(Constant.CUSTOMER, Constant.SELLER, Constant.ADMIN)
			.antMatchers(HttpMethod.DELETE, Constant.API_V1_PRODUCT_BRAND).hasAuthority(Constant.ADMIN)
			.antMatchers(HttpMethod.GET, Constant.API_V1_PRODUCT_BRANDS).hasAnyAuthority(Constant.ADMIN, Constant.CUSTOMER)
			.antMatchers(HttpMethod.GET, Constant.API_V1_PRODUCT_CATEGORY).hasAnyAuthority(Constant.ADMIN, Constant.CUSTOMER)
			.antMatchers(HttpMethod.GET, Constant.API_V1_PRODUCT_SUBCATEGORY).hasAnyAuthority(Constant.ADMIN, Constant.CUSTOMER)
			.antMatchers(HttpMethod.GET, Constant.API_V1_PRODUCT_STOCK_ALL).hasAuthority(Constant.ADMIN)
			.antMatchers(HttpMethod.GET, Constant.API_V1_PRODUCT_STOCK_SELLER).hasAuthority(Constant.SELLER)
			.antMatchers(HttpMethod.PUT, Constant.API_V1_PRODUCT_STOCK).hasAuthority(Constant.SELLER)
			.antMatchers(HttpMethod.GET, Constant.API_V1_PRODUCT_STOCK).hasAnyAuthority(Constant.SELLER, Constant.ADMIN)
			.antMatchers(HttpMethod.DELETE, Constant.API_V1_PRODUCT_STOCK).hasAuthority(Constant.SELLER)
			.antMatchers(HttpMethod.POST, Constant.CATEGORY).permitAll()//.hasAnyAuthority(Constant.ADMIN)
			.antMatchers(HttpMethod.GET, Constant.CATEGORY).permitAll()//.hasAnyAuthority(Constant.CUSTOMER,  Constant.SELLER, Constant.ADMIN)
			.antMatchers(HttpMethod.GET, Constant.CATEGORY_NAME).permitAll()//.hasAnyAuthority(Constant.CUSTOMER, Constant.SELLER, Constant.ADMIN)
		    .antMatchers(HttpMethod.POST, Constant.API_V1_ORDER).hasAuthority(Constant.CUSTOMER)
		    .antMatchers(HttpMethod.GET, Constant.API_V1_ORDER_ID).hasAuthority(Constant.CUSTOMER)
		    .antMatchers(HttpMethod.DELETE, Constant.API_V1_ORDER_ID).hasAuthority(Constant.CUSTOMER)
		    .antMatchers(HttpMethod.POST, Constant.API_V1_USER).permitAll()
			.antMatchers(Constant.AUTH_WHITELIST).permitAll()
		    .antMatchers(HttpMethod.POST, Constant.API_V1_USER_LOGIN).permitAll()
		    .anyRequest().authenticated()
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
}
