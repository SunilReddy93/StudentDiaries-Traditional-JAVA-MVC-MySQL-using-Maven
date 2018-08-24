package controller;

import dao.AdminDao;
import model.Admin;
import model.ChangePassword;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminChangePasswordSubmitServlet")
public class AdminChangePasswordSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();

        if (session.getAttribute("adminuser") != null && session.getAttribute("password") != null)
        {
            try
            {
                ChangePassword changePassword = new ChangePassword();
                changePassword.setCurrentpassword(request.getParameter("oldpassword"));
                changePassword.setNewpassword(request.getParameter("password"));
                changePassword.setVerifypassword(request.getParameter("verifypassword"));

                Admin admin = new Admin();
                admin.setAdminid(Integer.parseInt(session.getAttribute("sessionadminid").toString()));

                AdminDao adminDao = new AdminDao();
                boolean check = adminDao.changePassword(admin);

                if (check == true)
                {
                    if (admin.getAdminpassword().equals(changePassword.getCurrentpassword()))
                    {
                        if (changePassword.getNewpassword().length() >= 6)
                        {

                            if (changePassword.getNewpassword().equals(changePassword.getVerifypassword()))
                            {

                                if (!changePassword.getNewpassword().equals(changePassword.getCurrentpassword()))
                                {
                                    boolean flag = adminDao.updatePassword(admin, changePassword);

                                    if (flag == true)
                                    {
                                        session.setAttribute("PasswordChangeSuccess", "Password Successfully Changed");
                                        response.sendRedirect("/AdminLogout");
                                    }
                                }
                                else
                                {
                                    session.setAttribute("MatchingOldNewPasswords", "Old and new passwords must be different.");
                                    response.sendRedirect("AdminChangePassword.jsp");

                                }

                            }
                            else
                            {
                                session.setAttribute("NonMatchingNewPasswords", "New Password entries do no match.");
                                response.sendRedirect("AdminChangePassword.jsp");
                            }


                        }
                        else
                        {
                            session.setAttribute("Atleast6Characters", "Password must have at least 6 characters");
                            response.sendRedirect("AdminChangePassword.jsp");
                        }

                    }
                    else
                    {
                        session.setAttribute("IncorrectOldPassword", "Incorrect Current Password");
                        response.sendRedirect("AdminChangePassword.jsp");
                    }
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
