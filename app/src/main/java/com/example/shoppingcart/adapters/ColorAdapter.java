package com.example.shoppingcart.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.shoppingcart.R;
import com.example.shoppingcart.models.ProductAttribute;
import com.example.shoppingcart.utilities.OnAttributeClickListener;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorViewHolder> {
    List<ProductAttribute> productAttributeList;
    Context context;
    OnAttributeClickListener listener;

    public ColorAdapter(Context context, OnAttributeClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setProductAttributeList(List<ProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =  LayoutInflater.from(context).inflate(R.layout.size_layout, null, false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder colorViewHolder, int i) {
        final ProductAttribute attribute =  productAttributeList.get(i);
        ImageView colorImage = colorViewHolder.colorImage;
        String color = attribute.getAttributValue();
        if(color.equals("White")){
            colorImage.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        }
        if(color.equals("Black")){
            colorImage.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        }
        if(color.equals("Red")){
            colorImage.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        }
        if(color.equals("Orange")){
            colorImage.setColorFilter(Color.parseColor("#FFA500"), PorterDuff.Mode.SRC_IN);
        }
        if(color.equals("Yellow")){
            colorImage.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        }
        if(color.equals("Green")){
            colorImage.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        }
        if(color.equals("Blue")){
            colorImage.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        }
        if(color.equals("Purple")){
            colorImage.setColorFilter(Color.parseColor("#4b0082"), PorterDuff.Mode.SRC_IN);
        }
        colorViewHolder.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAttributeClickListener(attribute.getAttributValue());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productAttributeList == null ? 0 : productAttributeList.size();
    }
}

class ColorViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
    ImageView colorImage;

    View.OnClickListener listener;
    public ColorViewHolder(@NonNull View itemView) {
        super(itemView);
         colorImage = itemView.findViewById(R.id.color_image);

         itemView.setOnClickListener(this);
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }
}
