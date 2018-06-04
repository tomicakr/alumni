

function deletePost(postId) {
    var r = confirm("Jeste li sigurni da želite obrisati post?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/posts/' + postId + '/delete',
            complete: (obj, textStatus) => {
                if(textStatus === 'error') {
                    alert('Post nije obrisan.');
                    return;
                }

                alert('Post uspješno obrisan.');
                window.location.href = "/";
            }
        });
    }
}

function archivePost(postId) {
    var r = confirm("Jeste li sigurni da želite arhivirati post?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/posts/' + postId + '/archive',
            complete: (obj, textStatus) => {
                if(textStatus === 'error') {
                    alert('Post nije arhiviran.');
                    return;
                }

                alert('Post uspješno arhiviran.');
                window.location.href = "/";
            }
        });
    }
}

function comment(postId) {

    $.ajax({
        type: "POST",
        url: '/posts/' + postId + "/comment",
        data: JSON.stringify({message: $('#'+postId+'-commentMessage').val()}),
        contentType: "application/json; charset=utf-8",
        success: function(data) {
            $('#'+postId+'-commentMessage').val('');
            var t = document.getElementById("commentTemplate").innerHTML;
            var c = Handlebars.compile(t); 
            var comment = c(data);
            
            $('#'+postId+'-comment').prepend(comment);
        },
        dataType: 'json'
      });
}

function deleteComment(commentId) {
    var r = confirm("Jeste li sigurni da želite obrisati komentar?");
    if (r == true) {
        $.ajax({
            type: 'DELETE',
            url: '/posts/comment/' + commentId,
            complete: (obj, textStatus) => {
                if(textStatus === 'error') {
                    alert('Komentar nije obrisan.');
                    return;
                }
                $('#'+commentId).remove();
            }
        });
    }
}

Handlebars.registerHelper('formatTime', function (date, format) {
    var mmnt = moment(date);
    return mmnt.format(format);
});