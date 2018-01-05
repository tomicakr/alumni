 $('.item').on('click', function() {
      $('.item').removeClass('active');
      $(this).addClass('active');
   }); 


var items = document.getElementsByClassName("item");
var title = document.title.toString();
for ( i = 0; i < items.length; i++) {
	if(items[i].name == title){
		items[i].className +=" active";
	}
}

