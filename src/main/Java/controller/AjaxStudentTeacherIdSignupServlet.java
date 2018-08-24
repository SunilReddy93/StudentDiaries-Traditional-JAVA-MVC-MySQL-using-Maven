package controller;

import dao.StudentDao;
import model.Teacher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AjaxStudentTeacherIdSignupServlet")
public class AjaxStudentTeacherIdSignupServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {

        try
        {

            PrintWriter out = response.getWriter();

            StudentDao studentDao = new StudentDao();
            ArrayList<Teacher> teacherids;

            teacherids = studentDao.AjaxStudentTeacherIdSignup();

            for (int i = 0; i < teacherids.size(); i++)
            {
                out.println("<option value = '"+teacherids.get(i).getTeacherid()+"'>"+teacherids.get(i).getTeacherid()+"</option>");


            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
