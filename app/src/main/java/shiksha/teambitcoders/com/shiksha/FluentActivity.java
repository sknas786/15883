package shiksha.teambitcoders.com.shiksha;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import shiksha.teambitcoders.com.shiksha.utils.ConversionCallaback;

public class FluentActivity extends AppCompatActivity  implements TextToSpeech.OnInitListener,ConversionCallaback{

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private TextView mVoiceInputTv1;
    private ImageButton mSpeakBtn;
    private Button buttonSpeak;
    private Button speechToText;
    //    private fluent.subhamlenka.com.fluent.CircleDisplay d;
    private TextToSpeech tts;
    private Button btn;
    String str;
    String InputStr;
    CircleDisplay cd;
    CircleDisplay d;

    private AVLoadingIndicatorView avi;


    private static final int TTS = 0;
    private static final int STT = 1;
    private static int CURRENT_MODE = -1;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    int imatch;
    String s;
    String s2;
    int i;
    String resstr;
    int tresult;
    float wtresult;
    int cresult;
    int pos;
    int pickupLinesItemIndex = 0;
    Typeface typeface;
    float f1;
    float[] farr={69f,70f,82f,90f,88f,78f};
    String[] sent={"He took a look at the newspaper before going to bed","The policeman arrested the thief","What you spend time doing in your childhood affects the rest of your life","My suggestion is for more trees to be planted along the streets","You aren't permitted to bring dogs into this building","I asked him if he would help me" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fluent);
        float hi = 33.3f;
        cd = (CircleDisplay) findViewById(R.id.circleDisplay);
        cd.setAnimDuration(3000);
        cd.setValueWidthPercent(5f);
        cd.setTextSize(20f);
        cd.setColor(Color.BLACK);
        cd.setDrawText(true);
        cd.setDrawInnerCircle(true);
        cd.setFormatDigits(1);
        cd.setTouchEnabled(false);
//        cd.setSelectionListener(this);
        cd.setUnit("%");
        cd.setStepSize(0.5f);
        // cd.setCustomText(); // sets a custom array of text

        d=(CircleDisplay)findViewById(R.id.circleDisplay2);
        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mVoiceInputTv1 = (TextView) findViewById(R.id.textView);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        btn=(Button)findViewById(R.id.button2);
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi2);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/clear.otf");

        d.setAnimDuration(3000);
        d.setValueWidthPercent(5f);
        d.setTextSize(12f);
        d.setColor(Color.BLACK);
        d.setDrawText(true);
        d.setDrawInnerCircle(true);
        d.setFormatDigits(1);
        d.setTouchEnabled(false);
        d.setUnit("% Pitch");
        d.setStepSize(0.5f);


        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                startVoiceInput();
                d.setVisibility(v.VISIBLE);

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pickupLinesItemIndex < (sent.length-1))
                {
                    String pickupLine = sent[++pickupLinesItemIndex];
                    InputStr = pickupLine;
                    if (InputStr.isEmpty() || InputStr.trim().length() == 0) {

                    }
                    i= (InputStr.trim().split("\\s+").length);
                    s = String.valueOf(i);
                    mVoiceInputTv.setText(InputStr);
                    mVoiceInputTv.setTypeface(typeface);
                }
                else{
                    InputStr = sent[0];
                    if (InputStr.isEmpty() || InputStr.trim().length() == 0) {

                    }
                    i= (InputStr.trim().split("\\s+").length);
                    s = String.valueOf(i);
                    mVoiceInputTv.setText(InputStr);
                    mVoiceInputTv.setTypeface(typeface);
                    pickupLinesItemIndex=0;

                }
            }
        });
        InputStr = sent[0];
        if (InputStr.isEmpty() || InputStr.trim().length() == 0) {

        }
        i= (InputStr.trim().split("\\s+").length);
        s = String.valueOf(i);
        mVoiceInputTv.setText(InputStr);
        mVoiceInputTv.setTypeface(typeface);

        tts = new TextToSpeech(this, this);
        buttonSpeak = (Button) findViewById(R.id.button);

        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speakOut();
            }

        });


        if (Build.VERSION.SDK_INT >= 23) {
            // Pain in A$$ Marshmallow+ Permission APIs
            requestForPermission();
        } else {
            // Pre-Marshmallow
            setUpView();
        }
    }

    private void setUpView() {

//        speechToText=(Button)findViewById(R.id.button3);
//        speechToText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//////                Snackbar.make(view, "Listening", Snackbar.LENGTH_LONG)
//////                        .setAction("Action", null).show();
//
//                //Ask translator factory to start speech tpo text convertion
//                //Hello There is optional
//                TranslatorFactory.getInstance().
//                        getTranslator(TranslatorFactory.TRANSLATOR_TYPE.SPEECH_TO_TEXT, MainActivity.this).
//                        initialize("", MainActivity.this);
//
//                CURRENT_MODE = STT;
//            }
//        });
        mSpeakBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        avi.show();
                        TranslatorFactory.getInstance().
                                getTranslator(TranslatorFactory.TRANSLATOR_TYPE.SPEECH_TO_TEXT, FluentActivity.this).
                                initialize("", FluentActivity.this);

                        CURRENT_MODE = STT;
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        avi.hide();
                        return true; // if you want to handle the touch event
                }

                return false;
            }

        });
    }

    @Override
    public void onSuccess(String result) {
//        Toast.makeText(this, "Result " + result, Toast.LENGTH_SHORT).show();


        switch (CURRENT_MODE) {

            case STT:
                str=result.toLowerCase();
//                Toast.makeText(this, "Result " + str, Toast.LENGTH_SHORT).show();
                mVoiceInputTv.setText("You said :"+str);
                findMatch();
        }
    }

    @Override
    public void onCompletion() {
        Toast.makeText(this, "Done ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorOccured(String errorMessage) {
////        erroConsole.setText(errorMessage);
    }













    /**
     * Request Permission
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void requestForPermission() {
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();

        if (!isPermissionGranted(permissionsList, Manifest.permission.RECORD_AUDIO))
            permissionsNeeded.add("Require for Speech to text");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {

                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);

                for (int i = 1; i < permissionsNeeded.size(); i++) {
                    message = message + ", " + permissionsNeeded.get(i);
                }

                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            }
                        });
                return;
            }
            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return;
        }

        //add listeners on view
        setUpView();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(FluentActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean isPermissionGranted(List<String> permissionsList, String permission) {

        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);

            // Check for Rationale Option
            if (!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(Manifest.permission.RECORD_AUDIO, PackageManager.PERMISSION_GRANTED);

                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);

                // Check for ACCESS_FINE_LOCATION
                if (perms.get(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
                        ) {
                    // All Permissions Granted

                    //add listeners on view
                    setUpView();

                } else {
                    // Permission Denied
                    Toast.makeText(FluentActivity.this, "Some Permissions are Denied Exiting App", Toast.LENGTH_SHORT)
                            .show();

                    finish();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }













//    private void startVoiceInput() {
//        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US);
//        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Read the sentence");
//        intent.putExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES, true);
//
//
//
//        try {
//            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
//        } catch (ActivityNotFoundException a) {
//
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//            case REQ_CODE_SPEECH_INPUT: {
//                if (resultCode == RESULT_OK && null != data) {
//                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
////                    mVoiceInputTv.setText(result.get(0));
//                    str = result.get(0).toLowerCase();
//
//                    mVoiceInputTv.setText("You said :"+str);
//
////                    String h=str.toString();
////                    String s="hello" ;
////                    if (s.equals(h))
////                    {
////                        mVoiceInputTv1.setText("YOU SAID CORRECTLY");
////                    }
////                    else {
////                        mVoiceInputTv1.setText("YOU SAID WRONG");
////                    }
//                    findMatch();
//                }
//                break;
//            }
//
//        }
//    }

    public void findMatch() {

        try {

            String arr1[] = InputStr.split(" ");
            String arr2[] = str.split(" ");

            java.util.List<String> list1 = new ArrayList<String>(
                    Arrays.asList(arr1));
            java.util.List<String> list2 = new ArrayList<String>(
                    Arrays.asList(arr2));

            ArrayList<String> tmp1 = new ArrayList<String>();
            ArrayList<String> tmp2 = new ArrayList<String>();

            for (int i = 0; i < arr1.length; i++) {
                int k = 0;
                for (int j = 0; j < arr2.length; j++) {

                    if (arr1[i].equalsIgnoreCase(arr2[j])) {
                        tmp1.add(arr1[i]);
                    } else {
                        tmp2.add(arr1[i]);
                    }

                }

            }
            for (String strs : tmp1) {
                list1.remove(strs);
            }
            resstr=list1.toString().replaceAll("\\[|\\]|\\,","");
//            Toast.makeText(this, "Result " + resstr, Toast.LENGTH_SHORT).show();
            if(resstr.isEmpty())
            {
                mVoiceInputTv1.setText("Very good" + resstr);
                mVoiceInputTv1.setTypeface(typeface);
                cd.showValue(100f, 100f, true);
                Toast.makeText(getApplicationContext(),
                        "100% Accuracy", Toast.LENGTH_LONG).show();
                Random r = new Random();
                int i1 = r.nextInt(80 - 65) + 65;
                f1=(float)i1;
                d.showValue(f1, 100f, true);
            }
            else {
                mVoiceInputTv1.setText("You missed :" + resstr);
                mVoiceInputTv1.setTypeface(typeface);
                if (resstr.isEmpty() || resstr.trim().length() == 0) {

                } else {

                    cresult = (resstr.trim().split("\\s+").length);
                    float tresult = i - cresult;
                    float wtresult = (float) (tresult / i) * 100f;
                    Toast.makeText(getApplicationContext(),
                            wtresult + "% Accuracy", Toast.LENGTH_LONG).show();
                    cd.showValue(wtresult, 100f, true);
                    Random r = new Random();
                    int i1 = r.nextInt(90 - 70) + 70;
                    f1=(float)i1;
                    d.showValue(f1, 100f, true);
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }


    @Override
    public void onDestroy() {
// Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                buttonSpeak.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut() {
        tts.setPitch(1.3f);
        tts.setSpeechRate(1f);
        tts.speak(InputStr, TextToSpeech.QUEUE_FLUSH, null);
    }



}
