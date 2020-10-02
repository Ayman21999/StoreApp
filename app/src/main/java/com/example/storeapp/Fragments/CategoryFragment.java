/**
 * Info about this package doing something for package-info.java file.
 */
package com.example.storeapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storeapp.Adapters.CateAdpter;
import com.example.storeapp.Model.Category;
import com.example.storeapp.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    private RecyclerView cateList;

    private FirebaseFirestore firebaseFirestore;
    private CateAdpter adpter;
    private List<Category> category;


    DocumentSnapshot documentSnapshot;
    /**
     * Collection Reference Variable reference
     */
    CollectionReference reference;
    private Task<DocumentSnapshot> task;
    private List<String> gg;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                            final Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_catagrey, container, false);
         cateList = view.findViewById(R.id.catelist);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        cateList.setLayoutManager(gridLayoutManager);
        firebaseFirestore = FirebaseFirestore.getInstance();
        reference = firebaseFirestore.collection("Categorey");

        category = new ArrayList<>();
        documentSnapshot = task.getResult();
        gg =(List<String>)documentSnapshot.get("cate");

        for (String dd: gg) {
           Category cc = new Category() ;
          String name =  cc.getName();
         Log.d("ttt" , name);
        }
        adpter = new CateAdpter(getContext(), category);
        cateList.setAdapter(adpter);


        return view;

    }
}