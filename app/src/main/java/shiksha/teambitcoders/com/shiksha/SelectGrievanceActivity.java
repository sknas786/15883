package shiksha.teambitcoders.com.shiksha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectGrievanceActivity extends AppCompatActivity {
    String[] stringArrayForK1 = {"Ragging","Sexual harassment","Academic","Hostel"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_grievance);


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview,R.id.label, stringArrayForK1);

        ListView listView = (ListView) findViewById(R.id.mobile_list1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                switch(position)
                {
                    case 0:
                        Intent i=new Intent(getApplicationContext(),FeedbackActivity.class);
                        i.putExtra("flag", "Ragging");
                        startActivity(i);
                        break;
                    case 1:
                        Intent i1=new Intent(getApplicationContext(),FeedbackActivity.class);
                        i1.putExtra("flag", "Sexual harassment");
                        startActivity(i1);
                        break;
                    case 2:
                        Intent i2=new Intent(getApplicationContext(),FeedbackActivity.class);
                        i2.putExtra("flag", "Academic");
                        startActivity(i2);
                        break;
                    case 3:
                        Intent i3=new Intent(getApplicationContext(),FeedbackActivity.class);
                        i3.putExtra("flag", "Hostel");
                        startActivity(i3);
                        break;



                }

//                Toast.makeText(SelectCorrectSyllabus.this,
//                        "Item in position " + position + " clicked", Toast.LENGTH_LONG).show();
            }
        });
    }
}
