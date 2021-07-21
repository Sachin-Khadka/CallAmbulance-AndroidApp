package com.joker.callambulance.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joker.callambulance.R;
import com.joker.callambulance.landing.AmbulanceServices;
import com.joker.callambulance.landing.BloodDonor;
import com.joker.callambulance.landing.EmergencyLanding;
import com.joker.callambulance.landing.HospitalInfo;

public class HomeFragment extends Fragment
{
     private Button emergency_btn, donation_btn, services_btn, gps_btn, info_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View  view = inflater.inflate(R.layout.fragment_home, container, false);

        emergency_btn = view.findViewById(R.id.heart_icon);
        info_btn =  view.findViewById(R.id.hospitalDetails);
        services_btn = view.findViewById(R.id.ambulance_icon);
        donation_btn = view.findViewById(R.id.he);

        emergency_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent emergencyIntent = new Intent(getActivity(), EmergencyLanding.class);
                startActivity(emergencyIntent);
            }
        });

        info_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent infoIntent = new Intent(getActivity(), HospitalInfo.class);
                startActivity(infoIntent);
            }
        });

        services_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent servicesIntent = new Intent(getActivity(), AmbulanceServices.class);
                startActivity(servicesIntent);
            }
        });


        donation_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent servicesIntent = new Intent(getActivity(), BloodDonor.class);
                startActivity(servicesIntent);
            }
        });

        return view;
    }


}