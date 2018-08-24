$(document).ready(function () {


    $('#teacher').change(function () {

        var id = $(this).val();

        $.ajax({

            type: 'GET',
            data:{id: id},
            url: '/AjaxStudentTeacherCourseSignup',
            success: function (result) {
                $('#course').empty().append(result).selectric();
            }


        })



    })



});