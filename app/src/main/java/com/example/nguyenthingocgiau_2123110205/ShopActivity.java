package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    RecyclerView recyclerCake;
    List<Cake> allCakes = new ArrayList<>();
    List<Cake> currentCakes = new ArrayList<>();
    CakeAdapter adapter;

    EditText edtSearch;
    TextView tvResult;

    int currentPage = 1;
    int itemsPerPage = 6;

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

        // Ánh xạ View
        edtSearch = findViewById(R.id.edtSearch);
        tvResult = findViewById(R.id.tvResult);
        recyclerCake = findViewById(R.id.recyclerCake);
        recyclerCake.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CakeAdapter(this, currentCakes);
        recyclerCake.setAdapter(adapter);

        // Load dữ liệu
        loadData();

        // Tìm kiếm
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCakes(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Phân trang
        findViewById(R.id.btnPage1).setOnClickListener(v -> {
            currentPage = 1;
            updateData(allCakes);
        });
        findViewById(R.id.btnPage2).setOnClickListener(v -> {
            currentPage = 2;
            updateData(allCakes);
        });
        findViewById(R.id.btnPage3).setOnClickListener(v -> {
            currentPage = 3;
            updateData(allCakes);
        });
        findViewById(R.id.btnNext).setOnClickListener(v -> {
            int maxPage = (int) Math.ceil(allCakes.size() / (double) itemsPerPage);
            currentPage = (currentPage % maxPage) + 1;
            updateData(allCakes);
        });

        // Giỏ hàng
        findViewById(R.id.btnCart).setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
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
            }else if (id == R.id.nav_chat) {
                startActivity(new Intent(this, ContactActivity.class));
                return true;
            } else if (id == R.id.nav_account) {
                View accountItemView = bottomNav.findViewById(R.id.nav_account);
                PopupMenu popupMenu = new PopupMenu(this, accountItemView);

                popupMenu.setGravity(Gravity.END);

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

    private void loadData() {
        allCakes.add(new Cake("Strawberry Cake", "Dâu", "Cake", 15500, R.drawable.product1,
                "Bánh kem dâu mềm mịn, vị ngọt nhẹ hòa quyện với dâu tươi, thích hợp cho mọi dịp."));
        allCakes.add(new Cake("Choco Donut", "Chocolate", "Donut", 13200, R.drawable.product2,
                "Donut phủ socola đậm vị, lớp vỏ giòn rụm, thơm lừng mỗi lần cắn."));
        allCakes.add(new Cake("Vanilla Muffin", "Vani", "Muffin", 12500, R.drawable.product3,
                "Muffin vị vani truyền thống, mềm xốp, thơm ngọt dịu dàng."));
        allCakes.add(new Cake("Lemon Tart", "Chanh", "Tart", 14900, R.drawable.product4,
                "Tart chanh mát lạnh, lớp nhân chanh chua dịu, lớp vỏ giòn tan."));
        allCakes.add(new Cake("Matcha Cake", "Matcha", "Cake", 18100, R.drawable.product5,
                "Bánh trà xanh Nhật Bản, vị thanh nhẹ, thích hợp cho người ăn healthy."));
        allCakes.add(new Cake("Blueberry Donut", "Việt quất", "Donut", 16500, R.drawable.product6,
                "Donut mềm, thơm việt quất, phủ lớp mứt tươi tạo vị chua ngọt hài hòa."));
        allCakes.add(new Cake("Red Velvet", "Dâu đỏ", "Cake", 20300, R.drawable.product7,
                "Bánh Red Velvet sang trọng, lớp kem phô mai béo ngậy, sắc đỏ quyến rũ."));
        allCakes.add(new Cake("Mango Tart", "Xoài", "Tart", 17400, R.drawable.product8,
                "Tart xoài tươi, lớp kem nhẹ hòa quyện cùng miếng xoài mọng nước."));
        allCakes.add(new Cake("Coconut Muffin", "Dừa", "Muffin", 11700, R.drawable.product9,
                "Muffin dừa thơm lừng, bên trong có sợi dừa nạo, vị béo nhẹ tự nhiên."));
        allCakes.add(new Cake("Caramel Donut", "Caramel", "Donut", 15300, R.drawable.product10,
                "Donut phủ caramel ngọt ngào, tan ngay trong miệng, thích hợp cho tín đồ hảo ngọt."));
        allCakes.add(new Cake("Oreo Cake", "Oreo", "Cake", 19000, R.drawable.product11,
                "Bánh kem Oreo giòn xốp, lớp kem mịn béo, kèm topping bánh quy nghiền."));
        allCakes.add(new Cake("Strawberry Muffin", "Dâu", "Muffin", 13800, R.drawable.product1,
                "Muffin vị dâu thơm dịu, bên trong có miếng dâu khô, mềm xốp dễ ăn."));
        allCakes.add(new Cake("Chocolate Tart", "Chocolate", "Tart", 18500, R.drawable.product13,
                "Tart socola đặc biệt, nhân sánh mịn, phủ lớp socola đen đậm đà."));
        updateData(allCakes);
    }

    private void updateData(List<Cake> list) {
        int start = (currentPage - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, list.size());

        currentCakes.clear();
        if (start < list.size()) {
            currentCakes.addAll(list.subList(start, end));
        }
        adapter.notifyDataSetChanged();

        tvResult.setText("Hiển thị " + (start + 1) + " - " + end + " / " + list.size() + " sản phẩm");
    }

    private void filterCakes(String keyword) {
        List<Cake> filteredList = new ArrayList<>();
        for (Cake cake : allCakes) {
            if (cake.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(cake);
            }
        }
        currentPage = 1;
        updateData(filteredList);

        tvResult.setText("Tìm thấy " + filteredList.size() + " sản phẩm");
    }
}
