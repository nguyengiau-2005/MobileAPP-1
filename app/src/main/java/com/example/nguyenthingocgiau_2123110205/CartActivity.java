package com.example.nguyenthingocgiau_2123110205;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerCart = findViewById(R.id.recyclerCart);
        txtTotal = findViewById(R.id.txtTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        checkboxSelectAll = findViewById(R.id.checkboxSelectAll);

        cartItems = getCartItems(); // Lấy danh sách sản phẩm từ static, DB, hoặc intent
        adapter = new CartAdapter(this, cartItems, txtTotal, btnCheckout);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(adapter);

        checkboxSelectAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            for (CartItem item : cartItems) {
                item.setSelected(isChecked);
            }
            adapter.notifyDataSetChanged();
            adapter.updateTotal();
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private ArrayList<CartItem> getCartItems() {
        ArrayList<CartItem> list = new ArrayList<>();
        list.add(new CartItem("Bàn trang điểm", 159000, R.drawable.product1, 1));
        list.add(new CartItem("Kệ máy tính", 49000, R.drawable.product2, 1));
        list.add(new CartItem("Bèo tổ ong", 13260, R.drawable.product3, 1));
        return list;
    }
}