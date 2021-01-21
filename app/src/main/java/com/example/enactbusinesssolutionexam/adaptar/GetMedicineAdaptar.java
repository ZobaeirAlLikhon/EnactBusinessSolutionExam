package com.example.enactbusinesssolutionexam.adaptar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enactbusinesssolutionexam.MedicineDatails;
import com.example.enactbusinesssolutionexam.R;
import com.example.enactbusinesssolutionexam.model.Medicine;

import java.util.List;

public class GetMedicineAdaptar extends RecyclerView.Adapter<GetMedicineAdaptar.MyViewHolder> {
    Context context;
    private List<Medicine> medicineList ;

    public GetMedicineAdaptar(Context context, List<Medicine> medicineList) {
        this.context = context;
        this.medicineList = medicineList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_medi_adaptar,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mediName.setText(medicineList.get(position).getDrugs());
        holder.mediName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MedicineDatails.class);
                intent.putExtra("name",medicineList.get(position).getDrugs());
                intent.putExtra("pharmacology",medicineList.get(position).getPharmacology());
                intent.putExtra("precautions",medicineList.get(position).getPrecautions());
                intent.putExtra("sideEffect",medicineList.get(position).getSideEffects());
                intent.putExtra("storage",medicineList.get(position).getStorage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mediName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mediName=itemView.findViewById(R.id.medicineName);
        }
    }
}
