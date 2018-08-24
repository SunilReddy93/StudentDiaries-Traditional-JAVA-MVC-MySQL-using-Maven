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

@WebServlet(name = "AjaxAdminEmailDBVerificationServlet")
public class AjaxAdminEmailDBVerificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {

            try {

                Admin admin = new Admin();
                PrintWriter out = response.getWriter();

                admin.setAdminemailid(request.getParameter("email"));

                AdminDao adminDao = new AdminDao();
                boolean check = adminDao.AdminEmailDbVerification(admin);

                if (check == true) {
                    out.println("Email Already exists");
                } else {
                    out.println("");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


    }


}
