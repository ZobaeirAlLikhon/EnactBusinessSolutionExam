package com.example.enactbusinesssolutionexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.enactbusinesssolutionexam.apiInterface.ApiClint;
import com.example.enactbusinesssolutionexam.apiInterface.ApiInterface;
import com.example.enactbusinesssolutionexam.model.LoginModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginWithAPI extends AppCompatActivity {
    EditText userName,pass;
    Button signIn;
    ApiInterface apiInterface;
    String n,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_s_q_l);
        userName=findViewById(R.id.usernameAPI);
        pass=findViewById(R.id.passwordAPI);
        signIn=findViewById(R.id.btnSignInAPI);


        apiInterface= ApiClint.getClient().create(ApiInterface.class);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=userName.getText().toString().trim();
                p=pass.getText().toString().trim();
                Log.e("number:",n+" "+p);
                signInwithAPI();
            }
        });

    }

    private void signInwithAPI() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mobile",n);
        jsonObject.addProperty("password",p);


        Call<LoginModel> call=apiInterface.login(jsonObject);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Toast.makeText(LoginWithAPI.this,"Log in Succussfully.....",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(LoginWithAPI.this, Loginsuccessfull.class);
                intent.putExtra("name",response.body().getName());
                intent.putExtra("mobile",response.body().getMobile());
                intent.putExtra("deg",response.body().getDesignation());
                intent.putExtra("inst",response.body().getInstitute());
                startActivity(intent);
                Log.e("Log in with api:",response.body().getMessage().toString());
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.e("Log in with api:", t.toString());
            }
        });
    }
}