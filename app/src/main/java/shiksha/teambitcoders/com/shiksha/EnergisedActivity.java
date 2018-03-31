package shiksha.teambitcoders.com.shiksha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EnergisedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energised);
    }

    public void genn(View v)
    {
        Intent i=new Intent(getApplicationContext(),BarcodeViewActivity.class);
        startActivity(i);
    }
}
