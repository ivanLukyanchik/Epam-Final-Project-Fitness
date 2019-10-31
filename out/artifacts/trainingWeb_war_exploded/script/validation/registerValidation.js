function checkForRegistrationAnyData() {
    var loginRegisterInput = document.getElementById("login");
    if (loginRegisterInput.value === "" || (!/^[a-zA-Z][\w\d-_.]{2,19}$/.test(loginRegisterInput.value))) {
        var loginRegisterTitle = loginRegisterInput.getAttribute("title");
        loginRegisterInput.setCustomValidity(loginRegisterTitle);
    } else {
        loginRegisterInput.setCustomValidity('');
    }

    var passwordRegisterInput = document.getElementById("password");
    if (passwordRegisterInput.value === "" || (!/[\w\d-_.]{3,19}/.test(passwordRegisterInput.value))) {
        var passwordRegisterTitle = passwordRegisterInput.getAttribute("title");
        passwordRegisterInput.setCustomValidity(passwordRegisterTitle);
    } else {
        passwordRegisterInput.setCustomValidity('');
    }

    var emailRegisterInput = document.getElementById("email");
    var emailRegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (emailRegisterInput.value === "" || (!emailRegExp.test(emailRegisterInput.value))) {
        var emailRegisterTitle = emailRegisterInput.getAttribute("title");
        emailRegisterInput.setCustomValidity(emailRegisterTitle);
    } else {
        emailRegisterInput.setCustomValidity('');
    }
}

function checkRegisterPassword() {
    var passwordRegisterInput = document.getElementById("password");
    if (passwordRegisterInput.value === "" || (!/[\w\d-_.]{3,19}/.test(passwordRegisterInput.value))) {
        var passwordRegisterTitle = passwordRegisterInput.getAttribute("title");
        passwordRegisterInput.setCustomValidity(passwordRegisterTitle);
    } else {
        passwordRegisterInput.setCustomValidity('');
    }
}

function checkRegisterLoginEmail() {
    var loginRegisterInput = document.getElementById("login");
    if (loginRegisterInput.value === "" || (!/^[a-zA-Z][\w\d-_.]{2,19}$/.test(loginRegisterInput.value))) {
        var loginRegisterTitle = loginRegisterInput.getAttribute("title");
        loginRegisterInput.setCustomValidity(loginRegisterTitle);
    } else {
        loginRegisterInput.setCustomValidity('');
    }

    var emailRegisterInput = document.getElementById("email");
    var emailRegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (emailRegisterInput.value === "" || (!emailRegExp.test(emailRegisterInput.value))) {
        var emailRegisterTitle = emailRegisterInput.getAttribute("title");
        emailRegisterInput.setCustomValidity(emailRegisterTitle);
    } else {
        emailRegisterInput.setCustomValidity('');
    }
}