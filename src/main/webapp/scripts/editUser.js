
const firstName = $('#first-name');
const lastName = $('#last-name');
const phone = $('#phone');
const email = $('#email');
const address = $('#address');
const birthday = $('#birthday');
const graduation = $('#graduation');

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