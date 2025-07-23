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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthingocgiau_2123110205.adapter.CakeAdapter;
import com.example.nguyenthingocgiau_2123110205.CartManager;
import com.example.nguyenthingocgiau_2123110205.model.Cake;
import com.example.nguyenthingocgiau_2123110205.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView imgProduct;
    private TextView txtName, txtCategory, txtPrice, txtDescription;
    private Button btnBuyNow, btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        // Ánh xạ view
        imgProduct = findViewById(R.id.detailImage);
        txtName = findViewById(R.id.detailName);
        txtCategory = findViewById(R.id.txtCategory);
        txtPrice = findViewById(R.id.detailPrice);
        txtDescription = findViewById(R.id.txtDescription);
        btnBuyNow = findViewById(R.id.btnBuyNow);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        TextView btnToggle = findViewById(R.id.btnToggleDescription);

        // Nhận dữ liệu
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String category = intent.getStringExtra("category");
        double price = intent.getDoubleExtra("price", 0);
        int imageRes = intent.getIntExtra("image", R.drawable.cupcakes);
        String description = intent.getStringExtra("description");

        // Hiển thị dữ liệu
        if (name != null) txtName.setText(name);
        if (category != null) txtCategory.setText(category);
        txtPrice.setText(String.format("₫%.000f", price));
        imgProduct.setImageResource(imageRes);

        if (description != null && !description.isEmpty()) {
            txtDescription.setText(description);
        } else {
            txtDescription.setText("Đây là mô tả chi tiết của sản phẩm " + (name != null ? name : ""));
        }

        // Xem thêm mô tả
        final boolean[] isExpanded = {false};
        btnToggle.setOnClickListener(v -> {
            if (isExpanded[0]) {
                txtDescription.setMaxLines(4);
                btnToggle.setText("Xem thêm");
            } else {
                txtDescription.setMaxLines(Integer.MAX_VALUE);
                btnToggle.setText("Thu gọn");
            }
            isExpanded[0] = !isExpanded[0];
        });

        // Nút Mua ngay
        btnBuyNow.setOnClickListener(v ->
                Toast.makeText(this, "Mua ngay: " + name, Toast.LENGTH_SHORT).show()
        );

        // Nút Thêm vào giỏ hàng
        btnAddToCart.setOnClickListener(v -> {
            Cake cake = new Cake(name, "", category, price, imageRes, description);
            CartItem item = new CartItem(
                    name,
                    price,
                    1, // số lượng mặc định là 1
                    imageRes
            );
            CartManager.addToCart(item); // ✅ đúng kiểu CartItem

            Toast.makeText(this, "Đã thêm vào giỏ: " + name, Toast.LENGTH_SHORT).show();
        });

        // RecyclerView sản phẩm liên quan
        RecyclerView relatedRecyclerView = findViewById(R.id.recyclerCake);
        relatedRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Cake> relatedCakes = new ArrayList<>();
        relatedCakes.add(new Cake("Strawberry Cake", "Dâu", "Donuts", 29000, R.drawable.product1, "Bánh dâu tây ngọt dịu, được phủ lớp kem mềm mịn và trang trí dâu tươi mọng nước."));
        relatedCakes.add(new Cake("Vanilla Muffin", "Vani", "Donuts", 25000, R.drawable.product2, "Muffin vani thơm lừng, mềm xốp, là lựa chọn tuyệt vời cho bữa sáng hoặc bữa xế."));
        relatedCakes.add(new Cake("Lemon Tart", "Chanh", "Donuts", 35000, R.drawable.product3, "Tart chanh với lớp vỏ giòn rụm và nhân chanh chua ngọt, kích thích vị giác."));
        relatedCakes.add(new Cake("Chocolate Lava", "Socola", "Donuts", 39000, R.drawable.product4, "Bánh socola tan chảy với lớp nhân đậm vị cacao, hấp dẫn ngay từ lần cắn đầu tiên."));
        relatedCakes.add(new Cake("Matcha Cream Cake", "Trà xanh", "Muffin", 31000, R.drawable.product5, "Bánh trà xanh kết hợp kem béo nhẹ, thơm dịu vị matcha nguyên chất từ Nhật Bản."));
        relatedCakes.add(new Cake("Blueberry Cupcake", "Việt quất", "Cupcake", 28000, R.drawable.product6, "Cupcake việt quất ngọt thanh, xen lẫn hương vị trái cây tự nhiên và lớp kem mịn màng."));
        relatedCakes.add(new Cake("Cheesecake Slice", "Phô mai", "Slice", 36000, R.drawable.product7, "Miếng bánh cheesecake mềm mịn, béo thơm, thích hợp dùng lạnh cùng một tách trà."));
        relatedCakes.add(new Cake("Orange Tart", "Cam", "Tart", 34000, R.drawable.product8, "Tart cam chua ngọt nhẹ, giàu vitamin C với lớp vỏ vàng giòn và nhân trái cây tươi."));
        relatedCakes.add(new Cake("Pistachio Donut", "Hạt dẻ", "Donuts", 33000, R.drawable.product9, "Donut hạt dẻ thơm bùi, phủ lớp kem socola trắng cùng topping hạt dẻ giòn tan."));
        relatedCakes.add(new Cake("Coconut Cupcake", "Dừa", "Cupcake", 27000, R.drawable.product10, "Cupcake dừa ngọt ngào, được làm từ cơm dừa tươi và phủ kem dừa béo ngậy."));

        CakeAdapter adapter = new CakeAdapter(this, relatedCakes);
        relatedRecyclerView.setAdapter(adapter);
    }

    // Gắn menu toolbar (icon giỏ hàng)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    // Xử lý click trên toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_cart) {
            startActivity(new Intent(this, CartActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
