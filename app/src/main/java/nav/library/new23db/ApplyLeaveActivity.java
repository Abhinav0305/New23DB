package nav.library.new23db;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import nav.library.new23db.database.DatabaseAdapter;
import nav.library.new23db.model.Leave;

/**
 * Created by abhin on 4/9/2017.
 */

public class ApplyLeaveActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText to_date,from_date,ed_comments,leave_no;
    private Toolbar toolbar;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private RadioGroup grp;
    private RadioButton radioSick,radioPaid;
    private SimpleDateFormat dateFormatter;
    private Button submitBtn;
    private String employeeID;
    private String leavetype;
    private DatabaseAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_applyleave);
        toolbar = (Toolbar) findViewById(R.id.leaveToolbar);
        from_date = (EditText) findViewById(R.id.FromDate);
        to_date = (EditText) findViewById(R.id.ToDate);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        leave_no=(EditText) findViewById(R.id.input_noofleaves);
        ed_comments=(EditText) findViewById(R.id.emp_comment);
        grp=(RadioGroup) findViewById(R.id.radioGroup);
        radioPaid=(RadioButton) findViewById(R.id.radioPaid);
        radioSick=(RadioButton) findViewById(R.id.radioSick);
        setSupportActionBar(toolbar);
        TextView toolView = new TextView(this);
        toolView.setText("Leave ");
        toolView.setTextSize(30);
        toolView.setTypeface(null, Typeface.BOLD);
        dbAdapter=new DatabaseAdapter();
        employeeID=getIntent().getExtras().getString("employee_id");
        ed_comments=(EditText) findViewById(R.id.emp_comment);
        submitBtn=(Button) findViewById(R.id.btnSubmit);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(toolView);
        findViewsById();
        setDateTimeField();
        submitBtn.setOnClickListener(new ButtonListener());
    }
    private void findViewsById() {
        if(leave_no.isActivated()){
            from_date.setInputType(InputType.TYPE_NULL);
            from_date.requestFocus();

            to_date.setInputType(InputType.TYPE_NULL);
        }else{
            from_date.setActivated(Boolean.FALSE);
            to_date.setActivated(Boolean.FALSE);
        }

    }
    private void setDateTimeField() {
        from_date.setOnClickListener(this);
        to_date.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                from_date.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                to_date.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public void onClick(View view) {
        if(view == from_date) {
            Calendar cal=Calendar.getInstance();
            cal.add(Calendar.MONTH,1);
            long afterTwoMonthsinMilli=cal.getTimeInMillis();
            fromDatePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
            fromDatePickerDialog.getDatePicker().setMaxDate(afterTwoMonthsinMilli);
            fromDatePickerDialog.show();
        } else if(view == to_date) {
            Calendar cal=Calendar.getInstance();
            cal.set(fromDatePickerDialog.getDatePicker().getYear(),fromDatePickerDialog.getDatePicker().getMonth(),fromDatePickerDialog.getDatePicker().getDayOfMonth(),0,0);
            cal.add(Calendar.DAY_OF_YEAR,Integer.parseInt(leave_no.getText().toString())-1);
            long afterTwoMonthsinMilli=cal.getTimeInMillis();

            toDatePickerDialog.getDatePicker().setMinDate(afterTwoMonthsinMilli);
            toDatePickerDialog.getDatePicker().setMaxDate(afterTwoMonthsinMilli);
            toDatePickerDialog.show();
        }
    }
    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            if(radioSick!=null && radioPaid!=null){
                if(radioSick.isChecked()){
                    leavetype="Sick";
                }else{
                    leavetype="Paid";
                }
            }
            Date fromDate=new Date(fromDatePickerDialog.getDatePicker().getYear() - 1900, fromDatePickerDialog.getDatePicker().getMonth(), fromDatePickerDialog.getDatePicker().getDayOfMonth());
            Date toDate=new Date(toDatePickerDialog.getDatePicker().getYear() - 1900, toDatePickerDialog.getDatePicker().getMonth(), toDatePickerDialog.getDatePicker().getDayOfMonth());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
            String fDate=sdf.format(fromDate);
            String frmDate=fDate.substring(0,4)+"/"+fDate.substring(4,6)+"/"+fDate.substring(6);
            fDate=sdf.format(toDate);
            String tDate=fDate.substring(0,4)+"/"+fDate.substring(4,6)+"/"+fDate.substring(6);
            Date currentDate=new Date();
            String todayDate=sdf.format(currentDate);
            todayDate=todayDate.substring(0,4)+"/"+todayDate.substring(4,6)+"/"+todayDate.substring(6);
            Leave leave=new Leave();
            leave.setEmployeeID(employeeID);
            leave.setFromDATE(frmDate);
            leave.setToDATE(tDate);
            leave.setDateApplied(todayDate);
            leave.setEmployeeReasons(ed_comments.getText().toString());
            leave.setLeaveType(leavetype);
            leave.setNumberOfDays(Integer.parseInt(leave_no.getText().toString()));
            long value=dbAdapter.addLeaves(leave,getApplicationContext());
            if(value>0){
                Toast.makeText(ApplyLeaveActivity.this,"Succesfully leave application sent to manager",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(ApplyLeaveActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
            ed_comments.setText("");
            to_date.setText("");
            from_date.setText("");
            leave_no.setText("");
            submitBtn.setEnabled(false);
            radioPaid.setEnabled(false);
            radioSick.setEnabled(false);
        }
    }
}
