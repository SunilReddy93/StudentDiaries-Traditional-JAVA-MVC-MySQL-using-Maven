package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.TeacherDao;
import model.Teacher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name = "AjaxTeacherDashboardPieServlet")
public class AjaxTeacherDashboardPieServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        if (session.getAttribute("teacheruser") != null && session.getAttribute("teacherpassword") != null)
        {
            try {
                PrintWriter out = response.getWriter();

                Teacher teacher = new Teacher();
                teacher.setTeacherid(Integer.parseInt(session.getAttribute("sessionteacherid").toString()));
                TeacherDao teacherDao = new TeacherDao();
                int total = teacherDao.getTotalTeacherFeedbacks(teacher);

                int goodtstudents = teacherDao.getGoodTeacherFeedbacks(teacher);

                int averagestudents = teacherDao.getAverageTeacherFeedbacks(teacher);

                ArrayList<Integer> overallfeedback = new ArrayList<>();
                overallfeedback.add(goodtstudents);
                overallfeedback.add(averagestudents);
                overallfeedback.add(total);


                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                response.setContentType("appliction/json");
                out.println(gson.toJson(overallfeedback));
                out.flush();
                out.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            response.sendRedirect("TeacherLogin.jsp");
        }
    }


}
