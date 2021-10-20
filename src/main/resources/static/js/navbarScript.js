"use strict";

$( document ).ready(function() {
    requestUserName();
});

function requestUserName(){
    $.getJSON('/getUserName', function(serverData){
        if (serverData.length > 0) {
            $('#userNameNavbar').text("Hello, " + serverData);
        }
    })
}