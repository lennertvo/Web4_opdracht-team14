<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Grocery list</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Grocery list"/>
</jsp:include>

<label for="familyMember">Family member</label><select id="familyMember" name="familyMember">
    <option value="Daan">Daan</option>
    <option value="Brick">Brick</option>
    <option value="Lennert">Lennert</option>
    <option value="Ruben">Ruben</option>
</select>
<label for="item">Item</label><input type="text" id="item">
<label for="amount">Amount</label><input type="text" id="amount">
<input type="button" id="addGroceryButton" value="Add grocery">
<div id="messages"></div>
<table class="table table-striped">
    <thead>
    <tr class="bg-primary">
        <th>Family member</th>
        <th>Item</th>
        <th>Amount</th>
    </tr>
    </thead>
    <tbody id="groceryList"></tbody>
</table>
<script type="text/javascript" src="js/grocerylist.js"></script>
</body>
</html>
