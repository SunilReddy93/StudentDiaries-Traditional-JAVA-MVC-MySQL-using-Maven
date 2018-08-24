package controller;

import dao.AdminDao;
import model.Admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AjaxAdminPhoneDBVerificationServlet")
public class AjaxAdminPhoneDBVerificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {

            try
            {
                Admin admin = new Admin();
                PrintWriter out = response.getWriter();

                admin.setAdminphoneno(Long.parseLong(request.getParameter("phone")));

                AdminDao adminDao = new AdminDao();
                boolean check = adminDao.AdminPhoneDbVerification(admin);

                if (check == true) {
                    out.println("Phone Already exists");
                } else {
                    out.println("");
                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }


    }


}
