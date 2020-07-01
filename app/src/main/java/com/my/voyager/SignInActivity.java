package com.my.voyager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "Login";
    private FirebaseAuth mAuth;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        findViewById(R.id.registerLink).setOnClickListener(v -> {
            goToRegisterActivity();
        });

        findViewById(R.id.signIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (SignInActivity.this.validateForm(email, password)) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "signInWithEmail:success");
                                        firebaseUser = mAuth.getCurrentUser();
                                        goToFirstActivity();
                                    } else {
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(SignInActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
    private boolean validateForm (String email, String password){
        boolean valid = true;
        if (TextUtils.isEmpty(email)) {
            usernameEditText.setError("Required.");
            valid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            usernameEditText.setError("Email structure invalid.");
            valid = false;
        } else {
            usernameEditText.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Required.");
            valid = false;
        } else if (password.trim().length() < 5) {
            passwordEditText.setError("Password must have more than 5 letters");
        } else {
            passwordEditText.setError(null);
        }
        return valid;
    }
    public void goToFirstActivity(){
        Intent placesActivity = new Intent(this, MainActivity.class);
        finish();
        startActivity(placesActivity);
    }
    public void goToRegisterActivity(){
        Intent register = new Intent(this,RegisterActivity.class);
        finish();
        startActivity(register);
    }

}
