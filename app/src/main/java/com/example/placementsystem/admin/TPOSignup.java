package com.example.placementsystem.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.placementsystem.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TPOSignup extends AppCompatActivity implements View.OnClickListener {
    private EditText name,tpoid,password;
    private Button submit;
    private FirebaseFirestore firestore;
    private String strName,strId,strPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tposignup);

        name=findViewById(R.id.tpo_name);
        tpoid=findViewById(R.id.tpo_id);
        password=findViewById(R.id.password);

        submit=findViewById(R.id.submit);
        firestore=FirebaseFirestore.getInstance();

        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v==submit) {
            strName=name.getText().toString();
            strId=tpoid.getText().toString();
            strPassword=password.getText().toString();

            Map<String,String> TPO=new HashMap<>();
            TPO.put("Name",strName);
            TPO.put("Id",strId);
            TPO.put("Password",strPassword);

            firestore.collection("TPO USERS")
                    .add(TPO)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(TPOSignup.this, "NEW USER ADDED", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TPOSignup.this,"FAILED", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }
}
