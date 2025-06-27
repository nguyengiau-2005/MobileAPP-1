package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView imgProduct;
    TextView txtName, txtCategory, txtPrice;
    Button btnBuyNow, btnAddToCart;
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
        // Gán các view từ layout
        imgProduct = findViewById(R.id.imgProduct);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtCategory = findViewById(R.id.txtCategory);
        TextView txtDescription = findViewById(R.id.txtDescription);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBuyNow = findViewById(R.id.btnBuyNow);

        // Lấy dữ liệu từ Intent
        String name = getIntent().getStringExtra("name");
        String category = getIntent().getStringExtra("category");
        double price = getIntent().getDoubleExtra("price", 0);
        int imageRes = getIntent().getIntExtra("image", R.drawable.donut1);

        // Hiển thị dữ liệu
        txtName.setText(name);
        txtCategory.setText("Loại: " + category);
        txtPrice.setText(String.format("₫%.0f", price));
        txtDescription.setText("Đây là mô tả chi tiết của sản phẩm " + name); // bạn có thể sửa

        imgProduct.setImageResource(imageRes);

        // Xử lý sự kiện nút
        btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(this, name + " đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        });

        btnBuyNow.setOnClickListener(v -> {
            Toast.makeText(this, "Bạn chọn mua ngay sản phẩm: " + name, Toast.LENGTH_SHORT).show();
            // Chuyển sang activity thanh toán (nếu có)
            // startActivity(new Intent(this, CheckoutActivity.class));
        });
    }
}