/*
 * Copyright 2012-2016 Aerospike, Inc.
 *
 * Portions may be licensed to Aerospike, Inc. under one or more contributor
 * license agreements WHICH ARE COMPATIBLE WITH THE APACHE LICENSE, VERSION 2.0.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.aerospike.example.config;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.pivotal.labs.cfenv.CloudFoundryEnvironment;
import io.pivotal.labs.cfenv.CloudFoundryEnvironmentException;

@Configuration
@Profile("cloud")
public class AppConfiguration extends AbstractCloudConfig {
	private static final String CACHE_SERVICE_NAME = "aerospike-cache";
	private static final String SESSION_SERVICE_NAME = "aerospike-session";
	
    private AerospikeServiceConfiguration cacheService = null;
    private AerospikeServiceConfiguration sessionService = null;
    
	public AppConfiguration() throws CloudFoundryEnvironmentException {  
		CloudFoundryEnvironment environment = new CloudFoundryEnvironment(System::getenv);
        
        if (environment.getServiceNames().contains(CACHE_SERVICE_NAME)) {
			this.cacheService = new AerospikeServiceConfiguration(
					CACHE_SERVICE_NAME, 
					environment.getService(CACHE_SERVICE_NAME).getCredentials());
		}
        
        if (environment.getServiceNames().contains(SESSION_SERVICE_NAME)) {
        	this.sessionService = new AerospikeServiceConfiguration(
        			SESSION_SERVICE_NAME, 
        			environment.getService(SESSION_SERVICE_NAME).getCredentials());
        }
	}
	
	@Bean(name="cacheService")
	public AerospikeServiceConfiguration cacheService() {
		return this.cacheService;
	}
	
	@Bean(name="sessionService")
	public AerospikeServiceConfiguration sessionService() {
		return this.sessionService;
	}
}
