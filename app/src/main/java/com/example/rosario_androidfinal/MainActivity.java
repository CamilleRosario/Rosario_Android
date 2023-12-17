package com.example.rosario_androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int num1 =5;
    int num2 =10;

    Button btnLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLog = (Button) findViewById(R.id.btnLogIn);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActNav.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        num1 =num1+2;
        num2 =num2+2;
        Toast.makeText(MainActivity.this, "Maria Camille B. Rosario", Toast.LENGTH_LONG).show();
        Log.d("onStart", num1+" : " +num2);
    }
    @Override
    protected void onPause() {
        super.onPause();
        num1 = num1+2;
        num2 = num2+2;
        Log.d("onPause", num1+" : " +num2);
    }

}