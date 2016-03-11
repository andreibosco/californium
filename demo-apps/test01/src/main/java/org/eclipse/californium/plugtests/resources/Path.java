/*******************************************************************************
 * Copyright (c) 2015 Institute for Pervasive Computing, ETH Zurich and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *    http://www.eclipse.org/org/documents/edl-v10.html.
 * 
 * Contributors:
 *    Matthias Kovatsch - creator and main architect
 ******************************************************************************/
package org.eclipse.californium.plugtests.resources;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.*;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.LinkFormat;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * This resource implements a test of specification for the ETSI IoT CoAP Plugtests, London, UK, 7--9 Mar 2014.
 */
public class Path extends CoapResource {

	public Path() {
		super("path");
		getAttributes().setTitle("Hierarchical link description entry");
		getAttributes().addContentType(APPLICATION_LINK_FORMAT);
		add(new PathSub("sub1"));
		// add(new PathSub("sub2"));
		// add(new PathSub("sub3"));
		// add(new Mote("mote1"));
		// add(new Mote("mote2"));

		int devices;
		int devicesTotal = 50;
		for (devices = 0; devices < devicesTotal; devices++) {
			add(new Mote("mote"+devices));
		}
	}
	
	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.respond(CONTENT, LinkFormat.serializeTree(this), APPLICATION_LINK_FORMAT);
	}

}
