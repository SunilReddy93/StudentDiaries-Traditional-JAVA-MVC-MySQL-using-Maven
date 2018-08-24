package controller;

import dao.StudentDao;
import model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@WebServlet(name = "AjaxParentPhoneDBVerificationServlet")
public class AjaxParentPhoneDBVerificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {

        try
        {

            Student student = new Student();
            PrintWriter out = response.getWriter();

            student.setParentphoneno(Long.parseLong(request.getParameter("phone")));

            StudentDao studentDao = new StudentDao();
            boolean check = studentDao.AjaxParentPhoneDBVerification(student);

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
