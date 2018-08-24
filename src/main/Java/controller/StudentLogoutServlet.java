package controller;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "StudentLogoutServlet")
public class StudentLogoutServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            HttpSession session = request.getSession();

            if (session.getAttribute("studentuser") != null && session.getAttribute("studentpassword") != null) {


                session.removeAttribute("sessionstudentfirstname");
                session.removeAttribute("sessionstudentlastname");
                session.removeAttribute("sessionstudentid");


                if (session.getAttribute("StudentLoginIncorrectCredentials") != null) {
                    session.removeAttribute("StudentLoginIncorrectCredentials");
                }
                session.removeAttribute("studentuser");
                session.removeAttribute("studentpassword");
                response.sendRedirect("StudentLogin.jsp");
            }
            else{
                response.sendRedirect("StudentLogin.jsp");
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
