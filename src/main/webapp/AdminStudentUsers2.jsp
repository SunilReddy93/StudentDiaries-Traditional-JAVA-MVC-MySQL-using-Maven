<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="model.Teacher" %>
<%@ page import="dao.StudentDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Student" %>
<%@ page import="org.apache.commons.lang.WordUtils" %><%--
  Created by IntelliJ IDEA.
  User: Sunil Reddy
  Date: 11-06-2018
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%

    if (session.getAttribute("adminuser") != null && session.getAttribute("password") != null)
    {
        try
        {

%>


<html>
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat|Muli" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


    <!-- CHECKBOX CDN-->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/pretty-checkbox@3.0/dist/pretty-checkbox.min.css" />

    <link rel="stylesheet" href="//cdn.materialdesignicons.com/2.3.54/css/materialdesignicons.min.css">

    <!-- BOOTSTRAP CSS -->
    <link rel="stylesheet" type="text/css" href="mycss/users/teacherusers.css"/>

    <link rel="stylesheet" type="text/css" href="mycss/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="images/panda.ico" />

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>Students | StudentDiaries</title>
</head>
<body id="out" oncontextmenu="return false;">
<div id="load_screen"><div id="loading"></div></div>


<!-- MAIN- NAVBAR -->
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
<br>
<section id="replace">

    <!-- HEADER 2-->

    <header id="header">
        <div class="container container1" id="container-header" >
            <div class="row">
                <div class="col-md-6">
                    <div >
                        <a href="AdminTeacherStudentSelection.jsp">
                            <i class="material-icons md-dark">arrow_back</i>
                        </a>
                    </div>
                </div>


                <div class="col-md-6">
                    <div class="input-group-sm col-md-4 d-flex justify-content-end float-right">
                        <input class="form-control py-2 border-right-0 border" type="search" placeholder="search" id="search-input">
                        <span class="input-group-append">
                    <button class="btn btn-outline-secondary border-left-0 border" type="button">
                    <i class="fa fa-search"></i>
                    </button>
                    </span>
                    </div>




                    <div class=" d-flex justify-content-end float-right">
                        <a id="trash" href="" title="Delete"><span><i class="material-icons md-dark">
delete</i></span></a>
                    </div>




                    <div class=" d-flex justify-content-end float-right">
                        <a id="view" href="" title="View Profile"><span><i class="material-icons md-dark ">account_circle</i></span></a>
                    </div>


                    <div class=" d-flex justify-content-end float-right">
                        <a href="AdminStudentSignup.jsp" title="Add Student"><span><i class="material-icons md-dark">person_add</i></span></a>
                    </div>


                </div>

            </div>
        </div>

    </header>
    <br><br>

    <section id="main-content">
        <div class="container pre-scrollable container2" id="table-container">
            <div class="row">
                <div class="col">
                    <table class="table table-hover" id="table1">
                        <thead>
                        <tr>
                            <th scope="col">Action</th>
                            <th scope="col">Roll No</th>
                            <th scope="col">ID</th>
                            <th scope="col">First Name</th>
                            <th scope="col">Last Name</th>
                            <th scope="col">Course</th>
                        </tr>
                        </thead>
                        <tbody>

                        <%
                            Teacher teacher = new Teacher();
                            if (request.getParameter("id") != null)
                            {
                                /*create temp_teacher_id session for delete operation and other operations on student related to that teacher
                                 * on the AdminDeletion.jsp and maybe elsewhere to access the teacher id over there
                                 * Remember this is a temporary session just to access teacher id on another page.
                                 * */

                                    session.setAttribute("temp_teacher_id", Integer.parseInt(request.getParameter("id")));

                            }


                            teacher.setTeacherid(Integer.parseInt(session.getAttribute("temp_teacher_id").toString()));

                            StudentDao studentDao = new StudentDao();
                            ArrayList<Student> students;
                            int num = 0;

                            students = studentDao.getStudentInfo(teacher);

                            for (int i = 0; i < students.size(); i++)
                            {

                        %>
                        <tr>

                            <th>
                                <div class="pretty p-icon p-curve p-has-indeterminate">
                                    <input type="checkbox" class="checkitem" value="<%= students.get(i).getStudentid() %>" name="check"/>
                                    <div class="state">
                                        <i class="icon mdi mdi-check"></i>
                                        <label>Check</label>
                                    </div>
                                    <div class="state p-is-indeterminate">
                                        <i class="icon mdi mdi-minus"></i>
                                        <label>Indeterminate</label>
                                    </div>
                                </div>
                            </th>
                            <th scope="row"><%= students.get(i).getStudentrollno() %></th>
                            <th><%= students.get(i).getStudentid() %></th>
                            <td><%= StringUtils.capitalize(students.get(i).getStudentfirstname()) %></td>
                            <td><%= StringUtils.capitalize(students.get(i).getStudentlastname()) %></td>
                            <td><%= WordUtils.capitalizeFully(students.get(i).getStudentcoursename()) %></td>

                        </tr>

                        <%
                                num++;

                            }

                        %>

                        </tbody>
                    </table>

                    <%

                        if(num == 0)
                        {
                    %>
                    <p class="text-center" style="color: red;">Oops! No Student records found.</p>
                    <%

                        }

                    %>


                </div>
            </div>
        </div>
    </section>

</section><!--For section id replace-->
<br><br>

<!-- FOOTER-->
<section id="footer">
    <div class="container">
        <footer class="container">
            <p class="float-right"><a href="#main-header"> Back to top</a></p>
            <p class="float-right"><a href="AboutUs.jsp#contact">Contact Us</a></p>
            <p>&copy; StudentDiaries, Inc. 2017-2018 &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
        </footer>
    </div>
</section>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<!-- JQUERY 3.2.1 FOR DATEPICKER-->

<script
        src="https://code.jquery.com/jquery-3.2.1.js"
        integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous"></script>

<!--DATEPICKER-->
<!--
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
-->

<script src="javascript/AJAX/AdminStudentDelete.js"></script>
<script src="javascript/AJAX/AdminStudentView.js"></script>



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