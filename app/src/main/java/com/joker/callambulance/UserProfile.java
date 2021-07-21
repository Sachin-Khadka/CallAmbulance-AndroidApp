package com.joker.callambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.AutofillService;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.joker.callambulance.dbconnector.DatabaseHandler;
import com.joker.callambulance.entities.User;

import java.util.List;

public class UserProfile extends AppCompatActivity
{

    private Button updateButton;
    private Button deleteButton;
    private Button viewAllButton;
    private  EditText delete_text;
    private TextView   view_all;
    private EditText fullName, email, password, confirmPassword;
    DatabaseHandler connectorObject;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        view_all = findViewById(R.id.view_all);
        viewAllButton = findViewById(R.id.viewAllButton);

        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        connectorObject = new DatabaseHandler(UserProfile.this);



        updateButton.setOnClickListener(new View.OnClickListener()
        {
            String username = fullName.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String pwd = password.getText().toString().trim();
            String re_pwd = confirmPassword.getText().toString().trim();

            @Override
            public void onClick(View v)
            {
                User user = new User(username, mail,pwd, re_pwd);
                long store = connectorObject.addUser(user);

                if(store > 0)
                {
                    Toast.makeText(UserProfile.this, "You have been Updated.", Toast.LENGTH_SHORT).show();
                    Intent registerUserIntent = new Intent(UserProfile.this, LoginActivity.class);
                    startActivity(registerUserIntent);
                }
                else
                {
                    Toast.makeText(UserProfile.this,"Error for Updating!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void deleteAction(View view)
    {
        String entered_email = delete_text.getText().toString();
        connectorObject.deleteUser(entered_email);
    }


//    public void displayData()
//    {
//
//        Log.d("Reading: ", "Reading all contacts..");
//        List<User> userList = connectorObject.getAllUsers();
//
//        for (User cn : userList)
//        {
//            String log = "User Name: " + cn.getFullName();
//            Log.d("Name: ", log);
//            retrieve_data.setText(log);
//        }
//        retrieve_data.setText("");
//    }






}