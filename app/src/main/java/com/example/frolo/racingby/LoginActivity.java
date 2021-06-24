package com.example.frolo.racingby;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmailLogin;
    private EditText txtPwd;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmailLogin = (EditText) findViewById(R.id.txtEmailLogin);
        txtPwd = (EditText) findViewById(R.id.txtPasswordLogin);
        firebaseAuth = FirebaseAuth.getInstance();

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                Toast.makeText(LoginActivity.this, "Добро пожаловать "+ firebaseAuth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
                // Создание объекта Intent для запуска MainActivity
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                // Передача объекта с ключом "Email" и значением электронной почты
                i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                i.putExtra("name", firebaseAuth.getCurrentUser().getEmail());
                // Запуск MainActivity
                startActivity(i);
            } else {

            }
    }

    public void btnSendPasswordReset_Click(View v) {
        sendPasswordReset();
    }

    public void showDialog(View v) {
        if (txtEmailLogin.getText().toString().equals("")) {
            TextView textViewSendMessage = (TextView) findViewById(R.id.textView113);
            textViewSendMessage.setText("Для сброса пароля, введите Ваш электронный адрес в поле ввода");
        } else {
            CustomDialogFragment dialog = new CustomDialogFragment();
            dialog.show(getSupportFragmentManager(), "custom");
        }
    }

    public void sendPasswordReset() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(txtEmailLogin.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Сообщение отправлено", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        // [END send_password_reset]
    }

    public void btnLoginGuest_Click(View v) {
        Intent i = new Intent(LoginActivity.this, MainActivityGuest.class);
        startActivity(i);
    }

    public void btnVideo_Click(View v) {
        Intent i = new Intent(LoginActivity.this, VideoYTActivity.class);
        startActivity(i);
    }

    public void btnRegistration_Click(View v) {
        Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
    }

    public void btnUserLogin_Click(View v) {
        final EditText nameText = (EditText) findViewById(R.id.txtEmailLogin);
        final String name = nameText.getText().toString();

        if (txtEmailLogin.getText().toString().equals("") | txtPwd.getText().toString().equals(""))
        {
            Toast.makeText(LoginActivity.this, "Укажите email или пароль для входа!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(LoginActivity.this, "Пожалуйста ждите... Обработка...", Toast.LENGTH_LONG).show();
            (firebaseAuth.signInWithEmailAndPassword(txtEmailLogin.getText().toString(), txtPwd.getText().toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Добро пожаловать "+ name, Toast.LENGTH_LONG).show();
                                // Создание объекта Intent для запуска MainActivity
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                // Передача объекта с ключом "Email" и значением электронной почты
                                i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                i.putExtra("name", name);
                                // Запуск MainActivity
                                startActivity(i);
                            } else {
                                Log.e("Ошибка!", task.getException().toString());
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }
}
