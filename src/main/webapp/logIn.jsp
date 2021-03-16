<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Log in "/>
</jsp:include>
<c:choose>
    <c:when test="${not empty user}">
        <h3>Welcome ${user.firstName}!</h3>
        <form method="post" action="LogInServlet?command=logOut">
            <input type="submit" id="logout" value="Log Out">
        </form>
    </c:when>
    <c:otherwise>
        <form method="post" action="LogInServlet?command=logIn">
            <input type="text" id="userId" class="fadeIn second" name="userId" placeholder="userId">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>
    </c:otherwise>
</c:choose>


<script type="text/javascript" src="js/users2.js"></script>
</body>
</html>
