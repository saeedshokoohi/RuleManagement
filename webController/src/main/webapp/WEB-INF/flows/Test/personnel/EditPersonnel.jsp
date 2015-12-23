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
        <title>Edit Personnel</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Edit Personnel</h1>
            <h:form>
                <h:panelGrid columns="2">
                                    <h:outputText value="firstName:"/>
                                                                        <h:inputText value="#{personnel.entity.firstName}" title="firstName" />
                                                                                <h:outputText value="lastName:"/>
                                                                        <h:inputText value="#{personnel.entity.lastName}" title="lastName" />
                                                                                <h:outputText value="nationalNumber:"/>
                                                                        <h:inputText value="#{personnel.entity.nationalNumber}" title="nationalNumber" />
                                                                                <h:outputText value="age:"/>
                                                                        <h:inputText value="#{personnel.entity.age}" title="age" />
                                                                                <h:outputText value="gender:"/>
                                                                        <h:inputText value="#{personnel.entity.gender}" title="gender" />
                                                                                <h:outputText value="orgPositions:"/>
                    
                                                    </h:panelGrid>

                <h:commandButton action="#{personnel.save}" value="Save"/>
                <h:commandButton action="personnelList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
