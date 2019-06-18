package com.example.shoppingcart.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shoppingcart.R;
import com.example.shoppingcart.models.Cart;
import com.example.shoppingcart.models.Products;

import java.util.List;

public class BagDetailAdapter extends RecyclerView.Adapter<BagDetailAdapter.BagViewHolder> {
    List<Cart> bagDetailList;
    Context context;

    public BagDetailAdapter(Context context) {
        this.context = context;

    }


    @NonNull
    @Override
    public BagDetailAdapter.BagViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View bagDetailView = inflater.inflate(R.layout.bag_detail_list, viewGroup, false);
        return new BagViewHolder(bagDetailView);
    }

    @Override
    public void onBindViewHolder(@NonNull BagDetailAdapter.BagViewHolder bagViewHolder, int position) {
        final Cart bagDetail = bagDetailList.get(position);
        bagViewHolder.bagName.setText(bagDetail.getName());
        bagViewHolder.bagPrice.setText(bagDetail.getPrice());
        bagViewHolder.bagSize.setText(bagDetail.getAttributes());

    }

    @Override
    public int getItemCount() {
        return bagDetailList != null ? bagDetailList.size():0;
    }


    public class BagViewHolder extends RecyclerView.ViewHolder {
        TextView bagName, bagPrice, bagDiscountedPrice,bagSize;


        public BagViewHolder(@NonNull View itemView) {
            super(itemView);
            bagName = itemView.findViewById(R.id.bag_name);
            bagPrice = itemView.findViewById(R.id.bag_price);
            bagSize = itemView.findViewById(R.id.bag_size);
        }
    }

    public void setBagDetailList(List<Cart> bagDetailList) {
        this.bagDetailList = bagDetailList;
        notifyDataSetChanged();

    }
}
