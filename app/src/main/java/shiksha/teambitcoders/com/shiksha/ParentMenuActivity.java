package shiksha.teambitcoders.com.shiksha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ParentMenuActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    FirebaseUser user;
    static String LoggedIn_User_Email;
    String displayName, flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_menu);
        auth = FirebaseAuth.getInstance();
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

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapterForFaculty(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), MarksheetEntryActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getApplicationContext(), SingleEventActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getApplicationContext(), DailyNoticeBoardEntryActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent22 = new Intent(getApplicationContext(), EnergisedActivity.class);
                        startActivity(intent22);
                        break;
                    case 4:

                        auth.signOut();
                        Intent i = new Intent (getApplicationContext(), SigninActivity.class);
                        startActivity(i);
                        finish();
                        break;
//                    case 4:
//                        Intent intent4 = new Intent(getApplicationContext(), MapsActivity.class);
//                        startActivity(intent4);
//                        break;

                }
            }
        });


    }


//    public  void sincal(View v1)
//    {
//
//        Intent i = new Intent (this, MarksheetEntryActivity.class);
//        startActivity(i);
//    }
//
//    public  void mulcal(View v)
//    {
//
//        Intent i = new Intent (this, SingleEventActivity.class);
//        startActivity(i);
//    }
//
//    public  void logoutt(View v)
//    {
//        auth.signOut();
//        Intent i = new Intent (this, SigninActivity.class);
//        startActivity(i);
//        finish();
//    }

}
