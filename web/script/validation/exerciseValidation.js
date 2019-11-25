function checkSetNumber() {
    var SetNumberInput = document.getElementById("set_update");
    if (SetNumberInput.value==="" || (!/^[1-9][0-9]?$/.test(SetNumberInput.value))) {
        var nameRegisterTitle = SetNumberInput.getAttribute("title");
        SetNumberInput.setCustomValidity(nameRegisterTitle);
    } else {
        SetNumberInput.setCustomValidity('');
    }
}

function checkRepeatNumber() {
    var repeatsNumberInput = document.getElementById("repeats_update");
    if (repeatsNumberInput.value==="" || (!/^[1-9][0-9]?$/.test(repeatsNumberInput.value))) {
        var nameRegisterTitle = repeatsNumberInput.getAttribute("title");
        repeatsNumberInput.setCustomValidity(nameRegisterTitle);
    } else {
        repeatsNumberInput.setCustomValidity('');
    }
}

function checkName() {
    var nameRegisterInput = document.getElementById("name");
    if (nameRegisterInput.value==="" || (!/^[a-zA-Zа-яА-Я]{2,100}$/.test(nameRegisterInput.value))) {
        var nameRegisterTitle = nameRegisterInput.getAttribute("title");
        nameRegisterInput.setCustomValidity(nameRegisterTitle);
    } else {
        nameRegisterInput.setCustomValidity('');
    }
}

function checkDescription() {
    var descriptionRegisterInput = document.getElementById("description");
    if (descriptionRegisterInput.value==="" || (!/^[A-Za-zА-Яа-я0-9][A-Za-zА-Яа-я,.()\s0-9]{4,399}$/.test(descriptionRegisterInput.value))) {
        var nameRegisterTitle = descriptionRegisterInput.getAttribute("title");
        descriptionRegisterInput.setCustomValidity(nameRegisterTitle);
    } else {
        descriptionRegisterInput.setCustomValidity('');
    }
}
