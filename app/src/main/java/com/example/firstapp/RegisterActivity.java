package com.example.firstapp;

import static com.example.firstapp.UserStore.users;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if(users.containsKey(username)){
                Toast.makeText(this, "User already exist", Toast.LENGTH_SHORT).show();
                return;
            }

            users.put(username, password);

            Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, WelcomepageActivity.class);
            i.putExtra("Username", username);
            startActivity(i);
        });
    }
}