window.onload = getGroups;

function getGroups () {
    fetch("GroupServlet?").then(r => r.json()).then(data => showGroups(data));
}

function showGroups (groups) {
    var tbody = document.getElementById("groups");
    var tr = tbody.childNodes[0];
    if(tr == null) {
        createTable(groups);
    }
    else {
        removeAllChildNodes(tbody);
        createTable(groups);
    }

    function createTable(groups){
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

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

let addButton = document.getElementById("newGroupButton");
addButton.onclick = addGroup;

let addGroupRequest = new XMLHttpRequest();

function addGroup(){
    let groupName = document.getElementById("newgroup").value;
    let information = "groupName=" + encodeURIComponent(groupName);
    addGroupRequest.open("POST", "GroupServlet", true);
    addGroupRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    addGroupRequest.send(information);
    getGroups();
}