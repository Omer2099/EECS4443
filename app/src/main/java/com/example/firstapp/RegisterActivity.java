package com.example.firstapp;

import static com.example.firstapp.UserStore.users;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.text.InputType;
import android.widget.CheckBox;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registerRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Gets the user inputs
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        //Show password logic
        CheckBox cbShowPassword = findViewById(R.id.cbShowPassword);

        cbShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            etPassword.setSelection(etPassword.getText().length());
        });
        //

        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            //check if there is input in both fields
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            //checks if username already exists if yes it sends text "User already exist"
            if(users.containsKey(username)){
                Toast.makeText(this, "User already exist", Toast.LENGTH_SHORT).show();
                return;
            }

            // else it add new username to the hashmap and sends text "User registered" and takes you to welcome page
            users.put(username, password);
            Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, WelcomepageActivity.class);
            i.putExtra("Username", username);
            startActivity(i);
        });
    }

    // Creates a new activity and goes back to the sign in page
    public  void backout(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
