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
        prompt:'Lozinke se ne podudaraju'
    }]
  }
}


$(document)
  .ready(function() {
    $('.ui.form').form({
      inline: false,
      fields: validationRules
    });
  });

const passTimeout = 2000;

var pass = $('#password');
var passCheck = $('#password2');
var matchIcon = $('#pass-match');
var typingTimer;


$.fn.form.settings.rules.passwordMatch = () => {
  return passCheck.val() === pass.val();
};

$.fn.form.settings.rules.oibCheck = (oib) => {
    oib = oib.toString();
    if (oib.length != 11) return false;

    var b = parseInt(oib, 10);
    if (isNaN(b)) return false;

    var a = 10;
    for (var i = 0; i < 10; i++) {
        a = a + parseInt(oib.substr(i, 1), 10);
        a = a % 10;
        if (a == 0) a = 10;
        a *= 2;
        a = a % 11;
    }
    var control = 11 - a;
    if (control == 10) control = 0;

    return control == parseInt(oib.substr(10, 1));
}

passCheck.focusout(() => {
  console.log("tu smo");
  if (passCheck.val() === pass.val()) {
    matchIcon.removeClass('fa-times-circle-o').addClass('fa-check-circle-o');
  } else {
    matchIcon.removeClass('fa-check-circle-o').addClass('fa-times-circle-o');
  }
});


$('.ui.form')
  .form({
    fields: {
      name: {
        identifier: 'special-name',
        rules: [{
          type: 'empty'
        }]
      }
    }
  });