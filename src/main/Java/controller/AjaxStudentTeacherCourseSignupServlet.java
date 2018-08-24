package controller;

import dao.StudentDao;

import model.Teacher;
import org.apache.commons.lang.WordUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "AjaxStudentTeacherCourseSignupServlet")
public class AjaxStudentTeacherCourseSignupServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {

            PrintWriter out = response.getWriter();
            Teacher teacher = new Teacher();

            teacher.setTeacherid(Integer.parseInt(request.getParameter("id")));

            StudentDao studentDao = new StudentDao();
            boolean check = studentDao.AjaxStudentTeacherCourseSignup(teacher);

            if (check == true)
            {
                out.println("<option value = '"+WordUtils.capitalizeFully(teacher.getTeachercoursename())+"'>"+WordUtils.capitalizeFully(teacher.getTeachercoursename())+"</option>");
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
