var ws;
window.onload = openSocket

function openSocket() {
    ws = new WebSocket("ws://localhost:8080/groceries")

    ws.onmessage = function (event) {
        writeResponse(event.data);
    }
}

function writeResponse(text){
    var parts = text.split(" ");
    document.getElementById("groceryList").innerHTML += "<tr><td>" + parts[0] + "</td><td>" + parts[1] + "</td><td>" + parts[2] + "</td></tr>";
}

let addButton = document.getElementById("addGroceryButton");
addButton.onclick = addGroceryList;

function addGroceryList(){
    var familyMember = document.getElementById("familyMember").value;
    var item = document.getElementById("item").value;
    var amount = document.getElementById("amount").value;
    var tekst = familyMember + " " + item + " " + amount;

    ws.send(tekst)
}
