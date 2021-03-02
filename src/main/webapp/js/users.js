window.onload = getUsers;





function getUsers(){
    newUsersRequest.open("GET", "UserServlet", true);
    newUsersRequest.onreadystatechange = showUsers;
    newUsersRequest.send();
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
    let information = "userid=" + encodeURIComponent(userid) + "&firstName=" + encodeURIComponent(firstname) +
        "&lastName=" + encodeURIComponent(lastname) + "&password=" + encodeURIComponent(password) +
        "&email=" + encodeURIComponent(email) + "&phoneNumber=" + encodeURIComponent(phonenumber) +
        "&dateOfBirth=" + encodeURIComponent(dateofbirth);


    newUsersRequest.open("POST", "UserServlet", true);
    newUsersRequest.setRequestHeader('Content-Type', 'Application/x-www-form-urlencoded');
    newUsersRequest.send(information);
    getUsers();

}







function showUsers() {
    if(newUsersRequest.readyState === 4) {
        if (newUsersRequest.status === 200) {
            let users = JSON.parse(newUsersRequest.responseText);
            let tbody = document.getElementById("users")

            let tr = tbody.childNodes[0];
            if (tr == null) {
                createTable();
            } else {
                removeAllChildNodes(tbody)
                createTable()
            }



            function createTable() {
                for (let i = 0; i < users.length; i++) {
                    let tr1 = document.createElement('tr');
                    let td1 = document.createElement('td');
                    let td2 = document.createElement('td')
                    let firstname = document.createElement(users[i].firstName);
                    let lastname = document.createElement(users[i].lastName);
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