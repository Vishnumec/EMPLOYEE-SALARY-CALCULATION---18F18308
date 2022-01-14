package com.example.madassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button validBtn;
    EditText empValidEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        empValidEt = findViewById(R.id.empvalid);
        validBtn = findViewById(R.id.validbtn);
        validBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(empValidEt.getText().toString()) > 100) {
                    Toast.makeText(MainActivity.this, "Value cannot be greater than 100", Toast.LENGTH_SHORT).show();
                }
                else {

                    Intent intent = new Intent(MainActivity.this, calculation.class);
                    startActivity(intent);
                }

            }
        });
    }
}