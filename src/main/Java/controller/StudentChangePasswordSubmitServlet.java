package controller;

import dao.StudentDao;
import model.ChangePassword;
import model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StudentChangePasswordSubmitServlet")
public class StudentChangePasswordSubmitServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("studentuser") != null && session.getAttribute("studentpassword") != null)
        {
            try
            {

                ChangePassword changePassword = new ChangePassword();
                changePassword.setCurrentpassword(request.getParameter("oldpassword"));
                changePassword.setNewpassword(request.getParameter("password"));
                changePassword.setVerifypassword(request.getParameter("verifypassword"));

                Student student = new Student();
                student.setStudentid(Integer.parseInt(session.getAttribute("sessionstudentid").toString()));

                StudentDao studentDao = new StudentDao();
                boolean check = studentDao.changeStudentPasswordRequest(student);

                if (check == true)
                {
                    if (student.getStudentpassword().equals(changePassword.getCurrentpassword()))
                    {
                        if (changePassword.getNewpassword().length() >= 6)
                        {

                            if (changePassword.getNewpassword().equals(changePassword.getVerifypassword()))
                            {

                                if (!changePassword.getNewpassword().equals(changePassword.getCurrentpassword()))
                                {
                                    boolean flag = studentDao.updateStudentPassword(student, changePassword);

                                    if (flag == true)
                                    {
                                        session.setAttribute("PasswordChangeSuccess", "Password Successfully Changed");
                                        response.sendRedirect("/StudentLogout");
                                    }
                                }
                                else
                                {
                                    session.setAttribute("MatchingOldNewPasswords", "Old and new passwords must be different.");
                                    response.sendRedirect("StudentChangePassword.jsp");

                                }

                            }
                            else
                            {
                                session.setAttribute("NonMatchingNewPasswords", "New Password entries do no match.");
                                response.sendRedirect("StudentChangePassword.jsp");
                            }


                        }
                        else
                        {
                            session.setAttribute("Atleast6Characters", "Password must have at least 6 characters");
                            response.sendRedirect("StudentChangePassword.jsp");
                        }

                    }
                    else
                    {
                        session.setAttribute("IncorrectOldPassword", "Incorrect Current Password");
                        response.sendRedirect("StudentChangePassword.jsp");
                    }
                }
                else
                {
                    response.sendRedirect("StudentUnknownError.jsp");
                }




            }
            catch (Exception e)
            {
                e.printStackTrace();
                response.sendRedirect("StudentUnknownError.jsp");
            }


        }
        else
        {
            response.sendRedirect("StudentLogin.jsp");
        }
    }


}
