package com.joker.callambulance.landing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joker.callambulance.R;
import com.joker.callambulance.entities.Hospital;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.MyViewHolder>
{
    private List<Hospital> modelClassList;

    public HospitalAdapter(List<Hospital> modelClassList)
    {
        this.modelClassList = modelClassList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int h)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_list_layout, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        int img = modelClassList.get(position).getHospitalImage();
        String name = modelClassList.get(position).getHospitalName();
        String type = modelClassList.get(position). getHospitalType();
        String hosfor = modelClassList.get(position). getHospitalFor();
        String phone = modelClassList.get(position). getHospitalPhone();
        holder.setData(img, name, type, hosfor, phone);
    }

    @Override
    public int getItemCount()
    {
        return modelClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView hospitalImg;
        private TextView hospitalName;
        private TextView hospitalType;
        private TextView hospitalFor;
        private TextView hospitalPhone;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            hospitalImg = itemView.findViewById(R.id.hospitalImage);
            hospitalName = itemView.findViewById(R.id.hospitalName);
            hospitalType= itemView.findViewById(R.id.hospitalType);
            hospitalFor= itemView.findViewById(R.id.hospitalFor);
            hospitalPhone= itemView.findViewById(R.id.hospitalPhone);
        }

        public void setData(int img, String hName, String hType, String hFor, String hPhone)
        {
            hospitalImg.setImageResource(img);
            hospitalName.setText(hName);
            hospitalType.setText(hType);
            hospitalFor.setText(hFor);
            hospitalPhone.setText(hPhone);
        }
    }
}
