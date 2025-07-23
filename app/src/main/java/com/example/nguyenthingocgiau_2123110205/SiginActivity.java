package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SiginActivity extends AppCompatActivity {

    private static final String TAG = "SiginActivity";
    private final String API_URL = "https://6868e3f9d5933161d70cc0d7.mockapi.io/api/Users";

    EditText edtUsername, edtPassword;
    CheckBox chkRemember;
    Button btnSignIn;
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sigin);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            );
            return insets;
        });

        // Ánh xạ view
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        chkRemember = findViewById(R.id.chkRemember);
        btnSignIn = findViewById(R.id.btnSignIn);
        txtRegister = findViewById(R.id.txtRegister);

        // Xử lý khi nhấn đăng nhập
        btnSignIn.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Gọi hàm kiểm tra đăng nhập với API
            checkLoginWithAPI(username, password);
        });

        // Chuyển sang màn hình đăng ký
        txtRegister.setOnClickListener(v -> {
            startActivity(new Intent(SiginActivity.this, RegisterActivity.class));
        });
    }

    private void checkLoginWithAPI(String username, String password) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, API_URL,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        boolean found = false;

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject user = jsonArray.getJSONObject(i);
                            String u = user.getString("username");
                            String p = user.getString("password");

                            if (u.equals(username) && p.equals(password)) {
                                found = true;
                                break;
                            }
                        }

                        if (found) {
                            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, HomeActivity.class));
                            finish();
                        } else {
                            Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Lỗi xử lý dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.e(TAG, "Login API error: " + error.toString());
                    Toast.makeText(this, "Lỗi kết nối server", Toast.LENGTH_SHORT).show();
                });

        queue.add(request);
    }
}
