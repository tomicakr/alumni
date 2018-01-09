const validationRules = {
    city: {
            identifier: 'location',
            rules: [{
                type: 'empty',
                prompt: 'Molimo odaberite grad'
            }]
        },
    oldPassword: {
        identifier: 'old-password',
        rules: [{
            type: 'empty',
            prompt: 'Molimo odaberite lozinku'
        },
            {
                type: 'length[6]',
                prompt: 'Lozinka mora biti dugačka barem 6 znakova'
            }
        ]
    },
        password: {
            identifier: 'password',
            rules: [{
                type: 'empty',
                prompt: 'Molimo odaberite lozinku'
            },
                {
                    type: 'length[6]',
                    prompt: 'Lozinka mora biti dugačka barem 6 znakova'
                }
            ]
        },
        password2: {
            identifier: 'password2',
            rules: [{
                type: 'empty',
                prompt: 'Molimo ponovite lozinku'
            }, {
                type: 'passwordMatch',
                prompt: 'Lozinke se ne podudaraju'
            }]
        }
    }
;

const pass = $('#password');
const passCheck = $('#password2');

$.fn.form.settings.rules.passwordMatch = () => {
    return passCheck.val() === pass.val();
};


$(document)
    .ready(function () {
        $('.ui.dropdown')
            .dropdown();
        $('.ui.accordion.field')
            .accordion();
        $('.ui.form').form({
            inline: false,
            fields: validationRules
        });
    });

