<%@ page import="model.Student" %>
<%@ page import="dao.StudentDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="org.apache.commons.lang.WordUtils" %><%--
  Created by IntelliJ IDEA.
  User: Sunil Reddy
  Date: 12-06-2018
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    //session = request.getSession(false);


    if (session.getAttribute("teacheruser") != null && session.getAttribute("teacherpassword") != null)
    {
        try
        {

%>


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- DATEPICKER CSS & JS-->

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.css"/>

<!-- Bootstrap CSS -->
<link href="https://fonts.googleapis.com/css?family=Montserrat|Muli" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="mycss/style.css">
<link rel="shortcut icon" type="image/x-icon" href="images/panda.ico" />
<link rel="stylesheet" href="mycss/adminsignup.css">


<%
    int id = Integer.parseInt(request.getParameter("id"));

    Student student = new Student();
    student.setStudentid(id);

    StudentDao studentDao = new StudentDao();
    ArrayList<Student> students;

    students = studentDao.getAllStudentInfo(student);

    for (int i = 0; i < students.size() ; i++)
    {

%>




<section class="checkfeedback">
    <div class="container">
        <div class="form-inline">

            <div class="col-md-3">
                <h3><span><%= StringUtils.capitalize(students.get(i).getStudentfirstname()) %>'s </span>Profile</h3>
            </div>

            <div class="d-flex justify-content-end float-right ml-auto">
                <form class="form-inline" action="TeacherCheckFeedback.jsp" method="post" >
                    <input type="hidden" name="studentid" value="<%= students.get(i).getStudentid() %>">                       <div class="mr-2">
                    <small class="align-items-end">Select Date to check feedback:</small>
                </div>
                    <div class="input-group mr-2">
                        <main>
                            <input type="text" id="date" name="date" class="form-control" placeholder="DD/MM/YYYY" autocomplete="off" required aria-label="DD/MM/YYYY" aria-describedby="basic-addon2" />
                        </main>
                    </div>
                    <div >
                        <button type="submit" class="btn btn-success align-items-end">Check</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<br>


<section>
    <div class="container signupform">
        <div class="row">

            <div class="col">

                <div class="form-row">

                    <div class=" form-group col-lg-12">
                        <label>Student ID</label>
                        <input type="text" class="form-control" id="studentid" name="studentid" placeholder="Student ID" value="<%= students.get(i).getStudentid() %>" readonly>

                    </div>

                    <div class="form-group col-md-6">
                        <label>First Name</label>
                        <input type="text" class="form-control" id="firstname" name="firstname" placeholder="First Name" value="<%= StringUtils.capitalize(students.get(i).getStudentfirstname()) %>" readonly>

                    </div>

                    <div class=" form-group col-md-6">
                        <label>Last Name</label>
                        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Last Name" value="<%= StringUtils.capitalize(students.get(i).getStudentlastname()) %>" readonly>

                    </div>
                    <div class="form-group col-md-6">
                        <label>Father Name</label>
                        <input type="text" class="form-control" id="fathername" name="fathername" placeholder="Father Name" value="<%= StringUtils.capitalize(students.get(i).getStudentfathername()) %>" readonly>

                    </div>
                    <div class=" form-group col-md-6">
                        <label>Mother Name</label>
                        <input type="text" class="form-control" id="mothername" name="mothername" placeholder="Mother Name" value="<%= StringUtils.capitalize(students.get(i).getStudentmothername()) %>" readonly>

                    </div>
                    <div class=" form-group col-md-6">
                        <label>Student Email ID</label>
                        <input type="email" class="form-control" id="email" name="studentemail" placeholder="Student Email Address" value="<%= StringUtils.capitalize(students.get(i).getStudentemailid()) %>" readonly>

                    </div>
                    <div class=" form-group col-md-6">
                        <label>Parent Email ID</label>
                        <input type="email" class="form-control" id="parentemail" name="parentemail" placeholder="Parent Email Address" value="<%= StringUtils.capitalize(students.get(i).getParentemailid()) %>" readonly>

                    </div>

                    <div class=" form-group col-md-6">
                        <label>Student Phone No.</label>
                        <input type="text" class="form-control" id="phone" name="studentphone" placeholder="Student Phone No." value="<%= students.get(i).getStudentphoneno() %>" readonly>

                    </div>
                    <div class=" form-group col-md-6">
                        <label>Parent Phone No.</label>
                        <input type="text" class="form-control" name="parentphone" id="parentphone" placeholder="Parent Phone No." value="<%= students.get(i).getParentphoneno() %>" readonly>

                    </div>


                    <div class=" form-group col-md-6">
                        <label>Student Roll No.</label>
                        <input type="text" class="form-control" id="rollno" name="rollno" placeholder="Student Roll No." value="<%= students.get(i).getStudentrollno() %>" readonly>

                    </div>


                    <div class=" form-group col-md-6">
                        <label>Teacher ID</label>
                        <input type="text" class="form-control" id="teacher" name="teacher" placeholder="Teacher ID" value="<%= students.get(i).getStudentteacherid() %>" readonly>
                    </div>

                    <div class=" form-group col-md-6">
                        <label>Course Name</label>
                        <input type="text" class="form-control" id="course" name="course" placeholder="Course" value="<%= WordUtils.capitalizeFully(students.get(i).getStudentcoursename()) %>" readonly>
                    </div>

                    <div class=" form-group col-md-6">
                        <label>Year</label>
                        <input type="text" class="form-control" id="year" name="year" placeholder="Year" value="<%= students.get(i).getStudentyear() %>" readonly>
                    </div>

                    <div class=" form-group col-lg-12">
                        <label>School/College</label>
                        <input type="text" class="form-control" id="school" name="school" placeholder="School" value="<%= WordUtils.capitalizeFully(students.get(i).getSchoolname()) %>" readonly>
                    </div>


                    <div class="col-md-12">
                        <a href="TeacherStudentUsers.jsp" id="goback"><button type="submit"  class="btn btn-primary">Go Back</button></a>
                    </div>

                </div>

            </div>
        </div>
    </div>
</section>

<%
    }

%>


<br><br>

<script
        src="https://code.jquery.com/jquery-3.2.1.js"
        integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous">

</script>

<!--DATEPICKER-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>


<script>
    $(document).ready(function(){
        var date_input=$('input[name="date"]'); //our date input has the name "date"
        var container=$('.checkfeedback main').length>0 ? $('.checkfeedback main').parent() : "body";
        var options={
            format: 'dd/mm/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        };
        date_input.datepicker(options);
    })
</script>

<%


        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.sendRedirect("TeacherUnknownError.jsp");
        }


    }
    else
    {
        response.sendRedirect("TeacherLogin.jsp");
    }
%>
