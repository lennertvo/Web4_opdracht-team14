$(document).ready(
    function getFriends(){
        $("#chatMessages").empty();
        $.get("UserServlet?command=getFriends", function (data) {
            $("#friends").empty();

            let np = $('<p \>').text("hello it's me again");
            //$("#chatMessages").append(np);

            for (let i = 0; i < data.length; i++) {

                let newTr = $('<tr />');
                let newTd = $('<td id="friendName" />').text(data[i].firstName + " " + data[i].lastName);
                let nTd = $('<td />').text(data[i].status);
                let chatButton = document.createElement("BUTTON");
                chatButton.id = data[i].userid;
                let id = data[i].userid;
                let firstname = data[i].firstName;
                let lastname = data[i].lastName;
                chatButton.innerHTML = "Chat";
                chatButton.onclick = openChat;


                let div = document.getElementById("chat")

                // Deelopdracht 2b individueel - Ruben Bottu r0789520 - Kleur chat veranderen
                function createColorButton(color, hexColorCode) {
                    return $('<button/>')
                        .text('Make ' + color)
                        .click(() => $('#chatMessages').css('background-color', hexColorCode));
                }
                // einde individueel Ruben Bottu

                function openChat() {
                    // Deelopdracht 2b individueel - Ruben Bottu r0789520 - Kleur chat veranderen
                    const blueButton = createColorButton('blue', '#6EE0FF');
                    const redButton = createColorButton('red', '#FF5353');
                    const yellowButton = createColorButton('yellow', '#FFFF00');
                    const greenButton = createColorButton('green', '#81FF77');
                    // einde individueel Ruben Bottu

                    let p = document.createElement("p");
                    p.innerText = "heLLO MY DEAR"

                    let newInput = document.createElement("input")
                    newInput.type = "text"
                    newInput.placeholder = "Enter message here"
                    let b = document.createElement("BUTTON")
                    b.id = "sendMessage"
                    b.innerText = "Send"

                    let c = document.createElement("BUTTON")
                    c.innerText = "close"

                    $("#chatBox").append(newInput, b, c)
                    b.onclick = sendMessage
                    c.onclick = closeChat
                    let h = document.createElement("H3")
                    h.innerText = "Chat with " + firstname + " " + lastname
                    $("#chatHeader").append(h, blueButton, redButton, yellowButton, greenButton);

                    function sendMessage(){
                        var aSound = document.createElement('audio');
                        aSound.setAttribute('src', '/audio/beep.wav');
                        aSound.play();
                       // $.post("UserServlet?command=addMessage&message=" + newInput.textContent)
                        let information = "message=" + encodeURIComponent(newInput.value) ;
                        fetch("UserServlet?command=addMessage&user2id="+encodeURIComponent(id), {
                            method: "POST",
                            headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}),
                            body: information})
                    }
                    function closeChat() {
                        $("#chatBox").empty();
                        $("#chatMessages").empty();
                        $("#chatHeader").empty()
                    }
                }
                    $.ajax({
                        type: "Get",
                        url: "UserServlet?command=getMessagesBetweenFriends&user2id=" + encodeURIComponent(id),

                        //dataType: "JSON",

                        success: function (datajson){
                        $("#chatMessages").empty()
                           for(var y = 0; y < datajson.length; y++ ){
                                let $newP = $('<p />').text(datajson[y]);
                                let newBr = $('<br />');
                                $("#chatMessages").append($newP);
                            }
                        },
                        error: function (json){
                            alert("oke this fails");

                        }
                    })

                newTr.append(newTd);
                newTr.append(nTd);
                newTr.append(chatButton);
                $('#friends').append(newTr);

            }
            setTimeout(getFriends, 5000)
        })

    }
)



$(document).ready(
    function getPotentialFriends() {
        $.get("UserServlet?command=getPotentialFriends", function (data) {
            $("#potentialFriends").empty()
            for(var i = 0; i < data.length; i++) {
                var newOption = $('<option />').text(data[i].firstName + " " + data[i].lastName)
                newOption.val(data[i].userid)
                $('#potentialFriends').append(newOption)

            }
            setTimeout(getPotentialFriends, 3000)
        })

    }

)

var addFriendButton = document.getElementById("addFriend")
addFriendButton.onclick = addFriend;


function addFriend() {
    let userid = document.getElementById("potentialFriends").value;
    let information = "userid=" + encodeURIComponent(userid);


    fetch("UserServlet?command=addFriend", {
        method: "POST",
        headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}),
        body: information});
    $("#chatMessages").empty();


}

/*
let chatbutton = document.getElementById("chat");
chatbutton.onclick = openChat;
*/



/*
$("#chat").click(
    function openChat() {
        let newInput = document.createElement("input")
        newInput.type = "text"
        newInput.placeholder = "Enter message here"
        let tr = document.createElement("tr");
        tr.innerText = "hello"
        //$("chatBox").append(newInput)
        $("chatBox").innerHTML += "hello it's me"

    }
)*/


