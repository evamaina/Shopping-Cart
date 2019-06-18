package com.example.shoppingcart.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shoppingcart.R;
import com.example.shoppingcart.models.ProductAttribute;
import com.example.shoppingcart.utilities.OnAttributeClickListener;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeViewHolder> {
    List<ProductAttribute> attributeList;
    Context context;
    OnAttributeClickListener listener;

    public SizeAdapter(Context context, OnAttributeClickListener listener) {
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public SizeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.color_layout, null, false);
        return new SizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SizeViewHolder colorViewHolder, int i) {
        final ProductAttribute attribute = attributeList.get(i);
        colorViewHolder.sizeText.setText(attribute.getAttributValue());
        colorViewHolder.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAttributeClickListener(attribute.getAttributValue());
            }
        });
    }

    public void setAttributeList(List<ProductAttribute> attributeList) {
        this.attributeList = attributeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return  attributeList == null ? 0 : attributeList.size();
    }

}

class SizeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView sizeText;
    View.OnClickListener listener;

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public SizeViewHolder(@NonNull View itemView) {
        super(itemView);
        sizeText = itemView.findViewById(R.id.product_size_text_view);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }
}
