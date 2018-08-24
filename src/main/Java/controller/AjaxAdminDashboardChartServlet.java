package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.AdminDao;
import model.Admin;
import model.Feedback;
import model.Teacher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AjaxAdminDashboardChartServlet")
public class AjaxAdminDashboardChartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("adminuser") != null && session.getAttribute("password") != null) {
            try
            {
                PrintWriter out = response.getWriter();

                Admin admin = new Admin();
                admin.setAdminid(Integer.parseInt(session.getAttribute("sessionadminid").toString()));

                AdminDao adminDao = new AdminDao();
                ArrayList<Teacher> teachers;

                teachers = adminDao.getTeacherIds(admin);

                ArrayList<Feedback> feedbackArrayList = new ArrayList<>();


                for (int i = 0; i < teachers.size(); i++) {
                    Teacher teacher = new Teacher();
                    teacher.setTeacherid(teachers.get(i).getTeacherid());

                    Feedback feedback = new Feedback();

                    boolean check = adminDao.getTeacherAvgScore(teacher, feedback);

                    if (check == true) {
                        feedbackArrayList.add(feedback);
                    }

                }

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                response.setContentType("application/json");
                out.println(gson.toJson(feedbackArrayList));
                out.flush();
                out.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            response.sendRedirect("AdminLogin.jsp");
        }

    }

}
