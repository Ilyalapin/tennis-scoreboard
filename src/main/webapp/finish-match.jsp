<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <td class="table-text">
                        ${match.player1.name}
                            <c:choose>
                            <c:when test="${match.player1.name==match.winner.name}">
                                <img src="images/3.png" height="40" width="40" alt="" alt="">
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                      </c:choose>
                    </td>
                    <td class="table-text">0</td>
                    <td class="table-text">0</td>
                    <td class="table-text">0</td>
                </tr>

                <tr>
                    <th class="text-vs">vs.</th>
                </tr>

                <tr class="player2">
                    <td class="table-text">
                        ${match.player2.name}
                        <c:choose>
                        <c:when test="${match.player2.name==match.winner.name}">
                            <img src="images/3.png" height="40" width="40" alt="" alt="">
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                            </c:choose>
                    </td>
                    <td class="table-text">0</td>
                    <td class="table-text">0</td>
                    <td class="table-text">0</td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
    <div class="btn-completed-matches">
        <a href="matches">
            <button class="btn completed matches">
                Completed matches
            </button>
        </a>
    </div>
</div>
</body>
</html>