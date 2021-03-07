window.onload = getGroups;

let x = new XMLHttpRequest();

function getGroups () {
    x.open("GET", "GroupServlet?command=all", true);
    x.onreadystatechange = showGroups;
    x.send();
}

function showGroups() {
    if (x.readyState === 4) {
        if (x.status === 200) {
            let groups = JSON.parse(x.responseText)

            var tbody = document.getElementById("groups");

            var tr = tbody.childNodes[0];
            if (tr == null) {
                createTable(groups);
            } else {
                removeAllChildNodes(tbody);
                createTable(groups);
            }

            function createTable(groups) {
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
    addGroupRequest.open("POST", "GroupServlet?", true);
    addGroupRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    addGroupRequest.send(information);
    getGroups();
}


// individuele opdracht Lennert Van Oosterwyck
let filterButton = document.getElementById("filterByMax")
filterButton.onclick = filterByMax;

function filterByMax() {
    let filterNumber = document.getElementById("filterByMaxNumberOfPlayers").value;
    x.open("Get", "GroupServlet?command=filterByMaxNumberOfPlayers&max=" + filterNumber.toString(), true)
    x.onreadystatechange = showGroups
    x.send()
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

let searchButton = document.getElementById("searchGroupButton");
searchButton.onclick = searchGroup;

function searchGroup(){
    let searchedGroup = document.getElementById("search").value;
    x.open("GET", "GroupServlet?command=searchGroup&searchedGroup="+searchedGroup, true);
    x.onreadystatechange = showGroups;
    x.send();
}




