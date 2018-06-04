var template = document.getElementById("postsTemplate").innerHTML;
var compiledTemplatePosts = Handlebars.compile(template);
posts = document.getElementById("posts");
var selectedPage = 0
var postsWeGot;

function filterBy(input) {
   
    if (input === null) {
        $('#ftext').text('Svi postovi');
        $.get("/posts/all", function (data) {
            posts.innerHTML = "";

            var generatedHtml = compiledTemplatePosts(data);
           
            posts.innerHTML = generatedHtml;
            $('.ui.accordion')
                .accordion()
                ;
            paginate('posts', 'pager', 3);
        });
    } else {
        $('#ftext').text(input);
        $.get("/posts/all?type=" + input, function (data) {
            posts.innerHTML = "";

            var generatedHtml = compiledTemplatePosts(data);

            posts.innerHTML = generatedHtml;
            $('.ui.accordion')
                .accordion()
                ;
            paginate('posts', 'pager', 3);
        });
    }
}

$(document).ready(function () {
    filterBy(null);
    paginate('posts', 'pager', 3);
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
        
        $.get("/categories/all", function (data) {
            
            $.each(data, function (i, category) {
                $('#categories').append($('<a>', {
                    class: "item",
                    value: category.name,
                    text: category.name
                }).on("click", () => filterBy(category.name)));
            });
            
        });
       
}
)

Handlebars.registerHelper('formatTime', function (date, format) {
    var mmnt = moment(date);
    return mmnt.format(format);
});