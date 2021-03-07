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

    <h2>Add user</h2>
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
        <label for="dateofbirth">Date Of Birth</label>
        <input type="date" id="dateofbirth" name="dateofbirth"/>
        <input type="button" id="newUserButton" value="Add user"/>
    </form>

    <!-- Deelopdracht 1a individueel - Ruben Bottu r0789520 - gebruiker zoeken op voornaam -->
    <h2>Search user</h2>
    <form>
        <label for="firstNameText">First Name</label>
        <input type="text" id="firstNameText" required maxlength="100">
        <input type="button" id="searchUserButton" value="Search user"/>
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

<script type="text/javascript" src="js/users.js"></script>
</body>
</html>


