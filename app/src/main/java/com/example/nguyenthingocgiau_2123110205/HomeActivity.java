package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.PopupMenu;
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
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerCake;
    ArrayList<Cake> cakeList;
    CakeAdapter adapter;

    // Menu điều hướng
    TextView btnHome, btnAbout, btnShop, btnPages, btnBlog, btnContact;
    TextView[] menuButtons;

    // Flash Sale Timer
    TextView tvFlashSaleTimer;
    CountDownTimer countDownTimer;

    // Hàm animation cho ProgressBar
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

    // Active menu đang chọn
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

        // Banner chạy chữ
        TextView txtBanner = findViewById(R.id.txtBanner);
        txtBanner.setSelected(true);

        // Khởi tạo RecyclerView
        RecyclerView recyclerCategory = findViewById(R.id.recyclerCategory);
        recyclerCake = findViewById(R.id.recyclerCake);

        // Danh mục
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Donuts", R.drawable.cupcakes));
        categories.add(new Category("Cakes", R.drawable.donut2));
        categories.add(new Category("Cupcakes", R.drawable.donuts));
        categories.add(new Category("Padding", R.drawable.donut2));
        categories.add(new Category("Cakey", R.drawable.donut1));

        CategoryAdapter catAdapter = new CategoryAdapter(this, categories);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerCategory.setAdapter(catAdapter);

        // Danh sách bánh
        List<Cake> cakes = new ArrayList<>();
        cakes.add(new Cake("Choco Donut", "Chocolate", 15.5, R.drawable.product1));
        cakes.add(new Cake("Choco Cupcakes", "Donuts", 13.5, R.drawable.product2));
        cakes.add(new Cake("Choco Donut", "Chocolate", 15.5, R.drawable.product3));
        cakes.add(new Cake("Choco Cupcakes", "Donuts", 13.5, R.drawable.donut3));
        cakes.add(new Cake("Choco Donut", "Chocolate", 15.5, R.drawable.donut2));
        cakes.add(new Cake("Choco Cupcakes", "Donuts", 13.5, R.drawable.donut3));

        CakeAdapter cakeAdapter = new CakeAdapter(this, cakes);
        recyclerCake.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerCake.setNestedScrollingEnabled(false);
        recyclerCake.setAdapter(cakeAdapter);

        // Nút giỏ hàng
        ImageView btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // Nút tài khoản/avatar
        ImageView btnAccount = findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(HomeActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.drawe_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.menu_signin) {
                    startActivity(new Intent(this, SinginActivity.class));
                } else if (id == R.id.menu_signout) {
                    Toast.makeText(this, "Sign Out clicked", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.menu_settings) {
                    Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.menu_register) {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                return true;
            });

            popup.show();
        });
        // Nút tài khoản/avatar
        ImageView btnMenu = findViewById(R.id.btnMenu);  // đúng id
        btnMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(HomeActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.menu_drawer, popup.getMenu()); // đúng tên file

            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.menu_home) {
                    startActivity(new Intent(this, HomeActivity.class));
                } else if (id == R.id.menu_shop) {
                    startActivity(new Intent(this, ShopActivity.class));
                } else if (id == R.id.menu_about) {
                    startActivity(new Intent(this, AboutActivity.class));
                } else if (id == R.id.menu_contact) {
                    startActivity(new Intent(this, ContactActivity.class));
                }else if (id == R.id.menu_pages) {
                    startActivity(new Intent(this, PageActivity.class));
                }
                return true;
            });

            popup.show();  // QUAN TRỌNG
        });

        // Flash Sale Countdown
        tvFlashSaleTimer = findViewById(R.id.tvFlashSaleTimer);
        long duration = 5 * 60 * 1000; // 5 phút

        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int hours = (int) (millisUntilFinished / (1000 * 60 * 60));
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int seconds = (int) ((millisUntilFinished / 1000) % 60);

                String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
                tvFlashSaleTimer.setText(timeFormatted);
            }

            @Override
            public void onFinish() {
                tvFlashSaleTimer.setText("00:00:00");
            }
        };

        countDownTimer.start();
    }
}
