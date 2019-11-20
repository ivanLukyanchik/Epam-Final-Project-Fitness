function showHide() {
    var pwd = document.getElementById('password');
    if (pwd.type === 'text') {
        pwd.type = 'password';
    } else {
        pwd.type = 'text';
    }
}

function showDescription() {
    var description = document.getElementById('description');
    if (description.style.display === "block") {
        description.style.display = "none";
    } else {
        description.style.display = "block";
    }
}