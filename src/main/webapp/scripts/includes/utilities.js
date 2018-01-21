function hideElem(e){
    e.addClass('inactive');
}

function showElem(e) {
    e.removeClass('inactive');
}

function switchElements(toHide, toShow) {
    hideElem(toHide);
    showElem(toShow);
}

function Patch(op, path, value){
    this.op = op;
    this.path = path;
    this.value = value;
}

function patch(onSuccess, onFail, ...data){
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