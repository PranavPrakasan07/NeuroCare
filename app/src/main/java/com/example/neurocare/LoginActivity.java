package com.example.neurocare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.ShapeType;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    TextView signup_link;

    NeumorphCardView n_signin, n_login;

    TextView login_button;
    private static final int RC_SIGN_IN = 1;

    TextView login_tab, signin_tab;

    RelativeLayout login_layout, signin_layout;

    GoogleSignInClient mGoogleSignInClient;
    static FirebaseAuth auth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser != null) {
            startActivity(new Intent(getApplicationContext(), Home.class));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        login_tab = findViewById(R.id.login_tab);
        signin_tab = findViewById(R.id.signup_tab);

        signin_layout = findViewById(R.id.signin_layout);
        login_layout = findViewById(R.id.login_layout);

//        signup_link = findViewById(R.id.signup_link);

        n_login = findViewById(R.id.n_login_card);
        n_signin = findViewById(R.id.n_signin_card);

        login_button = findViewById(R.id.login_button);
        auth = FirebaseAuth.getInstance();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("126224530227-lvs4e8jd4pukvidhodbghkbd94118urk.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        findViewById(R.id.google_button).setOnClickListener(view -> {
            if (view.getId() == R.id.google_button) {
                signIn();
            }
        });

        signin_tab.setOnClickListener(v -> {
            login_layout.setVisibility(View.GONE);
            signin_layout.setVisibility(View.VISIBLE);

            n_signin.setShapeType(ShapeType.PRESSED);
            n_login.setShapeType(ShapeType.FLAT);
        });

        login_tab.setOnClickListener(v -> {
            signin_layout.setVisibility(View.GONE);
            login_layout.setVisibility(View.VISIBLE);

            n_login.setShapeType(ShapeType.PRESSED);
            n_signin.setShapeType(ShapeType.FLAT);

        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });

        Intent intent = getIntent();

        try {
            String email_text = intent.getStringExtra("email");
            String password_text = intent.getStringExtra("password");

            email.setText(email_text);
            password.setText(password_text);

        } catch (Exception e) {
            e.printStackTrace();
        }

//        signup_link.setOnClickListener(v -> {
//            Intent intent1 = new Intent(getApplicationContext(), SignUpActivity.class);
//
//            Bundle bundle = new Bundle();
//
//            String email_text = email.getText().toString();
//            String password_text = password.getText().toString();
//
//            bundle.putString("email", email_text);
//            bundle.putString("password", password_text);
//
//            intent1.putExtras(bundle);
//
//            startActivity(intent1);
//        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithCredential:success");
                        FirebaseUser user = auth.getCurrentUser();

                        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        finish();

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithCredential:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                        ;
                    }
                });
    }
}