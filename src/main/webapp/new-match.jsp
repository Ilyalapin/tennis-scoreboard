<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>New Match</title>
</head>

<body>

<div class= 'header2'>
    <div class= 'header-line2'>
        <div class='tennis-text2'>
            Tennis Scoreboard
        </div>

        <div class='nav2'>
            <a class='nav-item2' href="index.jsp">Home</a>
        </div>
    </div>

    <h1 class="create-match">Create New Match</h1>

    <c:if test="${not empty error}">
    <p class="error-message">${error}</p>
    </c:if>

    <div class='container2'>
        <div class='center'>
            <form action="${pageContext.request.contextPath}/new-match" method="POST">

                <label class="label-player" for="playerOne">Player one</label>


                <input class="input-player" placeholder="Enter the name of the first player..."
                       type="text" id="playerOne"
                       name="playerOne" pattern="[\p{L} .]+" required
                       title="Player's name must contain only letters ">

                <label class="label-player" for="playerTwo">Player two</label>


                <input class="input-player" placeholder="Enter the name of the second player..."
                       type="text" id="playerTwo"
                       name="playerTwo" pattern="[\p{L} .]+" required
                       title="Player's name must contain only letters ">


                <input class="btn-player" type="submit" value="Create">
            </form>
        </div>
    </div>
</div>
</body>
</html>