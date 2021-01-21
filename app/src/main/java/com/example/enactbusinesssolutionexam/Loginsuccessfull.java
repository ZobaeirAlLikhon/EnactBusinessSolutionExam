package com.example.enactbusinesssolutionexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Loginsuccessfull extends AppCompatActivity {
    TextView t2,t3,t4,t5;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("User Details");
        setContentView(R.layout.activity_loginsuccessfull);
        t2=findViewById(R.id.textView1);
        t3=findViewById(R.id.textView2);
        t4=findViewById(R.id.textView3);
        t5=findViewById(R.id.textView4);

        bundle = getIntent().getExtras();
        String name=bundle.getString("name");
        String deg=bundle.getString("deg");
        String inst=bundle.getString("inst");
        String mobile=bundle.getString("mobile");
        t2.setText("Name: "+name);
        t3.setText("Designation: "+deg);
        t4.setText("Institute: "+inst);
        t5.setText("Mobile: "+mobile);

    }
}