package com.example.shoppingcart.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shoppingcart.ProductDetailActivity;
import com.example.shoppingcart.R;
import com.example.shoppingcart.models.Products;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    List<Products> productsList;
    Context context;

    public ProductsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View product_view = inflater.inflate(R.layout.product_list,viewGroup,false);
        return new MyViewHolder(product_view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        final Products product = productsList.get(position);
        myViewHolder.name.setText(product.getName());
        myViewHolder.price.setText(product.getPrice());
        myViewHolder.price.setPaintFlags(myViewHolder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        myViewHolder.discounted_price.setText(product.getDiscounted_price());
        myViewHolder.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProductDetailActivity.class).putExtra("productId",
                        String.valueOf(product.getProduct_id())));
            }
        });

    }

    @Override
    public int getItemCount() {
        return productsList != null ? productsList.size() : 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, price, discounted_price;
        View.OnClickListener clickListener;

        public View.OnClickListener getClickListener() {
            return clickListener;
        }

        public void setClickListener(View.OnClickListener clickListener) {
            this.clickListener = clickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.bag_name);
            price = itemView.findViewById(R.id.price);
            discounted_price = itemView.findViewById(R.id.discounted_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v);
        }
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
        notifyDataSetChanged();

    }
}
