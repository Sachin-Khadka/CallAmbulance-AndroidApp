package com.joker.callambulance.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joker.callambulance.LoginActivity;
import com.joker.callambulance.R;
import com.joker.callambulance.dbconnector.DatabaseHandler;
import com.joker.callambulance.entities.User;

import java.util.List;

public class ProfileFragment extends Fragment
{
    private Button updateButton;
    private Button deleteButton;
    private Button viewAllButton;
    private EditText delete_text;
    private TextView view_all;
    private EditText fullName, email, password, confirmPassword;
    DatabaseHandler connectorObject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        updateButton = view.findViewById(R.id.updateButton);
        deleteButton = view.findViewById(R.id.deleteButton);
        viewAllButton = view.findViewById(R.id.viewAllButton);

        fullName = view.findViewById(R.id.fullName);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        confirmPassword = view.findViewById(R.id.confirmPassword);
        delete_text = view.findViewById(R.id.delete_text);
        view_all = view.findViewById(R.id.view_all);


        updateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = fullName.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String re_pwd = confirmPassword.getText().toString().trim();

                User user = new User(username, mail,pwd, re_pwd);
                long store = connectorObject.addUser(user);

                if(store > 0)
                {
                    Toast.makeText(getActivity(), "You have been Updated.", Toast.LENGTH_SHORT).show();
                    Intent registerUserIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(registerUserIntent);
                }
                else
                {
                    Toast.makeText(getActivity(),"Error for Updating!!",Toast.LENGTH_SHORT).show();
                }
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String entered_email = delete_text.getText().toString();
                connectorObject.deleteUser(entered_email);
            }
        });


        viewAllButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Reading: ", "Reading all contacts..");
                    List<User> userList = connectorObject.getAllUsers();

                    for (User cn : userList)
                    {
                        String log = "User ID: "+ cn.get_id() + ", " +"User Name: " + cn.getFullName() + ", "+"User Email: "+ cn.getEmail();
                        Log.d("Name: ", log);
                        view_all.setText(log);
                    }
                    view_all.setText("");
            }
        });

        return  view;
    }

    }







