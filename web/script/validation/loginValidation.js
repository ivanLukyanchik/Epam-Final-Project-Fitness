function checkLogin() {
    var input = document.getElementById("login");
    if(!/^[a-zA-Z][a-zA-Z\d-_.]{2,19}$/.test(input.value)) {
        var placeholderErrorText= input.getAttribute("title");
        input.setCustomValidity(placeholderErrorText);
    } else {
        input.setCustomValidity('');
    }
}

function checkPassword() {
    var inputPassword = document.getElementById("password");
    if (inputPassword.value==="" || (!/[\w\d-_.]{3,20}/.test(inputPassword.value))) {
        var passwordErrorText = inputPassword.getAttribute("title");
        inputPassword.setCustomValidity(passwordErrorText);
    }else {
        inputPassword.setCustomValidity('');
    }
}

function checkForLoginAnyData() {
    checkLogin();
    checkPassword();
}