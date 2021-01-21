package com.example.enactbusinesssolutionexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.enactbusinesssolutionexam.apiInterface.ApiClint;
import com.example.enactbusinesssolutionexam.apiInterface.ApiInterface;
import com.example.enactbusinesssolutionexam.constant.Constant;
import com.example.enactbusinesssolutionexam.model.Model;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    private static final int MY_PERMISSION_CONSTANT = 100;
    ImageView imageView;
    private Button pickBtn,upBtn;
    ApiInterface apiInterface;
    Uri imgUri;
    private String FilePathStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        pickBtn=findViewById(R.id.button1);
        upBtn=findViewById(R.id.button2);
        apiInterface= ApiClint.getClient().create(ApiInterface.class);
        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermisssionForReadStorage()) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
                }else {
                    Toast.makeText(MainActivity.this, "Please Give Permission!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadeImage();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {

            try {
                imgUri=data.getData();
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(),imgUri);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadeImage() {

//        MultipartBody.Part filePart;

//        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//        String image= Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

//        File file = FileUtils.getFile(this,uri);
        File file = new File(Constant.getRealPathFromUri(this,imgUri));
        RequestBody reqFile = RequestBody.create(MediaType.parse(getContentResolver().getType(imgUri)), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", file.getName(), reqFile);
        Log.e("path",Constant.getRealPathFromUri(this,imgUri));
        Log.e("image file",file.toString());
        Log.e("file Name",file.getName());
        Call<Model> call=apiInterface.uploadeImage(body);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Toast.makeText(MainActivity.this,"Uploded cpmplite.........",Toast.LENGTH_LONG).show();
                Log.e("mgs",response.body().message);
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });
    }
    public boolean checkPermisssionForReadStorage() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                ||

                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
        ) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)


            ) {


                ActivityCompat.requestPermissions(this,
                        new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSION_CONSTANT);

            } else {

                //explain("Please Allow Location Permission");
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSION_CONSTANT);
            }
            return false;
        } else {

            //  explain("Please Allow Location Permission");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_CONSTANT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0) {
                    boolean read_external_storage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean write_external_storage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (read_external_storage && write_external_storage) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
                    } else {
                        Toast.makeText(this, " permission denied, boo! Disable the functionality that depends on this permission.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "  permission denied, boo! Disable the functionality that depends on this permission.", Toast.LENGTH_SHORT).show();
                }
                // return;
            }
        }
    }


    public void login(View view) {
        Intent signUp=new Intent(MainActivity.this,SignUp.class);
        startActivity(signUp);
    }

    public void mediList(View view) {
        Intent medi=new Intent(MainActivity.this,MediList.class);
        startActivity(medi);
    }

    public void calclutor(View view) {
        Intent medi=new Intent(MainActivity.this,Calclutor.class);
        startActivity(medi);
    }

    public void loginSQL(View view) {
        Intent medi=new Intent(MainActivity.this, LoginWithAPI.class);
        startActivity(medi);
    }
}