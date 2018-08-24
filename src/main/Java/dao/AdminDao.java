package dao;

import model.Admin;
import model.ChangePassword;
import model.Feedback;
import model.Teacher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminDao {


    /*Create Connection*/
    public Connection getConnection() throws Exception
    {
        Connection con = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");


            con = DriverManager.getConnection("jdbc:mysql://node38999-studentdiaries2018.cloud.cms500.com/studentdiaries", "root", "IQAanm51701");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }

        return con;
    }

    /*Insert all admin User info into the database*/
    public int insertAll(Admin admin) throws Exception {
        int status = 0;

        try
        {

            String s1 = "insert into admin_signup (admin_firstname, admin_lastname, admin_emailid, phoneno, password, school_name, city) values(?,?,?,?,?,?,?)";

            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, admin.getAdminfirstname());
            pst.setString(2, admin.getAdminlastname());
            pst.setString(3, admin.getAdminemailid());

            pst.setLong(4, admin.getAdminphoneno());
            pst.setString(5, admin.getAdminpassword());
            pst.setString(6, admin.getAdminschoolname());
            pst.setString(7, admin.getAdmincity());

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


    /*Get admin id to create session*/
    public boolean fetchadminid(Admin admin) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select admin_id from admin_signup where admin_emailid = ?";


            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, admin.getAdminemailid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                admin.setAdminid(rs.getInt("admin_id"));
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


    /*Ajax DB Verification of Phone number*/
    public boolean AdminPhoneDbVerification(Admin admin) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select * from admin_signup where phoneno = ?";


            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setLong(1, admin.getAdminphoneno());
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


    /*Ajax DB Verification of Email id*/
    public boolean AdminEmailDbVerification(Admin admin) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select * from admin_signup where admin_emailid = ?";


            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, admin.getAdminemailid());
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


    /*For Admin Login*/
    public boolean adminLoginVerification(Admin admin) throws Exception
    {
        boolean check = false;

        try
        {
            String s1 = "select admin_id, admin_firstname, admin_lastname from admin_signup where admin_emailid = ? AND password = ?";

            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, admin.getAdminemailid());
            pst.setString(2, admin.getAdminpassword());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                admin.setAdminid(rs.getInt("admin_id"));
                admin.setAdminfirstname(rs.getString("admin_firstname"));
                admin.setAdminlastname(rs.getString("admin_lastname"));
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



    /*Count No. of teachers for counting card*/
    public int countTeachers(Admin admin) throws Exception
    {
        int num = 0;
        try
        {
            String s1 = "SELECT teacher_id FROM teacher_signup where admin_id = ?";
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(s1);

            pst.setInt(1, admin.getAdminid());
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


    /*Get Teacher info in asc order to display for ADMINTEACHERUSERS page*/
    public ArrayList<Teacher> getTeacherInfo(Admin admin) throws Exception
    {
        ArrayList<Teacher> teacherinfo = new ArrayList<>();
        try
        {
            String s1 = "select teacher_id, teacher_firstname, teacher_lastname, course_name from teacher_signup where admin_id = ? order by teacher_firstname ASC";

            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, admin.getAdminid());
            ResultSet rs = pst.executeQuery();



            while (rs.next())
            {
                Teacher teacher = new Teacher();

                teacher.setTeacherid(rs.getInt("teacher_id"));
                teacher.setTeacherfirstname(rs.getString("teacher_firstname"));
                teacher.setTeacherlastname(rs.getString("teacher_lastname"));
                teacher.setTeachercoursename(rs.getString("course_name"));

                teacherinfo.add(teacher);

            }

            pst.close();
            rs.close();
            con.close();


        }catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return teacherinfo;
    }


    /*Get All Admin info for Admin Profile*/
    public boolean getAllAdminInfo(Admin admin) throws Exception
    {
        boolean check = false;

        try
        {

            Connection con = getConnection();
            String s1 = "SELECT * FROM admin_signup WHERE admin_id = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, admin.getAdminid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                admin.setAdminid(rs.getInt("admin_id"));
                admin.setAdminfirstname(rs.getString("admin_firstname"));
                admin.setAdminlastname(rs.getString("admin_lastname"));
                admin.setAdminemailid(rs.getString("admin_emailid"));
                admin.setAdminphoneno(rs.getLong("phoneno"));
                admin.setAdminschoolname(rs.getString("school_name"));
                admin.setAdmincity(rs.getString("city"));

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



    /*Update admin account*/
    public boolean updateAdminAccount(Admin admin) throws Exception {
        boolean check = false;
        try
        {
            Connection con = getConnection();

            String s1 = "update admin_signup SET admin_firstname = ?, admin_lastname = ?, phoneno = ?, school_name = ?, city = ? WHERE admin_id = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, admin.getAdminfirstname());
            pst.setString(2, admin.getAdminlastname());

            pst.setLong(3, admin.getAdminphoneno());
            pst.setString(4, admin.getAdminschoolname());
            pst.setString(5, admin.getAdmincity());
            pst.setInt(6,admin.getAdminid());

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


    /*Delete admin Account*/
    public boolean deleteAdminAccount(Admin admin) throws Exception {
        boolean check = false;
        try
        {
            Connection con = getConnection();
            String s1 = "DELETE FROM admin_signup WHERE admin_id = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, admin.getAdminid());
            int num = pst.executeUpdate();

            if (num!=0)
            {
                check = true;
            }

            pst.close();
            con.close();

        }catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return check;
    }


    /*Admin Change Password Request*/
    public boolean changePassword(Admin admin) throws Exception {
        boolean check = false;
        try
        {
            Connection connection = getConnection();
            String s1 = "select password from admin_signup where admin_id = ?";

            PreparedStatement pst = connection.prepareStatement(s1);
            pst.setInt(1, admin.getAdminid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                admin.setAdminpassword(rs.getString("password"));
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


    /*Update Password*/
    public boolean updatePassword(Admin admin, ChangePassword changePassword) throws Exception {
        boolean check = false;
        try
        {
           Connection con = getConnection();
           String s1 = "UPDATE admin_signup SET password = ? WHERE admin_id = ?";
           PreparedStatement pst = con.prepareStatement(s1);
           pst.setString(1, changePassword.getNewpassword());
           pst.setInt(2, admin.getAdminid());
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


    /*To return teacher ids of this admin for the Chart*/
    public ArrayList<Teacher> getTeacherIds(Admin admin) throws Exception
    {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try
        {
            Connection con = getConnection();
            String s1 = "select teacher_id from teacher_signup where admin_id = ? LIMIT ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, admin.getAdminid());
            pst.setInt(2, 10);
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                Teacher teacher = new Teacher();
                teacher.setTeacherid(rs.getInt("teacher_id"));

                teachers.add(teacher);
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
        return teachers;
    }



    /*GET AVERAGE SCORE OF FEEDBACKS OF STUDENTS FOR A PARTICULAR TEACHER*/
    public boolean getTeacherAvgScore(Teacher teacher, Feedback feedback) throws Exception
    {
        boolean check = false;
        try
        {
            Connection con = getConnection();
            String s1 = "select AVG(performance), AVG(behaviour), AVG(improvement) from feedback where teacher_id = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, teacher.getTeacherid());

            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                feedback.setPerformance(rs.getInt(1));
                feedback.setBehaviour(rs.getInt(2));
                feedback.setImprovement(rs.getInt(3));
                feedback.setFeedbackteacherId(teacher.getTeacherid());

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


    /*Admin Reset Password*/
    public boolean adminPasswordReset(Admin admin) throws Exception
    {
        boolean check = false;
        try
        {
            Connection con = getConnection();
            String s1 = "select admin_firstname, password from admin_signup where admin_emailid = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, admin.getAdminemailid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                admin.setAdminfirstname(rs.getString("admin_firstname"));
                admin.setAdminpassword(rs.getString("password"));
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
