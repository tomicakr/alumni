export function initialize(fields){
    $(document)
        .ready(function() {
            alert("radi");
            $('.ui.form')
                .form({
                    inline: false,
                    fields: fields,
                    onSuccess: () => $('.submit.button').addClass('loading')
                })
            ;
        })
    ;
}