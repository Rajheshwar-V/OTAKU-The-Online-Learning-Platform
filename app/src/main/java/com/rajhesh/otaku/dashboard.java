package com.rajhesh.otaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class dashboard extends AppCompatActivity {
    private WebView webview;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
      //  intent = new Intent(this, messagelist.class);

        webview = (WebView) findViewById(R.id.dashboardwebview);
        webview.setWebViewClient(new WebViewClient());
            webview.loadUrl("https://dash-97e7a.web.app/ ");

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });



    }


    public void message(View view) {
        if(view.getId()==R.id.imageButton1){
            startActivity(new Intent(this, messagelist.class));
        }
        else if(view.getId()==R.id.imageButton2){
            startActivity(new Intent(this, search.class));
            Toast.makeText(this, "Module Under Developement", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId()==R.id.imageButton3){
            startActivity(new Intent(this, notification.class));
        }
        else if(view.getId()==R.id.imageButton4){
            startActivity(new Intent(this, profiledetails.class));
        }
    }
}