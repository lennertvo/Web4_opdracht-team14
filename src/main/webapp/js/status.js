window.onload = getStatus;


function getStatus(){
    fetch("UserServlet?command=getStatus").then(r=>r.json()).then(data => showStatus(data))
}

function showStatus(data) {
    let sdiv = document.getElementById("statusDiv")

    let tr = sdiv.childNodes[0];
    if(tr == null) {
        createStatus(data)
    }else{
        removeStatus(sdiv)
        createStatus(data)
    }

    setTimeout(getStatus, 10000);


    function createStatus(data){
        let h4 = document.createElement("h4");
        let text = document.createTextNode("Your status is:" + data )

        console.log(text)
        h4.appendChild(text)
        sdiv.appendChild(h4)

    }

    function removeStatus(parent) {
        while(parent.firstChild) {
            parent.removeChild(parent.firstChild);
        }

    }

}







let button = document.getElementById("changeStatus");
button.onclick = changeStatus


function changeStatus() {
    let status = document.getElementById("status").value;
    let information = "status=" + encodeURIComponent(status);

    fetch("UserServlet?command=changeStatus", {
            method: "POST",
            headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}),
            body: information
        }

    )

}