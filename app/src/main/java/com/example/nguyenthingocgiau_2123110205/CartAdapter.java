package com.example.nguyenthingocgiau_2123110205;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<CartItem> cartItems;
    TextView txtTotal;
    View btnCheckout;

    public CartAdapter(Context context, List<CartItem> cartItems, TextView txtTotal, View btnCheckout) {
        this.context = context;
        this.cartItems = cartItems;
        this.txtTotal = txtTotal;
        this.btnCheckout = btnCheckout;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.txtName.setText(item.getName());
        holder.txtPrice.setText(item.getPrice() + "đ");
        holder.txtQuantity.setText(String.valueOf(item.getQuantity()));
        holder.imgProduct.setImageResource(item.getImageResId());
        holder.checkboxItem.setChecked(item.isSelected());

        holder.checkboxItem.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setSelected(isChecked);
            updateTotal();
        });

        holder.btnPlus.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
            updateTotal();
        });

        holder.btnMinus.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(position);
                updateTotal();
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
            updateTotal();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public void updateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            if (item.isSelected()) {
                total += item.getPrice() * item.getQuantity();
            }
        }
        txtTotal.setText("Tổng cộng: " + total + "đ");
        btnCheckout.setEnabled(total > 0);
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct, btnPlus, btnMinus, btnDelete;
        TextView txtName, txtPrice, txtQuantity;
        CheckBox checkboxItem;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            checkboxItem = itemView.findViewById(R.id.checkboxItem);
            btnDelete = itemView.findViewById(R.id.btnDelete); // thêm dòng này
        }
    }
}
