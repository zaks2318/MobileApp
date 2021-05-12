package com.example.ad340app;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

public class ProductCardViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView productImage;
    public TextView productTitle;


    public ProductCardViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.product_image);
        productTitle = itemView.findViewById(R.id.product_title);
        Button likeBut = itemView.findViewById(R.id.like_button);
        likeBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Listener myListener = (Listener) v.getContext();
                if (myListener != null) {
                    myListener.matchesLikeToast(productTitle.getText().toString());
                }
            }
        });
    }

}