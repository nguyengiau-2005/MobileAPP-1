package com.example.nguyenthingocgiau_2123110205.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenthingocgiau_2123110205.CartItem;
import com.example.nguyenthingocgiau_2123110205.CartManager;
import com.example.nguyenthingocgiau_2123110205.ProductDetailActivity;
import com.example.nguyenthingocgiau_2123110205.R;
import com.example.nguyenthingocgiau_2123110205.model.Cake;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CakeAdapter extends RecyclerView.Adapter<CakeAdapter.ViewHolder> {

    private Context context;
    private List<Cake> cakeList;

    public CakeAdapter(Context context, List<Cake> cakeList) {
        this.context = context;
        this.cakeList = cakeList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCake;
        TextView txtName, txtFlavor, txtPrice;
        ImageView btnAddToCart;
        public ViewHolder(View itemView) {
            super(itemView);
            imgCake = itemView.findViewById(R.id.imgCake);
            txtName = itemView.findViewById(R.id.txtName);
            txtFlavor = itemView.findViewById(R.id.txtFlavor);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart); // không cần đổi dòng này nếu đã sửa kiểu
        }
    }

    @NonNull
    @Override
    public CakeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cake, parent, false);
        return new CakeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cake cake = cakeList.get(position);
        holder.txtName.setText(cake.getName());
        holder.imgCake.setImageResource(cake.getImageResId());

        String formattedPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(cake.getPrice());
        holder.txtPrice.setText(formattedPrice);

        holder.txtFlavor.setText("Vị: " + cake.getFlavor());

        // Sự kiện click item để xem chi tiết sản phẩm
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("name", cake.getName());
            intent.putExtra("category", cake.getFlavor());
            intent.putExtra("price", cake.getPrice());
            intent.putExtra("image", cake.getImageResId());
            intent.putExtra("description", "Mô tả chi tiết: " + cake.getDescription());
            context.startActivity(intent);
        });

        // Nút thêm vào giỏ hàng (nếu cần)
        holder.btnAddToCart.setOnClickListener(v -> {
            CartManager.addToCart(new CartItem(
                    cake.getName(),
                    cake.getPrice(),
                    1,
                    cake.getImageResId()
            ));
            Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return cakeList.size();
    }
    public void updateData(List<Cake> newCakes) {
        this.cakeList = newCakes;
        notifyDataSetChanged();
    }
}
