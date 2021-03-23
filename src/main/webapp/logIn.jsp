<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Welcome to Spookbook"/>
</jsp:include>
<div class="m-3">
<c:choose>
    <c:when test="${not empty user}">
        <h3>Welcome ${user.firstName}!</h3>
        <form method="post" action="LogInServlet?command=logOut">
            <input type="submit" id="logout" value="Log Out">
        </form>
        <div id="statusDiv">



        </div>



        <label for="status">Status</label><select id="status" name="status">
        <option>Online</option>
        <option>Offline</option>
        <option>Away</option>
        <option>Busy</option>
        <option>Taking a class</option>


        <input type="button" id="changeStatus" value="Change status">


    </select>
    </c:when>
    <c:otherwise>
        <form method="post" action="LogInServlet?command=logIn">
            <input type="text" id="userId" class="fadeIn second" name="userId" placeholder="userId">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>
    </c:otherwise>
</c:choose>
</div>

<script>

    window.onload = getStatus;

    function getStatus(){
        console.log("bruh")
        fetch("UserServlet?command=getStatus").then(r=>r.json()).then(data => showStatus(data))
    }

    function showStatus(data) {
        let sdiv = document.getElementById("statusDiv")

        let tr = sdiv.childNodes[0];
        if(tr == null) {
            createStatus(data)
        }else{
            removeStatus(sdiv)
            createStatus(data)
        }

        setTimeout(getStatus, 100);


        function createStatus(data){
            let h4 = document.createElement("h4");
            let text = document.createTextNode("Your status is:" + data )

            console.log(text)
            h4.appendChild(text)
            sdiv.appendChild(h4)

        }

        function removeStatus(parent) {
            while(parent.firstChild) {
                parent.removeChild(parent.firstChild);
            }

        }

    }





    let button = document.getElementById("changeStatus");
    button.onclick = changeStatus;


    function changeStatus() {
        let status = document.getElementById("status").value;
        let information = "status=" + encodeURIComponent(status);

        fetch("UserServlet?command=changeStatus", {
            method: "POST",
            headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}),
            body: information
        }

        )

    }
</script>
</body>
</html>
