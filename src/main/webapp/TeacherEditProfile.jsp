<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="model.Teacher" %>
<%@ page import="dao.TeacherDao" %>
<%@ page import="org.apache.commons.lang.WordUtils" %><%--
  Created by IntelliJ IDEA.
  User: Sunil Reddy
  Date: 13-06-2018
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%

    if (session.getAttribute("teacheruser") != null && session.getAttribute("teacherpassword") != null)
    {
        try
        {
            Teacher teacher = new Teacher();
            teacher.setTeacherid(Integer.parseInt(session.getAttribute("sessionteacherid").toString()));

            TeacherDao teacherDao = new TeacherDao();
            boolean check = teacherDao.getAllTeacherInfo(teacher);

            if (check == true)
            {

%>
<html>
<head>

    <link rel="stylesheet" href="mycss/adminsignup.css">

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->

    <link href="https://fonts.googleapis.com/css?family=Montserrat|Muli" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="mycss/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="images/panda.ico" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="javascript/Scroll/selectric.css">


    <title>Teacher Profile Update Form</title>
</head>
<body oncontextmenu="return false;">

<div id="load_screen"><div id="loading"></div></div>

<!-- HEADER-NAVBAR -->
<header id="main-header">

    <nav class="navbar navbar-expand-md navbar-light bg-white fixed-top">


        <a class="navbar-brand" href="index.html"><span>Student</span>Diaries</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="index.html">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="AboutUs.jsp#services">Services</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="AboutUs.jsp">About</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span id="fullname"><%= StringUtils.capitalize(session.getAttribute("sessionteacherfirstname").toString()) %> <%= StringUtils.capitalize(session.getAttribute("sessionteacherlastname").toString()) %></span></a>
                    <div class="dropdown-menu" aria-labelledby="dropdown01">
                        <a class="dropdown-item" href="/TeacherLogout">Logout</a>

                    </div>
                </li>

            </ul>

        </div>
    </nav>

</header>
<br>

<div class="container heading">
    <h1>Teacher Profile Update Form</h1>
</div>
<br>

<section>
    <div class="container signupform">
        <div class="row">

            <div class="col">
                <form action="/TeacherProfileUpdation" method="POST" id="myform2">
                    <input type="hidden" name="fno" value="2">
                    <div class="form-row">

                        <div class="form-group col-md-6">
                            <label>Teacher ID</label>
                            <input type="text" class="form-control" id="teacherid" name="teacherid" placeholder="Teacher ID" value="<%= teacher.getTeacherid() %>" readonly>
                            <p id="paraid" class="validation"></p>
                        </div>


                        <div class="form-group col-md-6">
                            <label>First Name</label>
                            <input type="text" class="form-control" id="firstname" name="firstname" placeholder="First Name" value="<%= StringUtils.capitalize(teacher.getTeacherfirstname()) %>" required>
                            <p id="parafirstname" class="validation"></p>
                        </div>
                        <div class=" form-group col-md-6">
                            <label>Last Name</label>
                            <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Last Name" value="<%= StringUtils.capitalize(teacher.getTeacherlastname()) %>" required>
                            <p id="paralastname" class="validation"></p>
                        </div>

                        <div class=" form-group col-md-6">
                            <label>Email ID</label>
                            <input type="email" class="form-control" name="teacheremail" id="email" placeholder="Email Address" value="<%= teacher.getTeacheremailid() %>" readonly>
                            <p id="paraemail" class="validation"></p>
                        </div>

                        <div class=" form-group col-md-6">
                            <label>Phone No.</label>
                            <input type="text" class="form-control" name="teacherphone" id="phone" placeholder="Phone No." value="<%= teacher.getTeacherphoneno() %>" required>
                            <div class="form-inline">
                                <p id="paraphone" class="validation"></p>
                                <p id="phoneajax" class="validation"></p>
                            </div>
                        </div>


                        <div class=" form-group col-md-6">
                            <label>Reference Admin ID</label>
                            <input type="text" class="form-control" name="adminid" id="adminid" placeholder="Reference Admin ID" value="<%= teacher.getTeacheradminid() %>" readonly>
                        </div>



                        <div class=" form-group col-lg-12">

                            <label>School/College</label>
                            <input type="text" class="form-control" name="school" id="school" placeholder="School" value="<%= WordUtils.capitalizeFully(teacher.getTeacherschool()) %>" readonly>

                        </div>


                        <div class=" form-group col-lg-12">
                            <label>Course Name</label>
                            <select class="form-control" name="course" id="course" required>
                                <option value="<%= WordUtils.capitalizeFully(teacher.getTeachercoursename()) %>"><%= WordUtils.capitalizeFully(teacher.getTeachercoursename()) %></option>
                                <option>Bachelor of Commerce</option>
                                <option>Master of Science</option>
                                <option>Bachelor of Business Administration</option>
                                <option>Bachelor of Computer Application</option>
                                <option>Bachelor of Engineering</option>
                                <option>Pharmacy</option>
                                <option>Bachelor of Technology</option>
                                <option>Diploma in Computer Management</option>
                                <option>Master of Computer Application</option>
                                <option>Master of Business Management</option>
                                <option>Bachelor of Science</option>
                            </select>
                        </div>


                        <div class="col-md-6 final">
                            <a href="TeacherProfileSettings.jsp" class="btn btn-primary">Go Back</a>
                        </div>
                        <div class="col-md-6 final d-flex justify-content-end">
                            <button type="submit" id="signup" class="btn btn-success" disabled>Update</button>
                        </div>


                    </div>
                </form>
            </div>
        </div>
    </div>
</section>



<!-- JS APIs-->


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script
        src="https://code.jquery.com/jquery-3.2.1.js"
        integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous"></script>

<script src="javascript/adminsignup/adminFirstNameValid.js"></script>
<script src="javascript/adminsignup/adminLastNamevalid.js"></script>
<script src="javascript/adminsignup/adminPhoneValid.js"></script>
<script src="javascript/adminsignup/singleformsubmit.js"></script>


<!--DB VERIFICATION-->
<script src="javascript/DBVerification/TeacherPhoneDBVerification.js"></script>

<!--Select Scroll-->
<script src="javascript/Scroll/jquery.selectric.js"></script>


<!--Select Scroll-->
<script>
    $(function() {
        $('select').selectric({
            maxHeight: 150
        });
    });
</script>

</body>
<!--LOADER-->
<script>
    window.addEventListener("load", function(){
        var load_screen = document.getElementById("load_screen");
        document.body.removeChild(load_screen);
    });
</script>

</html>

<%
            }
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