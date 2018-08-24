package model;

public class Admin {

    private int adminid;
    private String adminfirstname;
    private String adminlastname;
    private String adminemailid;
    private String adminschoolname;
    private String admincity;
    private long adminphoneno;
    private String adminpassword;

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getAdminfirstname() {
        return adminfirstname;
    }

    public void setAdminfirstname(String adminfirstname) {
        this.adminfirstname = adminfirstname;
    }

    public String getAdminlastname() {
        return adminlastname;
    }

    public void setAdminlastname(String adminlastname) {
        this.adminlastname = adminlastname;
    }

    public String getAdminemailid() {
        return adminemailid;
    }

    public void setAdminemailid(String adminemailid) {
        this.adminemailid = adminemailid;
    }

    public String getAdminschoolname() {
        return adminschoolname;
    }

    public void setAdminschoolname(String adminschoolname) {
        this.adminschoolname = adminschoolname;
    }

    public String getAdmincity() {
        return admincity;
    }

    public void setAdmincity(String admincity) {
        this.admincity = admincity;
    }

    public long getAdminphoneno() {
        return adminphoneno;
    }

    public void setAdminphoneno(long adminphoneno) {
        this.adminphoneno = adminphoneno;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminid=" + adminid +
                ", adminfirstname='" + adminfirstname + '\'' +
                ", adminlastname='" + adminlastname + '\'' +
                ", adminemailid='" + adminemailid + '\'' +
                ", adminschoolname='" + adminschoolname + '\'' +
                ", admincity='" + admincity + '\'' +
                ", adminphoneno=" + adminphoneno +
                ", adminpassword='" + adminpassword + '\'' +
                '}';
    }
}
