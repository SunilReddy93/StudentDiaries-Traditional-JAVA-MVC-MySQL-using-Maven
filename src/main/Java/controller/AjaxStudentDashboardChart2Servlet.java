package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.StudentDao;
import model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AjaxStudentDashboardChart2Servlet")
public class AjaxStudentDashboardChart2Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        HttpSession session = request.getSession();
        if (session.getAttribute("studentuser") != null && session.getAttribute("studentpassword") != null)
        {
            try {

                PrintWriter out = response.getWriter();
                Student student = new Student();
                student.setStudentid(Integer.parseInt(session.getAttribute("sessionstudentid").toString()));
                int[] attend = new int[3];
                StudentDao studentDao = new StudentDao();
                attend = studentDao.getAttendanceScore(student);


                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                response.setContentType("appliction/json");
                out.println(gson.toJson(attend));
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
