package nav.library.new23db;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nav.library.new23db.database.DatabaseAdapter;
import nav.library.new23db.database.DatabaseHelper;
import nav.library.new23db.model.User;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText usern,pass;
    private Button sigin;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);
        usern=(EditText) findViewById(R.id.email);
        pass=(EditText) findViewById(R.id.etpass);
        sigin=(Button) findViewById(R.id.btnLogin);
        try{
            DatabaseHelper.getInstance(getApplicationContext()).prepareDatabase();
        }catch (Exception e){
            Log.d("Exception",e.getMessage());
        }
        sigin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        DatabaseAdapter dbAdapter=new DatabaseAdapter();
        User user=new User();
        intent=new Intent(this,WelcomeActivity.class);
        if(validate()) {
            if(dbAdapter.getUser(usern.getText().toString(),pass.getText().toString(),user,getApplicationContext())){
                if(user!=null){
                    if(dbAdapter.isManager(user.getEmployeeID(),getApplicationContext())){
                        intent.putExtra("manager_id",user.getEmployeeID());
                        intent.putExtra("is_manager",true);
                    }else{
                        intent.putExtra("is_manager",false);
                    }
                    usern.setText("");
                    pass.setText("");
                    intent.putExtra("employee_id",user.getEmployeeID());
                    intent.putExtra("user",user);
                    this.startActivity(intent);
                }
            }
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
    public boolean validate() {
        boolean valid = true;

        String email = usern.getText().toString();
        String password = pass.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            usern.setError("enter a valid email address");
            valid = false;
        } else {
            usern.setError(null);
        }

        if (password.isEmpty() || password.length() < 0 ) {
            pass.setError("Enter correct password");
            valid = false;
        } else {
            pass.setError(null);
        }

        return valid;
    }
}