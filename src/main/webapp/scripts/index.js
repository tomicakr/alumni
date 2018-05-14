$.get("/posts/all", function (data) {

  var template = document.getElementById("postsTemplate").innerHTML;
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


$(document).ready(function () {
  alert("ready");

  $('.ui.sticky')
    .sticky({
      context: '#sticky-context'
    })
    ;

  $('.ui.accordion')
    .accordion()
    ;
}
)
