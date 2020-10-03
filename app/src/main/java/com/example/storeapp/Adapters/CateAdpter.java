package com.example.storeapp.Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapp.Model.Category;
import com.example.storeapp.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CateAdpter extends RecyclerView.Adapter<CateAdpter.ViewHolder> {

    List<Category> categories;
    Context context;

     public CateAdpter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cate_card_design, parent, false);
        return new CateAdpter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Category category  = categories.get(position);
            String name  = category.getName();
            holder.namecate.setText(name);
        if (name.equals("T-shirt")) {
            holder.cateimg.getResources().getDrawable(R.drawable.shirt);
        }else if (name.equals("sport tool")){
            holder.cateimg.getResources().getDrawable(R.drawable.football);

        }else {
          holder.cateimg.getResources().getDrawable(R.drawable.ic_launcher_foreground);
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cateimg;
        TextView namecate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cateimg = itemView.findViewById(R.id.cateimg);
            namecate = itemView.findViewById(R.id.namecate);

        }
    }
}
