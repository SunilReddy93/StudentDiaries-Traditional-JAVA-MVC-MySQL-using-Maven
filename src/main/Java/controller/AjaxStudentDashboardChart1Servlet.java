package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.StudentDao;
import model.Feedback;
import model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name = "AjaxStudentDashboardChart1Servlet")
public class AjaxStudentDashboardChart1Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("studentuser") != null && session.getAttribute("studentpassword") != null)
        {
            try {

                PrintWriter out = response.getWriter();

                Student student = new Student();
                student.setStudentid(Integer.parseInt(session.getAttribute("sessionstudentid").toString()));

                StudentDao studentDao = new StudentDao();
                ArrayList<Feedback> feedbacks;

                feedbacks = studentDao.getStudentFeedbackScores(student);

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                response.setContentType("application/json");
                out.println(gson.toJson(feedbacks));
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            response.sendRedirect("StudentLogin.jsp");
        }

    }

}
