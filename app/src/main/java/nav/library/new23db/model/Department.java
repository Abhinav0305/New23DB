package nav.library.new23db.model;

import java.io.Serializable;

/**
 * Created by abhin on 4/4/2017.
 */

public class Department implements Serializable{
    public Department() {
    }

    private String departmentID;
    private String departmentName;

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
