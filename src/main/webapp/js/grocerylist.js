var ws;
window.onload = openSocket

function openSocket() {
    ws = new WebSocket("ws://localhost:8080/groceries")

    ws.onmessage = function (event) {
        writeResponse(event.data);
    }
}

function writeResponse(text){
    document.getElementById("messages").innerHTML += text + "<br/>"
}

let addButton = document.getElementById("addGroceryButton");
addButton.onclick = addGroceryList;

function addGroceryList(){
    var familyMember = document.getElementById("familyMember").value;
    var item = document.getElementById("item").value;
    var amount = document.getElementById("amount").value;
    var tekst = "Family member: "+familyMember+", item: " + item+", amount: "+amount;
    ws.send(tekst)
}
