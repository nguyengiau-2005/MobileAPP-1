package com.example.nguyenthingocgiau_2123110205;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthingocgiau_2123110205.adapter.OrderAdapter;
import com.example.nguyenthingocgiau_2123110205.model.CartItem;
import com.example.nguyenthingocgiau_2123110205.model.OrderItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class OrderDetailActivity extends AppCompatActivity {

    TextView txtTotalAmount;
    ImageView btnBack;
    RecyclerView recyclerOrderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        txtTotalAmount = findViewById(R.id.txtTotalAmount);
        btnBack = findViewById(R.id.btnBack);
        recyclerOrderItems = findViewById(R.id.recyclerOrderItems);

        // Nhận dữ liệu từ Intent (CartItem)
        ArrayList<CartItem> cartItems = (ArrayList<CartItem>) getIntent().getSerializableExtra("purchasedItems");
        if (cartItems == null) cartItems = new ArrayList<>();

        // Convert sang OrderItem
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cartItems) {
            orderItems.add(new OrderItem(
                    item.getProductName(),
                    item.getQuantity(),
                    (int) item.getOldPrice(),
                    (int) item.getNewPrice(),
                    item.getImageResource()
            ));
        }

        // Setup RecyclerView với OrderAdapter
        OrderAdapter adapter = new OrderAdapter(this, orderItems);
        recyclerOrderItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrderItems.setAdapter(adapter);

        // Tính tổng tiền
        int total = 0;
        for (OrderItem item : orderItems) {
            total += item.getNewPrice() * item.getQuantity();
        }

        txtTotalAmount.setText("Thành tiền: " + formatCurrency(total));

        btnBack.setOnClickListener(v -> finish());
    }

    private String formatCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return formatter.format(amount);
    }
}
