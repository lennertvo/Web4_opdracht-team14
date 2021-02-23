window.onload = getGroups;

var x = new XMLHttpRequest();

function getGroups () {
    x.open("GET", "GroupServlet");
    x.onreadystatechange = showGroups;
    x.send();
}

function showGroups () {
    if (x.readyState == 4) {
        var group = JSON.parse(x.responseText);
        var div = document.getElementById("groups")
        var p = document.createElement('p');
        var name = document.createTextNode(group.name);
        p.appendChild(name);
        div.appendChild(p);
    }
}