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
package com.aerospike.example.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aerospike.example.config.AerospikeServiceConfiguration;

@Controller
public class IndexController {
	@Autowired(required = false)
	@Qualifier("cacheService")
	AerospikeServiceConfiguration cache;
	
	@Autowired(required = false) 
	@Qualifier("sessionService")
	AerospikeServiceConfiguration session;
	
	@RequestMapping("/")
    public String home(Model model) {
        Map<String, String> services = new LinkedHashMap<String, String>();
        if (cache != null) {
        	services.put(cache.getService(), cache.toString());
        }
        
        if (session != null) {
        	services.put(session.getService(), session.toString());
        }
        
        model.addAttribute("services", services.entrySet());
        
        return "index";
    }
}
