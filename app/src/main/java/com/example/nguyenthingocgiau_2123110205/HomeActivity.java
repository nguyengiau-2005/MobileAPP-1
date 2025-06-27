package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerCake;
    ArrayList<Cake> cakeList;
    CakeAdapter adapter;
//xuly menu
    TextView btnHome, btnAbout, btnShop, btnPages, btnBlog, btnContact;
    TextView[] menuButtons;
    //about cake
    //aboutcake

    private void animateProgress(ProgressBar progressBar, TextView textView, int target) {
        new Thread(() -> {
            int progress = 0;
            while (progress <= target) {
                int finalProgress = progress;
                runOnUiThread(() -> {
                    progressBar.setProgress(finalProgress);
                    textView.setText(textView.getText().toString().split(":")[0] + ": " + finalProgress + "%");
                });
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress++;
            }
        }).start();
    }
    //menu_bar
    private void setActive(TextView selectedBtn) {
        for (TextView btn : menuButtons) {
            btn.setTextAppearance(R.style.MenuItemStyle);
        }

        selectedBtn.setTextAppearance(R.style.MenuItemStyle_Active);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//banner
        TextView txtBanner = findViewById(R.id.txtBanner);
        txtBanner.setSelected(true); // Kích hoạt hiệu ứng chạy chữ


        RecyclerView recyclerCategory = findViewById(R.id.recyclerCategory);
        RecyclerView recyclerCake = findViewById(R.id.recyclerCake);

        // Danh mục
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Donuts", R.drawable.donut1));
        categories.add(new Category("Cakes", R.drawable.donut2));
        categories.add(new Category("Cupcakes", R.drawable.donut3));
        categories.add(new Category("Padding", R.drawable.donut2));
        categories.add(new Category("Cakey", R.drawable.donut1));

        CategoryAdapter catAdapter = new CategoryAdapter(this, categories);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerCategory.setAdapter(catAdapter);

        // Bánh
        List<Cake> cakes = new ArrayList<>();
        cakes.add(new Cake("Choco Donut", "Chocolate", 15.5, R.drawable.product1));
        cakes.add(new Cake("Choco Cupcakes", "Donuts", 13.5, R.drawable.product2));
        cakes.add(new Cake("Choco Donut", "Chocolate", 15.5, R.drawable.product3));
        cakes.add(new Cake("Choco Cupcakes", "Donuts", 13.5, R.drawable.donut3));
        cakes.add(new Cake("Choco Donut", "Chocolate", 15.5, R.drawable.donut2));
        cakes.add(new Cake("Choco Cupcakes", "Donuts", 13.5, R.drawable.donut3));
        CakeAdapter cakeAdapter = new CakeAdapter(this, cakes);
        recyclerCake.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerCake.setNestedScrollingEnabled(false);
        recyclerCake.setAdapter(cakeAdapter);
        //cart
        ImageView btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // Khởi tạo các nút
        btnHome = findViewById(R.id.btnHome);
        btnAbout = findViewById(R.id.btnAbout);
        btnShop = findViewById(R.id.btnShop);
        btnPages = findViewById(R.id.btnPages);
        btnBlog = findViewById(R.id.btnBlog);
        btnContact = findViewById(R.id.btnContact);
        // Cho dễ quản lý
        menuButtons = new TextView[]{btnHome, btnAbout, btnShop, btnPages, btnBlog, btnContact};

            //chuyen menu
            // Gán sự kiện click
            for (TextView btn : menuButtons) {
                btn.setOnClickListener(v -> {
                    setActive(btn);

                    if (btn == btnHome) {
                        startActivity(new Intent(this, HomeActivity.class));
                        finish();
                    } else if (btn == btnShop) {
                        startActivity(new Intent(this, ShopActivity.class));
                        finish();
                    } else if (btn == btnAbout) {
                        startActivity(new Intent(this, AboutActivity.class));
                        finish();
                    }

                    Toast.makeText(this, "Chọn: " + btn.getText(), Toast.LENGTH_SHORT).show();
                });
        }
    }
}