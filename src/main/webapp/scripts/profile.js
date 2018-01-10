const userIndex = window.location.href + (window.location.href.endsWith('/') ? '' : '/');
const petIndex = `${userIndex}pets/`;
const reservationIndex=`${userIndex}reservations/`;

const reservationsTable = $('#reservations').find('tbody');
const petsTable = $('#pets').find('tbody');

const btnPets = $('#btn-pets');
const btnReservations = $('#btn-reservations');
const btnEdit = $('#btn-edit');
const btnDelete = $('#btn-delete');
const btnAddPet = $('#btn-add-pet');
const btnAddReservation = $('#btn-add-reservation');

const addPetModal = $('#add-pet-modal');
const addReservationModal = $('#add-reservation-modal');

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

const reservationValidation = {
    service: {
        identifier: 'usluga',
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
            prompt: 'Molimo unesite željeno vrijeme usluge'
        }]
    },

    duration: {
        identifier: 'duration',
        rules: [{
            type: 'empty',
            prompt: 'Molimo unesite trajanje usluge'
        }]
    },
};

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
    let deleteButton = '<i class="big red remove icon" title="Obriši ljubimca"></i>';
    let petMarkup = $(formatTableRow(pet.name, pet.age, pet.species, pet.breed, pet.sex, pet.microchip, pet.remark,deleteButton));
    petMarkup.data('id',pet.petId);
    petsTable.append(petMarkup);
    $('.remove.icon').click(deletePet);

}

function updateReservatoins() {
    reservationsTable.empty();
    updateTable($('#reservations-table'),$.getJSON(reservationIndex), appendReservation,()=> $('#reservations-table-container h2').text('Nemate prijavljenih rezervacija'))

}
function updatePets() {
    petsTable.empty();
    updateTable($('#pets-table'), $.getJSON(petIndex), appendPet, ()=> $('#pets-table-container h2').text('Nemate prijavljenih ljubimaca'))

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

function updateTable(table,jsonGetter, appender, noneFound) {
    return jsonGetter
        .then(entities => {
            if(entities.length===0){
                table.hide();
                noneFound();
                return;
            }
            table.show();
            entities.forEach(entity => appender(entity));

        })
        .fail(console.log);

}




btnPets.click(updatePets);

btnAddPet.click(
    () => addPetModal.modal('show')
);

btnAddReservation.click(
    () => window.location.href=reservationIndex+'new'
    //() => addReservationModal.modal('show')
);
btnReservations.click(updateReservatoins);
btnDelete.click(function() {
    $('#delete-user-modal')
        .modal({
            closable  : false,
            onApprove : () => {
                $.ajax({
                    type: 'DELETE',
                    url: userIndex.substring(0,userIndex.length-1)
                })
                    .then(function() {
                            window.location.replace("/");
                        }
                    )
                    .catch(console.log);
            }
        })
        .modal('show')
    ;
});

btnEdit.click(() => window.location.href = `${userIndex}edit`);

function modalInit(modal, formFields, handler){
    modal.form({
        inline: false,
        fields: formFields,
        onSuccess: (event,fields) => {
            event.preventDefault();
            handler(fields);
            modal.modal('hide');
        }
    })
    ;

    $('.ui.dropdown')
        .dropdown()
    ;
}

$(document)
    .ready(function() {
        modalInit(addPetModal,petValidation,
            (fields) => addEntity(
                petIndex,
                appendPet,
                console.log,
                fields,
            ));

        modalInit(addReservationModal,reservationValidation,
            (fields) => addEntity(
                reservationIndex,
                appendReservation,
                console.log,
                fields,
            ));

        $('.menu .item')
            .tab({'onVisible':function(tabpath){
                if(tabpath==='first'){
                    return;
                }
                if(tabpath === 'second'){
                    updatePets();
                    return;
                }
                if(tabpath === 'third'){
                    updateReservatoins();
                }
            }})

        ;
    });


function addEntity(url, onSucess, onFail,fields){
    $.post({
        url: url,
        contentType: "application/json; charset=utf-8",
        cache: false,    //This will force requested pages not to be cached by the browser
        processData:false, //To avoid making query String instead of JSON
        data: JSON.stringify(fields)
    })
        .then(onSucess)
        .catch(onFail);
}
