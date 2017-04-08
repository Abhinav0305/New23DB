package nav.library.new23db;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import nav.library.new23db.database.DatabaseAdapter;
import nav.library.new23db.model.Employee;
import nav.library.new23db.util.PdfClass;

/**
 * Created by abhin on 4/6/2017.
 */

public class SalaryActivity extends AppCompatActivity {


    private Button viewSalary,downloadSalary;
    private Intent intent;
    private TableLayout tableLayout,tableLayout2;
    private String employeeID;
    private Spinner mySpinner;
    private Employee employee;
    private DatabaseAdapter dbadapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_salary);
        toolbar=(Toolbar) findViewById(R.id.salarytoolbar);
        setSupportActionBar(toolbar);
        TextView title=new TextView(this);
        title.setText("Salary");
        title.setTextSize(30);
        title.setTypeface(null, Typeface.BOLD);
        mySpinner=(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.year));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        employeeID=getIntent().getExtras().getString("employee_id");
        Log.d("sppiner",mySpinner.getSelectedItem().toString());
        dbadapter= new DatabaseAdapter();
        employee = dbadapter.getSalarySlip(getApplication(), employeeID);
        viewSalary=(Button) findViewById(R.id.viewSalary);
        downloadSalary=(Button) findViewById(R.id.downSalary);
        viewSalary.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                intent=new Intent(SalaryActivity.this,SalaryDisplayActivity.class);
                intent.putExtra("employee_id",employeeID);
                intent.putExtra("employee",employee);
                intent.putExtra("spinner_value",mySpinner.getSelectedItem().toString());
                startActivity(intent);
            }
        });
        downloadSalary.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                PdfClass pdfClass = new PdfClass();
                //try {
                    /*fout=openFileOutput("temp.txt",MODE_PRIVATE);
                    fout.write(employee.getEmployeeName().getBytes());
                    Toast.makeText(SalaryActivity.this,"Check your Internal folder",Toast.LENGTH_LONG).show();
                    fout.close();*/
                boolean status = pdfClass.createPdfFile(employee, mySpinner.getSelectedItem().toString(), getApplicationContext());
                if (status) {
                    Toast.makeText(SalaryActivity.this, "Successfully downloaded ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SalaryActivity.this, "Could not download the file ", Toast.LENGTH_LONG).show();
                }
                /*} catch (FileNotFoundException e) {
                    Toast.makeText(SalaryActivity.this,"Download failed ",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(SalaryActivity.this,"Download failed ",Toast.LENGTH_LONG).show();
                }*/
            }
                });
        /*viewSalary.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DatabaseAdapter adapter=new DatabaseAdapter();
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v  = vi.inflate(R.layout.activity_salarydisplay,null);
                Employee employee=adapter.getSalarySlip(getApplication(),employeeID);
                tableLayout2=(TableLayout) findViewById(R.id.rootTable);
                TableRow tr1,tr2,tr3,tr4,tr5,tr6,tr7,tr8;
                tr1=(TableRow) v.findViewById(R.id.table1);
                tr2=(TableRow) v.findViewById(R.id.table2);
                tr3=(TableRow) v.findViewById(R.id.table3);
                tr4=(TableRow) v.findViewById(R.id.table4);
                tr5=(TableRow) v.findViewById(R.id.table5);
                tr6=(TableRow) v.findViewById(R.id.table6);
                tr7=(TableRow) v.findViewById(R.id.table7);
                tr7=(TableRow) v.findViewById(R.id.table8);
                TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
                Button backBtn=(Button) v.findViewById(R.id.back);
                tv1=(TextView) v.findViewById(R.id.EmpCodeV);
                tv1.setText(employee.getEmployeeID());
                tv2=(TextView) v.findViewById(R.id.DepartNameV);
                tv2.setText(employee.getDepartment().getDepartmentName());
                tv3=(TextView) v.findViewById(R.id.BirthIdV);
                tv3.setText(employee.getDateOfBirth());
                tv4=(TextView) v.findViewById(R.id.JoiningIdV);
                tv4.setText(employee.getDateOfJoining());
                tv5=(TextView) v.findViewById(R.id.EmpNameIdV);
                tv5.setText(employee.getEmployeeName());
                tv6=(TextView) v.findViewById(R.id.GenderV);
                tv6.setText(employee.getGender());
                tv7=(TextView) v.findViewById(R.id.SalaryV);
                tv7.setText(Double.toString(employee.getSalary()));
                backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View vie = getLayoutInflater().inflate(R.layout.activity_salary, null);
                    }
                });
            }
        });*/
        }
}