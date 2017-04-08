package nav.library.new23db.model;

import java.io.Serializable;

/**
 * Created by abhin on 4/4/2017.
 */

public class User implements Serializable {
    private String emailID;
    private String password;
    private String lastLogin;
    private String employeeID;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public User() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getPassword() {
        return password;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public String getEmployeeID() {
        return employeeID;
    }
}
