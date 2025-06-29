package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    RecyclerView recyclerCake;
    List<Cake> allCakes = new ArrayList<>();
    List<Cake> currentCakes = new ArrayList<>();
    CakeAdapter adapter;

    Spinner spinnerCategory, spinnerSort;
    TextView[] menuButtons;

    int currentPage = 1;
    int itemsPerPage = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView btnProfile = findViewById(R.id.btnAccount);
        btnProfile.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(ShopActivity.this, v);  // ⬅ Đây là khai báo popup đúng chỗ
            popup.getMenuInflater().inflate(R.menu.drawe_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();

                SharedPreferences prefs = getSharedPreferences("USER", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                if (id == R.id.menu_signin) {
                    startActivity(new Intent(this, MainActivity.class));
                } else if (id == R.id.menu_signout) {
                    editor.clear();
                    editor.apply();
                    Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.menu_settings) {
                    Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                }
                return true;
            });

            popup.show(); // ⬅ Show menu sau khi cấu hình
        });


        // Spinner
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerSort = findViewById(R.id.spinnerSort);
        setupSpinners();

        // RecyclerView sản phẩm
        recyclerCake = findViewById(R.id.recyclerCake);
        recyclerCake.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CakeAdapter(this, currentCakes);
        recyclerCake.setAdapter(adapter);

        // Dữ liệu mẫu
        allCakes.add(new Cake("Strawberry Cake", "Cake", 15.5, R.drawable.donut1));
        allCakes.add(new Cake("Chocolate Cake", "Cake", 18.0, R.drawable.donut3));
        allCakes.add(new Cake("Matcha Delight", "Cake", 16.2, R.drawable.donut1));
        allCakes.add(new Cake("Vanilla Dream", "Cake", 12.0, R.drawable.donut2));
        allCakes.add(new Cake("Lemon Light", "Cake", 14.3, R.drawable.donut1));
        allCakes.add(new Cake("Berry Mix", "Cake", 19.0, R.drawable.donut2));
        allCakes.add(new Cake("Caramel Crunch", "Cake", 17.7, R.drawable.donut1));
        allCakes.add(new Cake("Red Velvet", "Cake", 20.0, R.drawable.donut2));
        allCakes.add(new Cake("Coconut Cream", "Cake", 13.5, R.drawable.donut1));
        allCakes.add(new Cake("Coffee Cake", "Cake", 15.9, R.drawable.donut2));
        allCakes.add(new Cake("Pineapple Cake", "Cake", 14.2, R.drawable.donut3));

        updateData();

        // Phân trang
        findViewById(R.id.btnPage1).setOnClickListener(v -> {
            currentPage = 1;
            updateData();
        });
        findViewById(R.id.btnPage2).setOnClickListener(v -> {
            currentPage = 2;
            updateData();
        });
        findViewById(R.id.btnPage3).setOnClickListener(v -> {
            currentPage = 3;
            updateData();
        });
        findViewById(R.id.btnNext).setOnClickListener(v -> {
            int maxPage = (int) Math.ceil(allCakes.size() / (double) itemsPerPage);
            if (currentPage < maxPage) {
                currentPage++;
                updateData();
            }
        });

        // Danh mục ngang
        RecyclerView recyclerCategory = findViewById(R.id.recyclerCategory);
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Donuts", R.drawable.donut1));
        categories.add(new Category("Cakes", R.drawable.donut2));
        categories.add(new Category("Cupcakes", R.drawable.donut3));

        CategoryAdapter catAdapter = new CategoryAdapter(this, categories);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerCategory.setAdapter(catAdapter);

        // Giỏ hàng
        ImageView btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(ShopActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // Profile menu (avatar tròn)
        ImageView btnAccount = findViewById(R.id.btnAccount);
        btnProfile.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(ShopActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.drawe_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.menu_signin) {
                    Intent intent = new Intent(ShopActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (id == R.id.menu_signout) {
                    Toast.makeText(this, "Sign Out clicked", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.menu_settings) {
                    Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                }
                return true;
            });

            popup.show();
        });

        // Nút tài khoản/avatar
        ImageView btnMenu = findViewById(R.id.btnMenu);  // đúng id
        btnMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(ShopActivity.this, v);
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
    }

    private void setupSpinners() {
        String[] categories = {"All", "Cake", "Donut", "Ice Cream", "Drink"};
        String[] sortOptions = {"Default", "Price Low to High", "Price High to Low"};

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        ArrayAdapter<String> sortAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sortOptions);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(sortAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = categories[position];
                Toast.makeText(ShopActivity.this, "Filter by: " + selected, Toast.LENGTH_SHORT).show();
                updateData();
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = sortOptions[position];
                Toast.makeText(ShopActivity.this, "Sort by: " + selected, Toast.LENGTH_SHORT).show();
                if (selected.equals("Price Low to High")) {
                    Collections.sort(allCakes, Comparator.comparingDouble(Cake::getPrice));
                } else if (selected.equals("Price High to Low")) {
                    Collections.sort(allCakes, (a, b) -> Double.compare(b.getPrice(), a.getPrice()));
                }
                updateData();
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void updateData() {
        int start = (currentPage - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, allCakes.size());

        currentCakes.clear();
        currentCakes.addAll(allCakes.subList(start, end));
        adapter.notifyDataSetChanged();

        TextView tvResult = findViewById(R.id.tvResult);
        tvResult.setText("Showing " + (start + 1) + " - " + end + " of " + allCakes.size() + " results");
    }

    private void setActive(TextView selectedBtn) {
        for (TextView btn : menuButtons) {
            btn.setTextAppearance(R.style.MenuItemStyle);
        }
        selectedBtn.setTextAppearance(R.style.MenuItemStyle_Active);
    }
}
