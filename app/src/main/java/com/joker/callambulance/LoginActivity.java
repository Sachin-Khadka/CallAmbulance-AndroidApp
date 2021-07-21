package com.joker.callambulance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.joker.callambulance.dbconnector.DatabaseHandler;

public class LoginActivity extends AppCompatActivity
{
    private Button  login_register_button, forgot_password_button, login_button;
    EditText email, password;
    DatabaseHandler connectorObject ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

          connectorObject = new DatabaseHandler(LoginActivity.this);
          login_register_button = findViewById(R.id.login_register_button);
          forgot_password_button = findViewById(R.id.forgot_password_button);
          login_button = findViewById(R.id.login_button);

          email = findViewById(R.id.user_email);
          password = findViewById(R.id.user_password);


         login_register_button.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View v)
            {
                Intent LoginIntent = new Intent(LoginActivity.this,Register.class);
                startActivity(LoginIntent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String userEmail = email.getText().toString().trim();
                String userPassword = password.getText().toString().trim();
                Boolean data =  connectorObject. verifyUser(userEmail, userPassword);
                if(data == true)
                {
                    Intent HomePageIntent = new Intent(LoginActivity.this, HomePage.class);
                    startActivity(HomePageIntent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }

            }
        });

        forgot_password_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent LoginIntent = new Intent(LoginActivity.this,ForgotPassword.class);
                startActivity(LoginIntent);
            }
        });

    }



}
