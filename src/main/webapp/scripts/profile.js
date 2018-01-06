const userUrl =window.location.href + (window.location.href.endsWith('/') ? '' : '/');
const reservationsTable = $('#reservations').find('table');
const petsTable = $('#pets').find('table');
const btnPets = $('#btn-pets');
const btnReservations = $('#btn-reservations');
const btnEdit = $('#btn-edit');
const btnDelete = $('#btn-delete');

function formatTableRow (...cells){
    let result = '<tr>';
    result += cells.map(cell => `<td>${cell}</td>`).join('');
    result +='</tr>';
    return result;
}

function appendReservation(res){
    let markup = $(formatTableRow(res.pet,res.service,res.employee,res.status,res.time));
    reservationsTable.append(markup);
}

function appendPet(pet){
    let markup = $(formatTableRow(pet.name,pet.age,pet.species,pet.breed,pet.sex,pet.microchip,pet.remark));
    petsTable.append(markup);
}

let getDataOn = entities => $.getJSON(`${userUrl}${entities}/`);

function updateReservatoins(){
    reservationsTable.empty();
    updateTable(getDataOn('reservations'), appendReservation)
}

function updatePets(){
    petsTable.empty();
    updateTable(getDataOn('pets'),appendPet)
}

function updateTable(jsonGetter,appender){
    return jsonGetter
        .then(entities => {
            entities.forEach(entity => appender(entity))
        })
        .fail(console.log);
}

btnPets.click(updatePets);
btnReservations.click(updateReservatoins);
btnDelete.click(function(){
   let decision = confirm("Jeste li sigurni da želite izbrisati korisnika?");
   if(!decision){
       return;
   }
    $.ajax({
        url: userUrl,
        type: 'DELETE'
    })
    ;
});

btnEdit.click(() => window.location.href = `${userUrl}edit`);
