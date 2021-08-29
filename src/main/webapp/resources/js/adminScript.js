let listSection;
let inputElement;
let inputDelButton;

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
                    {id: this.id, expressionList: this.getAttribute("expressionList"), result: this.getAttribute("result")});
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
        success:function() {
            location.reload();
        },
        error: function (error) {
            alert('error: ' + error.responseText);
        }
    });
}

function requestListExpressions(){
    $.getJSON('admin/expressionsList', function(serverData){
        if (serverData.length > 0) {
            serverData.forEach((obj_item) => {liElementCreate(obj_item)});
        }
        inputElement = document.querySelectorAll("input.chkbox");
    });
}

function liElementCreate(obj_item) {
    const liElement = document.createElement("div");
    const chkBtn = document.createElement("input");

    chkBtn.setAttribute("class", "chkBox");
    chkBtn.setAttribute("type", "checkbox");
    chkBtn.setAttribute("id", obj_item.id);
    chkBtn.setAttribute("expressionList", obj_item.expressionList);
    chkBtn.setAttribute("result", obj_item.result);

    liElement.appendChild(chkBtn);
    liElement.innerHTML += obj_item.expressionList + " = " + obj_item.result;

    listSection.appendChild(liElement);
}

$( document ).ready(function() {
    listSection = document.querySelector("#expressionList");
    inputDelButton = document.querySelector(".chkBoxSelectAll");
    requestListExpressions();
});