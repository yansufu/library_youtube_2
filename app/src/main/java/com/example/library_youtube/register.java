package com.example.library_youtube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class register extends AppCompatActivity {

    private Button register;
    TextView tflogin, tfloginbawah;
    private EditText email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);
        register = findViewById(R.id.btnregister);
        tflogin = findViewById(R.id.tflogin);
        tfloginbawah = findViewById(R.id.tfloginbawah);

        auth = FirebaseAuth.getInstance();


        // Inflate the layout for this fragment
        tflogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });
        tfloginbawah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = email.getText().toString();
                String pw = password.getText().toString();

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(register.this,"Enter your email", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                    Toast.makeText(register.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pw)) {
                    Toast.makeText(register.this,"Enter password", Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(username,pw);
                }

            }
        });



    }



    private void registerUser(String username, String pw) {
        auth.createUserWithEmailAndPassword(username, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    updateUserInfo();

                    Intent intent = new Intent(register.this, login.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(register.this, "Register Failed", Toast.LENGTH_SHORT).show();
                }
            }
        })
        ;

    }

    private void updateUserInfo() {
        //timestamp
        long timestamp = System.currentTimeMillis();

        //get current user ID
        String uid = auth.getUid();

        String username = email.getText().toString();
        String pw = password.getText().toString();


        //set data to add in db
        HashMap<String, Object> hashMap =new HashMap<>();
        hashMap.put("uid",uid);
        hashMap.put("email",username);
        hashMap.put("password",pw);
        hashMap.put("name", ""); //add empty, will do later
        hashMap.put("profileImage", ""); //add empty, will do later
        hashMap.put("timestamp", timestamp);

        //set data to db
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(register.this, "Registration success, please log in", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(register.this, "Registration gagal", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}