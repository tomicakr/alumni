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


$('.ui.dropdown')
  .dropdown();


function searchFunction(searchColumn){

	$('.card').each(function() {
		var cardText = $(this).find('.' + $('#'+ searchColumn +'-label').find($('.item.active.selected')).attr('value'));
		if (cardText.parents('#'+ searchColumn).length) {
			if (cardText.text().toLowerCase().indexOf($('#my-input' + '-' + searchColumn).val().toLowerCase()) >= 0)
			{
				$(this).show();	
			}else{
				$(this).hide();
			}
		}
		
	});
}

$('.message .close')
  .on('click', function() {
    $(this)
      .closest('.message')
      .transition('fade')
    ;
  })
;

