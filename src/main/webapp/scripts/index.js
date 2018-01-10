
// $('#btn-order-service').click(
//     ()=>window.location.href = "/sessions/new"
// );
$(document)
    .ready(function() {

        // create sidebar and attach to menu open
        $('.ui.sidebar')
            .sidebar('attach events', '.toc.item')
        ;

    })
;