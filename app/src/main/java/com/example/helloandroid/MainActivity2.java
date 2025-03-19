package com.example.helloandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        // Ánh xạ nút và Edit text
        Button back_btn = findViewById(R.id.back_btn);
        EditText result_txt = findViewById(R.id.result_txt);

        // Nhận bundle từ Main Activity
        Bundle bundle = getIntent().getExtras();

        // Kiểm tra kết quả từ bundle và hiển thị kết quả lên Edit text
        if(bundle != null){
            String result = bundle.getString("result");
            if(result.equals("Phương trình vô nghiệm")){
                result_txt.setText(result);
            } else if (result.equals("Phương trình có nghiệm kép")) {
                double x = bundle.getDouble("x");
                result_txt.setText(" x1 = x2 = " + x);
            } else {
                double x1 = bundle.getDouble("x1");
                double x2 = bundle.getDouble("x2");
                result_txt.setText(" x1 = " + x1 + ", x2 = " + x2);
            }
        }

        // Nhấn nút quay lại Main Activity
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}