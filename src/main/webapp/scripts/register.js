
const firstName = $('#first-name');
const lastName = $('#last-name');
const phone = $('#phone');
const email = $('#email');
const address = $('#address');
const birthday = $('#birthday');
const graduation = $('#graduation');
const pass = $('#password');
const passCheck = $('#password-confirm');

$.fn.form.settings.rules.passwordMatch = () => {
    return passCheck.val() === pass.val();
};

const rules = {
    firstName: {
        identifier: 'first-name',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite ime'
        }]
    },
    lastName: {
        identifier: 'last-name',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite prezime'
        }]
    },
    phone: {
        identifier: 'phone',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite broj telefona'
        }]
    },
    email: {
        identifier: 'email',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite adresu elektroničke pošte'
        },
        {
            type: 'email',
            prompt: 'Adresa elektroničke pošte nije valjana'
        }
        ]
    },
    address: {
        identifier: 'address',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite kućnu adresu'
        }]
    },
    birthday: {
        identifier: 'birthday',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite datum rođenja'
        }]
    },
    graduation: {
        identifier: 'graduation',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite datum diplomiranja'
        }]
    },
    password: {
        identifier: 'password',
        rules: [{
            type: 'empty',
            prompt: 'Molimo odaberite lozinku'
        },
        {
            type: 'length[8]',
            prompt: 'Lozinka mora biti dugačka barem 8 znakova'
        }
        ]
    },
    password2: {
        identifier: 'password-confirm',
        rules: [{
            type: 'empty',
            prompt: 'Molimo ponovite lozinku'
        }, {
            type: 'passwordMatch',
            prompt: 'Lozinke se ne podudaraju'
        }]
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