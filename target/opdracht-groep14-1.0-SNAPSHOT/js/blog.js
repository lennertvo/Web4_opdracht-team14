window.onload = openSocket
var webSocket
var name = document.getElementById("name")
var comment = document.getElementById("comment")
var topic = document.getElementById("topic")

function openSocket() {
    webSocket = new WebSocket("ws://localhost:8080/comment")
}