package model;

import java.sql.Date;

public class Feedback {

    private String feedbackdate;
    private java.sql.Date SQLDatefeedbakcdate;
    private String attendance;
    private int performance;
    private int behaviour;
    private int improvement;
    private String remark;
    private int feedbackteacherId;

    public int getFeedbackteacherId() {
        return feedbackteacherId;
    }

    public void setFeedbackteacherId(int feedbackteacherId) {
        this.feedbackteacherId = feedbackteacherId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getSQLDatefeedbakcdate() {
        return SQLDatefeedbakcdate;
    }

    public void setSQLDatefeedbakcdate(Date SQLDatefeedbakcdate) {
        this.SQLDatefeedbakcdate = SQLDatefeedbakcdate;
    }


    public String getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(String feedbackdate) {
        this.feedbackdate = feedbackdate;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public int getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(int behaviour) {
        this.behaviour = behaviour;
    }

    public int getImprovement() {
        return improvement;
    }

    public void setImprovement(int improvement) {
        this.improvement = improvement;
    }


    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackdate=" + feedbackdate +
                ", attendance='" + attendance + '\'' +
                ", performance=" + performance +
                ", behaviour=" + behaviour +
                ", improvement=" + improvement +
                '}';
    }
}
