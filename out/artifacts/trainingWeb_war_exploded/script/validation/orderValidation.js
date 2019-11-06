function checkCardNumber() {
    var input = document.getElementById("cardNumber");
    if (!/^[0-9]{16}$/.test(input.value)) {
        var placeholderErrorText= input.getAttribute("title");
        input.setCustomValidity(placeholderErrorText);
    } else {
        input.setCustomValidity('');
    }
}

function checkOffer() {
    var input = document.getElementById("period_cost");
    if(input.selectedIndex===0){
        input.setCustomValidity("Choose suitable for you offer!");
    } else {
        input.setCustomValidity('');
    }
}