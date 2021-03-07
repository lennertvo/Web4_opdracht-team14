var ws;
window.onload = openSocket

let addButton = document.getElementById("addGroceryButton");
addButton.onclick = addGroceryList;

function openSocket() {
    ws = new WebSocket("ws://localhost:8080/groceries")

    ws.onmessage = function (event) {
        writeResponse(event.data);
    }
}

function writeResponse(text){
    var parts = text.split(" ");
    var familyMember = parts[0]
    var item = parts[1]
    var amount = parts[2]
    document.getElementById("groceryList").innerHTML += "<tr id='" + familyMember + "'></tr>"
    document.getElementById(familyMember).innerHTML += "<td>" + familyMember + "</td><td>" + item + "</td><td>" + amount + "</td>";
}

function addGroceryList(){
    var familyMember = document.getElementById("familyMember").value;
    var item = document.getElementById("item").value;
    var amount = document.getElementById("amount").value;
    var tekst = familyMember + " " + item + " " + amount;

    ws.send(tekst)
}
