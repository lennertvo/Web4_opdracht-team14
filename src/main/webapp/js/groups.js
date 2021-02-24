window.onload = getGroups;

var x = new XMLHttpRequest();

function getGroups () {
    x.open("GET", "GroupServlet");
    x.onreadystatechange = showGroups;
    x.send();
}

function showGroups () {
    if (x.readyState === 4) {
        var groups = JSON.parse(x.responseText);
        var tbody = document.getElementById("groups");
        for (var i = 0; i < groups.length; i++) {
            var tr = document.createElement('tr');
            var td1 = document.createElement('td');
            var td2 = document.createElement('td');
            var name = document.createTextNode(groups[i].name);
            var number = document.createTextNode(groups[i].users.length);
            td1.appendChild(name);
            td2.appendChild(number);
            tr.appendChild(td1);
            tr.appendChild(td2);
            tbody.appendChild(tr);
        }
    }
}