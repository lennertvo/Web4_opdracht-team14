$(document).ready(
    function getFriends(){

        $.get("UserServlet?command=getFriends", function (data) {
            $("#friends").empty();

            for (var i = 0; i < data.length; i++) {

                let newTr = $('<tr />');
                let newTd = $('<td id="friendName" />').text(data[i].firstName + " " + data[i].lastName);
                let nTd = $('<td />').text(data[i].status);
                let chatButton = document.createElement("BUTTON");
                chatButton.id = data[i].userid;
                var id = data[i].userid;
                chatButton.innerHTML = "Chat"
                chatButton.onclick = openChat;
                //chatButton.onclick = getMessages

                let div = document.getElementById("chat")


                function openChat() {
                    //$("#chatBox").empty()
                    //setTimeout(openChat, 5000);
                    //getMessages





                    let newInput = document.createElement("input")
                    newInput.type = "text"
                    newInput.placeholder = "Enter message here"
                    let b = document.createElement("BUTTON")
                    b.id = "sendMessage"
                    b.innerText = "Send"
                    let c = document.createElement("BUTTON")
                    c.innerText = "close"


                    $("#chatBox").append(newInput, b, c)


                    b.onclick = sendMessage;
                    c.onclick = closeChat;

                    function sendMessage(){
                        var aSound = document.createElement('audio');
                        aSound.setAttribute('src', '/audio/beep.wav');
                        aSound.play();
                       // $.post("UserServlet?command=addMessage&message=" + newInput.textContent)
                        let information = "message=" + encodeURIComponent(newInput.value) ;
                        fetch("UserServlet?command=addMessage&user2id="+id, {
                            method: "POST",
                            headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}),
                            body: information})


                    }


                    function closeChat() {
                        $("#chatBox").empty()
                    }

                }
                $(document).ready(function (){

                })
                $.ajax({
                    type: "Get",
                    url: "UserServlet?command=getMessagesBetweenFriends&user2id=" + encodeURIComponent(id),

                    dataType: "json",
                    success: function (json){
                        for(let y =  0; y < json.length; y++) {
                            let a = document.createElement("a");
                            let br = document.createElement("br")
                            a.innerText = json[y];
                            $("#chatBody").append(a, br)


                        }



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
        body: information})


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


