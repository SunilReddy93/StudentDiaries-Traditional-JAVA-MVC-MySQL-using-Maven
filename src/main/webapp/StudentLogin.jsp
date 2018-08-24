<%--
  Created by IntelliJ IDEA.
  User: Sunil Reddy
  Date: 11-06-2018
  Time: 01:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%


    if (session.getAttribute("adminuser") != null && session.getAttribute("password") != null)
    {
        response.sendRedirect("AdminLogoutWarning.jsp");
    }
    else if (session.getAttribute("studentuser") != null && session.getAttribute("studentpassword") != null)
    {
        response.sendRedirect("StudentDashboard.jsp");
    }
    else if (session.getAttribute("teacheruser") != null && session.getAttribute("teacherpassword") != null)
    {
        response.sendRedirect("TeacherLogoutWarning.jsp");
    }
    else
    {

%>



<!DOCTYPE html>
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
    <link rel="stylesheet" href="mycss/Login.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <title>Student Login | StudentDiaries</title>
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
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Login</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown01">
                        <a class="dropdown-item" href="AdminLogin.jsp">Admin</a>
                        <a class="dropdown-item" href="TeacherLogin.jsp">Teacher</a>
                        <a class="dropdown-item" href="StudentLogin.jsp">Student</a>
                    </div>
                </li>
            </ul>

        </div>
    </nav>
</header>

<div class="container heading">

    <%
        if (session.getAttribute("StudentLoginIncorrectCredentials") != null)
        {
    %>
    <p id="error"><%= session.getAttribute("StudentLoginIncorrectCredentials").toString() %></p>
    <%
        session.removeAttribute("StudentLoginIncorrectCredentials");
    }
    else if (session.getAttribute("PasswordChangeSuccess") != null)
    {
    %>
    <p style="color: green; margin-left: 40%"><%= session.getAttribute("PasswordChangeSuccess").toString() %></p>

    <%
            session.removeAttribute("PasswordChangeSuccess");
        }
    %>


    <h1>Student Login</h1>
</div>
<br>

<section>
    <div class="container loginform">
        <div class="row">
            <div class="col-md-6">
                <img class = "img-fluid w-100 img-responsive" src="images/AdminLogin.jpg">
                <p class="caption">Explore infinite possibilities.</p>
            </div>
            <div class="col-md-6">
                <form class=" innerform" action="/LoginVerificationServlet" method="POST" >
                    <input type="hidden" name="fno" value="3">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" class="form-control" placeholder="Email" aria-describedby="emailhelpinline">
                        <small id="emailhelpinline" class="text-muted">

                        </small>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="Password" aria-describedby="passwordHelpInline">
                        <small id="passwordHelpInline" class="text-muted">
                            Must be at least 6 characters long.
                        </small>
                    </div>

                    <div class="form-group">
                        <div class="forget ">
                            <a href="StudentPasswordReset.jsp">Forgot Password?</a>
                        </div>
                    </div>

                    <div class="lastdiv">
                        <p>Not a Student Member yet? <a href="StudentSignup.jsp">Sign up</a></p>
                    </div>
                    <div class="lastdiv2 d-flex align-items-start">
                        <button type="submit" class="btn btn-success btn-md" >Sign In</button>
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
%>