package controller;

import dao.AdminDao;
import dao.StudentDao;
import dao.TeacherDao;
import model.Admin;
import model.Student;
import model.Teacher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginVerificationServlet")
public class LoginVerificationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        String fno = request.getParameter("fno");

        if (fno.equals("1"))
        {

            try
            {
                Admin admin =  new Admin();

                admin.setAdminemailid(request.getParameter("email").toLowerCase());
                admin.setAdminpassword(request.getParameter("password"));

                AdminDao adminDao = new AdminDao();
                boolean check = adminDao.adminLoginVerification(admin);
                HttpSession session = request.getSession();

                if (check == true)
                {

                    session.setAttribute("adminuser", admin.getAdminemailid());
                    session.setAttribute("password", admin.getAdminpassword());
                    session.setAttribute("sessionadminfirstname", admin.getAdminfirstname());
                    session.setAttribute("sessionadminlastname", admin.getAdminlastname());
                    session.setAttribute("sessionadminid", admin.getAdminid());

                    response.sendRedirect("AdminDashboard.jsp");
                }
                else
                {
                    session.setAttribute("LoginIncorrectCredentials", "Incorrect Credentials");
                    response.sendRedirect("AdminLogin.jsp");
                }


            }
            catch (Exception e)
            {
                response.sendRedirect("AdminLoginError.jsp");
            }

        }


        if (fno.equals("2"))
        {
            try
            {
                Teacher teacher = new Teacher();

                teacher.setTeacheremailid(request.getParameter("email").toLowerCase());
                teacher.setTeacherpassword(request.getParameter("password"));

                TeacherDao teacherDao = new TeacherDao();

                boolean check = teacherDao.teacherLoginVerification(teacher);
                HttpSession session = request.getSession();

                if (check == true)
                {
                    session.setAttribute("teacheruser", teacher.getTeacheremailid());
                    session.setAttribute("teacherpassword", teacher.getTeacherpassword());
                    session.setAttribute("sessionteacherfirstname", teacher.getTeacherfirstname());
                    session.setAttribute("sessionteacherlastname", teacher.getTeacherlastname());
                    session.setAttribute("sessionteacherid", teacher.getTeacherid());

                    response.sendRedirect("TeacherDashboard.jsp");
                }
                else
                {
                    session.setAttribute("TeacherLoginIncorrectCredentials", "Incorrect Credentials");
                    response.sendRedirect("TeacherLogin.jsp");
                }


            }
            catch (Exception e)
            {
                response.sendRedirect("TeacherLoginError.jsp");
            }
        }

        if (fno.equals("3"))
        {
            try
            {

                Student student = new Student();
                student.setStudentemailid(request.getParameter("email").toLowerCase());
                student.setStudentpassword(request.getParameter("password"));

                StudentDao studentDao = new StudentDao();
                boolean check = studentDao.studentLoginVerification(student);

                HttpSession session = request.getSession();
                if (check == true)
                {
                    session.setAttribute("studentuser", student.getStudentemailid());
                    session.setAttribute("studentpassword", student.getStudentpassword());
                    session.setAttribute("sessionstudentfirstname", student.getStudentfirstname());
                    session.setAttribute("sessionstudentlastname", student.getStudentlastname());
                    session.setAttribute("sessionstudentid", student.getStudentid());

                    response.sendRedirect("StudentDashboard.jsp");

                }
                else
                {
                    session.setAttribute("StudentLoginIncorrectCredentials", "Incorrect Credentials");
                    response.sendRedirect("StudentLogin.jsp");
                }

            }
            catch (Exception e)
            {
                response.sendRedirect("StudentLoginError.jsp");
            }
        }

    }


}
