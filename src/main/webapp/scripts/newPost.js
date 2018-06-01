const rules = {
    title: {
        identifier: 'title',
        rules: [
            {
                type: 'empty',
                prompt: 'Upišite naslov'
            }
        ]
    },
    shortDescription: {
        identifier: 'shortDescription',
        rules: [
            {
                type: 'empty',
                prompt: 'Upišite kratki opis'
            }
        ]
    },
    categories: {
        identifier: 'categories',
        rules: [
            {
                type: 'empty',
                prompt: 'Odaberite kategoriju'
            }
        ]
    }
};

$(document)
    .ready(function () {
        $('.ui.form')
            .form({
                inline: true,
                fields: rules,
                onSuccess: () => $('.submit.button').addClass('loading')
            })
            ;

        var editor = new wysihtml5.Editor("wysihtml5-textarea", { // id of textarea element
            toolbar: "wysihtml5-toolbar", // id of toolbar element
            parserRules: wysihtml5ParserRules // defined in parser rules set 
        });

        $('#multi-select')
            .dropdown()
            ;
        $('.ui.dropdown')
            .dropdown()
            ;

        $('.message .close')
            .on('click', function () {
                $(this)
                    .closest('.message')
                    .transition('fade')
                    ;
            })
            ;

        $.get("/categories/all", function (data) {

            console.log(data);

            $.each(data, function (i, item) {
                $('#categories').append($('<option>', {
                    value: item.name,
                    text: item.name
                }));
            });
        });
    })
    ;

