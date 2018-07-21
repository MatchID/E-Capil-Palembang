package com.cemerlang.ecapil_palembang;

/**
 * Created by RICHI on 2016/10/26.
 */

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
//import android.support.v7.widget.Toolbar;
//import android.view.Window;
import android.app.Activity;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Info_Display extends Activity {
    private ImageView imageView;

    private EditText editTextName,ednik,ednama;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    ProgressDialog progressDialog;

    //private String UPLOAD_URL ="http://fe-unpal.ac.id/upd/ubahElemen.php";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cemerlang.ecapil_palembang.R.layout.info);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = connMgr.getActiveNetworkInfo();

        AdView mAdView = (AdView) findViewById(com.cemerlang.ecapil_palembang.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (networkinfo != null && networkinfo.isConnected()) {
            WebView webView = (WebView)findViewById(com.cemerlang.ecapil_palembang.R.id.webView1);
            webView.setWebViewClient(new myWebClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("http://ecapilpalembang.web.id/upd/capilCari/index.html");

        }
        else{
           // WebView webView = (WebView)findViewById(R.id.webView1);
           // webView.loadUrl("file:///android_asset/info.html");
           // webView.getSettings().setJavaScriptEnabled(true);
            progressDialog = new ProgressDialog(Info_Display.this);
            progressDialog.setMessage("internet tidak konek...");
            progressDialog.show();
        }

        //file:///android_asset/satu.html
        // webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        // webView.setScrollbarFadingEnabled(false);
        //webView.getSettings().setBuiltInZoomControls(true);
        // webView.getSettings().setPluginState(PluginState.ON);
        // webView.getSettings().setAllowFileAccess(true);
        //webView.getSettings().setSupportZoom(true);



    }



    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
// TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
// TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }
    }




}