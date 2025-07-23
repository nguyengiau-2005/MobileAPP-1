package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Nút tài khoản/avatar
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
}