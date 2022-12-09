<%@ page isELIgnored="false" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Maze selection</title>
</head>

<body>

</body>
    <style> <%@include file="/WEB-INF/css/styles.css"%></style>
    <h1 class="title">Maze Game</h1>
    <form action="/start" method="POST">
        <div class="map-selection">
            <h2>Choose a map</h2>
                    <select name="mazeid" id="mazeid">
                        <option value="ChillMaze"> Chill Maze </option>
                        <option value="RegularMaze"> Regular Maze </option>
                        <option value="HardMaze"> Hard Maze </option>
                    </select><br>
            <input class="button" type="submit" id="save_button" value="Start">
        </div>
    </form>
</html>