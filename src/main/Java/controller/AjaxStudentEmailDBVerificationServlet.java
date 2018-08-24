package controller;

import dao.StudentDao;
import model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "AjaxStudentEmailDBVerificationServlet")
public class AjaxStudentEmailDBVerificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {

        try
        {


            Student student = new Student();
            PrintWriter out = response.getWriter();

            student.setStudentemailid(request.getParameter("email"));

            StudentDao studentDao = new StudentDao();
            boolean check = studentDao.AjaxStudentEmailDBVerification(student);

            if (check == true)
            {
                out.println("Email Already exists");
            }
            else
            {
                out.println("");
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
