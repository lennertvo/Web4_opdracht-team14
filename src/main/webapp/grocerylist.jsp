<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Blog"/>
</jsp:include>


<label for="familyMember">Family member</label><input type="text" id="familyMember">
<label for="grocery">Item</label><input type="text" id="grocery">
<label for="amount">Amount</label><input type="text" id="amount">
<label for="addGroceryButton">Add grocery</label><input type="button" id="addGroceryButton">

<table class="table table-striped">
    <thead>
    <tr class="bg-primary">
        <th>Family member</th>
        <th>Item</th>
        <th>Amount</th>
    </tr>
    </thead>
    <tbody id="groups"></tbody>
</table>
<script type="text/javascript" src="js/grocerylist.js"></script>
</body>
</html>
