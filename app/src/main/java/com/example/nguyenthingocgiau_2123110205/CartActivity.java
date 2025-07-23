package com.example.nguyenthingocgiau_2123110205;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        // Nút quay lại
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

//    private ArrayList<CartItem> getCartItems() {
//        ArrayList<CartItem> list = new ArrayList<>();
//        list.add(new CartItem("Bàn trang điểm", 159000, R.drawable.product1, 1));
//        list.add(new CartItem("Kệ máy tính", 49000, R.drawable.product2, 1));
//        list.add(new CartItem("Bèo tổ ong", 13260, R.drawable.product3, 1));
//        return list;
//
//    }
}
