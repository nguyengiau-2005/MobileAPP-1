package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;
import com.example.nguyenthingocgiau_2123110205.adapter.CakeAdapter;



import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthingocgiau_2123110205.model.Cake;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView imgProduct;
    private TextView txtName, txtCategory, txtPrice, txtDescription;
    private Button btnBuyNow, btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /* ───────────── Toolbar ───────────── */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        /* ───────────── Khởi tạo View ───────────── */
        imgProduct = findViewById(R.id.detailImage);
        txtName = findViewById(R.id.detailName);
        txtCategory = findViewById(R.id.txtCategory);
        txtPrice = findViewById(R.id.detailPrice);
        txtDescription = findViewById(R.id.txtDescription);
        btnBuyNow = findViewById(R.id.btnBuyNow);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        /* ───────────── Nhận & gán dữ liệu chi tiết ───────────── */
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String category = intent.getStringExtra("category");
        double price = intent.getDoubleExtra("price", 0);
        int imageRes = intent.getIntExtra("image", R.drawable.cupcakes);
        String description = intent.getStringExtra("description");

        if (name != null) txtName.setText(name);
        if (category != null) txtCategory.setText(category);
        txtPrice.setText(String.format("₫%.0f", price));
        imgProduct.setImageResource(imageRes);

        if (description != null && !description.isEmpty()) {
            txtDescription.setText(description);
        } else {
            txtDescription.setText("Đây là mô tả chi tiết của sản phẩm " + (name != null ? name : ""));
        }

        /* ───────────── Sự kiện nút ───────────── */
        btnBuyNow.setOnClickListener(v ->
                Toast.makeText(this, "Mua ngay: " + name, Toast.LENGTH_SHORT).show()
        );

        btnAddToCart.setOnClickListener(v ->
                Toast.makeText(this, "Đã thêm vào giỏ: " + name, Toast.LENGTH_SHORT).show()
        );

        /* ───────────── RecyclerView "Related Products" ───────────── */
        RecyclerView relatedRecyclerView = findViewById(R.id.recyclerCake);
        relatedRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Cake> relatedCakes = new ArrayList<>();
        relatedCakes.add(new Cake("Strawberry Cake", "Dâu", "Donuts", 29000, R.drawable.cake3));
        relatedCakes.add(new Cake("Vanilla Muffin", "Vani", "Donuts", 25000, R.drawable.donut3));
        relatedCakes.add(new Cake("Lemon Tart", "Chanh", "Donuts", 35000, R.drawable.donut1));

        CakeAdapter adapter = new CakeAdapter(this, relatedCakes);
        relatedRecyclerView.setAdapter(adapter);
    }

    /* ───────────── Gắn menu (icon giỏ hàng) ───────────── */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    /* ───────────── Xử lý click nút ← & icon giỏ ───────────── */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_cart) {
            Toast.makeText(this, "Đi đến Giỏ hàng", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, CartActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
