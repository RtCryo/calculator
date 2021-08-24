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
    error: false,
};
let listSection;

$(function(){
    $(".enter").click(function() {
        if (expression.lastButton === "enter") {
            expression.expressionList = expression.firstVar + expression.operation + expression.secondVar;
        } else {
            expression.expressionList = scoreSmall.innerText + scoreBig.innerText;
        }
        requestSubTotal(this);
        if(!expression.error) return;
        if(expression.lastButton !== "enter") expression.secondVar = scoreBig.innerText;
        expression.lastButton = "enter";
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
        expression.error = false;
    });
});

$(function(){
    $(".comma").click(function() {
        if (expression.lastButton === "enter") expression.operation = undefined;
        if (expression.lastButton === "operation" || expression.lastButton === "enter") scoreBig.innerText = 0;
        if (scoreBig.innerText.indexOf(",") > 0) return;
        scoreBig.innerText += ",";
        expression.lastButton = "comma";
    });
});

$(function(){
    $(".num").click(function() {
        if (expression.lastButton === "enter") expression.operation = undefined;
        if(expression.lastButton === "operation" || expression.lastButton === "enter") scoreBig.innerText = 0;
        scoreBig.innerText === "0" ? scoreBig.innerText = this.innerText : scoreBig.innerText += this.innerText;
        expression.lastButton = "num";
    });
});

$(function(){
    $(".op").click(function() {
        if(expression.lastButton === "comma") return;
        if(expression.lastButton === undefined ||expression.lastButton === "cancel") {
            expression.firstVar = scoreBig.innerText;
            expression.lastButton = "num";
        }
        if(expression.lastButton === "enter") {
            expression.lastButton = "num";
            expression.operation = undefined;
        }
        if(expression.lastButton === "num") {
            if (expression.operation === undefined) {
                expression.firstVar = scoreBig.innerText;
                expression.operation = this.innerText;
                scoreSmall.innerText = expression.firstVar + this.innerText;
            } else {
                requestSubTotal(this);
            }
        } else {
            scoreSmall.innerText = scoreSmall.innerText.substr(0, scoreSmall.innerText.length - 1) + this.innerText;
            expression.operation = this.innerText;
        }
        expression.lastButton = "operation"
    });
});

function requestSubTotal(input) {
    if (expression.lastButton !== "enter") expression.secondVar = scoreBig.innerText;
    $.ajax ({
        type: "POST",
        data: {"firstVar":expression.firstVar.replace(",","."),
            "secondVar": expression.secondVar.replace(",","."),
            "operation": expression.operation},
        url: 'subtotal',
        success:function(serverData) {
            if (serverData === "error") {
                alert("Division by zero");
                expression.error = true;
                return;
            }
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
            }
        },
        error: function (error) {
            alert('error; ' + eval(error));
        }
    });
}

function requestSave() {
    $.ajax ({
        type: "POST",
        data: expression,
        url: 'expressions',
        success:function(serverData)
        {
            scoreSmall.innerText = "";
            expression.firstVar = scoreBig.innerText;
            liElementCreate(serverData);
        },
        error: function (error) {
            alert('error; ' + eval(error));
        }
    });
}

function liElementCreate(obj_item) {
    const liElement = document.createElement("li");
    liElement.innerText = obj_item.expressionList + " = " + obj_item.result;
    liElement.setAttribute("data-id", obj_item.id);
    listSection.appendChild(liElement);
}

function requestListExpressions(){
    $.getJSON('expressions', function(serverData){
        if (serverData.length > 0) {
            serverData.forEach((obj_item) => {liElementCreate(obj_item)});
        }
    });
}

$( document ).ready(function() {
    input = document.querySelectorAll('button.item');
    scoreBig = document.querySelector('.item.score.big');
    scoreSmall = document.querySelector('.item.score.small');
    listSection = document.querySelector("#expressionList");
    requestListExpressions();
});