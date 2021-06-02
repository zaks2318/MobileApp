package com.example.ad340app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    private List<MatchView> productList;
    private Context context;

    ProductCardRecyclerViewAdapter(Context context,List<MatchView> productList) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        if (productList != null ) {
            MatchView picture = this.productList.get(position);
            holder.productTitle.setText(picture.name);
            holder.setLiked(picture.liked);
            Picasso.get().load(picture.imageUrl).into(holder.productImage);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setMatchesList(List<MatchView> picture) {
        this.productList = picture;
    }
}