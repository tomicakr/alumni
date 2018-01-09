
$(document)
    .ready(function() {
        $('.ui.form')
            .form({
                fields: {
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
                },
                onSuccess: () => $('.submit.button').addClass('loading')
            })
        ;
        if($('.error.message').text().length()>0){
            $('.error.message').show();
        }
    })
;