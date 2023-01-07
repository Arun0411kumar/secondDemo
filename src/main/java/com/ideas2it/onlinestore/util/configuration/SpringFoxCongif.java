/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 * 
 * This document is protected by copyright. No part of this document may be 
 * reproduced in any form by any means without prior written authorization of 
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.util.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This a configuration class for Swagger documentation.
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
@Configuration
@EnableSwagger2
@Component
public class SpringFoxCongif {

	/**
	 * This method generates a Docket object that contains 
	 * all the customizable properties we have set and is 
	 * used by Swagger to generate the documentation.
	 * 
	 * @return (Docket object with customized properties)
	 */
	@Bean
	public Docket swaggerConfiguration() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ideas2it.onlinestore.controller"))
				.paths(PathSelectors.ant("/api/v1/**"))
				.build();
	}
	
	/**
	 * This method holds the general information about the API
	 * and the contact information about the the developers.
	 * @return
	 */
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Online Store", 
	      "Online Portal to connect Sellers and Buyers", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("Aabid Farooq", "www.ideas2it.com", "aabidbinfarooq.ibniali@ideas2it.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}
	
	/**
	 * This method return the JWT tokens.
	 * @return ApiKey
	 */
	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "header"); 
	}
	
	/**
	 * This method returns the security context that uses deafult authentication
	 * to create security references for the currently logged in user.
	 *  
	 * @return SecurityContext(which stores all the security details of the
	 * authenticated user)
	 */
	private SecurityContext securityContext( ) {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}
	
	/**
	 * This method uses authorization scopes to return a list of
	 * Security references that dictate the privileges that the a
	 * user has.
	 * 
	 * @return(List of security references)
	 */
	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT",
				authorizationScopes));
	}
}
