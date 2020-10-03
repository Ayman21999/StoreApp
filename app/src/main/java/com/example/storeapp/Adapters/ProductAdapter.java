package com.example.storeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapp.Model.Products;
import com.example.storeapp.Model.SportBalls;
import com.example.storeapp.Model.TShirtData;
import com.example.storeapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    List<Products> products;
    Context context;

    ProductAdapter(Context context, List<Products> products) {
        this.context = context;
        this.products = products;

    }

    @NonNull
    @Override
    public ProductAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_desgin, parent, false);

        return new ProductAdapter.ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductHolder holder, int position) {
        Products p = products.get(position);
        TShirtData tdata = (TShirtData) products.get(position);
        SportBalls sballs= (SportBalls)products.get(position);
        String color = tdata.getColor();
        String name = sballs.getName();
        holder.price.setText(p.getPrice());
        Picasso.get().load(p.getImg()).fit().into(holder.productimg);
        if (color.equals("color")){
            holder.PnameOrColor.setText(color);
        }else {
            holder.PnameOrColor.setText(name);

        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

        ImageView productimg;
        TextView price;
        TextView PnameOrColor;
        Button add;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            productimg = itemView.findViewById(R.id.productimg);
            price = itemView.findViewById(R.id.productprice);
            PnameOrColor= itemView.findViewById(R.id.productname);
            add = itemView.findViewById(R.id.add_to_cart);
        }
    }
}
