 package com.example.a722462;

 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.os.Bundle;
 import android.os.Handler;
 import android.preference.PreferenceManager;

 import androidx.appcompat.app.AppCompatActivity;

 public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(LauncherActivity.this);
                if (sharedPref.contains("token") && sharedPref.contains("pin")){
                    Intent main = new Intent(LauncherActivity.this, PasswordApp.class);
                    startActivity(main);
                    finish();
                } else {
                    Intent onBoard = new Intent(LauncherActivity.this, OnBoard.class);
                    startActivity(onBoard);
                    finish();
                }
            }
        }, 3000);
    }
}