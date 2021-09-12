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

function onMessageReceived(message) {
    let expressionMessage = JSON.parse(message.body);
    const liElement = document.createElement("li");
    liElement.innerText = expressionMessage.expression.expressionList + " = " + expressionMessage.expression.result;
    liElement.setAttribute("data-id", message.id);
    listSection.appendChild(liElement);
}

function onError(error) {
    alert(error);
}