package model;

public class Student {

    private int studentid;
    private String studentfirstname;
    private String studentlastname;
    private int studentrollno;
    private String studentfathername;
    private String studentmothername;
    private String studentemailid;
    private String parentemailid;
    private long studentphoneno;
    private long parentphoneno;
    private String studentpassword;
    private String studentcoursename;
    private int studentteacherid;
    private int studentyear;
    private String schoolname;


    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getStudentfirstname() {
        return studentfirstname;
    }

    public void setStudentfirstname(String studentfirstname) {
        this.studentfirstname = studentfirstname;
    }

    public String getStudentlastname() {
        return studentlastname;
    }

    public void setStudentlastname(String studentlastname) {
        this.studentlastname = studentlastname;
    }

    public int getStudentrollno() {
        return studentrollno;
    }

    public void setStudentrollno(int studentrollno) {
        this.studentrollno = studentrollno;
    }

    public String getStudentfathername() {
        return studentfathername;
    }

    public void setStudentfathername(String studentfathername) {
        this.studentfathername = studentfathername;
    }

    public String getStudentmothername() {
        return studentmothername;
    }

    public void setStudentmothername(String studentmothername) {
        this.studentmothername = studentmothername;
    }

    public String getStudentemailid() {
        return studentemailid;
    }

    public void setStudentemailid(String studentemailid) {
        this.studentemailid = studentemailid;
    }

    public String getParentemailid() {
        return parentemailid;
    }

    public void setParentemailid(String parentemailid) {
        this.parentemailid = parentemailid;
    }

    public long getStudentphoneno() {
        return studentphoneno;
    }

    public void setStudentphoneno(long studentphoneno) {
        this.studentphoneno = studentphoneno;
    }

    public long getParentphoneno() {
        return parentphoneno;
    }

    public void setParentphoneno(long parentphoneno) {
        this.parentphoneno = parentphoneno;
    }

    public String getStudentpassword() {
        return studentpassword;
    }

    public void setStudentpassword(String studentpassword) {
        this.studentpassword = studentpassword;
    }

    public String getStudentcoursename() {
        return studentcoursename;
    }

    public void setStudentcoursename(String studentcoursename) {
        this.studentcoursename = studentcoursename;
    }

    public int getStudentteacherid() {
        return studentteacherid;
    }

    public void setStudentteacherid(int studentteacherid) {
        this.studentteacherid = studentteacherid;
    }

    public int getStudentyear() {
        return studentyear;
    }

    public void setStudentyear(int studentyear) {
        this.studentyear = studentyear;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentid=" + studentid +
                ", studentfirstname='" + studentfirstname + '\'' +
                ", studentlastname='" + studentlastname + '\'' +
                ", studentrollno=" + studentrollno +
                ", studentfathername='" + studentfathername + '\'' +
                ", studentmothername='" + studentmothername + '\'' +
                ", studentemailid='" + studentemailid + '\'' +
                ", parentemailid='" + parentemailid + '\'' +
                ", studentphoneno=" + studentphoneno +
                ", parentphoneno=" + parentphoneno +
                ", studentpassword='" + studentpassword + '\'' +
                ", studentcoursename='" + studentcoursename + '\'' +
                ", studentteacherid=" + studentteacherid +
                ", studentyear=" + studentyear +
                ", schoolname='" + schoolname + '\'' +
                '}';
    }
}
