<%@ page isELIgnored="false" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Maze record</title>
</head>

<body>
    <style> <%@include file="/WEB-INF/css/styles.css"%></style>
    <h1 class="title">Winners record</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Maze</th>
            <th>Elapsed Time</th>
        </tr>
        <c:forEach items="${winners}" var="w">
            <tr>
                <td>${w.getUserName()}</td>
                <td>${w.getMazeName()}</td>
                <td>${w.getElapsedTime()}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>