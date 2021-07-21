package com.joker.callambulance.landing;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.joker.callambulance.DetailedActivity;
import com.joker.callambulance.R;

public class AmbulanceServices extends AppCompatActivity
{

    ListView myListView;
    String[] items;
    String[] prices;
    String[] descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_services);

        Toolbar toolbar = findViewById(R.id.ambulanceAction);
        setSupportActionBar(toolbar);

        Resources res = getResources();
        myListView = findViewById(R.id.myListView);
        items = res.getStringArray(R.array.items);
        prices = res.getStringArray(R.array.prices);
        descriptions = res.getStringArray(R.array.descriptions);


        ItemAdapter itemAdapter = new ItemAdapter(this, items, prices,descriptions);
        myListView.setAdapter(itemAdapter);


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent showDetailActivity = new Intent(getApplicationContext(), DetailedActivity.class);
                showDetailActivity.putExtra("com.androidtest.ITEM_INDEX", i);
                startActivity(showDetailActivity);

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
                final AlertDialog.Builder builder = new AlertDialog.Builder(AmbulanceServices.this);
                builder.setMessage("Are sure to exit ?");
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
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