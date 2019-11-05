function checkCardNumber() {
    var input = document.getElementById("cardNumber");
    if(!/^[0-9]{16}$/.test(input.value)) {
        var placeholderErrorText= input.getAttribute("title");
        input.setCustomValidity(placeholderErrorText);
    } else {
        input.setCustomValidity('');
    }
}