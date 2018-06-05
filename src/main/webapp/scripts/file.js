function deleteFile(fileId) {
    var r = confirm("Jeste li sigurni da želite obrisati datoteku?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/files/' + fileId + '/delete',
            complete: function(obj, textStatus) {

                if(textStatus === "error") {
                    alert("Greška.");
                    return;
                }

                window.location.href = "/files";
            }
        });
    }
}

function downloadFile(fileId) {
    $.ajax({
        type: 'GET',
        url: '/files/' + fileId,
        complete: function(obj, textStatus) {

            if(textStatus === "error") {
                alert("Greška.");
                return;
            }

            alert("Datoteka preuzeta.");
            window.location.href = "/files";
        }
    });
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
        identifier  : 'description',
        rules: [
            {
                type   : 'empty',
                prompt : 'Upišite opis'
            }
        ]
    },
    file: {
        identifier  : 'file',
        rules: [
            {
                type   : 'empty',
                prompt : 'Odaberite datoteku'
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
    
