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


<label for="userid">User ID</label>
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
<label for="dateofbirth">Date Of Birth</label>
<input type="date" id="dateofbirth"/>
<input type="button" id="newUserButton" value="Add user"/>


<table class="table table-striped">
    <thead>
    <tr class="bg-primary">
        <th>Firstname </th>
        <th>Lastname</th>
    </tr>
    </thead>
    <tbody id="users"></tbody>
</table>


<script type="text/javascript" src="js/users.js"></script>
</body>
</html>


