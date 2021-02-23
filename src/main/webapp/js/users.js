window.onload = showUsers;




let userbutton = document.getElementById('userbutton');
userbutton.onclick = addUser;

let showUserRequest = new XMLHttpRequest();
let addUserRequest = new XMLHttpRequest();
function  addUser() {
    addUserRequest.open("GET", "UserServlet", true);
    addUserRequest.onreadystatechange = showUsers;

    addUserRequest.send();
}


function showQuotes() {
    if(addUserRequest.readyState === 4){
        if(addUserRequest.status === 200) {
            let person = JSON.parse(addUserRequest.responseText);
            let
        }
    }
}