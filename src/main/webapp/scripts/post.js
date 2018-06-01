$(document).ready(function () {

    var value = $('#postTypeValue').val();
    if (value == 'EVENT') {
        $('#postType').text('DOGAĐAJ');
    }

    if (value == 'INFO') {
        $('#postType').text('INFORMACIJA');
    }

    if (value == 'LECTURE') {
        $('#postType').text('PREDAVANJE');
    }
}
)

function deletePost(postId) {
    var r = confirm("Jeste li sigurni da želite obrisati post?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/posts/' + postId + '/delete'
        }).then(() => {
            window.location.replace("/");
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
            window.location.reload()
        });
    }
}