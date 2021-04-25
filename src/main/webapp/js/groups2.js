$(document).ready(
    function getYourGroups(){
        $.get("GroupServlet?command=all", function (data){
            showGroups(data)
        })
    }
)
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
            //make row and columns for each group
            var tr1 = document.createElement('tr');
            tr1.className = "group_rows"

            var td1 = document.createElement('td');
            var td2 = document.createElement('td');
            var td3 = document.createElement('td');
            var td4 = document.createElement('td');
            var name = document.createTextNode(groups[i].name);
            var numberOfUsers = document.createTextNode(groups[i].users.length);

            //make chat and info buttons
            var chatButton = document.createElement("BUTTON");
            var infoButton = document.createElement("BUTTON");
            chatButton.innerHTML = "Chat"
            chatButton.id = groups[i].name;
            $(chatButton).on('click',function (){
                console.log(chatButton.id)
                $.ajax({
                    type: "POST",
                    url: "GroupServlet?command=putGroupMessages",
                    data: {"name":chatButton.id}
                }).done(
                    location.href = "http://localhost:8080/chat.jsp"
                )
            })
            infoButton.innerHTML = "Show info";
            infoButton.className = "show_info_button"
            infoButton.id = i;

            //add columns and row to table
            td1.appendChild(name)
            td2.appendChild(numberOfUsers)
            td3.appendChild(chatButton)
            td4.appendChild(infoButton)
            tr1.appendChild(td1)
            tr1.appendChild(td2)
            tr1.appendChild(td3)
            tr1.appendChild(td4)

            //Deelopdracht 2b Brick van Roekel
            var hiddenRow = document.createElement('tr');
            hiddenRow.id = i + "_info";
            hiddenRow.style.display = 'none';
            var infoTd = document.createElement('td');
            infoTd.innerHTML = groups[infoButton.id].name+": <br>"+"Number of users: "+groups[infoButton.id].users.length
            $(infoButton).on('click',createClickHandler(infoButton.id+"_info"));
            //end

            hiddenRow.appendChild(infoTd)
            tbody.appendChild(tr1);
            tbody.appendChild(hiddenRow);
        }
    }

    //Deelopdracht 2b Brick van Roekel
    function createClickHandler(rowId) {
        return function (){
            var elem = document.getElementById(rowId)
            $(elem).toggle("slow");
        }
    }
    //end

    function removeAllChildNodes(parent) {
        while (parent.firstChild) {
            parent.removeChild(parent.firstChild);
        }
    }

    setTimeout(getGroups,10000)
}

let addButton = document.getElementById("newGroupButton2");
addButton.onclick = addGroup;


function addGroup() {
    let groupName = document.getElementById("newgroup2").value;
    let information = "groupName=" + encodeURIComponent(groupName);

    fetch("GroupServlet?command=add", {
        method: "POST",
        headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}),
        body: information})
}


// individuele functionaliteit met fetch van Daan Stallaert
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


// individuele opdracht Brick van Roekel met fetch

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
//end individuele opdracht








