package shiksha.teambitcoders.com.shiksha;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FeedbackActivity extends AppCompatActivity {
    Button btn;
    FirebaseUser user;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth1;
    EditText edt;
    String displayName;
    String easyPuzzle;
    final Context contextt = this;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        btn = (Button) findViewById(R.id.button9);
        auth1 = FirebaseAuth.getInstance();
        Intent i = getIntent();
        easyPuzzle = i.getStringExtra("flag");

        //get current user
        user = auth1.getCurrentUser();

        displayName = user.getDisplayName();

        edt = (EditText) findViewById(R.id.editText7);
        btn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                String smsNumber = "7873008109";
                String smsText = "Subham" + " has posted a grievance on " + easyPuzzle + " to be addressed immediately.\n Grievance: " + edt.getText().toString();
//                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                // get the last know location from your location manager.
//                if (ActivityCompat.checkSelfPermission(contextt, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(contextt, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//
//                    return;
//                }
//                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                SmsManager.getDefault().sendTextMessage(smsNumber, null, smsText, null,null);


                new SweetAlertDialog(contextt, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("")
                        .setContentText("Message sent to concern authority!")
                        .setConfirmText("Okay!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();

                edt.setText("");
            }});
    }
}
