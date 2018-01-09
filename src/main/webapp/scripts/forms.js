export  function initialize(fields){
    $(document)
        .ready(function() {
            $('.ui.form')
                .form({
                    fields: fields,
                    onSuccess: () => $('.submit.button').addClass('loading')
                })
            ;
            $('.ui.dropdown')
                .dropdown();
            $('.ui.accordion.field')
                .accordion();
        })
    ;
}