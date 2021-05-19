package com.example.ad340app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ad340app.network.ImageRequester;
import java.util.List;

public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    private List<MatchView> productList;
    private ImageRequester imageRequester;

    ProductCardRecyclerViewAdapter(List<MatchView> productList) {
        this.productList = productList;
        imageRequester = ImageRequester.getInstance();
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
            MatchView picture = productList.get(position);
            holder.productTitle.setText(picture.name);
            holder.setLiked(picture.liked);
            imageRequester.setImageFromUrl(holder.productImage, picture.imageUrl);
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