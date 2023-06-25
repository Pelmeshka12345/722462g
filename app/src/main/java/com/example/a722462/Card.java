package com.example.a722462;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Card extends AppCompatActivity {
    private Button next;
    private Button create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_card);
        next=(Button) findViewById(R.id.btnNextCard);
        create=(Button) findViewById(R.id.btnCreate);

        next.setOnClickListener(v -> {
            Intent code = new Intent(Card.this, MainActivity.class);
            startActivity(code);
        });

        create.setOnClickListener(v -> {
            Intent code = new Intent(Card.this, MainActivity.class);
            startActivity(code);
        });
    }
}