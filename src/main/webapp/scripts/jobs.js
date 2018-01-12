$('.ui.sidebar')
    .sidebar({
        context: $('.bottom.segment')
    })
    .sidebar('attach events', '.menu .item')
;


$('.trigger.example .accordion')
  .accordion({
    selector: {
      trigger: '.title .icon'
    }
  })
;

$('.ui.accordion')
  .accordion()
;
