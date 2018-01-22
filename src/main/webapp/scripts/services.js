let formatEntry = s =>
    `<div class="item">
                <div class="right floated content">
                    <div class="content">${s.price} kuna</div>
                </div>
                <div class="content">
                    <strong>${s.name}</strong>
                </div>
            </div>`;

const serviceList = $('#service-list');
$('.item#services').click(function(){
    let serviceTable = serviceList.find('.list');
    serviceTable.empty();
    $.ajax('/api/services')
        .then(
            services => services.forEach(
                s => serviceTable.append(formatEntry(s))
            )
        );
    serviceList.modal({observeChanges: true}).modal('show');
});