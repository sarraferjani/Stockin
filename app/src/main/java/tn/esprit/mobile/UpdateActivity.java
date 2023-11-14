package tn.esprit.mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText product_id, startdate, enddate, reduction;
    Button update_button;
    String id, productid, start_date, end_date, discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        product_id = findViewById(R.id.product_id2);
        startdate = findViewById(R.id.startdate2);
        enddate = findViewById(R.id.enddate2);
        reduction = findViewById(R.id.reduction2);
        update_button = findViewById(R.id.update_button);
        getAndSetIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB= new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id,product_id.getText().toString(),startdate.getText().toString(),enddate.getText().toString(),reduction.getText().toString());
            }
        });

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("product") && getIntent().hasExtra("start_date")
                && getIntent().hasExtra("end_date") && getIntent().hasExtra("discount")) {
            //Getting data from intent
            id=getIntent().getStringExtra("id");
            productid=getIntent().getStringExtra("product");
            start_date=getIntent().getStringExtra("start_date");
            end_date=getIntent().getStringExtra("end_date");
            discount=getIntent().getStringExtra("discount");
            //Setting Intent Data
            product_id.setText(productid);
            startdate.setText(start_date);
            enddate.setText(end_date);
            reduction.setText(discount);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}