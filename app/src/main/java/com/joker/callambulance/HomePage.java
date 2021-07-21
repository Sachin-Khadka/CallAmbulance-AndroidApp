package com.joker.callambulance;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.joker.callambulance.fragments.AboutUsFragment;
import com.joker.callambulance.fragments.ChatFragment;
import com.joker.callambulance.fragments.HomeFragment;
import com.joker.callambulance.fragments.ProfileFragment;
import com.joker.callambulance.fragments.ShareFragment;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState ==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

   @Override
   public  boolean onNavigationItemSelected(@NonNull MenuItem item)
   {
       switch(item.getItemId())
       {
           case R.id.nav_chat:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChatFragment()).commit();
               break;
           case R.id.nav_about_us:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutUsFragment()).commit();
               break;
           case R.id.nav_profile:
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
           break;
           case R.id.nav_share:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
               break;

           case R.id.nav_send:
               Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
               break;

           case R.id.nav_home:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
               break;

           case R.id.nav_Log_out:
               Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show();
               break;

       }
       drawer.closeDrawer(GravityCompat.START);
       return  true;
   }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
                final AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
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
