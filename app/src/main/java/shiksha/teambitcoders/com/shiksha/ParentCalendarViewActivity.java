package shiksha.teambitcoders.com.shiksha;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ParentCalendarViewActivity extends AppCompatActivity   {

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMM-yyyy", Locale.getDefault());
    long timeInMilliseconds;
    final Context contextt=this;
    DatabaseReference ref;
    TextView txt;

    //        ArrayList<String> EventNames = new ArrayList<String>(Arrays.asList("Teacher's Day", "Independence Day","Christmas Day","New Year", " Makar Sankranti"));
//    ArrayList<String> EventDates= new ArrayList<String>(Arrays.asList("14-11-2017", "15-08-2017","25-12-2017","01-01-2018", "14-01-2018"));
//    ArrayList<String> EventsTime=new ArrayList<>();
    ArrayList<String> EventNames= new ArrayList<String>();
    ArrayList<String> EventDates= new ArrayList<String>();
    ArrayList<String> EventColor= new ArrayList<String>();
    //    Date todayDate = new Date();
//    String thisDate = dateFormatMonth.format(todayDate);
//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//    Date parsedDate = dateFormat.parse("17012018");

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_calendar_view);
        Date todayDate = new Date();
        String thisDate = dateFormatMonth.format(todayDate);
//        final ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setTitle("Calendar View");

//        btn=(Button)findViewById(R.id.button7);
        txt=(TextView) findViewById(R.id.textView7);
        txt.setText(thisDate);
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(false);
        compactCalendar.shouldDrawIndicatorsBelowSelectedDays(true);
        compactCalendar.shouldSelectFirstDayOfMonthOnScroll(false);
        compactCalendar.setFirstDayOfWeek(1);
        compactCalendar.showCalendar();










//        compactCalendar.hideCalendar();

//        btn.setOnClickListener(new View.OnClickListener() {
////            @Override
//            public void onClick(View view) {
//                compactCalendar.setVisibility(View.VISIBLE);
////                compactCalendar.showCalendarWithAnimation();
//                btn.setVisibility(View.INVISIBLE);
//
//
//            }
//        });


//        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path_attendance);

        ref = FirebaseDatabase.getInstance().getReference().child("EventsDetails");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        collectPhoneNumbers((Map<String,Object>) dataSnapshot.getValue());
                        for(int i=0;i<EventNames.size();i++)
                        {
                            DateFormat originalFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
                            DateFormat targetedFormat = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = null;
                            try {
                                date = originalFormat.parse(EventDates.get(i));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String formattedDate = targetedFormat.format(date);
                            String givenDateString = formattedDate +" 00:00:00 GMT+05:30";
//                            String givenDateString = EventDates.get(i) +" 00:00:00 GMT+05:30";
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
                            try {
                                Date mDate = sdf.parse(givenDateString);
                                timeInMilliseconds = mDate.getTime();
                                System.out.println("Date in milli :: " + timeInMilliseconds);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String getEventColor=EventColor.get(i);
                            if(getEventColor.equals("Red (Public Holidays)")){
                                Event ev = new Event(Color.RED, timeInMilliseconds, EventNames.get(i));
                                compactCalendar.addEvent(ev);
                            }
                            if(getEventColor.equals("Blue (School Break)")){
                                Event ev = new Event(Color.BLUE, timeInMilliseconds, EventNames.get(i));
                                compactCalendar.addEvent(ev);
                            }
                            if(getEventColor.equals("Green (Other Events)")){
                                Event ev = new Event(Color.GREEN, timeInMilliseconds, EventNames.get(i));
                                compactCalendar.addEvent(ev);
                            }




                        }



                        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                            @Override
                            public void onDayClick(Date dateClicked) {
                                Context context = getApplicationContext();
                                List<Event> events = compactCalendar.getEvents(dateClicked);
                                SimpleDateFormat targetFormat = new SimpleDateFormat("MM-dd-yyyy");
//                                SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
                                String formattedDate = targetFormat.format(dateClicked);

                                for(int j=0;j<EventDates.size();j++) {
                                    if (formattedDate.compareTo(EventDates.get(j)) == 0) {
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                                contextt);


                                        DateFormat originalFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
                                        DateFormat targetedFormat = new SimpleDateFormat("dd MMM, yyyy");
                                        Date date = null;
                                        try {
                                            date = originalFormat.parse(EventDates.get(j));
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        String formattedDatee = targetedFormat.format(date);

                                        // set title
                                        alertDialogBuilder.setTitle(formattedDatee);

                                        // set dialog message
                                        alertDialogBuilder
                                                .setMessage(EventNames.get(j))
                                                .setCancelable(false)
                                                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        // if this button is clicked, just close
                                                        // the dialog box and do nothing
                                                        dialog.cancel();
                                                    }
                                                });

                                        // create alert dialog
                                        AlertDialog alertDialog = alertDialogBuilder.create();

                                        // show it
                                        alertDialog.show();
                                    }
                                    else {
//                        Toast.makeText(context, "No Events Planned for that day", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }



                            @Override
                            public void onMonthScroll(Date firstDayOfNewMonth) {
//                                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
                                txt.setText(dateFormatMonth.format(firstDayOfNewMonth));
                            }
                        });



                    }



                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });





//        List<Event> events = compactCalendar.getEvents(1477040400000L);

        //Set an event for Teachers' Professional Day 2016 which is 21st of October


//        Event ev2 = new Event(Color.BLUE, 1477040400000L, "Exam");
//        compactCalendar.addEvent(ev2);


    }

    private void collectPhoneNumbers(Map<String, Object> Student_Details_Database) {

        for (Map.Entry<String, Object> entry : Student_Details_Database.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            EventNames.add((String) singleUser.get("eventName"));
            EventDates.add((String) singleUser.get("eventDate"));
            EventColor.add((String) singleUser.get("eventColor"));


        }
    }

    public void eventlist(View v)
    {
//        Intent i=new Intent(getApplicationContext(),EventRecyclerViewActivity.class);
//        startActivity(i);
    }


}
