package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

        recyclerCart = findViewById(R.id.recyclerCart);
        txtTotal = findViewById(R.id.txtTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        checkboxSelectAll = findViewById(R.id.checkboxSelectAll);

        cartItems = new ArrayList<>(CartManager.getCartItems());
        adapter = new CartAdapter(this, cartItems, txtTotal, btnCheckout);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(adapter);

        // Select All Checkbox
        checkboxSelectAll.setOnCheckedChangeListener((btn, isChecked) -> {
            for (CartItem item : cartItems) {
                item.setSelected(isChecked);
            }
            adapter.notifyDataSetChanged();
            adapter.updateTotal();
        });

        // Nút Mua hàng
        btnCheckout.setOnClickListener(v -> {
            ArrayList<CartItem> purchasedItems = new ArrayList<>();
            double totalAmount = 0;

            for (CartItem item : cartItems) {
                if (item.isSelected()) {
                    purchasedItems.add(item);
                    totalAmount += item.getPrice() * item.getQuantity();
                }
            }

            if (purchasedItems.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn ít nhất một sản phẩm để mua!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Xoá sản phẩm đã mua khỏi giỏ
            cartItems.removeAll(purchasedItems);
            adapter.notifyDataSetChanged();
            adapter.updateTotal();
            Toast.makeText(this, "🎉 Mua hàng thành công!", Toast.LENGTH_SHORT).show();

            // Ngày mua và ngày giao dự kiến
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String purchaseDate = sdf.format(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 3);
            String deliveryDate = sdf.format(calendar.getTime());

            // Gửi dữ liệu qua OrderDetailActivity
            Intent intent = new Intent(this, OrderDetailActivity.class);
            intent.putExtra("purchasedItems", purchasedItems); // truyền đúng danh sách đã mua
            intent.putExtra("total", totalAmount);
            intent.putExtra("purchaseDate", purchaseDate);
            intent.putExtra("deliveryDate", deliveryDate);
            intent.putExtra("orderStatus", "Đã xử lý");
            startActivity(intent);
        });

        // Nút quay lại
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
