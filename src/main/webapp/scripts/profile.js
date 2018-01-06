const userUrl = window.location.href + (window.location.href.endsWith('/') ? '' : '/');
const reservationsTable = $('#reservations').find('table');
const petsTable = $('#pets').find('table');
const btnPets = $('#btn-pets');
const btnReservations = $('#btn-reservations');
const btnEdit = $('#btn-edit');
const btnDelete = $('#btn-delete');
const btnAddPet = $('#btn-add-pet');
const btnAddReservation = $('#btn-add-reservation');
const addPetForm = $('#add-pet-form');

function formatTableRow(...cells) {
    let result = '<tr>';
    result += cells.map(cell => `<td>${cell}</td>`).join('');
    result += '</tr>';
    return result;
}

function appendReservation(res) {
    let markup = $(formatTableRow(res.pet, res.service, res.employee, res.status, res.time));
    reservationsTable.append(markup);
}

function appendPet(pet) {
    let markup = $(formatTableRow(pet.name, pet.age, pet.species, pet.breed, pet.sex, pet.microchip, pet.remark));
    petsTable.append(markup);
}

let getDataOn = entities => $.getJSON(`${userUrl}${entities}/`);

function updateReservatoins() {
    reservationsTable.empty();
    updateTable(getDataOn('reservations'), appendReservation)
}

function updatePets() {
    petsTable.empty();
    updateTable(getDataOn('pets'), appendPet)
}

function updateTable(jsonGetter, appender) {
    return jsonGetter
        .then(entities => {
            entities.forEach(entity => appender(entity))
        })
        .fail(console.log);
}

btnPets.click(updatePets);
btnAddPet.click(function() {
    $('.ui.modal')
        .modal('show');
});
btnReservations.click(updateReservatoins);
btnDelete.click(function() {
    let decision = confirm("Jeste li sigurni da želite izbrisati korisnika?");
    if (!decision) {
        return;
    }
    $.ajax({
        url: userUrl,
        type: 'DELETE'
    });
});

btnEdit.click(() => window.location.href = `${userUrl}edit`);

const petValidation = {
    name: {
        identifier: 'pet-name',
        rules: [{
            type: 'empty',
            prompt: 'Ljubimac mora imati ime'
        }]
    },
    age: {
        identifier: 'pet-age',
        rules: [{
            type: 'empty',
            prompt: 'Ljubimac mora imati broj godina'
        }]
    },
    species: {
        identifier: 'pet-species',
        rules: [{
            type: 'empty',
            prompt: 'Molimo odaberite vrstu ljubimca'
        }]
    }
};

$(document)
    .ready(function() {
        $('.ui.form').form({
            inline: false,
            fields: petValidation,
            onSuccess: function(){
                $('.ui.modal')
                    .modal('hide');
            }
        });
    });
