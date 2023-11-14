package tn.esprit.mobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.mobile.Entities.CommandeModel;

public class ActiviyListCommande extends AppCompatActivity {
    private DBHelper dbHelper;
    private RecyclerView recyclerView;
    private CommandeAdapter commandeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listecommande);

        dbHelper = new DBHelper(this);
        recyclerView = findViewById(R.id.recyclerView);

        // Retrieve data from the Commande table
        List<CommandeModel> commandesList = dbHelper.getAllCommandes();

        // Set up RecyclerView with the adapter
        commandeAdapter = new CommandeAdapter(this, commandesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commandeAdapter);
    }
}
