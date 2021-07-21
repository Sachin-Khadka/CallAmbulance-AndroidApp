package com.joker.callambulance.landing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joker.callambulance.R;
import com.joker.callambulance.entities.Emergency;

import java.util.List;

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.MyViewHolder>
{
    private List<Emergency> modelClassList;

    public EmergencyAdapter(List<Emergency> modelClassList)
    {
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_items_layout, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        int img = modelClassList.get(position).getImg();
        String name = modelClassList.get(position).getEmergencyName();
        String type = modelClassList.get(position). getEmergencyType();
        holder.setData(img, name, type);
    }

    @Override
    public int getItemCount()
    {
        return modelClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView emImg;
        private TextView emName;
        private TextView emType;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            emImg = itemView.findViewById(R.id.emergencyImage);
            emName = itemView.findViewById(R.id.emergencyName);
            emType = itemView.findViewById(R.id.emergencyType);
        }

        public void setData(int img, String eName, String eType)
        {
            emImg.setImageResource(img);
            emName.setText(eName);
            emType.setText(eType);
        }
    }
}
