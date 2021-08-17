"use strict";

let input;
let scoreBig;
let scoreSmall;
let firstVar = undefined;
let secondVar = undefined;
let operation = undefined;
let clearBig = false;
let moreOp = false;
let xDefined = false;

function request(firstVarReq, secondVarReq, operation, input) {
    firstVarReq = firstVarReq.replace(",",".");
    secondVarReq = secondVarReq.replace(",",".");
    let data = {"x1":firstVarReq, "x2": secondVarReq, "op": operation};
    $.ajax ({
        type: "POST",
        data: data,
        url: 'calc',
        success:function(serverData)
        {
            $("#result").html(serverData.result);
            if (input === "E") {
                scoreSmall.innerText = "";
            } else {
                scoreSmall.innerText += secondVarReq;
                scoreSmall.innerText += input;
                moreOp = true;
            }
            secondVar = secondVarReq;
            firstVar = scoreBig.innerText;
        }
    });
}

function elementClean(){
    firstVar = secondVar = operation = undefined;
    scoreSmall.innerText = "";
    scoreBig.innerText = "0";
    clearBig = false;
    moreOp = false;
    xDefined = false;
}

function elementIsNumber(input) {
    if (xDefined) {elementClean();}
    if (clearBig) {scoreBig.innerText = ""; clearBig = false;}
    if (moreOp) {firstVar= scoreBig.innerText; scoreBig.innerText = "";}
    scoreBig.innerText === "0" ? scoreBig.innerText = input.innerText : scoreBig.innerText += input.innerText;
    moreOp = false;
    secondVar = undefined;
}

function elementNotNumber(input) {
    if(input.innerText === "C") {
        elementClean();
        return;
    }
    if(input.innerText === "E") {
        if (moreOp) {elementClean(); return;}
        if (firstVar !== undefined && operation !== undefined) {
            secondVar === undefined ? request(firstVar, scoreBig.innerText, operation, "E") : request(firstVar, secondVar, operation, "E");
            clearBig = true;
            xDefined = true;
        }
        return;
    }
    if(input.innerText === ",") {
        if (firstVar !== undefined && scoreBig.innerText.indexOf(",") > 0) scoreBig.innerText = 0;
        if (scoreBig.innerText.indexOf(",") > 0) return;
        if (xDefined) elementClean();
        scoreBig.innerText += ",";
        clearBig=false;
        return;
    }
    if (xDefined) {firstVar = scoreBig.innerText; secondVar = operation = undefined; xDefined = false;}
    if (operation === undefined) {
        clearBig = true;
        firstVar = scoreBig.innerText;
        scoreSmall.innerText = firstVar + input.innerText;
    } else if (firstVar !== undefined && moreOp === false) {
        if(clearBig) {scoreSmall.innerText = scoreSmall.innerText.substr(0, scoreSmall.innerText.length - 1) + input.innerText; operation = input.innerText; return;}
        request(firstVar, scoreBig.innerText, operation, input.innerText);
    } else {
        scoreSmall.innerText = scoreSmall.innerText.substr(0, scoreSmall.innerText.length - 1) + input.innerText;
    }
    operation = input.innerText;
}

function elementListner() {
    input = document.querySelectorAll('button.item');
    scoreBig = document.querySelector('.item.score.big');
    scoreSmall = document.querySelector('.item.score.small');
    for(let i=0;i<input.length;i++) {
        input[i].addEventListener('click', function () {
            isNaN(this.innerText) ? elementNotNumber(input[i]) : elementIsNumber(input[i]);
        });
    }
}

$( document ).ready(function() {
    elementListner();
});
