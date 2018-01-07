const validationRules = {
    uslugaPravilo: {
        identifier: 'usluga',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite opis usluge'
        }]
    },
    petOdabir: {
        identifier: 'petOdabir',
        rules: [{
            type: 'empty',
            prompt: 'Molimo odaberite svog ljubimca'
        }]
    },
    executionTime: {
        identifier: 'executionTime',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite datum rezervacije'
        }]
    },
    executionDate: {
        identifier: 'executionDate',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite opis usluge'
        }]
    },

    duration: {
        identifier: 'duration',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite opis usluge'
        }]
    },
}
;

$(document)
.ready(function () {
    $('.ui.form').form({
        inline: false,
        fields: validationRules
    });
});