function deleteUser(userId) {
    var r = confirm("Jeste li sigurni da želite obrisati korisnika?");
    if (r == true) {
        $.ajax({
            type: 'POST',
            url: '/users/' + userId + '/delete'
        });
    }
}