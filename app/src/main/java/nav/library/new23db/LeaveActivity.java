package nav.library.new23db;

import android.content.ContentValues;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import nav.library.new23db.constant.SQLConstant;
import nav.library.new23db.database.DatabaseAdapter;
import nav.library.new23db.model.Leave;
import nav.library.new23db.model.LeaveDetails;

/**
 * Created by abhin on 4/13/2017.
 */

public class LeaveActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Leave leave;
    private RadioGroup rg;
    private RadioButton rb1,rb2;
    private TextView tv1;
    private EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    private Button approve,reject;
    private LeaveDetails leaveDetails;
    private DatabaseAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_leave);
        toolbar=(Toolbar) findViewById(R.id.Toolbar_Leave);
        setSupportActionBar(toolbar);
        TextView tv=new TextView(this);
        tv.setText("Employee Leave");
        tv.setTextSize(30);
        tv.setTypeface(null, Typeface.BOLD);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);
        leave=(Leave) getIntent().getExtras().get("leave");
        dbAdapter=new DatabaseAdapter();
        leaveDetails=dbAdapter.getLeaveDetails(getApplicationContext(),leave.getEmployee().getEmployeeID());
        rg=(RadioGroup) findViewById(R.id.rdGroup);
        rb1=(RadioButton) findViewById(R.id.sickRadio);
        rb2=(RadioButton) findViewById(R.id.paidRadio);
        ed1=(EditText) findViewById(R.id.editText1);
        ed2=(EditText) findViewById(R.id.noEdit);
        ed3=(EditText) findViewById(R.id.FROMdate);
        ed4=(EditText) findViewById(R.id.TOdate);
        ed5=(EditText) findViewById(R.id.leavesTaken);
        ed6=(EditText) findViewById(R.id.CommentsTextBox);
        ed7=(EditText) findViewById(R.id.CommentsTextBoxManager);
        tv1=(TextView) findViewById(R.id.PaidLeavesTakend);
        approve=(Button) findViewById(R.id.approvebtn);
        reject=(Button) findViewById(R.id.rejectbtn);
        display();
        approve.setOnClickListener(new ButtonListener());
        reject.setOnClickListener(new ButtonListener());
    }
    public void display(){
        if(leave!=null && leaveDetails!=null){
            if(leave.getLeaveType().equals("Sick")){
                rb1.setChecked(true);
                rb1.setTypeface(null,Typeface.BOLD);
                tv1.setText("Sick Leaves Taken");
                ed5.setText(Integer.toString(leaveDetails.getSickLeaveTaken()));
            }else{
                rb2.setChecked(true);
                rb2.setTypeface(null,Typeface.BOLD);
                ed5.setText(Integer.toString(leaveDetails.getPaidLeaveTaken()));
            }
            rb1.setEnabled(false);
            rb2.setEnabled(false);
            ed1.setText(leave.getEmployee().getEmployeeName());
            ed1.setTextSize(20);
            ed1.setTypeface(null, Typeface.BOLD);
            ed2.setText(Integer.toString(leave.getNumberOfDays()));
            ed2.setTextSize(20);
            ed2.setTypeface(null, Typeface.BOLD);
            ed3.setText(leave.getFromDATE());
            ed3.setTextSize(20);
            ed3.setTypeface(null, Typeface.BOLD);
            ed4.setText(leave.getToDATE());
            ed4.setGravity(Gravity.RIGHT);
            ed4.setTextSize(20);
            ed4.setTypeface(null, Typeface.BOLD);
            ed6.setText(leave.getEmployeeReasons());
            ed6.setTextSize(20);
            ed6.setTypeface(null, Typeface.BOLD);
        }
            ed1.setEnabled(false);
            ed2.setEnabled(false);
            ed3.setEnabled(false);
            ed4.setEnabled(false);
            ed5.setEnabled(false);
            ed6.setEnabled(false);
    }
    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            int daysUpdated=0;
            if(leaveDetails!=null && leave!=null){
                if(leave.getLeaveType().equals("Sick")){
                    daysUpdated=leaveDetails.getSickLeaveTaken()+Integer.parseInt(ed2.getText().toString());
                    leaveDetails.setSickLeaveTaken(daysUpdated);
                }else{
                    daysUpdated=leaveDetails.getPaidLeaveTaken()+Integer.parseInt(ed2.getText().toString());
                    leaveDetails.setPaidLeaveTaken(daysUpdated);
                }
                leave.setRejectionReasons(ed7.getText().toString());
            }
            switch (view.getId()){
                case R.id.approvebtn:
                    leave.setApproved("Y");
                    break;
                case R.id.rejectbtn:
                    leave.setApproved("N");
                    break;
            }
            ContentValues leaves=new ContentValues();
            leaves.put("MANAGER_REASON",leave.getRejectionReasons());
            leaves.put("APPROVE",leave.getApproved());
            ContentValues leavede=new ContentValues();
            leavede.put("EMP_ID",leaveDetails.getEmployeeID());
            leavede.put("SICK_LEAVE_TAKEN",leaveDetails.getSickLeaveTaken());
            leavede.put("PAID_LEAVE_TAKEN",leaveDetails.getPaidLeaveTaken());
            boolean status=dbAdapter.updateLeave(SQLConstant.leave,SQLConstant.leave_details,getApplicationContext(),leaves,leavede,leave.getLeaveID(),leaveDetails.getEmployeeID());
            if(status){
                Toast.makeText(LeaveActivity.this,"Success ",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(LeaveActivity.this,"Failed contact system admin ",Toast.LENGTH_LONG).show();
            }
        }
    }
}
