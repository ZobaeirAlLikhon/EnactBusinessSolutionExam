package com.example.enactbusinesssolutionexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MedicineDatails extends AppCompatActivity {
    private Bundle bundle;
    String name,pharm,sideEff,prec,strog;
    TextView nameTV,phrmTv,sideEffTv,precTv,strogeTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_datails);
        getSupportActionBar().setTitle("Medicine Details");
        nameTV=findViewById(R.id.mediName);
        phrmTv=findViewById(R.id.pharmacology);
        sideEffTv=findViewById(R.id.sideEffect);
        precTv=findViewById(R.id.precautions);
        strogeTv=findViewById(R.id.storage);
        bundle = getIntent().getExtras();
        name=bundle.getString("name");
        pharm=bundle.getString("pharmacology");
        sideEff=bundle.getString("sideEffect");
        prec=bundle.getString("precautions");
        strog=bundle.getString("storage");
        nameTV.setText("Medicine Name: "+name);
        phrmTv.setText("Pharmacology: "+pharm);
        strogeTv.setText("Storage: "+strog);
        precTv.setText("Precautions: "+prec);
        sideEffTv.setText("SideEffect: "+sideEff);

    }
}