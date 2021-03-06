<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Groups using fetch</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Groups using fetch"/>
</jsp:include>
<div class="m-3">
    <c:if test="${not empty user}">
        <label for="newgroup2">New group</label><input type="text" id="newgroup2"><input type="button" id="newGroupButton2" value="Add group">
    </c:if>
    <br>
    <input type="text" id="search2" placeholder="Search..."><input type="button" id="searchGroupButton2" value="Search group">
    <br>
    <table class="table table-striped" id="groups_table">
        <thead>
        <tr class="bg-primary">
            <th onclick="sortColumn('name')">Name</th>
            <th onclick="sortColumn('users')">Number of users</th>
            <th>Chat</th>
            <th>Show info</th>
        </tr>
        </thead>
        <tbody id="groups2"></tbody>
    </table>
</div>
<div class="m-3">
    <div id="chatHeader"></div>
    <br>
    <br>
    <div id="chatMessages"></div>
    <br>
    <br>
    <div id="chatBox"></div>
</div>
<script type="text/javascript" src ="js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="js/groups2.js"></script>
</body>
</html>
