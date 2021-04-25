<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chat</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Chat"/>
</jsp:include>
<div class="m-3">
    <label for="message">Message: </label><input type="text" id="message">
    <input type="button" id="sendButton" value="Send">
    <div id="chatBody" class="m-3">
    </div>
</div>
<script type="text/javascript" src="js/chat.js"></script>
<script type="text/javascript" src ="js/jquery-3.6.0.js"></script>

</body>
</html>
