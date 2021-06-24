package com.example.frolo.racingby;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private EditText txtEmailAddress;
    private EditText txtPassword;
    private FirebaseAuth firebaseAuth;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        EditText EmailRegistration = new EditText(this);
        txtEmailAddress = (EditText) findViewById(R.id.txtEmailRegistration);
        txtPassword = (EditText) findViewById(R.id.txtPasswordRegistration);
        btn = (Button) findViewById(R.id.btnRegistrationUser);
        firebaseAuth = FirebaseAuth.getInstance();
        btn.setEnabled(false);
        btn.setTextColor(ContextCompat.getColor(this, R.color.colorFalseBtn));
        CheckBox checkbox = (CheckBox)findViewById(R.id.checkBoxPS1);
        checkbox.setText(Html.fromHtml("Я понимаю и принимаю условия " +
                "<a href='com.example.PSActivity://Kode'>Пользовательского соглашения.</a>"));
        checkbox.setClickable(true);
        checkbox.setMovementMethod(LinkMovementMethod.getInstance());
        EmailRegistration.setText(txtEmailAddress.toString());
    }

    public void onCheckedChanged(View view) {
        CheckBox cB = (CheckBox) findViewById(R.id.checkBoxPS1);
        if(cB.isChecked()) {
            btn.setEnabled(true);
            btn.setTextColor(ContextCompat.getColor(this, R.color.colorTrueBtn));
        }
        else {
            btn.setEnabled(false);
            btn.setTextColor(ContextCompat.getColor(this, R.color.colorFalseBtn));
        }
    }

    public void btnRegistrationUser_Click(View v) {
        if (txtEmailAddress.getText().toString().equals("") | txtPassword.getText().toString().equals("")) {
            //       Toast.makeText(LoginActivity.this, "Укажите email или пароль для входа!", Toast.LENGTH_LONG).show();
        } else {



            final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, "Пожалуйста ждите...", "Обработка...", true);
            (firebaseAuth.createUserWithEmailAndPassword(txtEmailAddress.getText().toString(), txtPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrationActivity.this, "Регистрация успешно завершена!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(RegistrationActivity.this, EmailVerificationActivity.class);
                        startActivity(i);
                    } else {
                        Log.e("Ошибка!", task.getException().toString());
                        Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }
}
