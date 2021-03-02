window.onload = getGroups;


function getGroups() {
    fetch("GroupServlet?").then(r => r.json()).then(data => showGroups(data));

}


function showGroups(groups) {

    var tbody = document.getElementById("groups2")

    var tr = tbody.childNodes[0];
    if (tr == null) {
        createTable(groups);
    } else {
        removeAllChildNodes(tbody)
        createTable(groups)
    }

    function createTable(groups) {
        for (var i = 0; i < groups.length; i++) {
            var tr1 = document.createElement('tr');
            var td1 = document.createElement('td');
            var td2 = document.createElement('td')
            var name = document.createTextNode(groups[i].name);
            var numberOfUsers = document.createTextNode(groups[i].users.length);
            td1.appendChild(name);
            td2.appendChild(numberOfUsers)
            tr1.appendChild(td1)
            tr1.appendChild(td2)
            tbody.appendChild(tr1);
        }
    }

    function removeAllChildNodes(parent) {
        while (parent.firstChild) {
            parent.removeChild(parent.firstChild);
        }
    }

}

let addButton = document.getElementById("newGroupButton2");
addButton.onclick = addGroup;




function addGroup() {
    let groupName = document.getElementById("newgroup2").value;
    let information = "groupName=" + encodeURIComponent(groupName);


    fetch("GroupServlet?", {
        method: "POST",
        headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}),
        body: information})

    getGroups()





}





