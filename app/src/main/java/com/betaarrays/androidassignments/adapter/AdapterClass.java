package com.betaarrays.androidassignments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betaarrays.androidassignments.R;
import com.betaarrays.androidassignments.response.ProductResponse;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {
    List<ProductResponse> list;
    Context context;

    public AdapterClass(List<ProductResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getThumbnail()).placeholder(R.drawable.loading).into(holder.image);
        String percent= String.valueOf(list.get(position).getDiscountPercentage());
       holder.percantage.setText(percent+"% (Off)");
       String price= String.valueOf(list.get(position).getPrice());
        holder.price.setText("Rs."+price);
        holder.description.setText(list.get(position).getDescription());
        String ratings= String.valueOf(list.get(position).getRating());
       holder.rating.setText("Ratings  "+ratings);


        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "The id is     "+list.get(position).getId(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView description,rating,price,percantage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.thumbnail_image);
            description=itemView.findViewById(R.id.description);
            rating=itemView.findViewById(R.id.ratings);
            price=itemView.findViewById(R.id.price);
            percantage=itemView.findViewById(R.id.percent);
        }
    }
}
