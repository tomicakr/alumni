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
    }]
  }
}


$(document)
  .ready(function() {
    $('.ui.form').form({
      inline: true,
      fields: validationRules
    });
  });

const passTimeout = 2000;

var pass = $('#password');
var passCheck = $('#password2');
var matchIcon = $('#pass-match');
var typingTimer;


$.fn.form.settings.rules.passmatch = (value, adminLevel) => {
  return passCheck.val() === pass.val();
};

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