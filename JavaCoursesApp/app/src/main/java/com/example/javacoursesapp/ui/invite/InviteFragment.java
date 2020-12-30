package com.example.javacoursesapp.ui.invite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.javacoursesapp.R;

public class InviteFragment extends Fragment {

TextView inviteTxtView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_invite, container, false);
        inviteTxtView=rootView.findViewById(R.id.textView_invite_via);
        inviteTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Check this cool app named \"Java Course App\" for best java tutorials.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        return rootView;
    }
}