package shiksha.teambitcoders.com.shiksha;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dnkilic.waveform.WaveView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class AssistantActivity extends AppCompatActivity implements TextToSpeech.OnInitListener  {
    EditText input1;
    public TextToSpeech mTTS;
    TextView txt;
    DatabaseReference DataRef;
    String s;
    String acctname,voiceType;
    String demo;
    RadioGroup rg;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private WaveView mWaveView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant);

        MediaPlayer ring = MediaPlayer.create(AssistantActivity.this, R.raw.voicemail);
        ring.start();
        mTTS = new TextToSpeech(this, this);

        input1 = (EditText)findViewById(R.id.input);
        txt=(TextView)findViewById(R.id.txtmsg) ;
        Button fab = (Button) findViewById(R.id.button5);
//        mWaveView = (WaveView) findViewById(R.id.waveview);

        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        voiceType="Text";

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
                        voiceType="Text";
                        input1.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioWindows
                                                   :
                        // TODO Something
//                        edt_ill.setVisibility(View.INVISIBLE);
//                        edt_remark.setVisibility(View.INVISIBLE);
                        voiceType="Voice";
                        input1.setVisibility(View.INVISIBLE);
//                        DisplayMetrics dm = new DisplayMetrics();
//                        getWindowManager().getDefaultDisplay().getMetrics(dm);
//                        mWaveView.initialize(dm);

                        break;

                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(voiceType.equals("Voice"))
                {
//                    s=input1.getText().toString();
//                    input1.setText("");
                    promptSpeechInput();
//                    mWaveView.speechStarted();
                }

                if(voiceType.equals("Text"))
                {
                    s=input1.getText().toString();
                    input1.setText("");

                    DataRef = FirebaseDatabase.getInstance().getReference().child("check_msg").child(s);

                    DataRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()) {
                                acctname = (String) dataSnapshot.child("messageText").getValue();
                                txt.setText(acctname);
                                mTTS = new TextToSpeech(AssistantActivity.this, new TextToSpeech.OnInitListener() {
                                    @Override
                                    public void onInit(int status) {
                                        mTTS.setLanguage(Locale.UK);
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                            mTTS.speak(acctname, TextToSpeech.QUEUE_FLUSH, null, null);
                                        } else {
                                            mTTS.speak(acctname, TextToSpeech.QUEUE_FLUSH, null);
                                        }

                                    }
                                });


                            }
                            else{
                                acctname="Sorry.Ask another related question ?";
                                txt.setText(acctname);
                                mTTS = new TextToSpeech(AssistantActivity.this, new TextToSpeech.OnInitListener() {
                                    @Override
                                    public void onInit(int status) {
                                        mTTS.setLanguage(Locale.UK);
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                            mTTS.speak(acctname, TextToSpeech.QUEUE_FLUSH, null, null);
                                        } else {
                                            mTTS.speak(acctname, TextToSpeech.QUEUE_FLUSH, null);
                                        }

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });
                }


            }

        });
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
//                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    s=result.get(0);
                    DataRef = FirebaseDatabase.getInstance().getReference().child("check_msg").child(s);

                    DataRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()) {
                                acctname = (String) dataSnapshot.child("messageText").getValue();
                                txt.setText(acctname);
                                mTTS = new TextToSpeech(AssistantActivity.this, new TextToSpeech.OnInitListener() {
                                    @Override
                                    public void onInit(int status) {
                                        mTTS.setLanguage(Locale.UK);
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                            mTTS.speak(acctname, TextToSpeech.QUEUE_FLUSH, null, null);
                                        } else {
                                            mTTS.speak(acctname, TextToSpeech.QUEUE_FLUSH, null);
                                        }

                                    }
                                });


                            }
                            else{
                                acctname="Sorry.Ask another related question ?";
                                txt.setText(acctname);
                                mTTS = new TextToSpeech(AssistantActivity.this, new TextToSpeech.OnInitListener() {
                                    @Override
                                    public void onInit(int status) {
                                        mTTS.setLanguage(Locale.UK);
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                            mTTS.speak(acctname, TextToSpeech.QUEUE_FLUSH, null, null);
                                        } else {
                                            mTTS.speak(acctname, TextToSpeech.QUEUE_FLUSH, null);
                                        }

                                    }
                                });
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });

                }
                break;
            }

        }
    }


    @Override
    public void onInit(int i) {

    }
}
