package controller;

import dao.TeacherDao;
import model.Teacher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "AjaxTeacherEmailDBVerificationServlet")
public class AjaxTeacherEmailDBVerificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            Teacher teacher = new Teacher();
            PrintWriter out = response.getWriter();

            teacher.setTeacheremailid(request.getParameter("email"));

            TeacherDao teacherDao = new TeacherDao();
            boolean check = teacherDao.TeacherEmailDbVerification(teacher);

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
