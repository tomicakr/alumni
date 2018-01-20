const userIndex           = window.location.href + (window.location.href.endsWith('/') ? '' : '/');
const petIndex            = `${userIndex}pets/`;
const reservationIndex    =`${userIndex}reservations/`;
const api = {
    species:'/api/species',
    employees: '/api/users?role=employee',
    pets: petIndex,
    service: '/api/services'
};

const btnPets             = $('#btn-pets'           );
const btnReservations     = $('#btn-reservations'   );
const btnEdit             = $('#btn-edit-user'      );
const btnDelete           = $('#btn-delete-user'    );
const btnEmploy           = $('#btn-employ-user'    );
const btnFire             = $('#btn-fire-user'      );
const btnAddPet           = $('#btn-add-pet'        );

const petSpecies          = $('#pet-species' );
const resEmployee         = $('#res-employee');
const resService          = $('#res-service' );
const resPet              = $('#res-pet' );

const btnAddReservation   = $('#btn-add-reservation');
const addPetModal         = $('#add-pet-modal'        );

const addReservationModal = $('#add-reservation-modal');

const userRole            = $('#role');
const clientLabel   = '<p class="ui client long tag label">Klijent</p>';

const employeeLabel = '<p class="ui employee long tag label">Zaposlenik</p>';
function hideElem(e){
    e.addClass('inactive');

}
function showElem(e) {
    e.removeClass('inactive');

}
function patch(data, onSuccess, onFail){
    $.ajax({
        url: userIndex,
        type: 'PATCH',
        contentType: "application/json; charset=utf-8",
        cache: false,
        processData: false,
        data: JSON.stringify(data)
    })
        .then(onSuccess)
        .catch(onFail)

}

const employOperation = {
    "op": "replace",
    "path": "/status",
    "value": "employee"
};

const fireOperation = {
    "op": "replace",
    "path": "/status",
    "value": "client"
};
let Table= function(indexUrl, table, tableBody, placeholder, loader, deleteModal){
    this.indexUrl = indexUrl;
    this.table = table;
    this.tableBody = tableBody;
    this.placeholder = placeholder;
    this.loader = loader;
    this.deleteModal = deleteModal;
    this.isUpdating = false;
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
        this.deleteModal
            .modal({
            onApprove: () => {
                $.ajax({
                    url: deleteUrl,
                    type: 'DELETE',
                }).then(() => {
                    row.remove();
                    if (this.isEmpty()) {
                        this.showPlaceholder()
                    }
                })
            }
        })
            .modal('show');

    },

    update: function(){
        if(this.isUpdating){
            return;
        }
        this.load();
        this.isUpdating = true;
        this.tableBody.empty();
        this.getData()
            .then(entities => {
                if(entities.length===0){
                    this.showPlaceholder();
                    return;
                }
                entities.forEach(entity => this.append(entity));
                this.showContent();

            })
            .fail(console.log)
            .always(
                () => this.isUpdating = false
            );
    },

    save: function(fields) {
        $.post({
            url: this.indexUrl,
            contentType: "application/json; charset=utf-8",
            cache: false,    //This will force requested pages not to be cached by the browser
            processData: false, //To avoid making query String instead of JSON
            data: JSON.stringify(fields)
        })
            .then(data => {
                this.append(data);
            })
            .catch(console.log);
    },

    load: function(){
        this.loader.addClass('active')
    },
    finishLoad: function(){
        this.loader.removeClass('active')
    },
    showContent: function(){
        this.finishLoad();
        hideElem(this.placeholder);
        showElem(this.table);
    },
    showPlaceholder: function(){
        this.finishLoad();
        hideElem(this.table);
        showElem(this.placeholder);
    },
    hideAll: function(){
        this.finishLoad();
        hideElem(this.table);
        hideElem(this.placeholder);
    }
};

let petTable = new Table(
    petIndex,
    $('#pets-table'),
    $('#pets').find('tbody'),
    $('#pet-placeholder'),
    $('#pet-loader'),
    $('#delete-pet-modal')
);
let appendPet = function(pet){
    let deleteButton = '<i class="big trash action del icon" title="Ukloni"></i>';

    let petMarkup = $(this.formatTableRow(pet.name, pet.age, pet.species,pet.sex, pet.microchip, pet.remark,deleteButton));
    petMarkup.data('id',pet.petId);
    petTable.tableBody.append(petMarkup);

    petMarkup.find('.del.icon').click(petTable.remove.bind(petTable,petMarkup));
    petMarkup.find('.del.icon').popup();
    this.showContent()
};

petTable.append = appendPet.bind(petTable);

let resTable = new Table(
    reservationIndex,
    $('#reservations-table'),
    $('#reservations').find('tbody'),
    $('#reservations-placeholder'),
    $('#reservations-loader'),
    $('#delete-reservation-modal')
);

resTable.append = (function(res){
    let deleteButton = '<i class="big remove from calendar del action icon" title="Otkaži"></i>';
    let resMarkup = $(this.formatTableRow(res.pet, res.service, res.employee, res.status, res.time,deleteButton));
    console.log(res.reservationId);
    resMarkup.data('id',res.reservationId);
    resTable.tableBody.append(resMarkup);

    resMarkup.find('.del.icon').click(resTable.remove.bind(resTable,resMarkup));
    resMarkup.find('.icon').popup();
    this.showContent();
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
        identifier: 'service',
        rules: [{
            type: 'empty',
            prompt: 'Molimo odaberite uslugu'
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

function fillDropDown(dropdown, getId, getText, getName, data){
        dropdown.empty();
        let options = [];
        data.forEach(x => {
            let [v,t,n] = [getId(x),getText(x),getName(x)];
            options.push({
                value: v,
                text: t,
                name: n
            });
            dropdown.append($('<option>', {value: v, text: t}));
        });
        dropdown.dropdown('setup menu', {values: options});
}

btnPets.click(petTable.update.bind(petTable));
btnAddPet.click(() => {
        addPetModal.modal('show');
        $.getJSON(api.species)
            .then(
                data => fillDropDown(
                    petSpecies,
                    x => x.id,
                    x => x.name,
                    x => x.name,
                    data)
            )
    }
);

let twoDigitFormat = (getter) => ('0' + getter()).slice(-2);

let deklinacijeMrtve = {
    'siječanj': 'siječnja',
    'veljača': 'veljače',
    'ožujak': 'ožujka',
    'travanj': 'travnja',
    'svibanj': 'svibnja',
    'lipanj': 'lipaipnja',
    'srpanj': 'srpnja',
    'kolovoz': 'kolovoza',
    'rujan': 'rujna',
    'listopad': 'listopada',
    'studeni': 'studenog',
    'prosinac': 'prosinca'
};

btnAddReservation.click(
    () => {
        addReservationModal.modal({
            autofocus: false
        }).modal('show');

        let monthFormat   = (date) => twoDigitFormat(() => date.getMonth() + 1);
        let hourFormat    = (date) => twoDigitFormat(date.getHours.bind(date));
        let minutesFormat = (date) => twoDigitFormat(date.getMinutes.bind(date));
        let dayFormat     = (date) => twoDigitFormat(date.getDate.bind(date));
        let timeFormat    = (date) => `${hourFormat(date)}:${minutesFormat(date)}`;

        $('#res-time').calendar({
            ampm: false,
            monthFirst: false,
            text: {
                days: ['P', 'U', 'S', 'Č', 'P', 'S', 'N'],
                months: ['siječanj', 'ožujak', 'ožujak', 'travanj', 'svibanj', 'lipanj', 'srpanj', 'kolovoz', 'rujan', 'listopad', 'studeni', 'prosinac'],
                today: 'Danas',
                now: 'Sad'
            },
            formatter: {
                datetime: function(date){
                    if (!date) return '';
                    return `${dayFormat(date)}.${monthFormat(date)}.${date.getFullYear()}. ${timeFormat(date)}`;
                }
            }
        });
        $('#res-duration').calendar({
            type: 'time',
            ampm: false,
            formatter:{
                time: timeFormat
            }
        });
        $.getJSON(api.employees)
            .then(
                data => fillDropDown(
                    resEmployee,
                    x => x.userId,
                    x => `${x.firstName} ${x.lastName}`,
                    x => `${x.firstName} ${x.lastName}`,
                    data)
            );
        $.getJSON(api.service)
            .then(
                data => fillDropDown(
                    resService,
                    x => x.id,
                    x => x.name,
                    x => x.name,
                    data)
            );
        $.getJSON(api.pets)
            .then(
                data => fillDropDown(
                    resPet,
                    x => x.petId,
                    x => x.name,
                    x => x.name,
                    data)
            )
    }
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

btnEdit.click(
    () => window.location.href = `${userIndex}edit`
);



function switchButtons(toHide, toShow) {
    hideElem(toHide);
    showElem(toShow);
}

function hire(){
    switchButtons(btnEmploy, btnFire);
    userRole.find('p').remove();
    userRole.append(employeeLabel);
}

function fire(){
    switchButtons(btnFire, btnEmploy);
    userRole.find('p').remove();
    userRole.append(clientLabel);
}

btnEmploy.click(() => {
    patch(
        [employOperation],
        hire,
        () => console.log(`Ovo se poslalo: "${JSON.stringify([employOperation])}", ali nista od toga`),
    );
});

btnFire.click(() => {
    patch(
        [fireOperation],
        fire,
        () => console.log(`Ovo se poslalo: "${JSON.stringify([fireOperation])}", ali nista od toga`),
    );
});


function modalInit(modal, formFields, handler){
    modal.form({
        inline: false,
        fields: formFields,
        onSuccess: (event,fields) => {
            event.preventDefault();
            handler(fields);
            modal.modal('hide');
        }
    });

    $('.ui.dropdown')
        .dropdown();
}

$(document)
    .ready(function() {
        modalInit(addPetModal,petValidation,petTable.save.bind(petTable));
        modalInit(addReservationModal,reservationValidation,resTable.save.bind(resTable));
        $('.menu .item')
            .tab({'onVisible':function(tabpath){
                if(tabpath==='first'){
                    return;
                }
                if(tabpath === 'second'){
                    petTable.hideAll();
                    petTable.update();
                    return;
                }
                if(tabpath === 'third'){
                    resTable.hideAll();
                    resTable.update();
                }
            }})
        ;
    });


$('.action.icon')
  .popup();

