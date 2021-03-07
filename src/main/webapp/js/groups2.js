window.onload = getGroups;


function getGroups() {
    fetch("GroupServlet?command=all").then(r => r.json()).then(data => showGroups(data));
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
            var td2 = document.createElement('td');
            var name = document.createTextNode(groups[i].name);
            console.log(groups[i].users.length)
            var numberOfUsers = document.createTextNode(groups[i].users.length);
            var deleteButton = document.createElement('BUTTON');
            deleteButton.innerHTML = "Delete";
            td1.appendChild(name);
            td2.appendChild(numberOfUsers)
            tr1.appendChild(td1)
            tr1.appendChild(td2)
            tr1.appendChild(deleteButton)
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




// individuele functionaliteit van Daan Stallaert
let sortDirection = true;

function sortColumn(columnName){
    sortDirection = !sortDirection;

    if(columnName === "users"){
        fetch("GroupServlet?command=all").then(r => r.json()).then(data => sortNumberColumn(sortDirection, data));
    }
    else if(columnName === "name"){
        fetch("GroupServlet?command=all").then(r => r.json()).then(data => sortNameColumn(sortDirection, data));
    }
}

function sortNumberColumn(sort, data){
    let sortedData = data.sort((g1, g2) => {
        return sort ? g1.users.length - g2.users.length : g2.users.length - g1.users.length
    });
    showGroups(sortedData);
}


function sortNameColumn(sort, data){
    let sortedData = data.sort((g1, g2) => {
        return sort ? compareString(g1.name.toLowerCase(), g2.name.toLowerCase()) : compareString(g2.name.toLowerCase(), g1.name.toLowerCase())
    });

    function compareString(a, b){
        if (a > b) {
            return 1;
        }
        if (b > a) {
            return -1;
        }
        return 0;
    }
    showGroups(sortedData);
}


// individuele opdracht Brick van Roekel

let searchButton = document.getElementById("searchGroupButton2");
searchButton.onclick = searchGroup;

function searchGroup(){
    let searchValue = document.getElementById("search2").value;
    if(searchValue === ""){
        getGroups();
    }else {
        fetch("GroupServlet?command=all").then(r => r.json()).then(data => executeSearchGroup(data, searchValue));
    }
}
function executeSearchGroup(groups,groupname){
    for(let i=0; i!==groups.length; i++){
        if(groups[i].name === groupname){
            let searchedGroup = [groups[i]]
            showGroups(searchedGroup);
        }
    }
}








