package com.example.stockin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.stockin.R;
import com.example.stockin.database.AppDatabase;
import com.example.stockin.model.Blog;

public class UpdateBlogActivity extends AppCompatActivity {
    private AppDatabase database;
    private EditText etTitle;
    private EditText etDesc;
    private EditText etUsername;
    private Button saveBtn;
    private Blog blog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_blog);
        etTitle = findViewById(R.id.etBlogTitle);
        etDesc = findViewById(R.id.etBlogDesc);
        etUsername = findViewById(R.id.etUserName);
        saveBtn = findViewById(R.id.btnSaveBlog);

        database = AppDatabase.getAppDatabase(this); // Initialize the database instance
        Intent intent = getIntent();
        blog = (Blog) intent.getSerializableExtra("blog");
        Log.d("database", "ID: " + blog.getBid());
        if(intent != null) {
            if(intent.hasExtra("blog")) {
                blog = (Blog) intent.getSerializableExtra("blog");
                Log.d("database", "ID: " + blog.getBid());
                Log.d("database", "Title: " + blog.getBlogTitle());
                Log.d("database", "Description: " + blog.getBlogDescription());

                etTitle.setText(blog.getBlogTitle());
                etDesc.setText(blog.getBlogDescription());
                etUsername.setText(blog.getBlogUserName());

            }
        }
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blog.setBlogTitle(etTitle.getText().toString());
                blog.setBlogDescription(etDesc.getText().toString());
                blog.setBlogUserName(etUsername.getText().toString());
                blog.setBlogImage("blog_img");
                blog.setBlogUserImg("user_img");
                if (database != null) {
                    database.blogDao().updateOne(blog);
                    Log.d("database", "ID:update " );
                    Log.d("database", "ID: " + blog.getBid());
                    Log.d("database", "Title: " + blog.getBlogTitle());
                    Log.d("database", "Description: " + blog.getBlogDescription());
                    Intent intent = new Intent(v.getContext(), BlogsActivity.class);
                    v.getContext().startActivity(intent);
                } else {
                    Log.e("database", "Database is null");
                }
            }
        });
    }
}