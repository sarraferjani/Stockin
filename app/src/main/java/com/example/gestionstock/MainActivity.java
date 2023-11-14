package com.example.gestionstock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> id,product_id,start_date,end_date,reduction;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       recyclerView=findViewById(R.id.recyclerView);
        add_button=findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AddPromotion.class);
                startActivity(intent);
            }
        });
        myDB= new MyDatabaseHelper(MainActivity.this);
        id=new ArrayList<>();
        product_id=new ArrayList<>();
        start_date=new ArrayList<>();
        end_date=new ArrayList<>();
        reduction=new ArrayList<>();

        storeDataInArrays();

        customAdapter=new CustomAdapter(MainActivity.this,this,id,product_id,start_date,end_date,reduction);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor=myDB.readAllData();
        if(cursor.getCount() ==0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                product_id.add(cursor.getString(1));
                start_date.add(cursor.getString(2));
                end_date.add(cursor.getString(3));
                reduction.add(cursor.getString(4));
            }
        }

    }
}