package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "TeacherLogoutServlet")
public class TeacherLogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            HttpSession session = request.getSession();

            if (session.getAttribute("teacheruser") != null && session.getAttribute("teacherpassword") != null)
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

                response.sendRedirect("TeacherLogin.jsp");

            }
            else
            {
                response.sendRedirect("TeacherLogin.jsp");
            }



        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
