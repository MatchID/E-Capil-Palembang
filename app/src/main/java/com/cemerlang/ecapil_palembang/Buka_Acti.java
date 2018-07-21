package com.cemerlang.ecapil_palembang;

/**
 * Created by RICHI on 2016/10/26.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebView;
//import android.support.v7.widget.Toolbar;
//import android.view.Window;
import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;


public class Buka_Acti extends Activity {
    private ImageView imageView;

    private EditText editTextName,ednik,ednama;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="http://fe-unpal.ac.id/upd/e_akte_kirim.php";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    String pilihx;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(com.cemerlang.ecapil_palembang.R.layout.buka);
            imageView  = (ImageView) findViewById(com.cemerlang.ecapil_palembang.R.id.imageView);
            WebView webView = (WebView)findViewById(com.cemerlang.ecapil_palembang.R.id.webView1);
            webView.loadUrl("file:///android_asset/akte.html");

            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(new WebViewJavaScriptInterface(this), "app");



            AdView mAdView = (AdView) findViewById(com.cemerlang.ecapil_palembang.R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        public class WebViewJavaScriptInterface{

            private Context context;

            public WebViewJavaScriptInterface(Context context){
                this.context = context;
            }


            @JavascriptInterface
            public void makeToast(String message, boolean True){
                pilihx=message;
                showFileChooser();
                //Toast.makeText(context, "Sqlite : "+message, (lengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)).show();
            }
        }


    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 8, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
                        Toast.makeText(Buka_Acti.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.dismiss();
                        Toast.makeText(Buka_Acti.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String image = getStringImage(bitmap);

                String name ="test1234";
                //editTextName.getText().toString().trim();
                Map<String,String> params = new Hashtable<String, String>();

                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, pilihx);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showFileChooser() {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
                uploadImage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    }