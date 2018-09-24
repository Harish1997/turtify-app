package rexzen.turtify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by harishananth on 10/06/17.
 */

public class login extends AppCompatActivity {
    Button login,proceed;
    EditText username,password;
    String uname,pass;
    TextView status;
    public static int st=0;
    private DatabaseReference root;
    HashMap<String,String> logins=new HashMap<>();
    SharedPreferences sharedpreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login=(Button)findViewById(R.id.loginb);
        proceed=(Button)findViewById(R.id.proceedb);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        status=(TextView)findViewById(R.id.status);

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("Choose your organization", "SSTCN", "Tree Foundation", "Sea Turtle Org", "Turtle Protection Squad","Greenpeace");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Group: " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        root = FirebaseDatabase.getInstance().getReference().child("Passwords");


        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                searchdb(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                searchdb(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,MainActivity.class);
                intent.putExtra("status","nil");
                st=0;
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uname = username.getText().toString();
                pass = password.getText().toString();


                status.setText("");
                uname=uname.toLowerCase();
                uname=uname.trim();

                pass=pass.toLowerCase();
                pass=pass.trim();
                if (logins.containsKey(uname)) {
                    if (logins.get(uname).equals(pass)) {
                        status.setText("Login successful");
                        st = 1;
                        SharedPreferences.Editor editor = getSharedPreferences("login result", MODE_PRIVATE).edit();
                        editor.putString("result","yes");
                        editor.putInt("st_value",1);
                        editor.apply();
                        Intent intent = new Intent(login.this, MainActivity.class);
                        intent.putExtra("status", "success");
                        startActivity(intent);

                    } else {
                        status.setText("Incorrect password");
                    }
                }
                else{
                    status.setText("Username doesn't exist");
                }



            }

        });

    }

    private void searchdb(DataSnapshot dataSnapshot)
    {
        Iterator i=dataSnapshot.getChildren().iterator();
        String uname,pass;
        while(i.hasNext())
        {
            uname= (String) ((DataSnapshot)i.next()).getValue();
            pass= (String) ((DataSnapshot)i.next()).getValue();
            logins.put(uname,pass);


        }

    }


}
