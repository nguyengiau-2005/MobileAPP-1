package com.example.nguyenthingocgiau_2123110205;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthingocgiau_2123110205.adapter.CartAdapter;
import com.example.nguyenthingocgiau_2123110205.model.CartItem;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerCart;
    ArrayList<CartItem> cartItems;
    CartAdapter adapter;
    TextView txtTotal;
    Button btnCheckout;
    CheckBox checkboxSelectAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Ánh xạ view
        recyclerCart = findViewById(R.id.recyclerCart);
        txtTotal = findViewById(R.id.txtTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        checkboxSelectAll = findViewById(R.id.checkboxSelectAll);

        // Lấy danh sách giỏ hàng
        cartItems = new ArrayList<>(CartManager.getCartItems());

        // Gán adapter
        adapter = new CartAdapter(this, cartItems, txtTotal, btnCheckout);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(adapter);

        // Chọn tất cả
        checkboxSelectAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            for (CartItem item : cartItems) {
                item.setSelected(isChecked);
            }
            adapter.notifyDataSetChanged();
            adapter.updateTotal();
        });

        // Nút mua hàng
        btnCheckout.setOnClickListener(v -> {
            boolean hasItemSelected = false;

            for (CartItem item : cartItems) {
                if (item.isSelected()) {
                    hasItemSelected = true;
                    break;
                }
            }

            if (hasItemSelected) {
                // Xoá mục đã chọn
                for (int i = cartItems.size() - 1; i >= 0; i--) {
                    if (cartItems.get(i).isSelected()) {
                        cartItems.remove(i);
                    }
                }

                adapter.notifyDataSetChanged();
                adapter.updateTotal();

                Toast.makeText(CartActivity.this, "🎉 Mua hàng thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CartActivity.this, "Vui lòng chọn ít nhất một sản phẩm để mua!", Toast.LENGTH_SHORT).show();
            }
        });

        // Nút quay lại
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
