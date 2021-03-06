
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
<label for="newgroup2">New group</label><input type="text" id="newgroup2"><input type="button" id="newGroupButton2" value="Add group">

<table class="table table-striped">
    <thead>
    <tr class="bg-primary">
        <th onclick="sortColumn('name')">Name</th>
        <th onclick="sortColumn('users')">Number of users</th>
    </tr>
    </thead>
    <tbody id="groups2"></tbody>
</table>
<script type="text/javascript" src="js/groups2.js"></script>
</body>
</html>
