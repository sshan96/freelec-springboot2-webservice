var main = {
    init: function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },

    update: function () {
        var data = {
            email: $('#email').val(),
            name: $('#name').val(),
            role: $('#role').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/admin/users/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('해당 회원 정보를 수정했습니다.');
            window.location.href = '/admin';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })

    },

    delete: function () {

        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/admin/users/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('해당 회원을 삭제했습니다.');
            window.location.href = '/admin';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};

main.init();