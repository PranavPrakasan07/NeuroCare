package com.example.neurocare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MessagingActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("articles");

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        RelativeLayout chat1 = findViewById(R.id.chat1);
        RelativeLayout chat2 = findViewById(R.id.chat2);

        ImageButton back_button = findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });

        chat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatBotActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("code", "1");
                bundle.putString("name", "D 1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        chat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatBotActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("name", "D 2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        TextView header = findViewById(R.id.top_header);

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.setValue("Hello, World!");
                mDatabase.child("articles").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).setValue("name");

            }
        });
//        database = Firebase.database.reference
//
//        database.child("pages").child("Help").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.e("firebase", "onDataChange ${snapshot.value.toString()}" )
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("firebase", "onCancelled ${error.message}" )
//
//            }
//        })
    }
}