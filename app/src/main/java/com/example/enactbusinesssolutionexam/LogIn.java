package com.example.enactbusinesssolutionexam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enactbusinesssolutionexam.constant.LocaleHelper;
import com.example.enactbusinesssolutionexam.helper.DBHelper;

import java.util.Locale;

public class LogIn extends AppCompatActivity {
    EditText userName,pass;
    Button signIn,signUp;
    String name,password;
    Switch aSwitch;
    TextView textView,textAcc;
    Context context;
    Resources resources;
    EditText user,passw;
    DBHelper DB;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
//        loadLocal();
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("lang","");
//        Toast.makeText(LogIn.this,name,Toast.LENGTH_LONG).show();

        getSupportActionBar().setTitle("Sign in");
        textView=findViewById(R.id.wellcome);
        user=findViewById(R.id.username);
        passw=findViewById(R.id.password);
        
        userName=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        textAcc=findViewById(R.id.noAcc);
        signIn=findViewById(R.id.btnSignIn);
        signUp=findViewById(R.id.btnSignUp);
        DB = new DBHelper(this);
        if(name.trim()=="bn")
        {
            textView.setText("স্বাগত");
            user.setHint("ব্যবহারকারীর নাম");
            passw.setHint("পাসওয়ার্ড");
            signIn.setText("লগ ইন করুন");
            signUp.setText("সাইন ইন করুন");
            textAcc.setText("আপনার কোন অ্যাকাউন্ট নেই?");

        }
        if(name.trim()=="en")
        {
            textView.setText("Welcome");
            user.setHint("User Name");
            passw.setHint("Password");
            signIn.setText("Sign In");
            signUp.setText("Sign Up");
            textAcc.setText("You have no account?");
        }

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });
//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked)
//                {
////                    context= LocaleHelper.setLocale(LogIn.this,"bn-rBD");
////                    resources=context.getResources();
//                    textView.setText("স্বাগত");
//                    user.setHint("ব্যবহারকারীর নাম");
//                    passw.setHint("পাসওয়ার্ড");
//                    signIn.setText("লগ ইন করুন");
//                    signUp.setText("সাইন ইন করুন");
//                    textAcc.setText("আপনার কোন অ্যাকাউন্ট নেই?");
////                    Locale locale = new Locale("hi");
////                    Locale.setDefault(locale);
////                    Configuration config = new Configuration();
////                    config.locale = locale;
////                    getResources().updateConfiguration(config,getResources().getDisplayMetrics());
//
//                    Toast.makeText(LogIn.this,aSwitch.getTextOn().toString(),Toast.LENGTH_SHORT).show();
//
//                }
//                if(isChecked==false){
////                    setLocal("en");
//                    textView.setText("Welcome");
//                    user.setHint("User Name");
//                    passw.setHint("Password");
//                    signIn.setText("Sign In");
//                    signUp.setText("Sign Up");
//                    textAcc.setText("You have no account?");
//                    Toast.makeText(LogIn.this,aSwitch.getTextOff().toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }

//    private void setLocal(String toString) {
//        Locale locale=new Locale(toString);
//        Locale.setDefault(locale);
//        Configuration config=new Configuration();
//        config.locale=locale;
//        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
//        SharedPreferences.Editor editor=getSharedPreferences("Setting",MODE_PRIVATE).edit();
//        editor.putString("My Lang",toString);
//        editor.apply();
//    }
//    public void loadLocal()
//    {
//        SharedPreferences preferences= (SharedPreferences) getSharedPreferences("Setting", Activity.MODE_PRIVATE).edit();
//        String lang=preferences.getString("My Lang","");
//        setLocal(lang);
//
//    }

    private void SignIn() {
        name=userName.getText().toString();
        password=pass.getText().toString();
        if(name.equals("")||password.equals(""))
            Toast.makeText(LogIn.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        else{
            Boolean checkuserpass = DB.checkusernamepassword(name, password);
            if(checkuserpass==true){
                Toast.makeText(LogIn.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(LogIn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }
}