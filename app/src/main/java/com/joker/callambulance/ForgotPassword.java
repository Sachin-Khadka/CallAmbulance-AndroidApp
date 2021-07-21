package com.joker.callambulance;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.joker.callambulance.dbconnector.DatabaseHandler;
import com.joker.callambulance.entities.User;

public class ForgotPassword extends AppCompatActivity
{
    private Button resetButton;
    private EditText fullName, email, password, confirmPassword;
    @Override
protected void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forgot_password);
    Toolbar toolbar = findViewById(R.id.actionPerform);
    setSupportActionBar(toolbar);

    resetButton = findViewById(R.id.updateButton);
    fullName = findViewById(R.id.fullName);
    email = findViewById(R.id.email);
    password = findViewById(R.id.password);
    confirmPassword = findViewById(R.id.confirmPassword);

    resetButton.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            String username = fullName.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String pwd = password.getText().toString().trim();
            String re_pwd = confirmPassword.getText().toString().trim();

            if(username.isEmpty() || mail.isEmpty() || pwd.isEmpty() || re_pwd.isEmpty())
            {
                Toast.makeText(ForgotPassword.this, "Fields are Empty !!", Toast.LENGTH_SHORT).show();
            }

            User user = new User(username, mail,pwd, re_pwd);
            DatabaseHandler connectorObject = null;
            long store = connectorObject.addUser(user);

            if(store > 0)
            {
                Toast.makeText(ForgotPassword.this, "Reset Password Successful !!", Toast.LENGTH_SHORT).show();
                Intent registerUserIntent = new Intent(ForgotPassword.this, LoginActivity.class);
                startActivity(registerUserIntent);
            }
            else
            {
                Toast.makeText(ForgotPassword.this, "Failed to Reset your Password !!", Toast.LENGTH_SHORT).show();
            }
        }
    });

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.action_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "Download it through playstore: www.joker.callambulance.com");
                startActivity(Intent.createChooser(sharingIntent, "ShareVia: "));
                break;

            case R.id.action_exit:
                final AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);
                builder.setMessage("Are sure to exit ?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

        }

        return super.onOptionsItemSelected(item);

    }
}