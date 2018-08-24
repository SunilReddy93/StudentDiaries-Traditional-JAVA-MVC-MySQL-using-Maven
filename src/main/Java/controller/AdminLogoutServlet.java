package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminLogoutServlet")
public class AdminLogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            HttpSession session = request.getSession();

            if (session.getAttribute("adminuser") != null && session.getAttribute("password") != null) {


                session.removeAttribute("sessionadminfirstname");
                session.removeAttribute("sessionadminlastname");
                session.removeAttribute("sessionadminid");
                if (session.getAttribute("temp_teacher_id") != null) {
                    session.removeAttribute("temp_teacher_id");
                }

                if (session.getAttribute("LoginIncorrectCredentials") != null) {
                    session.removeAttribute("LoginIncorrectCredentials");
                }
                session.removeAttribute("adminuser");
                session.removeAttribute("password");
                response.sendRedirect("AdminLogin.jsp");
            }
            else
            {
                response.sendRedirect("AdminLogin.jsp");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
