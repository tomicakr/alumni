function deleteCategory(categoryId) {
    var r = confirm("Jeste li sigurni da želite obrisati kategoriju?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/categories/' + categoryId + '/delete',
            success: () =>  alert("Uspješno obrisano."),
            error: () =>  alert("Nije moguće obrisati kategoriju dok postoji post koji je tog tipa."),
            afterSend : () =>  window.location.reload()
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
    name: {
        identifier  : 'name',
        rules: [
            {
                type   : 'empty',
                prompt : 'Upišite naziv'
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
    
