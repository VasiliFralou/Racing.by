package com.example.frolo.racingby;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText NewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        final TextView EmailSetting = (TextView) findViewById(R.id.EmailSetting);
        NewPassword = (EditText) findViewById(R.id.NewPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        EmailSetting.setText(user.getEmail());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    public void btnNewPassword_Click(View v) {
        NewPassword();
    }

    public void btnDeleteUser_Click(View v){
        deleteUser();
    }

    public void NewPassword() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String newPassword = NewPassword.getText().toString();

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SettingActivity.this, "Пароль успешно изменён!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void deleteUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Intent i = new Intent(SettingActivity.this, LoginActivity.class);
        startActivity(i);

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SettingActivity.this, "Учётная запись удалена!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
