<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav class="navbar navbar-expand-sm navbar-dark bg-primary">
        <img src="${pageContext.request.contextPath}/img/Logo.png" alt="Logo" class="rounded float-left" height="100">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a class="nav-link" href="logIn.jsp">Log in</a></li>
            <li class="nav-item"><a class="nav-link" href="index.jsp">Users</a></li>
            <li class="nav-item"><a class="nav-link" href="index2.jsp">Users using fetch</a></li>
            <li class="nav-item"><a class="nav-link" href="groups.jsp">Groups</a></li>
            <li class="nav-item"><a class="nav-link" href="groups2.jsp">Groups using fetch</a></li>
            <li class="nav-item"><a class="nav-link" href="blog.jsp">Blog</a></li>
            <li class="nav-item"><a class="nav-link" href="grocerylist.jsp">Grocery list</a></li>
        </ul>
        <h1>SPOOKBOOK</h1>
    </nav>
    <h2>${param.title}</h2>
</header>
