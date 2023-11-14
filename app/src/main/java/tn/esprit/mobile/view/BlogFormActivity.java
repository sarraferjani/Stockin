package tn.esprit.mobile.view;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.mobile.R;
import tn.esprit.mobile.database.AppDatabase;
import tn.esprit.mobile.model.BadWords;
import tn.esprit.mobile.model.Blog;


public class BlogFormActivity extends AppCompatActivity {
    private AppDatabase database;
    private EditText etTitle;
    private EditText etDesc;
    private EditText etUsername;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(tn.esprit.mobile.R.layout.activity_blog_form);

        etTitle = findViewById(R.id.etBlogTitle);
        etDesc = findViewById(R.id.etBlogDesc);
        etUsername = findViewById(R.id.etUserName);
        saveBtn = findViewById(R.id.btnSaveBlog);

        database = AppDatabase.getAppDatabase(this); // Initialize the database instance

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Blog blog = new Blog();
                blog.setBlogTitle(etTitle.getText().toString());
                String filteredDescription = filterBadWords(etDesc.getText().toString());
                blog.setBlogDescription(filteredDescription);
                blog.setBlogUserName(etUsername.getText().toString());
                blog.setBlogImage("blog_img");
                blog.setBlogUserImg("user_img");
                if (database != null) {
                    database.blogDao().insertOne(blog);
                    Log.d("database", "ID: " + blog.getBid());
                    Log.d("database", "Title: " + blog.getBlogTitle());
                    Log.d("database", "Description: " + filterBadWords(blog.getBlogDescription()));
                    Intent intent = new Intent(v.getContext(), BlogsActivity.class);
                    v.getContext().startActivity(intent);
                } else {
                    Log.e("database", "Database is null");
                }
            }
        });
    }
    private String filterBadWords(String description) {
        for (String badWord : BadWords.BAD_WORDS) {
            description = description.replaceAll("(?i)" + badWord, "*filtered*");
        }
        return description;
    }
}
