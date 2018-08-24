package controller;


import dao.TeacherDao;
import model.Admin;
import org.apache.commons.lang.WordUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "AjaxTeacherAdminSchoolSignupServlet")
public class AjaxTeacherAdminSchoolSignupServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            PrintWriter out = response.getWriter();
            Admin admin = new Admin();

            admin.setAdminid(Integer.parseInt(request.getParameter("id")));

            TeacherDao teacherDao = new TeacherDao();
            boolean check = teacherDao.AjaxTeacherAdminSchoolSignup(admin);

            if (check == true)
            {
                out.println("<option value = '"+WordUtils.capitalizeFully(admin.getAdminschoolname())+"'>"+WordUtils.capitalizeFully(admin.getAdminschoolname())+"</option>");
            }



        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
