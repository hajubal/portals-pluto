/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package javax.portlet.tck.portlets;

import java.io.*;
import java.util.logging.*;
import javax.xml.namespace.QName;
import javax.portlet.*;
import javax.portlet.tck.beans.*;
import javax.portlet.tck.constants.*;
import static javax.portlet.tck.constants.Constants.*;
import static javax.portlet.tck.beans.JSR286SpecTestCaseDetails.*;
import static javax.portlet.PortletSession.*;

/**
 * This is the event processing portlet for the test cases. This portlet processes events, but does
 * not publish them. Events are published in the main portlet for the test cases.
 * 
 * @author ahmed
 */
public class AddlPortletTests_SPEC2_5_ActionHandling_event implements Portlet, EventPortlet {
  private static final String LOG_CLASS =
      AddlPortletTests_SPEC2_5_ActionHandling_event.class.getName();
  private final Logger LOGGER = Logger.getLogger(LOG_CLASS);

  private boolean tr2 = false;
  private boolean tr3 = false;
  private boolean tr4 = false;

  @Override
  public void init(PortletConfig config) throws PortletException {}

  @Override
  public void destroy() {}

  @Override
  public void processAction(ActionRequest portletReq, ActionResponse portletResp)
      throws PortletException, IOException {
    LOGGER.entering(LOG_CLASS, "event companion processAction entry");
    portletResp.setRenderParameters(portletReq.getParameterMap());
    long tid = Thread.currentThread().getId();
    portletReq.setAttribute(THREADID_ATTR, tid);

    String action = portletReq.getParameter("inputval");
    if (action != null) {
      if (action.equals("V2AddlPortletTests_SPEC2_5_ActionHandling_exception1")) {
        /* TestCase: V2AddlPortletTests_SPEC2_5_ActionHandling_exception1 */
        /* Details: "If the portlet throws an PortletException in */
        /* processAction, all operations on the ActionResponse, including set */
        /* events, must be ignored" */
        portletResp.setRenderParameter("tr3_action", "true");
        portletReq.getPortletSession().setAttribute(
            Constants.RESULT_ATTR_PREFIX + "AddlPortletTests_SPEC2_5_ActionHandling_event_tr3",
            "true", APPLICATION_SCOPE);
        QName eventQName = new QName(TCKNAMESPACE, "AddlPortletTests_SPEC2_5_ActionHandling");
        portletResp.setEvent(eventQName, "Hi!");
        throw new PortletException(
            "PortletException from V2AddlPortletTests_SPEC2_5_ActionHandling_exception1");
      } else if (action.equals("V2AddlPortletTests_SPEC2_5_ActionHandling_exception2")) {
        /* TestCase: V2AddlPortletTests_SPEC2_5_ActionHandling_exception2 */
        /* Details: "If the portlet throws a RuntimeException in */
        /* processAction, all operations on the ActionResponse, including set */
        /* events, must be ignored" */
        portletResp.setRenderParameter("tr4_action", "true");
        portletReq.getPortletSession().setAttribute(
            Constants.RESULT_ATTR_PREFIX + "AddlPortletTests_SPEC2_5_ActionHandling_event_tr4",
            "true", APPLICATION_SCOPE);
        QName eventQName = new QName(TCKNAMESPACE, "AddlPortletTests_SPEC2_5_ActionHandling");
        portletResp.setEvent(eventQName, "Hi!");
        throw new RuntimeException(
            "RuntimeException from V2AddlPortletTests_SPEC2_5_ActionHandling_exception2");
      }
    }
  }

  @Override
  public void processEvent(EventRequest portletReq, EventResponse portletResp)
      throws PortletException, IOException {
    LOGGER.entering(LOG_CLASS, "event companion processEvent");
    this.tr2 = true;
    this.tr3 = true;
    this.tr4 = true;
  }

  @Override
  public void render(RenderRequest portletReq, RenderResponse portletResp)
      throws PortletException, IOException {

    LOGGER.entering(LOG_CLASS, "event companion render");
    JSR286SpecTestCaseDetails tcd = new JSR286SpecTestCaseDetails();

    PortletSession ps = portletReq.getPortletSession();
    portletResp.setContentType("text/html");
    PrintWriter writer = portletResp.getWriter();
    writer.write("<h3>Event Companion Portlet </h3>\n");
    writer.write("<p>AddlPortletTests_SPEC2_15_ActionHandling_event</p>\n");

    /* TestCase: V2AddlPortletTests_SPEC2_5_ActionHandling_exception1 */
    /* Details: "If the portlet throws an PortletException in */
    /* processAction, all operations on the ActionResponse, including set */
    /* events, must be ignored" */
    String tr3_success = (String) ps.getAttribute(
        RESULT_ATTR_PREFIX + "AddlPortletTests_SPEC2_5_ActionHandling_event_tr3",
        APPLICATION_SCOPE);
    if (tr3_success != null && tr3_success.equals("true") && !this.tr3
        && portletReq.getParameter("tr3_action") == null) {
      TestResult tr3 =
          tcd.getTestResultFailed(V2ADDLPORTLETTESTS_SPEC2_5_ACTIONHANDLING_EXCEPTION1);
      tr3.setTcSuccess(true);
      tr3.writeTo(writer);
    } else {
      PortletURL aurl = portletResp.createActionURL();
      aurl.setParameters(portletReq.getPrivateParameterMap());
      TestButton tb = new TestButton("V2AddlPortletTests_SPEC2_5_ActionHandling_exception1", aurl);
      tb.writeTo(writer);
    }

    /* TestCase: V2AddlPortletTests_SPEC2_5_ActionHandling_exception2 */
    /* Details: "If the portlet throws a RuntimeException in */
    /* processAction, all operations on the ActionResponse, including set */
    /* events, must be ignored" */
    String tr4_success = (String) ps.getAttribute(
        RESULT_ATTR_PREFIX + "AddlPortletTests_SPEC2_5_ActionHandling_event_tr4",
        APPLICATION_SCOPE);
    if (tr4_success != null && tr4_success.equals("true") && !this.tr4
        && portletReq.getParameter("tr4_action") == null) {
      TestResult tr3 =
          tcd.getTestResultFailed(V2ADDLPORTLETTESTS_SPEC2_5_ACTIONHANDLING_EXCEPTION2);
      tr3.setTcSuccess(true);
      tr3.writeTo(writer);
    } else {
      PortletURL aurl = portletResp.createActionURL();
      aurl.setParameters(portletReq.getPrivateParameterMap());
      TestButton tb = new TestButton("V2AddlPortletTests_SPEC2_5_ActionHandling_exception2", aurl);
      tb.writeTo(writer);
    }

    /* TestCase: V2AddlPortletTests_SPEC2_5_ActionHandling_action7 */
    /* Details: "If the portlet issues a redirect during action */
    /* processing, events that were set are not processed" */
    String tr2_success = (String) ps.getAttribute(
        RESULT_ATTR_PREFIX + "AddlPortletTests_SPEC2_5_ActionHandling_event_tr2",
        APPLICATION_SCOPE);
    TestResult tr2 = tcd.getTestResultFailed(V2ADDLPORTLETTESTS_SPEC2_5_ACTIONHANDLING_ACTION7);
    if (!this.tr2 && tr2_success != null && tr2_success.equals("true")) {
      tr2.setTcSuccess(true);
    } else {
      tr2.appendTcDetail(
          "Failed because ProcessEvent method of AddlPortletTests_SPEC2_5_ActionHandling_event portlet is called after sending redirect");
    }
    tr2.writeTo(writer);

  }

}
