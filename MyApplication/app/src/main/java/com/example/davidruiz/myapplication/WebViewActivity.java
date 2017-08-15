package com.example.davidruiz.myapplication;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.webView);
        //webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        String host = "https://3c37ca43.ngrok.io/";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYWlzIjoiUEUiLCJjb2RpZ29Vc3VhcmlvIjoiMDMyNjEwMDk5IiwiRXNBcHBNb2JpbGUiOnRydWUsIlBhZ2luYSI6IlBFRElETyIsIklkZW50aWZpZXIiOiJGSU9SRUxMQS01NGIzLWU3YzctMDAwMC0wMDAwNDZiZmZkOTcifQ.1AEXg5P5ASfqF6GUrQr4ub_EGOv9KCeWH6cC5opr2J4";
        final String url = host + "/Login/IngresoExterno?token=" + token;

        startWebView(url);

    }

    private void startWebView(final String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        //WebChromeClient client = new WebChromeClient();
        //client.onConsoleMessage("This is ", );

        webView.setWebChromeClient(new WebChromeClient(){
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                Log.d("MyApplication ", message + " -- From line "
                        + lineNumber + " of "
                        + sourceID);
            }
        });

        // Javascript inabled on webview
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        //webView.getSettings().setAppCacheEnabled(true);
        //webView.getSettings().setPluginsEnabled(true);
        //webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webView.setFocusable(true);


        webView.setWebViewClient(new WebViewClient() {

            @Deprecated
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.w("WebActivity", "Error loading page " + description);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(getBaseContext(), "TERMINO", Toast.LENGTH_SHORT).show();
            }
        });


        // Other webview options

        /*
         String summary = "<html><body>You scored <b>192</b> points.</body></html>";
         webview.loadData(summary, "text/html", null);
         */

        //Load url in webview
        webView.loadUrl(url);


    }

    // Open previous opened link from history on webview when back button pressed

    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }

}
