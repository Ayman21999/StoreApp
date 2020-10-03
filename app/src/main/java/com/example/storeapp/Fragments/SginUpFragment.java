package com.example.storeapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SginUpFragment extends DialogFragment {
    EditText emailEd;
    EditText MobileNumED;
    EditText passwordED;
    EditText confirmpasswordED;
    Button btn_SginUp;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    CollectionReference reference;
    UserData udata;

    public SginUpFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sgin_in, container, false);
        emailEd = view.findViewById(R.id.emailuser);
        MobileNumED = view.findViewById(R.id.mobilenum);
        passwordED = view.findViewById(R.id.password);
        confirmpasswordED = view.findViewById(R.id.confirm_pass);
        btn_SginUp = view.findViewById(R.id.btn_singup);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        reference = firebaseFirestore.collection("User of storeApp");
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        btn_SginUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useemail = emailEd.getText().toString();
                String mobileNum = MobileNumED.getText() + "";
                String password = passwordED.getText() + "";
                String conPassword = confirmpasswordED.getText() + "";

                if (useemail.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                } else if (mobileNum.isEmpty()) {
                    Toast.makeText(getContext(), "Enter your phone number ", Toast.LENGTH_SHORT).show();

                } else if (mobileNum.length() != 10) {
                    Toast.makeText(getContext(), "wrong in phone number ", Toast.LENGTH_SHORT).show();

                } else if (password.isEmpty()) {
                    Toast.makeText(getContext(), "Enter your password ", Toast.LENGTH_SHORT).show();

                } else if (!password.equals(conPassword)) {
                    Toast.makeText(getContext(), "password didn't  match", Toast.LENGTH_SHORT).show();

                } else {
                    CreateAccount(useemail, mobileNum, password);
                }

            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);
    }

    public void CreateAccount(String email, final String mobileNumber, final String password) {
        final Task<AuthResult> task = auth.createUserWithEmailAndPassword(email, password);

        if (task.isSuccessful()) {
            task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    udata = new UserData();
                    FirebaseUser user = auth.getCurrentUser();
                    udata.setMobileNum(mobileNumber);
                    udata.setEmail(user.getEmail());
                    udata.setPasswor(password);
                    Task<DocumentReference> task1 = reference.add(udata);

                    Toast.makeText(getContext(), "Add Successfully", Toast.LENGTH_LONG).show();

                    if (!task1.isSuccessful()) {
                        task1.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), e.getLocalizedMessage() + "Error number  ", Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                }

            });
        } else {
            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }



    public static SginUpFragment fff() {

        return new SginUpFragment();
    }


}