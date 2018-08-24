<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="model.Admin" %>
<%@ page import="dao.AdminDao" %><%--
  Created by IntelliJ IDEA.
  User: Sunil Reddy
  Date: 12-06-2018
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    if (session.getAttribute("adminuser") != null && session.getAttribute("password") != null)
    {
        try
        {
            Admin admin = new Admin();
            admin.setAdminid(Integer.parseInt(session.getAttribute("sessionadminid").toString()));

            AdminDao adminDao = new AdminDao();
            boolean check = adminDao.getAllAdminInfo(admin);

            if (check == true)
            {

%>
<html>
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat|Muli" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="mycss/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="images/panda.ico" />
    <link rel="stylesheet" href="mycss/adminsignup.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>Admin | Edit Profile</title>
</head>
<body oncontextmenu="return false;">
<div id="load_screen"><div id="loading"></div></div>

<!-- HEADER -->
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
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span id="fullname"><%= StringUtils.capitalize(session.getAttribute("sessionadminfirstname").toString()) %> <%= StringUtils.capitalize(session.getAttribute("sessionadminlastname").toString())%></span></a>
                    <div class="dropdown-menu" aria-labelledby="dropdown01">
                        <a class="dropdown-item" href="/AdminLogout">Logout</a>

                    </div>
                </li>
            </ul>

        </div>
    </nav>

</header>
<div class="container heading">
    <h1><span>Edit </span>Profile</h1>
</div>
<br>

<section>
    <div class="container signupform">
        <div class="row">

            <div class="col">
                <form action="/AdminUpdation" method="post" id="myform">

                    <div class="form-row">

                        <div class="form-group col-md-6">
                            <label id="labeladminid">Admin ID</label>
                            <input type="text" class="form-control" name="id" id="id" value="<%= admin.getAdminid() %>" readonly>
                            <p id="paraid" class="validation"></p>
                        </div>

                        <div class="form-group col-md-6">
                            <label id="labelfirstname">First Name</label>
                            <input type="text" class="form-control" name="firstname" placeholder="First Name" id="firstname" value="<%= StringUtils.capitalize(admin.getAdminfirstname()) %>" required>
                            <p id="parafirstname" class="validation"></p>
                        </div>
                        <div class=" form-group col-md-6">
                            <label id="labellastname">Last Name</label>
                            <input type="text" class="form-control" name="lastname" placeholder="Last Name" id="lastname" value="<%= StringUtils.capitalize(admin.getAdminlastname()) %>" required>
                            <p id="paralastname" class="validation"></p>
                        </div>
                        <div class=" form-group col-lg-12">
                            <label id="labelemail">Email</label>

                            <input type="email" class="form-control" name="email" placeholder="Email Address" id="email" value="<%= admin.getAdminemailid() %>" readonly>
                            <p id="paraemail" class="validation"></p>
                        </div>

                        <div class=" form-group col-md-6">
                            <label id="labelphone">Phone No.</label>
                            <input type="text" class="form-control" name="phone" placeholder="Phone Number" id="phone" value="<%= admin.getAdminphoneno() %>" required>
                            <p id="paraphone" class="validation"></p>
                            <p id="phoneajax" class="validation"></p>
                        </div>

                        <div class=" form-group col-md-6">
                            <label id="labelcity">City</label>
                            <input type="text" class="form-control" name="city" placeholder="City" id="city" value="<%= admin.getAdmincity() %>" required>
                            <p id="paracity" class="validation"></p>
                        </div>


                        <div class=" form-group col-md-12">
                            <label id="labelschool">School/College Name</label>
                            <input type="text" class="form-control" name="school" placeholder="School/College" id="school" value="<%= admin.getAdminschoolname() %>" required>
                            <p id="paraschool" class="validation"></p>
                        </div>

                        <div class="col-md-6 final">
                            <a href="AdminProfileSettings.jsp" class="btn btn-primary">Go Back</a>
                        </div>


                        <div class="col-md-6 final d-flex justify-content-end">
                            <button type="submit" class="btn btn-success" id="signup">Update</button>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<br><br>

<section id="footer">
    <div class="container">
        <footer class="container">
            <p class="float-right"><a href="#main-header"> Back to top</a></p>
            <p class="float-right"><a href="AboutUs.jsp#contact">Contact Us</a></p>
            <p>&copy; StudentDiaries, Inc. 2017-2018 &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
        </footer>
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
<script src="javascript/adminsignup/adminPasswordValid.js"></script>
<script src="javascript/adminsignup/singleformsubmit.js"></script>

<!--DB VERIFICATION-->
<script src="javascript/DBVerification/AdminPhoneDBVerification.js"></script>

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
            response.sendRedirect("AdminUnknownError.jsp");
        }


    }
    else
    {
        response.sendRedirect("AdminLogin.jsp");

    }

%>