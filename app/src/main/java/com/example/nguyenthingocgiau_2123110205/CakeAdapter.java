package com.example.nguyenthingocgiau_2123110205;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CakeAdapter extends RecyclerView.Adapter<CakeAdapter.CakeViewHolder> {

    Context context;
    List<Cake> cakeList;

    public CakeAdapter(Context context, List<Cake> list) {
        this.context = context;
        this.cakeList = list;
    }

    @NonNull
    @Override
    public CakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cake, parent, false);
        return new CakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CakeViewHolder holder, int position) {
        Cake cake = cakeList.get(position);
        holder.txtName.setText(cake.getName());
        holder.txtFlavor.setText("Flavor: " + cake.getFlavor());
        holder.txtPrice.setText("$" + String.format("%.2f", cake.getPrice()));
        holder.imgCake.setImageResource(cake.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("name", cake.getName());
            intent.putExtra("category", cake.getFlavor());
            intent.putExtra("price", cake.getPrice());
            intent.putExtra("image", cake.getImageResId());
            context.startActivity(intent);
        });

        // 👇 Xử lý thêm vào giỏ
        holder.btnAddToCart.setOnClickListener(v -> {
            CartItem item = new CartItem(cake.getName(), cake.getPrice(), cake.getImageResId(), 1);
            CartManager.addToCart(item);
            Toast.makeText(context, "Đã thêm vào giỏ: " + cake.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return cakeList.size();
    }

    public class CakeViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCake;
        TextView txtName, txtFlavor, txtPrice;
        Button btnAddToCart;

        public CakeViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCake = itemView.findViewById(R.id.imgCake);
            txtName = itemView.findViewById(R.id.txtName);
            txtFlavor = itemView.findViewById(R.id.txtFlavor);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
