package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PageActivity extends AppCompatActivity {
    // Các nút menu
    TextView btnHome, btnAbout, btnShop, btnPages, btnBlog, btnContact;
    TextView[] menuButtons;

    private void setActive(TextView selectedBtn) {
        for (TextView btn : menuButtons) {
            btn.setTextAppearance(R.style.MenuItemStyle); // style mặc định
        }
        selectedBtn.setTextAppearance(R.style.MenuItemStyle_Active); // style khi được chọn
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Nút tài khoản/avatar
        ImageView btnMenu = findViewById(R.id.btnMenu);  // đúng id
        btnMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(PageActivity.this, v);
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
                }
                return true;
            });

            popup.show();  // QUAN TRỌNG
        });
    }
}