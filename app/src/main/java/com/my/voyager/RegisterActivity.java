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
import com.google.firebase.database.FirebaseDatabase;
import com.my.voyager.model.User;


import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        usernameEditText = findViewById(R.id.newAccountUsername);
        passwordEditText = findViewById(R.id.newAccountPassword);

        findViewById(R.id.loginLink).setOnClickListener(v -> {
           goToLoginActivity();
        });


        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (RegisterActivity.this.validateForm(email, password)) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:success");
                                User user = new User(email,password,null);
                                database.getReference("user").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())
                                        .getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this,"User created!",
                                                    Toast.LENGTH_SHORT).show();
                                            FirebaseAuth.getInstance().signOut();
                                            goToLoginActivity();
                                        }else {
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(),
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
        public void goToLoginActivity(){
            Intent signInActivity = new Intent(this,SignInActivity.class);
            finish();
            startActivity(signInActivity);
        }
}
