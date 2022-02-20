/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.services.listeners;


import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;

public interface ViewServiceSetListener {

    void onBrowse(Session session, ServiceRequest serviceRequest);

    void onBrowseNext(Session session, ServiceRequest serviceRequest);

    void onTranslateBrowsePaths(Session session, ServiceRequest serviceRequest);

    void onRegisterNodes(Session session, ServiceRequest serviceRequest);

    void onUnregisterNodes(Session session, ServiceRequest serviceRequest);

}
