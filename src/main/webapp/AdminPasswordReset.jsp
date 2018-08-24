<%--
  Created by IntelliJ IDEA.
  User: Sunil Reddy
  Date: 15-06-2018
  Time: 03:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

    <title>Password Reset | Admin</title>

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
        if (session.getAttribute("NoEmailFoundForReset")!= null)
        {
    %>
    <p style="color: red; margin-left: 43%;"><%=session.getAttribute("NoEmailFoundForReset").toString() %></p>
    <%
        session.removeAttribute("NoEmailFoundForReset");
    }
    else if (session.getAttribute("ResetEmailSent") != null)
    {
    %>
    <p style="color: green; margin-left: 30%;"><%=session.getAttribute("ResetEmailSent").toString()%></p>

    <%
            session.removeAttribute("ResetEmailSent");
        }
    %>
</div>


<div class="col-md-6 offset-md-3">
    <span class="anchor" id="formResetPassword"></span>


    <!-- form card reset password -->
    <div class="card card-outline-secondary">
        <div class="card-header">
            <h3 class="mb-0">Password Retrieval</h3>
        </div>
        <div class="card-body">
            <form class="form" role="form" autocomplete="off" action="/AdminPasswordResetSubmit" method="post">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Please enter your registered email id" required="">
                    <span id="helpResetPasswordEmail" class="form-text small text-muted">
                                            Your Password will be sent to this email address.
                                        </span>
                </div>
                <div class="form-group">
                    <a class="btn btn-primary btn-lg" href="AdminLogin.jsp">Go back</a>
                    <button type="submit" class="btn btn-success btn-lg float-right">Retrieve</button>
                </div>
            </form>
        </div>
    </div>
    <!-- /form card reset password -->

</div>

<br><br><br><br><br><br><br><br>
<section id="footer" class="bg-light align-items-center">
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

</body>
<!--LOADER-->
<script>
    window.addEventListener("load", function(){
        var load_screen = document.getElementById("load_screen");
        document.body.removeChild(load_screen);
    });
</script>
</html>



