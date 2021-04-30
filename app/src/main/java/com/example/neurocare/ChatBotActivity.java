package com.example.neurocare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class ChatBotActivity extends AppCompatActivity {

    TextView user_welcome;
    private final int RecordAudioRequestCode = 1;
    TextView message_text;
    boolean mic_on = false;
    boolean isSendButton = false;

    EditText chatbox;

    ImageButton mic_button;
    private SpeechRecognizer speechRecognizer;

    @SuppressLint("ClickableViewAccessibility")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        user_welcome = findViewById(R.id.top_header);
        message_text = findViewById(R.id.message_text);

        mic_button = findViewById(R.id.mic_button);
        chatbox = findViewById(R.id.chatbox);

        user_welcome.setText("Hi " + Objects.requireNonNull(LoginActivity.auth.getCurrentUser()).getDisplayName() + "!");

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        }

        chatbox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(ChatBotActivity.this, "Touched!", Toast.LENGTH_SHORT).show();

                mic_button.setImageResource(R.drawable.ic_send);
                isSendButton = true;

                return false;
            }
        });



        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {

            @Override
            public void onReadyForSpeech(Bundle bundle) {
//                message_text.setText("");
                message_text.setHint("Listening...");
            }

            @Override
            public void onBeginningOfSpeech() {
//                message_text.setText("");
                message_text.setHint("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {
                mic_button.setImageResource(R.drawable.ic_mic_off);

            }

            @Override
            public void onError(int i) {
                mic_button.setImageResource(R.drawable.ic_mic_off);
            }

            @Override
            public void onResults(Bundle bundle) {
                mic_button.setImageResource(R.drawable.ic_mic_off);
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                String response = data.get(0);

                message_text.setText(response);
                Toast.makeText(ChatBotActivity.this, response, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        mic_button.setOnClickListener(v -> {

            if (isSendButton){
                message_text.setText(chatbox.getText().toString());
                chatbox.setText("");
                mic_button.setImageResource(R.drawable.ic_mic_off);
                mic_on = false;
                isSendButton = false;
            }

            if (!mic_on) {
                mic_button.setImageResource(R.drawable.ic_mic);
                speechRecognizer.startListening(speechRecognizerIntent);
            } else {
                speechRecognizer.stopListening();
                mic_button.setImageResource(R.drawable.ic_mic);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }
}