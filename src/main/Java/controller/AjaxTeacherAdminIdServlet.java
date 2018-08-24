package controller;

import dao.TeacherDao;
import model.Admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AjaxTeacherAdminIdServlet")
public class AjaxTeacherAdminIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            PrintWriter out = response.getWriter();

            TeacherDao teacherDao = new TeacherDao();
            ArrayList<Admin> adminids;

            adminids = teacherDao.AjaxTeacherAdminId();

            for (int i = 0; i < adminids.size(); i++)
            {
                out.println("<option value = '"+adminids.get(i).getAdminid()+"'>"+adminids.get(i).getAdminid()+"</option>");


            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

}
