package shiksha.teambitcoders.com.shiksha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {
    private com.rengwuxian.materialedittext.MaterialEditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    FirebaseUser user;

    private ProgressBar progressBar;
    private Button btnLogin;
    DatabaseReference ref,ref1,ref2,ref3,ref4,ref5,ref6,ref7,ref8,ref9,ref10;
    public ArrayList<String> userEmailStudent = new ArrayList<>();
    public ArrayList<String> userEmailParent = new ArrayList<>();
    public ArrayList<String> userEmailTeacher = new ArrayList<>();
    String email;
    int statusForParent;

    String LoggedEmail;

    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_signin);
//        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(SigninActivity.this, MainActivity.class));
            finish();
        }


        inputEmail = (com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.editTextEmail);
        inputPassword = (com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        ref1 = FirebaseDatabase.getInstance().getReference();
        ref1.child("UsersDetails").child("Student").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            //Get map of users in datasnapshot
                            collectPhoneNumbers1((Map<String, Object>) dataSnapshot.getValue());
                    }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });


        ref2 = FirebaseDatabase.getInstance().getReference();
        ref2.child("UsersDetails").child("Parent").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        if (dataSnapshot.exists()) {
                            collectPhoneNumbers2((Map<String, Object>) dataSnapshot.getValue());
                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });

        ref3 = FirebaseDatabase.getInstance().getReference();
        ref3.child("UsersDetails").child("Teacher").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        if (dataSnapshot.exists()) {
                            collectPhoneNumbers3((Map<String, Object>) dataSnapshot.getValue());
                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });





        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                if(userEmailStudent.contains(email) || userEmailTeacher.contains(email) || userEmailParent.contains(email) )
                {
                    //authenticate user
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    progressBar.setVisibility(View.INVISIBLE);
                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        if (password.length() < 6) {
                                            inputPassword.setError("Minium password is 6");
                                        } else {
                                            Toast.makeText(SigninActivity.this, "Wrong Username and password", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        if(userEmailStudent.contains(email))
                                        {
                                                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }

                                            if (userEmailParent.contains(email)) {
                                                Intent intent1 = new Intent(SigninActivity.this, MainActivity.class);
                                                startActivity(intent1);
                                                finish();
                                            }

                                        if (userEmailTeacher.contains(email)) {
                                            Intent intent1 = new Intent(SigninActivity.this, ParentMenuActivity.class);
                                            startActivity(intent1);
                                            finish();
                                        }



                                        }


                                }
                            });

                }
                else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"Incorrect credientials",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    public void collectPhoneNumbers1(Map<String,Object> Faculty) {

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : Faculty.entrySet()) {

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            userEmailStudent.add((String) singleUser.get("email"));

        }


    }


    public void collectPhoneNumbers2(Map<String,Object> Faculty) {

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : Faculty.entrySet()) {

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            userEmailParent.add((String) singleUser.get("email"));

        }


    }



    public void collectPhoneNumbers3(Map<String,Object> Faculty) {

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : Faculty.entrySet()) {

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            userEmailTeacher.add((String) singleUser.get("email"));

        }


    }
public void signupp(View v)
{
    Intent i=new Intent(this,SignupActivity.class);
    startActivity(i);
}


}
