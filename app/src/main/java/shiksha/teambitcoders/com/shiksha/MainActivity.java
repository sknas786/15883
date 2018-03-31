package shiksha.teambitcoders.com.shiksha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    FirebaseUser user;
    String stringPassword, stringCurrentPassword, stringRetypePassword;
    EditText titleBox, descriptionBox, descriptionBox2;
    DatabaseReference refer;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    static String LoggedIn_User_Email;
    String displayName, flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapterMenuForStudent(this));


        auth = FirebaseAuth.getInstance();

        //get current user
        user = FirebaseAuth.getInstance().getCurrentUser();

        displayName = user.getDisplayName();


//        final ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setTitle("Welcome " + displayName);
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
//        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#330000ff")));
//        getSupportActionBar().setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#550000ff")));


        LoggedIn_User_Email = user.getEmail();
//        OneSignal.sendTag("User_ID", LoggedIn_User_Email);

//        user = auth.getCurrentUser();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                switch (position) {
                    case 0:
//                        sendNotification();
                        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
//                        user.updatePassword("123456789")
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            Toast.makeText(MainActivity.this, "Password is updated!", Toast.LENGTH_SHORT).show();
//                                        } else {
//                                            Toast.makeText(MainActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
//
//                                        }
//                                    }
//
//                                });

//                        Toast.makeText(getApplicationContext(), "This part is under development",
//                                Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(getApplicationContext(), TimetableActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
//                        Toast.makeText(getApplicationContext(), "This part is under development",
//                                Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(getApplicationContext(), CommunityChatActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        //energized material
                        Intent intent4 = new Intent(getApplicationContext(), FluentActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
//                        Toast.makeText(getApplicationContext(), "This part is under development",
//                                Toast.LENGTH_SHORT).show();
//                        flag="admin_report";
//
//                        Intent i = new Intent (getApplicationContext(), ChoosClassActivity.class);
//                        i.putExtra("flag", flag);
//                        startActivity(i);
//                        Intent intent5 = new Intent(getApplicationContext(), ReportMenuActivity.class);
//                        startActivity(intent5);
                        //attendance
                        Intent intent6 = new Intent(getApplicationContext(), ParentCalendarViewActivity.class);
                        startActivity(intent6);
                        break;
                    case 6:
                        Intent intent7 = new Intent(getApplicationContext(), RecyclerViewNotice.class);
                        startActivity(intent7);
                        break;
                    case 7:
                        Intent intent8 = new Intent(getApplicationContext(), SelectGrievanceActivity.class);
                        startActivity(intent8);
                        break;
                    case 8:
                        Intent intent9 = new Intent(getApplicationContext(), AssistantActivity.class);
                        startActivity(intent9);
                        break;
                    case 9:
                        Intent intent10 = new Intent(getApplicationContext(), AttendanceActivity.class);
                        startActivity(intent10);
                        break;
                    case 10:
                        Intent intent11 = new Intent(getApplicationContext(), ReportAttendanceActivity.class);
                        startActivity(intent11);
                        break;
                    case 11:
                        auth.signOut();
                        Intent intent12 = new Intent(getApplicationContext(), SigninActivity.class);
                        startActivity(intent12);
                        break;
//                    case 8:
////                        Toast.makeText(getApplicationContext(), "This part is under development",
////                                Toast.LENGTH_SHORT).show();
////                        Intent intent8 = new Intent(getApplicationContext(), ContactUsActivity.class);
////                        startActivity(intent8);
//                        break;

                }
            }
        });
    }
}



















