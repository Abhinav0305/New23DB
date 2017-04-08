package nav.library.new23db.model;

import java.io.Serializable;

/**
 * Created by abhin on 4/11/2017.
 */

public class LeaveDetails implements Serializable {

    private String employeeID;
    private int sickLeaveEntitled;
    private int paidLeaveEntitled;
    private int sickLeaveTaken;
    private int paidLeaveTaken;

    public LeaveDetails() {
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public int getSickLeaveEntitled() {
        return sickLeaveEntitled;
    }

    public int getPaidLeaveEntitled() {
        return paidLeaveEntitled;
    }

    public int getSickLeaveTaken() {
        return sickLeaveTaken;
    }

    public int getPaidLeaveTaken() {
        return paidLeaveTaken;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setSickLeaveEntitled(int sickLeaveEntitled) {
        this.sickLeaveEntitled = sickLeaveEntitled;
    }

    public void setPaidLeaveEntitled(int paidLeaveEntitled) {
        this.paidLeaveEntitled = paidLeaveEntitled;
    }

    public void setSickLeaveTaken(int sickLeaveTaken) {
        this.sickLeaveTaken = sickLeaveTaken;
    }

    public void setPaidLeaveTaken(int paidLeaveTaken) {
        this.paidLeaveTaken = paidLeaveTaken;
    }
}
