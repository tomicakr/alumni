const userIndex           = window.location.href + (window.location.href.endsWith('/') ? '' : '/');
const petIndex            = `${userIndex}pets/`;
const reservationIndex    =`${userIndex}reservations/`;
const api = {
    species:'/api/species',
    employees: '/api/users?role=employee',
    pets: petIndex,
    service: '/api/services'
};

let employeeSettings =
    $(`<div class="ui hidden divider"></div>
            <div class="ui segment">
            <div>
                <h2 class="ui darkred left floated header">Postavke zaposlenika</h2>
                <h4 class="ui right floated header">
                    <i id="btn-edit-settings" class="setting action icon" title="Promijeni"></i>
                </h4>
            </div>
        
            <div id="employee-settings">
                <p id="availability">
                </p>
                <p id="notifications">
                </p>
            </div>
       </div>`);

const btnPets             = $('#btn-pets'           );
const btnReservations     = $('#btn-reservations'   );
const btnEdit             = $('#btn-edit-user'      );
const btnDelete           = $('#btn-delete-user'    );
const btnEmploy           = $('#btn-employ-user'    );
const btnFire             = $('#btn-fire-user'      );
const btnAddPet           = $('#btn-add-pet'        );

const btnAddReservation   = $('#btn-add-reservation');

const userRole      = $('#role');
const clientLabel   = $('<p class="ui client long tag label">Klijent</p>');

const employeeLabel = $('<p class="ui employee long tag label">Zaposlenik</p>');

const btnEmployeeJobs = $('<i id="btn-employe-jobs" class="industry action icon" title="Poslovi"></i>');

const employOperation = new Patch("replace", "/status","employee");
const fireOperation = new Patch("replace", "/status","client");

const transition = 250;

let petTable = new Table(
    petIndex,
    $('#pets-table'),
    $('#pets').find('tbody'),
    $('#pet-placeholder'),
    $('#pet-loader'),
    {
        title: 'Ukloni ljubimca',
        icon: 'trash',
        text: `Uklanjanje ljubimca uzrokovat će otkazivanje svih njegovih
                rezervacija, neovisno o razini. Jeste li sigurni da želite
                nastaviti?`
    },
    'Ljubimac je uspješno uklonjen.',
    'Došlo je do pogreške pri brisanju ljubimca.'
);
let appendPet = function(pet){
    let deleteButton = '<i class="big trash action del icon" title="Ukloni" data-position="right center"></i>';

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
    {
        title: 'Otkaži rezervaciju',
        icon: 'remove from calendar',
        text: 'Otkazivanje rezervacije ne uključuje povrat novaca, jeste li sigurni da želite nastaviti?'
    },
    'Rezervacija je uspješno otkazana.',
    'Došlo je do pogreške pri otkazivanju rezervacije.'
);

resTable.append = (function(res){
    let buttons = '';
    buttons = res.status==='Otvorena' ? buttons + '<i class="big edit action icon" title="Uredi" data-position="right center"></i>': buttons;
    buttons =buttons + '<i class="big remove from calendar del action icon" title="Otkaži" style="float: right;" data-position="right center"></i>';
    let resMarkup = $(this.formatTableRow(res.pet, res.service, res.employee, res.status, res.time, buttons));
    resMarkup.data('id',res.reservationId);
    resTable.tableBody.append(resMarkup);

    resMarkup.find('.del.icon').click(resTable.remove.bind(resTable,resMarkup));
    resMarkup.find('.edit.icon').click(() => window.location.href = `${reservationIndex}${res.reservationId}/edit`);
    resMarkup.find('.icon').popup();
    this.showContent();
}).bind(resTable);

btnPets.click(petTable.update.bind(petTable));
btnAddPet.click(() => {
        modalInit(
            addPetModal,
            petValidation,
            petTable.save.bind(petTable)
        );
        addPetModal.modal('show');
        $.getJSON(api.species)
            .then(
                data => fillDropDown(
                    addPetModal.find('#pet-species'),
                    x => x.id,
                    x => x.name,
                    x => x.name,
                    data)
            )
    }
);

btnAddReservation.click(
    () => {
        modalInit(
            addReservationModal,
            reservationValidation,
            resTable.save.bind(resTable)
        );

        addReservationModal.modal({
            autofocus: false
        }).modal('show');

        addReservationModal.find('#res-time')
            .calendar(dateInputConfig);

        addReservationModal.find('#res-duration')
            .calendar(timeInputConfig);

        $.getJSON(api.employees)
            .then(
                data => fillDropDown(
                    addReservationModal.find('#res-employee'),
                    x => x.userId,
                    x => `${x.firstName} ${x.lastName}`,
                    x => `${x.firstName} ${x.lastName}`,
                    data)
            );

        $.getJSON(api.service)
            .then(
                data => fillDropDown(
                    addReservationModal.find('#res-service'),
                    x => x.id,
                    x => x.name,
                    x => x.name,
                    data)
            );

        $.getJSON(api.pets)
            .then(
                data => fillDropDown(
                    addReservationModal.find('#res-pet'),
                    x => x.petId,
                    x => x.name,
                    x => x.name,
                    data)
            )
    }
);

btnReservations.click(resTable.update.bind(resTable));

btnDelete.click(function() {
    confirm({
        title: 'Brisanje korisničkog računa',
        icon: 'trash',
        text: 'Jeste li sigurni da želite obrisati korisnički račun?'
    },() => {
        $.ajax({
            type: 'DELETE',
            url: userIndex.substring(0,userIndex.length-1)
        })
            .then(function() {
                    showSuccess('Korisniči račun je uspješno obrisan.',
                        () => window.location.replace("/"));
                }
            )
            .catch(showFailure.bind(this,'Korisniči račun ne može se obrisati.'));
    });
});

btnEdit.click(
    () => window.location.href = `${userIndex}edit`
);

btnEmployeeJobs.click(
    () => window.location.href = `${userIndex}jobs`
);

employeeSettings.find('#btn-edit-settings').click(() =>
    {
        modalInit(settingsModal,
            availabilityValidation,
            setAvailability);
        settingsModal
            .modal({
                autofocus:false
            }).modal('show');
        settingsModal.find('.calendar').calendar(timeInputConfig);
    }
);
employeeSettings.find('#btn-edit-settings').popup();


function hire(){
    switchElements(btnEmploy, btnFire);
    (async () => userRole.find('p').fadeOut(125, function(){
        employeeLabel.hide();
        $(this).replaceWith(employeeLabel);
        employeeLabel.fadeIn(125);
    }))();
    (async () => btnEmployeeJobs.hide().prependTo('#user-actions').fadeIn(transition))();
    (async () => employeeSettings.hide().appendTo('#user-info').fadeIn(transition))();
    btnEmployeeJobs
        .popup();
    employeeSettings.find('.setting.icon')
        .popup();
}

function fire(){
    switchElements(btnFire, btnEmploy);
    (async () => userRole.find('p').fadeOut(transition/2, function(){
        clientLabel.hide();
        $(this).replaceWith(clientLabel);
        clientLabel.fadeIn(transition/2);
    }))();
    (async () => btnEmployeeJobs.fadeOut(transition, () => $(this).detach()))();
    (async () => employeeSettings.fadeOut(transition, () => $(this).detach()))();
}

function renderEmployeeSettings(availability, notifications){
    employeeSettings.find('#availability').html(availability);
    employeeSettings.find('#notifications').text(notifications);
}

btnEmploy.click(() => {
    patch(
        hire,
        () => console.log(`Ovo se poslalo: "${JSON.stringify([employOperation])}", ali nista od toga`),
        [employOperation]
    );
});

btnFire.click(() => {
    patch(
        fire,
        () => showFailure('Nije moguće otpustiti zaposlenika. Provjerite ima li aktivnih poslova.'),
        [fireOperation]
    );
});

$(document)
    .ready(function() {
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

