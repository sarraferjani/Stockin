package tn.esprit.mobile.ActivitiesUserManagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import tn.esprit.mobile.AccueilActivity;
import tn.esprit.mobile.DBHelper;
import tn.esprit.mobile.R;

public class ActivityLogin extends AppCompatActivity {

    EditText etuser, etPwd;
    MaterialButton loginbtn;
    CheckBox rememberMeCheckBox;
    DBHelper dbHelper;
    private static final String PREFS_NAME = "LoginPrefs";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";
    private static final String PREF_REMEMBER_ME = "rememberMe";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etuser = findViewById(R.id.loginUsername);
        etPwd = findViewById(R.id.loginPassword);
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);
        loginbtn = findViewById(R.id.loginBtn);

        dbHelper = new DBHelper(this);
        TextView seconnect=findViewById(R.id.info);

        seconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(loginIntent);
            }});
        // Load saved login information from SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedUsername = preferences.getString(PREF_USERNAME, "");
        String savedPassword = preferences.getString(PREF_PASSWORD, "");
        boolean isRememberMeChecked = preferences.getBoolean(PREF_REMEMBER_ME, false);
        // Set the loaded values to the UI
        etuser.setText(savedUsername);
        etPwd.setText(savedPassword);
        rememberMeCheckBox.setChecked(isRememberMeChecked);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etuser.getText().toString();
                String password = etPwd.getText().toString();

                if (dbHelper.checkCredentials(username, password)) {
                    // Login successful

                    // Save login information to SharedPreferences if "Remember Me" is checked
                    if (rememberMeCheckBox.isChecked()) {
                        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(PREF_USERNAME, username);
                        editor.putString(PREF_PASSWORD, password);
                        editor.putBoolean(PREF_REMEMBER_ME, true);
                        editor.apply();
                    } else {
                        // If "Remember Me" is unchecked, clear saved login information
                        clearLoginPreferences();
                    }

                    // Proceed with your app logic after successful login
                    Toast.makeText(ActivityLogin.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                    // You can start a new activity or perform other actions here
                    Intent intent = new Intent(ActivityLogin.this, AccueilActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Login failed, handle accordingly
                    Toast.makeText(ActivityLogin.this, "Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearLoginPreferences() {
        // Clear saved login information from SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}