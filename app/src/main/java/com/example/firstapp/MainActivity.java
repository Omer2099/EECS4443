package com.example.firstapp;

import static com.example.firstapp.UserStore.users;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText usernameInput;
    EditText passwordInput;

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
        // Gets the user inputs
        usernameInput = (EditText) findViewById(R.id.etUsername);
        passwordInput = (EditText) findViewById(R.id.etPassword);
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
            startActivity(i);
        }
        else if( users.containsKey(username) && password.equals(users.get(username)) )
        {
            Intent i = new Intent(this, WelcomepageActivity.class);
            i.putExtra("Username", username);
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