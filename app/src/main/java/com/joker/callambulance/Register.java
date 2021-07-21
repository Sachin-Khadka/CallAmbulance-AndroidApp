package com.joker.callambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.EditText;
import android.widget.Toast;

import com.joker.callambulance.dbconnector.DatabaseHandler;
import com.joker.callambulance.entities.User;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity
{
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private Button register_login_btn, register_button;
    private TextInputLayout username, email, password, rePassword;
    CheckBox checkBox;
    DatabaseHandler  connectorObject;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        connectorObject = new DatabaseHandler(Register.this);
        register_login_btn = (Button) findViewById(R.id.register_login);
        username = findViewById(R.id.register_username);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        rePassword = findViewById(R.id.register_re_password);
        checkBox = findViewById(R.id.checkbox);
        register_button = findViewById(R.id.register_button);

        register_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerLoginButtonClicker();
            }
        });

        registerButtonClicker();
    }

    private boolean validateEmail() {
        String emailInput = email.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }
    private boolean validateUsername() {
        String usernameInput = username.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            username.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            username.setError("Username too long");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }
    private boolean validatePassword()
    {
        String passwordInput = password.getEditText().getText().toString().trim();
        String re_passwordInput = password.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }


    public void registerLoginButtonClicker()
    {
        Intent registerIntent = new Intent(Register.this, LoginActivity.class);
        startActivity(registerIntent);
    }

    public void registerButtonClicker()
    {
        register_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String fullName = username.getEditText().getText().toString().trim();
                String mail = email.getEditText().getText().toString().trim();
                String pwd = password.getEditText().getText().toString().trim();
                String re_pwd = rePassword.getEditText().getText().toString().trim();


                if (!validateEmail() | !validateUsername() | !validatePassword())
                {
                    return;
                }

                    else if(pwd.equals(re_pwd))
                    {
                        User user = new User(fullName, mail, pwd, re_pwd);
                        long store = connectorObject.addUser(user);

                        if(store > 0)
                        {
                            Toast.makeText(Register.this, "You have been Registered.", Toast.LENGTH_SHORT).show();
                            Intent registerUserIntent = new Intent(Register.this, LoginActivity.class);
                            startActivity(registerUserIntent);
                        }
                        else
                        {
                            Toast.makeText(Register.this,"Registration Error!!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Register.this,"Password is not matching!!",Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }
}