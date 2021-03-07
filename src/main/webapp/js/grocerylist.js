var ws;

function openSocket() {
    ws = new WebSocket("ws://localhost:8080/groceries")

    ws.onmessage = function (event) {
        writeResponse(event.data);
    }
}

function writeResponse(){

}

let addButton = document.getElementById("addGroceryButton");
addButton.onclick = addGroceryList;

function addGroceryList(){
    var familyMember = document.getElementById("familyMember");
    var item = document.getElementById("item");
    var amount = document.getElementById("amount");
    var tekst = "familyMember="+familyMember+", item=" + item+", amount="+amount;
    webSocket.send(tekst)
}
