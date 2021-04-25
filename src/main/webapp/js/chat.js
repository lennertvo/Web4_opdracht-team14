var ws;
window.onload = openSocket;
let sendButton = document.getElementById("sendButton");
sendButton.onclick = sendMessage;

function openSocket(){
    ws = new WebSocket("ws://localhost:8080/ChatServer")

    ws.onopen = function (event){
        writeResponse("Joined the chatroom!");
    }

    ws.onmessage = function (event) {
        writeResponse(event.data);
    }

    ws.onclose = function(event){
        writeResponse("Left the chat room");
    }
}


function sendMessage(){
    var message = document.getElementById("message").value
    ws.send(message)
    //fetch("ChatServer?command=getUser").then(r => r.json()).then(data => ws.send(data+":"+message));
}

function writeResponse(text) {
    document.getElementById("chatBody").innerHTML += "</br><label>"+text+ "</label>";
}

