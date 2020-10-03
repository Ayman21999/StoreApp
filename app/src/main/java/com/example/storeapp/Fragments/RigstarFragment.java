package com.example.storeapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storeapp.Model.UserData;
import com.example.storeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.Iterator;

public class RigstarFragment extends DialogFragment {

    TextView register_TW;
    EditText emai_log;
    EditText pass_log;
    Button btn_log;
    FirebaseUser user;
    FirebaseAuth auth;
    UserData ud;
    FirebaseFirestore firebaseFirestore ;
    CollectionReference reference ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rigstar, container, false);
        register_TW = view.findViewById(R.id.register_text);
        emai_log = view.findViewById(R.id.useremail_log);
        pass_log = view.findViewById(R.id.password_log);
        btn_log = view.findViewById(R.id.btn_register);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        firebaseFirestore = FirebaseFirestore.getInstance();
        reference = firebaseFirestore.collection("User of storeApp");

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emai_log.getText() + "";
                final String pass = pass_log.getText() + "";

                if (email.isEmpty()) {
                    Toast.makeText(getContext(), "please enter your email", Toast.LENGTH_SHORT).show();


                }else if (pass.isEmpty()) {
                    Toast.makeText(getContext(), "please enter your password", Toast.LENGTH_SHORT).show();
                }else {
                    Task<QuerySnapshot> snapshotTask  = reference.get();
                    snapshotTask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            task.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    Iterator<QueryDocumentSnapshot> iterator = queryDocumentSnapshots.iterator();
                                    while (iterator.hasNext()){
                                        ud = iterator.next().toObject(UserData.class);
                                        if (!pass.equals(ud.getPasswor())){

                                        }else {
                                        Login(email , pass);

                                        }
                                    }
                                }
                            });
                        }
                    });
                }

            }

        });


        register_TW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = SginUpFragment.fff();
                dialogFragment.show(getChildFragmentManager(), "dd");
            }
        });
        return view;
    }

    public void Login(String email, String pass) {
        Task<AuthResult> task = auth.signInWithEmailAndPassword(email, pass);

            if (task.isSuccessful()) {
                task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        DialogFragment dialogFragment= ProfileFragment.Pfragment();
                        dialogFragment.show(getChildFragmentManager(),"dd");
                        Toast.makeText(getContext(), "Log in Successfully", Toast.LENGTH_SHORT).show();

                    }
                });

            }else {

                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }

    }

    public static RigstarFragment RF() {

        return new RigstarFragment();

    }
}