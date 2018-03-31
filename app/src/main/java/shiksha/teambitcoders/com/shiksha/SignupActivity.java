package shiksha.teambitcoders.com.shiksha;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private com.rengwuxian.materialedittext.MaterialEditText inputEmail, inputPassword,inputAge,inputName,inputPhoneno;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    public DatabaseReference databaseReference;
    String email,password,name;
    CheckBox checkBox;
    Context context=this;
    RadioGroup rg;
    String stdType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_signup);
//        getSupportActionBar().hide();



        auth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("UsersDetails");

        btnSignUp = (Button) findViewById(R.id.btnLogin);
        inputEmail = (com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.editTextEmail);
        inputPassword = (com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.editTextPassword);
        inputName = (com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.materialEditText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        stdType="Student";
        rg = (RadioGroup) findViewById(R.id.radioGroup1);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.radioAndroid:
                        // TODO Something
//                        edt_ill.setVisibility(View.INVISIBLE);
//                        edt_remark.setVisibility(View.INVISIBLE);
                        stdType="Student";
                        break;
                    case R.id.radioiPhone:
                        // TODO Something
//                        edt_ill.setVisibility(View.INVISIBLE);
//                        edt_remark.setVisibility(View.INVISIBLE);
                        stdType="Teacher";

                        break;
                    case R.id.radioWindows:
                        // TODO Something
//                        edt_ill.setVisibility(View.INVISIBLE);
//                        edt_remark.setVisibility(View.INVISIBLE);
                        stdType="Parent";

                        break;
//                    case R.id.s:
//                        // TODO Something
//                        edt_ill.setVisibility(View.VISIBLE);
//                        edt_remark.setVisibility(View.VISIBLE);
//                        stdStatus="S";
//                        break;
                }
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = inputEmail.getText().toString().trim();
                password = inputPassword.getText().toString().trim();
                name= inputName.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Name can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Email can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Password number can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!checkBox.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "Please agree to our policy first", Toast.LENGTH_SHORT).show();
                    return;
                }





                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
//                                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                                            .setTitleText("Email already exist")
//                                            .setContentText("")
//                                            .setConfirmText("Okay!")
//                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                                @Override
//                                                public void onClick(SweetAlertDialog sDialog) {
//                                                    sDialog.dismissWithAnimation();
//                                                }
//                                            })
//                                            .show();
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    saveUserInformation();
                                    userProfile();
//                                    new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
//                                            .setTitleText("Account created succesfully")
//                                            .setContentText("")
//                                            .setConfirmText("Okay!")
//                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                                @Override
//                                                public void onClick(SweetAlertDialog sDialog) {
//
//                                                    sDialog.dismissWithAnimation();
//                                                }
//                                            })
//                                            .show();
                                    if (stdType.equals("Student"))
                                    {
                                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                    finishAffinity();
                                }
                                    if (stdType.equals("Teacher"))
                                    {
                                        startActivity(new Intent(SignupActivity.this, ParentMenuActivity.class));
                                        finishAffinity();
                                    }

                                }
                            }
                        });

            }
        });
    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    //Set UserDisplay Name
    private void userProfile()
    {

        FirebaseUser user = auth.getCurrentUser();
        if(user!= null)
        {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
//.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg")) // here you can set image link also.
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("TESTING", "User profile updated.");
                            }
                        }
                    });
        }
    }




    private void saveUserInformation() {
        //Getting values from database
//        String email = inputEmail.getText().toString().trim();
//        String  name= inputName.getText().toString().trim();
//        String  age= inputAge.getText().toString().trim();
//        String  phoneno= inputPhoneno.getText().toString().trim();
//        String  password=inputPassword.getText().toString();

        //creating a userinformation object
        SigninClass userInformation = new SigninClass();

        //getting the current logged in user
//        FirebaseUser user = auth.getCurrentUser();
        userInformation.setEmail(email);
        userInformation.setName(name);
        userInformation.setPassword(password);

        String StudentRecordIDFromServer = databaseReference.push().getKey();

        // Adding the both name and number values using student details class object using ID.
//        databaseReference.child().child(StudentRecordIDFromServer).setValue(studentDetails);

        databaseReference.child(stdType).child(StudentRecordIDFromServer).setValue(userInformation);

    }
}
