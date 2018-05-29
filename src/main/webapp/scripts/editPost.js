$(document).ready(function () {
    $('#multi-select')
        .dropdown()
        ;
    $('.ui.dropdown')
        .dropdown()
        ;

    var value = $('#selectValue').val();
    $('#select')
    .val(value).change()
    ;
}
)