package controller;

import dao.TeacherDao;
import model.ResetPasswordEmail;
import model.Teacher;
import org.apache.commons.lang.WordUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "TeacherPasswordResetSubmitServlet")
public class TeacherPasswordResetSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        try
        {
            HttpSession session = request.getSession();
            Teacher teacher = new Teacher();
            teacher.setTeacheremailid(request.getParameter("email"));

            TeacherDao teacherDao = new TeacherDao();
            boolean check = teacherDao.teacherPasswordReset(teacher);

            if (check == true) {
                String to = teacher.getTeacheremailid();
                String subject = "Teacher Password Retrieval - StudentDiaries";
                String message = "Hello " + WordUtils.capitalizeFully(teacher.getTeacherfirstname()) + ", \n\nYour Password: " + teacher.getTeacherpassword() + "\n\nRegards, \n\nStudentDiaries 2018";


                String user = "studentdiaries2018@gmail.com";
                String password2 = "imsd@2018";
                ResetPasswordEmail.send(to, subject, message, user, password2);
                // System.out.println("Mail SuccessFully Sent");
                session.setAttribute("ResetEmailSent", "Success. An email has been sent to you with password.");

                response.sendRedirect("TeacherPasswordReset.jsp");

            } else {

                session.setAttribute("NoEmailFoundForReset", "Email not found");
                response.sendRedirect("TeacherPasswordReset.jsp");
            }


        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }

}
