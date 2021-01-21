package com.example.enactbusinesssolutionexam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.enactbusinesssolutionexam.adaptar.GetMedicineAdaptar;
import com.example.enactbusinesssolutionexam.apiInterface.ApiClint;
import com.example.enactbusinesssolutionexam.apiInterface.ApiInterface;
import com.example.enactbusinesssolutionexam.model.GetModel;
import com.example.enactbusinesssolutionexam.model.Medicine;
import com.example.enactbusinesssolutionexam.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediList extends AppCompatActivity {
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    List<Medicine> getModelList;
    GetMedicineAdaptar getMedicineAdaptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medi_list);
        getSupportActionBar().setTitle("Medicine Name");
        recyclerView=findViewById(R.id.recyclarViewMedicine);
        recyclerView.setLayoutManager(new LinearLayoutManager(MediList.this,LinearLayoutManager.VERTICAL,false));
        apiInterface= ApiClint.getClient().create(ApiInterface.class);
        Call<GetModel> call=apiInterface.getMovie();
        call.enqueue(new Callback<GetModel>() {
            @Override
            public void onResponse(Call<GetModel> call, Response<GetModel> response) {
                getModelList=response.body().getMedicines();
                getMedicineAdaptar=new GetMedicineAdaptar(getApplicationContext(),getModelList);
                recyclerView.setAdapter(getMedicineAdaptar);
                Log.e("Get",response.body().getMedicines().get(0).getDrugs().toString());
            }

            @Override
            public void onFailure(Call<GetModel> call, Throwable t) {
                Log.e("error",t.toString());

            }
        });
    }
}