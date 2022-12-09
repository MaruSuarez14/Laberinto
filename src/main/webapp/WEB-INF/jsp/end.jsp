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
    <title>Maze End</title>
</head>

<body>
    <style> <%@include file="/WEB-INF/css/styles.css"%></style>
    <h1 class="end_title"> ¡Congratulations! You have completed the maze in ${elapsedTime} seconds</h1>
        <h2 class="end_subtitle"> If you want to save the game, put your name below </h2>
        <form action="/endform" method="POST" class="save_form">
            Name: <input type="text" name="name" id="name"><br>
            <input class="button" type="submit" id="save_button" value="Save">
        </form>
</body>

</html>