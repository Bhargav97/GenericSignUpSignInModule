package com.couchsurf.bhargav.couchsurfing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment implements View.OnClickListener {
    static public int USER_TYPE;
    static public String UID;
    static public String UNAME;
    CardView regNewCouch, manageCouches, adminPanel;

    private void showViewWithAnim(int delay, View v) {
        Handler handler = new Handler();
        final View w = v;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                w.setAlpha(0.0f);
                w.setVisibility(View.VISIBLE);
                w.animate().alpha(1.0f);
            }
        }, 500);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = getLayoutInflater().inflate(R.layout.home_layout, container, false);
        final SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        USER_TYPE = sharedpreferences.getInt("USER_TYPE", 0);
        if (sharedpreferences.getString("UID", "").trim().equals(""))
            Toast.makeText(getActivity(), "Error retrieving UID", Toast.LENGTH_LONG).show();
        else
            UID = sharedpreferences.getString("UID", "");
        UNAME = sharedpreferences.getString("UNAME", "");
        final TextView welcome = v.findViewById(R.id.welcome_text);
        //showViewWithAnim(500, welcome);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                welcome.setText(welcome.getText() + " " + UNAME + "!");
                welcome.setAlpha(0.0f);
                welcome.setVisibility(View.VISIBLE);
                welcome.animate().alpha(1.0f);
            }
        }, 1500);

        regNewCouch = v.findViewById(R.id.registerCouch);
        manageCouches = v.findViewById(R.id.manageCouch);
        adminPanel = v.findViewById(R.id.adminPanel);
        regNewCouch.setOnClickListener(this);
        manageCouches.setOnClickListener(this);
        adminPanel.setOnClickListener(this);
        //SHOW APPROPRIATE CARDS FOR THE TYPE OF USER
        switch (USER_TYPE) {
            case 0:
                //
                break;
            case 1:
                showViewWithAnim(1000, regNewCouch);
                showViewWithAnim(1000, manageCouches);
                break;
            case 2:
                showViewWithAnim(1000, regNewCouch);
                showViewWithAnim(1000, manageCouches);
                showViewWithAnim(1000, adminPanel);
                break;
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.registerCouch:
                //
                break;
            case R.id.manageCouch:
                //
                break;
            case R.id.adminPanel:
                //
                startActivity(new Intent(getActivity(),AdminPanel.class));
                break;

        }
    }
}