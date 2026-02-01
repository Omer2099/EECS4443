package com.example.firstapp;

import static com.example.firstapp.UserStore.users;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.widget.CheckBox;
import android.text.InputType;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    EditText usernameInput;
    EditText passwordInput;
    CheckBox cbRemember;

    private static final String PREFS = "LoginPrefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Gets the user inputs
        usernameInput = (EditText) findViewById(R.id.etUsername);
        passwordInput = (EditText) findViewById(R.id.etPassword);

        // Show password logic
        CheckBox cbShowPassword = findViewById(R.id.cbShowPassword);

        cbShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            passwordInput.setSelection(passwordInput.getText().length()); // keep cursor at end
        });

        cbRemember = findViewById(R.id.cbRemember);

        // login right away if Remember Me was clicked
        SharedPreferences sp = getSharedPreferences(PREFS, MODE_PRIVATE);
        boolean remember = sp.getBoolean(KEY_REMEMBER, false);
        String savedUser = sp.getString(KEY_USERNAME, null);

        if (remember && savedUser != null) {
            Intent i = new Intent(this, WelcomepageActivity.class);
            i.putExtra("Username", savedUser);
            Toast.makeText(this, "User is remembered",Toast.LENGTH_LONG).show();
            startActivity(i);
            finish();
        }

    }

    // Checks the user input and creates a new activity / sends user to the Welcome page
    public void login(View v)
    {
        // Changes the Data type of the input to string
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        // Validates the username and password with the admin or registered users. An alert is made when the input is invalid or incorrect.
        if( username.equals("admin") && password.equals("1234"))
        {
            Intent i = new Intent(this, WelcomepageActivity.class);
            i.putExtra("Username", username);
            Toast.makeText(this, "Login Successful",Toast.LENGTH_LONG).show();

            // Checks if Remember me is checked and skips log in
            SharedPreferences sp = getSharedPreferences(PREFS, MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();

            if (cbRemember.isChecked()) {
                ed.putBoolean(KEY_REMEMBER, true);
                ed.putString(KEY_USERNAME, username);
            } else {
                ed.clear();
            }
            ed.apply();

            startActivity(i);
        }
        else if( users.containsKey(username) && password.equals(users.get(username)) )
        {
            Intent i = new Intent(this, WelcomepageActivity.class);
            i.putExtra("Username", username);

            // Also Checks if Remember me is checked and skips log in
            SharedPreferences sp = getSharedPreferences(PREFS, MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();

            if (cbRemember.isChecked()) {
                ed.putBoolean(KEY_REMEMBER, true);
                ed.putString(KEY_USERNAME, username);
            } else {
                ed.clear();
            }
            ed.apply();

            startActivity(i);
        }
        else if ( (!username.equals("admin") && !password.equals("1234") )
                || !users.containsKey(username)
                || !password.equals(users.get(username)) ) {
            Toast.makeText(this, "Either Username or Password is invalid. Please try again",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Enter a username and password",Toast.LENGTH_LONG).show();
        }

    }

    // creates a new activity / sends user to the Register page
    public  void register( View v)
    {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    // Clears the fields of data inputted
    public void cancel( View v)
    {
        usernameInput.setText("");
        passwordInput.setText("");
    }


}