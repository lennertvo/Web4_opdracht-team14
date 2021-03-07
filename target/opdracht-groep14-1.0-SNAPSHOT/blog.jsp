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
<div class="blockquote" id="firstBlog">
    <h2>First blog</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab optio, possimus. Asperiores culpa dicta, incidunt inventore minus quae quam saepe!</p>
</div>
<div class="blockquote" id="secondBlog">
    <h2>Second blog</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab optio, possimus. Asperiores culpa dicta, incidunt inventore minus quae quam saepe!</p>
</div>
<div class="blockquote" id="thirdBlog">
    <h2>Third blog</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab optio, possimus. Asperiores culpa dicta, incidunt inventore minus quae quam saepe!</p>
</div>
<div class="blockquote" id="fourthBlog">
    <h2>Fourth blog</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab optio, possimus. Asperiores culpa dicta, incidunt inventore minus quae quam saepe!</p>
</div>
<label for="name">Name</label><input type="text" id="name" >
<label for="Comment">Comment</label><input type="text" id="comment">
<label for="topic">Topic</label><input type="text" id="topic">
<label for="addComment">Add Comment</label><input type="button" id="addComment">


<script type="text/javascript" src="js/blog.js"></script>
</body>
</html>
