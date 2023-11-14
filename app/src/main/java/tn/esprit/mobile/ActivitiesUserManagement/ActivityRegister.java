package tn.esprit.mobile.ActivitiesUserManagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import tn.esprit.mobile.DBHelper;
import tn.esprit.mobile.R;
public class ActivityRegister extends AppCompatActivity {


    private DBHelper dbHelper;
    private EditText etUsername, etPassword, etRepassword, etFirstName, etLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        etRepassword = findViewById(R.id.repassword);
        etFirstName = findViewById(R.id.fname);
        etLastName = findViewById(R.id.lname);

        dbHelper = new DBHelper(this);
        TextView seconnect=findViewById(R.id.info);
        MaterialButton signupBtn = findViewById(R.id.signupbtn);

        seconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginIntent = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(loginIntent);


            }});

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String repassword = etRepassword.getText().toString();
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();

                if (username.isEmpty() || password.isEmpty() || repassword.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                    Toast.makeText(ActivityRegister.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    if (password.equals(repassword)) {
                        if (dbHelper.checkUsername(username)) {
                            Toast.makeText(ActivityRegister.this, "Username already exists", Toast.LENGTH_LONG).show();
                            return;
                        }

                        boolean isInserted = dbHelper.insertData(username, password, firstName, lastName);

                        if (isInserted) {

                            Toast.makeText(ActivityRegister.this, "Successfully registered", Toast.LENGTH_LONG).show();
                            Intent loginIntent = new Intent(ActivityRegister.this, ActivityLogin.class);
                            startActivity(loginIntent);
                        } else {
                            Toast.makeText(ActivityRegister.this, "Error during registration", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(ActivityRegister.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


}
