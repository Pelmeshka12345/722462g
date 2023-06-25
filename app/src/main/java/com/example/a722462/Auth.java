package com.example.a722462;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Auth extends AppCompatActivity {

    Button sendEmail;
    EditText emailUser;
    TextView authWithYandex;
    private Button btnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        sendEmail = findViewById(R.id.sendEmail);
        emailUser = findViewById(R.id.loginEmail);
        authWithYandex = findViewById(R.id.signInYandex);
        btnEmail= findViewById(R.id.button2);


        sendEmail.setEnabled(false);

        btnEmail.setOnClickListener(v -> {
            Intent code = new Intent(Auth.this, GetCodeEmail.class);
            startActivity(code);
        });
        emailUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                sendEmail.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sendEmail.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(emailUser.getText())) {
                    sendEmail.setEnabled(false);
                }

            }
        });
        sendEmail.setOnClickListener(v -> {
            Api api = Api.retrofit.create(Api.class);
            Call<Void> call = api.sendCode(emailUser.getText().toString());

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    if(response.isSuccessful()) {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Auth.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("email", emailUser.getText().toString());
                        editor.apply();
                        Intent code = new Intent(Auth.this, GetCodeEmail.class);
                        startActivity(code);
                    } else {
                        Log.d("JJJJJ", ""+response.body());
                        Toast.makeText(Auth.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, Throwable t) {
                    Log.d("Error_API", "Ошибка"+t.getMessage());
                    Toast.makeText(Auth.this, "Ошибка"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        });
        authWithYandex.setOnClickListener(v -> {

        });
    }
}