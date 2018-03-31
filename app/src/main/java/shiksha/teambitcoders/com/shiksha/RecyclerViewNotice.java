package shiksha.teambitcoders.com.shiksha;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecyclerViewNotice extends AppCompatActivity {

    DatabaseReference databaseReference, ref;

    ProgressDialog progressDialog;

    public List<Noticeboardclass> list = new ArrayList<>();

    public ArrayList<String> list1 = new ArrayList<>();


    public RecyclerView recyclerView;

    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_notice);


//        final ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setTitle("All Events");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewNotice.this));

        progressDialog = new ProgressDialog(RecyclerViewNotice.this);

        progressDialog.setMessage("Loading Data,Please Wait");

        progressDialog.show();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM-dd-yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("NoticeboardDetails");

        databaseReference.orderByChild("noticeDate").equalTo(thisDate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Noticeboardclass noticeboardclass = dataSnapshot.getValue(Noticeboardclass.class);

                    list.add(noticeboardclass);
                }

                adapter = new RecyclerViewNoticeAdapter(RecyclerViewNotice.this, list);
//
                recyclerView.setAdapter(adapter);


                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });


    }
}