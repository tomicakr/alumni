const userIndex           = window.location.href + (window.location.href.endsWith('/') ? '' : '/');
const petIndex            = `${userIndex}pets/`;
const reservationIndex    =`${userIndex}reservations/`;

const btnPets             = $('#btn-pets'           );
const btnReservations     = $('#btn-reservations'   );
const btnEdit             = $('#btn-edit-user'      );
const btnDelete           = $('#btn-delete-user'    );
const btnAddPet           = $('#btn-add-pet'        );
const btnAddReservation   = $('#btn-add-reservation');

const addPetModal         = $('#add-pet-modal');
const addReservationModal = $('#add-reservation-modal');

let Table= function(indexUrl, table, tableBody, placeholder){
    this.indexUrl = indexUrl;
    this.table = table;
    this.tableBody = tableBody;
    this.placeholder = placeholder;
};


Table.prototype = {
    isEmpty:function(){
        return this.table.find('tbody tr').length === 0;
    },

    formatTableRow: function(...cells) {
        let result = '<tr>';
        result += cells.map(cell => `<td>${cell}</td>`).join('');
        result += '</tr>';
        return result;

    },

    getData: function(){
        return $.getJSON(this.indexUrl);
    },

    remove: function(row){
        const deleteUrl = `${this.indexUrl}${row.data('id')}/`;

        $.ajax({
            url: deleteUrl,
            type: 'DELETE',
        })
            .then(data => {
                row.remove();
                if(this.isEmpty()){
                    this.table.hide();
                    this.placeholder.show();
                }
            });
    },

    update: function(){
        this.tableBody.empty();
        this.getData()
            .then(entities => {
                if(entities.length===0){
                    this.table.hide();
                    this.placeholder.show();
                    return;
                }
                this.placeholder.hide();
                this.table.show();
                entities.forEach(entity => this.append(entity));

            })
            .fail(console.log);
    },

    save: function(fields) {
        $.post({
            url: this.indexUrl,
            contentType: "application/json; charset=utf-8",
            cache: false,    //This will force requested pages not to be cached by the browser
            processData: false, //To avoid making query String instead of JSON
            data: JSON.stringify(fields)
        })
            .then(this.append)
            .catch(console.log);
    }
};



let petTable = new Table(
    petIndex,
    $('#pets-table'),
    $('#pets').find('tbody'),
    $('#pet-placeholder')
);

let appendPet = function(pet){
    let deleteButton = '<i class="big red remove icon" title="Obriši ljubimca"></i>';
    let petMarkup = $(this.formatTableRow(pet.name, pet.age, pet.species, pet.breed, pet.sex, pet.microchip, pet.remark,deleteButton));
    petMarkup.data('id',pet.petId);
    petTable.tableBody.append(petMarkup);
    petMarkup.find('.remove.icon').click(this.remove.bind(this,petMarkup));
    this.placeholder.hide();
    this.table.show();
};

petTable.append = appendPet.bind(petTable);

let resTable = new Table(
    reservationIndex,
    $('#reservations-table'),
    $('#reservations').find('tbody'),
    $('#reservations-placeholder')
);

resTable.append = (function(res){
    let resMarkup = $(this.formatTableRow(res.pet, res.service, res.employee, res.status, res.time));
    resMarkup.data('id',resMarkup.reservationId);
    resTable.tableBody.append(resMarkup);
}).bind(resTable);

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


btnPets.click(petTable.update.bind(petTable));

btnAddPet.click(
    () => addPetModal.modal('show')
);

btnAddReservation.click(
    () => window.location.href=reservationIndex+'new'
    //() => addReservationModal.modal('show')
);

btnReservations.click(resTable.update.bind(resTable));

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
        modalInit(addPetModal,petValidation,petTable.save.bind(petTable));

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
                    petTable.update();
                    return;
                }
                if(tabpath === 'third'){
                    resTable.update();
                }
            }})
        ;
    });

