$(document).ready(
    function getYourGroups(){
        $("#chatMessages").empty();
        $.get("GroupServlet?command=all", function (data){
            $("#groups2").empty();

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
            if(i === groups.length - 1){
                tr1.id = "new"
            }

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
            var id = groups[i].name;
            chatButton.onclick = openChat;

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

            // Deelopdracht 2b individueel - Ruben Bottu r0789520 - Kleur chat veranderen
            function createColorButton(color, hexColorCode) {
                return $('<button/>')
                    .text('Make ' + color)
                    .click(() => $('#chatMessages').css('background-color', hexColorCode));
            }
            //end

            function openChat(){
                // Deelopdracht 2b individueel - Ruben Bottu r0789520 - Kleur chat veranderen
                const blueButton = createColorButton('blue', '#6EE0FF');
                const redButton = createColorButton('red', '#FF5353');
                const yellowButton = createColorButton('yellow', '#FFFF00');
                const greenButton = createColorButton('green', '#81FF77');
                //end

                let newInput = document.createElement("input")
                newInput.type = "text"
                newInput.placeholder = "Enter message here"
                let b = document.createElement("BUTTON")
                b.id = "sendMessage"
                b.innerText = "Send"

                let c = document.createElement("BUTTON")
                c.innerText = "close"

                $("#chatBox").append(newInput, b, c)
                b.onclick = sendMessage
                c.onclick = closeChat
                let h = document.createElement("H3")
                h.innerText = "Chat with " + id
                $("#chatHeader").append(h, blueButton, redButton, yellowButton, greenButton);

                function sendMessage(){
                    var aSound = document.createElement('audio');
                    aSound.setAttribute('src', '/audio/beep.wav');
                    aSound.play();
                    // $.post("UserServlet?command=addMessage&message=" + newInput.textContent)
                    let information = "message=" + encodeURIComponent(newInput.value) ;
                    fetch("GroupServlet?command=addMessage&groupName="+id, {
                        method: "POST",
                        headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}),
                        body: information})
                    getMessages()

                }

                function closeChat() {
                    $("#chatBox").empty();
                    $("#chatMessages").empty();
                    $("#chatHeader").empty()
                }

                function getMessages(){
                    $.ajax({
                        type: "Get",
                        url: "GroupServlet?command=getGroupMessages&groupId=" + encodeURIComponent(id),

                        //dataType: "JSON"
                        success: function (datajson){
                            $("#chatMessages").empty()
                            for(var y = 0; y < datajson.length; y++ ){
                                let $newP = $('<p />').text(datajson[y]);
                                let newBr = $('<br />');
                                $("#chatMessages").append($newP);
                            }
                        },
                        error: function (json){
                            alert("oke this fails");

                        }
                    })
                }
            }
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
    setTimeout(getGroups,1000)
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

    $.get("GroupServlet?command=all", function (data){
        showGroups(data)
        // Individuele opdracht Daan Stallaert - R0720550
        $("#new").hide().fadeIn(1500)
        // end
    })
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








