package com.example.neurocare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class ChatBotActivity extends AppCompatActivity {

    TextView user_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        user_welcome = findViewById(R.id.top_header);

        user_welcome.setText("Hi " + Objects.requireNonNull(LoginActivity.auth.getCurrentUser()).getDisplayName() + "!");
    }
}