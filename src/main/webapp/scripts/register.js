import {initialize} from './forms.js';

const firstName   = $('#first-name'  );
const lastName    = $('#last-name'   );
const mobilePhone = $('#mobile-phone');
const telephone   = $('#telephone'   );
const email       = $('#email'       );
const address     = $('#address'     );
const pass        = $('#password'    );
const oib         = $('#oib'         );
const passCheck   = $('#password2'   );

function oibCheck(oib) {
    oib = oib.toString();
    if (oib.length !== 11) return false;

    let b = parseInt(oib, 10);
    if (isNaN(b)) return false;

    let a = 10;
    for (let i = 0; i < 10; i++) {
        a = a + parseInt(oib.substr(i, 1), 10);
        a = a % 10;
        if (a === 0) a = 10;
        a *= 2;
        a = a % 11;
    }
    let control = 11 - a;
    if (control === 10) control = 0;

    return control === parseInt(oib.substr(10, 1));
}

$.fn.form.settings.rules.passwordMatch = () => {
    return passCheck.val() === pass.val();
};

$.fn.form.settings.rules.oibCheck = oibCheck;

initialize({
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
    oib: {
        identifier: 'oib',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite OIB'
        },
            {
                type: 'oibCheck[value]',
                prompt: 'Molimo unesite OIB'
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
            prompt: 'Molimo upišite adresu elektroničke pošte'
        },
            {
                type: 'email',
                prompt: 'Adresa elektroničke pošte nije valjana'
            }
        ]
    },
    city: {
        identifier: 'email',
        rules: [{
            type: 'empty',
            prompt: 'Molimo odaberite grad'
        }]
    },
    address: {
        identifier: 'email',
        rules: [{
            type: 'empty',
            prompt: 'Molimo upišite kućnu adresu'
        }]
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
});

let generateOib = () => {
    Math.floor(Math.random() * 1000000000);
    let first10 = Math.floor(Math.random() * 1000000000).toString();
    let last = 0;
    let oib;
    do {
        oib = first10 + last.toString();
        last++;
    } while (!oibCheck(oib));
    return oib;
};

$('#komba').click(function () {
    let oibgen = generateOib();
    oib.val(oibgen);
    $.getJSON('https://uinames.com/api/?region=england')
        .done(function (data) {
            firstName.val(data.name);
            lastName.val(data.surname);
            address.val(`${data.region} ${Math.floor(Math.random() * 100)}`);
            mobilePhone.val(oibgen);
            telephone.val(oibgen);
            pass.val(data.name.toLowerCase() + "123");
            passCheck.val(data.name.toLowerCase() + "123");
            email.val(`${data.name.toLowerCase()}.${data.surname.toLowerCase()}@gmail.com`);
        })
        .fail(function () {
            alert('Morat ces sam, ne radi API');
        });
});

