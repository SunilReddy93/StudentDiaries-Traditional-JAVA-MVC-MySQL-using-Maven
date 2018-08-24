package controller;

import dao.TeacherDao;
import model.ChangePassword;
import model.Teacher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "TeacherChangePasswordSubmitServlet")
public class TeacherChangePasswordSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        if (session.getAttribute("teacheruser") != null && session.getAttribute("teacherpassword") != null)
        {
            try
            {

                ChangePassword changePassword = new ChangePassword();
                changePassword.setCurrentpassword(request.getParameter("oldpassword"));
                changePassword.setNewpassword(request.getParameter("password"));
                changePassword.setVerifypassword(request.getParameter("verifypassword"));

                Teacher teacher = new Teacher();
                teacher.setTeacherid(Integer.parseInt(session.getAttribute("sessionteacherid").toString()));

                TeacherDao teacherDao = new TeacherDao();
                boolean check = teacherDao.changeTeacherPassword(teacher);

                if (check == true)
                {
                    if (teacher.getTeacherpassword().equals(changePassword.getCurrentpassword()))
                    {
                        if (changePassword.getNewpassword().length() >= 6)
                        {

                            if (changePassword.getNewpassword().equals(changePassword.getVerifypassword()))
                            {

                                if (!changePassword.getNewpassword().equals(changePassword.getCurrentpassword()))
                                {
                                    boolean flag = teacherDao.updateTeacherPassword(teacher, changePassword);

                                    if (flag == true)
                                    {
                                        session.setAttribute("PasswordChangeSuccess", "Password Successfully Changed");
                                        response.sendRedirect("/TeacherLogout");
                                    }
                                }
                                else
                                {
                                    session.setAttribute("MatchingOldNewPasswords", "Old and new passwords must be different.");
                                    response.sendRedirect("TeacherChangePassword.jsp");

                                }

                            }
                            else
                            {
                                session.setAttribute("NonMatchingNewPasswords", "New Password entries do no match.");
                                response.sendRedirect("TeacherChangePassword.jsp");
                            }


                        }
                        else
                        {
                            session.setAttribute("Atleast6Characters", "Password must have at least 6 characters");
                            response.sendRedirect("TeacherChangePassword.jsp");
                        }

                    }
                    else
                    {
                        session.setAttribute("IncorrectOldPassword", "Incorrect Current Password");
                        response.sendRedirect("TeacherChangePassword.jsp");
                    }
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
