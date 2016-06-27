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

import java.util.Map;

public class AerospikeServiceConfiguration {
	private final String service;
	private final String hostname;
	private final Integer port;
	private final String set;
	private final String namespace;
	
	public AerospikeServiceConfiguration(String service, Map<String, Object> credentials) {
		this.service = service;
		this.hostname = (String)credentials.get("hostname");
		this.port = (Integer)credentials.get("port");
		this.set = (String)credentials.get("set");
		this.namespace = (String)credentials.get("namespace");
	}
	
	public final String getHostname() {
		return hostname;
	}

	public final Integer getPort() {
		return port;
	}

	public final String getSet() {
		return set;
	}

	public final String getNamespace() {
		return namespace;
	}
	
	public final String getService() {
		return service;
	}
	
	@Override
	public String toString() {
		return "Host: " + this.hostname + ":" + this.port +
			   " Namespace: " + this.namespace +
			   " Set: " + this.set;
	}
}
