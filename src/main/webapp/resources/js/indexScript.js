$( document ).ready(function() {
    requestUserName();
});

function requestUserName(){
    $.getJSON('/getUserName', function(){
        $(location).prop('href', '/calc');
    }).fail (function () {
        $('#userNameNavbar').text("Hello, Anon");
    })
}