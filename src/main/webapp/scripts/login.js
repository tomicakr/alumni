const rules = {
    email: {
        identifier  : 'email',
        rules: [
            {
                type   : 'empty',
                prompt : 'Upišite e-mail adresu'
            }
        ]
    },
    password: {
        identifier  : 'password',
        rules: [
            {
                type   : 'empty',
                prompt : 'Upišite lozinku'
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
    