package nav.library.new23db;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import nav.library.new23db.database.DatabaseAdapter;
import nav.library.new23db.model.Leave;

/**
 * Created by abhin on 4/11/2017.
 */

public class ManagerApproveActivity extends AppCompatActivity {


    private TableLayout tableLayout;    // variable for tblLayout
    private Toolbar toolbar;
    private Button btnNewView; // variable declare for dynamic View,Delete and Edit button
    private TableRow newTR;                   // variable declare for dynamic table row
    private TextView newTxtID, newTxtName, newTxtLeaveID;           // variable declare for dynamic textview ID and Name
    private String managerID, employeeID;
    private DatabaseAdapter dbAdapter;
    private List<Leave> list;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_managerapprova);
        toolbar = (Toolbar) findViewById(R.id.Toolbar_Manager);
        setSupportActionBar(toolbar);
        TextView tv = new TextView(this);
        tv.setTextSize(30);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setText("Manage Leave");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);
        tableLayout = (TableLayout) findViewById(R.id.tblLayout); // table layout typecasting...
        tableLayout.setColumnStretchable(0, true); //first column
        tableLayout.setColumnStretchable(1, true); //second column
        tableLayout.setColumnStretchable(2, true); //third column
        tableLayout.setColumnStretchable(3, false); //third column
        dbAdapter = new DatabaseAdapter();
        managerID = getIntent().getExtras().getString("manager_id");
        list = dbAdapter.getLeavesOfEmployeeUnderManager(getApplicationContext(), managerID);
        dynamicViewElement();
    }

    public void dynamicViewElement() {
        newTR = new TableRow(ManagerApproveActivity.this);
        btnNewView = new Button(ManagerApproveActivity.this);
        newTxtID = new TextView(ManagerApproveActivity.this);
        newTxtName = new TextView(ManagerApproveActivity.this);
        newTxtLeaveID = new TextView(ManagerApproveActivity.this);
        /** start setting values of dyamic View Elements **/


        btnNewView.setText("View");
        /** end setting values of dyamic View Elements **/

        /** start appending dynamic textviews and buttons in tablerow **/
        newTR.addView(newTxtID);
        newTR.addView(newTxtName);
        newTR.addView(btnNewView);
        newTR.addView(newTxtLeaveID);
        /** start appending dynamic textviews and buttons in tablerow **/
        if (list != null) {
            for (final Leave leave : list) {
                TableRow tmp = new TableRow(ManagerApproveActivity.this);
                TextView tvID = new TextView(ManagerApproveActivity.this);
                tvID.setText(leave.getEmployeeID());
                tvID.setTextSize(20);
                TextView tvName = new TextView(ManagerApproveActivity.this);
                tvName.setText(leave.getEmployee().getEmployeeName());
                tvName.setTextSize(20);
                final TextView tvLeaveID = new TextView(ManagerApproveActivity.this);
                tvLeaveID.setText(Long.toString(leave.getLeaveID()));
                tvLeaveID.setTextSize(20);
                final Button rowView = new Button(ManagerApproveActivity.this);
                rowView.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        Intent i=new Intent(ManagerApproveActivity.this,LeaveActivity.class);
                        i.putExtra("leave",leave);
                        startActivity(i);
                    }
                });
                rowView.setText("view");
                tmp.addView(tvID);
                tmp.addView(tvName);
                tmp.addView(tvLeaveID);
                tmp.addView(rowView);
                tableLayout.addView(tmp);
            }
        }
    }

}
