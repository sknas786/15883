package shiksha.teambitcoders.com.shiksha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BarcodeViewActivity extends AppCompatActivity {

    WebView webview;
    String s,checkString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_view);
        s="https://firebasestorage.googleapis.com/v0/b/shiksha-a2832.appspot.com/o/qr1sql.pdf?alt=media&token=0421662f-8f24-4c64-9f4a-e00d086f6864";
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
