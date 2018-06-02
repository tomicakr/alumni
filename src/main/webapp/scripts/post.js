
function deletePost(postId) {
    var r = confirm("Jeste li sigurni da želite obrisati post?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/posts/' + postId + '/delete'
        }).then(() => {
            window.location.href = "/";
        });
    }
}

function archivePost(postId) {
    var r = confirm("Jeste li sigurni da želite arhivirati post?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/posts/' + postId + '/archive'
        }).then(() => {
            window.location.href = "/posts/archive"
            alert("Post je arhiviran.");
        });
    }
}

function deleteComment(postId, commentId) {
    var r = confirm("Jeste li sigurni da želite obrisati komentar?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/posts/' + postId + '/comment/' + commentId + "/delete"
        }).then(() => {
            window.location.href = "/posts/" + postId;
            alert("Komentar je obrisan.");
        });
    }
}