package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.PrintWriter;

@WebFilter(filterName = "SignupValidationFilter")
public class SignupValidationFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
    {
        try
        {
            String fno = req.getParameter("fno");
            PrintWriter out = resp.getWriter();
            /*For Admin*/
            if (fno.equals("1"))
            {
                String firstname = req.getParameter("firstname");
                String lastname = req.getParameter("lastname");
                String phone = req.getParameter("phone");
                String password = req.getParameter("password");
                String school = req.getParameter("school");
                String city = req.getParameter("city");

                String phoneregex = "^[0-9]{10}$";
                String stringregex = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$";

                if (!phone.matches(phoneregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Phone Number. Please read for error messages.</h1>");
                }
                else if (password.length() < 6 || password.length() > 15)
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Password. Please read for error messages.</h1>");
                }
                else if (!school.matches(stringregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid School Name. Please avoid any special character or number.</h1>");
                }
                else if (!firstname.matches(stringregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid First Name. Please avoid any special character or number.</h1>");
                }
                else if (!lastname.matches(stringregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Last Name. Please avoid any special character or number.</h1>");
                }
                else if (!city.matches(stringregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid City. Please avoid any special character or number.</h1>");
                }
                else
                {
                    chain.doFilter(req, resp);
                }

            }

            /*For Teacher*/
            if (fno.equals("2") || fno.equals("4"))
            {
                String firstname = req.getParameter("firstname");
                String lastname = req.getParameter("lastname");
                String phone = req.getParameter("teacherphone");
                String password = req.getParameter("password");

                String phoneregex = "^[0-9]{10}$";
                String stringregex = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$";

                if (!phone.matches(phoneregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Phone Number. Please read for error messages.</h1>");
                }
                else if (password.length() < 6 || password.length() > 15)
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Password. Please read for error messages.</h1>");
                }
                else if (!firstname.matches(stringregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid First Name. Please read for error messages.</h1>");
                }
                else if (!lastname.matches(stringregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Last Name. Please read for error messages.</h1>");
                }
                else
                {
                    chain.doFilter(req, resp);
                }
            }

            /*For Student*/
            if (fno.equals("3") || fno.equals("5") || fno.equals("6"))
            {
                String firstname = req.getParameter("firstname");
                String lastname = req.getParameter("lastname");
                String fathername = req.getParameter("fathername");
                String mothername = req.getParameter("mothername");
                String studentphone = req.getParameter("studentphone");
                String parentphone = req.getParameter("parentphone");
                String password = req.getParameter("password");
                String roll = req.getParameter("rollno");

                String rollregex = "^[0-9]{1,3}$";
                String phoneregex = "^[0-9]{10}$";
                String stringregex = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$";


                if (!studentphone.matches(phoneregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Student Phone Number. Please read for error messages.</h1>");
                }
                else if (!parentphone.matches(phoneregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Parent Phone Number. Please read for error messages.</h1>");
                }
                else if (password.length() < 6 || password.length() > 15)
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Password. Please read for error messages.</h1>");
                }
                else if (!firstname.matches(stringregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid First Name. Please read for error messages.</h1>");
                }
                else if (!lastname.matches(stringregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Last Name. Please read for error messages.</h1>");
                }
                else if (!fathername.matches(stringregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Father Name. Please read for error messages.</h1>");
                }
                else if (!mothername.matches(stringregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Mother Name. Please read for error messages.</h1>");
                }
                else if (!roll.matches(rollregex))
                {
                    out.println("<h1 style='color: red; margin-left=45%;'>Invalid Roll No. Please read for error messages.</h1>");
                }
                else
                {
                    chain.doFilter(req, resp);
                }

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }


}
