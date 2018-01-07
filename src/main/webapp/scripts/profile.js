const userIndex = window.location.href + (window.location.href.endsWith('/') ? '' : '/');
const petIndex = `${userIndex}pets/`;
const reservationIndex=`${userIndex}reservations/`;
const reservationsTable = $('#reservations').find('table');
const petsTable = $('#pets').find('table');
const btnPets = $('#btn-pets');
const btnReservations = $('#btn-reservations');
const btnEdit = $('#btn-edit');
const btnDelete = $('#btn-delete');
const btnAddPet = $('#btn-add-pet');
const btnAddReservation = $('#btn-add-reservation');


function formatTableRow(...cells) {
    let result = '<tr>';
    result += cells.map(cell => `<td>${cell}</td>`).join('');
    result += '</tr>';
    return result;
}

function appendReservation(res) {
    let resMarkup = $(formatTableRow(res.pet, res.service, res.employee, res.status, res.time));
    resMarkup.data('id',resMarkup.reservationId);
    reservationsTable.append(resMarkup);
}

function appendPet(pet) {
    let deleteButton = '<div class="ui red button btn-pet-delete">Obriši</div>';
    let petMarkup = $(formatTableRow(pet.name, pet.age, pet.species, pet.breed, pet.sex, pet.microchip, pet.remark,deleteButton));
    petMarkup.data('id',pet.petId);
    petsTable.append(petMarkup);
    $('.btn-pet-delete').click(deletePet);
}

let getDataOn = entities => $.getJSON(`${userIndex}${entities}/`);

function updateReservatoins() {
    reservationsTable.empty();
    updateTable(getDataOn('reservations'), appendReservation)
}

function updatePets() {
    petsTable.empty();
    updateTable(getDataOn('pets'), appendPet)
}

function deletePet(){
    const petRow = $(this).parent().parent();
    const deletePetURL = `${petIndex}${petRow.data('id')}/`;
    $.ajax({
        url: deletePetURL,
        type: 'DELETE',
    })
        .then(data => petRow.remove());

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
    $('#add-pet-modal')
        .modal('show');
});
btnReservations.click(updateReservatoins);
btnDelete.click(function() {
    $('#delete-user-modal')
        .modal({
            closable  : false,
            onApprove : () => {
                $.ajax({
                    type: 'DELETE',
                    url: userIndex
                });
            }
        })
        .modal('show')
    ;
});
btnEdit.click(() => window.location.href = `${userIndex}edit`);

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
    sex: {
        identifier: 'gender',
        rules: [{
            type: 'empty',
            prompt: 'Molimo odredite spol'
        }]
    },
    species: {
        identifier: 'pet-species',
        rules: [{
            type: 'empty',
            prompt: 'Molimo odaberite vrstu ljubimca'
        }]
    },
    breed: {
        identifier: 'pet-breed'
    },
    microchip: {
        identifier: 'pet-chip'
    },
    remark:{
        identifier: 'remark'
    }
};
$(document)
    .ready(function() {
        $('.ui.form').form({
            inline: false,
            fields: petValidation,
            onSuccess: (event,fields) => {
                event.preventDefault();
                addPet(fields);
                $('#add-pet-modal')
                    .modal('hide');
            }
        })
        ;
        $('.ui.dropdown')
            .dropdown()
        ;
    });

function addPet(fields){
    $.post({
        url: petIndex,
        contentType: "application/json; charset=utf-8",
        cache: false,    //This will force requested pages not to be cached by the browser
        processData:false, //To avoid making query String instead of JSON
        data: JSON.stringify(fields)
    })
        .then(data =>{
            console.log(data);
            appendPet(data);
        })
        .catch(console.log);
}

