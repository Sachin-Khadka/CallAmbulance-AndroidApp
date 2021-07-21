package com.joker.callambulance.landing;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joker.callambulance.R;
import com.joker.callambulance.entities.Emergency;

import java.util.ArrayList;
import java.util.List;
public class EmergencyLanding extends AppCompatActivity
{
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        Toolbar toolbar = findViewById(R.id.performAction);
        setSupportActionBar(toolbar);

        rv = (RecyclerView) findViewById(R.id.recycleViewID);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        List<Emergency> emergenciesList =  new ArrayList<>();
        emergenciesList.add(new Emergency(R.drawable.ic_launcher_background, "Head Broke", "ICU"));
        emergenciesList.add(new Emergency(R.drawable.ic_launcher_background, "Leg Broke", "Normal"));
        emergenciesList.add(new Emergency(R.drawable.ic_launcher_background, "Cancer", "ICU"));
        emergenciesList.add(new Emergency(R.drawable.ic_launcher_background, "Corona Virus", "ICU"));
        emergenciesList.add(new Emergency(R.drawable.ic_launcher_background, "Blood Cancer", "Normal"));
        emergenciesList.add(new Emergency(R.drawable.ic_launcher_background, "Thylate", "Normal"));
        emergenciesList.add(new Emergency(R.drawable.ic_launcher_background, "Bone", "Normal"));
        emergenciesList.add(new Emergency(R.drawable.ic_launcher_background, "TB Cancer", "Normal"));
        emergenciesList.add(new Emergency(R.drawable.ic_launcher_background, "Poilo Virus", "Normal"));
        emergenciesList.add(new Emergency(R.drawable.ic_launcher_background, "Brain Tumber Cancer", "ICU"));

        EmergencyAdapter adapter = new EmergencyAdapter(emergenciesList);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
                final AlertDialog.Builder builder = new AlertDialog.Builder(EmergencyLanding.this);
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