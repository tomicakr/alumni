var template = document.getElementById("postsTemplate").innerHTML;
var compiledTemplate = Handlebars.compile(template);
posts = document.getElementById("posts");
var selectedPage = 0
var postsWeGot;

function filterArchivedBy(input) {
   
    if (input === null) {
        $('#ftext').text('Svi arhivirani postovi');
        $.get("/posts/all?archived=1", function (data) {
            posts.innerHTML = "";

            var generatedHtml = compiledTemplate(data);
            console.log(data)
            posts.innerHTML = generatedHtml;
            $('.ui.accordion')
                .accordion()
                ;
            paginate('posts', 'pager', 3);
        });
    } else {
        $('#ftext').text(input);
        $.get("/posts/all?type=" + input + "&archived=1", function (data) {
            posts.innerHTML = "";

            var generatedHtml = compiledTemplate(data);

            posts.innerHTML = generatedHtml;
            $('.ui.accordion')
                .accordion()
                ;
            paginate('posts', 'pager', 3);
        });
    }
}

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
    $('.ui.dropdown')
    .dropdown()
    ;
    filterArchivedBy(null);
    paginate('posts', 'pager', 3);
    $.get("/categories/all", function (data) {
        console.log("podaci:");
        console.log(data);
        
        $.each(data, function (i, category) {
            $('#categories').append($('<a>', {
                class: "item",
                value: category.name,
                text: category.name
            }).on("click", () => filterArchivedBy(category.name)));
        });
        
    });
       
}
)

Handlebars.registerHelper('formatTime', function (date, format) {
    var mmnt = moment(date);
    return mmnt.format(format);
});