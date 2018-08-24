package controller;

import dao.TeacherDao;
import model.Teacher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "TeacherProfileUpdationServlet")
public class TeacherProfileUpdationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        if (session.getAttribute("teacheruser") != null && session.getAttribute("teacherpassword") != null)
        {
            try
            {

                Teacher teacher = new Teacher();

                teacher.setTeacherid(Integer.parseInt(request.getParameter("teacherid")));
                teacher.setTeacherfirstname(request.getParameter("firstname"));
                teacher.setTeacherlastname(request.getParameter("lastname"));
                teacher.setTeacherphoneno(Long.parseLong(request.getParameter("teacherphone")));
                teacher.setTeachercoursename(request.getParameter("course"));

                TeacherDao teacherDao = new TeacherDao();
                boolean check = teacherDao.updateTeacherAccount(teacher);

                if (check == true)
                {
                    session.setAttribute("sessionteacherfirstname", teacher.getTeacherfirstname());
                    session.setAttribute("sessionteacherlastname", teacher.getTeacherlastname());
                    response.sendRedirect("TeacherProfileSettings.jsp");
                }
                else
                {
                    response.sendRedirect("TeacherUnknownError.jsp");
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
                response.sendRedirect("TeacherUnknownError.jsp");
            }

        }
        else
        {
            response.sendRedirect("TeacherLogin.jsp");
        }
    }


}
