<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Welcome to Spookbook"/>
</jsp:include>
<div class="m-3">
    <c:choose>
        <c:when test="${not empty user}">
            <h3>Welcome ${user.firstName}!</h3>
            <form method="post" action="LogInServlet?command=logOut">
                <input type="submit" id="logout" value="Log Out">
            </form>
            <div id="statusDiv">


            </div>


            <label for="status">Status</label><select id="status" name="status">
            <option>Online</option>
            <option>Offline</option>
            <option>Away</option>
            <option>Busy</option>
            <option>Taking a class</option>


            <input type="button" id="changeStatus" value="Change status">

            <h3>Friends</h3>
            <table class="table">
                <thead>
                <tr class="bg-primary">
                    <th>Friend</th>
                    <th>Status</th>
                    <th>Chat</th>
                </tr>
                </thead>
                <tbody id="friends"></tbody>
            </table>

            <label for="potentialFriends">Add Friend</label>
            <select id="potentialFriends" name="potentialFriends"></select>

            <input type="button" id="addFriend" value="Add Friend">


        </select>

        </c:when>
        <c:otherwise>
            <form method="post" action="LogInServlet?command=logIn">
                <input type="text" id="userId" class="fadeIn second" name="userId" placeholder="userId">
                <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
                <input type="submit" class="fadeIn fourth" value="Log In">
            </form>
        </c:otherwise>
    </c:choose>
</div>
<br>
<br>
<div id="chatHeader"></div>
<br>
<br>
<div id="chatMessages"></div>
<br>
<br>
<div id="chatBox"></div>


<br>
<br>
<br>



<script type="text/javascript" src="js/status.js"></script>
<script type="text/javascript" src="js/friends.js"></script>
</body>
</html>
