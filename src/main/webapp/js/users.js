window.onload = getUsers;
let x = new XMLHttpRequest();
function getUsers(){
    //fetch("UserServlet?").then(r => r.json()).then(data => showUsers(data));
    x.open("GET", "UserServlet", true);
    x.onreadystatechange = showUsers ;
    x.send();
}

function showUsers() {
    if (x.readyState === 4) {
        if (x.status === 200) {

            let users = JSON.parse(x.responseText)
            var tbody = document.getElementById("users")

            var tr = tbody.childNodes[0];
            if (tr == null) {
                createTable(users);
            } else {
                removeAllChildNodes(tbody)
                createTable(users)
            }

            function createTable(users) {
                for (var i = 0; i < users.length; i++) {
                    var tr1 = document.createElement('tr');
                    var td1 = document.createElement('td');
                    var td2 = document.createElement('td')
                    var firstname = document.createTextNode(users[i].firstName);
                    var lastname = document.createTextNode(users[i].lastName);
                    td1.appendChild(firstname);
                    td2.appendChild(lastname)
                    tr1.appendChild(td1)
                    tr1.appendChild(td2)
                    tbody.appendChild(tr1);
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

let userButton = document.getElementById('newUserButton');
userButton.onclick = addUser;

let newUsersRequest = new XMLHttpRequest();

function  addUser() {
    let userid = document.getElementById("userid").value;
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;
    let phonenumber = document.getElementById("phonenumber").value;
    let dateofbirth = document.getElementById("dateofbirth").value;
    let information = "userid=" + encodeURIComponent(userid) + "&firstname=" + encodeURIComponent(firstname) +
        "&lastname=" + encodeURIComponent(lastname) + "&password=" + encodeURIComponent(password) +
        "&email=" + encodeURIComponent(email) + "&phonenumber=" + encodeURIComponent(phonenumber) +
        "&dateofbirth=" + encodeURIComponent(dateofbirth);

    newUsersRequest.open("POST", "UserServlet", true);
    newUsersRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    newUsersRequest.send(information);
    getUsers();

}

// Deelopdracht 1a individueel - Ruben Bottu r0789520 - gebruiker zoeken op voornaam
let searchUserButton = document.getElementById("searchUserButton");
searchUserButton.onclick = searchUser;

function searchUser(){
    let firstName = document.getElementById("firstNameText").value;
    x.open("GET", "UserServlet?command=searchUser&firstName=" + encodeURIComponent(firstName), true);
    x.onreadystatechange = showUsers;
    x.send();
    // showUsers() ???
}
/*
function searchUser(){
    let firstName = document.getElementById("firstNameText").value;
    x.open("GET", "UserServlet?command=searchUser&firstName=" + encodeURIComponent(firstName), true);
    x.send();
    showUsers();
}*/
