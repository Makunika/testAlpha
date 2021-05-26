let stompClient = null;

let username;
let token;
let isAuth = false;

function addMessage(message) {
    if (message.owner_username === username) {
        $("#messages").append(`
            <div class="media-body media-body-reverse">
                <p>${message.message}</p>
                <p class="meta"><time datetime="2018">${message.date}</time> | ${message.owner_username}</p>
            </div>
        `)
    } else {
        $("#messages").append(`
            <div class="media-body">
                <p>${message.message}</p>
                <p class="meta"><time datetime="2018">${message.date}</time> | ${message.owner_username}</p>
            </div>
        `)
    }
}

function connect() {
    const socket = new SockJS('ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/chat/messages', function (message) {
            addMessage(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function openChat() {
    $("#auth").hide();
    $("#chat").show();
    $("#username_chat").text(username);
    $.ajax({
        type: "GET",
        url: "api/messages",
        dataType: 'json',
        contentType: "application/json",
        success: function (data) {
            data.forEach(element => addMessage(element));
            connect();
        }
    })
}

function entry() {
    $.ajax({
        type: "POST",
        url: "api/auth",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({ 'username': $("#username_auth").val() }),
        success: function (data) {
            username = data.username;
            token = data.token;
            isAuth = true;
            openChat();
        }
    })
}

function exit() {
    $.ajax({
        type: "DELETE",
        url: "api/auth",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({ 'token': token }),
        success: function (data) {
            disconnect();
            username = null;
            $("#auth").show();
            $("#chat").hide();
            isAuth = false;
            $("#messages").html(`
         <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;">
            <div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div>
         </div>
         <div class="ps-scrollbar-y-rail" style="top: 0px; height: 0px; right: 2px;">
             <div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 2px;"></div>
         </div>
    `)
        }
    })
}

function sendMessage() {
    stompClient.send("/app/api/messages", {}, JSON.stringify({
        'token': token,
        'message': $("#input_message").val()
    }));
}

$(function () {
    $("#chat").hide();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#btn_message" ).click(function() { sendMessage(); })
    $( "#exit" ).click(function() { exit(); });
    $( "#entry" ).click(function() { entry(); });
});