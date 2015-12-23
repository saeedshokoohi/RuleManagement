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
        <title>Personnel View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Personnel View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="firstName:"/>
                                                   <h:outputText value="#{personnel.entity.firstName}" title="firstName" />
                                                                                                       <h:outputText value="lastName:"/>
                                                   <h:outputText value="#{personnel.entity.lastName}" title="lastName" />
                                                                                                       <h:outputText value="nationalNumber:"/>
                                                   <h:outputText value="#{personnel.entity.nationalNumber}" title="nationalNumber" />
                                                                                                       <h:outputText value="age:"/>
                                                   <h:outputText value="#{personnel.entity.age}" title="age" />
                                                                                                       <h:outputText value="gender:"/>
                                                   <h:outputText value="#{personnel.entity.gender}" title="gender" />
                                                                                
                                                    </h:panelGrid>

                <h:commandButton action="editPersonnel" value="Edit" />
                <br>
                <h:commandButton action="personnelList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
