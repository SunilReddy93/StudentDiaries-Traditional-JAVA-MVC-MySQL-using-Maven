$(document).ready(function () {


        $('#adminid').change(function () {

            var id = $(this).val();

            $.ajax({

                type: 'GET',
                data: {id: id},
                url: '/AjaxTeacherAdminSchoolSignup',
                success: function (result) {
                    $('#school').empty().append(result).selectric();

                }
            })

        })




});