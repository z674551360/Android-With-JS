package nz.co.getunified.android_with_js;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private WebView myWebView;
    private TextView myTv;
    private Button jsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setWebView();
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    private void setWebView() {
        myWebView = findViewById(R.id.web);
        myTv = findViewById(R.id.textview);
        jsBtn = findViewById(R.id.button);

        //JavaScript chinese stuff
        WebSettings webSettings = myWebView.getSettings();
        // chinese Javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);



        myWebView.addJavascriptInterface(this, "my");

        jsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.loadUrl("javascript:javacalljst()");
                myWebView.loadUrl("javascript:javacalljswithargst(" + "'First arg' + 'Second arg'" + ")");
            }
        });
    }

    @JavascriptInterface
    public void startFunction() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myTv.setText(myTv.getText() + "\njs use java");
            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myTv.setText(myTv.getText() + "\njs use java with parameters:" + str);
            }
        });
    }
}
