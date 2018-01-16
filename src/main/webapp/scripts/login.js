//import {initialize} from './forms.js';

initialize({
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
});