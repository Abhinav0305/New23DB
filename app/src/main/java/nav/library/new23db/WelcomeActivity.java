package nav.library.new23db;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import nav.library.new23db.database.DatabaseAdapter;
import nav.library.new23db.model.User;
import nav.library.new23db.view.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by abhin on 4/4/2017.
 */

public class WelcomeActivity extends AppCompatActivity {

    private Intent intent;
    private String employeeID;
    private boolean is_manager;
    private String managerID;
    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;
    private TextView lastLogin;
    private User user;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_welcome);
        TextView toolView=new TextView(this);
        toolView.setText("Home Page");
        toolView.setTextSize(30);
        toolView.setTypeface(null, Typeface.BOLD);
        toolbar=(Toolbar) findViewById(R.id.welcometoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(toolView);
        Bundle b=getIntent().getExtras();
        is_manager=b.getBoolean("is_manager");
        managerID=b.getString("manager_id");
        employeeID=b.getString("employee_id");
        user=(User) b.get("user");
        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new NavigationImplementation());
        expandableListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);
        lastLogin=(TextView) findViewById(R.id.textDEmployee);
        lastLogin.setText("Welcome "+user.getName());
        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);
        setItems();
        setListener();

    }

    public void setItems(){
        List<String> header=new ArrayList<String>();

        //Arraylist for childs
        List<String> child1 = new ArrayList<String>();
        List<String> child2 = new ArrayList<String>();
        List<String> child3 = new ArrayList<String>();

        // Hash map for both header and child
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

        //Adding headers to list
        header.add("Leaves");
        header.add("Bookings");
        header.add("Rooms");

        header=Arrays.asList(getResources().getStringArray(R.array.yelp));
        if(is_manager){
            child1=Arrays.asList(getResources().getStringArray(R.array.manager));
        }else{
            child1=Arrays.asList(getResources().getStringArray(R.array.nonmanager));
        }
        child2=Arrays.asList(getResources().getStringArray(R.array.Book));
        child3=Arrays.asList(getResources().getStringArray(R.array.Room));

        hashMap.put(header.get(0),child1);
        hashMap.put(header.get(1),child2);
        hashMap.put(header.get(2),child3);

        adapter = new ExpandableListAdapter(WelcomeActivity.this, header, hashMap);

        expandableListView.setAdapter(adapter);

    }
    void setListener() {

        // This listener will show toast on group click
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {

                Toast.makeText(WelcomeActivity.this,
                        "You clicked : " + adapter.getGroup(group_pos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // This listener will expand one group at one time
        // You can remove this listener for expanding all groups
        expandableListView
                .setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    // Default position
                    int previousGroup = -1;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != previousGroup)

                            // Collapse the expanded group
                            expandableListView.collapseGroup(previousGroup);
                        previousGroup = groupPosition;
                    }

                });

        // This listener will show toast on child click
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {

                if(groupPos==0){
                    switch (childPos){
                        case 0:
                            intent=new Intent(WelcomeActivity.this,ApplyLeaveActivity.class);
                            intent.putExtra("employee_id",employeeID);
                            startActivity(intent);
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            intent=new Intent(WelcomeActivity.this,ManagerApproveActivity.class);
                            intent.putExtra("employee_id",employeeID);
                            intent.putExtra("manager_id",managerID);
                            startActivity(intent);
                            break;
                    }
                }else if(groupPos==1){
                    switch (childPos){
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                    }
                }else if(groupPos==2){
                    switch (childPos){
                        case 0:
                            break;
                        case 1:
                            break;
                    }
                }

                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Do you want to exit");
            builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int which){finish();
                }
            });
            builder.setNegativeButton("No",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int which){
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }

    private class NavigationImplementation implements BottomNavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_salary:
                    intent=new Intent(WelcomeActivity.this,SalaryActivity.class);
                    intent.putExtra("employee_id",employeeID);
                    startActivity(intent);
                    break;
                case R.id.action_profile:
                    intent=new Intent(WelcomeActivity.this,UserActivity.class);
                    intent.putExtra("employee_id",employeeID);
                    startActivity(intent);
                    break;
                case R.id.action_logout:
                    intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    break;
            }
            return false;
        }
    }
}
