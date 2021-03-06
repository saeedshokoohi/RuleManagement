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
        <title>Create OrgPosition</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Create OrgPosition</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                            <h:outputText value="title:"/>
                                                    <h:inputText value="#{orgPosition.entity.title}" title="title" />
                                                                                                        <h:outputText value="level:"/>
                                                    <h:inputText value="#{orgPosition.entity.level}" title="level" />
                                                                                                        <h:outputText value="byPersonnel:"/>
                                                    <h:selectOneMenu value="#{orgPosition.entity.byPersonnel}"  title="byPersonnel">
                                <f:selectItems  value="#{personnel.allEntitiesAsSelectedItems}"/>
                            </h:selectOneMenu>
                                                                            </h:panelGrid>

                <h:commandButton action="#{orgPosition.create}" value="Save" />
                <h:commandButton action="orgPositionList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
