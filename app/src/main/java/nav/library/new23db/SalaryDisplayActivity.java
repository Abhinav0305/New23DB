package nav.library.new23db;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import nav.library.new23db.database.DatabaseAdapter;
import nav.library.new23db.model.Employee;

/**
 * Created by abhin on 4/8/2017.
 */

public class SalaryDisplayActivity extends AppCompatActivity {

    private Context context;
    private TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_salarydisplay);

        context=this;
        Object emp = getIntent().getSerializableExtra("employee");
        Employee employee=(Employee) emp;

        tableLayout=(TableLayout) findViewById(R.id.rootTable);
        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        TextView header=new TextView(this);
        header.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        header.setGravity(Gravity.CENTER);
        header.setTextSize(16);
        header.setGravity(Gravity.CENTER);
        header.setText(" PaySlip for " +getIntent().getStringExtra("spinner_value"));
        rowHeader.addView(header);

        TableRow row1=new TableRow(context);
        row1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv1 = new TextView(this);
        tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextSize(16);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setText("Emp Code");
        tv1.setTypeface(boldTypeface);

        TextView tv2 = new TextView(this);
        tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv2.setGravity(Gravity.CENTER);
        tv2.setTextSize(16);
        tv2.setPadding(5, 5, 5, 5);
        tv2.setText(employee.getEmployeeID());
        tv2.setTypeface(boldTypeface);
        row1.addView(tv1);
        row1.addView(tv2);

        TableRow row2=new TableRow(context);
        row2.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv3 = new TextView(this);
        tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv3.setGravity(Gravity.CENTER);
        tv3.setTextSize(16);
        tv3.setPadding(5, 5, 5, 5);
        tv3.setText("Department");
        tv3.setTypeface(boldTypeface);
        TextView tv4 = new TextView(this);
        tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv4.setGravity(Gravity.CENTER);
        tv4.setTextSize(16);
        tv4.setPadding(5, 5, 5, 5);
        tv4.setText(employee.getDepartment().getDepartmentName());
        tv4.setTypeface(boldTypeface);
        row2.addView(tv3);
        row2.addView(tv4);

        TableRow row3=new TableRow(context);
        row3.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv5 = new TextView(this);
        tv5.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv5.setGravity(Gravity.CENTER);
        tv5.setTextSize(16);
        tv5.setPadding(5, 5, 5, 5);
        tv5.setText("Date of Birth");
        tv5.setTypeface(boldTypeface);
        TextView tv6 = new TextView(this);
        tv6.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv6.setGravity(Gravity.CENTER);
        tv6.setTextSize(16);
        tv6.setPadding(5, 5, 5, 5);
        tv6.setText(employee.getDateOfBirth());
        tv6.setTypeface(boldTypeface);
        row3.addView(tv5);
        row3.addView(tv6);

        TableRow row4=new TableRow(context);
        row4.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv7 = new TextView(this);
        tv7.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv7.setGravity(Gravity.CENTER);
        tv7.setTextSize(16);
        tv7.setPadding(5, 5, 5, 5);
        tv7.setText("Date of Joining");
        tv7.setTypeface(boldTypeface);
        TextView tv8 = new TextView(this);
        tv8.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv8.setGravity(Gravity.CENTER);
        tv8.setTextSize(16);
        tv8.setPadding(5, 5, 5, 5);
        tv8.setText(employee.getDateOfJoining());
        tv8.setTypeface(boldTypeface);
        row4.addView(tv7);
        row4.addView(tv8);


        TableRow row5=new TableRow(context);
        row5.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv9 = new TextView(this);
        tv9.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv9.setGravity(Gravity.CENTER);
        tv9.setTextSize(16);
        tv9.setPadding(5, 5, 5, 5);
        tv9.setText("Emp Name");
        tv9.setTypeface(boldTypeface);
        TextView tv10 = new TextView(this);
        tv10.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv10.setGravity(Gravity.CENTER);
        tv10.setTextSize(16);
        tv10.setPadding(5, 5, 5, 5);
        tv10.setText(employee.getEmployeeName());
        tv10.setTypeface(boldTypeface);
        row5.addView(tv9);
        row5.addView(tv10);

        TableRow row6=new TableRow(context);
        row6.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv11 = new TextView(this);
        tv11.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv11.setGravity(Gravity.CENTER);
        tv11.setTextSize(16);
        tv11.setPadding(5, 5, 5, 5);
        tv11.setText("Gender");
        tv11.setTypeface(boldTypeface);
        TextView tv12 = new TextView(this);
        tv12.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv12.setGravity(Gravity.CENTER);
        tv12.setTextSize(16);
        tv12.setPadding(5, 5, 5, 5);
        tv12.setText(employee.getGender());
        tv12.setTypeface(boldTypeface);
        row6.addView(tv11);
        row6.addView(tv12);


        TableRow row7=new TableRow(context);
        row7.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv13 = new TextView(this);
        tv13.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv13.setGravity(Gravity.CENTER);
        tv13.setTextSize(16);
        tv13.setPadding(5, 5, 5, 5);
        tv13.setText("Salary");
        tv13.setTypeface(boldTypeface);
        TextView tv14 = new TextView(this);
        tv14.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv14.setGravity(Gravity.CENTER);
        tv14.setTextSize(16);
        tv14.setPadding(5, 5, 5, 5);
        tv14.setText(Double.toString(employee.getSalary()));
        tv14.setTypeface(boldTypeface);
        row7.addView(tv13);
        row7.addView(tv14);


        tableLayout.addView(rowHeader);
        tableLayout.addView(row1);
        tableLayout.addView(row2);
        tableLayout.addView(row3);
        tableLayout.addView(row4);
        tableLayout.addView(row5);
        tableLayout.addView(row6);
        tableLayout.addView(row7);
    }
}
