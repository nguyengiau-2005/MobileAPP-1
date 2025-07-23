package com.example.nguyenthingocgiau_2123110205.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthingocgiau_2123110205.R;
import com.example.nguyenthingocgiau_2123110205.model.Bake;

import java.util.List;

public class TopBakeAdapter extends RecyclerView.Adapter<TopBakeAdapter.BakeViewHolder> {

    private final List<Bake> bakeList;

    public TopBakeAdapter(List<Bake> bakeList) {
        this.bakeList = bakeList;
    }

    @NonNull
    @Override
    public BakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_topbake, parent, false);
        return new BakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BakeViewHolder holder, int position) {
        Bake bake = bakeList.get(position);
        holder.imgBake.setImageResource(bake.getImageRes());
        holder.txtTitle.setText(bake.getTitle());
        holder.txtDescription.setText(bake.getDescription());
    }

    @Override
    public int getItemCount() {
        return bakeList.size();
    }

    static class BakeViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBake;
        TextView txtTitle, txtDescription;

        public BakeViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBake = itemView.findViewById(R.id.imgBake);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }
    }
}
