var template = document.getElementById("postsTemplate").innerHTML;
var compiledTemplate = Handlebars.compile(template);
posts = document.getElementById("posts");



function filterBy(input){
  if (input === null) {
    $.get("/posts/all", function (data) {

      var generatedHtml = compiledTemplate(data);
    
      posts.innerHTML = generatedHtml;
      $('.ui.accordion')
        .accordion()
        ;
    });
  } else {

    $.get("/posts/all?type="+input, function (data) {
      posts.innerHTML = "";
      
      var generatedHtml = compiledTemplate(data);
      
      posts.innerHTML = generatedHtml;
      $('.ui.accordion')
      .accordion()
      ;
    });
  }
}

function comment(input) {
  alert(input);
}

/*
$.get("/links/all", function (data) {

  var template = document.getElementById("links").innerHTML;
  var compiledTemplate = Handlebars.compile(template);
  var generatedHtml = compiledTemplate(data);
  console.log(generatedHtml);
  console.log(data);
  console.log(template);

  document.getElementById("posts").innerHTML = generatedHtml;
  $('.ui.accordion')
    .accordion()
    ;
});
*/


$(document).ready(function () {
  $('.ui.sticky')
    .sticky({
      context: '#sticky-context'
    })
    ;

  $('.ui.accordion')
    .accordion()
    ;
  $('#multi-select')
    .dropdown()
    ;
    filterBy(null);
}
)

Handlebars.registerHelper('formatTime', function (date, format) {
  var mmnt = moment(date);
  return mmnt.format(format);
});

Handlebars.registerHelper('postTypeConversion', function (type) {
  if(type == 'EVENT') return "DOGAĐAJ";
  if(type == 'INFO') return "INFORMACIJA";
  if(type == 'LECTURE') return "PREDAVANJE";
});