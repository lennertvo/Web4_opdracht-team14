$(document).ready(
    function getFriends(){

        $.get("UserServlet?command=getFriends", function (data) {
            $("#friends").empty();

            for (var i = 0; i < data.length; i++){

                var newTr = $('<tr />');
                var newTd = $('<td id="friendName" />').text(data[i].firstName + " " + data[i].lastName);
                var nTd = $('<td />').text(data[i].status);
                var chatButton = document.createElement("BUTTON");
                chatButton.innerHTML = "Chat"
                chatButton.id = data[i].firstName+"Chat";
                newTr.append(newTd);
                newTr.append(nTd);
                newTr.append(chatButton);
                $('#friends').append(newTr);

            }
            setTimeout(getFriends, 1000)
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

let addFriendButton = document.getElementById("addFriend")
addFriendButton.onclick = addFriend;


function addFriend() {
    let userid = document.getElementById("potentialFriends").value;
    let information = "userid=" + encodeURIComponent(userid);

    fetch("UserServlet?command=addFriend", {
        method: "POST",
        headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}),
        body: information})


}

$("#friendName").click(
    function openChat() {
        let newInput = document.createElement("input")
        newInput.type = "text"
        newInput.placeholder = "Enter message here"
        $("chatBox").append(newInput)

    }
)


