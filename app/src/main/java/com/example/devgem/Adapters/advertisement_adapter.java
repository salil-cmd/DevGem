package com.example.devgem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devgem.Models.advertisement;
import com.example.devgem.R;
import com.example.devgem.Models.advertisement;

import java.util.ArrayList;

public class advertisement_adapter extends RecyclerView.Adapter<advertisement_adapter.ViewHolder> {
    Context context;
    ArrayList<advertisement> list;


    public advertisement_adapter(Context context, ArrayList<advertisement> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.advertisement_item, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // grab all the views that will be changed dynamically
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final advertisement advertisement = list.get(position);
        holder.imageView.setImageResource(advertisement.getImage());
        holder.textView.setText(advertisement.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
