$(document).ready(function(){


        $.ajax({

            type: 'GET',
            url: '/AjaxTeacherAdminId',
            success: function (data) {

                $('#adminid').append(data).selectric();
            }

        })




});