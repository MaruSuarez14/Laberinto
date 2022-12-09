<%@ page isELIgnored="false" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Maze game</title>
    <script id="jsonObject" type="application/json">
        ${json}
    </script>
</head>

<body>
    <style> <%@include file="/WEB-INF/css/styles.css"%></style>
    <h1 class="title">Maze Game</h1>
    <div class="page">
        <canvas class="canvas" height="650" width="1000" id="canvas"></canvas><br>
        <div class="options_game">
            <button id="options" class="button" onclick="window.location.href='/reset'">Reset Game</button>
            <button id="options" class="button" onclick="window.location.href='/start'">Map Selection</button>
        </div>
    </div>
    <script>
        const json = JSON.parse(document.getElementById('jsonObject').textContent);
        const canvas = document.getElementById("canvas");
        const ctx = canvas.getContext("2d");

        //Tamaño de la puerta o pared (mapsite)
        const mapsiteSize = 50;

        //Anchura y altura del laberinto
        const mazeWidth = 500;
        const mazeHeight = 500;

        //Anchura y altura de la sala
        const roomWidth = 400;
        const roomHeight = 400;

        //Punto de Origen X e Y de la sala
        const roomStartX = 250;
        const roomStartY = 150;

        //Tamaño moneda y llaves
        const coinSize = 40;
        const keySize = 60;

        //Coordenadas de Mapsites (puertas y muros)
        const mapsites = {
            north: {
                x: 425,
                y: 100
            },
            south: {
                x: 425,
                y: 550
            },
            east: {
                x: 650,
                y: 325
            },
            west: {
                x: 200,
                y: 325
            }
        }

        drawText();
        drawMaze();
        drawMessage();
        drawControls();

        //Función para escribir la información del juego
        function drawText() {
            ctx.font = "italic bold 14pt Times New Roman, serif ";
            ctx.fillStyle = "#000";
            ctx.fillText("Room: " + json.room.id, 20, 30);
            ctx.fillText("Keys: " + json.inventory.keys, 20, 60);
            ctx.fillText("Coins: " + json.inventory.coins, 20, 90);
        }

        //Función para escribir el texto con los mensajes de interacción del usuario
        function drawMessage() {
            ctx.font = "italic bold 18pt Times New Roman, serif ";
            ctx.fillStyle = "#000";
            ctx.fillText(json.message, 250, 60);
        }

        //Función para dibujar el laberinto
        function drawMaze() {
            //Dibujar el laberinto
            ctx.beginPath();
            //Laberinto completo
            ctx.fillRect(roomStartX - mapsiteSize, roomStartY - mapsiteSize, mazeWidth, mazeHeight);
            ctx.fillStyle = "#FFF";
            //Habitación
            ctx.fillRect(roomStartX, roomStartY, roomWidth, roomHeight);
            //Lado izquierdo
            ctx.fillStyle = mapsiteStatus(json.room.sides.W);
            ctx.fillRect(mapsites.west.x, mapsites.west.y, mapsiteSize, mapsiteSize);
            //Lado derecho
            ctx.fillStyle = mapsiteStatus(json.room.sides.E);
            ctx.fillRect(mapsites.east.x, mapsites.east.y, mapsiteSize, mapsiteSize);
            //Lado de arriba
            ctx.fillStyle = mapsiteStatus(json.room.sides.N);
            ctx.fillRect(mapsites.north.x, mapsites.north.y, mapsiteSize, mapsiteSize);
            //lado de abajo
            ctx.fillStyle = mapsiteStatus(json.room.sides.S);
            ctx.fillRect(mapsites.south.x, mapsites.south.y, mapsiteSize, mapsiteSize);
            //Personaje
            character = new Image();
            character.src = 'img/character.png';
            character.onload = () => {
                ctx.drawImage(character, 425, 325, 50, 50);
            }
            ctx.stroke();

            drawCoins();
            drawKeys();

        }

        function drawCoins () {
            //Dibujar las monedas
            if (json.room.items.hasOwnProperty("coins")) {
                const coins = json.room.items.coins;
                for (i = 0; i < coins.length; i++) {
                    if (!coins[i].isCollected) {
                        let absoluteCoorXCoin = roomStartX + coins[i].x * roomWidth - coinSize / 2;
                        let absoluteCoorYCoin = roomStartY + coins[i].y * roomHeight - coinSize / 2;
                        coin = new Image();
                        coin.src = 'img/coin.png';
                        coin.onload = () => {
                            ctx.drawImage(coin, absoluteCoorXCoin, absoluteCoorYCoin, coinSize, coinSize);
                        }
                    }
                }
            }
        }

        function drawKeys() {
            //Dibujar las llaves
            if (json.room.items.hasOwnProperty("keys")) {
                const keys = json.room.items.keys;
                for (i = 0; i < keys.length; i++) {
                    if (!keys[i].isCollected) {
                        let absoluteCoorXKey = roomStartX + keys[i].x * roomWidth - keySize / 2;
                        let absoluteCoorYKey = roomStartY + keys[i].y * roomHeight - keySize / 2;
                        key = new Image();
                        key.src = 'img/key.png';
                        key.onload = () => {
                            ctx.drawImage(key, absoluteCoorXKey, absoluteCoorYKey, keySize, keySize);
                        }
                    }
                }
            }
        }

        //Método para dibujar los controles
        function drawControls() {
            up_arrow = new Image();
            up_arrow.src = 'img/up-arrow.png';
            up_arrow.onload = () => {
                ctx.drawImage(up_arrow, 830, 400, 60, 60);
            }
            down_arrow = new Image();
            down_arrow.src = 'img/down-arrow.png';
            down_arrow.onload = () => {
                ctx.drawImage(down_arrow, 830, 470, 60, 60);
            }
            left_arrow = new Image();
            left_arrow.src = 'img/left-arrow.png';
            left_arrow.onload = () => {
                ctx.drawImage(left_arrow, 760, 470, 60, 60);
            }
            right_arrow = new Image();
            right_arrow.src = 'img/right-arrow.png';
            right_arrow.onload = () => {
                ctx.drawImage(right_arrow, 900, 470, 60, 60);
            }
        }

        //Método para determinar el color del muro/puerta dependiendo del estado
        function mapsiteStatus(mapsite) {
            switch (mapsite) {
                case "open":
                    return "#FFF";
                    break;
                case "closed":
                     return "#F00";
                     break;
                default:
                     return "#000";
                     break;
            }
        }

        //Evento para determinar si el usuario pulsa el botón izquierdo del ratón
        canvas.addEventListener("mousedown", function (event) {
            if (!json.endGame) {
                const boundingRect = canvas.getBoundingClientRect();
                const mouseX = event.clientX - boundingRect.left;
                const mouseY = event.clientY - boundingRect.top;
                if (event.button == 0) {
                    collectCoin(mouseX, mouseY);
                    collectKey(mouseX, mouseY);
                    moveCharacter(mouseX, mouseY);
                    openDoor(mouseX, mouseY);
                }
            }
        });

        //Método para recolectar monedas
        function collectCoin(mouseX, mouseY) {
            const coins = json.room.items.coins;
            for (i = 0; i < coins.length; i++) {
                let absoluteCoorXCoin = roomStartX + coins[i].x * roomWidth - coinSize / 2;
                let absoluteCoorYCoin = roomStartY + coins[i].y * roomHeight - coinSize / 2;
                if (mouseX >= absoluteCoorXCoin && mouseX <= absoluteCoorXCoin + coinSize && mouseY >= absoluteCoorYCoin && mouseY <= absoluteCoorYCoin + coinSize) {
                    if (!coins[i].isCollected) {
                        let name = coins[i].name;
                        window.location.href = ("/getCoin?name=" + name);
                    }
                }
            }
        }

        //Método para recolectar llaves
        function collectKey(mouseX, mouseY) {
            const keys = json.room.items.keys;
            for (i = 0; i < keys.length; i++) {
                let absoluteCoorXKey = roomStartX + keys[i].x * roomWidth - keySize / 2;
                let absoluteCoorYKey = roomStartY + keys[i].y * roomHeight - keySize / 2;
                if (mouseX >= absoluteCoorXKey && mouseX <= absoluteCoorXKey + keySize && mouseY >= absoluteCoorYKey && mouseY <= absoluteCoorYKey + keySize) {
                    if (!keys[i].isCollected) {
                        let name = keys[i].name;
                        window.location.href = ("/getKey?name=" + name);
                    }
                }
            }
        }

        //Método para mover el personaje
        function moveCharacter(mouseX, mouseY) {
            if (mouseX >= 830 && mouseX <= 890 && mouseY >= 400 && mouseY <= 460) {
                window.location.href = ("/nav?dir=N");
            } else if (mouseX >= 830 && mouseX <= 890 && mouseY >= 470 && mouseY <= 530) {
                window.location.href = ("/nav?dir=S");
            } else if (mouseX >= 900 && mouseX <= 960 && mouseY >= 470 && mouseY <= 530) {
                window.location.href = ("/nav?dir=E");
            } else if (mouseX >= 760 && mouseX <= 820 && mouseY >= 470 && mouseY <= 530) {
                window.location.href = ("/nav?dir=W");
            }
        }

        //Método para abrir una puerta
        function openDoor(mouseX, mouseY) {
            if (mouseX >= mapsites.north.x && mouseX <= mapsites.north.x + mapsiteSize && mouseY >= mapsites.north.y && mouseY <= mapsites.north.y + mapsiteSize) {
                if (json.room.sides.N == "closed") {
                    window.location.href = ("/open?dir=N");
                }
            } else if (mouseX >= mapsites.south.x && mouseX <= mapsites.south.x + mapsiteSize && mouseY >= mapsites.south.y && mouseY <= mapsites.south.y + mapsiteSize) {
                if (json.room.sides.S == "closed") {
                    window.location.href = ("/open?dir=S");
                }
            } else if (mouseX >= mapsites.east.x && mouseX <= mapsites.east.x + mapsiteSize && mouseY >= mapsites.east.y && mouseY <= mapsites.east.y + mapsiteSize) {
                if (json.room.sides.E == "closed") {
                    window.location.href = ("/open?dir=E");
                }
            } else if (mouseX >= mapsites.west.x && mouseX <= mapsites.west.x + mapsiteSize && mouseY >= mapsites.west.y && mouseY <= mapsites.west.y + mapsiteSize) {
                if (json.room.sides.W == "closed") {
                    window.location.href = ("/open?dir=W");
                }
            }
        }

    </script>
</body>

</html>