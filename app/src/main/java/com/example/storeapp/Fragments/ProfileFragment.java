package com.example.storeapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.storeapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends DialogFragment{


    FirebaseAuth auth ;
    Button btn_sing;
    Button btn_log ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        btn_sing = view.findViewById(R.id.sginin);
        btn_log = view.findViewById(R.id.login);

        btn_sing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment dialogFragment= SginUpFragment.fff();
                dialogFragment.show(getChildFragmentManager(),"dd");
            }
        });

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment=RigstarFragment.RF();
                dialogFragment.show(getChildFragmentManager(),"dd");
            }
        });
        return view;
    }

    public static ProfileFragment Pfragment(){
        return  new ProfileFragment();
    }

}