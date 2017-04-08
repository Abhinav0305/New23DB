package nav.library.new23db.constant;

/**
 * Created by abhin on 4/4/2017.
 */

public abstract class SQLConstant {

    public static  final String getUser="select last_login_date,s.emp_id,fname||' '||lname from employee e,system s where e.emp_id=s.emp_id and  s.EMAIL_ID=? AND s.PASSWORD=?";

    public static final String isManager="select count(1) from employee where manager_id=?";

    public static final String salarySlip="select e.emp_id,d.dname,d.dept_id,e.dob,e.doj,e.manager_id,e.fname||' '||e.lname,e.salary,e.gender from employee e ,department d where e.dept_id=d.dept_id and e.emp_id=?";

    public static final String user_profile="select fname,lname,dob,zipcode,city,state,address,gender,contact_no from employee where emp_id=?";

    public static final String employee="EMPLOYEE";

    public static final String leave="LEAVE";

    public static final String leave_details="LEAVE_DE";

    public static final String leave_entitled="select sick_leave_taken,paid_leave_taken from leave_de where emp_id=?";

    public static final String manager_approval="select e.manager_id,e.emp_id,l.leave_id,l.from_date,l.end_date,l.date_applied,leave_type,l.employee_reasons,l.noofdays,e.fname||' '||e.lname from employee e ,leave l where e.emp_id=l.emp_id and e.manager_id=? and approve is null";



}
