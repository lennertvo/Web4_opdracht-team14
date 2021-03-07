var ws;
window.onload = openSocket;
let postButton = document.getElementById("addComment");
postButton.onclick = postComment;

function openSocket() {
    ws = new WebSocket("ws://localhost:8080/comment")

    ws.onopen = function (event){
        writeResponse("connection opened")
    }

    ws.onmessage = function (event) {
        writeResponse(event.data);
    }

    ws.onclose = function(event){
        writeResponse("connection closed")
    }

}
function postComment(){
    var name = document.getElementById("name").value
    var comment = document.getElementById("comment").value
    var topic = document.getElementById("topic").value
    

    ws.send(name+","+comment+","+topic);
}

function writeResponse(text){
    var parts = text.split(",")
    var name = parts[0]
    var comment = parts[1]
    var topic = parts[2]
    document.getElementById(topic).innerHTML += "<p>" + name + ": " + comment + "</p>"




}