package com.example.nguyenthingocgiau_2123110205;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnLogin = findViewById(R.id.btnLogin);
        EditText objUser = findViewById(R.id.txtUsername);
        EditText objPass = findViewById(R.id.txtPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  txtUser = objUser.getText().toString();
                String txtPass = objPass.getText().toString();
//                if (txtUser.isEmpty() || txtPass.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), " vui lòng nhap thong tin day du!", Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                if (txtUser.equals("nguyengiau") && txtPass.equals("1234")) {
                    Intent it = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(it);
//                } else {
//                    Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_LONG).show();
//                }
            }
        });
    }
}