"use strict";

let input;
let scoreBig;
let scoreSmall;
let expression = {
    firstVar: undefined,
    secondVar: undefined,
    operation: undefined,
    result: undefined,
    expressionList: "",
    lastButton: undefined,
};
let listSection;
let stompClient;

$(function(){
    $(".enter").click(function() {
        if (expression.lastButton === "comma") {
            scoreBig.innerText = scoreBig.innerText.substr(0,scoreBig.innerText.length - 1);
            expression.lastButton = "cancel";
            return;
        }
        if (expression.lastButton === "cancel") return;
        if (expression.lastButton === "enter") {
            expression.expressionList = expression.firstVar + expression.operation + expression.secondVar;
            requestSubTotal(this);
        } else if (expression.operation !== undefined){
            expression.expressionList = scoreSmall.innerText + scoreBig.innerText;
            requestSubTotal(this);
        }
    });
});

$(function(){
    $(".cancel").click(function() {
        expression.firstVar = undefined;
        expression.secondVar = undefined;
        expression.operation = undefined;
        expression.result = undefined;
        expression.expressionList = "";
        expression.lastButton = undefined;
        scoreSmall.innerText = "";
        scoreBig.innerText = "0";
        expression.lastButton = "cancel";
    });
});

$(function(){
    $(".comma").click(function() {
        if (expression.lastButton === "enter") {
            expression.operation = undefined;
        }
        if (expression.lastButton === "operation" || expression.lastButton === "enter" || expression.lastButton === "error") scoreBig.innerText = 0;
        if (scoreBig.innerText.indexOf(",") > 0) return;
        scoreBig.innerText += ",";
        expression.lastButton = "comma";
    });
});

$(function(){
    $(".num").click(function() {
        if (expression.lastButton === "enter") {
            expression.operation = undefined;
            scoreBig.innerText = 0;
        }
        if(expression.lastButton === "operation" || expression.lastButton === "enter" || expression.lastButton === "error") scoreBig.innerText = 0;
        scoreBig.innerText === "0" ? scoreBig.innerText = this.innerText : scoreBig.innerText += this.innerText;
        expression.lastButton = "num";
    });
});

$(function(){
    $(".op").click(function() {
        if(expression.lastButton === "comma") return;
        if(expression.lastButton === undefined || expression.lastButton === "cancel") {
            expression.firstVar = scoreBig.innerText;
            scoreSmall.innerText = scoreBig.innerText + this.innerText;
            expression.operation = this.innerText;
            expression.lastButton = "operation";
            return;
        }
        if(expression.lastButton === "enter") {
            expression.operation = undefined;
            expression.lastButton = "num";
        }
        if(expression.lastButton === "num") {
            if (expression.operation === undefined) {
                expression.firstVar = scoreBig.innerText;
                expression.operation = this.innerText;
                scoreSmall.innerText = expression.firstVar + this.innerText;
                expression.lastButton = "operation";
            } else {
                requestSubTotal(this);
            }
        } else {
            scoreSmall.innerText = scoreSmall.innerText.substr(0, scoreSmall.innerText.length - 1) + this.innerText;
            expression.operation = this.innerText;
            expression.lastButton = "num";
        }
    });
});

function requestSubTotal(input) {
    if (expression.lastButton !== "enter") expression.secondVar = scoreBig.innerText;
    $.ajax ({
        type: "POST",
        data: {"firstVar":expression.firstVar.replace(",","."),
            "secondVar": expression.secondVar.replace(",","."),
            "operation": expression.operation},
        url: 'calc/subtotal',
        async: false,
        success:function(serverData) {
            $("#result").html(serverData);
            if (input.innerText === "E") {
                expression.result = serverData;
                requestSave();
            } else {
                scoreSmall.innerText += expression.secondVar;
                scoreSmall.innerText += input.innerText;
                expression.operation = input.innerText;
                expression.isPolynom = true;
                expression.firstVar = scoreBig.innerText;
                expression.lastButton = "operation"
            }
        },
        error: function (error) {
            expression.lastButton = "error";
            alert('error: ' + error.responseText);
        }
    });
}

function requestSave() {
    $.ajax ({
        type: "POST",
        data: expression,
        url: 'calc/expressions',
        async: false,
        success:function()
        {
            expression.firstVar = scoreBig.innerText;
            scoreSmall.innerText = "";
            expression.lastButton = "enter";
        },
        error: function (error) {
            expression.lastButton = "error";
            alert('error: ' + error.responseText);
        }
    });
}

function liElementCreate(obj_item) {
    if ($('#expressionList li').length > 10) $('#expressionList li').last().remove();
    const liElement = document.createElement("li");
    liElement.innerText = obj_item.expressionList + " = " + obj_item.result;
    liElement.setAttribute("data-id", obj_item.id);
    listSection.insertBefore(liElement, listSection.firstChild);
}

function liElementDelete(itemId) {
    $('li[data-id = ' +  itemId + ' ]').remove();
}

function requestListExpressions(){
    $.getJSON('calc/expressions', function(serverData){
        $('#expressionList li').remove();
        if (serverData.length > 0) {
            for(let p = serverData.length - 1; p >= 0; p--) {
                liElementCreate(serverData[p]);
            }
        }
    });
}

function onError(error) {
    console.log(error);
}

function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);
}

function onMessageReceived(message) {
    let expressionMessage = JSON.parse(message.body);
    if (expressionMessage.type === "ADD") liElementCreate(expressionMessage.expression);
    if (expressionMessage.type === "DELETE") liElementDelete(expressionMessage.expression.id);
    if (expressionMessage.type === "REFRESH") requestListExpressions();
}

$( document ).ready(function() {
    input = document.querySelectorAll('button.item');
    scoreBig = document.querySelector('.item.score.big');
    scoreSmall = document.querySelector('.item.score.small');
    listSection = document.querySelector("#expressionList");
    requestListExpressions();
    let socket = new SockJS('ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
});