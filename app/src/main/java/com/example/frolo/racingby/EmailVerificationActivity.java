package com.example.frolo.racingby;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerificationActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        final TextView textViewEV = (TextView) findViewById(R.id.textViewEmailV);
        firebaseAuth = FirebaseAuth.getInstance();

        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            textViewEV.setText(user.getEmail());
                        }

                        else {
                            Log.e("sendEmailVerification", task.getException().toString());
                            Toast.makeText(EmailVerificationActivity.this,
                                    "Ошибка при отправки подтверждения Email.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
    }

    public void FRegistration_Click (View v) {
            Intent i = new Intent(EmailVerificationActivity.this, LoginActivity.class);
            i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
            startActivity(i);
    }
}
