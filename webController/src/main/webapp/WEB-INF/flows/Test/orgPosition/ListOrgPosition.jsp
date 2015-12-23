<!--
  Created by IntelliJ IDEA.
  User: saeed
  Date: 23/12/2015
  Time: 06:02 PM
  To change this template use File | Settings | File Templates.
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>OrgPosition List</title>
    </head>
    <body>
        <f:view>
            <h1>OrgPosition List</h1>
            <h:form>
              <h:commandButton action="#{orgPosition.startCreate}" value="Create"/>

              <h:dataTable value='#{orgPosition.allEntities}' var='item' border="1" cellpadding="2" cellspacing="0">
                                                       <h:column>
                      <f:facet name="header">
                      <h:outputText value="title"/>
                      </f:facet>
                      <h:outputText value="#{item.title}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="level"/>
                      </f:facet>
                      <h:outputText value="#{item.level}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="byPersonnel"/>
                      </f:facet>
                      <h:outputText value="#{item.byPersonnel}"/>
                  </h:column>
                                                   <h:column>
                      <h:commandButton value="View" action="#{orgPosition.startView}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Edit" action="#{orgPosition.startEdit}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Delete" action="#{orgPosition.delete}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>
                  </h:column>
              </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
