package com.example.library_youtube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    FirebaseUser user;
    FirebaseAuth mAuth;

    FirebaseFirestore fireDb;

    private RecyclerView recDrinks;
    private ArrayList<Drinks> listDrinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initFab();

        fireDb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        recDrinks = findViewById((R.id.rec_drinks));
        initDataDrinks();

        recDrinks.setAdapter(new DrinksAdapter(listDrinks));
        recDrinks.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initFab(){

        FloatingActionButton fabLogout = findViewById(R.id.fabLogout);
        fabLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), home.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
    }

    private void initDataDrinks(){
        this.listDrinks = new ArrayList<>();
        listDrinks.add(new Drinks("Gramedia", "contact", R.drawable.account));
        listDrinks.add(new Drinks("BPKP", "contact", R.drawable.account));
        listDrinks.add(new Drinks("American Library Association", "contact", R.drawable.account));
        listDrinks.add(new Drinks("Sience Direct", "contact", R.drawable.account));
        listDrinks.add(new Drinks("PT Erlangga", "contact", R.drawable.account));
        listDrinks.add(new Drinks("PT Tirta Buana", "contact", R.drawable.account));
    }
}