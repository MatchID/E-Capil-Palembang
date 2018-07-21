package com.cemerlang.ecapil_palembang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/// referensi dari internet https://www.simplifiedcoding.net/android-upload-image-to-server-using-php-mysql/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    String ret;
    public static final String UPLOAD_URL = "http://ecapilpalembang.web.id/upd/baru.php";
    public static final String UPLOAD_KEY = "image";
    public static final String UPLOAD_ket = "ket";
    public static final String TAG = "MY MESSAGE";

    private int PICK_IMAGE_REQUEST = 1;

    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView;
    private ImageView imageView;

    private Bitmap bitmap;

    private Uri filePath;

    EditText nik,nama,alamat,hp,rt,rw,kdpos,nhp,edkk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cemerlang.ecapil_palembang.R.layout.activity_main);
        setupToolbar();

        AdView mAdView = (AdView) findViewById(com.cemerlang.ecapil_palembang.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        nik=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.txtnik);
        nama=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.txtnama);
        alamat=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.txtalamat);
        hp=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.txthp);
        rt=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.txtrt);
        rw=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.txtrw);
        kdpos=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.txtkdpos);
        nhp=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.txthp);
        edkk=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.txtnokk);


        buttonChoose = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.buttonChoose);
        buttonUpload = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.buttonUpload);
       // buttonView = (Button) findViewById(R.id.buttonViewImage);

        imageView = (ImageView) findViewById(com.cemerlang.ecapil_palembang.R.id.imageView);

         buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
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

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Uploading Image", "Please wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);



                String nmkirim =nik.getText().toString()+"--"+nama.getText().toString()+"--"+alamat.getText().toString()+"--"+hp.getText().toString()+"--"+rt.getText().toString()+"--"+rw.getText().toString()+"--"+kdpos.getText().toString()+"--"+nhp.getText().toString() +"--"+ edkk.getText().toString();
                HashMap<String,String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);
                data.put(UPLOAD_ket, nmkirim);

                String result = rh.sendPostRequest(UPLOAD_URL,data);

                    return result;

            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }


public    String kirimx(String nik,String nama, String alamat, String nohp){
        ret=nik+"--"+nama+"--"+alamat+"--"+nohp;
        return ret;
    }


    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }
        if(v == buttonUpload){

            if( (nik.getText().toString().trim().length()>0) ||  (edkk.getText().toString().trim().length()>0)    ){
                uploadImage();

            }
            else {
                Toast.makeText(MainActivity.this, "Silahkan lengkapi data terlebih dahulu", Toast.LENGTH_LONG).show();

            }
        }
    }


    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}