function deleteLink(linkId) {
    var r = confirm("Jeste li sigurni da želite obrisati link?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/links/' + linkId + '/delete'
        }).then(() => {
            window.location.reload()
        });;
    }
}

$('.message .close')
.on('click', function () {
    $(this)
        .closest('.message')
        .transition('fade')
        ;
})
;

const rules = {
    title: {
        identifier  : 'title',
        rules: [
            {
                type   : 'empty',
                prompt : 'Upišite naziv'
            }
        ]
    },
    url: {
        identifier  : 'url',
        rules: [
            {
                type   : 'empty',
                prompt : 'Upišite url'
            }
        ]
    }
};

$(document)
    .ready(function () {
        $('.ui.form')
            .form({
                inline: true,
                fields: rules,
                onSuccess: () => $('.submit.button').addClass('loading')
            })
            ;
    })
    ;
    
