package com.example.firstapp;

import static com.example.firstapp.UserStore.users;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

        // Gets the user input
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            // Changes the data type of the input to String
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            // Checks if the user has entered any data and checks to see if the username is taken or not
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if(users.containsKey(username)){
                Toast.makeText(this, "User already exist", Toast.LENGTH_SHORT).show();
                return;
            }

            // The username and password is stored
            users.put(username, password);

           // An alert is made to inform the user of the successful registration and creates a new activity/ user is sent to the Welcome page
            Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, WelcomepageActivity.class);
            i.putExtra("Username", username);
            startActivity(i);
        });
    }

    // Creates a new activity / goes back to the sign in page
    public void backout(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}