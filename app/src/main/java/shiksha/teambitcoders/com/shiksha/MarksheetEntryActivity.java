package shiksha.teambitcoders.com.shiksha;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class MarksheetEntryActivity extends AppCompatActivity {

    EditText edt1,edt2,edt3,edt4,edt5,edt6;
    AutoCompleteTextView autoTxt;
    Button btn;
    String stringSem,thisDate,strAuto,str1,str2,str3,str4,str5,str6,sStudentName,sStudentBranch,sStudentClass,sAuthEmail,stringStudentBranchClass;
    DatabaseReference databaseReference,ref,ref8,ref9,ref10;
    final Context contextt=this;
    ArrayAdapter<String> adapter;
    public ArrayList<String> phoneNumbers = new ArrayList<>();
    public ArrayList<String> sem1Marks = new ArrayList<>();
    public ArrayList<String> sem2Marks = new ArrayList<>();
    Spinner semSp;
    String[] semArray= {"Select semester","Sem1","Sem2"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_marksheet_entry);
        databaseReference = FirebaseDatabase.getInstance().getReference("MarkSheetDetails");
        ref = FirebaseDatabase.getInstance().getReference();
        ref8 = FirebaseDatabase.getInstance().getReference();
        ref9= FirebaseDatabase.getInstance().getReference();
        semSp=(Spinner)findViewById(R.id.semSp);
        edt1=(EditText)findViewById(R.id.editText1);
        edt2=(EditText)findViewById(R.id.editText2);
        edt3=(EditText)findViewById(R.id.editText3);
        edt4=(EditText)findViewById(R.id.editText4);
        edt5=(EditText)findViewById(R.id.editText5);
        edt6=(EditText)findViewById(R.id.editText6);
        autoTxt=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        btn=(Button)findViewById(R.id.SubmitBtn);
//        Bundle extras = getIntent().getExtras();
//        sStudentBranch = extras.getString("STUDENT_BRANCH");
//        sStudentClass = extras.getString("STUDENT_CLASS");
//        stringStudentBranchClass=sStudentBranch+"_"+sStudentClass;

        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy");
        Date todayDate = new Date();
        thisDate = currentDate.format(todayDate);


        // Initializing an ArrayAdapter
        final ArrayAdapter<String> semSpinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,semArray){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        semSpinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        semSp.setAdapter(semSpinnerArrayAdapter);
        semSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemTextSem = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    stringSem=selectedItemTextSem;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        ref9 = FirebaseDatabase.getInstance().getReference();
//        ref9.child("MarkSheetDetails").child(thisDate).child("senior").child("Sem1").orderByChild("studentBranchClass").equalTo(stringStudentBranchClass).addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if(dataSnapshot.exists())
//                        {
//                            collectPhoneNumbers1((Map<String,Object>) dataSnapshot.getValue());
//                        }
//
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
//        ref10 = FirebaseDatabase.getInstance().getReference();
//        ref10.child("MarkSheetDetails").child(thisDate).child("senior").child("Sem2").orderByChild("studentBranchClass").equalTo(stringStudentBranchClass).addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if(dataSnapshot.exists())
//                        {
//                            collectPhoneNumbers2((Map<String,Object>) dataSnapshot.getValue());
//                        }
//
//                    }
//
//
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });


        ref8 = FirebaseDatabase.getInstance().getReference();
        ref8.child("UsersDetails").child("Student").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            collectPhoneNumbers((Map<String,Object>) dataSnapshot.getValue());

                        }

                    }



                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });

        adapter = new ArrayAdapter<String>(contextt, android.R.layout.simple_dropdown_item_1line, phoneNumbers);
        autoTxt.setAdapter(adapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sStudentName=autoTxt.getText().toString();
                str1=edt1.getText().toString();
                str2=edt2.getText().toString();
                str3=edt3.getText().toString();
                str4=edt4.getText().toString();
                str5=edt5.getText().toString();
                str6=edt6.getText().toString();

                if (TextUtils.isEmpty(sStudentName)) {
                    Toast.makeText(getApplicationContext(), "Enter Student Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (stringSem.equals("")) {
                    Toast.makeText(getApplicationContext(), "Select Semester", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(str1)) {
                    Toast.makeText(getApplicationContext(), "Enter the English mark of the student", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(str1)>100) {
                    Toast.makeText(getApplicationContext(), "English Mark can't be more than 100", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str2)) {
                    Toast.makeText(getApplicationContext(), "Enter the Bahasa Melayu mark of the student", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(str2)>100) {
                    Toast.makeText(getApplicationContext(), "Bahasa Melayu Mark can't be more than 100", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str3)) {
                    Toast.makeText(getApplicationContext(), "Enter the Mandarin mark of the student", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(str3)>100) {
                    Toast.makeText(getApplicationContext(), "Mandarin Mark can't be more than 100", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str4)) {
                    Toast.makeText(getApplicationContext(), "Enter the Mathematics mark of the student", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(str4)>100) {
                    Toast.makeText(getApplicationContext(), "Mathematics Mark can't be more than 100", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str5)) {
                    Toast.makeText(getApplicationContext(), "Enter the Science mark of the student", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(str5)>100) {
                    Toast.makeText(getApplicationContext(), "Science Mark can't be more than 100", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str6)) {
                    Toast.makeText(getApplicationContext(), "Enter the Moral mark of the student", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(str6)>100) {
                    Toast.makeText(getApplicationContext(), "Moral Mark can't be more than 100", Toast.LENGTH_SHORT).show();
                    return;
                }



                if (stringSem.equals("Sem1") && sem1Marks.contains(sStudentName) ) {
                    Toast.makeText(getApplicationContext(), "Marksheet Report of "+sStudentName+" for Sem 1 is already present", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (stringSem.equals("Sem2") && sem2Marks.contains(sStudentName)) {
                    Toast.makeText(getApplicationContext(), "Marksheet Report of "+sStudentName+" for Sem 2 is already present", Toast.LENGTH_SHORT).show();
                    return;
                }

                InsertMarkReport2Class insertMarkReport2Class = new InsertMarkReport2Class();
                insertMarkReport2Class.setAuthEmail(sAuthEmail);
//                                    insertMarkReport2Class.setAuthEmailstudentName(sAuthEmail+"_"+sStudentName);
//                                    insertMarkReport2Class.setStudentBranchClass(sStudentBranch+"_"+sStudentClass);
//                                    insertMarkReport2Class.setStudentBranchClassName(sStudentBranch+"_"+sStudentClass+"_"+sStudentName);
                insertMarkReport2Class.setStudentName(sStudentName);
                insertMarkReport2Class.setStudentBranch(sStudentBranch);
                insertMarkReport2Class.setStudentclass(sStudentClass);
                insertMarkReport2Class.setHighEnglish(str1);
                insertMarkReport2Class.setHighBahasaMelayu(str2);
                insertMarkReport2Class.setHighMandarin(str3);
                insertMarkReport2Class.setHighMathematics(str4);
                insertMarkReport2Class.setHighScience(str5);
                insertMarkReport2Class.setHighMoral(str6);
                float TotalMark=Float.parseFloat(str1)+Float.parseFloat(str2)+Float.parseFloat(str3)+Float.parseFloat(str4)+Float.parseFloat(str5)+Float.parseFloat(str6);
                float TotalPercentage=(TotalMark / 600.0f) * 100;
                insertMarkReport2Class.setTotalMark(String.valueOf(TotalMark));
                insertMarkReport2Class.setTotalpercentage(String.valueOf(TotalPercentage));

                SimpleDateFormat currentDate = new SimpleDateFormat("yyyy");
                Date todayDate = new Date();
                String thisDate = currentDate.format(todayDate);

                String StudentRecordIDFromServer = databaseReference.push().getKey();

                // Adding the both name and number values using student details class object using ID.
                databaseReference.child(thisDate).child(stringSem).child(StudentRecordIDFromServer).setValue(insertMarkReport2Class);

//                                    new SweetAlertDialog(contextt, SweetAlertDialog.SUCCESS_TYPE)
//                                            .setContentText("MarkSheet Report for " + sStudentName + " added succesfully")
//                                            .setTitleText("")
//                                            .setConfirmText("Okay!")
//                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                                @Override
//                                                public void onClick(SweetAlertDialog sDialog) {
//                                                    sDialog.dismissWithAnimation();
//                                                }
//                                            })
//                                            .show();


                autoTxt.setText("");
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
                edt4.setText("");
                edt5.setText("");
                edt6.setText("");
                phoneNumbers.remove(sStudentName);
                adapter = new ArrayAdapter<String>(contextt, android.R.layout.simple_dropdown_item_1line, phoneNumbers);
                autoTxt.setAdapter(adapter);

//                ref.child("UsersDetails").child("Students").orderByChild("studentBranchClassName").equalTo(stringStudentBranchClass+"_"+sStudentName).addListenerForSingleValueEvent(
//                        new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                if (dataSnapshot.exists()) {
//
//
//                                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//
//                                        sAuthEmail = (String) postSnapshot.child("authEmail").getValue();
//
//
//
//
//
//                                    }
//
//
//
//
//                                }
//                            }
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                                //handle databaseError
//                            }
//                        });










            }
        });



    }



    public void collectPhoneNumbers(Map<String,Object> Students) {
//        ArrayList<Long> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : Students.entrySet()){

            //Get user map
            Map singleUser1 = (Map) entry.getValue();
            //Get phone field and append to list
            phoneNumbers.add((String) singleUser1.get("name"));



        }



    }


    public void collectPhoneNumbers1(Map<String,Object> AttendanceDetails) {
//        ArrayList<Long> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : AttendanceDetails.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            sem1Marks.add((String) singleUser.get("studentName"));
        }

    }


    public void collectPhoneNumbers2(Map<String,Object> AttendanceDetails) {
//        ArrayList<Long> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : AttendanceDetails.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            sem2Marks.add((String) singleUser.get("studentName"));
        }

    }



}
