function checkSetNumber() {
    var SetNumberInput = document.getElementById("set_update");
    if (SetNumberInput.value==="" || (!/^[1-9][0-9]*$/.test(SetNumberInput.value))) {
        var nameRegisterTitle = SetNumberInput.getAttribute("title");
        SetNumberInput.setCustomValidity(nameRegisterTitle);
    } else {
        SetNumberInput.setCustomValidity('');
    }
}

function checkRepeatNumber() {
    var repeatsNumberInput = document.getElementById("repeats_update");
    if (repeatsNumberInput.value==="" || (!/^[1-9][0-9]*$/.test(repeatsNumberInput.value))) {
        var nameRegisterTitle = repeatsNumberInput.getAttribute("title");
        repeatsNumberInput.setCustomValidity(nameRegisterTitle);
    } else {
        repeatsNumberInput.setCustomValidity('');
    }
}
