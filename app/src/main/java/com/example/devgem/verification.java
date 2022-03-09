package com.example.devgem;

//import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
//import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.biometric.BiometricManager;
//import androidx.core.content.ContextCompat;
//
//import android.content.Intent;
//import android.hardware.biometrics.BiometricPrompt;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.util.Log;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import java.util.concurrent.Executor;


import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;


public class verification extends AppCompatActivity {

    private static final int REQUEST_CODE = 101010;

    ImageView fingerPrintImg;
    LinearLayout loginWithUandP;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    public verification() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        Intent intent = getIntent();
        fingerPrintImg = findViewById(R.id.fingerprint);
        loginWithUandP = findViewById(R.id.loginWithUandP);
        loginWithUandP.setOnClickListener(View ->{
            Intent toLogin = new Intent(getApplicationContext(), login.class);
            startActivity(toLogin);
        });

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "Fingerprint Sensor Does Not Exist", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "Sensor Not Available", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Prompts the user to create credentials that your app accepts.
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED, BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, REQUEST_CODE);
                break;
        }

        executor = ContextCompat.getMainExecutor(this);


        biometricPrompt = new androidx.biometric.BiometricPrompt(verification.this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);

                Toast.makeText(verification.this, "Authentication Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull androidx.biometric.BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                startActivity(new Intent(verification.this, home_screen.class));
                Toast.makeText(verification.this, "Authentication Succeeded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();

                Toast.makeText(verification.this, "you are fraud call the right person", Toast.LENGTH_SHORT).show();
            }

        });


        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("BioMetric for My app")
                .setSubtitle("Use your fingerprint")
                .setNegativeButtonText("Use account password")

                .build();

        fingerPrintImg.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });

    }
}