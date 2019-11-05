package com.example.placementsystem.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.placementsystem.R;

public class AdminHome extends AppCompatActivity implements View.OnClickListener {
    private Button btnTPO,btnStudent;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        btnTPO=findViewById(R.id.tpo_btn);
        btnStudent=findViewById(R.id.student_btn);

        btnTPO.setOnClickListener(this);
        btnStudent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(btnTPO==v) {
            intent=new Intent(AdminHome.this,TPOSignup.class);
            startActivity(intent);
        }
        if(btnStudent==v) {
            intent=new Intent(AdminHome.this,StudentSignup.class);
            startActivity(intent);
        }

    }
}
