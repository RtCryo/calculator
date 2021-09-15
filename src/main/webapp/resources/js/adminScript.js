let listSection;
let inputDelButton;
let stompClient;

$(function(){
    $(".chkBoxSelectAll").click(function() {
        if($(".chkBoxSelectAll").prop("checked") === true) {
            $(".chkbox").each(function () {
                $(this).prop("checked", true);
            })
        } else {
            $(".chkbox").each(function () {
                $(this).prop("checked", false);
            })
        }
    })
});

$(function () {
    $(".buttonDel").click(function () {
        let listExpressionsDelete = [];
        $(".chkbox").each(function () {
            if (this.checked === true) {
                listExpressionsDelete.push(
                    {id: $(this).parent().attr("data-id"),
                        expressionList: $(this).parent().attr("expressionList"),
                        result: $(this).parent().attr("result")});
            }
        })
        sendListToDelete(listExpressionsDelete);
    })
})

function sendListToDelete(list) {
    $.ajax ({
        type: "POST",
        data: JSON.stringify(list),
        dataType: "json",
        url: 'admin/expressionsToDelete',
        contentType:"application/json",
        async: false,
        success:function() {
            $(".chkBoxSelectAll").prop("checked", false);
        },
        error: function (error) {
            alert('error: ' + error.responseText);
        }
    });
}

function requestListExpressions(){
    $('.expressionItem').remove();
    $.getJSON('admin/expressionsList', function(serverData){
        if (serverData.length > 0) {
            serverData.forEach((obj_item) => {liElementCreate(obj_item)});
        }
    });
}

function liElementCreate(objItem) {
    let liElement = document.createElement("div");
    liElement.setAttribute("class", "expressionItem")
    liElement.setAttribute("data-id", objItem.id);
    liElement.setAttribute("expressionList", objItem.expressionList);
    liElement.setAttribute("result", objItem.result);

    let chkBtn = document.createElement("input");
    chkBtn.setAttribute("class", "chkBox");
    chkBtn.setAttribute("type", "checkbox");

    liElement.appendChild(chkBtn);
    liElement.innerHTML += objItem.date + ": " + objItem.expressionList + " = " + objItem.result;

    listSection.appendChild(liElement);
}

function liElementDelete(itemId) {
    $('.expressionItem[data-id = ' + itemId + ']').remove();
}

function onError(error) {
    alert(error);
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
    listSection = document.querySelector("#expressionList");
    inputDelButton = document.querySelector(".chkBoxSelectAll");
    requestListExpressions();
    let socket = new SockJS('ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
});