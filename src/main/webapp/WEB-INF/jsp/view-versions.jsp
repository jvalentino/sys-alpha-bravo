<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
    <head>
        <title>Invalid</title>
        <spring:url value="/resources/core/css/main.css" var="coreCss" />
        <link href="${coreCss}" rel="stylesheet" />
    </head>
    <body>
        <h1>${model.doc.name}</h1>

        <%@ include file="menu.jsp" %>

        <table>
            <theader>
                <tr>
                    <th>Version</th>
                    <th>Uploaded</th>
                </tr
            </theader>
            <tbody>
                <c:forEach items="${model.doc.versions}" var="version">
                    <tr>
                        <td>${version.versionNum}</td>
                        <td>${version.createdDateTime}</td>
                    </tr>
                </c:forEach>
            </tbody>
        <table>

    </body>
</html>