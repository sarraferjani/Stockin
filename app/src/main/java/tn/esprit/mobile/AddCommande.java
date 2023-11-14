package tn.esprit.mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class AddCommande extends AppCompatActivity {
    private DBHelper dbhelper;
    private EditText etcodecommande,etcommandetitle,etqte,etprixtotal,etadresse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_commande);
        etcodecommande=findViewById(R.id.codecommande);
        etcommandetitle=findViewById(R.id.commandetitle);
        etqte=findViewById(R.id.qte);
        etprixtotal=findViewById(R.id.prixtotal);
        etadresse=findViewById(R.id.adresse);
        MaterialButton addcommande = findViewById(R.id.addcommande);
        dbhelper = new DBHelper(this);

        addcommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codecommande=etcodecommande.getText().toString();
                String commandetitle=etcommandetitle.getText().toString();
                String quantity=etqte.getText().toString();
                String prixtotal=etprixtotal.getText().toString();
                String adresse=etadresse.getText().toString();
                // Check if any of the fields are empty
                if (codecommande.isEmpty() || commandetitle.isEmpty() || quantity.isEmpty() || prixtotal.isEmpty() || adresse.isEmpty()) {
                    Toast.makeText(AddCommande.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                    return; // Stop further execution if any field is empty
                }

                // All fields are non-empty, proceed with inserting data
                boolean isInsertedCommande = dbhelper.insertDataCommande(codecommande, commandetitle, quantity, prixtotal, adresse);

                if (isInsertedCommande) {
                    Toast.makeText(AddCommande.this, "Added Successfully", Toast.LENGTH_LONG).show();
                }





            }
        });


    }
}
