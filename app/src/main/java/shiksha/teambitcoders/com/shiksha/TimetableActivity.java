package shiksha.teambitcoders.com.shiksha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class TimetableActivity extends AppCompatActivity {

    WebView webview;
    String s,checkString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        s="https://firebasestorage.googleapis.com/v0/b/shiksha-a2832.appspot.com/o/time.docx?alt=media&token=b98d353b-5728-46d8-922c-1b31a2d67cc9";
        webview= (WebView) findViewById(R.id.webbb1);


        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.loadUrl("https://docs.google.com/viewer?url="
                +s+".pdf");

//        webview.loadUrl(s);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webview, WebResourceRequest request) {
                webview.loadUrl(request.toString());
                return true;
            }
        });

    }
}
