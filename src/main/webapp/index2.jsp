<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Users"/>
</jsp:include>
<div class="m-3">
<form><label for="userid">User ID</label>
    <input type="text" id="userid"/>
    <label for="firstname">First Name</label>
    <input type="text" id="firstname"/>
    <label for="lastname">Last Name</label>
    <input type="text" id="lastname"/>
    <label for="password">Password</label>
    <input type="password" id="password"/>
    <label for="email">Email</label>
    <input type="email" id="email"/>
    <label for="phonenumber">Phone Number</label>
    <input type="tel" id="phonenumber"/>
    <input type="button" id="newUserButton" value="Add user"/>
</form>


<table class="table table-striped">
    <thead>
    <tr class="bg-primary">
        <th>Firstname </th>
        <th>Lastname</th>
    </tr>
    </thead>
    <tbody id="users"></tbody>
</table>
</div>
<script type="text/javascript" src ="js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="js/users2.js"></script>
</body>
</html>
