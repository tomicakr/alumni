let Table= function(indexUrl, table, tableBody, placeholder, loader, confirmDelete, successMessage,errorMessage){
    this.indexUrl = indexUrl;
    this.table = table;
    this.tableBody = tableBody;
    this.placeholder = placeholder;
    this.loader = loader;
    this.confirmDelete = confirmDelete;
    this.isUpdating = false;
    this.successMessage = successMessage;
    this.errorMessage = errorMessage;
};

Table.prototype = {
    isEmpty:function(){
        return this.table.find('tbody tr').length === 0;
    },

    formatTableRow: function(...cells) {
        let result = '<tr>';
        result += cells.map(cell => `<td>${cell}</td>`).join('');
        result += '</tr>';
        return result;

    },

    getData: function(){
        return $.getJSON(this.indexUrl);
    },

    remove: function(row){
        let self = this;
        const deleteUrl = `${this.indexUrl}${row.data('id')}/`;
        confirm(this.confirmDelete,() => {
            $.ajax({
                url: deleteUrl,
                type: 'DELETE',
            }).then(() => {
                showSuccess(self.successMessage);
                row.remove();
                if (self.isEmpty()) {
                    self.showPlaceholder()
                }
            }).catch(() => showFailure.bind(self.errorMessage))
        });

    },

    update: function(){
        if(this.isUpdating){
            return;
        }
        this.load();
        this.isUpdating = true;
        this.tableBody.empty();
        this.getData()
            .then(entities => {
                if(entities.length===0){
                    this.showPlaceholder();
                    return;
                }
                entities.forEach(entity => this.append(entity));
                this.showContent();

            })
            .fail(console.log)
            .always(
                () => this.isUpdating = false
            );
    },

    save: function(fields) {
        console.log(fields)
        $.post({
            url: this.indexUrl,
            contentType: "application/json; charset=utf-8",
            cache: false,    //This will force requested pages not to be cached by the browser
            processData: false, //To avoid making query String instead of JSON
            data: JSON.stringify(fields)
        })
            .then(data => {
                this.append(data);
            })
            .catch(console.log);
    },

    load: function(){
        this.loader.addClass('active')
    },
    finishLoad: function(){
        this.loader.removeClass('active')
    },
    showContent: function(){
        this.finishLoad();
        hideElem(this.placeholder);
        showElem(this.table);
    },
    showPlaceholder: function(){
        this.finishLoad();
        hideElem(this.table);
        showElem(this.placeholder);
    },
    hideAll: function(){
        this.finishLoad();
        hideElem(this.table);
        hideElem(this.placeholder);
    }
};