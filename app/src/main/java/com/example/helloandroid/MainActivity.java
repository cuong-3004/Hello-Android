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

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
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

        // Ánh xạ nút và các Edit text
        Button gpt_btn = findViewById(R.id.gpt_btn);
        EditText a_txt = findViewById(R.id.a);
        EditText b_txt = findViewById(R.id.b);
        EditText c_txt = findViewById(R.id.c);

        gpt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {;
                Bundle bundle = new Bundle();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                // Lấy dữ liệu từ các Edit text
                int a = Integer.parseInt(a_txt.getText().toString().trim());
                int b = Integer.parseInt(b_txt.getText().toString().trim());
                int c = Integer.parseInt(c_txt.getText().toString().trim());

                // Tính delta
                double delta = b * b - 4 * a * c;

                // So sánh giá trị delta để xác định nghiệm phương trình và đưa kết quả vào bundle
                if(delta < 0){
                    bundle.putString("result", "Phương trình vô nghiệm");
                } else if (delta == 0) {
                    bundle.putString("result", "Phương trình có nghiệm kép");
                    bundle.putDouble("x", (double) -b / (2.0 * a));
                } else {
                    bundle.putString("result", "Phương trình có 2 nghiệm phân biệt");
                    bundle.putDouble("x1", (double) (-b + Math.sqrt(delta)) / (2.0 * a));
                    bundle.putDouble("x2", (double) (-b - Math.sqrt(delta)) / (2.0 * a));
                }

                //Gửi bundle đên màn hình kết quả
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        
    }
}