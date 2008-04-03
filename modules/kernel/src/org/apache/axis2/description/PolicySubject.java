/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.axis2.description;

import org.apache.axiom.om.util.UUIDGenerator;
import org.apache.neethi.Policy;
import org.apache.neethi.PolicyComponent;
import org.apache.neethi.PolicyReference;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PolicySubject {
	private HashMap attachedPolicyComponents = new HashMap();
	
	public void attachPolicy(Policy policy) {
		String key = policy.getName();
		if (key == null) {
			key = policy.getId();
			if (key == null) {
				key = UUIDGenerator.getUUID();
				policy.setId(key);
			}
		}
		attachPolicyComponent(key, policy);
	}
	
	public void attachPolicyReference(PolicyReference reference) {
		attachedPolicyComponents.put(reference.getURI(), reference); 
	}
	
	public void attachPolicyComponents(List policyComponents) {
		for (Iterator iterator = policyComponents.iterator(); iterator.hasNext();) {
			attachPolicyComponent((PolicyComponent) iterator.next());
		}
	}
	
	public void attachPolicyComponent(PolicyComponent policyComponent) {
		if (policyComponent instanceof Policy) {
			attachPolicy((Policy) policyComponent);
		} else if (policyComponent instanceof PolicyReference) {
			attachPolicyReference((PolicyReference) policyComponent);
		} else {
			throw new IllegalArgumentException("Invalid top level policy component type");
		}
		
	}
	public void attachPolicyComponent(String key, PolicyComponent policyComponent) {
		attachedPolicyComponents.put(key, policyComponent);
	}
	
	
	
	public Collection getAttachPolicyComponents() {
		return attachedPolicyComponents.values();
	}
}
