package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
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
import com.example.nguyenthingocgiau_2123110205.adapter.TopBakeAdapter;
import com.example.nguyenthingocgiau_2123110205.model.Bake;
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
        categoryList.add(new Category("Donuts", R.drawable.cate1));
        categoryList.add(new Category("Cupcakes", R.drawable.cate2));
        categoryList.add(new Category("Cheesecakes", R.drawable.cate3));
        categoryList.add(new Category("Pancakes", R.drawable.cate4));
        categoryList.add(new Category("Muffins", R.drawable.cate5));
        categoryList.add(new Category("Tarts", R.drawable.cate6));

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
        cakes.add(new Cake("Choco Cupcakes", "Chocolate", "Cupcakes", 13500, R.drawable.product1,
                "Bánh cupcake socola mềm mịn, phủ lớp kem tươi ngọt ngào, thích hợp cho buổi trà chiều."));
        cakes.add(new Cake("Strawberry Donut", "Strawberry", "Donuts", 14000, R.drawable.product2,
                "Donut dâu tây thơm ngon, phủ lớp kem dâu tươi, vị ngọt dịu."));
        cakes.add(new Cake("Vanilla Cheesecake", "Vanilla", "Cheesecakes", 18000, R.drawable.product3,
                "Cheesecake vani mịn mượt, hòa quyện giữa vị béo và ngọt thanh."));
        cakes.add(new Cake("Matcha Pancake", "Matcha", "Pancakes", 16500, R.drawable.product4,
                "Bánh pancake trà xanh đậm vị, kèm lớp syrup ngọt nhẹ cho buổi sáng năng lượng."));
        cakes.add(new Cake("Blueberry Donut", "Blueberry", "Donuts", 15500, R.drawable.product5,
                "Donut vị việt quất, giòn bên ngoài – mềm bên trong, thơm hương trái cây."));
        cakes.add(new Cake("Red Velvet Cupcake", "Red Velvet", "Cupcakes", 17000, R.drawable.product6,
                "Bánh Red Velvet sang trọng, phủ kem phô mai, phù hợp tiệc sinh nhật."));
        cakes.add(new Cake("Mango Cheesecake", "Mango", "Cheesecakes", 19500, R.drawable.product7,
                "Cheesecake xoài mát lạnh, lớp xoài tươi phía trên tạo hương vị nhiệt đới."));
        cakes.add(new Cake("Caramel Pancake", "Caramel", "Pancakes", 14500, R.drawable.product8,
                "Pancake caramel mềm, phủ sốt caramel ngọt ngào, đậm đà."));
        cakes.add(new Cake("Lemon Donut", "Lemon", "Donuts", 13000, R.drawable.product9,
                "Donut chanh thanh mát, lớp vỏ giòn, vị chua nhẹ cân bằng."));
        cakes.add(new Cake("Chocolate Lava Cupcake", "Chocolate", "Cupcakes", 20000, R.drawable.product10,
                "Cupcake lava với nhân socola nóng chảy bên trong, cực kỳ hấp dẫn."));
        cakes.add(new Cake("Blueberry Cheesecake", "Blueberry", "Cheesecakes", 18500, R.drawable.product11,
                "Cheesecake việt quất chua ngọt, lớp kem phô mai tan chảy."));
        cakes.add(new Cake("Oreo Cupcake", "Oreo", "Cupcakes", 17500, R.drawable.product13,
                "Cupcake Oreo thơm lừng, topping bánh quy Oreo giòn tan."));

        cakeList = new ArrayList<>(cakes);
        adapter = new CakeAdapter(this, cakeList);
        recyclerCake.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerCake.setNestedScrollingEnabled(false);
        recyclerCake.setAdapter(adapter);
// === TOP BÁNH ===
        RecyclerView recyclerTopBake = findViewById(R.id.recyclerTopBake);
        List<Bake> bakes = new ArrayList<>();
        bakes.add(new Bake(
                R.drawable.bunny_twist,
                "Bunny Twist Rolls",
                "Những cuộn bánh hình chú thỏ đáng yêu, làm từ bột quế thơm lừng và phủ nhẹ đường bột. Phù hợp cho trẻ nhỏ và tiệc trà."
        ));

        bakes.add(new Bake(
                R.drawable.donut_choco,
                "Choco Donut",
                "Bánh donut mềm mịn phủ lớp socola đắng ngọt, tan ngay trong miệng. Món ăn vặt yêu thích mọi thời đại."
        ));

        bakes.add(new Bake(
                R.drawable.strawberry_pie,
                "Strawberry Pie",
                "Bánh pie dâu tây tươi ngon, lớp vỏ giòn rụm kết hợp cùng nhân dâu ngọt dịu và chút hương vani tự nhiên."
        ));        // thêm nếu cần

        TopBakeAdapter topBakeAdapter = new TopBakeAdapter(bakes);
        recyclerTopBake.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerTopBake.setAdapter(topBakeAdapter);
        Handler bakeHandler = new Handler();
        Runnable bakeRunnable = new Runnable() {
            int position = 0;

            @Override
            public void run() {
                if (position >= bakes.size()) {
                    position = 0;
                }
                recyclerTopBake.smoothScrollToPosition(position++);
                bakeHandler.postDelayed(this, 3000); // 3 giây cuộn 1 lần
            }
        };
        bakeHandler.postDelayed(bakeRunnable, 3000);



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

        // Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_shop) {
                startActivity(new Intent(this, ShopActivity.class));
                return true;
            } else if (id == R.id.nav_chat) {
                startActivity(new Intent(this, ContactActivity.class));
                return true;
            } else if (id == R.id.nav_account) {
                View accountItemView = bottomNav.findViewById(R.id.nav_account);
                PopupMenu popupMenu = new PopupMenu(this, accountItemView);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    popupMenu.setGravity(Gravity.END);
                }

                popupMenu.getMenu().add(Menu.NONE, 1, 0, "Đăng nhập");
                popupMenu.getMenu().add(Menu.NONE, 2, 1, "Đăng ký");
                popupMenu.getMenu().add(Menu.NONE, 3, 2, "Đăng xuất");

                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    int itemId = menuItem.getItemId();
                    if (itemId == 1) {
                        startActivity(new Intent(this, SiginActivity.class));
                    } else if (itemId == 2) {
                        startActivity(new Intent(this, RegisterActivity.class));
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
