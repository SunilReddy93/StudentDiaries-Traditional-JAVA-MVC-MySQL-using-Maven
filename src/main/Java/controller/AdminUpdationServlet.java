package controller;

import dao.AdminDao;
import model.Admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminUpdationServlet")
public class AdminUpdationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("adminuser") != null && session.getAttribute("password") != null)
        {
            try
            {
                Admin admin = new Admin();
                admin.setAdminid(Integer.parseInt(request.getParameter("id")));
                admin.setAdminfirstname(request.getParameter("firstname"));
                admin.setAdminlastname(request.getParameter("lastname"));

                admin.setAdminphoneno(Long.parseLong(request.getParameter("phone")));
                admin.setAdminschoolname(request.getParameter("school"));
                admin.setAdmincity(request.getParameter("city"));

                AdminDao adminDao = new AdminDao();
                boolean check = adminDao.updateAdminAccount(admin);

                if (check == true)
                {
                    session.setAttribute("sessionadminfirstname", admin.getAdminfirstname());
                    session.setAttribute("sessionadminlastname", admin.getAdminlastname());
                    response.sendRedirect("AdminProfileSettings.jsp");
                }
                else
                {
                    response.sendRedirect("AdminUnknownError.jsp");
                }


            }catch (Exception e)
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
