package shiksha.teambitcoders.com.shiksha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AttendanceActivity extends AppCompatActivity {

    TextView txtMark1,txtMark2,txtMark3,txtMark4,txtMark5,txtMark6,txtMark7,txtMark8;
    TextView txtMark21,txtMark22,txtMark23,txtMark24,txtMark25,txtMark26,txtMark27,txtMark28;
    DatabaseReference databaseReference,ref,ref2;
    String thisDate,stringStudentName,stringAuthEmail;
    private FirebaseAuth auth;
    LinearLayout linearLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

//        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},00);


//        linearLayout=(LinearLayout)findViewById(R.id.linear1);


        auth = FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference();
        ref2= FirebaseDatabase.getInstance().getReference();
        stringAuthEmail=auth.getCurrentUser().getEmail();


//        Bundle extras = getIntent().getExtras();
//        stringStudentName = extras.getString("STUDENT_NAME");



        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy");
        Date todayDate = new Date();
        thisDate = currentDate.format(todayDate);

        txtMark1=(TextView)findViewById(R.id.mark1);
        txtMark2=(TextView)findViewById(R.id.mark2);
        txtMark3=(TextView)findViewById(R.id.mark3);
        txtMark4=(TextView)findViewById(R.id.mark4);
        txtMark5=(TextView)findViewById(R.id.mark5);
        txtMark6=(TextView)findViewById(R.id.mark6);
        txtMark7=(TextView)findViewById(R.id.mark7);
        txtMark8=(TextView)findViewById(R.id.mark8);
        txtMark21=(TextView)findViewById(R.id.mark21);
        txtMark22=(TextView)findViewById(R.id.mark22);
        txtMark23=(TextView)findViewById(R.id.mark23);
        txtMark24=(TextView)findViewById(R.id.mark24);
        txtMark25=(TextView)findViewById(R.id.mark25);
        txtMark26=(TextView)findViewById(R.id.mark26);
        txtMark27=(TextView)findViewById(R.id.mark27);
        txtMark28=(TextView)findViewById(R.id.mark28);

        ref.child("MarkSheetDetails").child(thisDate).child("Sem1").orderByChild("studentName").equalTo("Subham").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        txtMark1.setText((String) postSnapshot.child("highEnglish").getValue());
                        txtMark2.setText((String) postSnapshot.child("highBahasaMelayu").getValue());
                        txtMark3.setText((String) postSnapshot.child("highMandarin").getValue());
                        txtMark4.setText((String) postSnapshot.child("highMathematics").getValue());
                        txtMark5.setText((String) postSnapshot.child("highScience").getValue());
                        txtMark6.setText((String) postSnapshot.child("highMoral").getValue());
                        txtMark7.setText((String) postSnapshot.child("totalMark").getValue());
                        txtMark8.setText((String) postSnapshot.child("totalpercentage").getValue());

                    }
//
//
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        ref2.child("MarkSheetDetails").child(thisDate).child("Sem2").orderByChild("studentName").equalTo("Subham").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        txtMark21.setText((String) postSnapshot.child("highEnglish").getValue());
                        txtMark22.setText((String) postSnapshot.child("highBahasaMelayu").getValue());
                        txtMark23.setText((String) postSnapshot.child("highMandarin").getValue());
                        txtMark24.setText((String) postSnapshot.child("highMathematics").getValue());
                        txtMark25.setText((String) postSnapshot.child("highScience").getValue());
                        txtMark26.setText((String) postSnapshot.child("highMoral").getValue());
                        txtMark27.setText((String) postSnapshot.child("totalMark").getValue());
                        txtMark28.setText((String) postSnapshot.child("totalpercentage").getValue());

                    }
//
//
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });








    }




//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.download_report_menu, menu);
//        return true;
//    }
//
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem download_report_menu) {
//        switch (download_report_menu.getItemId()) {
//
//            case R.id.action_notifications:
//                Toast.makeText(getApplicationContext(),"Report is saved to gallerty", Toast.LENGTH_LONG).show();
//                captureScreen();
//
//
//                return true;
//
//
//
//            default:
//                return super.onOptionsItemSelected(download_report_menu);
//        }
//    }
//
//    public void captureScreen() {
//
//
////        View v = getWindow().getDecorView().getRootView();
//        View v=linearLayout;
//        v.setDrawingCacheEnabled(true);
//        Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
//        v.setDrawingCacheEnabled(false);
//        try {
//            FileOutputStream fos = new FileOutputStream(new File(Environment
//                    .getExternalStorageDirectory().toString(), "SCREEN"
//                    + System.currentTimeMillis() + ".png"));
//            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
//            fos.flush();
//            fos.close();.
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }




}
