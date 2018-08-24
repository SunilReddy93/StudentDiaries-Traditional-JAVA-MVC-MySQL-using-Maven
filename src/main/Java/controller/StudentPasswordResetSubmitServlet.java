package controller;

import dao.StudentDao;
import model.ResetPasswordEmail;
import model.Student;
import org.apache.commons.lang.WordUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StudentPasswordResetSubmitServlet")
public class StudentPasswordResetSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        try
        {
            HttpSession session = request.getSession();
            Student student = new Student();
            student.setStudentemailid(request.getParameter("email"));

            StudentDao studentDao = new StudentDao();
            boolean check = studentDao.studentPasswordReset(student);

            if (check == true)
            {
                String to = student.getStudentemailid();
                String subject = "Student Password Retrieval - StudentDiaries";
                String message = "Hello "+WordUtils.capitalizeFully(student.getStudentfirstname())+", \n\nYour Password: "+student.getStudentpassword()+"\n\nRegards, \n\nStudentDiaries 2018";


                String user = "studentdiaries2018@gmail.com";
                String password2 = "imsd@2018";
                ResetPasswordEmail.send(to, subject, message, user, password2);
                // System.out.println("Mail SuccessFully Sent");
                session.setAttribute("ResetEmailSent", "Success. An email has been sent to you with password.");

                response.sendRedirect("StudentPasswordReset.jsp");

            }
            else
            {

                session.setAttribute("NoEmailFoundForReset", "Email not found");
                response.sendRedirect("StudentPasswordReset.jsp");
            }




        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
        }

    }


}
