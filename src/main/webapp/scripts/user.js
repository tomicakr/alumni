function deleteUser(userId) {
    var r = confirm("Jeste li sigurni da želite obrisati korisnika?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/users/' + userId + '/delete',
            complete: (obj, textStatus) => {
                if(textStatus === 'error') {
                    alert('Korisnik nije obrisan.');
                    return;
                }

                alert('Korisnik uspješno obrisan.');
                window.location.href = "/users";
            }
        });
    }
}

function changeRole(newRole, userId) {
    var r = confirm("Jeste li sigurni da želite promijeniti ulogu?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/users/' + userId + '/changeRole?newRole=' + newRole,
            complete: (obj, textStatus) => {
                if(textStatus === 'error') {
                    alert('Uloga nije promijenjena.');
                    return;
                }

                alert('Uloga uspješno promijenjena.');
                window.location.href = "/users" + userId;
            }
        });
    }
}