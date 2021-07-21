package com.joker.callambulance.landing;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.joker.callambulance.R;

public class BloodDonor extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donor);

        Toolbar toolbar = findViewById(R.id.performAction);
        setSupportActionBar(toolbar);


        ListView listViewDonors = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<CharSequence> adapterDonors = ArrayAdapter.createFromResource(this,
                R.array.donorsArray,
                android.R.layout.simple_list_item_1);

        listViewDonors.setAdapter(adapterDonors);
        listViewDonors.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        String message = getString(R.string.ok ) + " " + adapterDonors.getItem(position);
                        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
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
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "Download it through playstore.");
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;

            case R.id.action_exit:
                final AlertDialog.Builder builder = new AlertDialog.Builder(BloodDonor.this);
                builder.setMessage("Are sure to exit ?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                       dialog.cancel();
                    }
                });

                        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
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