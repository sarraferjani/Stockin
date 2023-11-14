package com.example.gestionstock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPromotion extends AppCompatActivity {

    EditText product_id, startdate, enddate, reduction;
    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_promotion);

        product_id = findViewById(R.id.product_id);
        startdate = findViewById(R.id.startdate);
        enddate = findViewById(R.id.enddate);
        reduction = findViewById(R.id.reduction);
        add_button = findViewById(R.id.add_button);
        product_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Appelé lorsque le texte change
                updateReductionValue();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });

        // Désactiver l'édition de l'EditText reduction
        reduction.setEnabled(false);


    }

    private void updateReductionValue() {
        // Met à jour la valeur de l'EditText reduction en fonction de la logique souhaitée
        try {
            int productIdValue = Integer.valueOf(product_id.getText().toString().trim());
            MyDatabaseHelper myDB=new MyDatabaseHelper(AddPromotion.this);
            Product product = myDB.getProductById(productIdValue);

            if (product != null) {
                if (product.getPrice() > 20) {
                    reduction.setText("20");
                } else {
                    reduction.setText("10");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Gérer l'exception si la conversion en int échoue
        }







        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int productIdValue = Integer.valueOf(product_id.getText().toString().trim());
                Date startDateValue = parseDate(startdate.getText().toString().trim());
                Date endDateValue = parseDate(enddate.getText().toString().trim());
                float reductionValue = Integer.parseInt(reduction.getText().toString().trim());
                MyDatabaseHelper myDB=new MyDatabaseHelper(AddPromotion.this);
                Product product = myDB.getProductById(productIdValue);
                if(product !=null && startDateValue.before(endDateValue) ) {
                    double discountedPrice = product.getPrice() - (product.getPrice() * (reductionValue / 100));
                    product.setPrice(discountedPrice);
                    myDB.addpromotion(product, startDateValue, endDateValue, reductionValue);
                } }
        });
    }
    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}