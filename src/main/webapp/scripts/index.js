var template = document.getElementById("postsTemplate").innerHTML;
var compiledTemplate = Handlebars.compile(template);
posts = document.getElementById("posts");
var selectedPage = 0
var postsWeGot;

function getArchived() {
    $.get("/posts/archived", function (data) {
        postsWeGot = data;
        var generatedHtml = compiledTemplate(data);

        posts.innerHTML = generatedHtml;
        $('.ui.accordion')
            .accordion()
            ;
        paginate('posts', 'pager', 3);
    });
}



function filterBy(input) {
    if (input === null) {
        $.get("/posts/all", function (data) {
            posts.innerHTML = "";

            var generatedHtml = compiledTemplate(data);

            posts.innerHTML = generatedHtml;
            $('.ui.accordion')
                .accordion()
                ;
            paginate('posts', 'pager', 3);
        });
    } else {

        $.get("/posts/all?type=" + input, function (data) {
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
    filterBy(null);
    paginate('posts', 'pager', 3);

    $.get("/categories/all", function (data) {

        console.log(data);

        $.each(data, function (i, category) {
            $('#categories').append($('<a>', {
                class: "item",
                value: category.name,
                text: category.name
            }).on("click", () => filterBy(category.name)));
        });

    });

    $.get("/links/all", function (data) {
        console.log("linkovi:");
        console.log(data);

        $.each(data, function (i, link) {
            console.log(link);
            $('#links').append($('<a>', {
                value: link.title,
                class: "item",
                href: link.url,
                text: link.title
            }));
        });
    });
}
)

Handlebars.registerHelper('formatTime', function (date, format) {
    var mmnt = moment(date);
    return mmnt.format(format);
});

Handlebars.registerHelper('postTypeConversion', function (type) {
    if (type == 'EVENT') return "DOGAĐAJ";
    if (type == 'INFO') return "INFORMACIJA";
    if (type == 'LECTURE') return "PREDAVANJE";
});