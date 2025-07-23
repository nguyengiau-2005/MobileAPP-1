package com.example.nguyenthingocgiau_2123110205.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthingocgiau_2123110205.R;
import com.example.nguyenthingocgiau_2123110205.model.OrderItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Context context;
    ArrayList<OrderItem> items;

    public OrderAdapter(Context context, ArrayList<OrderItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_product, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItem item = items.get(position);
        holder.imgProduct.setImageResource(item.getImageRes());
        holder.txtName.setText(item.getName());
        holder.txtQuantity.setText("x" + item.getQuantity());
        holder.txtOldPrice.setText(formatCurrency(item.getOldPrice()));
        holder.txtOldPrice.setPaintFlags(holder.txtOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.txtNewPrice.setText(formatCurrency(item.getNewPrice()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private String formatCurrency(int amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return formatter.format(amount);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtName, txtQuantity, txtOldPrice, txtNewPrice;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtName = itemView.findViewById(R.id.txtName);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtOldPrice = itemView.findViewById(R.id.txtOldPrice);
            txtNewPrice = itemView.findViewById(R.id.txtNewPrice);
        }
    }
}
