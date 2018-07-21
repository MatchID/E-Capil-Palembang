package com.cemerlang.ecapil_palembang;

/**
 * Created by RICHI on 2016/10/31.
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



/**
 * Created by RICHI on 2016/10/21.
 */


public class AkteMain extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    String pilih;
    private Button buttonChoose;
    private Button buttonChoose2;
    private Button btn_KTPIbu;
    private Button btn_BukuNikah1, btn_BukuNikah2, btn_BukuNikah3, btn_Formulir, bt_SKL, bt_SPTJM, bt_SPTJM_Istri, btn_Formulir2, btn_Formulir3, btn_Formulir4;
    private Button buttonUpload;

    private Button bt_BukuAyah1, bt_BukuAyah2, bt_BukuAyah3;

    private ImageView imageView;

    private EditText nmMohon, editTextName, ednik, ednama, edkk, edhp, edibu, edttl, edalamat,TxtTelpon;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL = "http://ecapilpalembang.web.id/upd/akte_baru.php";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

    String nmFoto;

    TextView ps1, ps2;

    String kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cemerlang.ecapil_palembang.R.layout.akte_main);
        setupToolbar();


        buttonChoose = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btKK);
        buttonChoose2 = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btKTPAyah);
        btn_KTPIbu = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btKTPIbu);
        btn_BukuNikah1 = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btBukuNikah1);
        btn_BukuNikah2 = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btBukuNikah2);
        btn_BukuNikah3 = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btBukuNikah3);

        // bt_BukuAyah1= (Button) findViewById(R.id.btBukuAyah1);
        //bt_BukuAyah2= (Button) findViewById(R.id.btBukuAyah2);
        // bt_BukuAyah3= (Button) findViewById(R.id.btBukuAyah3);

        btn_Formulir = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btnFormulir);

        bt_SKL = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btSKL);
        bt_SPTJM = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btSPTJM);
        bt_SPTJM_Istri = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btSPTJM_Istri);

        btn_Formulir2 = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btnFormulir2);
        btn_Formulir3 = (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btnFormulir3);
        //editTextName = (EditText) findViewById(R.id.txtNoKK);

        btn_Formulir4= (Button) findViewById(com.cemerlang.ecapil_palembang.R.id.btnFormulir4);

        imageView = (ImageView) findViewById(com.cemerlang.ecapil_palembang.R.id.imageView);

        //ps1=(TextView)findViewById(R.id.pesan1);
        //ps2=(TextView)findViewById(R.id.pesan2);

        btn_Formulir4.setVisibility(View.INVISIBLE);


        ednik = (EditText) findViewById(com.cemerlang.ecapil_palembang.R.id.txtNIK);
        ednama = (EditText) findViewById(com.cemerlang.ecapil_palembang.R.id.txtNamaPemohon);
        edkk = (EditText) findViewById(com.cemerlang.ecapil_palembang.R.id.txtNoKK);
        edhp = (EditText) findViewById(com.cemerlang.ecapil_palembang.R.id.txtNamaAyah);
        edibu = (EditText) findViewById(com.cemerlang.ecapil_palembang.R.id.txtNamaIbu);
        edttl = (EditText) findViewById(com.cemerlang.ecapil_palembang.R.id.txtTTL);
        edalamat = (EditText) findViewById(com.cemerlang.ecapil_palembang.R.id.txtAlamat);
        TxtTelpon = (EditText) findViewById(com.cemerlang.ecapil_palembang.R.id.txtTelpon);

        buttonChoose.setOnClickListener(this);
        buttonChoose2.setOnClickListener(this);
        btn_KTPIbu.setOnClickListener(this);

        btn_BukuNikah1.setOnClickListener(this);
        btn_BukuNikah2.setOnClickListener(this);
        btn_BukuNikah3.setOnClickListener(this);

        // bt_BukuAyah1.setOnClickListener(this);
        // bt_BukuAyah2.setOnClickListener(this);
        // bt_BukuAyah3.setOnClickListener(this);

        //buttonUpload.setOnClickListener(this);

        bt_SKL.setOnClickListener(this);

        btn_Formulir.setOnClickListener(this);
        bt_SPTJM.setOnClickListener(this);
        bt_SPTJM_Istri.setOnClickListener(this);

        btn_Formulir2.setOnClickListener(this);
        btn_Formulir3.setOnClickListener(this);
        btn_Formulir4.setOnClickListener(this);

    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 4, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(AkteMain.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(AkteMain.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name

                String name = dtk(ednik.getText().toString(), ednama.getText().toString(), edkk.getText().toString(), edhp.getText().toString()
                        , edibu.getText().toString(), edttl.getText().toString(), edalamat.getText().toString(), nmFoto,TxtTelpon.getText().toString()  );
                //editTextName.getText().toString().trim();

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

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
        if (pilih != "1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            //buttonChoose.setText("Silakan Kirim");
            pilih = "2";
            nmFoto = "Foto KK";
        } else if (pilih == "1") {
            //nmFoto = "Foto KK";
            //uploadImage();
            //ps1.setText("Foto KK berhasil di upload");
            pilih = "0";
            buttonChoose.setText("Foto Sudah dikirim  ");

        }


    }

    private void showFileChooser2() {
        if (pilih != "1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            //buttonChoose2.setText("Silakan Kirim");
            pilih = "1";
            nmFoto = "KTP Ayah";
        } else if (pilih == "1") {
           // nmFoto = "KTP Ayah";
           // uploadImage();
            // ps2.setText("Foto KTPAyah Berhasil di upload");
            pilih = "2";
            buttonChoose2.setText("Foto Sudah dikirim  ");
            //imageView.setImageBitmap(bitmap);
            //Toast.makeText(getApplicationContext(), "Data berhasil diupload ", Toast.LENGTH_LONG).show();
            // Intent xk = new Intent(getApplicationContext(),MenuKtp.class);
            //startActivity(xk);
        }


    }


    private void showFileChooser3() {
        if (pilih != "1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
           // btn_KTPIbu.setText("Silakan Kirim");
            pilih = "1";
            nmFoto = "KTP Ibu";
        } else if (pilih == "1") {
            //nmFoto = "KTP Ibu";
            //uploadImage();
            // ps2.setText("Foto KTPAyah Berhasil di upload");
            pilih = "2";
            btn_KTPIbu.setText("Foto Sudah dikirim  ");
            //imageView.setImageBitmap(bitmap);
            //Toast.makeText(getApplicationContext(), "Data berhasil diupload ", Toast.LENGTH_LONG).show();
            //Intent xk = new Intent(getApplicationContext(),MenuKtp.class);
            //startActivity(xk);
        }


    }


    private void showFileChooser4() {
        if (pilih != "1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            //btn_BukuNikah1.setText("Silakan Kirim");
            pilih = "1";
            nmFoto = "Buku Nikah 1";
        } else if (pilih == "1") {
            //nmFoto = "Buku Nikah 1";
            //uploadImage();
            // ps2.setText("Foto KTPAyah Berhasil di upload");
            pilih = "2";
            btn_BukuNikah1.setText("Foto Sudah dikirim  ");

            //imageView.setImageBitmap(bitmap);
            //Toast.makeText(getApplicationContext(), "Data berhasil diupload ", Toast.LENGTH_LONG).show();
            //Intent xk = new Intent(getApplicationContext(),MenuKtp.class);
            //startActivity(xk);
        }


    }


    private void showFileChooser5() {
        if (pilih != "1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
           // btn_BukuNikah2.setText("Silakan Kirim");
            pilih = "1";
            nmFoto = "Buku Nikah 2";
        } else if (pilih == "1") {
            //nmFoto = "Buku Nikah 2";
            //uploadImage();
            // ps2.setText("Foto KTPAyah Berhasil di upload");
            //pilih = "0";
           btn_BukuNikah2.setText("Foto Sudah dikirim  ");
            pilih = "2";
            //imageView.setImageBitmap(bitmap);
            //Toast.makeText(getApplicationContext(), "Data berhasil diupload ", Toast.LENGTH_LONG).show();
            //Intent xk = new Intent(getApplicationContext(),MenuKtp.class);
            //startActivity(xk);
        }


    }


    private void showFileChooser6() {
        if (pilih != "1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            //btn_BukuNikah3.setText("Silakan Kirim");
            pilih = "1";
            nmFoto = "Buku Nikah 3";
        } else if (pilih == "1") {
            //nmFoto = "Buku Nikah 3";
            //uploadImage();
            // ps2.setText("Foto KTPAyah Berhasil di upload");
            //pilih = "0";
            btn_BukuNikah3.setText("Foto Sudah dikirim");
            pilih = "2";

            //imageView.setImageBitmap(bitmap);
           // Toast.makeText(getApplicationContext(), "Data berhasil diupload ", Toast.LENGTH_LONG).show();
            //Intent xk = new Intent(getApplicationContext(),MenuKtp.class);
            //startActivity(xk);
        }


    }


    private void showFileChooser7() {
        if (pilih != "1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            //bt_BukuAyah2.setText("Silakan Kirim");
            pilih = "1";
            nmFoto = "Buku Ayah Nikah 1";
        } else if (pilih == "1") {
            //nmFoto = "BBuku Ayah Nikah 1";
            //uploadImage();
            // ps2.setText("Foto KTPAyah Berhasil di upload");
            pilih = "2";
            bt_BukuAyah1.setText("Foto Sudah dikirim");
            //imageView.setImageBitmap(bitmap);
           //Toast.makeText(getApplicationContext(), "Data berhasil diupload ", Toast.LENGTH_LONG).show();
            //Intent xk = new Intent(getApplicationContext(),MenuKtp.class);
            //startActivity(xk);
        }


    }

    private void showFileChooser8() {
        if (pilih != "1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
           // bt_BukuAyah2.setText("Silakan Kirim");
            pilih = "1";
            nmFoto = "Buku Ayah Nikah 2";
        } else if (pilih == "1") {
           // nmFoto = "Buku Ayah Nikah 2";
           /// uploadImage();
            // ps2.setText("Foto KTPAyah Berhasil di upload");
            pilih = "2";
            bt_BukuAyah2.setText("Foto Sudah dikirim");
            //imageView.setImageBitmap(bitmap);
           // Toast.makeText(getApplicationContext(), "Data berhasil diupload ", Toast.LENGTH_LONG).show();
            //Intent xk = new Intent(getApplicationContext(),MenuKtp.class);
            //startActivity(xk);
        }


    }

    private void showFileChooser9() {
        if (pilih != "1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            //bt_BukuAyah3.setText("Silakan Kirim");
            pilih = "1";
            nmFoto = "Buku Ayah Nikah 3";
        } else if (pilih == "1") {
           // nmFoto = "Buku Ayah Nikah 3";
           // uploadImage();
            // ps2.setText("Foto KTPAyah Berhasil di upload");
             pilih = "0";
            bt_BukuAyah3.setText("Foto Sudah dikirim");
            pilih = "2";
            //imageView.setImageBitmap(bitmap);
            //Toast.makeText(getApplicationContext(), "Data berhasil diupload ", Toast.LENGTH_LONG).show();
            //Intent xk = new Intent(getApplicationContext(),MenuKtp.class);
            //startActivity(xk);
        }
    }


    private void showFileChooser10() {
        if (pilih != "1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            //bt_SKL.setText("Silakan Kirim");
            pilih = "1";
            nmFoto = "SKL";
        } else if (pilih == "1") {
            //nmFoto = "SKL";
            //uploadImage();
            // ps2.setText("Foto KTPAyah Berhasil di upload");

            bt_SKL.setText("Foto Sudah dikirim  ");
            pilih = "2";
            //imageView.setImageBitmap(bitmap);
           // Toast.makeText(getApplicationContext(), "Data berhasil diupload ", Toast.LENGTH_LONG).show();
            //Intent xk = new Intent(getApplicationContext(),MenuKtp.class);
            //startActivity(xk);
        }
    }


    private void showFileChooser11() {
        if (pilih != "1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            //bt_SPTJM.setText("Silakan Kirim");
            pilih = "1";
            nmFoto = "SPTJM";
        } else if (pilih == "1") {
            //nmFoto = "SPTJM";
            //uploadImage();
            // ps2.setText("Foto KTPAyah Berhasil di upload");

            bt_SPTJM.setText("Foto Sudah dikirim  ");
            pilih = "2";
            //imageView.setImageBitmap(bitmap);
            //Toast.makeText(getApplicationContext(), "Data berhasil diupload ", Toast.LENGTH_LONG).show();
            //Intent xk = new Intent(getApplicationContext(),MenuKtp.class);
            //startActivity(xk);
        }
    }


    private void showFileChooser12() {
        if (pilih != "1") {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            //bt_SPTJM_Istri.setText("Silakan Kirim");
            pilih = "1";
            nmFoto = "SPTJM   Istri";
        } else if (pilih == "1") {
            //nmFoto = "SPTJM Istri  ";
            //uploadImage();
            // ps2.setText("Foto KTPAyah Berhasil di upload");
           // pilih = "0";
            pilih = "2";
            bt_SPTJM_Istri.setText("Foto Sudah dikirim");
            //imageView.setImageBitmap(bitmap);
           // Toast.makeText(getApplicationContext(), "Data berhasil diupload ", Toast.LENGTH_LONG).show();
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

    public String dtk(String nik, String pemohon, String kk, String hpx, String ibu, String ttl, String alamat, String ft,String hp) {
        return kembali = nik + "--" + pemohon + "--" + kk + "--" + hpx + "--" + ibu + "--" + ttl + "--" + alamat + "--" + ft +"--"+ hp;
    }


    @Override
    public void onClick(View v) {

        if (v == buttonChoose) {
            try{
            showFileChooser();
            }
            catch(NullPointerException e)
            {            }
        }
        if (v == buttonChoose2) {
            try{
            showFileChooser2();
        }
        catch(NullPointerException e)
        {            }
        }

        if (v == btn_KTPIbu) {
            try{
            showFileChooser3();
        }
        catch(NullPointerException e)
        {            }
        }
        if (v == btn_BukuNikah1) {
            try{
            showFileChooser4();
        }
        catch(NullPointerException e)
        {            }
        }
        if (v == btn_BukuNikah2) {
            try{
            showFileChooser5();
        }
        catch(NullPointerException e)
        {            }
        }
        if (v == btn_BukuNikah3) {
            try{
            showFileChooser6();
        }
        catch(NullPointerException e)
        {            }
        }

        if (v == bt_SKL) {
            try{
            showFileChooser10();
        }
        catch(NullPointerException e)
        {            }
        }
        if (v == bt_SPTJM) {
            try{
            showFileChooser11();
        }
        catch(NullPointerException e)
        {            }
        }
        if (v == bt_SPTJM_Istri) {
            try{
            showFileChooser12();
        }
        catch(NullPointerException e)
        {            }
        }


            if (v == btn_Formulir2) {
                Uri uri = Uri.parse("http://ecapilpalembang.web.id/upd/download/sptjm1.php");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
            if (v == btn_Formulir3) {
                Uri uri = Uri.parse("http://ecapilpalembang.web.id/upd/download/sptjm2.php");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        if (v == btn_Formulir) {
            Uri uri = Uri.parse("http://ecapilpalembang.web.id/upd/download/formulir.php");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

        if (v == btn_Formulir4) {
            Uri uri = Uri.parse("http://ecapilpalembang.web.id/upd/download/formulir.php");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

        }



    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    }
