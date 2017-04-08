package nav.library.new23db.model;

import java.io.Serializable;

/**
 * Created by abhin on 4/4/2017.
 */

public class Employee implements Serializable{
    private String employeeID;
    private String managerID;
    private String dateOfJoining;
    private String dateOfBirth;
    private double salary;
    private String employeeName;
    private String gender;
    private String contactNo;
    private Address address;


    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {

        this.employeeName = employeeName;
    }

    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {

        this.department = department;
    }

    public Employee() {
    }

    public String getEmployeeID() {

        return employeeID;
    }

    public String getManagerID() {
        return managerID;
    }



    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setEmployeeID(String employeeID) {

        this.employeeID = employeeID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
