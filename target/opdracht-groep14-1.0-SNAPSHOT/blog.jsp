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
<div class="m-3">
<div class="blockquote">
    <section id="lesson">
        <h2>Hoe was de les?</h2>
    </section>
    <section id="music">
        <h2>Welke muziek luister je?</h2>
    </section>
    <section id="random">
        <h2>Random?</h2>
    </section>
    <section id="feelings">
        <h2>Hoe voel je je?</h2>
    </section>
</div>

<label for="name">Name</label><input type="text" id="name" >

<label for="Comment">Comment</label><input type="text" id="comment">

<label for="topic">Topic</label><select id="topic" name="topic">
    <option value="lesson">Hoe was de les?</option>
    <option value="music">Welke muziek luister je?</option>
    <option value="random">Random?</option>
    <option value="feelings">Hoe voel je je?</option>
</select>

<label for="addComment"></label><input type="button" id="addComment" value="Add comment">

</div>
<script type="text/javascript" src="js/blog.js"></script>
</body>
</html>
