$(document).ready(function(){


    $.ajax({

        type: 'GET',
        url: '/AjaxStudentTeacherIdSignup',
        success: function (data) {
            $('#teacher').append(data).selectric();
        }

    })





});