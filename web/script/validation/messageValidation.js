function checkComment() {
    var messageInput = document.getElementById("message");
    if (messageInput.value==="" || (!/[A-Za-zА-Яа-я0-9][A-Za-zА-Яа-я,.'!?()\s0-9]{1,299}/.test(messageInput.value))) {
        var messageTitle = messageInput.getAttribute("title");
        messageInput.setCustomValidity(messageTitle);
    } else {
        messageInput.setCustomValidity('');
    }
}