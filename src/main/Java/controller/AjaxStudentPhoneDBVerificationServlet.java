package controller;

import dao.StudentDao;
import model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "AjaxStudentPhoneDBVerificationServlet")
public class AjaxStudentPhoneDBVerificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {

        try
        {
            Student student = new Student();
            PrintWriter out = response.getWriter();

            student.setStudentphoneno(Long.parseLong(request.getParameter("phone")));

            StudentDao studentDao = new StudentDao();
            boolean check = studentDao.AjaxStudentPhoneDBVerification(student);

            if (check == true)
            {
                out.println("Phone Already exists");
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
