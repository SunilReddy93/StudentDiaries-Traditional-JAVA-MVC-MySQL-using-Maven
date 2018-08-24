package controller;

import dao.TeacherDao;
import model.Teacher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "AjaxTeacherPhoneDBVerificationServlet")
public class AjaxTeacherPhoneDBVerificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            Teacher teacher = new Teacher();
            PrintWriter out = response.getWriter();

            teacher.setTeacherphoneno(Long.parseLong(request.getParameter("phone")));

            TeacherDao teacherDao = new TeacherDao();
            boolean check = teacherDao.TeacherPhoneDbVerification(teacher);

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
