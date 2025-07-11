package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            );
            return insets;
        });

        // Ánh xạ các thành phần giao diện
        EditText txtUsername = findViewById(R.id.txtUsername);
        EditText txtEmail = findViewById(R.id.txtEmail);
        EditText txtPassword = findViewById(R.id.txtPassword);
        EditText txtConfirm = findViewById(R.id.txtConfirm);
        Button btnRegister = findViewById(R.id.btnRegister);

        // Xử lý sự kiện khi nhấn nút Đăng ký
        btnRegister.setOnClickListener(v -> {
            String username = txtUsername.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();
            String password = txtPassword.getText().toString();
            String confirm = txtConfirm.getText().toString(); // dùng đúng biến đã ánh xạ

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirm)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            // Chuyển về màn hình đăng nhập
            Intent intent = new Intent(this, SiginActivity.class); // đảm bảo tên class đúng
            startActivity(intent);
            finish();
        });
    }
}
