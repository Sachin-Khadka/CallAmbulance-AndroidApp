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
import com.joker.callambulance.entities.Hospital;

import java.util.ArrayList;
import java.util.List;

public class HospitalInfo extends AppCompatActivity
{

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info);

        Toolbar toolbar = findViewById(R.id.performAction);
        setSupportActionBar(toolbar);

        rv = (RecyclerView) findViewById(R.id.recycleViewID);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        List<Hospital> hospitalsList =  new ArrayList<>();
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Neuro Hospital", "Private", "Neuro Related", "034034323"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Kanti Hospital", "Govenment", "Child Related", "01256335"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Eye Hospital", "Govenment", "Eye Related", "014260813"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Nepal Medicite Hospital", "Private", "all Related", "014217766"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Sushma Koirala Hospital", "Private", "all Related", "014523557"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Shubhatara Hospital", "Govenment", "all Related", "0147735281"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Anand Ban Leprosy Hospital", "Govenment", "all Related", "01253688"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Vayodha Hospital", "Govenment", "all Related", "104221988"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Bir Hospital", "Govenment", "all Related", "014221119"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Bharosa Hospital", "Private", "all Related", "014475999"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Alka Hospital", "Private", "all Related", "015544477"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Hams Hospital", "Private", "all Related", "014377781"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Nobel Hospital", "Private", "all Related", "014110842"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Chitwon Hospital", "Private", "all Related", "02455566"));
        hospitalsList.add(new Hospital(R.drawable.ic_launcher_background, "Teaching Hospital", "Govenment", "all Related", "023244542"));

        HospitalAdapter adapter = new HospitalAdapter(hospitalsList);
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
                final AlertDialog.Builder builder = new AlertDialog.Builder(HospitalInfo.this);
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