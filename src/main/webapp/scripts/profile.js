function deleteUser(userId) {
    var r = confirm("Jeste li sigurni da želite obrisati korisnika?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/users/' + userId + '/delete'
<<<<<<< HEAD
        }).then(() => {
            location.reload();
        });;
=======
        });
>>>>>>> 3bcf26512a1a8adf6f997a55f5f812e44b8d47c8
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