function checkForRegistrationAnyData() {
    checkRegisterPassword();
    checkRegisterLoginEmail();
    checkName();
    checkSurname()
}

function checkName() {
    var nameRegisterInput = document.getElementById("name");
    if (nameRegisterInput.value==="" || (!/^[a-zA-Zа-яА-Я]{3,20}$/.test(nameRegisterInput.value))) {
        var nameRegisterTitle = nameRegisterInput.getAttribute("title");
        nameRegisterInput.setCustomValidity(nameRegisterTitle);
    } else {
        nameRegisterInput.setCustomValidity('');
    }
}

function checkSurname() {
    var surnameRegisterInput = document.getElementById("surname");
    if (surnameRegisterInput.value==="" || (!/^[a-zA-Zа-яА-Я]{3,20}$/.test(surnameRegisterInput.value))) {
        var surnameRegisterTitle = surnameRegisterInput.getAttribute("title");
        surnameRegisterInput.setCustomValidity(surnameRegisterTitle);
    } else {
        surnameRegisterInput.setCustomValidity('');
    }
}

function checkRegisterPassword() {
    var passwordRegisterInput = document.getElementById("password");
    if (passwordRegisterInput.value === "" || (!/[\w\d-_.]{3,20}/.test(passwordRegisterInput.value))) {
        var passwordRegisterTitle = passwordRegisterInput.getAttribute("title");
        passwordRegisterInput.setCustomValidity(passwordRegisterTitle);
    } else {
        passwordRegisterInput.setCustomValidity('');
    }
    var passwordRegisterInput2 = document.getElementById("confirmPassword");
    if (passwordRegisterInput2.value === "" || (!/[\w\d-_.]{3,20}/.test(passwordRegisterInput2.value))) {
        var passwordRegisterTitle2 = passwordRegisterInput2.getAttribute("title");
        passwordRegisterInput2.setCustomValidity(passwordRegisterTitle2);
    } else {
        passwordRegisterInput2.setCustomValidity('');
    }
}

function checkRegisterLoginEmail() {
    checkLogin();

    checkEmail();
}

function checkLogin() {
    var loginRegisterInput = document.getElementById("login");
    if (loginRegisterInput.value === "" || (!/^[a-zA-Z][a-zA-Z\d-_.]{2,19}$/.test(loginRegisterInput.value))) {
        var loginRegisterTitle = loginRegisterInput.getAttribute("title");
        loginRegisterInput.setCustomValidity(loginRegisterTitle);
    } else {
        loginRegisterInput.setCustomValidity('');
    }
}

function checkEmail() {
    var emailRegisterInput = document.getElementById("email");
    var emailRegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (emailRegisterInput.value === "" || (!emailRegExp.test(emailRegisterInput.value))) {
        var emailRegisterTitle = emailRegisterInput.getAttribute("title");
        emailRegisterInput.setCustomValidity(emailRegisterTitle);
    } else {
        emailRegisterInput.setCustomValidity('');
    }

}

function checkForChangingAnyData() {
    checkRegisterLoginEmail();
    checkName();
    checkSurname();
}