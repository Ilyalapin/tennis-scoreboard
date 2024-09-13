<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Completed Matches</title>
</head>

<body>
<div class='header3'>
    <div class='header-line'>
        <div class='tennis-text'>
            Tennis Scoreboard
        </div>

        <div class='nav'>
            <a class='nav-item' href="index.jsp">Home</a>
        </div>
    </div>

    <h1 class="completed-matches">Completed Matches</h1>

    <div class='container3'>
        <form action="${pageContext.request.contextPath}/matches" method="get">

            <form class="form-matches" method="get" action="/matches">
                <input class="input-filter"
                       placeholder="Find a match by player name..."
                       type="text" id="filter_by_player_name"
                       name="filter_by_player_name"
                       pattern="[\p{L} .]+" required
                       title="Player name cannot be empty or just spaces.">

                <a href="matches">
                    <button
                            type="button" class="btn-reset">
                        Reset search results
                    </button>
                </a>
            </form>

            <table class="table-matches">
                <tr>
                    <th>Player One</th>
                    <th>Player Two</th>
                </tr>

                <c:forEach var="match" items="${matches}">
                    <tr>
                        <td>
                                ${match.player1.name}
                            <c:choose>
                                <c:when test="${match.player1.name==match.winner.name}">
                                    <img src="images/3.png" height="20" width="20" alt="" style="margin-left: 5px;">
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                                ${match.player2.name}
                            <c:choose>
                                <c:when test="${match.player2.name==match.winner.name}">
                                    <img src="images/3.png" height="20" width="20" alt="" style="margin-left: 5px;">
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <%-- Навигация по страницам --%>
            <div class='pagination'>
                <c:if test="${currentPage > 1}">
                    <a href="?page=${currentPage - 1}&filter_by_player_name=${param.filter_by_player_name}">Back</a>
                </c:if>

                <c:forEach begin="1" end="${totalPages}" var="i">
                    <a href="?page=${i}&filter_by_player_name=${param.filter_by_player_name}"
                       style="${i == currentPage ? 'text-decoration: underline; font-weight: bold;' : ''}">
                            ${i}
                    </a>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="?page=${currentPage + 1}&filter_by_player_name=${param.filter_by_player_name}">Next</a>
                </c:if>
            </div>
    </div>
    </form>
</div>
</div>
</body>
</html>