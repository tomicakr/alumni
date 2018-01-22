//import {initialize} from './forms.js';

const passOld = $('#old-password');
const pass = $('#password');
const passCheck = $('#password2');

$.fn.form.settings.rules.passwordMatch = () => {
    return passCheck.val() === pass.val();
};

$.fn.form.settings.rules.firstAreaFilled = () => {
  return passOld.val().length > 8 ||  pass.val() === '';
};

$.fn.form.settings.rules.secondAreaFilled = () => {
  return pass.val().length > 8 ||  pass.val() === '';
};

initialize({
    oldPassword: {
        identifier: 'oldPassword',
        rules: [{
            type: 'firstAreaFilled',
            prompt: 'Neispravan unos! Broj znakova mora biti veći od 6'
        }]
    },
    password: {
        identifier: 'password',
        rules: [{
            type: 'secondAreaFilled',
            prompt: 'Neispravan unos! Broj znakova mora biti veći od 6'
        }]
    },
    password2: {
        identifier: 'password2',
        rules: [ {
            type: 'passwordMatch',
            prompt: 'Lozinke se ne podudaraju!'
        }]
    }
});
