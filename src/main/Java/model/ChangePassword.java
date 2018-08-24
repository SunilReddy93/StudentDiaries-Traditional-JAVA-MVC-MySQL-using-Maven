package model;

public class ChangePassword {

    private String currentpassword;
    private String newpassword;
    private String verifypassword;

    public String getCurrentpassword() {
        return currentpassword;
    }

    public void setCurrentpassword(String currentpassword) {
        this.currentpassword = currentpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getVerifypassword() {
        return verifypassword;
    }

    public void setVerifypassword(String verifypassword) {
        this.verifypassword = verifypassword;
    }

    @Override
    public String toString() {
        return "ChangePassword{" +
                "currentpassword='" + currentpassword + '\'' +
                ", newpassword='" + newpassword + '\'' +
                ", verifypassword='" + verifypassword + '\'' +
                '}';
    }
}
