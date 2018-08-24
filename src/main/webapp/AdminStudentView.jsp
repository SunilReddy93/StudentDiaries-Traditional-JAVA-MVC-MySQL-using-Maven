<%@ page import="model.Student" %>
<%@ page import="dao.StudentDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="org.apache.commons.lang.WordUtils" %><%--
  Created by IntelliJ IDEA.
  User: Sunil Reddy
  Date: 12-06-2018
  Time: 00:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%

    if (session.getAttribute("adminuser") != null && session.getAttribute("password") != null)
    {
        try
        {

%>


<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<body oncontextmenu="return false;">
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
                    <a class="nav-link dropdown-toggle" href="" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span id="fullname"><%= StringUtils.capitalize(session.getAttribute("sessionadminfirstname").toString()) %> <%= StringUtils.capitalize(session.getAttribute("sessionadminlastname").toString())%></span></a>
                    <div class="dropdown-menu" aria-labelledby="dropdown01">
                        <a class="dropdown-item" href="/AdminLogout">Logout</a>

                    </div>
                </li>



            </ul>

        </div>
    </nav>

</header>
<br>

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

<section class="checkfeedback2">
    <div class="container">
        <div class="form-inline">

            <div class="col-md-3">
                <h3><span><%= StringUtils.capitalize(students.get(i).getStudentfirstname()) %>'s </span>Profile</h3>
            </div>

            <div class="d-flex justify-content-end float-right ml-auto">
                <form class="form-inline" action="AdminCheckFeedback.jsp" method="post" >
                    <input type="hidden" name="studentid" value="<%= students.get(i).getStudentid() %>">                       <div class="mr-2">
                    <small class="align-items-end">Select Date to check feedback:</small>
                </div>
                    <div class="input-group mr-2 datediv">
                        <main>
                            <input type="text" id="datepicker" name="datepicker" class="form-control datepicker" placeholder="DD/MM/YYYY" autocomplete="off" required aria-label="DD/MM/YYYY" aria-describedby="basic-addon2" />
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
                        <input type="email" class="form-control" id="email" name="studentemail" placeholder="Student Email Address" value="<%= students.get(i).getStudentemailid() %>" readonly>

                    </div>
                    <div class=" form-group col-md-6">
                        <label>Parent Email ID</label>
                        <input type="email" class="form-control" id="parentemail" name="parentemail" placeholder="Parent Email Address" value="<%= students.get(i).getParentemailid() %>" readonly>

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
                    <br>

                    <div class="col-md-12" style="margin-left: 45%; margin-top: 20px;">
                        <a href="AdminTeacherStudentSelection.jsp" id="goback"><button class="btn btn-primary">Go Back</button></a>
                    </div>

                </div>

            </div>
        </div>
    </div>
</section>

<%
    } //for loop ends

%>


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

</body>

<script
        src="https://code.jquery.com/jquery-3.2.1.js"
        integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous">

</script>



<script>
    $(document).ready(function () {

        $( function() {
            $( ".datepicker" ).datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'dd/mm/yy',
                showAnim: 'clip'
            });


        } );

    });

</script>

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