let alertPlaceholder = document.getElementById('liveAlertPlaceholder')

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

function failAuth(serverData){
    $.getJSON('/fail', function(){
        console.log(serverData);
    })
}

function alert(message, type) {
    let wrapper = document.createElement('div')
    wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert">' + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'

    alertPlaceholder.append(wrapper)
}