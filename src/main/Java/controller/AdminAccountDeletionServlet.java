package controller;

import dao.AdminDao;
import model.Admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminAccountDeletionServlet")
public class AdminAccountDeletionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        HttpSession session = request.getSession();
        if (session.getAttribute("adminuser") != null && session.getAttribute("password") != null)
        {
            try
            {
                Admin admin = new Admin();
                admin.setAdminid(Integer.parseInt(session.getAttribute("sessionadminid").toString()));

                AdminDao adminDao = new AdminDao();
                boolean check = adminDao.deleteAdminAccount(admin);

                if (check == true)
                {
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

                    response.sendRedirect("AdminDeleteSuccess.jsp");

                }
                else
                {
                    response.sendRedirect("AdminUnknownError.jsp");
                }


            }
            catch (Exception e)
            {
                e.printStackTrace();
                response.sendRedirect("AdminUnknownError.jsp");
            }
        }
        else
        {
            response.sendRedirect("AdminLogin.jsp");
        }
    }

}
