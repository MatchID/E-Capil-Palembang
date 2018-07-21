package com.cemerlang.ecapil_palembang;

/**
 * Created by RICHI on 2016/10/26.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
//import android.support.v7.widget.Toolbar;
//import android.view.Window;
import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MenuUtama extends Activity {
    private ImageView imageView;

    private EditText editTextName,ednik,ednama;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="http://ecapilpalembang.web.id/upd/ubahElemen.php";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cemerlang.ecapil_palembang.R.layout.test1);

        AdView mAdView = (AdView) findViewById(com.cemerlang.ecapil_palembang.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        WebView webView = (WebView)findViewById(com.cemerlang.ecapil_palembang.R.id.webView1);
        webView.loadUrl("file:///android_asset/satu.html");
        //file:///android_asset/satu.html
       // webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
       // webView.setScrollbarFadingEnabled(false);
        //webView.getSettings().setBuiltInZoomControls(true);
       // webView.getSettings().setPluginState(PluginState.ON);
       // webView.getSettings().setAllowFileAccess(true);
        //webView.getSettings().setSupportZoom(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebViewJavaScriptInterface(this), "app");
    }

    public class WebViewJavaScriptInterface{

        private Context context;

        public WebViewJavaScriptInterface(Context context){
            this.context = context;
        }


        @JavascriptInterface
                 public void makeToast(String message, boolean True){
            Intent ak = new Intent(getApplicationContext(), MenuKtp.class);
            startActivity(ak);
            //Toast.makeText(context, "Sqlite : "+message, (lengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)).show();
        }

        @JavascriptInterface
        public void makeAkte(String message, boolean True){
            Intent ak = new Intent(getApplicationContext(), AkteMain.class);
            startActivity(ak);
            //Toast.makeText(context, "Sqlite : "+message, (lengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)).show();
        }

        @JavascriptInterface
        public void makeInfo(String message, boolean True){
            Intent ak = new Intent(getApplicationContext(), MenuPindah.class);
            startActivity(ak);
            //Toast.makeText(context, "Sqlite : "+message, (lengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)).show();
        }

        @JavascriptInterface
        public void makeUmum(String message, boolean True){
            Intent ak = new Intent(getApplicationContext(), Info_Display.class);
            startActivity(ak);
            //Toast.makeText(context, "Sqlite : "+message, (lengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)).show();
        }

    }






}