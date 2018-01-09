import {initialize} from './forms.js';

const passOld = $('#old-password');
const pass = $('#password');
const passCheck = $('#password2');

$.fn.form.settings.rules.passwordMatch = () => {
    return passCheck.val() === pass.val();
};


$.fn.form.settings.rules.passwordMatch = () => {
    return (passCheck.val() === pass.val() && pass.val().toString().length > 6 ) || isEmpty();
};

$.fn.form.settings.rules.firstAreaFilled = () => {
  return   passOld.val().toString().length > 6 ||  isEmpty();
};

$.fn.form.settings.rules.secondAreaFilled = () => {
  return pass.val().toString().length > 6 ||  isEmpty();
};


function isEmpty(){
    return (passOld.val().toString().length + pass.val().toString().length + passCheck.val().toString().length) === 0;

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
