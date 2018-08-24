package controller;

import dao.StudentDao;
import model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StudentAccountDeletionServlet")
public class StudentAccountDeletionServlet extends HttpServlet
{


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("studentuser") != null && session.getAttribute("studentpassword") != null) {
            try {

                Student student = new Student();
                student.setStudentid(Integer.parseInt(session.getAttribute("sessionstudentid").toString()));
                StudentDao studentDao = new StudentDao();
                boolean check = studentDao.deleteParticularStudent(student);

                if (check == true) {
                    session.removeAttribute("sessionstudentfirstname");
                    session.removeAttribute("sessionstudentlastname");
                    session.removeAttribute("sessionstudentid");


                    if (session.getAttribute("StudentLoginIncorrectCredentials") != null) {
                        session.removeAttribute("StudentLoginIncorrectCredentials");
                    }
                    session.removeAttribute("studentuser");
                    session.removeAttribute("studentpassword");

                    response.sendRedirect("StudentDeleteSuccess.jsp");
                } else {
                    response.sendRedirect("StudentLogin.jsp");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("StudentUnknownError.jsp");
            }
        } else {
            response.sendRedirect("StudentLogin.jsp");
        }
    }
}
