<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Groups</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Groups"/>
</jsp:include>
<div class="m-3">
    <c:if test="${not empty user}">
        <label for="newgroup">New group</label><input type="text" id="newgroup"><input type="button" id="newGroupButton" value="Add group">
    </c:if>
    <br>
    <input type="text" id="search" placeholder="Search..."><input type="button" id="searchGroupButton" value="Search group">
    <input type="number" id="filterByMaxNumberOfPlayers" placeholder="Filter"><input type="button" id="filterByMax" value="Filter by max number of players">



    <table class="table table-striped">
        <thead>
        <tr class="bg-primary">
            <th onclick="sortColumn('name')">Name</th>
            <th onclick="sortColumn('users')">Number of users</th>
            <c:if test="${not empty user}">
                <th>Join</th>
            </c:if>
        </tr>
        </thead>
        <tbody id="groups"></tbody>
    </table>
</div>
<script type="text/javascript" src="js/groups.js"></script>
</body>
</html>
