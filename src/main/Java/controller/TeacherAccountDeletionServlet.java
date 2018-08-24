package controller;

import dao.TeacherDao;
import model.Teacher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "TeacherAccountDeletionServlet")
public class TeacherAccountDeletionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        if (session.getAttribute("teacheruser") != null && session.getAttribute("teacherpassword") != null)
        {
            try
            {
                Teacher teacher = new Teacher();
                teacher.setTeacherid(Integer.parseInt(session.getAttribute("sessionteacherid").toString()));

                TeacherDao teacherDao = new TeacherDao();
                boolean check = teacherDao.deleteTeacherAccount(teacher);

                if (check == true)
                {
                    session.removeAttribute("sessionteacherfirstname");
                    session.removeAttribute("sessionteacherlastname");

                    if (session.getAttribute("TeacherLoginIncorrectCredentials") != null)
                    {
                        session.removeAttribute("TeacherLoginIncorrectCredentials");
                    }

                    if (session.getAttribute("sessionteacherid") != null)
                    {
                        session.removeAttribute("sessionteacherid");
                    }

                    session.removeAttribute("teacheruser");
                    session.removeAttribute("teacherpassword");

                    response.sendRedirect("TeacherDeleteSuccess.jsp");
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
