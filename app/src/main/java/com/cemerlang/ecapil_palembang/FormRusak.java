package com.cemerlang.ecapil_palembang;

/**
 * Created by RICHI on 2016/10/21.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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


public class FormRusak extends AppCompatActivity implements View.OnClickListener  {
    private Toolbar toolbar;
    String pilih;
    private Button buttonChoose;
    private Button buttonChoose2;
    private Button buttonUpload;

    private ImageView imageView;

    private EditText editTextName,ednik,ednama,edkk,edhp;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="http://ecapilpalembang.web.id/upd/e_rusak.php";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    TextView ps1,ps2;

    String kembali ,namaFoto ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cemerlang.ecapil_palembang.R.layout.kerusakan);
        setupToolbar();

        AdView mAdView = (AdView) findViewById(com.cemerlang.ecapil_palembang.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        buttonChoose = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.buttonChoose);
        buttonChoose2 = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.buttonChoose2);


        editTextName = (EditText) findViewById(com.cemerlang.ecapil_palembang.R.id.editText);

        imageView  = (ImageView) findViewById(com.cemerlang.ecapil_palembang.R.id.imageView);

        ps1=(TextView)findViewById(com.cemerlang.ecapil_palembang.R.id.pesan1);
        ps2=(TextView)findViewById(com.cemerlang.ecapil_palembang.R.id.pesan2);

        ednik=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.editText);
        ednama=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.txtnama);
        edkk=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.NoKK);
        edhp=(EditText)findViewById(com.cemerlang.ecapil_palembang.R.id.txthp);

        buttonChoose.setOnClickListener(this);
        buttonChoose2.setOnClickListener(this);
        //buttonUpload.setOnClickListener(this);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 6, baos);
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
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(FormRusak.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(FormRusak.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name

                String name =dtk(ednik.getText().toString(),ednama.getText().toString(),edkk.getText().toString(),edhp.getText().toString(),namaFoto);
                //editTextName.getText().toString().trim();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void showFileChooser() {
        if (pilih!="1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            //buttonChoose.setText("Silakan Kirim");
            namaFoto="Foto KTP-el Rusak berhasil di upload";
            pilih = "1";
        }else  if (pilih=="1") {
            //uploadImage();
            ps1.setText("Foto KTP-el Rusak berhasil di upload");
            pilih="0";
            ///buttonChoose.setText("Foto KTP-el Rusak");
        }


    }

    private void showFileChooser2() {
        if (pilih!="1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            //buttonChoose2.setText("Silakan Kirim");
            namaFoto="Foto Kartu Keluarga Berhasil di upload";
            pilih = "1";
        }else  if (pilih=="1") {
            //uploadImage();
            ps2.setText("Foto Kartu Keluarga Berhasil di upload");
            pilih="0";
            //buttonChoose2.setText("Foto Kartu Keluarga");
            //imageView.setImageBitmap(bitmap);
           // Toast.makeText(getApplicationContext(),"Data berhasil diupload ", Toast.LENGTH_LONG).show();
            //Intent xk = new Intent(getApplicationContext(),MenuKtp.class);
            //startActivity(xk);
        }


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
                uploadImage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public  String dtk(String nik, String pemohon, String kk, String hpx, String ft){
        return kembali=nik+"-"+pemohon+"-"+kk+"-"+hpx+"-"+ft;
    }


    @Override
    public void onClick(View v) {

        if(v == buttonChoose){
         try{
             if((edkk.getText().toString().trim().length()>0) || (ednik.getText().toString().trim().length()>0)) {
                 showFileChooser();
             }
             else{
                 Toast.makeText(FormRusak.this, "Silahkan lengkapi data terlebih dahulu", Toast.LENGTH_LONG).show();
             }
        }
        catch(NullPointerException e)
        {
        }
        }
        if(v == buttonChoose2){
         try{
             if((edkk.getText().toString().trim().length()>0) || (ednik.getText().toString().trim().length()>0)) {
                 showFileChooser2();
             }
             else{
                 Toast.makeText(FormRusak.this, "Silahkan lengkapi data terlebih dahulu", Toast.LENGTH_LONG).show();
             }
        }
        catch(NullPointerException e)
        {
        }
        }

    }


    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}