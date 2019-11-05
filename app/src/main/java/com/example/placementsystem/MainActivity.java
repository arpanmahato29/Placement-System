package com.example.placementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.placementsystem.admin.AdminHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email,password;
    private String strEmail,strPassword;
    private Button login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        login=findViewById(R.id.login_btn);

        strEmail=email.getText().toString();
        strPassword=password.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        strEmail=email.getText().toString();
        strPassword=password.getText().toString();
        if(TextUtils.isEmpty(strEmail)) {
            email.setError("Required");
        }
        if(TextUtils.isEmpty(strPassword)){
            password.setError("Required");
        }
        Intent intent;
        if(login==v) {

            if(strEmail.equalsIgnoreCase("admin")) {

                if(strPassword.equals("admin123")){
                    intent=new Intent(MainActivity.this, AdminHome.class);
                    startActivity(intent);
                }
            } else {
                mAuth.signInWithEmailAndPassword(strEmail, strPassword)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, ""+user+" signed in", Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        }
    }
}
