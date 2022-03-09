package com.example.devgem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    EditText Textemail, Textpassword;
    Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Textemail = findViewById(R.id.typeofDeveloper);
        Textpassword = findViewById(R.id.password);
        submitbtn = findViewById(R.id.registerBtn);

        submitbtn.setOnClickListener(View -> {
            String emailValue = Textemail.getText().toString().trim();
            String passwordValue = Textpassword.getText().toString().trim();

            if (TextUtils.isEmpty(emailValue)) {

                Textemail.setError("Email is required");
                return;
            }
            if (passwordValue.length() < 8) {
                Textpassword.setError("Password is too short");
                return;
            }

            Intent intent = new Intent(getApplicationContext(), home_screen.class);
            finish();
            startActivity(intent);
        });
    }
}