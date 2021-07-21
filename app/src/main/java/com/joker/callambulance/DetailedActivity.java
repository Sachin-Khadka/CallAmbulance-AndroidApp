package com.joker.callambulance;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailedActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delailed);

        Toolbar toolbar = findViewById(R.id.ambulanceAction);
        setSupportActionBar(toolbar);

        Intent in =getIntent();
        int index = in.getIntExtra("com.androidtest.ITEM_INDEX", -1);

        if (index > -1)
        {
            int pic = getImg(index);
            ImageView img = (ImageView) findViewById(R.id.imageViewSac);
            scaleImg(img, pic);
        }
    }

    private int  getImg(int index)
    {
        switch (index)
        {
            case 0: return R.drawable.heart;
            case 1: return R.drawable.heart1;
            case 2: return R.drawable.ambulance;
            default: return -1;
        }
    }

    private void scaleImg(ImageView img, int pic)
    {
        Display screen = getWindowManager().getDefaultDisplay(); //give access to screen
        BitmapFactory.Options options = new BitmapFactory.Options(); //give access of java file to resize/manage the images

        options.inJustDecodeBounds = true; // turn on boundary
        BitmapFactory.decodeResource(getResources(), pic, options); // determines the height and width, give access to look resources

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if(imgWidth > screenWidth)
        {
            int ratio = Math.round((float)imgWidth / (float) screenWidth);
            options.inSampleSize = ratio;
        }

        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);

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
                    final AlertDialog.Builder builder = new AlertDialog.Builder(DetailedActivity.this);
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