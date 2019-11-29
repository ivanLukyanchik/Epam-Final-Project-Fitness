function checkNutrition() {
    var nutritionDescriptionInput = document.getElementById("nutrition_description");
    if (nutritionDescriptionInput.value==="" || (!/[A-Za-zА-Яа-я0-9][A-Za-zА-Яа-я,.()\s0-9]{1,299}/.test(nutritionDescriptionInput.value))) {
        var nutritionDescriptionTitle = nutritionDescriptionInput.getAttribute("title");
        nutritionDescriptionInput.setCustomValidity(nutritionDescriptionTitle);
    } else {
        nutritionDescriptionInput.setCustomValidity('');
    }
}