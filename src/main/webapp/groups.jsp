<%--
  Created by IntelliJ IDEA.
  User: daans
  Date: 23/02/2021
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
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
    <table class="container-sm">
        <thead>
            <tr>
                <th>Name</th>
                <th>Number of users</th>
            </tr>
        </thead>
        <tbody id="groups"></tbody>
    </table>
<script type="text/javascript" src="js/groups.js"></script>
</body>
</html>
