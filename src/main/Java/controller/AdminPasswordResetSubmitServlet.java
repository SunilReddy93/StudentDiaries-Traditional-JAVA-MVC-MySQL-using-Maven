package controller;

import dao.AdminDao;
import model.Admin;
import model.ResetPasswordEmail;
import org.apache.commons.lang.WordUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminPasswordResetSubmitServlet")
public class AdminPasswordResetSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        try
        {
            HttpSession session = request.getSession();
            Admin admin = new Admin();
            admin.setAdminemailid(request.getParameter("email"));

            AdminDao adminDao = new AdminDao();
            boolean check = adminDao.adminPasswordReset(admin);

            if (check == true)
            {
                String to = admin.getAdminemailid();
                String subject = "Admin Password Retrieval - StudentDiaries";
                String message = "Hello "+WordUtils.capitalizeFully(admin.getAdminfirstname())+", \n\nYour Password: "+admin.getAdminpassword()+"\n\nRegards, \n\nStudentDiaries 2018";


                String user = "studentdiaries2018@gmail.com";
                String password2 = "imsd@2018";
                ResetPasswordEmail.send(to, subject, message, user, password2);
                // System.out.println("Mail SuccessFully Sent");
                session.setAttribute("ResetEmailSent", "Success. An email has been sent to you with password.");

                response.sendRedirect("AdminPasswordReset.jsp");

            }
            else
            {

                session.setAttribute("NoEmailFoundForReset", "Email not found");
                response.sendRedirect("AdminPasswordReset.jsp");
            }




        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }


}
