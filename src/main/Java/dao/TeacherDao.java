package dao;

import model.Admin;
import model.ChangePassword;
import model.Teacher;

import java.sql.*;
import java.util.ArrayList;


public class TeacherDao {

    AdminDao adminDao = new AdminDao();


    public int insertAll(Teacher teacher) throws Exception {
        int status = 0;

        try
        {
            String s1 = "insert into teacher_signup (teacher_firstname, teacher_lastname, teacher_emailid, password, phoneno, admin_id, school_name, course_name )values(?,?,?,?,?,?,?,?)";

            Connection con = adminDao.getConnection();

            PreparedStatement pst = con.prepareStatement(s1);

            pst.setString(1, teacher.getTeacherfirstname());
            pst.setString(2, teacher.getTeacherlastname());
            pst.setString(3, teacher.getTeacheremailid());
            pst.setString(4, teacher.getTeacherpassword());
            pst.setLong(5, teacher.getTeacherphoneno());
            pst.setInt(6, teacher.getTeacheradminid());
            pst.setString(7, teacher.getTeacherschool());
            pst.setString(8, teacher.getTeachercoursename());

            status = pst.executeUpdate();

            pst.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }

        return status;
    }


    /*get teacher id from database to create session*/
    public boolean fetchteacherid(Teacher teacher) throws Exception {
        boolean check = false;

        try
        {
            String s1 = "select teacher_id from teacher_signup where teacher_emailid = ?";

            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, teacher.getTeacheremailid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                teacher.setTeacherid(rs.getInt("teacher_id"));
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
    public boolean TeacherEmailDbVerification(Teacher teacher) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select * from teacher_signup where teacher_emailid = ?";


            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, teacher.getTeacheremailid());
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


    /*Ajax DB Verification of Phone number*/
    public boolean TeacherPhoneDbVerification(Teacher teacher) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select * from teacher_signup where phoneno = ?";

            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setLong(1, teacher.getTeacherphoneno());
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


    /*Ajax to get all admin ids for teacher signup*/
    public ArrayList<Admin> AjaxTeacherAdminId() throws Exception {

        ArrayList<Admin> adminids = new ArrayList<>();

        try
        {
            String s1 = "select admin_id from admin_signup";
            Connection con = adminDao.getConnection();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(s1);



            while (rs.next())
            {
                Admin admin = new Admin();
                admin.setAdminid(rs.getInt("admin_id"));

                adminids.add(admin);
            }

            st.close();
            rs.close();
            con.close();


        }catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        return adminids;
    }


    /*Ajax get school info corresponding to admin for teacher signup*/
    public boolean AjaxTeacherAdminSchoolSignup(Admin admin) throws Exception {
        boolean check = false;
        try
        {
            String s1 = "select school_name from admin_signup where admin_id = ?";

            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, admin.getAdminid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                admin.setAdminschoolname(rs.getString("school_name"));
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



    /*For Teacher Login*/
    public boolean teacherLoginVerification(Teacher teacher) throws Exception
    {
        boolean check = false;

        try
        {
            String s1 = "select teacher_id, teacher_firstname, teacher_lastname from teacher_signup where teacher_emailid = ? AND password = ?";

            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, teacher.getTeacheremailid());
            pst.setString(2, teacher.getTeacherpassword());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                teacher.setTeacherid(rs.getInt("teacher_id"));
                teacher.setTeacherfirstname(rs.getString("teacher_firstname"));
                teacher.setTeacherlastname(rs.getString("teacher_lastname"));
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


    /*Count No. of Students for counting card on Dashboard*/
    public int countStudents(Teacher teacher) throws Exception
    {
        int num = 0;
        try
        {
            String s1 = "SELECT student_id FROM student_signup where teacher_id = ?";
            Connection con = adminDao.getConnection();
            PreparedStatement pst = con.prepareStatement(s1);

            pst.setInt(1, teacher.getTeacherid());
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


    /*DELETE TEACHERs VIA ADMIN ACCOUNT*/
    public boolean deleteTeacher(ArrayList<Teacher> teacherids) throws Exception
    {
        boolean check = false;
        try
        {
            String s1 = "DELETE FROM teacher_signup WHERE teacher_id = ?";
            Connection con = adminDao.getConnection();

            for (int i = 0; i < teacherids.size(); i++)
            {
                PreparedStatement pst = con.prepareStatement(s1);
                pst.setInt(1, teacherids.get(i).getTeacherid());
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



    /*Get all teacher info for admin teacher profile*/
    public boolean getAllTeacherInfo(Teacher teacher) throws Exception
    {
        boolean check = false;
        try
        {

            Connection con = adminDao.getConnection();
            String s1 = "select * from teacher_signup where teacher_id = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, teacher.getTeacherid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                teacher.setTeacherid(rs.getInt("teacher_id"));
                teacher.setTeacherfirstname(rs.getString("teacher_firstname"));
                teacher.setTeacherlastname(rs.getString("teacher_lastname"));
                teacher.setTeacheremailid(rs.getString("teacher_emailid"));
                teacher.setTeacherphoneno(rs.getLong("phoneno"));
                teacher.setTeacheradminid(rs.getInt("admin_id"));
                teacher.setTeacherschool(rs.getString("school_name"));
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



    /*Delete Teacher Account*/
    public boolean deleteTeacherAccount(Teacher teacher) throws Exception {
        boolean check = false;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "DELETE FROM teacher_signup WHERE teacher_id = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, teacher.getTeacherid());
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


    /*Update Teacher Account*/
    public boolean updateTeacherAccount(Teacher teacher) throws Exception {
        boolean check = false;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "update teacher_signup SET teacher_firstname = ?, teacher_lastname = ?, phoneno = ?, course_name = ? WHERE teacher_id = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, teacher.getTeacherfirstname());
            pst.setString(2, teacher.getTeacherlastname());
            pst.setLong(3, teacher.getTeacherphoneno());
            pst.setString(4, teacher.getTeachercoursename());
            pst.setInt(5, teacher.getTeacherid());
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


    /*Teacher Change Password Request*/
    public boolean changeTeacherPassword(Teacher teacher) throws Exception {
        boolean check = false;
        try {
            Connection connection = adminDao.getConnection();
            String s1 = "select password from teacher_signup where teacher_id = ?";

            PreparedStatement pst = connection.prepareStatement(s1);
            pst.setInt(1, teacher.getTeacherid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                teacher.setTeacherpassword(rs.getString("password"));
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



    /*Update Teacher Password*/
    public boolean updateTeacherPassword(Teacher teacher, ChangePassword changePassword) throws Exception
    {
        boolean check = false;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "UPDATE teacher_signup SET password = ? WHERE teacher_id = ?";
            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, changePassword.getNewpassword());
            pst.setInt(2, teacher.getTeacherid());
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



    /*Select all to find out the total no. of feedbacks for this particular teacher*/
    public int getTotalTeacherFeedbacks(Teacher teacher) throws Exception
    {
        int num = 0;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "select * from feedback where teacher_id=?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, teacher.getTeacherid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                num++;
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
        return num;
    }


    /*Return no. of Good Students for Pie*/
    public int getGoodTeacherFeedbacks(Teacher teacher) throws Exception
    {
        int num = 0;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "select * from feedback where (teacher_id = ? AND performance >= ? AND performance < ? AND behaviour >= ? AND behaviour < ? AND improvement >= ? AND improvement < ?)";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, teacher.getTeacherid());
            pst.setInt(2, 7);
            pst.setInt(3, 11);
            pst.setInt(4, 7);
            pst.setInt(5, 11);
            pst.setInt(6, 7);
            pst.setInt(7, 11);
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                num++;
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
        return num;
    }



    /*Get total no. of average students*/
    public int getAverageTeacherFeedbacks(Teacher teacher) throws Exception
    {
        int num = 0;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "select * from feedback where (teacher_id = ? AND performance >= ? AND performance < ? AND behaviour >= ? AND behaviour < ? AND IMPROVEMENT >= ? AND improvement < ?) OR (teacher_id = ? AND performance >= ? AND behaviour >= ? AND improvement >= ? AND improvement < ?) OR (teacher_id = ? AND performance >= ? AND performance < ? AND behaviour >= ? AND improvement >= ?) OR (teacher_id = ? AND performance >= ? AND behaviour >= ? AND behaviour < ? AND improvement >= ?)";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setInt(1, teacher.getTeacherid());
            pst.setInt(2, 4);
            pst.setInt(3, 7);
            pst.setInt(4, 4);
            pst.setInt(5, 7);
            pst.setInt(6, 4);
            pst.setInt(7, 7);
            pst.setInt(8, teacher.getTeacherid());
            pst.setInt(9, 4);
            pst.setInt(10, 4);
            pst.setInt(11, 4);
            pst.setInt(12, 8);
            pst.setInt(13, teacher.getTeacherid());
            pst.setInt(14, 4);
            pst.setInt(15, 8);
            pst.setInt(16, 4);
            pst.setInt(17, 4);
            pst.setInt(18, teacher.getTeacherid());
            pst.setInt(19, 4);
            pst.setInt(20, 4);
            pst.setInt(21, 8);
            pst.setInt(22, 4);


            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                num++;
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
        return num;
    }


    /*Teacher Reset Password*/
    public boolean teacherPasswordReset(Teacher teacher) throws Exception
    {
        boolean check = false;
        try
        {
            Connection con = adminDao.getConnection();
            String s1 = "select teacher_firstname, password from teacher_signup where teacher_emailid = ?";

            PreparedStatement pst = con.prepareStatement(s1);
            pst.setString(1, teacher.getTeacheremailid());
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                teacher.setTeacherfirstname(rs.getString("teacher_firstname"));
                teacher.setTeacherpassword(rs.getString("password"));
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
