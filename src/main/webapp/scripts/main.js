$(document)
 .ready(function() {
     $('.ui.menu a.item')
         .on('click', function() {
             $(this)
                 .addClass('active')
                 .siblings()
                 .removeClass('active')
             ;
         })
     ;
 })
;