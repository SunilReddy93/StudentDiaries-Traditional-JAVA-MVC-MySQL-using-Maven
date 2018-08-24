package controller;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import dao.FeedbackDao;
import dao.StudentDao;
import model.Feedback;
import model.SendMail;
import model.Student;
import model.Teacher;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "TeacherStudentFeedbackSubmitServlet")
public class TeacherStudentFeedbackSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        if (session.getAttribute("teacheruser") != null && session.getAttribute("teacherpassword") != null)
        {
            try
            {
                Feedback feedback =  new Feedback();
                feedback.setFeedbackdate(request.getParameter("date"));

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date1 = simpleDateFormat.parse(feedback.getFeedbackdate());

                Date date = new Date();
                Calendar c = Calendar.getInstance();
                //c.setTime(date);
                //c.add(Calendar.DATE, 1);
                date = c.getTime();

                if (date1.compareTo(date) <= 0)//to check if the feedback is not for future
                {

                    Student student = new Student();
                    student.setStudentid(Integer.parseInt(request.getParameter("sid")));

                    Teacher teacher = new Teacher();
                    teacher.setTeacherid(Integer.parseInt(session.getAttribute("sessionteacherid").toString()));

                    /*Continued from above*/
                    feedback.setAttendance(request.getParameter("attend"));
                    feedback.setPerformance(Integer.parseInt(request.getParameter("performance")));
                    feedback.setBehaviour(Integer.parseInt(request.getParameter("behaviour")));
                    feedback.setImprovement(Integer.parseInt(request.getParameter("improvement")));
                    feedback.setRemark(request.getParameter("remark"));

                    FeedbackDao feedbackDao = new FeedbackDao();
                    boolean check = feedbackDao.insertFeedback(student, teacher, feedback);

                    if (check == true) {
                        StudentDao studentDao = new StudentDao();
                        boolean check2 = studentDao.getSomeStudentInfo(student);

                        if (check2 == true) {
                            boolean check3 = feedbackDao.getFeedbackDetails(student, feedback);

                            if (check3 == true) {

                                SimpleDateFormat dateformat = new SimpleDateFormat("E, dd MMM yyyy");
                                String desireddate = dateformat.format(feedback.getSQLDatefeedbakcdate());

                                /*Preparing to set path*/




                                /*Preparing the creation of PDF DOCUMENT*/
                                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                                PdfWriter.getInstance(document, new FileOutputStream(getServletContext().getRealPath("/Documents/StudentDiariesFeedback.pdf")));


                                document.open();
                                document.add(new Paragraph(org.apache.commons.lang.StringUtils.capitalize(student.getStudentfirstname()) + " " + org.apache.commons.lang.StringUtils.capitalize(student.getStudentlastname() + "'s Feedback for " + desireddate)));
                                document.add(new Paragraph("Feedback Updated by Teacher ID: " + teacher.getTeacherid()));

                                document.add(new Paragraph("Attendance: " + org.apache.commons.lang.StringUtils.capitalize(feedback.getAttendance()) + ","));
                                document.add(new Paragraph("Performance: " + feedback.getPerformance() + "/10,"));
                                document.add(new Paragraph("Behaviour: " + feedback.getBehaviour() + "/10,"));
                                document.add(new Paragraph("Improvement: " + feedback.getImprovement() + "/10,"));
                                document.add(new Paragraph("Remark: " + feedback.getRemark()));
                                document.close();


                                /***************************************************************************************************/
                                /*EMAIL SECTION*/
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss");
                                LocalDateTime todaydate = LocalDateTime.now();



                                /*FOR SENDING EMAIL*/
                                String to = student.getParentemailid();
                                String subject = org.apache.commons.lang.StringUtils.capitalize(student.getStudentfirstname()) + " " + org.apache.commons.lang.StringUtils.capitalize(student.getStudentlastname()) + " - Teacher Feedback as updated on " + dtf.format(todaydate);
                                String message = "Dear Parent, \n\nPlease find attachment. \n\nRegards, \n\nStudentDiaries 2018";

                                String filename = getServletContext().getRealPath("/Documents/StudentDiariesFeedback.pdf");
                                String user = "studentdiaries2018@gmail.com";
                                String password = "imsd@2018";
                                SendMail.send(to, subject, message, filename, user, password);
                                // System.out.println("Mail SuccessFully Sent");


                                /***********************************************************************/
                                response.sendRedirect("TeacherStudentFeedbackSuccess.jsp");


                            } else {
                                response.sendRedirect("TeacherUnknownError.jsp");

                            }
                        } else {
                            response.sendRedirect("TeacherUnknownError.jsp");
                        }

                    } else {
                        response.sendRedirect("TeacherFeedbackAlreadyUpdated.jsp");
                    }

                }
                else
                {
                    response.sendRedirect("TeacherInvalidFeedbackCheckDate.jsp");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                response.sendRedirect("TeacherFeedbackAlreadyUpdated.jsp");
            }

        }
        else
        {
            response.sendRedirect("TeacherLogin.jsp");
        }
    }


}
