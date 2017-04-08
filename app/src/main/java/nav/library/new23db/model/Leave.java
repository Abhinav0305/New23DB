package nav.library.new23db.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by abhin on 4/11/2017.
 */

public class Leave implements Serializable {
    private long leaveID;
    private String employeeID;
    private String fromDATE;
    private String toDATE;
    private String approved;
    private String dateApplied;
    private String leaveType;
    private String employeeReasons;
    private String rejectionReasons;
    private Employee employee;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {

        return employee;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {

        this.numberOfDays = numberOfDays;
    }

    private int numberOfDays;
    public Leave() {
    }

    public long getLeaveID() {
        return leaveID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getFromDATE() {
        return fromDATE;
    }

    public String getToDATE() {
        return toDATE;
    }

    public String getApproved() {
        return approved;
    }

    public String getDateApplied() {
        return dateApplied;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public String getEmployeeReasons() {
        return employeeReasons;
    }

    public String getRejectionReasons() {
        return rejectionReasons;
    }

    public void setLeaveID(long leaveID) {
        this.leaveID = leaveID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setFromDATE(String fromDATE) {
        this.fromDATE = fromDATE;
    }

    public void setToDATE(String toDATE) {
        this.toDATE = toDATE;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public void setEmployeeReasons(String employeeReasons) {
        this.employeeReasons = employeeReasons;
    }

    public void setRejectionReasons(String rejectionReasons) {
        this.rejectionReasons = rejectionReasons;
    }
}
