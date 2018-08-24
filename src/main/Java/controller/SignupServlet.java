package controller;

import dao.AdminDao;
import dao.StudentDao;
import dao.TeacherDao;
import model.Admin;
import model.Student;
import model.Teacher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignupServlet")
public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {


           String fno = request.getParameter("fno");

           /*Account created by new admin user*/
           if (fno.equals("1"))
           {
               try
               {

                   Admin admin = new Admin();
                   admin.setAdminfirstname(request.getParameter("firstname"));
                   admin.setAdminlastname(request.getParameter("lastname"));
                   admin.setAdminemailid(request.getParameter("email").toLowerCase());
                   admin.setAdminphoneno(Long.parseLong(request.getParameter("phone")));
                   admin.setAdminpassword(request.getParameter("password"));
                   admin.setAdminschoolname(request.getParameter("school"));
                   admin.setAdmincity(request.getParameter("city"));


                   AdminDao admindao = new AdminDao();

                   int i = admindao.insertAll(admin);

                   if(i!=0)
                   {

                       HttpSession session = request.getSession();
                       session.setAttribute("adminuser", admin.getAdminemailid());
                       session.setAttribute("password", admin.getAdminpassword());
                       session.setAttribute("sessionadminfirstname", admin.getAdminfirstname());
                       session.setAttribute("sessionadminlastname", admin.getAdminlastname());

                       /*To create adminid session*/
                       boolean check = admindao.fetchadminid(admin);

                       if (check == true)
                       {
                           session.setAttribute("sessionadminid", admin.getAdminid());
                       }


                       response.sendRedirect("AdminDashboard.jsp");

                   }



               }
               catch (Exception e)
               {
                   response.sendRedirect("AdminSignupError.jsp");
               }

           }


           /*When a new Teacher user signs up*/
           if (fno.equals("2"))
           {
               try
               {

                   Teacher teacher = new Teacher();

                   teacher.setTeacherfirstname(request.getParameter("firstname"));
                   teacher.setTeacherlastname(request.getParameter("lastname"));
                   teacher.setTeacheremailid(request.getParameter("teacheremail").toLowerCase());
                   teacher.setTeacherpassword(request.getParameter("password"));
                   teacher.setTeacherphoneno(Long.parseLong(request.getParameter("teacherphone")));
                   teacher.setTeacheradminid(Integer.parseInt(request.getParameter("adminid")));
                   teacher.setTeachercoursename(request.getParameter("course"));
                   teacher.setTeacherschool(request.getParameter("school"));

                   TeacherDao teacherdao = new TeacherDao();
                   int i = teacherdao.insertAll(teacher);

                   if (i!=0)
                   {
                       /*Create required Teacher Sessions*/
                       HttpSession session = request.getSession();
                       session.setAttribute("teacheruser", teacher.getTeacheremailid());
                       session.setAttribute("teacherpassword", teacher.getTeacherpassword());

                       session.setAttribute("sessionteacherfirstname", teacher.getTeacherfirstname());
                       session.setAttribute("sessionteacherlastname", teacher.getTeacherlastname());

                       /*create a session for teacher id*/
                       boolean check = teacherdao.fetchteacherid(teacher);

                       if (check == true)
                       {
                           session.setAttribute("sessionteacherid", teacher.getTeacherid());
                       }


                       response.sendRedirect("TeacherDashboard.jsp");


                   }

               }
               catch (Exception e)
               {
                   response.sendRedirect("TeacherSignupError.jsp");
               }
           }


           /*When New Student user creates an account*/
           if (fno.equals("3"))
           {
               try
               {

                   Student student = new Student();

                   student.setStudentfirstname(request.getParameter("firstname"));
                   student.setStudentlastname(request.getParameter("lastname"));
                       student.setStudentrollno(Integer.parseInt(request.getParameter("rollno")));
                   student.setStudentfathername(request.getParameter("fathername"));
                   student.setStudentmothername(request.getParameter("mothername"));
                   student.setStudentemailid(request.getParameter("studentemail").toLowerCase());
                   student.setParentemailid(request.getParameter("parentemail"));
                   student.setStudentphoneno(Long.parseLong(request.getParameter("studentphone")));
                   student.setParentphoneno(Long.parseLong(request.getParameter("parentphone")));
                   student.setStudentpassword(request.getParameter("password"));
                   student.setStudentcoursename(request.getParameter("course"));
                   student.setStudentteacherid(Integer.parseInt(request.getParameter("teacher")));
                   student.setStudentyear(Integer.parseInt(request.getParameter("year")));
                   student.setSchoolname(request.getParameter("school"));

                   StudentDao studentdao = new StudentDao();
                   int i = studentdao.insertAll(student);

                   if (i!= 0)
                   {
                       /*Create student sessions*/
                       HttpSession session = request.getSession();
                       session.setAttribute("studentuser", student.getStudentemailid());
                       session.setAttribute("studentpassword", student.getStudentpassword());
                       session.setAttribute("sessionstudentfirstname", student.getStudentfirstname());
                       session.setAttribute("sessionstudentlastname", student.getStudentlastname());

                       /*Now create studentid session*/
                       boolean check = studentdao.fetchstudentid(student);

                       if (check == true)
                       {
                           session.setAttribute("sessionstudentid", student.getStudentid());
                       }


                       response.sendRedirect("StudentDashboard.jsp");
                   }

               }
               catch (Exception e)
               {
                   response.sendRedirect("StudentSignupError.jsp");
               }

           }

           /*When Admin Creates Teacher Account*/
           if (fno.equals("4"))
           {
               try
               {

                   Teacher teacher = new Teacher();

                   teacher.setTeacherfirstname(request.getParameter("firstname"));
                   teacher.setTeacherlastname(request.getParameter("lastname"));
                   teacher.setTeacheremailid(request.getParameter("teacheremail").toLowerCase());
                   teacher.setTeacherpassword(request.getParameter("password"));
                   teacher.setTeacherphoneno(Long.parseLong(request.getParameter("teacherphone")));
                   teacher.setTeacheradminid(Integer.parseInt(request.getParameter("adminid")));
                   teacher.setTeachercoursename(request.getParameter("course"));
                   teacher.setTeacherschool(request.getParameter("school"));

                   TeacherDao teacherdao = new TeacherDao();
                   int i = teacherdao.insertAll(teacher);

                   if (i!=0)
                   {
                       response.sendRedirect("AdminTeacherUsers.jsp");

                   }
                   else {
                       response.sendRedirect("AdminTeacherSignupFailure.jsp");
                   }

               }
               catch (Exception e)
               {
                   e.printStackTrace();
                   response.sendRedirect("AdminTeacherSignupFailure.jsp");
               }
           }

           /*When Admin creates student account*/
           if (fno.equals("5"))
           {
               try
               {
                   HttpSession session = request.getSession();

                   Student student = new Student();

                   student.setStudentfirstname(request.getParameter("firstname"));
                   student.setStudentlastname(request.getParameter("lastname"));
                   student.setStudentrollno(Integer.parseInt(request.getParameter("rollno")));
                   student.setStudentfathername(request.getParameter("fathername"));
                   student.setStudentmothername(request.getParameter("mothername"));
                   student.setStudentemailid(request.getParameter("studentemail").toLowerCase());
                   student.setParentemailid(request.getParameter("parentemail"));
                   student.setStudentphoneno(Long.parseLong(request.getParameter("studentphone")));
                   student.setParentphoneno(Long.parseLong(request.getParameter("parentphone")));
                   student.setStudentpassword(request.getParameter("password"));
                   student.setStudentcoursename(request.getParameter("course"));
                   student.setStudentteacherid(Integer.parseInt(request.getParameter("teacher")));
                   student.setStudentyear(Integer.parseInt(request.getParameter("year")));
                   student.setSchoolname(request.getParameter("school"));

                   StudentDao studentdao = new StudentDao();
                   int i = studentdao.insertAll(student);

                   if (i!= 0)
                   {
                       /*remove temp session created by admin for operations*/
                       session.removeAttribute("temp_teacher_id");
                       response.sendRedirect("AdminTeacherStudentSelection.jsp");
                   }
               }
               catch (Exception e)
               {
                   response.sendRedirect("AdminUnknownError.jsp");
               }
           }

           /*When Teacher creates Student Account*/
           if (fno.equals("6"))
           {
               try
               {

                   Student student = new Student();

                   student.setStudentfirstname(request.getParameter("firstname"));
                   student.setStudentlastname(request.getParameter("lastname"));
                   student.setStudentrollno(Integer.parseInt(request.getParameter("rollno")));
                   student.setStudentfathername(request.getParameter("fathername"));
                   student.setStudentmothername(request.getParameter("mothername"));
                   student.setStudentemailid(request.getParameter("studentemail").toLowerCase());
                   student.setParentemailid(request.getParameter("parentemail"));
                   student.setStudentphoneno(Long.parseLong(request.getParameter("studentphone")));
                   student.setParentphoneno(Long.parseLong(request.getParameter("parentphone")));
                   student.setStudentpassword(request.getParameter("password"));
                   student.setStudentcoursename(request.getParameter("course"));
                   student.setStudentteacherid(Integer.parseInt(request.getParameter("teacher")));
                   student.setStudentyear(Integer.parseInt(request.getParameter("year")));
                   student.setSchoolname(request.getParameter("school"));

                   StudentDao studentdao = new StudentDao();
                   int num = studentdao.insertAll(student);

                   if (num!= 0)
                   {
                       response.sendRedirect("TeacherStudentUsers.jsp");
                   }

               }
               catch (Exception e)
               {
                   e.printStackTrace();
                   response.sendRedirect("TeacherUnknownError.jsp");
               }
           }



    }


}
