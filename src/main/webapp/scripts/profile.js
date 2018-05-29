function deleteUser(userId) {
    var r = confirm("Jeste li sigurni da želite obrisati korisnika?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/users/' + userId + '/delete'
        }).then(() => {
            window.location.replace("/users");
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
            location.reload();
        });
    }
}