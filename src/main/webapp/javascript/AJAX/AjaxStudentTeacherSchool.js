$(document).ready(function () {


    $('#teacher').change(function () {

        var id = $(this).val();

        $.ajax({

            type: 'GET',
            data:{id: id},
            url: '/AjaxStudentTeacherSchoolSignup',
            success: function (result) {
                $('#school').empty().append(result).selectric();
            }


        })



    })



});