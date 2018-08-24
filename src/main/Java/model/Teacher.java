package model;

public class Teacher {

    private int teacherid;
    private String teacherfirstname;
    private String teacherlastname;
    private long teacherphoneno;
    private String teachercoursename;
    private String teacheremailid;
    private int teacheradminid;
    private String teacherpassword;
    private String teacherschool;


    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeacherfirstname() {
        return teacherfirstname;
    }

    public void setTeacherfirstname(String teacherfirstname) {
        this.teacherfirstname = teacherfirstname;
    }

    public String getTeacherlastname() {
        return teacherlastname;
    }

    public void setTeacherlastname(String teacherlastname) {
        this.teacherlastname = teacherlastname;
    }

    public long getTeacherphoneno() {
        return teacherphoneno;
    }

    public void setTeacherphoneno(long teacherphoneno) {
        this.teacherphoneno = teacherphoneno;
    }

    public String getTeachercoursename() {
        return teachercoursename;
    }

    public void setTeachercoursename(String teachercoursename) {
        this.teachercoursename = teachercoursename;
    }

    public String getTeacheremailid() {
        return teacheremailid;
    }

    public void setTeacheremailid(String teacheremailid) {
        this.teacheremailid = teacheremailid;
    }

    public int getTeacheradminid() {
        return teacheradminid;
    }

    public void setTeacheradminid(int teacheradminid) {
        this.teacheradminid = teacheradminid;
    }

    public String getTeacherpassword() {
        return teacherpassword;
    }

    public void setTeacherpassword(String teacherpassword) {
        this.teacherpassword = teacherpassword;
    }

    public String getTeacherschool() {
        return teacherschool;
    }

    public void setTeacherschool(String teacherschool) {
        this.teacherschool = teacherschool;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "teacherid=" + teacherid +
                ", teacherfirstname='" + teacherfirstname + '\'' +
                ", teacherlastname='" + teacherlastname + '\'' +
                ", teacherphoneno=" + teacherphoneno +
                ", teachercoursename='" + teachercoursename + '\'' +
                ", teacheremailid='" + teacheremailid + '\'' +
                ", teacheradminid=" + teacheradminid +
                ", teacherpassword='" + teacherpassword + '\'' +
                ", teacherschool='" + teacherschool + '\'' +
                '}';
    }
}
