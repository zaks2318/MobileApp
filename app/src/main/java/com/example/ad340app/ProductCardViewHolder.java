package com.example.ad340app;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductCardViewHolder extends RecyclerView.ViewHolder {

    public ImageView productImage;
    public TextView productTitle;
    public MatchView k;
    CheckBox likeButton;

    public ProductCardViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.product_image);
        productTitle = itemView.findViewById(R.id.product_title);
        likeButton = itemView.findViewById(R.id.like_button);
        likeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Listener myListener = (Listener) itemView.getContext();
                if (myListener != null) {
                    myListener.matchesLikeToast(k);
                }
            }
        });
    }
    public void setLiked(boolean liked) {
        likeButton.setChecked(liked);
    }


}