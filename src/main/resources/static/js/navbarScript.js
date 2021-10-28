"use strict";

let userNameNavbar;

$( document ).ready(function() {
    $.getJSON('/navbarRequest', function(serverData){
        userNameNavbar = serverData.username;
        viewUserNameOrRedirect();
    })
});

function viewUserNameOrRedirect() {
    if (userNameNavbar === null) {
        $(location).prop('href', '/calc');
    } else {
        $('#userNameNavbar').text("Hello, " + userNameNavbar);
    }
}