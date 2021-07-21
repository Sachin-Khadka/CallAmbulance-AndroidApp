package com.joker.callambulance.fragments;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joker.callambulance.R;

import java.io.File;

public class ShareFragment extends Fragment
{
   Button share_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        share_btn = view.findViewById(R.id.share_button);
        share_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ApplicationInfo api = getContext().getApplicationInfo();
                String apkpath = api.sourceDir;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("application/vdn.android.package-archieve");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkpath)));
                startActivity(Intent.createChooser(intent, "ShareVia"));
            }
        });

        return view;
    }



}