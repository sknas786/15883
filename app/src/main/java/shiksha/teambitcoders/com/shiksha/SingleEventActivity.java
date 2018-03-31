package shiksha.teambitcoders.com.shiksha;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class SingleEventActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
private EditText e1,e2;
    private ImageButton btn;
    private Button submitBtn;
    String eventStringDate,eventStringName,eventStringColor;
    String colorSelect;
    String[] country = { "Red (Public Holidays)", "Blue (School Break)", "Green (Other Events)" };
    final Context contextt=this;
    String cDate,formattedDate,stringUniqueID;
    String send_email;
    DatabaseReference databaseReference,ref;
    ArrayList<String> phoneNumbers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event);


//        final ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setTitle("Calendar and Event");

        databaseReference = FirebaseDatabase.getInstance().getReference("EventsDetails");
//        ref = FirebaseDatabase.getInstance().getReference("UsersDetails");
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        btn=(ImageButton)findViewById(R.id.button4);
        submitBtn=(Button)findViewById(R.id.button5);
        e1=(EditText)findViewById(R.id.editText3);
        e2=(EditText)findViewById(R.id.editText4);


//        ref.child("Faculty").addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        //Get map of users in datasnapshot
//                        collectPhoneNumbers((Map<String,Object>) dataSnapshot.getValue());
//                    }
//
//
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });
//
//
//        ref.child("VerifiedParents").addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        //Get map of users in datasnapshot
//                        collectPhoneNumbers1((Map<String,Object>) dataSnapshot.getValue());
//                    }
//
//
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(SingleEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
//                        selectedmonth = selectedmonth + 1;

                        try{
                            Date date = new Date(selectedyear-1900, selectedmonth,selectedday);
                            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                             cDate = formatter.format(date);
//                            DateFormat aoriginalFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                            DateFormat atargetFormat = new SimpleDateFormat("dd MMM,yyyy");
                            Date date2 = formatter.parse(cDate);
                            formattedDate = atargetFormat.format(date2);
                        }catch (ParseException e1){
                            e1.printStackTrace(); }
                        catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }

                        e1.setText(formattedDate);
//                        if(String.valueOf(selectedday).length()== 1 )
//                        {
//
//                            if(String.valueOf(selectedmonth).length()== 1)
//                            {
//                                e1.setText("0"+selectedday + "-0" + selectedmonth + "-" + selectedyear);
//                            }
//
//                            e1.setText("0"+selectedday +  selectedmonth + "-" + selectedyear);
//
//                        }
//
//                        else{
//                            e1.setText(selectedday + "-" + selectedmonth + "-" + selectedyear);
//                        }
//
//                        e1.setText("0"+selectedday + "-0" + selectedmonth + "-" + selectedyear);

                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                eventStringDate=e1.getText().toString();
                eventStringName=e2.getText().toString();
                eventStringColor=colorSelect;


                if (TextUtils.isEmpty(eventStringDate)) {
                    Toast.makeText(getApplicationContext(), "Date can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(eventStringName)) {
                    Toast.makeText(getApplicationContext(), "Comment can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                EventClass eventclass = new EventClass();
//                GetDataFromEditText();
                stringUniqueID = UUID.randomUUID().toString();
                eventclass.setEventUniqueId(stringUniqueID);
                eventclass.setEventName(eventStringName);
                eventclass.setEventDate(cDate);
                eventclass.setEventColor(eventStringColor);


                // Getting the ID from firebase database.
                String StudentRecordIDFromServer = databaseReference.push().getKey();

                // Adding the both name and number values using student details class object using ID.
                databaseReference.child(StudentRecordIDFromServer).setValue(eventclass);

//                SweetAlertDialog sweetAlertDialog=new SweetAlertDialog(contextt);
                Toast.makeText(getApplicationContext(), "Event created succesfully",Toast.LENGTH_LONG).show();
                new SweetAlertDialog(contextt, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Event added succesfully")
                        .setContentText("")
                        .setConfirmText("Okay!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();

//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                        contextt);
//
//                // set title
//                alertDialogBuilder.setTitle("Event");
//
//                // set dialog message
//                alertDialogBuilder
//                        .setMessage("Event added succesfully")
//                        .setCancelable(false)
//                        .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // if this button is clicked, just close
//                                // the dialog box and do nothing
//                                dialog.cancel();
//                            }
//                        });
//
//                // create alert dialog
//                AlertDialog alertDialog = alertDialogBuilder.create();
//
//                // show it
//                alertDialog.show();

//                for (int i = 0; i < phoneNumbers.size(); i++) {
////                    Toast.makeText(getApplicationContext(), phoneNumbers.get(i),Toast.LENGTH_LONG).show();
//                    send_email=phoneNumbers.get(i);
////                    sendNotification();
//
//                    }

                e1.setText("");
                e2.setText("");


//                Toast.makeText(SingleEventActivity.this,"Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();
            }
        });

    }

//    private void GetDataFromEditText() {
//
//
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.event_menu_item, menu);
//        return true;
//    }
//
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem event_menu_item) {
//        switch (event_menu_item.getItemId()) {
//            case R.id.view_in_calendar:
//                Intent i = new Intent(this,ParentCalendarViewActivity.class);
//                this.startActivity(i);
//                return true;
//            case R.id.view_in_list:
//                Intent j = new Intent(this,TaskDeleteActivity.class);
//                this.startActivity(j);
//                return true;
//            default:
//                return super.onOptionsItemSelected(event_menu_item);
//        }
//    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        colorSelect=country[position];

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    private void sendNotification()
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);


                    //This is a Simple Logic to Send Notification different Device Programmatically....
//                    if (MainActivity.LoggedIn_User_Email.equals("user1@gmail.com")) {
//                        send_email = "user2@gmail.com";
//                    } else {
//                        send_email = "user1@gmail.com";
//                    }
//                    send_email = "sam@gmail.com";

                    try {
                        String jsonResponse;

                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);

                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization", "Basic ZWI0YjE4NmEtNzhlYy00OTUxLTkwYTMtODgzODk4ZTlhODZk");
                        con.setRequestMethod("POST");

                        String strJsonBody = "{"
                                + "\"app_id\": \"9fb09dc3-d594-4fc5-961f-04b1a671be2a\","

                                + "\"filters\": [{\"field\": \"tag\", \"key\": \"User_ID\", \"relation\": \"=\", \"value\": \"" + send_email + "\"}],"

                                + "\"data\": {\"foo\": \"bar\"},"
                                + "\"contents\": {\"en\": \"New Event is on "+formattedDate+"\"}"
                                + "}";


                        System.out.println("strJsonBody:\n" + strJsonBody);

                        byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                        con.setFixedLengthStreamingMode(sendBytes.length);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(sendBytes);

                        int httpResponse = con.getResponseCode();
                        System.out.println("httpResponse: " + httpResponse);

                        if (httpResponse >= HttpURLConnection.HTTP_OK
                                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        } else {
                            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        }
                        System.out.println("jsonResponse:\n" + jsonResponse);

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
    }



    public void collectPhoneNumbers(Map<String,Object> Faculty) {
//

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : Faculty.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            phoneNumbers.add((String) singleUser.get("facultyEmail"));
        }
//        Toast.makeText(getApplicationContext(), "It is running",
//                Toast.LENGTH_SHORT).show();

    }


    public void collectPhoneNumbers1(Map<String,Object> VerifiedParents) {
//

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : VerifiedParents.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            phoneNumbers.add((String) singleUser.get("email"));
        }
//        Toast.makeText(getApplicationContext(), "It is running",
//                Toast.LENGTH_SHORT).show();

    }
}
