'use strict';

let stompClient;
let listSection;

$( document ).ready(function() {
    let socket = new SockJS('ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
    listSection = document.querySelector("#messages");
});

function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);
}

function liElementCreate(objItem) {
    const liElement = document.createElement("li");
    liElement.innerText = objItem.expressionList + " = " + objItem.result;
    liElement.setAttribute("data-id", objItem.id);
    listSection.appendChild(liElement);
}

function liElementDelete(itemId) {
    $('li[data-id = ' +  itemId + ' ]').remove();
}

function onMessageReceived(message) {
    let expressionMessage = JSON.parse(message.body);
    if (expressionMessage.type === "ADD") liElementCreate(expressionMessage.expression);
    if (expressionMessage.type === "DELETE") liElementDelete(expressionMessage.expression.id);
}

function onError(error) {
    alert(error);
}