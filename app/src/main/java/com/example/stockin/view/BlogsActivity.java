package com.example.stockin.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.stockin.R;
import com.example.stockin.model.Blog;

import java.util.ArrayList;
import java.util.List;

public class BlogsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BlogAdapter blogAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);
        List<Blog> blogPosts = new ArrayList<>();
        blogPosts.add(new Blog("Titre 1", "deihkncas 1", "Auteur 1","blog_img","user_img"));
        blogPosts.add(new Blog("Titre 1", "deihkncas 1", "Auteur 1","blog_img","user_img"));
        blogPosts.add(new Blog("Titre 1", "deihkncas 1", "Auteur 1","blog_img","user_img"));
        blogPosts.add(new Blog("Titre 1", "deihkncas 1", "Auteur 1","blog_img","user_img"));
        blogPosts.add(new Blog("Titre 1", "deihkncas 1", "Auteur 1","blog_img","user_img"));
        // Ajoutez d'autres articles

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        blogAdapter = new BlogAdapter(blogPosts);
        recyclerView.setAdapter(blogAdapter);
    }
}