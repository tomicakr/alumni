let settingsModal=
    $(`<div id="availability-modal" class="ui mini modal">
        <i class="close icon"></i>
        <div class="header">Postavke</div>
        <div class="ui center aligned content">
            <form id="availability-form" class="ui large form">
                <div class="ui calendar field" id="not-available-from">
                    <div class="ui input left icon">
                        <i class="clock icon"></i>
                        <input type="text" name="notAvailableFrom" placeholder="Nedostupan od">
                    </div>
                </div>
                <div class="ui calendar field" id="not-available-to">
                    <div class="ui input left icon">
                        <i class="clock icon"></i>
                        <input type="text" name="notAvailableTo" placeholder="Nedostupan do">
                    </div>
                </div>
                <div class="field" id="email-setting">
                <label>Obavijesti elektroničkom poštom</label>
                    <select name="emailSetting" class="ui dropdown">
                      <option value="0">Ne obavještavaj me</option>
                      <option value="1">Za preferencijalne poslove</option>
                      <option value="2">Za sve poslove</option>
                    </select>
                </div>
                <div class="ui fluid huge darkred submit button" style="width: 100%">Spremi promjene</div>
            </form>
        </div>
    </div>`);

let addPetModal =
    $(`<div id="add-pet-modal" class="ui mini modal">
        <i class="close icon"></i>
        <div class="header">Dodaj ljubimca</div>
        <div class="ui center aligned content">
            <form id="add-pet-form" class="ui large form">
                <div class="field">
                    <input type="text" name="name" id="pet-name" placeholder="Ime">
                </div>
                <div class="two equal width fields">
                    <div class="field">
                        <input type="number" name="age" id="pet-age" placeholder="Starost">
                    </div>
                    <div class="field">
                        <div class="ui dropdown selection" tabindex="0">
                            <select name="sex" id="gender">
                                <option value="">Spol</option>
                                <option value="M">Dečko</option>
                                <option value="F">Cura</option>
                            </select><i class="dropdown icon"></i>
                            <div class="default text">Spol</div>
                            <div class="menu transition hidden" tabindex="-1">
                                <div class="item" data-value="M">Dečko</div>
                                <div class="item" data-value="F">Cura</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="field">
                    <div class="required field">
                        <select name="species" class="ui dropdown" id="pet-species">
                        </select>
                    </div>
                </div>
                <div class="field">
                    <input type="text" name="microchip" id="pet-chip"
                           placeholder="Broj mikročipa ljubimca.">
                </div>
                <div class="field">
					<textarea rows="4" name="remark" id="remark"
                              placeholder="Napomene..."></textarea>
                </div>
                <div class="ui fluid huge darkred submit button" style="width: 100%">Dodaj</div>
            </form:form>
        </div>
    </div>`);

let addReservationModal =
    $(`<div id="add-reservation-modal" class="ui mini modal">
        <i class="close icon"></i>
        <div class="header">Nova rezervacija</div>
        <div class="ui center aligned content">
            <form id="add-pet-form" class="ui large form">
                <div class="ui calendar field" id="res-time">
                    <div class="ui input left icon">
                        <i class="calendar icon"></i>
                        <input type="text" name="executionTime" placeholder="Odaberite termin">
                    </div>
                </div>
                <div class="field">
                    <div class="required field">
                        <select name="service" class="ui dropdown" id="res-service">
                        </select>
                    </div>
                </div>
                <div class="field">
                    <div class="required field">
                        <select name="pet" class="ui dropdown" id="res-pet">
                        </select>
                    </div>
                </div>
                <div class="field">
                    <div class="required field">
                        <select name="employee" class="ui dropdown" id="res-employee">
                        </select>
                    </div>
                </div>
                <div class="ui calendar field" id="res-duration">
                    <div class="ui input left icon">
                        <i class="clock icon"></i>
                        <input type="text" name="duration" placeholder="Trajanje">
                    </div>
                </div>
                <div class="field">
                    <div class="ui checked checkbox">
                        <input type="checkbox" name="sendReminder" value="1" checked="checked">
                        <label>Želim dobiti podsjetnik na mail?</label>
                    </div>
                </div>
                <div class="ui fluid huge darkred submit button" style="width: 100%">Rezerviraj</div>
            </form>
        </div>
    </div>`);

let confirmationModal =
    $(`<div id="confirmation-modal" class="ui small basic first coupled modal transition hidden">
            <div class="ui icon header">
                <i></i><h2></h2>
            </div>
            <div class="content">
                <p></p>
            </div>
            <div class="actions">
                <div class="ui green basic cancel inverted button">
                    <i class="remove icon"></i> Ne
                </div>
                <div class="ui red basic ok inverted button">
                    <i class="checkmark icon"></i> Da
                </div>
            </div>
        </div>`);

let successModal =
    $(`<div id="success-modal" class="ui small basic second coupled modal transition hidden">
            <div class="ui icon header">
                <i class="green checkmark icon"></i><h2></h2>
            </div>
            <div class="ui actions">
                <div class="ui basic ok inverted button">U redu</div>
            </div>
        </div>`);

let failureModal =
    $(`<div id="failure-modal" class="ui small basic second coupled modal transition hidden">
            <div class="ui icon header">
                <i class="red remove icon"></i><h2></h2>
            </div>
            <div class="ui actions">
                <div class="ui basic ok inverted button">U redu</div>
            </div>
        </div>`);


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

const availabilityValidation = {
    notAvailableFrom: {
        identifier: 'notAvailableFrom',
    },
    notAvailableTo: {
        identifier: 'notAvailableTo',
    },
    emailSetting: {
        identifier: 'emailSetting',
    }
};

function confirm(content, approveAction){
    let header = confirmationModal.find('.ui.icon.header');
    header.find('i').removeClass().addClass(content.icon).addClass('icon');
    header.find('h2').text(content.title);
    confirmationModal.find('.content p').text(content.text);

    confirmationModal.addClass('first coupled');
    confirmationModal.modal({
        allowMultiple: true
    });

    confirmationModal.modal({
            onApprove : approveAction
        })
        .modal('show');
}

function showSuccess(successMessage = 'Uspješno obavljeno.',closeAction){
    successModal.find('.header h2').text(successMessage);
    successModal.modal({
        onHide: closeAction
    }).modal('show');
}

function showFailure(failureMessage = 'Došlo je do pogreške'){
    console.log('tu sam');
    failureModal.find('.header h2').text(failureMessage);
    failureModal.modal('show');
}

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

    modal.find('.ui.dropdown')
        .dropdown();
}

function setAvailability(formFields){
    let patchFields = Object.getOwnPropertyNames(formFields).map(
        prop => new Patch('replace',prop, formFields[prop])
    );
    patch(
        () => console.log(`Ovo se poslalo: "${JSON.stringify(patchFields)}" i server kaze da je dobro`),
        () => console.log(`Ovo se poslalo: "${JSON.stringify(patchFields)}", ali nista od toga`),
        fireOperation
    )

}