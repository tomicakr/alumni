const validationRules = {
        service: {
            identifier: 'service',
            rules: [{
                type: 'empty',
                prompt: 'Molimo unesite opis usluge'
            }]
        },
        pet: {
            identifier: 'pet',
            rules: [{
                type: 'empty',
                prompt: 'Molimo odaberite svog ljubimca'
            }]
        },
        executionTime: {
            identifier: 'executionTime',
            rules: [{
                type: 'empty',
                prompt: 'Molimo unesite termin rezervacije'
            }]
        },
        preferedEmployee: {
            identifier: 'preferedEmployee'
        },
        duration: {
            identifier: 'duration',
            rules: [{
                type: 'empty',
                prompt: 'Molimo unesite trajanje rezervacije'
            }]
        },
        sendReminder:{
            identifier: 'sendReminder',
            rules: [{
                type: 'empty',
                prompt: 'Molimo unesite e-mail postavku'
            }]
        }
    }
;

$(document)
    .ready(function () {
        $('.ui.form').form({
            inline: false,
            fields: validationRules
        });

        $('#res-time')
            .calendar(dateInputConfig);

        $('#res-duration')
            .calendar(timeInputConfig);
    });