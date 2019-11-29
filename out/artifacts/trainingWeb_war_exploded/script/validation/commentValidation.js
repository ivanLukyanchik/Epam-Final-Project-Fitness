function checkComment() {
    var CommentInput = document.getElementById("commentContent");
    if (CommentInput.value==="" || (!/[A-Za-zА-Яа-я0-9][A-Za-zА-Яа-я,.!?()\s0-9]{1,299}/.test(CommentInput.value))) {
        var commentTitle = CommentInput.getAttribute("title");
        CommentInput.setCustomValidity(commentTitle);
    } else {
        CommentInput.setCustomValidity('');
    }
}