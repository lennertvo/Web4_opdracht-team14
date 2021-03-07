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
    <label for="newgroup">New group</label><input type="text" id="newgroup"><input type="button" id="newGroupButton" value="Add group">
    <br>
    <input type="text" id="search" placeholder="Search..."><input type="button" id="searchGroupButton" value="Search group">
    <input type="number" id="filterByMaxNumberOfPlayers" placeholder="Filter"><input type="button" id="filterByMax" value="Filter by max number of players">

    <table class="table table-striped">
        <thead>
            <tr class="bg-primary">
                <th>Name</th>
                <th>Number of users</th>
            </tr>
        </thead>
        <tbody id="groups"></tbody>
    </table>
<script type="text/javascript" src="js/groups.js"></script>
</body>
</html>
