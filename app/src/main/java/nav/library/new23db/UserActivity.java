package nav.library.new23db;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nav.library.new23db.database.DatabaseAdapter;
import nav.library.new23db.model.Employee;

/**
 * Created by abhin on 4/5/2017.
 */

public class UserActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText inputF,inputL,inputAdd,inputZip,inputSt,inputCity;
    private Button editButton,submitButton;
    private TextInputLayout fLayout,lLayout,addLayout,zLayout,cLayout,sLayout;
    private Employee employee;
    private DatabaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_userdetails);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fLayout=(TextInputLayout) findViewById(R.id.input_layout_fname);
        lLayout=(TextInputLayout) findViewById(R.id.input_layout_phone);
        addLayout=(TextInputLayout) findViewById(R.id.input_layout_address);
        zLayout=(TextInputLayout) findViewById(R.id.input_layout_zip);
        cLayout=(TextInputLayout) findViewById(R.id.input_layout_city);
        sLayout=(TextInputLayout) findViewById(R.id.input_layout_state);

        inputF=(EditText) findViewById(R.id.input_firstname);
        inputL=(EditText) findViewById(R.id.input_contact);
        inputAdd=(EditText) findViewById(R.id.input_address);
        inputZip=(EditText) findViewById(R.id.input_zipcode);
        inputSt=(EditText) findViewById(R.id.input_state);
        inputCity=(EditText) findViewById(R.id.input_city);

        editButton=(Button) findViewById(R.id.btn_edit);
        submitButton=(Button) findViewById(R.id.btn_submit);

        adapter=new DatabaseAdapter();
        employee=adapter.getEmployeeDetails(getApplicationContext(),getIntent().getExtras().getString("employee_id"));
        inputF.setText(employee.getEmployeeName());
        inputL.setText(employee.getContactNo());
        inputAdd.setText(employee.getAddress().getAddress());
        inputZip.setText(Integer.toString(employee.getAddress().getZipCode()));
        inputCity.setText(employee.getAddress().getCity());
        inputSt.setText(employee.getAddress().getState());
        inputF.setEnabled(false);
        inputL.setEnabled(false);
        inputAdd.setEnabled(false);
        inputZip.setEnabled(false);
        inputSt.setEnabled(false);
        inputCity.setEnabled(false);
        editButton.setOnClickListener(new EditListener());
        submitButton.setOnClickListener(new SubmitListener());
    }
    private class EditListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            inputF.setEnabled(true);
            inputL.setEnabled(true);
            inputAdd.setEnabled(true);
            inputZip.setEnabled(true);
            inputSt.setEnabled(true);
            inputCity.setEnabled(true);
            submitButton.setEnabled(true);
        }
    }
    private class SubmitListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            if(validate()){
                if(employee!=null){
                    employee.getAddress().setCity(inputCity.getText().toString());
                    employee.getAddress().setAddress(inputAdd.getText().toString());
                    employee.getAddress().setState(inputSt.getText().toString());
                    employee.getAddress().setZipCode(Integer.parseInt(inputZip.getText().toString()));
                    employee.setContactNo(inputL.getText().toString());
                    if(adapter.updateEmployee(getApplication(),employee)){
                        Toast.makeText(UserActivity.this, "Updated details succesfully ", Toast.LENGTH_LONG).show();
                    }
                }
            }else{
                Toast.makeText(UserActivity.this, "Please enter all the details ", Toast.LENGTH_LONG).show();
            }
        }
        public boolean validate(){
            if(inputL!=null && inputL.getText().length()>0 && inputAdd!=null && inputAdd.getText().length()>0 && inputZip!=null && inputZip.getText().length()>0 && inputSt!=null && inputSt.getText().length()>0 && inputCity!=null && inputCity.getText().length()>0){
                return true;
            }
            return false;
        }
    }
}
