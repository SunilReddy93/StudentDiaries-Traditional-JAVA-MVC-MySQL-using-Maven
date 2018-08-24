<%@ page import="model.Student" %>
<%@ page import="dao.StudentDao" %>
<%@ page import="org.apache.commons.lang.StringUtils" %><%--
  Created by IntelliJ IDEA.
  User: Sunil Reddy
  Date: 13-06-2018
  Time: 00:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    if (session.getAttribute("teacheruser") != null && session.getAttribute("teacherpassword") != null)
    {
        try
        {
            int id = Integer.parseInt(request.getParameter("id"));
            Student student = new Student();
            student.setStudentid(id);

            StudentDao studentDao = new StudentDao();
            boolean check = studentDao.getSomeStudentInfo(student);

            if (check == true)
            {


%>

<head>

    <!-- Bootstrap CSS -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- CHECKBOX CDN-->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/pretty-checkbox@3.0/dist/pretty-checkbox.min.css" />

    <link rel="stylesheet" href="//cdn.materialdesignicons.com/2.3.54/css/materialdesignicons.min.css">

    <!-- DATEPICKER CSS & JS-->

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.css"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>Student Feedback | StudentDiaries</title>
</head>
<body oncontextmenu="return false;">

<h4 id="heading" class="text-center"><%= StringUtils.capitalize(student.getStudentfirstname()) %> <%= StringUtils.capitalize(student.getStudentlastname()) %>'s Feedback Form</h4>
<br>

<div class="bootstrap-iso">
    <div class="container list-group list-group-flush feedback-form">
        <form action="/TeacherStudentFeedbackSubmit" method="post">
            <div class="row">

                <div class="col-md-12 feeditem list-group-item">
                    <label class="col-md-4">Student ID:</label>
                    <div class="col-lg-8 float-right">
                        <label><%= student.getStudentid() %></label>
                        <input type="hidden" id="sid" name="sid" value="<%= student.getStudentid() %>">
                    </div>
                </div>

                <div class="col-md-12 feeditem list-group-item">
                    <label class="col-md-4">Date:</label>
                    <div class="col-md-8 float-right">
                        <main>
                            <div class="input-group mb-3">
                                <input type="text" id="date" name="date" class="form-control" placeholder="DD/MM/YYYY" autocomplete="off" required aria-label="DD/MM/YYYY" aria-describedby="basic-addon2"/>
                            </div>
                        </main>
                    </div>
                </div>

                <!--ATTENDANCE PRESENT-->
                <div class="col-md-12 feeditem list-group-item">
                    <label class="col-md-4">Attendance:  </label>
                    <div class="col-md-8 float-right">
                        <div class="pretty p-icon p-round">
                            <input type="radio" value="present" name="attend" required/>
                            <div class="state p-success">
                                <i class="icon mdi mdi-check"></i>
                                <label>Present</label>
                            </div>
                        </div>

                        <div class="pretty p-icon p-round">
                            <input type="radio" value="absent" name="attend" />
                            <div class="state p-danger">
                                <i class="icon mdi mdi-check"></i>
                                <label>Absent</label>
                            </div>
                        </div>

                    </div>
                </div>


                <div class="col-md-12 feeditem list-group-item">
                    <label class="col-md-4">Performance:  </label>
                    <div name="performance" id="performance" class="col-md-8 float-right"></div>
                </div>


                <div class="col-md-12 feeditem list-group-item">
                    <label class="col-md-4">Behaviour:  </label>
                    <div name="behaviour" id="behaviour" class="col-md-8 float-right" aria-required="true"></div>
                </div>


                <div class="col-md-12 feeditem list-group-item">
                    <label class="col-md-4">Improvement:  </label>
                    <div name="improvement" id="improvement" class="col-md-8 float-right" aria-required="true"></div>
                </div>

                <div class="col-md-12 feeditem list-group-item">
                    <label class="col-md-4">Remark:  </label>
                    <textarea maxlength="255" rows="5" name="remark" id="remark" placeholder="Enter your remark here (255 Characters only)" class="col-md-8 float-right" required></textarea>
                </div>

                <div class="col-md-12 feeditem list-group-item text-center">
                    <a href="TeacherStudentUsers.jsp" id="goback" class="btn btn-primary">Go Back</a>

                    <button type="submit" id="feedbacksubmit" name="submit" class="btn btn-success">Submit</button>
                </div>



            </div>

        </form>

    </div>
</div>

<br><br>


<!-- DATEPICKER CSS & JS-->
<script
        src="https://code.jquery.com/jquery-3.2.1.js"
        integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous"></script>


<script src="javascript/AJAX/TeacherStudentRating.js"></script>
<script src="javascript/AJAX/datetimepicker.js"></script>
<script src="javascript/Rating/jquery.sodah-rating.min.js"></script>
<!--DATEPICKER-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>

<script>


    $('#performance').sodahrating({
        count: "10",
        iconcolor: "#2ecc71",
        inputname: "performance",
        type: "score",
        maxsize: "20",
        firstvalue: "1"
    })

    $('#behaviour').sodahrating({
        count: "10",
        iconcolor: "#3498db",
        inputname: "behaviour",
        type: "score",
        maxsize: "20",
        firstvalue: "1"
    })

    $('#improvement').sodahrating({
        count: "10",
        iconcolor: "#f1c40f",
        inputname: "improvement",
        type: "score",
        maxsize: "20",
        firstvalue: "1"
    })



</script>
<body>


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

