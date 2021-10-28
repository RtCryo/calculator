"use strict";

$( document ).ready(function() {
    $.getJSON('/navbarRequest', function() {
        $(location).prop('href', '/calc');
    });
    $('#userNameNavbar').text("Hello, Anon");
});

function alertMessage(message, type) {
    let wrapper = document.createElement('div');
    wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert">' + message + '</div>';
    $("#liveAlertPlaceholder").append(wrapper).delay(4000).slideUp(200);
}