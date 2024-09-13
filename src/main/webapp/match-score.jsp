<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Match Score</title>
</head>

<body>

<div class= 'header4'>
    <div class= 'header-line'>
        <div class='tennis-text'>
            Tennis Scoreboard
        </div>

        <div class='nav'>
            <a class='nav-item' href="index.jsp">Home</a>
        </div>
    </div>


    <div class="container4">
        <div class="container-logo">

            <div class="logo1">
                <img src="images/logo1.png">
            </div>

            <div class="court">
                COURT No. 7
            </div>

            <div class="logo2">
                <img src="images/logo2.png">
            </div>


        </div>

        <section class="score">

            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>

                <tr class="player1">
                    <td>${match.player1.name}</td>
                    <td class="table-text">${match.score.getPlayerScore(0)}</td>
                    <td class="table-text">${match.score.getSetScore().getPlayerScore(0)}</td>
                    <td class="table-text">${match.score.getSetScore().getCurrentScore().getPlayerScore(0).toString()}</td>

                    <td class="table-text">
                        <form action="${pageContext.request.contextPath}/match-score" method="POST">
                            <input type="hidden" name="uuid" value="${uuid}">
                            <input type="hidden" name="winner" value="playerOne">
                            <button class="player-btn" type="submit"> Win Point</button>
                        </form>
                    </td>
                </tr>

                <tr>
                    <th class="text-vs">vs.</th>
                </tr>

                <tr class="player2">
                    <td class="table-text">${match.player2.name}</td>
                    <td class="table-text">${match.score.getPlayerScore(1)}</td>
                    <td class="table-text">${match.score.getSetScore().getPlayerScore(1)}</td>
                    <td class="table-text">${match.score.getSetScore().getCurrentScore().getPlayerScore(1).toString()}</td>

                    <td class="table-text">
                        <form action="${pageContext.request.contextPath}/match-score" method="POST">
                            <input type="hidden" name="uuid" value="${uuid}">
                            <input type="hidden" name="winner" value="playerTwo">
                            <button class="player-btn" type="submit">Win Point</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>

    </div>
</div>
</body>
</html>