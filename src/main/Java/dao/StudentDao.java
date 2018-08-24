package dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class StudentDao {

    AdminDao adminDao = new AdminDao();


    /*Insert all Student User info into the database*/
    public int insertAll(Student student) throws Exception {
        int status = 0;

        try
        {

            String s1 = "insert into student_signup (student_firstname, student_lastname, rollno, father_name, mothername, student_emailid, parent_emailid, student_phoneno, parent_phoneno, password, teacher_id, course_name, year, school_name) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, student.getStudentfirstname());
            pst.setString(2, student.getStudentlastname());
            pst.setInt(3, student.getStudentrollno());
            pst.setString(4, student.getStudentfathername());
            pst.setString(5, student.getStudentmothername());
            pst.setString(6, student.getStudentemailid());
            pst.setString(7, student.getParentemailid());
            pst.setLong(8, student.getStudentphoneno());
            pst.setLong(9, student.getParentphoneno());
            pst.setString(10, student.getStudentpassword());
            pst.setInt(11, student.getStudentteacherid());
            pst.setString(12, student.getStudentcoursename());
            pst.setInt(13, student.getStudentyear());
            pst.setString(14, student.getSchoolname());

            status = pst.executeUpdate();

            pst.close();
            con.close();


        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }

        return status;
    }


    /*Get Student id to create a session*/
    public boolean fetchstudentid(Student student) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select student_id from student_signup where student_emailid = ?";


            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, student.getStudentemailid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                student.setStudentid(rs.getInt("student_id"));
                check = true;
            }

            pst.close();
            rs.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }


    /*Ajax to get all teacher ids for student signup*/
    public ArrayList<Teacher> AjaxStudentTeacherIdSignup() throws Exception {

        ArrayList<Teacher> teacherids = new ArrayList<>();

        try
        {
            String s1 = "select teacher_id from teacher_signup";
            Connection con = adminDao.getConnection();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(s1);



            while (rs.next())
            {
                Teacher teacher = new Teacher();
                teacher.setTeacherid(rs.getInt("teacher_id"));

                teacherids.add(teacher);
            }

            st.close();
            rs.close();
            con.close();


        }catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return teacherids;
    }



    /*Ajax get course info corresponding to teacher for student signup*/
    public boolean AjaxStudentTeacherCourseSignup(Teacher teacher) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select course_name from teacher_signup where teacher_id = ?";

            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, teacher.getTeacherid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                teacher.setTeachercoursename(rs.getString("course_name"));
                check = true;
            }

            pst.close();
            rs.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }



    /*Ajax get school info corresponding to teacher for student signup*/
    public boolean AjaxStudentTeacherSchoolSignup(Teacher teacher) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select school_name from teacher_signup where teacher_id = ?";

            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, teacher.getTeacherid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                teacher.setTeacherschool(rs.getString("school_name"));
                check = true;
            }

            pst.close();
            rs.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }

    /*Ajax StudentEmail DB verification for signup*/
    public boolean AjaxStudentEmailDBVerification(Student student) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select * from student_signup where student_emailid = ?";


            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, student.getStudentemailid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                check = true;
            }

            pst.close();
            rs.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }


    /*Ajax ParentEmail DB verification for signup*/
    public boolean AjaxParentEmailDBVerification(Student student) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select * from student_signup where parent_emailid = ?";


            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, student.getParentemailid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                check = true;
            }

            pst.close();
            rs.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }



    /*Ajax Student Phone DB Verification for student signup*/
    public boolean AjaxStudentPhoneDBVerification(Student student) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select * from student_signup where student_phoneno = ?";

            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setLong(1, student.getStudentphoneno());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                check = true;
            }

            pst.close();
            rs.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }


    /*Ajax Parent Phone DB Verification for student signup*/
    public boolean AjaxParentPhoneDBVerification(Student student) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select * from student_signup where parent_phoneno = ?";

            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setLong(1, student.getParentphoneno());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                check = true;
            }

            pst.close();
            rs.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }


    /*For Student Login*/
    public boolean studentLoginVerification(Student student) throws Exception
    {
        boolean check = false;

        try
        {
            String s1 = "select student_id, student_firstname, student_lastname from student_signup where student_emailid = ? AND password = ?";

            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, student.getStudentemailid());
            pst.setString(2, student.getStudentpassword());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                student.setStudentid(rs.getInt("student_id"));
                student.setStudentfirstname(rs.getString("student_firstname"));
                student.setStudentlastname(rs.getString("student_lastname"));
                check = true;
            }

            pst.close();
            rs.close();
            con.close();


        }catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }



    /*Get student info in asc order to display for ADMINSTUDENTUSERS and other page*/
    public ArrayList<Student> getStudentInfo(Teacher teacher) throws Exception
    {
        ArrayList<Student> studentinfo = new ArrayList<>();
        try
        {
            String s1 = "select student_id, student_firstname, student_lastname, rollno, course_name from student_signup where teacher_id = ? order by rollno ASC";

            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, teacher.getTeacherid());
            ResultSet rs = pst.executeQuery();



            while (rs.next())
            {
                Student student = new Student();

                student.setStudentid(rs.getInt("student_id"));
                student.setStudentfirstname(rs.getString("student_firstname"));
                student.setStudentlastname(rs.getString("student_lastname"));
                student.setStudentrollno(rs.getInt("rollno"));
                student.setStudentcoursename(rs.getString("course_name"));

                studentinfo.add(student);

            }

            pst.close();
            rs.close();
            con.close();


        }catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return studentinfo;
    }


    /*get all student data*/
    public ArrayList<Student> getAllStudentInfo(Student student) throws Exception
    {
        ArrayList<Student> studentArrayList = new ArrayList<>();

        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "select * from student_signup where student_id = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, student.getStudentid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                Student student1 = new Student();

                student1.setStudentid(rs.getInt("student_id"));
                student1.setStudentfirstname(rs.getString("student_firstname"));
                student1.setStudentlastname(rs.getString("student_lastname"));
                student1.setStudentrollno(rs.getInt("rollno"));
                student1.setStudentfathername(rs.getString("father_name"));
                student1.setStudentmothername(rs.getString("mothername"));
                student1.setStudentemailid(rs.getString("student_emailid"));
                student1.setParentemailid(rs.getString("parent_emailid"));
                student1.setStudentphoneno(rs.getLong("student_phoneno"));
                student1.setParentphoneno(rs.getLong("parent_phoneno"));
                student1.setStudentpassword(rs.getString("password"));
                student1.setStudentteacherid(rs.getInt("teacher_id"));
                student1.setStudentcoursename(rs.getString("course_name"));
                student1.setStudentyear(rs.getInt("year"));
                student1.setSchoolname(rs.getString("school_name"));

                studentArrayList.add(student1);
            }


            pst.close();
            rs.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return studentArrayList;
    }


    /*DELETE MULTIPLE STUDENTS VIA ADMIN ACCOUNT*/
    public boolean deleteStudent(ArrayList<Student> studentids) throws Exception
    {
        boolean check = false;
        try
        {
            String s1 = "DELETE FROM student_signup WHERE student_id = ?";
            Connection con = adminDao.getConnection();

            for (int i = 0; i < studentids.size(); i++)
            {
                PreparedStatement pst = con.prepareStatement(s1);
                pst.setInt(1, studentids.get(i).getStudentid());
                int n = pst.executeUpdate();

                pst.close();

                if (n!=0)
                {
                    check = true;
                }
                else if (n == 0)
                {
                    check = false;
                }
            }

            con.close();


        }catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }


    /*GET SOME STUDENT INFO FOR TEACHERFEEDBACK FORM PAGE*/
    public boolean getSomeStudentInfo(Student student) throws Exception {
        boolean check = false;
        try
        {
            Connection con= adminDao.getConnection();
            String s1 = "select student_id, student_firstname, student_lastname, rollno, parent_emailid from student_signup where student_id = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, student.getStudentid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                student.setStudentid(rs.getInt("student_id"));
                student.setStudentfirstname(rs.getString("student_firstname"));
                student.setStudentlastname(rs.getString("student_lastname"));
                student.setStudentrollno(rs.getInt("rollno"));
                student.setParentemailid(rs.getString("parent_emailid"));

                check = true;
            }
            pst.close();
            rs.close();
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }




    /*Get all feedback scores of students for last 10 days*/
    public ArrayList<Feedback> getStudentFeedbackScores(Student student) throws Exception
    {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try
        {

            Connection con = adminDao.getConnection();
            String s1 = "select * from feedback where student_id = ? order by feedback_date DESC LIMIT  ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, student.getStudentid());
            pst.setInt(2, 10);
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                Feedback feedback = new Feedback();

                feedback.setSQLDatefeedbakcdate(rs.getDate("feedback_date"));
                feedback.setPerformance(rs.getInt("performance"));
                feedback.setBehaviour(rs.getInt("behaviour"));
                feedback.setImprovement(rs.getInt("improvement"));

                feedbacks.add(feedback);

            }
            pst.close();
            rs.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return feedbacks;
    }


    /*Get Attendance Report of Student of last 10 days for pie chart*/
    public int[] getAttendanceScore(Student student) throws Exception
    {
        int[] attendance = new int[3];
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "select attendance from feedback where student_id = ? order by feedback_date DESC LIMIT ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, student.getStudentid());
            pst.setInt(2, 10);
            ResultSet rs = pst.executeQuery();

            int present = 0;
            int absent = 0;
            int total = 0;

            while (rs.next())
            {
                if (rs.getString("attendance").equals("present"))
                {
                    present++;
                }
                else if (rs.getString("attendance").equals("absent"))
                {
                    absent++;
                }
                total++;

            }

            attendance[0] = present;
            attendance[1] = absent;
            attendance[2] = total;

            pst.close();
            rs.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return attendance;
    }



    /*Get all Student data for profile*/
    public boolean getAllStudentData(Student student) throws Exception
    {
        boolean check = false;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "select * from student_signup where student_id = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, student.getStudentid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                student.setStudentid(rs.getInt("student_id"));
                student.setStudentfirstname(rs.getString("student_firstname"));
                student.setStudentlastname(rs.getString("student_lastname"));
                student.setStudentrollno(rs.getInt("rollno"));
                student.setStudentfathername(rs.getString("father_name"));
                student.setStudentmothername(rs.getString("mothername"));
                student.setStudentemailid(rs.getString("student_emailid"));
                student.setParentemailid(rs.getString("parent_emailid"));
                student.setStudentphoneno(rs.getLong("student_phoneno"));
                student.setParentphoneno(rs.getLong("parent_phoneno"));
                student.setStudentteacherid(rs.getInt("teacher_id"));
                student.setStudentcoursename(rs.getString("course_name"));
                student.setStudentyear(rs.getInt("year"));
                student.setSchoolname(rs.getString("school_name"));

                check = true;
            }


            pst.close();
            rs.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }



    /*Update Student Profile*/
    public boolean updateStudent(Student student) throws Exception
    {
        boolean check = false;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "update student_signup SET student_firstname = ?, student_lastname = ?, rollno = ?, father_name = ?, mothername = ?, parent_emailid = ?, student_phoneno =?, parent_phoneno = ?, year = ? WHERE student_id = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, student.getStudentfirstname());
            pst.setString(2, student.getStudentlastname());
            pst.setInt(3, student.getStudentrollno());
            pst.setString(4, student.getStudentfathername());
            pst.setString(5, student.getStudentmothername());
            pst.setString(6, student.getParentemailid());
            pst.setLong(7, student.getStudentphoneno());
            pst.setLong(8, student.getParentphoneno());
            pst.setInt(9, student.getStudentyear());
            pst.setInt(10, student.getStudentid());

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


    /*Delete a particular Student*/
    public boolean deleteParticularStudent(Student student) throws Exception
    {
        boolean check = false;

        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "DELETE FROM student_signup WHERE student_id = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, student.getStudentid());
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


    /*Student Change Password Request*/
    public boolean changeStudentPasswordRequest(Student student) throws Exception
    {
        boolean check = false;
        try {
            Connection connection = adminDao.getConnection();
            String s1 = "select password from student_signup where student_id = ?";

            PreparedStatement pst = connection.prepareStatement(s1);
            pst.setInt(1, student.getStudentid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                student.setStudentpassword(rs.getString("password"));
                check = true;
            }

            pst.close();
            rs.close();
            connection.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }


    /*Update Student Password*/
    public boolean updateStudentPassword(Student student, ChangePassword changePassword) throws Exception
    {
        boolean check = false;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "UPDATE student_signup SET password = ? WHERE student_id = ?";
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, changePassword.getNewpassword());
            pst.setInt(2, student.getStudentid());
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


    /*Student Reset Password*/
    public boolean studentPasswordReset(Student student) throws Exception
    {
        boolean check = false;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "select student_firstname, password from student_signup where student_emailid = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, student.getStudentemailid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                student.setStudentfirstname(rs.getString("student_firstname"));
                student.setStudentpassword(rs.getString("password"));
                check = true;
            }

            pst.close();
            rs.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }


}
