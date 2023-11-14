package com.example.stockin.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stockin.R;
import com.example.stockin.database.AppDatabase;
import com.example.stockin.model.Blog;

import java.util.ArrayList;
import java.util.List;

public class BlogsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BlogAdapter blogAdapter;
    private Button blogFrom;
    private AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);
        List<Blog> blogPosts = new ArrayList<>();
        /*blogPosts.add(new Blog(1,"Titre 1", "deihkncas 1", "Auteur 1","blog_img","user_img"));
        blogPosts.add(new Blog(1,"Titre 1", "deihkncas 1", "Auteur 1","blog_img","user_img"));
        blogPosts.add(new Blog(1,"Titre 1", "deihkncas 1", "Auteur 1","blog_img","user_img"));
        blogPosts.add(new Blog(1,"Titre 1", "deihkncas 1", "Auteur 1","blog_img","user_img"));
        blogPosts.add(new Blog(1,"Titre 1", "deihkncas 1", "Auteur 1","blog_img","user_img"));
*/
        // Ajoutez d'autres articles
        database = AppDatabase.getAppDatabase(this);
        blogPosts = database.blogDao().getAll();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        blogAdapter = new BlogAdapter(blogPosts);
        recyclerView.setAdapter(blogAdapter);
        blogFrom=findViewById(R.id.blogForm);
        blogFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BlogFormActivity.class);

                v.getContext().startActivity(intent);
            }
        });



    }


}