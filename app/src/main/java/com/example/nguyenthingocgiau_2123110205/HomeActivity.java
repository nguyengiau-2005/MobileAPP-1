package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
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

import com.example.nguyenthingocgiau_2123110205.adapter.BannerAdapter;
import com.example.nguyenthingocgiau_2123110205.adapter.CakeAdapter;
import com.example.nguyenthingocgiau_2123110205.adapter.CategoryAdapter;
import com.example.nguyenthingocgiau_2123110205.model.Banner;
import com.example.nguyenthingocgiau_2123110205.model.Cake;
import com.example.nguyenthingocgiau_2123110205.model.Category;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerCake;
    ArrayList<Cake> cakeList;
    CakeAdapter adapter;

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

        // === DANH MỤC ===
        RecyclerView recyclerCategory = findViewById(R.id.recyclerCategory);
        recyclerCake = findViewById(R.id.recyclerCake);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Donuts", R.drawable.cake1));
        categoryList.add(new Category("Cupcakes", R.drawable.cake2));
        categoryList.add(new Category("Cheesecakes", R.drawable.cake3));
        categoryList.add(new Category("Pancakes", R.drawable.cake4));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerCategory.setLayoutManager(layoutManager);
        CategoryAdapter catAdapter = new CategoryAdapter(this, categoryList);
        recyclerCategory.setAdapter(catAdapter);

        // Tự cuộn danh mục
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int position = 0;

            @Override
            public void run() {
                if (position >= catAdapter.getItemCount()) {
                    position = 0;
                }
                recyclerCategory.smoothScrollToPosition(position++);
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);

        // === SẢN PHẨM ===
        List<Cake> cakes = new ArrayList<>();
        cakes.add(new Cake("Choco Cupcakes", "Chocolate", "Cupcakes", 13.5, R.drawable.cake3));
        cakes.add(new Cake("Strawberry Donut", "Strawberry", "Donuts", 14.0, R.drawable.cake2));
        cakes.add(new Cake("Vanilla Cheesecake", "Vanilla", "Cheesecakes", 18.0, R.drawable.cake4));
        cakes.add(new Cake("Matcha Pancake", "Matcha", "Pancakes", 16.5, R.drawable.cake1));
        cakes.add(new Cake("Blueberry Donut", "Blueberry", "Donuts", 15.5, R.drawable.donut1));
        cakes.add(new Cake("Red Velvet Cupcake", "Red Velvet", "Cupcakes", 17.0, R.drawable.cake3));
        cakes.add(new Cake("Mango Cheesecake", "Mango", "Cheesecakes", 19.5, R.drawable.cake2));
        cakes.add(new Cake("Caramel Pancake", "Caramel", "Pancakes", 14.5, R.drawable.cake4));
        cakes.add(new Cake("Lemon Donut", "Lemon", "Donuts", 13.0, R.drawable.cake1));
        cakes.add(new Cake("Chocolate Lava Cupcake", "Chocolate", "Cupcakes", 20.0, R.drawable.cake3));

        cakeList = new ArrayList<>(cakes);
        adapter = new CakeAdapter(this, cakeList);
        recyclerCake.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerCake.setNestedScrollingEnabled(false);
        recyclerCake.setAdapter(adapter);

        // === TÌM KIẾM ===
        EditText edtSearch = findViewById(R.id.edtSearch);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCakes(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

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

    // === HÀM LỌC SẢN PHẨM ===
    private void filterCakes(String keyword) {
        List<Cake> filteredList = new ArrayList<>();
        for (Cake cake : cakeList) {
            if (cake.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(cake);
            }
        }
        adapter.updateData(filteredList);
    }
}
