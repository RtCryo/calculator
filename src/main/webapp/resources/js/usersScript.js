let listSection;

$( document ).ready(function() {
    requestListUser();
    listSection = document.querySelector("#userslist");
});

function requestListUser(){
    $.getJSON('userslist/getlist', function(serverData){
        $("#userslist li").remove();
        if (serverData.length > 0) {
            serverData.forEach((obj_item) => liElementCreate(obj_item));
        }
    });
}

function liElementCreate(obj_item) {
    const liElement = document.createElement("li");
    liElement.innerText = obj_item.username;
    liElement.setAttribute("data-id", obj_item.id);
    listSection.appendChild(liElement);
}