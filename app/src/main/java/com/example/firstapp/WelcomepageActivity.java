package com.example.firstapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomepageActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcomepage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Gets the username from the previous activity
        textView = (TextView) findViewById(R.id.Welcome_textView);
        Intent i = getIntent();
        String user = i.getStringExtra("Username");

        textView.setText("Welcome "+user);
    }

    // Creates a new activity / goes back to the sign in page
    public  void Signout( View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        // Resets the Remember me check point
        SharedPreferences sp = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        sp.edit().clear().apply();


    }
}
