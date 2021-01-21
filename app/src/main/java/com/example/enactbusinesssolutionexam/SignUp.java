package com.example.enactbusinesssolutionexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.enactbusinesssolutionexam.helper.DBHelper;

public class SignUp extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;
    Switch aSwitch;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        preferences= PreferenceManager.getDefaultSharedPreferences(SignUp.this);
        SharedPreferences.Editor editor = preferences.edit();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        aSwitch=findViewById(R.id.simpleSwitchreg);
        editor.putString("lang","en");
        editor.apply();
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    editor.putString("lang","bn");
                    editor.apply();
//                    context= LocaleHelper.setLocale(LogIn.this,"bn-rBD");
//                    resources=context.getResources();
                    username.setHint("ব্যবহারকারীর নাম");
                    password.setHint("পাসওয়ার্ড");
                    repassword.setHint("আবার পাসওয়ার্ড");
                    signup.setText("নিবন্ধন করুন");
                    signin.setText("বিব্যবহারকারি দ্যমান? সাইন ইন পৃষ্ঠাতে যান");


//                    Locale locale = new Locale("hi");
//                    Locale.setDefault(locale);
//                    Configuration config = new Configuration();
//                    config.locale = locale;
//                    getResources().updateConfiguration(config,getResources().getDisplayMetrics());

                    Toast.makeText(SignUp.this,aSwitch.getTextOn().toString(),Toast.LENGTH_SHORT).show();

                }
                if(isChecked==false){
                    editor.putString("lang","en");
                    editor.apply();
//                    setLocal("en");
                    username.setHint("User Name");
                    password.setHint("Password");
                    repassword.setHint("rePassword");
                    signup.setText("Sign Up");
                    signin.setText("Existing user! Go to Sign in page");
                    editor.putString("lang","en");
                    editor.apply();
                    Toast.makeText(SignUp.this,aSwitch.getTextOff().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(SignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LogIn.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignUp.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUp.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
            }
        });
    }
}