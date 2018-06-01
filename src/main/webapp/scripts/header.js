$(document).ready(function () {
    $('.ui.dropdown')
    .dropdown()
    ;

    

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