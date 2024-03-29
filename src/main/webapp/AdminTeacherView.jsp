<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="model.Teacher" %>
<%@ page import="dao.TeacherDao" %>
<%@ page import="org.apache.commons.lang.WordUtils" %><%--
  Created by IntelliJ IDEA.
  User: Sunil Reddy
  Date: 12-06-2018
  Time: 02:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%

    if (session.getAttribute("adminuser") != null && session.getAttribute("password") != null)
    {
        try
        {
             int teacherid = Integer.parseInt(request.getParameter("id"));

            Teacher teacher = new Teacher();
            teacher.setTeacherid(teacherid);

            TeacherDao teacherDao = new TeacherDao();
            boolean check = teacherDao.getAllTeacherInfo(teacher);

            if (check == true)
            {

%>


<!-- HEADER 2-->

<header id="header">
    <div class="container container1" id="container-header" >
        <div class="row">
            <div class="col-md-6">
                <div class="fafaicons">
                    <a href="AdminTeacherUsers.jsp"><i class="material-icons md-dark">arrow_back</i></a>
                </div>
            </div>


            <div class="d-flex justify-content-end float-right ml-auto">
                <a  id="profilename" href="#" title="Teacher Name">
                    <h3><%= StringUtils.capitalize(teacher.getTeacherfirstname()) %></h3>
                </a>
            </div>
        </div>

    </div>
</header>


<section>
    <div class="container py-3">
        <div class="card">
            <div class="row ">
                <div class="col-md-4">
                    <img id="teacherimage" src="images/giphy.gif" class="img-fluid w-100 img-responsive d-flex justify-content-center align-items-center">
                </div>
                <div class="col-md-8 px-3">
                    <div class="card-block px-3">
                        <h5 class="card-title"><%= StringUtils.capitalize(teacher.getTeacherfirstname()) %> <%= StringUtils.capitalize(teacher.getTeacherlastname()) %></h5>
                        <p class="card-text">Teacher ID: <%= teacher.getTeacherid() %>,</p>
                        <p class="card-text">Reference Admin ID: <%= teacher.getTeacheradminid() %>,</p>
                        <p class="card-text"><%= WordUtils.capitalizeFully(teacher.getTeachercoursename()) %>,  <%=   WordUtils.capitalizeFully(teacher.getTeacherschool()) %></p>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><p><span><i class="fa fa-envelope"></i></span>  <%= teacher.getTeacheremailid() %></p></li>
                            <li class="list-group-item"><p><span><i class="fa fa-phone"></i></span>  <%= teacher.getTeacherphoneno() %></p> </li>

                        </ul>
                        <div class="card-body">
                            <a href="#" class="card-link"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                            <a href="#" class="card-link"><i class="fa fa-twitter"></i></a>
                            <a href="#" class="card-link"><i class="fa fa-linkedin"></i></a>
                            <a href="#" class="card-link"><i class="fa fa-instagram"></i></a>
                            <a href="#" class="card-link"><i class="fa fa-youtube"></i></a>
                        </div>
                    </div>

                </div>



            </div>

        </div>
    </div>
    </div>

</section>

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
        response.sendRedirect("TeacherLogin.jsp");
    }

%>