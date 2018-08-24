package dao;

import model.Feedback;
import model.Student;
import model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FeedbackDao {

    AdminDao adminDao = new AdminDao();

    /*TO COUNT NO. OF FEEDBACKS*/

    public int countFeedbacks(Student student) throws Exception
    {
        int num = 0;
        try
        {
            String s1 = "SELECT * FROM feedback WHERE student_id = ?";
            Connection con = adminDao.getConnection();

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, student.getStudentid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                num++;
            }

            pst.close();
            rs.close();
            con.close();

        }catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return num;
    }


    /*Insert Feedback*/
    public boolean insertFeedback(Student student, Teacher teacher, Feedback feedback) throws Exception {
        boolean check = false;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "insert into feedback(feedback_date, attendance, performance, behaviour, improvement, remark, student_id, teacher_id) values (STR_TO_DATE(?, '%d/%m/%Y'),?,?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, feedback.getFeedbackdate());
            pst.setString(2, feedback.getAttendance());
            pst.setInt(3, feedback.getPerformance());
            pst.setInt(4, feedback.getBehaviour());
            pst.setInt(5, feedback.getImprovement());
            pst.setString(6, feedback.getRemark());
            pst.setInt(7, student.getStudentid());
            pst.setInt(8, teacher.getTeacherid());
            int num = pst.executeUpdate();

            if (num!=0)
            {
                check = true;
            }

            pst.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }



    /*Select All feedback details*/
    public boolean getFeedbackDetails(Student student, Feedback feedback) throws Exception {
        boolean check = false;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "select * from feedback where (feedback_date = STR_TO_DATE(?, '%d/%m/%Y') AND student_id = ?)";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, feedback.getFeedbackdate());
            pst.setInt(2, student.getStudentid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                feedback.setSQLDatefeedbakcdate(rs.getDate("feedback_date"));
                feedback.setAttendance(rs.getString("attendance"));
                feedback.setPerformance(rs.getInt("performance"));
                feedback.setBehaviour(rs.getInt("behaviour"));
                feedback.setImprovement(rs.getInt("improvement"));
                feedback.setRemark(rs.getString("remark"));

                check = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }


}
