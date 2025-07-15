package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import com.example.nguyenthingocgiau_2123110205.adapter.CakeAdapter;
import com.example.nguyenthingocgiau_2123110205.adapter.CategoryAdapter;
import com.example.nguyenthingocgiau_2123110205.model.Cake;
import com.example.nguyenthingocgiau_2123110205.model.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    RecyclerView recyclerCake;
    List<Cake> allCakes = new ArrayList<>();
    List<Cake> filteredCakes = new ArrayList<>();
    List<Cake> currentCakes = new ArrayList<>();
    CakeAdapter adapter;

    Spinner spinnerCategory, spinnerSort;

    int currentPage = 1;
    int itemsPerPage = 6;

    String selectedCategory = "All";

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

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerSort = findViewById(R.id.spinnerSort);
        recyclerCake = findViewById(R.id.recyclerCake);
        recyclerCake.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CakeAdapter(this, currentCakes);
        recyclerCake.setAdapter(adapter);

        setupSpinners();
        loadData();

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
            int maxPage = (int) Math.ceil(filteredCakes.size() / (double) itemsPerPage);
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
        findViewById(R.id.btnCart).setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class)));

        // Menu account


        // Avatar - tài khoản
    }

    private void setupSpinners() {
        String[] categories = {"All", "Cake", "Donut", "Ice Cream", "Drink"};
        String[] sortOptions = {"Default", "Price Low to High", "Price High to Low"};

        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(catAdapter);

        ArrayAdapter<String> sortAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sortOptions);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(sortAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = categories[position];
                currentPage = 1;
                applyFilter();
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = sortOptions[position];
                if (selected.equals("Price Low to High")) {
                    Collections.sort(allCakes, Comparator.comparingDouble(Cake::getPrice));
                } else if (selected.equals("Price High to Low")) {
                    Collections.sort(allCakes, (a, b) -> Double.compare(b.getPrice(), a.getPrice()));
                }
                applyFilter();
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void loadData() {
        allCakes.add(new Cake("Strawberry Cake", "Dâu", "Cake", 15.5, R.drawable.donut1));
        allCakes.add(new Cake("Choco Donut", "Chocolate", "Donut", 13.0, R.drawable.donut2));
        allCakes.add(new Cake("Vanilla Muffin", "Vani", "Muffin", 12.5, R.drawable.donut3));
        allCakes.add(new Cake("Lemon Tart", "Chanh", "Tart", 14.0, R.drawable.cake3));
        allCakes.add(new Cake("Matcha Cake", "Matcha", "Cake", 18.0, R.drawable.donut1));
        allCakes.add(new Cake("Blueberry Donut", "Việt quất", "Donut", 16.5, R.drawable.donut2));
        allCakes.add(new Cake("Red Velvet", "Dâu đỏ", "Cake", 20.0, R.drawable.cake2));
        allCakes.add(new Cake("Mango Tart", "Xoài", "Tart", 17.0, R.drawable.donut3));
        allCakes.add(new Cake("Coconut Muffin", "Dừa", "Muffin", 11.5, R.drawable.cake1));
        allCakes.add(new Cake("Caramel Donut", "Caramel", "Donut", 15.0, R.drawable.donut2));

        applyFilter();
    }

    private void applyFilter() {
        filteredCakes.clear();
        for (Cake c : allCakes) {
            if (selectedCategory.equals("All") || c.getCategory().equalsIgnoreCase(selectedCategory)) {
                filteredCakes.add(c);
            }
        }
        updateData();
    }

    private void updateData() {
        int start = (currentPage - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, filteredCakes.size());

        currentCakes.clear();
        if (start < filteredCakes.size()) {
            currentCakes.addAll(filteredCakes.subList(start, end));
        }
        adapter.notifyDataSetChanged();

        TextView tvResult = findViewById(R.id.tvResult);
        tvResult.setText("Hiển thị " + (start + 1) + " - " + end + " / " + filteredCakes.size() + " sản phẩm");
    }
}
