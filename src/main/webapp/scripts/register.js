const validationRules = {
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
    address: {
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
            prompt: 'Molimo upišite adresu elektroničke pošte'
        },
            {
                type: 'email',
                prompt: 'Adresa elektroničke pošte nije valjana'
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


$(document)
    .ready(function () {
        $('.ui.form').form({
            inline: false,
            fields: validationRules
        });
    });

const matchIcon = $('#pass-match');
const firstName = $('#first-name');
const lastName = $('#last-name');
const mobilePhone = $('#mobile-phone');
const telephone = $('#telephone');
const email = $('#email');
const address = $('#address');
const pass = $('#password');
const oib = $('#oib');
const passCheck = $('#password2');


$.fn.form.settings.rules.passwordMatch = () => {
    return passCheck.val() === pass.val();
};

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

$.fn.form.settings.rules.oibCheck = oibCheck;

passCheck.focusout(() => {
    console.log("tu smo");
    if (passCheck.val() === pass.val()) {
        matchIcon.removeClass('fa-times-circle-o').addClass('fa-check-circle-o');
    } else {
        matchIcon.removeClass('fa-check-circle-o').addClass('fa-times-circle-o');
    }
});

$('#komba').click(function () {
    let oibgen = generateOib();
    oib.val(oibgen)
    $.getJSON('https://uinames.com/api/')
        .done(function (data) {
            firstName.val(data.name);
            lastName.val(data.surname);
            address.val(`${data.region} ${Math.floor(Math.random() * 100)}`);
            mobilePhone.val(oibgen);
            telephone.val(oibgen);
            pass.val(data.name.toLowerCase() + "123");
            passCheck.val(data.name.toLowerCase() + "123");
            email.val(`${data.name}.${data.surname}@gmail.com`);
        })
        .fail(function () {
            alert('morat ces sam, ne radi API');
        });
});
generateOib = () => {
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

