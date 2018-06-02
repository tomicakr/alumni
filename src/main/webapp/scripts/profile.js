function deleteUser(userId) {
    var r = confirm("Jeste li sigurni da želite obrisati korisnika?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/users/' + userId + '/delete'
        }).then(() => {
            window.location.replace("/users");
            alert("Korisnik je obrisan.");
        });;
    }
}

function changeRole(newRole, userId) {
    var r = confirm("Jeste li sigurni da želite promijeniti ulogu?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/users/' + userId + '/changeRole?newRole=' + newRole
        }).then(() => {
            window.location.href = '/users/' + userId;
            alert("Uloga promijenjena.");
        });
    }
}