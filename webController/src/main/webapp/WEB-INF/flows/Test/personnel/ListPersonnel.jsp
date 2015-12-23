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
        <title>Personnel List</title>
    </head>
    <body>
        <f:view>
            <h1>Personnel List</h1>
            <h:form>
              <h:commandButton action="#{personnel.startCreate}" value="Create"/>

              <h:dataTable value='#{personnel.allEntities}' var='item' border="1" cellpadding="2" cellspacing="0">
                                                       <h:column>
                      <f:facet name="header">
                      <h:outputText value="firstName"/>
                      </f:facet>
                      <h:outputText value="#{item.firstName}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="lastName"/>
                      </f:facet>
                      <h:outputText value="#{item.lastName}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="nationalNumber"/>
                      </f:facet>
                      <h:outputText value="#{item.nationalNumber}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="age"/>
                      </f:facet>
                      <h:outputText value="#{item.age}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="gender"/>
                      </f:facet>
                      <h:outputText value="#{item.gender}"/>
                  </h:column>
                                                                                     <h:column>
                      <h:commandButton value="View" action="#{personnel.startView}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Edit" action="#{personnel.startEdit}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Delete" action="#{personnel.delete}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>
                  </h:column>
              </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
