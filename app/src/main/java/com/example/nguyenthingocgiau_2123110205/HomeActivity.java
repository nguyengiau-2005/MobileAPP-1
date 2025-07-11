package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
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
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerCake;
    ArrayList<Cake> cakeList;
    CakeAdapter adapter;

    TextView btnHome, btnAbout, btnShop, btnPages, btnBlog, btnContact;
    TextView[] menuButtons;

    TextView tvFlashSaleTimer;
    CountDownTimer countDownTimer;

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

        // === BANNER ===
        ViewPager2 viewPager = findViewById(R.id.bannerSlider);
        List<Banner> banners = new ArrayList<>();
        banners.add(new Banner(R.drawable.banner1, "Big Sale 50% Off"));
        banners.add(new Banner(R.drawable.banner2, "Buy 1 Get 1 Free"));
        banners.add(new Banner(R.drawable.banner3, "Free Shipping Today"));

        viewPager.setAdapter(new BannerAdapter(banners));

        // Auto-scroll
        Handler bannerHandler = new Handler();
        Runnable bannerRunnable = new Runnable() {
            int currentPage = 0;

            @Override
            public void run() {
                if (viewPager.getAdapter() != null) {
                    int totalPages = viewPager.getAdapter().getItemCount();
                    currentPage = (currentPage + 1) % totalPages;
                    viewPager.setCurrentItem(currentPage, true);
                }
                bannerHandler.postDelayed(this, 3000);
            }
        };
        bannerHandler.postDelayed(bannerRunnable, 3000);

        // === DANH MỤC & SẢN PHẨM ===
        RecyclerView recyclerCategory = findViewById(R.id.recyclerCategory);
        recyclerCake = findViewById(R.id.recyclerCake);

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Donuts", R.drawable.donut2));
        categories.add(new Category("Cakes", R.drawable.donut2));

        CategoryAdapter catAdapter = new CategoryAdapter(this, categories);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerCategory.setAdapter(catAdapter);

        List<Cake> cakes = new ArrayList<>();
        cakes.add(new Cake("Choco Cupcakes", "Donuts", 13.5, R.drawable.donut3));
        cakes.add(new Cake("Choco Donut", "Chocolate", 15.5, R.drawable.donut2));
        cakes.add(new Cake("Choco Cupcakes", "Donuts", 13.5, R.drawable.donut3));

        CakeAdapter cakeAdapter = new CakeAdapter(this, cakes);
        recyclerCake.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerCake.setNestedScrollingEnabled(false);
        recyclerCake.setAdapter(cakeAdapter);

        // === GIỎ HÀNG ===
        ImageView btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // === BOTTOM NAVIGATION ===
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_shop) {
                startActivity(new Intent(this, ShopActivity.class));
                return true;
            } else if (id == R.id.nav_account) {
                PopupMenu popupMenu = new PopupMenu(this, bottomNav);

                popupMenu.getMenu().add(Menu.NONE, 1, 0, "Đăng nhập");
                popupMenu.getMenu().add(Menu.NONE, 2, 1, "Đăng ký");
                popupMenu.getMenu().add(Menu.NONE, 3, 2, "Đăng xuất");

                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    int itemId = menuItem.getItemId();
                    if (itemId == 1) {
                        startActivity(new Intent(HomeActivity.this, SiginActivity.class));
                    } else if (itemId == 2) {
                        startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
                    } else if (itemId == 3) {
                        Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                });

                popupMenu.show();
                return true;
            }
            return false;
        });
    }
}
