window.onload = openSocket
var webSocket
let postButton = document.getElementById("addComment")
postButton.onclick = postComment

function openSocket() {
    webSocket = new WebSocket("ws://localhost:8080/comment")

    webSocket.onopen

    ws.onmessage = function (event) {
        writeResponse(event.data);
    }

}
function postComment(){
    var name = document.getElementById("name").value
    var comment = document.getElementById("comment").value
    var topic = document.getElementById("topic").value
    var tekst = "name="+name+"&comment="+comment+"&topic=" + topic
    webSocket.send(tekst)
}

function writeResponse(text){

}