"use strict";

$( document ).ready(function() {
    // $.getJSON('/navbarRequest', function() {
    //     $(location).prop('href', '/calc');
    // });
    $('#userNameNavbar').text("Hello, Anon");
});

function alertMessage(message, type) {
    $("#liveAlertPlaceholder").append('<div class="col-sm-3 alert alert-' + type + ' alert-dismissible" role="alert">' + message + '</div>').delay(4000).slideUp(200);
}