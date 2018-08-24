package controller;

import dao.StudentDao;
import model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StudentProfileUpdationServlet")
public class StudentProfileUpdationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        if (session.getAttribute("studentuser") != null && session.getAttribute("studentpassword") != null)
        {
            try
            {
                Student student = new Student();
                student.setStudentid(Integer.parseInt(session.getAttribute("sessionstudentid").toString()));

                student.setStudentfirstname(request.getParameter("firstname"));
                student.setStudentlastname(request.getParameter("lastname"));
                student.setStudentrollno(Integer.parseInt(request.getParameter("rollno")));
                student.setStudentfathername(request.getParameter("fathername"));
                student.setStudentmothername(request.getParameter("mothername"));
                student.setParentemailid(request.getParameter("parentemail"));
                student.setStudentphoneno(Long.parseLong(request.getParameter("studentphone")));
                student.setParentphoneno(Long.parseLong(request.getParameter("parentphone")));
                student.setStudentyear(Integer.parseInt(request.getParameter("year")));

                StudentDao studentDao = new StudentDao();
                boolean check = studentDao.updateStudent(student);

                if (check == true)
                {
                    session.setAttribute("sessionstudentfirstname", student.getStudentfirstname());
                    session.setAttribute("sessionstudentlastname", student.getStudentlastname());
                    response.sendRedirect("StudentProfile.jsp");
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
