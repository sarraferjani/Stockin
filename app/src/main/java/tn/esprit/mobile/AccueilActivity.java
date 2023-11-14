package tn.esprit.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import tn.esprit.mobile.ActivitiesUserManagement.ActivityLogin;
import tn.esprit.mobile.ActivitiesUserManagement.ActivityRegister;
import tn.esprit.mobile.view.BlogsActivity;

public class AccueilActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "LoginPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Button commandeBtn = findViewById(R.id.btnPasserCommande);
        Button  btnPasserCommande=findViewById(R.id.btnlistcommande);
        ImageButton btnLogout=findViewById(R.id.btnLogout);
        Button btnblog =findViewById(R.id.btnblog);
        Button btnpormotion=findViewById(R.id.btnpormotion);
        btnpormotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(AccueilActivity.this, listPromActivity.class);
                startActivity(loginIntent);

            }
        });


        btnblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(AccueilActivity.this, BlogsActivity.class);
                startActivity(loginIntent);

            }
            });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                // Start the LoginActivity
                Intent loginIntent = new Intent(AccueilActivity.this, ActivityLogin.class);
                startActivity(loginIntent);

                // Finish the current activity (optional)
                finish();

            }});
        commandeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginIntent = new Intent(AccueilActivity.this, AddCommande.class);
                startActivity(loginIntent);


            }});
        btnPasserCommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginIntent = new Intent(AccueilActivity.this, ActiviyListCommande.class);
                startActivity(loginIntent);


            }});
    }
}
